package com.example.demo.dto;

import java.util.List;

import lombok.Data;

@Data
/**
 * コープと学Mの加入者データ
 * @author katomab
 *
 */
public class InsuredDto {
	// データ種類
	private String dataType;
	// 加入者番号
	private String insuredNo;
	// 起票日
	private String draftDate;
	// 所属名
	private String belongName;
	// 所属コード
	private String belongCode;
	// 団体扱社員番号
	private String groupEmployeeNumber;
	// 加入者氏名(カナ)
	private String insuredNameKana;
	// シフトコード
	private String shiftCode;
	// 加入者氏名(漢字)
	private String insuredName;
	// 加入者生年月日
	private String insuredBirthday;
	// 加入者性別
	private String insuredSex;
	// 加入者特記事項
	private String insuredNotices;
	// 電話番号
	private String phoneNumber;
	// 郵便番号
	private String postNumber;
	// 加入者住所 -都道府県、市区町村、丁目(漢字)
	private String address;
	// 加入者住所 -都道府県、市区町村、丁目(カナ)
	private String addressKana;
	// データ発生元サイン
	private String dataOriginSign;
	// 加入者合計保険料
	private String totalInsuranceFee;

	// コープ被保険者リスト
	private List<InsuredPersonCoopDto> insuredPersonCoopDtoList;
	// 学M被保険者リスト
	private List<InsuredPersonUniversityDto> insuredPersonUniversityDto;
}
