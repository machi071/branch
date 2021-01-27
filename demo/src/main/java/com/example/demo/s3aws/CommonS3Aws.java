//package com.example.demo.s3aws;
//
//import java.io.File;
//
//import javax.annotation.PostConstruct;
//
//import org.springframework.beans.factory.annotation.Value;
//import org.springframework.stereotype.Component;
//
//import com.amazonaws.AmazonServiceException;
//import com.amazonaws.SdkClientException;
//import com.amazonaws.services.s3.AmazonS3;
//import com.amazonaws.services.s3.AmazonS3ClientBuilder;
//import com.example.demo.log.ILogger;
//
///**
// * AWS共通クラス
// */
//
//@Component
//public class CommonS3Aws implements ILogger {
//
//    /** BUCKET名 */
//    @Value("${com.team.kc.bucketName}")
//    private String BUCKET_NAME;
//
//    /** KC_KEY名 */
//    final private String KC_KEY_NAME = "data01/KEIYAKU.aws";
//    /** KDR_KEY名 */
//    final private String KDR_KEY_NAME = "data01/KDR.aws";
//
//    /** リージョン名 */
//    @Value("${com.team.kc.region}")
//    private String REGION_NAME;
//
//    private AmazonS3 s3;
//
//    @PostConstruct
//    public void method() {
//        s3 = AmazonS3ClientBuilder.standard().withRegion(REGION_NAME).build();
//    }
//
//    /**
//     * ファイル転送処理
//     * <br>
//     * @author nagashimasb
//     * @param filePath put対象のファイルパス
//     * @return 無し
//     */
//    public void putS3Aws(String KCFilePath, String KDRFilePath) {
//        log.info(START, "CommonS3Aws");
//
//        try {
//            log.info(MSG75_99_00002, KCFilePath, BUCKET_NAME);
//
//            s3.putObject(BUCKET_NAME, KC_KEY_NAME, new File(KCFilePath));
//            s3.putObject(BUCKET_NAME, KDR_KEY_NAME, new File(KDRFilePath));
//        } catch (AmazonServiceException e) {
//            log.error(MSG75_99_00004, KCFilePath, BUCKET_NAME);
//        } catch (SdkClientException e) {
//            log.error(MSG75_99_00005, KCFilePath);
//        } catch (Exception e) {
//            log.error(MSG75_99_00005, KCFilePath);
//        }
//
//        log.info(END, "CommonS3Aws");
//    }
//
//    /**
//     * ファイル存在チェック処理
//     * <br>
//     * @author nagashimasb
//     * @param key チェック対象のファイルパス
//     * @return boolean型の結果
//     */
//    public boolean existsS3File(String key) {
//        //param確認用のコメント
//        if (log.isDebugEnabled()) {
//            log.debug(BUCKET_NAME, key);
//        }
//
//        try {
//            boolean result = s3.doesObjectExist(BUCKET_NAME, key);
//
//            return result;
//        } catch (AmazonServiceException e) {
//            if (e.getStatusCode() != 404) {
//                log.error(MSG_75_50_00100, e);
//                throw e;
//            }
//
//            return false;
//        } catch (SdkClientException e) {
//            log.error(MSG75_99_00005, BUCKET_NAME, key);
//            return false;
//        }
//    }
//}
