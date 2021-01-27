package com.example.demo.create.service;

import java.io.File;
import java.io.FileWriter;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.example.demo.convert.ConvertCode;
import com.example.demo.dao.GroupDao;
import com.example.demo.dto.AdditionFaceDto;
import com.example.demo.dto.DetailInvoiceDto;
import com.example.demo.edit.service.EditKCFileItem;
import com.example.demo.log.ILogger;
import com.example.demo.util.CommonUtils;

/**
 * 計上レコードのKCファイル作成機能
 * <br>
 * @author katomab
 *
 */
@Component
public class CreateAccoutingKcFile implements ILogger{

    /** KDRレコードの固定値部分 */
    private static final String KDR_COOP_RECORD = "KDRCOOP";

    /** KDRレコードの固定値部分 */
    private static final String KDR_UNIVERSITY_RECORD = "KDRUNIVERSITY";

    /** KDRレコード半角スペース数 */
    private static final int KDR_COOP_RECORD_HALF_SPACE_COUNT = 20;

    /** KDRレコード半角スペース数 */
    private static final int KDR_UNIVERSITY_RECORD_HALF_SPACE_COUNT = 40;

    /** 半角スペース */
    private static final String HALF_SPACE = " ";

    /** 加入フェース～被保険者Mapper */
    @Autowired
    private GroupDao groupDao;

    /** KCファイル項目編集機能 */
    @Autowired
    private EditKCFileItem editKCFileItem;

    /** コード変換クラス */
    @Autowired
    private ConvertCode convertCode;

    /** 加入フェースリスト */
    List<AdditionFaceDto> afList = new ArrayList<>();

	/**
     * KCファイル作成処理
     * <br>
	 * @param executeDate バッチ実行日
	 * @param dirPath ディレクトリパス
	 * @param middleFilePath 中間ファイルパス
	 * @param outputFilePath 出力ファイルパス
	 */
    public void createAccoutingKcFile(Date executeDate, String dirPath, String middleFilePath, String outputFilePath) {

    	// データ取得処理
    	this.getData();
    	// KCファイル作成処理
    	this.outputEditKc(executeDate, middleFilePath);
		// コード変換処理(.txt→.awsとEBCDICへ)
		this.convertCode.convert(middleFilePath, outputFilePath);
//    	// 履歴作成処理(加入フェース履歴、明細送り状履歴、明細送り状)
//		this.groupDao.insertAdditionFace(afList);
//		// 更新処理（加入フェース作成日、異動発生日、明細計上月、最終送付サイン、明細送り状枚数）
//		this.groupDao.updateAdditionFace(afList);

    }

    /**
     * KCファイル出力処理
     * <br>
     * 文字列編集処理後、KCファイルに出力する
     * <br>
     * @param executeDate バッチ実行日
	 * @param middleFilePath 中間ファイルパス
     */
    public void outputEditKc(Date executeDate, String middleFilePath) {

    	// KCファイル出力用文字列
		StringBuilder outputKc = new StringBuilder();
        try (FileWriter fileWriter = new FileWriter(new File(middleFilePath), true)){

        	boolean flg = false;
	    	// 加入フェース
	    	for (AdditionFaceDto additionFaceDto: afList) {

	    		// フラグが"1"の場合コープのKDRセット
	    		if (!flg) {
	    		    outputKc.append(addKDRRecord(KDR_COOP_RECORD, KDR_COOP_RECORD_HALF_SPACE_COUNT, executeDate));
	    		    flg =true;
	    		} else {
	    		    outputKc.append(addKDRRecord(KDR_UNIVERSITY_RECORD, KDR_UNIVERSITY_RECORD_HALF_SPACE_COUNT, executeDate));
	    		}
	    		// 加入フェース編集処理
	    		editKCFileItem.editAdditionFace(additionFaceDto, executeDate);
	    		// 最終送付サインを"1"にセット
	    		additionFaceDto.setLastSendSign("1");
		   		// 出力加入フェース編集処理・中間ファイル作成
	    		outputKc.append(editKCFileItem.editOutputAdditionFace(additionFaceDto));

	    		// 明細送り状
		   		for (DetailInvoiceDto detailInvoiceDto: additionFaceDto.getDetailInvoiceDtoList()) {

		   			// 明細送り状編集処理
		   			editKCFileItem.editDetailInvoice(detailInvoiceDto, executeDate);

		   			// 更新（ここでするかは未定）


			   		// 出力明細送り状編集処理・中間ファイル作成
		   			outputKc.append(editKCFileItem.editOutputDetailInvoice(detailInvoiceDto));
		   		}

		   		// ファイル書き込み
		   		fileWriter.write(outputKc.toString());
		   		// 初期化
		   		outputKc.setLength(0);

		   	}

	    } catch (Exception e) {
            log.error("", e);
	    }
    }

    /**
     * データ取得処理
     * <br>
     * DBから加入フェース、明細送り状を呼び出す
     */
    public void getData() {
		afList = this.groupDao.getAdditionFaceDto();
    }
    /**
     * 日付を追加し半角スペース埋めする
     * @param executeDate
     * @return
     */
    public String addKDRRecord(String kdrVal, int halfSpace, Date executeDate) {
        // 現在日付をGYYMMDDで取得
        CommonUtils utils = new CommonUtils();
        String sysDate = utils.getJapaneseCalendar(executeDate);

        // KDRレコードファイルに出力する値を作成
        StringBuilder sb = new StringBuilder();
        sb.append(kdrVal);
        sb.append(sysDate);

        // 半角スペース埋め
        for (int i = 0; i < halfSpace; i++) {
            sb.append(HALF_SPACE);
        }
        return sb.toString();
    }

}
