package com.naosim.dddwork.domain;

import java.util.Arrays;
import java.util.List;

public class WorkTimeCalculator {
    private static final List<Integer> REST_HOURS = Arrays.asList(12, 18, 21); // 休憩時間の開始時刻

    public int calculateWorkMinutes(WorkTime workTime) {
        int startMinutes = workTime.getWorkingStartHour().getHour() * 60 + workTime.getWorkingStartMinute().getMinute();
        int endMinutes = workTime.getWorkingEndHour().getHour() * 60 + workTime.getWorkingEndMinute().getMinute();
        int workMinutes = endMinutes - startMinutes;

        // --- 休憩時間を差し引く ---
        for (int restHour : REST_HOURS) {
            if (workTime.getWorkingEndHour().getHour() == restHour) {
                workMinutes -= workTime.getWorkingEndMinute().getMinute();
            } else if (workTime.getWorkingEndHour().getHour() >= restHour + 1) {
                workMinutes -= 60;
            }
        }

        if (workMinutes < 0)
            throw new IllegalArgumentException("時間が不正です。");

        return workMinutes;
    }
}
