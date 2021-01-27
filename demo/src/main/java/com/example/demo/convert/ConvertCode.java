package com.example.demo.convert;

import java.io.File;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.stereotype.Component;

import com.example.demo.util.CommonUtils;

/**
 * コード変換クラス
 * 
 * @author machitb
 *
 */
@Component
@ComponentScan("com.zatech.team.kc.scenario")
public class ConvertCode {

	/** 変換コード（co930） */
	private static final String CONVERT_CODE_930 = "cp930";
	/** 変換コード（cp939） */
	private static final String CONVERT_CODE_939 = "cp939";
	/** バイト数取得用文字コード */
	private static final String SJIS = "SJIS";
	/** 変換記号 */
	private static final String CONVERT_SIMBOL = "‐";
	/** 2バイト記号リスト */
	private static final List<String> simbolList = new ArrayList<String>() {
		{
			add("―");
			add("～");
			add("∥");
			add("－");
			add("￠");
			add("￡");
			add("￢");
		}
	};

	/** 変換記号リスト */
	private static final List<String> convertSimbolList = new ArrayList<String>() {
		{
			add("―");
			add("～");
			add("∥");
			add("－");
		}
	};

	/** 共通処理クラス */
	@Autowired
	CommonUtils utils;

	/**
	 * 中間ファイルの文字コードを指定の文字コードに変換し、ファイルに出力する。 <br>
	 * 
	 * @param middleFilePath 中間ファイルパス
	 * @param outputFilePath 出力ファイルパス
	 */
	public void convert(String middleFilePath, String outputFilePath) {
//        log.info(START, "ConvertCode.java");

		// 出力用ファイルを作成する。
		this.createFile(outputFilePath);

		// コード変換処理
		this.convertExecute(middleFilePath, outputFilePath);

//        log.info(END, "ConvertCode.java");
	}

	/**
	 * ファイル作成処理 <br>
	 * 
	 * @param outputFilePath 出力ファイルパス
	 */
	private void createFile(String filePath) {
		try {
			File outPutFile = new File(filePath);
			outPutFile.createNewFile();
		} catch (Exception e) {
//            log.error(MSG_75_50_00100, e);
		}
	}

	/**
	 * コード変換実行処理 <br>
	 * 中間ファイルより文字を読み込みコード変換を行い、 出力ファイルに出力します。 <br>
	 * 
	 * @param middleFilePath 中間ファイルパス
	 * @param outputFilePath 出力ファイルパス
	 */
	public void convertExecute(String middleFilePath, String outputFilePath) {
		try {
			// 中間ファイルをオープンする
			File file = new File(middleFilePath);
			FileReader filereader = new FileReader(file);

			// 出力ファイルをオープン
			FileOutputStream fos = new FileOutputStream(new File(outputFilePath), true);

			// 全角文字結合用（1文字ずつ変換すると文字数分シフトコードが付与されてしまうため）
			StringBuilder sb = new StringBuilder();
			// 全角文字出力フラグ
			boolean doubleByteFlg = false;
			// 文字バイト
			byte[] strByte;
			// 文字バイト数
			int byteLength = 0;
			// ファイル値
			int fileValue = 0;

			boolean kanaFlg = false;
			// ファイルを1文字ずつ読み込む
			while ((fileValue = filereader.read()) != -1) {

				// 文字変換
				String str = String.valueOf((char) fileValue);

				// 記号変換
				if (convertSimbolList.contains(str)) {
					str = CONVERT_SIMBOL;
				}

				// バイト数取得
				byteLength = str.getBytes(SJIS).length;

				// 半角カナチェック
				kanaFlg = utils.checkHalfWidthKana(str);

				// 1バイトかつ半角カナでないかつ記号リストに設定されいない記号の場合
				if (byteLength == 1 && !kanaFlg && !simbolList.contains(str)) {

					if (doubleByteFlg) {
						// コード変換
						strByte = sb.toString().getBytes(CONVERT_CODE_930);
						// ファイル出力
						fos.write(strByte);

						// 初期化
						sb = new StringBuilder();
						doubleByteFlg = false;
					}

					// コード変換
					strByte = str.getBytes(CONVERT_CODE_939);
					// ファイル出力
					fos.write(strByte);

					// 2バイトまたは半角カナの場合
				} else {
					// 文字列結合
					sb.append(str);
					// 全角フラグをtrueにする
					doubleByteFlg = true;
					// 全角文字は全て読み込むまでコード変換を行わないため次の文字へ
					continue;
				}
			}
			// まだ出力していない文字が存在する場合
			if (doubleByteFlg) {
				// コード変換
				strByte = sb.toString().getBytes(CONVERT_CODE_930);
				// ファイル出力
				fos.write(strByte);
			}
			// ファイルを閉じる
			filereader.close();
			fos.close();
		} catch (Exception e) {
//            log.error(MSG_75_50_00100, e);
		}
	}
}
