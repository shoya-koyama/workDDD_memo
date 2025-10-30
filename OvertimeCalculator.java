package com.naosim.dddwork.domain;

public class OvertimeCalculator {
    private static final int REGULAR_WORKING_HOURS = 8; // 定時勤務時間

    private final WorkTimeCalculator workTimeCalculator;

    // コンストラクタで勤務時間計算クラスを注入（依存関係逆転の原則）
    public OvertimeCalculator(WorkTimeCalculator workTimeCalculator) {
        this.workTimeCalculator = workTimeCalculator;
    }

    public int calculateOvertimeMinutes(WorkTime workTime) {
        int workMinutes = workTimeCalculator.calculateWorkMinutes(workTime);
        int regularMinutes = REGULAR_WORKING_HOURS * 60;
        return Math.max(workMinutes - regularMinutes, 0);
    }
}
