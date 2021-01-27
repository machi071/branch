package com.example.demo.dto;

import lombok.Data;

@Data
public class InsuredPersonCoopDto {
	// データ種類
	private String dataType;
	// 符号
	private String code;
	// 保険種類
	private String insuranceType;
	// 被保険者氏名(カナ)
	private String insuredPersonNameKana;
	// 被保険者氏名(漢字)
	private String insuredPersonName;
	// 被保険者住所(カナ)
	private String insuredPersonAddress;
	// 被保険者性別
	private String insuredPersonSex;
	// 被保険者年齢
	private String insuredPersonAge;
	// 被保険者生年月日
	private String insuredPersonBirthday;
	// 保険契約者との続柄
	private String relationShip;
	// セット販売形式コード
	private String goodsPlanCode;
	// 加入口数
	private String AdditionNum;
	// 他の保険契約有りサイン
	private String otherAgreemenSign;
	// 健康状況告知１
	private String healthStatus1;
	// 健康状況告知２
	private String healthStatus2;
	// 健康状況告知日
	private String healthStatusNoticeDate;
	// 他の保険契約等の告知1(疾病通院保険金日額)
	private String OtherInsuranceNotice;
	// 他保険疾病入院日額
	private String otherDiseaseDailyHospitalization;
	// 被保険者告知者名カナ
	private String insuredPersonNoticeKana;

}
