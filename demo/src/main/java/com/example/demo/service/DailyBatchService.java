package com.example.demo.service;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.example.demo.create.service.CreateCorrectionKCFileService;
import com.example.demo.create.service.CreateMidwayKCFileService;
import com.example.demo.util.CommonUtils;

/**
 * 日次バッチサービス
 * 
 * @author machitb
 *
 */
@Component
public class DailyBatchService {

    /** ファイル格納用ディレクトリパス */
    private static final String DIR_PATH = "";

    /** 中間ファイルパス（訂正） */
    private static final String CORRECTION_MIDDLE_PATH = "";

    /** 中間ファイルパス（中途） */
    private static final String MIDWAY_MIDDLE_PATH = "";

    /** 転送用KCファイルパス */
    private static final String OUTPUT_KCFILE_PATH = "";

    /** 訂正データKCファイル作成サービス */
    @Autowired
    private CreateCorrectionKCFileService correctionService;

    /** 中途データKCファイル作成サービス */
    @Autowired
    private CreateMidwayKCFileService midwayService;

    /** 共通処理クラス */
    @Autowired
    private CommonUtils commonUtils;

    /** AWS共通クラス */

    /** カレンダーDao */

    /**
     * バッチ実行処理
     * 
     * @param executeDate バッチ実行日
     */
    public void executeBatch(Date executeDate) {

        // 実行日判定処理
        if (!this.checkExecuteDate(executeDate)) {
            // 実行対象外の場合処理を終了する。
            return;
        }

        // ディレクトリ作成処理
        commonUtils.createDirectory(DIR_PATH);

        // 訂正KCファイル作成
        this.correctionService.createCorrectionKCFile(executeDate, CORRECTION_MIDDLE_PATH, OUTPUT_KCFILE_PATH);

        // 中途KCファイル作成
        this.midwayService.createMidwayKCFile(executeDate, MIDWAY_MIDDLE_PATH, OUTPUT_KCFILE_PATH);

        // TODO ファイルチェック
        // 対象ファイルが生成されていない場合、０バイトのKCファイルを作成する。

        // S3プット処理

        // ディレクトリ削除処理
        commonUtils.deleteDirectory(DIR_PATH);

    }

    /**
     * 実施日判定処理
     * <p>
     * 週次バッチ実行対象日か判定する。
     * 
     * @param executeDate バッチ実行日
     * @return true（実施対象）／false（実施対象外）
     * 
     */
    private boolean checkExecuteDate(Date executeDate) {
        // バッチ実行日を抽出条件にカレンダーテーブルを取得する。
        return true;
    }
}
