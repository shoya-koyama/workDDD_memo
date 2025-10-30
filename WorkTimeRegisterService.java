package com.naosim.dddwork.service;

import com.naosim.dddwork.domain.*;

/**
 * 勤務時間登録のサービスクラス
 */
public class WorkTimeRegisterService {
    private final WorkDataRepository workDataRepository;
    private final WorkRule rule;
    private final OverWorkTime overWorkTime;

    public WorkTimeRegisterService(WorkDataRepository workDataRepository) {
        this.workDataRepository = workDataRepository;
        this.rule = new WorkRule();
        this.overWorkTime = new OverWorkTime();
    }

    public void registerWorkTime(WorkTime workTime) {
        // 実働時間を計算
        int workMinutes = workTime.calculateWorkMinutes(rule);

        // 残業時間を計算
        int overWorkMinutes = overWorkTime.calculateOverWorkMinutes(workMinutes, rule);

        // データ保存
        WorkTimeStorage storage = new WorkTimeStorage(workMinutes, overWorkMinutes);
        workDataRepository.save(workTime, storage);
    }
}
