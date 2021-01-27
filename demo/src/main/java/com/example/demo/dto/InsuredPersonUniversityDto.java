package com.example.demo.dto;

import lombok.Data;

@Data
public class InsuredPersonUniversityDto {
	// データ種類
	private String dataType;
	// 符号
	private String code;
	// 保険種類
	private String insuranceType;
	// 保種サブNo
	private String insuranceTypeNo;
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
	// 扶養者と被保険者との関係
	private String dependentsRelationship;
	// 扶養者氏名
	private String dependentsName;
	// セット販売形式コード
	private String goodsPlanCode;
	// 加入口数
	private String AdditionNum;
	// 他の保険契約有りサイン
	private String otherAgreemenSign;
	// 職種級別ー傷害所得基本
	private String occupationIncomeBasic;
	// 職種コード
	private String occupationCode;
	// 職業名・職種名
	private String ProfessionOccupationName;
	// 他の保険契約・保険金額１
	private String otherInsuranceAmount1;
	// 他保険疾病入院日額
	private String otherDiseaseDailyHospitalization;
	// 他保険傷害入院日額
	private String otherInjuryDailyHospitalization;;
	// 他保険疾病通院日額
	private String otherDiseaseOutpatientDailyAmount;
	// 他保険傷害通院日額
	private String otherInjuryOutpatientDailyAmount;
	// プラン
	private String plan;

}
