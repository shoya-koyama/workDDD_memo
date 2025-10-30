package com.naosim.dddwork.domain;

import java.time.LocalTime;

public class WorkTimeCalculator {

    /**
     * 実働時間（分）を計算
     */
    public int calculateWorkMinutes(WorkTime workTime) {
        LocalTime start = LocalTime.of(
                workTime.getWorkingStartHour().getHour(),
                workTime.getWorkingStartMinute().getMinute()
        );
        LocalTime end = LocalTime.of(
                workTime.getWorkingEndHour().getHour(),
                workTime.getWorkingEndMinute().getMinute()
        );

        int totalMinutes = (int) java.time.Duration.between(start, end).toMinutes();

        // 休憩時間の重複分を減算
        for (RestTime rest : WorkRule.REST_TIMES) {
            totalMinutes -= rest.getOverlapMinutes(start, end);
        }

        if (totalMinutes < 0) {
            throw new IllegalArgumentException("時間が不正です。");
        }

        return totalMinutes;
    }

    /**
     * 残業時間（分）を計算
     */
    public int calculateOverWorkMinutes(WorkTime workTime) {
        int workMinutes = calculateWorkMinutes(workTime);
        int regularMinutes = WorkRule.REGULAR_WORKING_HOURS * 60;
        return Math.max(workMinutes - regularMinutes, 0);
    }
}
