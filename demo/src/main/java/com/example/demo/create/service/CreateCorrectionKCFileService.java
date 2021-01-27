package com.example.demo.create.service;

import java.util.Date;
import java.util.List;

import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;

import com.example.demo.constant.InsuredTypeEnum;
import com.example.demo.dto.AdditionFaceDto;

/**
 * 訂正計上KCファイル作成クラス
 * 
 * @author machitb
 *
 */
@Component
public class CreateCorrectionKCFileService extends CreateKCFileService {

	/** 訂正データ（コープ） */
	private List<AdditionFaceDto> additionFaceCoopDtoList;

	/** 訂正データ（学M） */
	private List<AdditionFaceDto> additionFaceUniversityDtoList;

	/** 団体保険Dao */
//	@Autowired
//	private GroupInsuranceDao groupInsuranceDao;

	/**
	 * KCファイル作成処理 <br>
	 * 
	 * @param executeDate
	 */
	public void createCorrectionKCFile(Date executeDate, String middleFilePath, String outputFilePath) {

		// データ取得処理
		this.getData();

		// 取得データが０件の場合、空ファイルを作成して処理を終了する。
		if (CollectionUtils.isEmpty(additionFaceCoopDtoList)
				&& CollectionUtils.isEmpty(additionFaceUniversityDtoList)) {
			// TODO 出力用ファイル作成処理
			return;
		}

		// KCファイル作成処理（コープ）
		super.createKCFile(additionFaceCoopDtoList, middleFilePath, executeDate, InsuredTypeEnum.COOP);
		// KCファイル作成処理（学M）
		super.createKCFile(additionFaceUniversityDtoList, middleFilePath, executeDate, InsuredTypeEnum.UNIVERSITY);

		// KCファイルコード変換処理
		super.convertCode(middleFilePath, outputFilePath);

		// 登録処理
		super.insertDate();

		// 更新処理
		super.updateDate();

	}

	/**
	 * データ取得処理
	 */
	private void getData() {
		// TODO
	}

}
