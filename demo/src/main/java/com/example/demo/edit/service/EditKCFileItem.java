package com.example.demo.edit.service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.example.demo.dto.AdditionFaceDto;
import com.example.demo.dto.DetailInvoiceDto;
import com.example.demo.dto.InsuredDto;
import com.example.demo.dto.InsuredPersonCoopDto;
import com.example.demo.dto.InsuredPersonUniversityDto;
import com.example.demo.util.CommonUtils;

/**
 * KCファイル項目編集機能 <br>
 * 
 * @author katomab
 */

@Component
public class EditKCFileItem {
	/** コロン */
	private static final String COLON = ":";
	/** 半角スペース */
	private static final String HALF_SPACE = " ";
	/** 中間ファイル半角スペース区切り用 */
	private static final int ADDITIONFACE_FILLER1 = 38;
	/** 中間ファイル半角スペース区切り用 */
	private static final int DETAILINVOICE_FILLER1 = 35;
	/** 中間ファイル半角スペース区切り用 */
	private static final int INSURED_FILLER1 = 43;
	/** 中間ファイル半角スペース区切り用 */
	private static final int INSURED_FILLER2 = 14;
	/** 中間ファイル半角スペース区切り用 */
	private static final int INSURED_FILLER3 = 48;
	/** 中間ファイル半角スペース区切り用 */
	private static final int INSURED_FILLER4 = 74;
	/** 中間ファイル半角スペース区切り用 */
	private static final int INSURED_PERSON_COOP_FILLER1 = 27;
	/** 中間ファイル半角スペース区切り用 */
	private static final int INSURED_PERSON_COOP_FILLER2 = 140;
	/** 中間ファイル半角スペース区切り用 */
	private static final int INSURED_PERSON_UNIVERSITY_FILLER1 = 41;
	/** 中間ファイル半角スペース区切り用 */
	private static final int INSURED_PERSON_UNIVERSITY_FILLER2 = 77;
	/** 中間ファイル半角スペース区切り用 */
	private static final int INSURED_PERSON_UNIVERSITY_FILLER3 = 107;

	/** 共通処理クラス */
	@Autowired
	private CommonUtils commonUtils;

	/** モデルマッパー */
//	@Autowired
	ModelMapper modelMapper;

	/**
	 * 加入フェース編集処理 <br>
	 * 
	 * @param dto         加入フェース
	 * @param executeDate バッチ実行日
	 */
	public void editAdditionFace(AdditionFaceDto dto, Date executeDate) {

		// 加入フェース作成日(和暦変換)
		dto.setAdditionFaceCreated(commonUtils.getJapaneseCalendar(executeDate));
		// 明細計上月
		dto.setInvoiceAccountingMonth(commonUtils.getMonth(executeDate));
		// 最終送付サイン(一律"0"を設定。月次の場合は月次バッチ処理内で個別に"1"を設定)
		dto.setLastSendSign("0");
		// 明細送り状枚数
		dto.setDeliveryinvoiceNum(String.valueOf(dto.getDetailInvoiceDtoList().size()));
		// 部店・課支社ｺｰﾄﾞ(英大文字変換)
		dto.setDepartmentCode(dto.getDepartmentCode().toUpperCase());
		// 代理店ｺｰﾄﾞ（英大文字変換）
		dto.setAgencyCode(dto.getAgencyCode().toUpperCase());
		// 異動発生日(和暦変換)
		dto.setTransferDate(commonUtils.getJapaneseCalendar(commonUtils.convertStrDate(dto.getTransferDate())));

	}

	/**
	 * 明細送り状編集処理 <br>
	 * 
	 * @param dto         明細送り状
	 * @param executeDate バッチ実行日
	 */
	public void editDetailInvoice(DetailInvoiceDto dto, Date executeDate) {

		// 加入者数
		dto.setInsuredNum(dto.getInsuredDtoList().size());
		// 計上日
		dto.setAccountingDate(executeDate);
		// 加入フェースNo
		dto.setAdditionFaceNo("1");
		// 保険料チェック不要サイン
		dto.setInsuranceFeeCertificateSign("0");
		// 部店課支社(英大文字変換)
		dto.setDepartmentCode(dto.getDepartmentCode().toUpperCase());
		// 代理店ｺｰﾄﾞ(英大文字変換)
		dto.setAgencyCode(dto.getAgencyCode().toUpperCase());
		// 団体コード(英大文字変換)
		dto.setGroupCode(dto.getGroupCode().toUpperCase());
		// 団体コード枝番(英大文字変換)
		dto.setGroupCodeNumber(dto.getGroupCodeNumber().toUpperCase());

	}

	/**
	 * コープ・学M加入者編集処理 <br>
	 * 
	 * @param dto コープ、学M加入者
	 */
	public void editInsured(InsuredDto dto) {

		// 加入者番号(英大文字変換)
		dto.setInsuredNo(dto.getInsuredNo().toUpperCase());
		// 起票日(和暦変換)
		dto.setDraftDate(commonUtils.getJapaneseCalendar(commonUtils.convertStrDate(dto.getDraftDate())));
		// 所属名(半角変換・英大文字変換)
		dto.setBelongName(commonUtils.convertHalfWidthKana(dto.getBelongName()).toUpperCase());
		// 所属コード(英大文字変換)
		dto.setBelongCode(dto.getBelongCode().toUpperCase());
		// 団体扱社員番号(英大文字変換)
		dto.setGroupEmployeeNumber(dto.getGroupEmployeeNumber().toUpperCase());
		// 加入者氏名(カナ)(半角変換)
		dto.setInsuredNameKana(commonUtils.convertHalfWidthKana(dto.getInsuredNameKana()));
		// 加入者住所 -都道府県、市区町村、丁目(カナ)(半角変換・英大文字変換)
		dto.setAddressKana(commonUtils.convertHalfWidthKana(dto.getAddressKana()).toUpperCase());
		// 加入者生年月日（和暦変換）
		dto.setInsuredBirthday(commonUtils.getJapaneseCalendar(commonUtils.convertStrDate(dto.getInsuredBirthday())));
		// TODO 加入者合計保険料（SQLで計算するか、ゼロ埋めなど）
	}

	/**
	 * コープ被保険者編集処理 <br>
	 * 
	 * @param dto コープ被保険者DTO
	 */
	public void editInsuredPersonCoop(InsuredPersonCoopDto dto) {

		// 被保険者氏名(カナ)（半角カナ変換）
		dto.setInsuredPersonNameKana(commonUtils.convertHalfWidthKana(dto.getInsuredPersonNameKana()));
		// 被保険者年齢（誕生日、始期日から算出）
//		dto.setInsuredAge(this.commonUtils.calcAge(dto.getInsuredBirthday(), 始期日));
		// 被保険者生年月日（和暦変換）
		dto.setInsuredPersonBirthday(
				commonUtils.getJapaneseCalendar(commonUtils.convertStrDate(dto.getInsuredPersonBirthday())));
		// 健康状況告知日(和暦変換)
		dto.setHealthStatusNoticeDate(
				commonUtils.getJapaneseCalendar(commonUtils.convertStrDate(dto.getHealthStatusNoticeDate())));

	}

	/**
	 * 学M被保険者情報編集処理 <br>
	 * 
	 * @param dto 学M被保険者情報
	 */
	public void editInsuredPersonUniversity(InsuredPersonUniversityDto dto) {

		// 被保険者氏名(カナ)（半角カナ変換）
		dto.setInsuredPersonNameKana(commonUtils.convertHalfWidthKana(dto.getInsuredPersonNameKana()));
		// 被保険者年齢（誕生日、始期日から算出）
//		dto.setInsuredPersonAge(this.commonUtils.calcAge(dto.getInsuredBirthday(), 始期日));
		// 被保険者生年月日（和暦変換）
		dto.setInsuredPersonBirthday(
				commonUtils.getJapaneseCalendar(commonUtils.convertStrDate(dto.getInsuredPersonBirthday())));
//		// 扶養者と被保険者との関係(父は母にあてまらない場合、半角カナ単純移送)
//		dto.setDependentsRelationship();
		// 扶養者氏名（半角カナ）
		dto.setDependentsName(commonUtils.convertHalfWidthKana(dto.getDependentsName()));
		// TODO 職業名・職種名（固定値）
	}

	/**
	 * 出力用加入フェース編集処理 <br>
	 * 加入フェースの項目をKCファイル出力用に編集します。 <br>
	 * 
	 * @param dto 加入フェース
	 * @return KCファイル出力用文字
	 */
	public String editOutputAdditionFace(AdditionFaceDto dto) {

		// 項目コードの設定、半角スペース埋め、Fillerをセット
		StringBuilder outputItem = new StringBuilder();

		// データ種類
		outputItem.append(dto.getDataType());
		// ＭＡＣＳ識別コード
		outputItem.append(commonUtils.addCodeFillUpSpace(dto.getMacsCode(), "R00", HALF_SPACE, COLON));
		// 明細計上月
		outputItem.append(commonUtils.addCodeFillUpSpace(dto.getInvoiceAccountingMonth(), "001", HALF_SPACE, COLON));
		// 加入フェース作成日
		outputItem.append(commonUtils.addCodeFillUpSpace(dto.getAdditionFaceCreated(), "R14", HALF_SPACE, COLON));
		// 部店・課支社ｺｰﾄﾞ
		outputItem.append(
				commonUtils.addCodeFillUpSpace(dto.getDepartmentCode(), "023", HALF_SPACE, COLON).toUpperCase());
		// 代理店ｺｰﾄﾞ
		outputItem.append(commonUtils.addCodeFillUpSpace(dto.getAgencyCode(), "025", HALF_SPACE, COLON).toUpperCase());
		// 代表POL(代表証券番号)
		outputItem.append(commonUtils.addCodeFillUpSpace(dto.getRepresentativePolicyNo(), "021", HALF_SPACE, COLON));
		// 種目
		outputItem.append(commonUtils.addCodeFillUpSpace(dto.getItem(), "NNN", HALF_SPACE, COLON));
		// 新異区分
		outputItem.append(commonUtils.addCodeFillUpSpace(dto.getNewClassification(), "005", HALF_SPACE, COLON));
		// 送付方法(最終送付サイン)
		outputItem.append(commonUtils.addCodeFillUpSpace(dto.getLastSendSign(), "R13", HALF_SPACE, COLON));
		// 全分割回数
		outputItem.append(commonUtils.addCodeFillUpSpace(dto.getBunkatuKisu(), "R16", HALF_SPACE, COLON));
		// 送付（営業）課支社コード
		outputItem.append(commonUtils.addCodeFillUpSpace(dto.getSignAgencyCode(), "R18", HALF_SPACE, COLON));
		// 代理店/仲立人コード
		outputItem.append(commonUtils.addCodeFillUpSpace(dto.getAgencyBrokerCode(), "R19", HALF_SPACE, COLON));
		// 明細送り状枚数
		outputItem.append(commonUtils.addCodeFillUpSpace(dto.getDeliveryinvoiceNum(), "L08", HALF_SPACE, COLON));
		// 異動発生日
		outputItem.append(commonUtils.addCodeFillUpSpace(dto.getTransferDate(), "171", HALF_SPACE, COLON));
		// MS&AD事務S使用欄
		outputItem.append(commonUtils.addCodeFillUpSpace(dto.getMsAd(), "L30", HALF_SPACE, COLON));
		// KF追加サイン
		outputItem.append(commonUtils.addCodeFillUpSpace(dto.getKfAddSign(), "R17", HALF_SPACE, COLON));
		// 追加先入力ID
		outputItem.append(commonUtils.addCodeFillUpSpace(dto.getAddInputSign(), "370", HALF_SPACE, COLON));
		// 追加先バッチSEQ
		outputItem.append(commonUtils.addCodeFillUpSpace(dto.getAddBatchSeq(), "R26", HALF_SPACE, COLON));

		// FILLER
		for (int i = 0; i < ADDITIONFACE_FILLER1; i++) {
			outputItem.append(HALF_SPACE);
		}

		return outputItem.toString();
	}

	/**
	 * 出力用明細送り状編集処理 <br>
	 * 明細送り状の項目をKCファイル出力用に編集する <br>
	 * 
	 * @param dto 明細送り状
	 * @return KCファイル出力用文字
	 */
	public String editOutputDetailInvoice(DetailInvoiceDto dto) {

		// 項目コードの設定、半角スペース埋め、Fillerをセット
		StringBuilder outputItem = new StringBuilder();

		// データ種類
		outputItem.append(dto.getDataType());
		// 自動継続種類
		outputItem.append(commonUtils.addCodeFillUpSpace(dto.getContinuationType(), "R46", HALF_SPACE, COLON));
		// ＭＡＣＳ識別コード
		outputItem.append(commonUtils.addCodeFillUpSpace(dto.getMacsCode(), "R00", HALF_SPACE, COLON));
		// 明細送り状番号
		outputItem.append(commonUtils.addCodeFillUpSpace(dto.getDetailInvoiceId(), "D05", HALF_SPACE, COLON));
		// 部店課支社
		outputItem.append(
				commonUtils.addCodeFillUpSpace(dto.getDepartmentCode(), "D03", HALF_SPACE, COLON).toUpperCase());
		// 代理店ｺｰﾄﾞ
		outputItem.append(commonUtils.addCodeFillUpSpace(dto.getAgencyCode(), "D02", HALF_SPACE, COLON).toUpperCase());
		// 団体コード
		outputItem.append(commonUtils.addCodeFillUpSpace(dto.getGroupCode(), "D06", HALF_SPACE, COLON).toUpperCase());
		// 団体コード枝番
		outputItem.append(
				commonUtils.addCodeFillUpSpace(dto.getGroupCodeNumber(), "D07", HALF_SPACE, COLON).toUpperCase());
		// 控除資料作成要・不要
		outputItem.append(commonUtils.addCodeFillUpSpace(dto.getDeductionSign(), "D12", HALF_SPACE, COLON));
		// 年調資料のみ作成
		outputItem
				.append(commonUtils.addCodeFillUpSpace(String.valueOf(dto.getInsuredNum()), "LLY", HALF_SPACE, COLON));
		// 加入者数
		outputItem.append(commonUtils.addCodeFillUpSpace(dto.getBelongcodeStart(), "R20", HALF_SPACE, COLON));
		// 加入者識別コード
		outputItem.append(commonUtils.addCodeFillUpSpace(dto.getInsuredIdentifyCode(), "L05", HALF_SPACE, COLON));
		// 所属ｺｰﾄﾞｿｰﾄｽﾀｰﾄ
		outputItem.append(commonUtils.addCodeFillUpSpace(dto.getBelongcodeDigits(), "382", HALF_SPACE, COLON));
		// 所属ｺｰﾄﾞｿｰﾄ桁数
		outputItem.append(commonUtils.addCodeFillUpSpace(dto.getBelongcodeDigits(), "383", HALF_SPACE, COLON));
		// 所属転記
		outputItem.append(commonUtils.addCodeFillUpSpace(dto.getBelongPosting(), "385", HALF_SPACE, COLON));
		// ファイルチェック
		outputItem.append(commonUtils.addCodeFillUpSpace(dto.getFileCheck(), "394", HALF_SPACE, COLON));
		// 関連会社番号
		outputItem.append(commonUtils.addCodeFillUpSpace(dto.getRelationCompanyNo(), "D08", HALF_SPACE, COLON));
		// 集計区分
		outputItem.append(commonUtils.addCodeFillUpSpace(dto.getAggregationClassification(), "D09", HALF_SPACE, COLON));
		// 社番桁数
		outputItem.append(commonUtils.addCodeFillUpSpace(dto.getEmployeeDigits(), "D13", HALF_SPACE, COLON));
		// 社番英字有無
		outputItem.append(commonUtils.addCodeFillUpSpace(dto.getEmployeeEnglish(), "D14", HALF_SPACE, COLON));
		// 団体ディコード
		outputItem.append(commonUtils.addCodeFillUpSpace(dto.getGroupDCode(), "376", HALF_SPACE, COLON));
		// 所属コード
		outputItem.append(commonUtils.addCodeFillUpSpace(dto.getBelongcode(), "019", HALF_SPACE, COLON));
		// 強制作成サイン
		outputItem.append(commonUtils.addCodeFillUpSpace(dto.getForceCreateSing(), "KYS", HALF_SPACE, COLON));
		// 加入者証発行不要サイン
		outputItem.append(commonUtils.addCodeFillUpSpace(dto.getInsuredCertificateSign(), "WBT", HALF_SPACE, COLON));
		// 保険料チェック不要サイン
		outputItem
				.append(commonUtils.addCodeFillUpSpace(dto.getInsuranceFeeCertificateSign(), "WBV", HALF_SPACE, COLON));

		// FILLER
		for (int i = 0; i < DETAILINVOICE_FILLER1; i++) {
			outputItem.append(HALF_SPACE);
		}

		return outputItem.toString();
	}

	/**
	 * 出力用コープ・学M加入者編集処理 <br>
	 * 
	 * @param dto コープ・学M加入者情報
	 * @return 出力用文字列
	 *
	 */
	public String editOutputInsured(InsuredDto dto) {

		// 項目コードの設定、半角スペース埋め、Fillerをセット
		StringBuilder outputItem = new StringBuilder();

		// データ種類
		outputItem.append(dto.getDataType());
		// 加入者番号
		outputItem.append(commonUtils.addCodeFillUpSpace(dto.getInsuredNo(), "098", HALF_SPACE, COLON).toUpperCase());
		// 起票日
		outputItem.append(commonUtils.addCodeFillUpSpace(dto.getDraftDate(), "010", HALF_SPACE, COLON));
		// 所属名
		outputItem.append(commonUtils.addCodeFillUpSpace(dto.getBelongName(), "018", HALF_SPACE, COLON).toUpperCase());
		// 所属コード
		outputItem.append(commonUtils.addCodeFillUpSpace(dto.getBelongCode(), "019", HALF_SPACE, COLON).toUpperCase());
		// 団体扱社員番号
		outputItem.append(
				commonUtils.addCodeFillUpSpace(dto.getGroupEmployeeNumber(), "017", HALF_SPACE, COLON).toUpperCase());
		// 加入者氏名(カナ)
		outputItem.append(commonUtils.addCodeFillUpSpace(dto.getInsuredNameKana(), "307", HALF_SPACE, COLON));

		// FILLER
		for (int i = 0; i < INSURED_FILLER1; i++) {
			outputItem.append(HALF_SPACE);
		}

		// 加入者氏名(漢字)
		outputItem.append(commonUtils.addCodeFillUpSpace(dto.getInsuredName(), "341", HALF_SPACE, COLON));
		// 加入者生年月日
		outputItem.append(commonUtils.addCodeFillUpSpace(dto.getInsuredBirthday(), "980", HALF_SPACE, COLON));
		// 加入者性別
		outputItem.append(commonUtils.addCodeFillUpSpace(dto.getInsuredSex(), "982", HALF_SPACE, COLON));
		// 加入者特記事項
		outputItem.append(commonUtils.addCodeFillUpSpace(dto.getInsuredNotices(), "331", HALF_SPACE, COLON));

		// FILLER
		for (int i = 0; i < INSURED_FILLER2; i++) {
			outputItem.append(HALF_SPACE);
		}

		// 電話番号
		outputItem.append(commonUtils.addCodeFillUpSpace(dto.getPhoneNumber(), "011", HALF_SPACE, COLON));
		// 郵便番号
		outputItem.append(commonUtils.addCodeFillUpSpace(dto.getPostNumber(), "012", HALF_SPACE, COLON));
		// 加入者住所 -都道府県、市区町村、丁目(漢字)
		outputItem.append(commonUtils.addCodeFillUpSpace(dto.getAddress(), "399", HALF_SPACE, COLON));

		// FILLER
		for (int i = 0; i < INSURED_FILLER3; i++) {
			outputItem.append(HALF_SPACE);
		}

		// 加入者住所 -都道府県、市区町村、丁目(カナ)
		outputItem.append(commonUtils.addCodeFillUpSpace(dto.getAddressKana(), "317", HALF_SPACE, COLON));
		// データ発生元サイン
		outputItem.append(commonUtils.addCodeFillUpSpace(dto.getDataOriginSign(), "L35", HALF_SPACE, COLON));
		// 加入者合計保険料
		outputItem.append(commonUtils.addCodeFillUpSpace(dto.getTotalInsuranceFee(), "R50", HALF_SPACE, COLON));

		// FILLER
		for (int i = 0; i < INSURED_FILLER4; i++) {
			outputItem.append(HALF_SPACE);
		}

		return outputItem.toString();
	}

	/**
	 * 出力用コープ被保険者編集処理 <br>
	 * 
	 * @param dto 出力用コープ被保険者情報
	 * @return 出力用文字列
	 *
	 */
	public String editOutputInsuredPersonCoop(InsuredPersonCoopDto dto) {

		// 項目コードの設定、半角スペース埋め、Fillerをセット
		StringBuilder outputItem = new StringBuilder();

		// データ種類
		outputItem.append(dto.getDataType());
		// 保険種類
		outputItem.append(dto.getInsuranceType());
		// 符号
		outputItem.append(commonUtils.addCodeFillUpSpace(dto.getCode(), "390", HALF_SPACE, COLON));
		// 被保険者氏名(カナ)
		outputItem.append(commonUtils.addCodeFillUpSpace(dto.getInsuredPersonNameKana(), "J04", HALF_SPACE, COLON));
		// 被保険者氏名(漢字)
		outputItem.append(commonUtils.addCodeFillUpSpace(dto.getInsuredPersonName(), "L67", HALF_SPACE, COLON));
		// 被保険者性別
		outputItem.append(commonUtils.addCodeFillUpSpace(dto.getInsuredPersonSex(), "302", HALF_SPACE, COLON));
		// 被保険者年齢
		outputItem.append(commonUtils.addCodeFillUpSpace(dto.getInsuredPersonAge(), "303", HALF_SPACE, COLON));
		// 被保険者生年月日
		outputItem.append(commonUtils.addCodeFillUpSpace(dto.getInsuredPersonBirthday(), "323", HALF_SPACE, COLON));
		// 保険契約者との続柄
		outputItem.append(commonUtils.addCodeFillUpSpace(dto.getRelationShip(), "L18", HALF_SPACE, COLON));
		// セット販売形式コード
		outputItem.append(commonUtils.addCodeFillUpSpace(dto.getGoodsPlanCode(), "300", HALF_SPACE, COLON));
		// 加入口数
		outputItem.append(commonUtils.addCodeFillUpSpace(dto.getAdditionNum(), "572", HALF_SPACE, COLON));

		// FILLER
		for (int i = 0; i < INSURED_PERSON_COOP_FILLER1; i++) {
			outputItem.append(HALF_SPACE);
		}

		// 他の保険契約有りサイン
		outputItem.append(commonUtils.addCodeFillUpSpace(dto.getOtherAgreemenSign(), "Y34", HALF_SPACE, COLON));
		// 健康状況告知１
		outputItem.append(commonUtils.addCodeFillUpSpace(dto.getHealthStatus1(), "LKA", HALF_SPACE, COLON));
		// 健康状況告知２
		outputItem.append(commonUtils.addCodeFillUpSpace(dto.getHealthStatus2(), "LKH", HALF_SPACE, COLON));
		// 健康状況告知日
		outputItem.append(commonUtils.addCodeFillUpSpace(dto.getHealthStatusNoticeDate(), "LW8", HALF_SPACE, COLON));
		// 他の保険契約等の告知1(疾病通院保険金日額)
		outputItem.append(commonUtils.addCodeFillUpSpace(dto.getOtherInsuranceNotice(), "T29", HALF_SPACE, COLON));
		// Ｓ他保険疾病入院日額
		outputItem.append(
				commonUtils.addCodeFillUpSpace(dto.getOtherDiseaseDailyHospitalization(), "Y38", HALF_SPACE, COLON));

		// FILLER
		for (int i = 0; i < INSURED_PERSON_COOP_FILLER2; i++) {
			outputItem.append(HALF_SPACE);
		}

		return outputItem.toString();

	}

	/**
	 * 出力用学M被保険者編集処理 <br>
	 * 
	 * @param dto 出力用学M被保険者情報
	 * @return 出力用文字列
	 *
	 */
	public String editOutputInsuredPersonUniversity(InsuredPersonUniversityDto dto) {

		// 項目コードの設定、半角スペース埋め、Fillerをセット
		StringBuilder outputItem = new StringBuilder();

		// データ種類
		outputItem.append(dto.getDataType());
		// 保険種類
		outputItem.append(dto.getInsuranceType());
		// 符号
		outputItem.append(commonUtils.addCodeFillUpSpace(dto.getCode(), "390", HALF_SPACE, COLON));
		// 保種サブNO
		outputItem.append(commonUtils.addCodeFillUpSpace(dto.getInsuranceTypeNo(), "374", HALF_SPACE, COLON));
		// 被保険者氏名(カナ)
		outputItem.append(commonUtils.addCodeFillUpSpace(dto.getInsuredPersonNameKana(), "J04", HALF_SPACE, COLON));
		// 被保険者氏名(漢字)
		outputItem.append(commonUtils.addCodeFillUpSpace(dto.getInsuredPersonName(), "L67", HALF_SPACE, COLON));
		// 被保険者性別
		outputItem.append(commonUtils.addCodeFillUpSpace(dto.getInsuredPersonSex(), "302", HALF_SPACE, COLON));
		// 被保険者年齢
		outputItem.append(commonUtils.addCodeFillUpSpace(dto.getInsuredPersonAge(), "303", HALF_SPACE, COLON));
		// 被保険者生年月日
		outputItem.append(commonUtils.addCodeFillUpSpace(dto.getInsuredPersonBirthday(), "323", HALF_SPACE, COLON));

		// FILLER
		for (int i = 0; i < INSURED_PERSON_UNIVERSITY_FILLER1; i++) {
			outputItem.append(HALF_SPACE);
		}

		// 扶養者と被保険者との関係
		outputItem.append(commonUtils.addCodeFillUpSpace(dto.getDependentsRelationship(), "499", HALF_SPACE, COLON));
		// 扶養者氏名
		outputItem.append(commonUtils.addCodeFillUpSpace(dto.getDependentsName(), "557", HALF_SPACE, COLON));
		// セット販売形式コード
		outputItem.append(commonUtils.addCodeFillUpSpace(dto.getGoodsPlanCode(), "300", HALF_SPACE, COLON));
		// 加入口数
		outputItem.append(commonUtils.addCodeFillUpSpace(dto.getAdditionNum(), "572", HALF_SPACE, COLON));
		// 他の保険契約有りサイン
		outputItem.append(commonUtils.addCodeFillUpSpace(dto.getOtherAgreemenSign(), "Y34", HALF_SPACE, COLON));
		// 職種級別ー傷害所得基本
		outputItem.append(commonUtils.addCodeFillUpSpace(dto.getOccupationIncomeBasic(), "573", HALF_SPACE, COLON));
		// 職種コード
		outputItem.append(commonUtils.addCodeFillUpSpace(dto.getOccupationCode(), "312", HALF_SPACE, COLON));
		// 職業名・職種名
		outputItem.append(commonUtils.addCodeFillUpSpace(dto.getProfessionOccupationName(), "576", HALF_SPACE, COLON));

		// FILLER
		for (int i = 0; i < INSURED_PERSON_UNIVERSITY_FILLER2; i++) {
			outputItem.append(HALF_SPACE);
		}

		// 他の保険契約・保険金額１
		outputItem.append(commonUtils.addCodeFillUpSpace(dto.getOtherInsuranceAmount1(), "Y36", HALF_SPACE, COLON));
		// 他保険傷害入院日額
		outputItem.append(
				commonUtils.addCodeFillUpSpace(dto.getOtherInjuryDailyHospitalization(), "Y37", HALF_SPACE, COLON));
		// 他保険疾病入院日額
		outputItem.append(
				commonUtils.addCodeFillUpSpace(dto.getOtherDiseaseDailyHospitalization(), "Y38", HALF_SPACE, COLON));
		// 他保険傷害通院日額
		outputItem.append(
				commonUtils.addCodeFillUpSpace(dto.getOtherInjuryOutpatientDailyAmount(), "T28", HALF_SPACE, COLON));
		// 他保険疾病通院日額
		outputItem.append(
				commonUtils.addCodeFillUpSpace(dto.getOtherDiseaseOutpatientDailyAmount(), "T29", HALF_SPACE, COLON));
		// プラン
		outputItem.append(commonUtils.addCodeFillUpSpace(dto.getPlan(), "R02", HALF_SPACE, COLON));

		// FILLER
		for (int i = 0; i < INSURED_PERSON_UNIVERSITY_FILLER3; i++) {
			outputItem.append(HALF_SPACE);
		}

		return outputItem.toString();
	}

	/**
	 * 明細送り状分割処理<br>
	 * <br>
	 * 加入フェースに紐づく明細送り状を120件ごとに分割します
	 * 
	 * @param additionFaceDtoList 加入フェースDTOリスト
	 */
	public void splitDetailInvoice(List<AdditionFaceDto> additionFaceDtoList) {

		// 加入フェースの件数分、処理を行う
		for (AdditionFaceDto additionFace : additionFaceDtoList) {
			// 分割明細送り状リスト
			List<DetailInvoiceDto> splitDetailInvoiceDtoList = new ArrayList<>();

			// TODO ここで最大の明細送り状上番号を取得する。
			// 明細送り状番号は代POLごとに振られるため
			Integer detailNo = 001;

			// 明細送り状の件数分、処理を行う
			for (DetailInvoiceDto detail : additionFace.getDetailInvoiceDtoList()) {

				// 分割件数
				int splitSize = 120;

				for (int i = 0; i < detail.getInsuredDtoList().size(); i += splitSize) {
					// 複製
					DetailInvoiceDto copy = modelMapper.map(detail, DetailInvoiceDto.class);

					// １２０件ごとに加入者データを分割
					copy.setInsuredDtoList(new ArrayList<>(detail.getInsuredDtoList().subList(i,
							Math.min(i + splitSize, detail.getInsuredDtoList().size()))));

					// 明細送り状Noを設定
					this.setDetailInvoiceNo(copy, detailNo);

					// 明細送り状を追加
					splitDetailInvoiceDtoList.add(copy);

					detailNo++;
				}
			}

			// 加入フェースに分割明細送り状リストを設定
			additionFace.setDetailInvoiceDtoList(splitDetailInvoiceDtoList);
		}
	}

	/**
	 * 明細送り状No設定処理
	 * 
	 * @param dto
	 * @param no
	 */
	private void setDetailInvoiceNo(DetailInvoiceDto dto, Integer no) {

		// TODO 日次バッチは5万件の上限があるため、訂正データの紐づきを参照する必要もある
//		// 新規・中途データが紐づいていない場合
//		if (count == 0 && dto.getNewMidwayLinkFrg().equals("0")) {
//			no = Integer.parseInt(dto.getDetailInvoiceNo());
//
//		} else if (count == 0 && dto.getNewMidwayLinkFrg().equals("1")) {
//			// 新規・中途データが紐づいている場合
//			no++;
//		}

		no++;
		// 前ゼロ埋め
		String a = no.toString();
		int loopSize = 3 - a.length();

		for (int i = 0; i < loopSize; i++) {
			a = "0" + a;
		}
		dto.setDetailInvoiceNo(a);
	}

}
