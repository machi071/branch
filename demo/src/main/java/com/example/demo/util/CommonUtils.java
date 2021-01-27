package com.example.demo.util;

import java.io.File;
import java.io.InputStream;
import java.text.DateFormat;
import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.Properties;

import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import com.example.demo.log.ILogger;


/**
 * 共通処理クラス
 */
@Component
public class CommonUtils implements ILogger {

    /** 項目桁数プロパティファイルパス */
    private static final String LENGTH_PROPERTY_FILE_PATH = "contractLength.properties";

    /** 文字コード（Shift_JIS） */
    private static final String SJIS = "SJIS";

//    /** カレンダーテーブルMapper(予定) */
//    @Autowired
//    private CalendarHolidayConfigMapper calendarholidayconfigMapper;
//  /** 営業日 */
//  private static String BUSINESS_DAY = "N";

    /** 全角カナ→半角カナ変換用マップ */
    private static final Map<String, String> kanaMap = new HashMap<String, String>() {
        {
            put("ァ", "ｧ");
            put("ア", "ｱ");
            put("ィ", "ｨ");
            put("イ", "ｲ");
            put("ゥ", "ｩ");
            put("ウ", "ｳ");
            put("ェ", "ｪ");
            put("エ", "ｴ");
            put("ォ", "ｫ");
            put("オ", "ｵ");
            put("カ", "ｶ");
            put("ガ", "ｶﾞ");
            put("キ", "ｷ");
            put("ギ", "ｷﾞ");
            put("ク", "ｸ");
            put("グ", "ｸﾞ");
            put("ケ", "ｹ");
            put("ゲ", "ｹﾞ");
            put("コ", "ｺ");
            put("ゴ", "ｺﾞ");
            put("サ", "ｻ");
            put("ザ", "ｻﾞ");
            put("シ", "ｼ");
            put("ジ", "ｼﾞ");
            put("ス", "ｽ");
            put("ズ", "ｽﾞ");
            put("セ", "ｾ");
            put("ゼ", "ｾﾞ");
            put("ソ", "ｿ");
            put("ゾ", "ｿﾞ");
            put("タ", "ﾀ");
            put("ダ", "ﾀﾞ");
            put("チ", "ﾁ");
            put("ヂ", "ﾁﾞ");
            put("ッ", "ｯ");
            put("ツ", "ﾂ");
            put("ヅ", "ﾂﾞ");
            put("テ", "ﾃ");
            put("デ", "ﾃﾞ");
            put("ト", "ﾄ");
            put("ド", "ﾄﾞ");
            put("ナ", "ﾅ");
            put("ニ", "ﾆ");
            put("ヌ", "ﾇ");
            put("ネ", "ﾈ");
            put("ノ", "ﾉ");
            put("ハ", "ﾊ");
            put("バ", "ﾊﾞ");
            put("パ", "ﾊﾟ");
            put("ヒ", "ﾋ");
            put("ビ", "ﾋﾞ");
            put("ピ", "ﾋﾟ");
            put("フ", "ﾌ");
            put("ブ", "ﾌﾞ");
            put("プ", "ﾌﾟ");
            put("ヘ", "ﾍ");
            put("ベ", "ﾍﾞ");
            put("ペ", "ﾍﾟ");
            put("ホ", "ﾎ");
            put("ボ", "ﾎﾞ");
            put("ポ", "ﾎﾟ");
            put("マ", "ﾏ");
            put("ミ", "ﾐ");
            put("・", "･");
            put("ム", "ﾑ");
            put("メ", "ﾒ");
            put("モ", "ﾓ");
            put("ャ", "ｬ");
            put("ヤ", "ﾔ");
            put("ュ", "ｭ");
            put("ユ", "ﾕ");
            put("ョ", "ｮ");
            put("ヨ", "ﾖ");
            put("ラ", "ﾗ");
            put("リ", "ﾘ");
            put("ル", "ﾙ");
            put("レ", "ﾚ");
            put("ロ", "ﾛ");
            put("ヮ", "ﾜ");
            put("ワ", "ﾜ");
            put("ヰ", "ｲ");
            put("ヱ", "ｴ");
            put("ヲ", "ｦ");
            put("ン", "ﾝ");
            put("ヴ", "ｳﾞ");
            put("ヵ", "ｶ");
            put("ヶ", "ｹ");
            put("ﾞ", "ﾞ");
            put("―", "-");
            put("‐", "-");
            put("－", "-");
            put("ー", "-");
            put("０", "0");
            put("１", "1");
            put("２", "2");
            put("３", "3");
            put("４", "4");
            put("５", "5");
            put("６", "6");
            put("７", "7");
            put("８", "8");
            put("９", "9");
            put("ａ", "a");
            put("ｂ", "b");
            put("ｃ", "c");
            put("ｄ", "d");
            put("ｅ", "e");
            put("ｆ", "f");
            put("ｇ", "g");
            put("ｈ", "h");
            put("ｉ", "i");
            put("ｊ", "j");
            put("ｋ", "k");
            put("ｌ", "l");
            put("ｎ", "n");
            put("ｍ", "m");
            put("ｏ", "o");
            put("ｐ", "p");
            put("ｑ", "q");
            put("ｒ", "r");
            put("ｓ", "s");
            put("ｔ", "t");
            put("ｕ", "u");
            put("ｖ", "v");
            put("ｗ", "w");
            put("ｘ", "x");
            put("ｙ", "y");
            put("ｚ", "z");
            put("Ａ", "A");
            put("Ｂ", "B");
            put("Ｃ", "C");
            put("Ｄ", "D");
            put("Ｅ", "E");
            put("Ｆ", "F");
            put("Ｇ", "G");
            put("Ｈ", "H");
            put("Ｉ", "I");
            put("Ｊ", "J");
            put("Ｋ", "K");
            put("Ｌ", "L");
            put("Ｎ", "N");
            put("Ｍ", "M");
            put("Ｏ", "O");
            put("Ｐ", "P");
            put("Ｑ", "Q");
            put("Ｒ", "R");
            put("Ｓ", "S");
            put("Ｔ", "T");
            put("Ｕ", "U");
            put("Ｖ", "V");
            put("Ｗ", "W");
            put("Ｘ", "X");
            put("Ｙ", "Y");
            put("Ｚ", "Z");
        }
    };

    /** 特殊記号リスト */
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

    /**
     * 項目コード結合スペース埋め処理
     * <br>
     * 項目コードを先頭に連結後、スペース埋め処理
     * <br>
     * @param item スペース埋めする値
     * @param code 項目コード
     * @param spaceType 半角スペースまたは全角スペース
     * @param delimiter 区切り文字
     * @return 項目コードを付け、スペース埋めした値
     *
     */
    public String addCodeFillUpSpace(String item, String code, String spaceType, String delimiter) {
    	// 値チェック
        if (StringUtils.isEmpty(item)) {
            return "";
        }

        // 項目コードチェック
        if (StringUtils.isEmpty(code)) {
            return item;
        }

        // 項目コード、区切り文字、値を結合
        String fillUppedString = code + delimiter + item;
        try {

            // 項目値の桁数を取得
            int dataLen = item.getBytes(SJIS).length;
            int count = 0;
            for (int i = 0; i < item.length(); i++) {
                char c = item.charAt(i);
                if (simbolList.contains(String.valueOf(c))) {
                    // 特殊記号の数をカウント
                    count++;
                }
            }
            // 特殊記号は1バイト扱いされてしまうため2バイト扱いになるよう桁数に加算する。
            dataLen += count;

            // 桁数プロパティファイルを読み込む
            Properties properties = new Properties();
            InputStream istream = new ClassPathResource(LENGTH_PROPERTY_FILE_PATH).getInputStream();
            properties.load(istream);

            // 項目コードをキーに桁数を取得
            String lengthStr = properties.getProperty(code);
            if (StringUtils.isEmpty(lengthStr)) {
                // 桁数は取得できない場合は値をそのまま返却する。
                return fillUppedString;
            }

            // 項目値の桁数が桁数以上の場合は処理を終了
            int length = Integer.parseInt(lengthStr);
            if (length <= dataLen) {
                return fillUppedString;
            }

            // スペースの桁数を取得（半角か全角）
            int spaceTypeLen = spaceType.getBytes(SJIS).length;
            // スペース埋めを実施
            for (int i = 0; i < (length - dataLen) / spaceTypeLen; i++) {
                fillUppedString += spaceType;
            }
            istream.close();

        } catch (Exception e) {
            log.error("", fillUppedString, e);
        }
        return fillUppedString;
    }

    /**
     * 半角カナ変換処理
     * <br>
     * 全角カナを半角カナに変換します。
     * <br>
     * @param fullWidthKana 全角カナ
     * @return 変換後カナ
     */
    public String convertHalfWidthKana(String fullWidthKana) {
        if (StringUtils.isEmpty(fullWidthKana)) {
            return fullWidthKana;
        }
        StringBuilder sb = new StringBuilder();
        String keyStr;
        for (int i = 0; i < fullWidthKana.length(); i++) {
            char c = fullWidthKana.charAt(i);
            keyStr = String.valueOf(c);
            String kana = kanaMap.get(String.valueOf(keyStr));
            if (!StringUtils.isEmpty(kana)) {
                sb.append(kana);
            } else {
                sb.append(keyStr);
            }
        }
        return sb.toString();
    }

    /**
     * 半角カナチェック処理
     * <br>
     * 引数の値が半角カナであるかチェックします。
	 * <br>
     * @param strValue 文字列
     * @return true（半角カナ）/false（半角カナ以外）
     */
    public boolean checkHalfWidthKana(String strValue) {
        if (StringUtils.isEmpty(strValue)) {
            return false;
        }
        boolean checkFlg = false;
        for (String key : kanaMap.keySet()) {
            if (strValue.equals(kanaMap.get(key))) {
                checkFlg = true;
                break;
            }
        }
        return checkFlg;
    }

    /**
     * 年齢算出処理
	 * <br>
     * 誕生日から始期日時点の年齢を算出する
	 * <br>
     * @param birthday 誕生日
     * @param startDay 始期日
     * @return 年齢
     */
    public String calcAge(String strBirthday, String strStartDay) {
        if (StringUtils.isEmpty(strBirthday) || StringUtils.isEmpty(strStartDay)) {
            return "";
        }

        int age = 0;
        try {

            SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
            Date startDay = sdf.parse(strStartDay);
            Date birthday = sdf.parse(strBirthday);
            age = (Integer.parseInt(sdf.format(startDay)) - Integer.parseInt(sdf.format(birthday))) / 10000;

        } catch (Exception e) {
            log.error("", e);
        }
        return String.valueOf(age);
    }

    /**
     * 和暦変換処理
	 * <br>
     * GyyMMddのフォーマットで和暦の日付文字列を返却する
     * <br>
     * @param date 変換元日付
     * @return 和暦日付
     */
    public String getJapaneseCalendar(Date date) {

        String warekiTable[][] = {
                //令和
                { "20190501", "99991231", "R", "2018" }
        };
        if (date == null) {
            return "";
        }
        //ロケールを指定してCalendarインスタンスを取得
        Locale locale = new Locale("ja", "JP", "JP");
        Calendar calendar = Calendar.getInstance(locale);
        // 引数の時刻をカレンダーにセット
        calendar.setTime(date);

        // フォーマットの作成
        DateFormat japaseseFormat = new SimpleDateFormat("GyyMMdd", locale);
        //YYYYMMDD型に変換する
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy");
        NumberFormat frm = new DecimalFormat("00");

        // 年・月・日取得(YYYYMMDD型)
        String year = sdf.format(calendar.getTime());
        String month = frm.format(calendar.get(Calendar.MONTH) + 1);
        String day = frm.format(calendar.get(Calendar.DATE));
        String ymd = year + month + day;

        int i = 0;
        for (i = 0; i < warekiTable.length; i++) {
            //当該西暦が開始年以上で最終年以下ならbreak
            if (ymd.compareTo(warekiTable[i][0]) >= 0 && ymd.compareTo(warekiTable[i][1]) <= 0) {
                return warekiTable[i][2] + frm.format(Integer.parseInt(year) - Integer.parseInt(warekiTable[i][3]))
                        + month + day;
            }
        }
        // カレンダーにセットした日付の和暦を日付文字列で返却する。
        return japaseseFormat.format(calendar.getTime());
    }

    /**
     * 月返却処理
     * <br>
     * 日付の月のみ返却
     * <br>
     * @param date 変換元日付
     * @return 月
     * @author katomab
     */
    public String getMonth(Date date) {
    	// 日本のロケールを設定
        Calendar calendar = Calendar.getInstance(new Locale("ja", "JP", "JP"));
        calendar.setTime(date);
        NumberFormat frm = new DecimalFormat("00");
        //日付の月のみ返却
        return frm.format(calendar.get(Calendar.MONTH) + 1);
    }

    /**
     * 日付Date型変換処理　
     * <br>
     * 日付文字列をDate型に変換
     * <br>
     * @param strDate 日付文字列
     * @return 変換後Date
     */
    public Date convertStrDate(String strDate) {
        if (StringUtils.isEmpty(strDate)) {
            return null;
        }

        DateFormat sdf = new SimpleDateFormat("yyyyMMdd");
        Date toDate = null;
        try {
            toDate = sdf.parse(strDate);
        } catch (Exception e) {
            log.error("", strDate, e);
        }
        return toDate;
    }

	/**
	* ディレクトリ作成処理
	* <br>
	* ディレクトリが存在しない場合作成
	* <br>
	* @param ディレクトリパス
	*/
	public void createDirectory(String filePath) {
		File dir = new File(filePath);
		if (!dir.exists()) {
			dir.mkdir();
		}
	}

  /**
  * 削除処理
	* <br>
  * 作成したディレクトリとファイルを削除
  * <br>
  * @param ディレクトリパス
  */
	public void deleteDirectory(String filePath) {
		File dir = new File(filePath);
		// ディレクトリ内の全ファイル削除
		File[] fileList = dir.listFiles();
		for (int i = 0; i < fileList.length; i++) {
		    fileList[i].delete();
		}
		// ディレクトリ削除
		dir.delete();
	}

//  /**
//  * 営業日チェック
//  * <br>
//  * @param today
//  * @return 営業日：true 休日・祝日：false
//  */
// public boolean CheckBusinessDay(Date executeDate) {
//
//     try {
//
//         SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
//         CalendarHolidayConfig chc = calendarholidayconfigMapper.selectByCalender(sdf.format(executeDate));
//
//    	// 休日であればログを返却
//         if (chc.getIsHoliday().equals(BUSINESS_DAY)) {
//             log.info(TODAY_BUSINESS_DAY);
//             return true;
//         }
//         log.info(TODAY_HOLIDAY);
//         return false;
//
//     } catch (Exception e) {
//         log.error(MSG_75_50_00100, executeDate, e);
//         return false;
//     }
// }

//    /**
//     * 実施日判定処理
//     * 営業日カレンダーテーブルから実施日判定取得
//     * <br>
//     * @param executeDate バッチ実行日
//     */
//    public boolean getExcuteDate(Date executeDate) {
//
//        // 営業日チェック
//        if (!commonUtils.CheckBusinessDay(executeDate)) {
//            // 営業日でない場合はログを出力して処理を終了する。
//            log.info(PROCESSING_END);
//            return false;
//        }
//    	return true;
//    }

}