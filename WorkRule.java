package com.naosim.dddwork.domain;

import java.util.List;

/**
 * 勤務に関する規定値（定時・休憩時間など）を管理するクラス
 */
public class WorkRule {
    public static final int REGULAR_WORKING_HOURS = 8;

    public static final List<RestTime> REST_TIMES = List.of(
            new RestTime(12, 0, 13, 0),  // 昼休憩 12:00〜13:00
            new RestTime(15, 0, 15, 30), // 15時休憩 15:00〜15:30
            new RestTime(18, 0, 18, 30)  // 18時休憩 18:00〜18:30
    );

    private WorkRule() {}
}
