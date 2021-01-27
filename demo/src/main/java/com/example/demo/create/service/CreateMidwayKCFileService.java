package com.example.demo.create.service;

import java.util.Date;

import org.springframework.stereotype.Component;

@Component
public class CreateMidwayKCFileService extends CreateKCFileService {

	/**
	 * 中途KCファイル作成処理
	 * 
	 * @param executeDate バッチ実行日
	 */
	public void createMidwayKCFile(Date executeDate, String middleFilePath, String outputFilePath) {

		// データ取得処理
		this.getData();

		// KCファイル作成処理
		// コード変換
		// 登録処理
		// 更新処理
	}

	private void getData() {

	}
}
