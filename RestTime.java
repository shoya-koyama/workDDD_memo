package com.naosim.dddwork.domain;

import java.time.LocalTime;

/**
 * 休憩時間帯を表すクラス
 */
public class RestTime {
    private final LocalTime start;
    private final LocalTime end;

    public RestTime(int startHour, int startMinute, int endHour, int endMinute) {
        this.start = LocalTime.of(startHour, startMinute);
        this.end = LocalTime.of(endHour, endMinute);
    }

    public LocalTime getStart() {
        return start;
    }

    public LocalTime getEnd() {
        return end;
    }

    /**
     * 勤務時間内での休憩重複時間（分）を計算
     */
    public int getOverlapMinutes(LocalTime workStart, LocalTime workEnd) {
        LocalTime overlapStart = workStart.isBefore(start) ? start : workStart;
        LocalTime overlapEnd = workEnd.isAfter(end) ? end : workEnd;

        if (overlapEnd.isAfter(overlapStart)) {
            return (int) java.time.Duration.between(overlapStart, overlapEnd).toMinutes();
        }
        return 0;
    }
}
