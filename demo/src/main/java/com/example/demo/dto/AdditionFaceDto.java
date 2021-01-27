package com.example.demo.dto;

import java.util.Date;
import java.util.List;

import lombok.Data;

/**
 * 加入フェースDTO
 * <br>
 * @author katomab
 *
 */
@Data
public class AdditionFaceDto {

	// 団体ごとにKCファイル出力できるように分ける
	private String groupFlg = "1";

	// テスト用
	/** データ種類 */
	private String dataType = "_KF   ";
	/** ＭＡＣＳ識別コード */
	private String macsCode = "A";
	/** 明細計上月 */
	private String invoiceAccountingMonth;
	/** 加入フェース作成日 */
//	private Date additionFaceCreated;
	private String additionFaceCreated;
	/** 部店・課支社ｺｰﾄﾞ*/
	private String departmentCode = "0001";
	/** 代理店ｺｰﾄﾞ */
	private String agencyCode = "0002";
	/** 種目 */
	private String item = "4";
	/** 新異区分 */
	private String newClassification = "1";
	/** 送付方法(最終送付サイン) */
	private String lastSendSign;
	/** 全分割回数 */
	private String bunkatuKisu;
	/** 送付（営業）課支社コード */
	private String signAgencyCode;
	/** 代理店/仲立人コード */
	private String agencyBrokerCode;
	/** 明細送り状枚数 */
	private String deliveryinvoiceNum;
	/** 異動発生日 */
//	private Date transferDate = new Date();
	private String transferDate;
	/** MS&AD事務S使用欄 */
	private String msAd;
	/** KF追加サイン */
	private String kfAddSign;
	/** 追加先入力ID */
	private String addInputSign;
	/** 追加先バッチSEQ */
	private String addBatchSeq;


//	/** データ種類 */
//	private String dataType = "_KF   ";
//	/** ＭＡＣＳ識別コード */
//	private String macsCode = "A";
//	/** 明細計上月 */
//	private String invoiceAccountingMonth;
//	/** 加入フェース作成日 */
//	private Date additionFaceCreated;
//	/** 部店・課支社ｺｰﾄﾞ*/
//	private String departmentCode;
//	/** 代理店ｺｰﾄﾞ */
//	private String agencyCode;
//	/** 種目 */
//	private String item;
//	/** 新異区分 */
//	private String newClassification;
//	/** 送付方法(最終送付サイン) */
//	private String lastSendSign;
//	/** 全分割回数 */
//	private String bunkatuKisu;
//	/** 送付（営業）課支社コード */
//	private String signAgencyCode;
//	/** 代理店/仲立人コード */
//	private String agencyBrokerCode;
//	/** 明細送り状枚数 */
//	private String deliveryinvoiceNum;
//	/** 異動発生日 */
//	private Date transferDate;
//	/** MS&AD事務S使用欄 */
//	private String msAd;
//	/** KF追加サイン */
//	private String kfAddSign;
//	/** 追加先入力ID */
//	private String addInputSign;
//	/** 追加先バッチSEQ */
//	private String addBatchSeq;

	/** 代表証券番号 */
	private String representativePolicyNo;
	/** 加入フェースNO */
	private String additionFaceNo;
	/** 明細送り状リスト */
	List<DetailInvoiceDto> detailInvoiceDtoList;

	// 団体コード
	private String groupCode;
	// 団体コード枝番
	private String groupCodeNumber;
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

