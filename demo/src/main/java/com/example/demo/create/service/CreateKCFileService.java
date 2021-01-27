package com.example.demo.create.service;

import java.io.File;
import java.io.FileWriter;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.example.demo.constant.InsuredTypeEnum;
import com.example.demo.convert.ConvertCode;
import com.example.demo.dto.AdditionFaceDto;
import com.example.demo.dto.DetailInvoiceDto;
import com.example.demo.dto.InsuredDto;
import com.example.demo.dto.InsuredPersonCoopDto;
import com.example.demo.dto.InsuredPersonUniversityDto;
import com.example.demo.edit.service.EditKCFileItem;

@Component
public class CreateKCFileService {

    /** 編集用クラス */
    @Autowired
    private EditKCFileItem editItem;

    /** コード変換クラス */
    @Autowired
    private ConvertCode convertCode;

    /**
     * KCファイル作成処理
     * 
     * @param additionFaceDtoList 加入フェースリスト
     * @param filePath            KCファイル出力ファイルパス
     * @param executeDate         バッチ実行日
     * @param insuredType         保険タイプ
     */
    public void createKCFile(List<AdditionFaceDto> additionFaceDtoList, String filePath, Date executeDate,
            InsuredTypeEnum insuredType) {

        // 計上用に明細送り状を分割する。
        editItem.splitDetailInvoice(additionFaceDtoList);

        // ファイル出力用
        StringBuilder outputFileVal = new StringBuilder();

        File outputFile = new File(filePath);
        try (FileWriter fileWriter = new FileWriter(outputFile, true)) {

            // TODO コープ、学Mを判定してKDRレコードを設定する。
            if (insuredType.equals(InsuredTypeEnum.COOP)) {
                // 自クラスメソッドの呼び出し

            } else if (insuredType.equals(InsuredTypeEnum.UNIVERSITY)) {
                // 自クラスメソッドの呼び出し

            }

            // 加入フェースの件数分ループ
            for (AdditionFaceDto additionFace : additionFaceDtoList) {

                // データ編集
                editItem.editAdditionFace(additionFace, executeDate);

                // KCファイル出力用に値を整形する
                outputFileVal.append(editItem.editOutputAdditionFace(additionFace));

                // 明細送り状の件数分ループ
                for (DetailInvoiceDto detailInvoice : additionFace.getDetailInvoiceDtoList()) {

                    // データ編集
                    editItem.editDetailInvoice(detailInvoice, executeDate);

                    // KCファイル出力用に値を整形する
                    outputFileVal.append(editItem.editOutputDetailInvoice(detailInvoice));

                    // 加入者情報の件数分ループ
                    for (InsuredDto insured : detailInvoice.getInsuredDtoList()) {
                        // データ編集
                        editItem.editInsured(insured);
                        // KCファイル出力用に値を整形する
                        outputFileVal.append(editItem.editOutputInsured(insured));

                        // コープ、学Mの判定
                        if (insuredType.equals(InsuredTypeEnum.COOP)) {
                            // コープ被保険者
                            this.editCoop(insured.getInsuredPersonCoopDtoList(), outputFileVal);

                        } else if (insuredType.equals(InsuredTypeEnum.UNIVERSITY)) {
                            // 学M被保険者
                            this.editUniversity(insured.getInsuredPersonUniversityDto(), outputFileVal);
                        }
                    }
                }

                // KCファイル出力を行う
                fileWriter.write(outputFileVal.toString());
                // 初期化
                outputFileVal.setLength(0);
            }
        } catch (Exception e) {
            System.out.println(e);
        }
    }

    /**
     * コープ被保険者情報編集処理
     * 
     * @param coopList      コープ被保険者リスト
     * @param outputFileVal KCファイル出力項目
     */
    private void editCoop(List<InsuredPersonCoopDto> coopList, StringBuilder outputFileVal) {
        for (InsuredPersonCoopDto coop : coopList) {
            // データ編集
            editItem.editInsuredPersonCoop(coop);
            // データ出力
            outputFileVal.append(editItem.editOutputInsuredPersonCoop(coop));
        }
    }

    /**
     * 学M被保険者情報編集処理
     * 
     * @param universityList 学M被保険者リスト
     * @param outputFileVal  KCファイル出力項目
     */
    private void editUniversity(List<InsuredPersonUniversityDto> universityList, StringBuilder outputFileVal) {
        for (InsuredPersonUniversityDto university : universityList) {
            // データ編集
            editItem.editInsuredPersonUniversity(university);
            // データ出力
            outputFileVal.append(editItem.editOutputInsuredPersonUniversity(university));
        }
    }

    /**
     * コード変換処理
     * 
     * @param middlePath
     * @param outputPath
     */
    public void convertCode(String middlePath, String outputPath) {
        this.convertCode.convert(middlePath, outputPath);
    }

    /**
     * 登録処理
     */
    public void insertDate() {
        // 履歴の登録を行う

        // 明細送り状の登録を行う
        // insert,update

    }

    /**
     * 更新処理
     */
    public void updateDate() {
        // 加入フェースを更新する。

        // 加入者データを更新する。

    }

}
