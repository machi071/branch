package com.example.demo.log;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * ロガー
 * <br>
 * ログを出力するクラスが実装してください。
 */
public interface ILogger {

    /** 業務システム異常 */
    /** 桁数エラー */
    public static final String MSG_75_20_00010 = "75_20_00010_length_error = [{}][{}][{}]";
    /** 外字エラー */
    public static final String MSG_75_20_00020 = "75_20_00020_gaiji_error =[{}][{}][{}]";
    /** 電話番号不整合 */
    public static final String MSG_75_20_00030 = "75_50_00020_phoneNumber_error = [{}]";

    /** システム異常 */
    public static final String MSG_75_50_00100 = "75_50_00100_error = [{}]";

    /** DB異常 */
    public static final String MSG_75_40_00010 = "MSG_75_40_00010_DB_error";

    /** infoログ */
    /** 処理開始 */
    public static final String START = "start_{}";
    /** 処理終了 */
    public static final String END = "end_{}";
    /** DB取得件数*/
    public static final String GET_ROW = "{}row_returned";
    /** DBアクセス開始 */
    public static final String START_SQL_SESSION = "start_sql_session";
    /** DBアクセス終了 */
    public static final String END_SQL_SESSION = "end_sql_session";
    /** 前回計上分取得ログ */
    public static final String RETRY = "retry_lastTime_accounting_KCFile";
    /** 営業日 */
    public static final String TODAY_BUSINESS_DAY = "today_is_business_day";
    /** 休日・祝日 */
    public static final String TODAY_HOLIDAY = "today_is_holiday";
    /** 営業日外実行時処理終了ログ */
    public static final String PROCESSING_END = "Execution_outside_business_days_Processing end";

    /** その他 */
    public static final String MSG75_99_00002 = "Uploading {} to S3 bucket {}";
    public static final String MSG75_99_00003 = "Uploading {} to S3 bucket {} completed";
    public static final String MSG75_99_00004 = "Uploading {} to S3 bucket {} failed";
    public static final String MSG75_99_00005 = "The specified file {} could not be found";

    /** ロガー */
    Logger log = LoggerFactory.getLogger(new Throwable().getStackTrace()[1].getClassName());

}
