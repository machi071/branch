package com.example.demo.dto;

import java.util.Date;
import java.util.List;

import lombok.Data;

/**
 * 明細送り状DTO
 * <br>
 * @author machitb
 *
 */
@Data
public class DetailInvoiceDto {

	/** データ種類 */
	private String dataType = "_41   ";
	/** 自動継続種類 */
	private String continuationType;
	/** ＭＡＣＳ識別コード */
	private String macsCode = "A";
	/** 明細送り状番号 */
	private String detailInvoiceNo;
	/** 部店課支社 */
	private String departmentCode;
	/** 代理店ｺｰﾄﾞ */
	private String agencyCode;
	/** 団体コード */
	private String groupCode;
	/** 団体コード枝番 */
	private String groupCodeNumber;
	/** 控除資料作成要・不要 */
	private String deductionSign;
	/** 年調資料のみ作成 */
	private String yearEndAdjustment;
	/** 加入者数 */
	private int insuredNum;
	/** 加入者識別コード */
	private String insuredIdentifyCode;
	/** 所属ｺｰﾄﾞｿｰﾄｽﾀｰﾄ */
	private String belongcodeStart;
	/** 所属ｺｰﾄﾞｿｰﾄ桁数 */
	private String belongcodeDigits;
	/** 所属転記 */
	private String belongPosting;
	/** ファイルチェック */
	private String fileCheck;
	/** 関連会社番号 */
	private String relationCompanyNo;
	/** 集計区分 */
	private String aggregationClassification;
	/** 社番桁数 */
	private String employeeDigits;
	/** 社番英字有無 */
	private String employeeEnglish;
	/** 団体ディコード */
	private String groupDCode;
	/** 所属コード */
	private String belongcode;
	/** 強制作成サイン */
	private String forceCreateSing;
	/** 加入者証発行不要サイン */
	private String insuredCertificateSign;
	/** 保険料チェック不要サイン */
	private String insuranceFeeCertificateSign;

	// サンプル
	List<InsuredDto> insuredDtoList;

	// DBにはないが更新するかもしれない
	// 計上日
	private Date accountingDate;
	// 加入フェースNO
	private String additionFaceNo;

	// 代表証券番号
	private String representativePolicyNo;
	// 明細送り状ID
	private String detailInvoiceId;
	// 団体名
	private String groupName;
	// 作成者
	private String creator;
	// 修正者
	private String modifier;
	// 作成日時
	private Date gmtCreated;
	// 修正日時
	private Date gmtModified;
	// 削除フラグ
	private String delFlg;

}