package com.chs.boot.common.util;

/**
 */

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.math.BigDecimal;
import java.net.URL;
import java.net.URLConnection;
import java.net.URLEncoder;
import java.sql.Clob;
import java.sql.SQLException;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javax.servlet.http.HttpServletRequest;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.StringUtils;

public class CommonUtil {

	private static final Logger LOGGER = LoggerFactory.getLogger(CommonUtil.class);
	
	private static final Pattern HTML_TAG = Pattern.compile("\\<.*?\\>", 2);

	/**
	* 소수점 이하 Rounding
	* @param f
	* @param radix
	* @param round
	* @return
	*/
	public static String getRounding(float f,int radix,int round) {
		return getRounding(String.valueOf(f),radix,round);
	}

	/**
	* 소수점 이하 Rounding
	* @param d
	* @param radix
	* @param round
	* @return
	*/
	public static String getRounding(double d,int radix,int round) {
		return getRounding(String.valueOf(d),radix,round);
	}

	/**
	* 소수점 이하 Rounding
	* @param s
	* @param radix
	* @param round
	* @return
	*/
	public static String getRounding(String s,int radix,int round) {

		BigDecimal rtn_big = null;

		try {
			s = s.replaceAll(",", "");

			rtn_big = new BigDecimal(s);
			rtn_big = rtn_big.setScale(radix, round);

		} catch (Exception e) {return s;}

		return String.valueOf(rtn_big.doubleValue());
	}

	public static int getListSize(List l){
		return (l==null?0:l.size());
	}

	/**
	* String -> int
	*@param s
	*@return
	*/
	public static int cvtInt(String s) {

		int rtn = 0;
		if (s == null || s.length() == 0)
			return rtn;

		// 소수점 이하 제거
		if (s.indexOf(".") > -1) {
			s = s.substring(0,s.indexOf("."));
		}

		// comma[,] 제거
		if (s.indexOf(",") > -1) {
			s = s.replaceAll(",", "");
		}

		try {
			rtn = Integer.parseInt(s);
		} catch (Exception e) {}

		return rtn;
	}

	/**
	* String -> long
	*@param s
	*@return
	*/
	public static long cvtLong(String s) {

		long rtn = 0;
		if (s == null || s.length() == 0)
			return rtn;

		// 소수점 이하 제거
		if (s.indexOf(".") > -1) {
			s = s.substring(0,s.indexOf("."));
		}

		// comma[,] 제거
		if (s.indexOf(",") > -1) {
			s = s.replaceAll(",", "");
		}

		try {
			rtn = Long.parseLong(s);
		} catch (Exception e) {}

		return rtn;
	}

	/**
	* String -> double
	*@param s
	*@return
	*/
	public static double cvtDouble(String s) {

		double rtn = 0;
		if (s == null || s.length() == 0)
			return rtn;

		// comma[,] 제거
		if (s.indexOf(",") > -1) {
			s = s.replaceAll(",", "");
		}

		try {
			rtn = Double.parseDouble(s);
		} catch (Exception e) {}

		return rtn;
	}

    /**
     * <pre>
     * String에 대해 NVL함수의 기능을 수행한다.
     * </pre>
     * @param str 대상문자열
     * @return String str이 null이면 "". 아니면 원본문자열.
     */
    public static String nullToEmpty(String str)
    {
        return ( str == null )? "" : str;
    }


    /**
     * null 이거나 "" 아닐때 true을 반환함.
     * @param obj
     * @return
     */
    public static boolean isNotNullAndEmpty(Object obj)
    {
    	if(obj == null)
    	{
    		return false;
    	}
		else if(obj instanceof List){
			if( ((List<?>) obj).size()==0){
				return false;
			}
		}
    	else
    	{
    		String str = obj.toString();
    		if("".equals(str))
    		{
    			return false;
    		}
    		else
    		{
    			return true;
    		}

    	}
		return true;
    }

    /**
     * null 이거나 ""이 일때 true를 반환함.
     * @param obj
     * @return
     */
    public static boolean isNullAndEmpty(Object obj)
    {
    	if(obj == null)
    	{
    		return true;
    	}
    	else
    	{
    		String str = obj.toString();
    		if("".equals(str))
    		{
    			return true;
    		}
    		else
    		{
    			return false;
    		}

    	}
    }


    /**
     * <pre>
     * String에 대해 NVL함수의 기능을 수행한다.
     * </pre>
     * @param str 대상문자열
     * @return String str이 null이거나 ""이면 "&nbsp;". 아니면 원본문자열.
     */
    public static String nullToHtmlEmpty(String str)
    {
        return ( str == null || "".equals(str) )? "&nbsp;" : str;
    }


    /**
     * <pre>
     * 문자열에 대해 null 처리 기능 수행 기능을 수행한다.
     * </pre>
     * @param str1   대상 문자열
     * @param str2   대체 문자열
     * @return String  nvl 결과 문자열
     */
    public static String nvl(String str1, String str2)
    {
        if(str1 == null || "".equals(str1.trim()))
        {
            return str2.trim();
        }
        else
        {
            return str1.trim();
        }
    }
    /**
     * <pre>
     * 문자열에 대해 null 처리 기능 수행 기능을 수행한다.
     * </pre>
     * @param str1   대상 문자열
     * @param str2   대체 문자열
     * @return String  nvl 결과 문자열
     */
    public static String nvl(Object str1, String str2)
    {
    	if(str1 == null || "".equals(str1.toString().trim()))
    	{
    		return str2.trim();
    	}
    	else
    	{
    		return str1.toString().trim();
    	}
    }

    public static String NVL(String str) {
        if ((str == null) || (str.trim().equals("")) || (str.toLowerCase().trim().equals("null")))
            return "";
        else
            return str.trim();
    }


	/**
	* Html Tag replace
	*@param html
	*@return
	*/
	public static String htmlToText(String html) {
		if (html == null)
			return "";

		html = HTML_TAG.matcher(html).replaceAll("");
		html = html.trim();
		return html;
	}



	/**
	*
	* @param comment
	* @return
	*/
	public static String convertHtmlBr(String comment) {

		if (comment == null || comment.trim().length() == 0) {
			return "";
		}

		int length = comment.length();

		StringBuffer buffer = new StringBuffer();

		for (int i = 0; i < length; i++) {
			String tmp = comment.substring(i, i + 1);

			if ("\r".compareTo(tmp) == 0) {
				tmp = comment.substring(++i, i + 1);

				if ("\n".compareTo(tmp) == 0) {
					buffer.append("<br>\r");
				} else {
					buffer.append("\r");
				}
			} else if ("\n".compareTo(tmp) == 0) {
				buffer.append("<br>");
			}
			buffer.append(tmp);
		}

		return buffer.toString();
	}


	/**
	* String을 delimeter를 기준으로 Token으로 분리한 후 ArrayList에 담아 Return하는 Method.
	* StringTokenizer를 사용할경우 문제점 보안
	* String test = "a,b,c,,d"
	* 와 같은경우 StringTokenizer는 c와d사이의 값을 무시함.
	* 이와 같은 중간에 공백문자까지 처리해야할경우 사용함
	*
	* @param str
	* @param delimeter
	* @return
	*/
	public static ArrayList<String> splitString(String str, String delimeter) {
		ArrayList<String> arr = new ArrayList<String>();
		int nCount = 0;
		int nLastIndex = 0;

		try {
			if(str == null) return arr;
			nLastIndex = str.indexOf(delimeter);
			if (nLastIndex == -1) {
				if(str.length() > 0) {
					arr.add(0, str);
				}
			} else {
				while ((str.indexOf(delimeter) > -1)) {
					nLastIndex = str.indexOf(delimeter);
					arr.add(nCount, str.substring(0, nLastIndex));
					str = str.substring(nLastIndex + delimeter.length(), str.length());
					nCount ++;
				}
				arr.add(nCount, str);
			}
		} catch (Exception e) {
			return null;
		}
		return arr;
	}



	/**
	 * "2.0" 을 "2" 와 같은 형태로 소숫점 아래 0을 제거하여 리턴한다.
	 * 소숫점 아래가 0이 아니면 제거하지 않는다.
	 * <pre>
	 * 사용예)
	 * CommonUtil.showNumeric("2.0")
	 * @param numeric
	 * @return
	 */
	public static String showNumeric(float numeric) {
		String tmp = Float.toString(numeric);
		int nidx = tmp.indexOf(".") + 1;
		if(tmp.substring(nidx, nidx+1).equals("0")) {
				return Integer.toString((int)numeric);
		} else {
				return Float.toString(numeric);
		}
	}

	/**
	 * "2.0" 을 "2" 와 같은 형태로 소숫점 아래 0을 제거하여 리턴한다.
	 * 소숫점 아래가 0이 아니면 제거하지 않는다.
	 * <pre>
	 * 사용예)
	 * CommonUtil.showNumeric("2.0")
	 * @param numeric
	 * @return
	 */
	public static String showNumeric(double numeric) {
		String tmp = Double.toString(numeric);
		int nidx = tmp.indexOf(".") + 1;
		if(tmp.substring(nidx, nidx+1).equals("0")) {
				return Integer.toString((int)numeric);
		} else {
				return Double.toString(numeric);
		}
	}

	/**
	 * 넘어온 값에 세자리마다 ','를 넣어주는 함수. 숫자인지는 체크하지 않음.
	 * @param	value	comma를 붙여야 되는 String
	 * @return	세자리 마다 ,가 붙어 있는 String
	**/
	public static String formatNumber(String value)
	{
		if (value == null || value.equals("")){
			value = "0";
			return value;
		}

		if (value.length() <= 3){
			return value;
		}else{
			String remainder = value.substring(value.length() - 3, value.length());
			return formatNumber(value.substring(0, value.length() - 3)) + "," + remainder;
		}
	}

	/**
	 * 구분자로 나열된 문자를 List로 return
	 * @param obj
	 * @param delimiter
	 * @return
	 */
	public static List<String> convertByStringToList(Object obj, String delimiter ) {
		List<String> list = null;
		String[] array = CommonUtil.nvl(obj, "").split(delimiter);

		if(array.length > 0) {
			list = new ArrayList<String>();
			for(String str : array) {
				if(!"".equals(str)) {
					list.add(str);
				}
			}
		}

		return list;
	}

	/*********************************************************************
	 * 설   명 		: 다운로드 자원언어 코드메핑
	 * 작   성   자 : 최진영
	 * 작   성   일 : 2016-02-12
	 ********************************************************************/
	public static String getLanguageMap(String lang){

		String result = "";

		HashMap map = new HashMap();
		map.put("sq", "Albanian");
		map.put("ar", "Arabic");
		map.put("bl", "Belorussian");
		map.put("bg", "Bulgarian");
		map.put("ct", "Cantonese");
		map.put("zh", "中文");
		map.put("hr", "Croatian");
		map.put("cs", "Czech");
		map.put("da", "Danish");
		map.put("nl", "Dutch");
		map.put("en", "English");
		map.put("et", "Estonian");
		map.put("fi", "Finnish");
		map.put("fr", "French");
		map.put("de", "German");
		map.put("el", "Greek");
		map.put("iw", "Hebrew");
		map.put("hu", "Hungarian");
		map.put("in", "Indonesian");
		map.put("ir", "Iranian");
		map.put("it", "Italian");
		map.put("ja", "日本語");
		map.put("kk", "Kazakh");
		map.put("kr", "한글");
		map.put("lv", "Latvian");
		map.put("lt", "Lithuanian");
		map.put("mk", "Macedonian");
		map.put("ms", "Malay");
		map.put("mt", "Maltese");
		map.put("no", "Norwegian");
		map.put("pk", "Pekingese");
		map.put("fa", "Persian");
		map.put("pl", "Polish");
		map.put("pt", "Portuguese");
		map.put("ro", "Romanian");
		map.put("ru", "Russian");
		map.put("sr", "Serbian");
		map.put("sk", "Slovak");
		map.put("sl", "Slovenian");
		map.put("es", "Spanish");
		map.put("sv", "Swedish");
		map.put("tw", "Taiwanese");
		map.put("th", "Thai");
		map.put("tr", "Turkish");
		map.put("uk", "Ukrainian");
		map.put("uz", "Uzbek");
		map.put("vi", "Vietnamese");

		result = nvl(map.get(lang), lang);

		int ct = lang.split(",").length;
		if(ct>1){
			result = "Multi Language";
		}

		return result;
	}

	public static String replace(String line, String oldString, String newString) {
		int index = 0;
		while ((index = line.indexOf(oldString, index)) >= 0) {
			line = line.substring(0, index) + newString
					+ line.substring(index + oldString.length());
			index += newString.length();
		}
		return line;
	}

	public static String convertXSS(String str) {
		if (str != null) {
			str = str.trim();
			str = replace(str, "&", "&amp;");
			str = replace(str, "<", "&#60;");
			str = replace(str, ">", "&#62;");
			str = replace(str, "'", "&#39;");
			str = replace(str, "\"", "&#34;");
			str = replace(str, "--", "&#45;&#45;");
			str = replace(str, "..", "&#46;&#46;");
			str = replace(str, "(", "&#40;");
			str = replace(str, ")", "&#41;");
			str = replace(str, "{", "&#123;");
			str = replace(str, "}", "&#125;");
			str = replace(str, "exec(", "");
			str = replace(str, "\"", "&quot;");
		}
		return str;
	}

	public static String convertXSS2(String str) {
		if (str != null) {
			str = str.trim();
			str = replace(str, "&", "&amp;");
			str = replace(str, "<", "&#60;");
			str = replace(str, ">", "&#62;");
			str = replace(str, "'", "&#39;");
			str = replace(str, "\"", "&#34;");
			str = replace(str, "-", "&#45;");
			str = replace(str, ".", "&#46;");
			str = replace(str, "(", "&#40;");
			str = replace(str, ")", "&#41;");
			str = replace(str, "{", "&#123;");
			str = replace(str, "}", "&#125;");
			str = replace(str, "exec(", "");
			str = replace(str, "\"", "&quot;");
		}
		return str;
	}

	 public static String reConvertXSS(String str) {
    	if (str != null) {
    		str = str.trim();
    		str = replace(str, "&amp;" , "&");
    		str = replace(str, "&#60;" , "<");
    		str = replace(str, "&#62;" , ">");
    		str = replace(str, "&#39;" , "'");
    		str = replace(str, "&#34;" ,  "\"");
    		str = replace(str, "&#45;&#45;" , "--");
    		str = replace(str, "&#46;&#46;" , "..");
    		str = replace(str, "&#40;" , "(");
    		str = replace(str, "&#41;" , ")");
    		str = replace(str, "&#123;" , "{");
    		str = replace(str, "&#125;" , "}");
    		str = replace(str, "&lsquo;" , "‘");
    		str = replace(str, "&rsquo;" , "’");
    		str = replace(str, "&quot;" ,  "\"");
    	}
    	return str;
    }

	 public static String reConvertXSS2(String str) {
		 if (str != null) {
			 str = str.trim();
			 str = replace(str, "&amp;" , "&");
			 str = replace(str, "&#60;" , "<");
			 str = replace(str, "&#62;" , ">");
			 str = replace(str, "&#39;" , "'");
			 str = replace(str, "&#34;" ,  "\"");
			 str = replace(str, "&#45;" , "-");
			 str = replace(str, "&#46;" , ".");
			 str = replace(str, "&#40;" , "(");
			 str = replace(str, "&#41;" , ")");
			 str = replace(str, "&#123;" , "{");
			 str = replace(str, "&#125;" , "}");
			 str = replace(str, "&lsquo;" , "‘");
			 str = replace(str, "&rsquo;" , "’");
			 str = replace(str, "&quot;" ,  "\"");
		}
		return str;
	 }

	 /*******************************
	 * 설명   : 소모품샵 제조사/국가
	 * 작성일 : 2016-02-12
	 ********************************/
	 public static HashMap getNationMap(){
			HashMap map = new HashMap();
			map.put("KR", "한국");
			map.put("CN", "중국");
			map.put("TW", "타이완");
			map.put("HK", "홍콩");
			map.put("BR", "브라질");
			map.put("IN", "인도");
			map.put("JP", "일본");
			map.put("MX", "멕시코");
			return map;
	}

	 /**
	  * 주소 초성 검색시 반드시 진행 되야 할 로직
	  * hyunseok.woo 2016-03-10
	  * @param searchVO
	  * @param initial
	  * @return
	  */
	 public static Map<String, Object> setInitialData(Map<String, Object> searchVO, String initial) {
		 String likeInitial = "";
		 String startInitial = "";
		 String endInitial = "";
		 initial = nullToEmpty(initial);

		 if(initial.equals("ㄱ")){
			 likeInitial = "^(ㄱ|ㄲ)"; startInitial = "가"; endInitial = "나";
		 } else if(initial.equals("ㄴ")) {
			 likeInitial = "^(ㄴ)"; startInitial = "나"; endInitial = "다";
		 } else if(initial.equals("ㄷ")) {
			 likeInitial = "^(ㄷ|ㄸ)"; startInitial = "다"; endInitial = "라";
		 } else if(initial.equals("ㄹ")) {
			 likeInitial = "^(ㄹ)"; startInitial = "라"; endInitial = "마";
		 } else if(initial.equals("ㅁ")) {
			 likeInitial = "^(ㅁ)"; startInitial = "마"; endInitial = "바";
		 } else if(initial.equals("ㅂ")) {
			 likeInitial = "^(ㅂ|ㅃ)"; startInitial = "바"; endInitial = "사";
		 } else if(initial.equals("ㅅ")) {
			 likeInitial = "^(ㅅ|ㅆ)"; startInitial = "사"; endInitial = "아";
		 } else if(initial.equals("ㅇ")) {
			 likeInitial = "^(ㅇ)"; startInitial = "아"; endInitial = "자";
		 } else if(initial.equals("ㅈ")) {
			 likeInitial = "^(ㅈ|ㅉ)"; startInitial = "자"; endInitial = "차";
		 } else if(initial.equals("ㅊ")) {
			 likeInitial = "^(ㅊ)"; startInitial = "차"; endInitial = "카";
		 } else if(initial.equals("ㅋ")) {
			 likeInitial = "^(ㅋ)"; startInitial = "카"; endInitial = "타";
		 } else if(initial.equals("ㅌ")) {
			 likeInitial = "^(ㅌ)"; startInitial = "타"; endInitial = "파";
		 } else if(initial.equals("ㅍ")) {
			 likeInitial = "^(ㅍ)"; startInitial = "파"; endInitial = "하";
		 } else if(initial.equals("ㅎ")) {
			 likeInitial = "^(ㅎ)"; startInitial = "하"; endInitial = "힣";
		 }

		 searchVO.put("likeInitial", likeInitial);
		 searchVO.put("startInitial", startInitial);
		 searchVO.put("endInitial", endInitial);


		 /**
		  * query 삽입 시
			<if test="@lgservice.common.util.MyComparatorUtil@isNotEmpty(likeInitial)">
				AND (column RLIKE #{likeInitial}
				<if test="@lgservice.common.util.MyComparatorUtil@isNotEmpty(startInitial)">
					OR(column >= #{startInitial}
					<if test="@lgservice.common.util.MyComparatorUtil@isNotEmpty(endInitial)">
						AND column < #{endInitial}
					</if>
					)
				</if>
				)
			</if>
		  */

		 return searchVO;
	 }

		/**
		 * 메서드명 getValidationMobilePhoneNum
		 * 설명 : 휴대전화 Validation
		 * 입력값 : 휴대전화 번호
		 * 출력값 :
		 * MOBILE_PHONE_NUM : 입력값
		 * MOBILE_PHONE_NUM_VALIDATION_CD : Validation 코드
		 * MOBILE_PHONE_NUM_VALIDATION_MSG : Validation 결과
		 * MOBILE_PHONE_NUM1 : 휴대폰 앞자리
		 * MOBILE_PHONE_NUM2 : 휴대폰 가운데
		 * MOBILE_PHONE_NUM3 : 휴대폰 끝자리
		 * MOBILE_PHONE_NUM_DESC : 가운데('-') 붙인 휴대폰 번호 표현 (휴대폰 앞자리-휴대폰 가운데-휴대폰 끝자리)
		 * 작성자 : 이기석
		 * @param mobilePhoneNum
		 * @return
		 */
		public static Map<String,Object> getValidationMobilePhoneNum(String mobilePhoneNum)	{
			Map<String,Object> resultMap = new HashMap<String,Object>();
			resultMap.put("MOBILE_PHONE_NUM", mobilePhoneNum);	// 입력값

			String validationCdKey = "MOBILE_PHONE_NUM_VALIDATION_CD";
			String validationMsgKey = "MOBILE_PHONE_NUM_VALIDATION_MSG";

			// TODO : Validation logic 수정
			// 2017.11.13 안정훈 수정
			/* ======================================================================
			Function : 전화번호 validation 처리 함수

			   	1. 휴대폰 번호 010, 011, 016, 017, 018, 019 만 허용

				2. 처음 세 자리가 010일 경우 전체 번호는 11자리만 허용, 11자 미만 또는 초과시 Error 처리

				3. 처음 세 자리가 010일 경우 4번째 숫자가 0, 1 가 입력 시 Error 처리

				4. 처음 세 자리가 010 이 아닐 경우 (즉, 011, 016, 017, 018, 019일 경우)
			     	전체 번호는 10~11자 숫자만 허용, 10~11자 초과 또는 미만 시 Error 처리

			     1) "01X" 이면서 국번이 4자리인 경우
			       - "011" 이면 "9500~9999" 까지 또는 "1700~1799"" 만 허용
			       - "019", “016이면 "9000~9999" 까지만 허용
			       - "017","018" 이면서 국번이 4자리 이면 Error처리
			    2) "011,016,017,018,019" 이면서 국번이 3자리인 경우
			        "200~899" 만 허용하고 000~199 ,900~999 이면  Error 처리


			========================================================================= */

			if(StringUtils.isEmpty(mobilePhoneNum) || mobilePhoneNum == null || "".equals(mobilePhoneNum)) { // 전화번호 인자가 빈값일 경우
				resultMap.put(validationCdKey, "N");
				resultMap.put(validationMsgKey, "입력값 없음");
				return resultMap;
			}

			if( mobilePhoneNum.length() < 10 || mobilePhoneNum.length() > 11 ) { // 휴대폰 번호가 10자리 미만 or 휴대폰 번호가 11자리 초과
				resultMap.put(validationCdKey, "N");
				resultMap.put(validationMsgKey, "휴대폰 번호가 10자리 미만 or 휴대폰 번호가 11자리 초과 ");
				return resultMap;
			}

			String mobilePhoneNum1 = mobilePhoneNum.substring(0,3);	// 앞자리
			String mobilePhoneNum2 = mobilePhoneNum.substring(3,mobilePhoneNum.length()-4); // 가운데
			String mobilePhoneNum3 = mobilePhoneNum.substring(mobilePhoneNum.length()-4); // 끝자리
			int nMobilePhoneNum = CommonUtil.cvtInt(mobilePhoneNum); // 숫자값
			int midNum = CommonUtil.cvtInt(mobilePhoneNum2);
			String telecom = mobilePhoneNum.substring(2,3);	// 통신사 구분자리
			String fstMidNum = mobilePhoneNum.substring(3,mobilePhoneNum.length()-7); // 가운데 번호 첫자리

			if(nMobilePhoneNum == 0) { // 숫자값이 아닐경우
				resultMap.put(validationCdKey, "N");
				resultMap.put(validationMsgKey, "숫자값이 아님");
				return resultMap;
			}

			if("016789".indexOf(telecom) < 0) { // 일치하는 통신사가 없을 경우
				resultMap.put(validationCdKey, "N");
				resultMap.put(validationMsgKey, "존재하는 통신사 번호가 아님");
				return resultMap;
			}
			if(mobilePhoneNum2.length() < 3 || mobilePhoneNum2.length() > 4) { // 국번 3~4 자리 허용
				resultMap.put(validationCdKey, "N");
				resultMap.put(validationMsgKey, "국번은 3~4 자리만 허용");
				return resultMap;
			}

			if("010".equals(mobilePhoneNum1)) { // 010 일 경우
				if(mobilePhoneNum.length() != 11) { // 010은 11자리 제한
					resultMap.put(validationCdKey, "N");
					resultMap.put(validationMsgKey, "010은 11자리만 허용");
					return resultMap;
				}
				if("01".indexOf(fstMidNum) >= 0){ // 010은 국번이 0이나 1로 시작 못함.
					resultMap.put(validationCdKey, "N");
					resultMap.put(validationMsgKey, "010은 국번 첫자리가 0 또는 1이 올 수 없음.");
					return resultMap;
				}
			} else { // 011, 016, 017, 018, 019 일 경우
				if(mobilePhoneNum2.length() == 3) { // 국번이 3자리인 경우
					if((midNum >= 0 && midNum <= 199) || (midNum >= 900 && midNum <= 999)){ // "200~899" 만 허용하고 000~199 ,900~999 이면  Error 처리
						resultMap.put(validationCdKey, "N");
						resultMap.put(validationMsgKey, "3자리 국번은 200~899 만 허용 (011, 016, 017, 018, 019)");
						return resultMap;
					}
				} else if(mobilePhoneNum2.length() == 4) {
					if("011".equals(mobilePhoneNum1)) {
						if(midNum < 1700 || (midNum > 1799 && midNum < 9000) || midNum > 9999){
							resultMap.put(validationCdKey, "N");
							resultMap.put(validationMsgKey, "010은 국번이 1700~1799 혹은 9500~9999 만 허용");
							return resultMap;
						}
					} else if("019".equals(mobilePhoneNum1) || "016".equals(mobilePhoneNum1)) {
						if(midNum < 9000 || midNum > 9999){
							resultMap.put(validationCdKey, "N");
							resultMap.put(validationMsgKey, "016, 019는 국번이 9000~9999 만 허용");
							return resultMap;
						}
					} else if("017".equals(mobilePhoneNum1) || "018".equals(mobilePhoneNum1)) {
						if(mobilePhoneNum2.length() == 4) {
							resultMap.put(validationCdKey, "N");
							resultMap.put(validationMsgKey, "017, 018는 국번이 4자리 불가");
							return resultMap;
						}
					}
				}

			}
			// 2017.11.13 안정훈 수정

			// AS-IS
			/*
			if(mobilePhoneNum == null) {
				resultMap.put(validationCdKey, "N");
				resultMap.put(validationMsgKey, "입력값 : null");
				return resultMap;
			}

			int nMobilePhoneNum = CommonUtil.cvtInt(mobilePhoneNum); // 숫자값
			String mobilePhoneNum1 = mobilePhoneNum.substring(0,3);	// 앞자리
			String mobilePhoneNum2 = mobilePhoneNum.substring(3,mobilePhoneNum.length()-4); // 가운데
			String mobilePhoneNum3 = mobilePhoneNum.substring(mobilePhoneNum.length()-4); // 끝자리

			if(nMobilePhoneNum==0) { // 숫자값이 아닐경우
				resultMap.put(validationCdKey, "N");
				resultMap.put(validationMsgKey, "숫자값이 아님");
				return resultMap;
			}

			int nCount = 0;
			String [] arrHpNum1 = new String[]{"010" , "011", "016" , "017" , "018", "019"};
			for(String hpNum1 : arrHpNum1) {
				if(hpNum1.equals(mobilePhoneNum1)) {
					nCount ++;
				}
			}
			if(nCount==0) { // 일치하는 통신사가 없을 경우
				resultMap.put(validationCdKey, "N");
				resultMap.put(validationMsgKey, "통신사 오류");
				return resultMap;
			}

			// 1. 첫째 자리가 010이 아니면서 두번째 자리가 3자리 미만인 경우
			if(!"010".equals(mobilePhoneNum1)) { // 1. 첫째 자리가 010이 아니면서
				if(mobilePhoneNum2.length() < 3) { // 두번째 자리가 3자리 미만인 경우
					resultMap.put(validationCdKey, "N");
					resultMap.put(validationMsgKey, "첫째 자리가 010이 아니면서 두번째 자리가 3자리 미만인 경우");
					return resultMap;
				}
			}

			// 2. 첫째 자리가 010이면서 두번째칸이 4자리 미만인 경우
			if("010".equals(mobilePhoneNum1)) { // 1. 첫째 자리가 010이면서
				if(mobilePhoneNum2.length() < 4) { // 두번째칸이 4자리 미만인 경우
					resultMap.put(validationCdKey, "N");
					resultMap.put(validationMsgKey, "첫째 자리가 010이면서 두번째칸이 4자리 미만인 경우");
					return resultMap;
				}
			}

			// 3. 두번째 자리가 4자리이면서 2000미만인 경우
			if(mobilePhoneNum2.length() == 4) {
				int nMobilePhoneNum2 = CommonUtil.cvtInt(mobilePhoneNum2);
				if(nMobilePhoneNum2<2000) {
					resultMap.put(validationCdKey, "N");
					resultMap.put(validationMsgKey, "두번째 자리가 4자리이면서 2000미만인 경우");
					return resultMap;
				}
			}

			// 4. 세번째 자리가 4자리 미만인 경우
			if(mobilePhoneNum3.length() < 4) {
				resultMap.put(validationCdKey, "N");
				resultMap.put(validationMsgKey, "세번째 자리가 4자리 미만인 경우");
				return resultMap;
			}
			*/
			// AS-IS

			resultMap.put(validationCdKey, "Y");	//
			resultMap.put(validationMsgKey, "OK");	// 성공
			resultMap.put("MOBILE_PHONE_NUM1", mobilePhoneNum1);	// 휴대폰 앞자리
			resultMap.put("MOBILE_PHONE_NUM2", mobilePhoneNum2);	// 휴대폰 가운데
			resultMap.put("MOBILE_PHONE_NUM3", mobilePhoneNum3); // 휴대폰 끝자리
			resultMap.put("MOBILE_PHONE_NUM_DESC", mobilePhoneNum1+"-"+mobilePhoneNum2+"-"+mobilePhoneNum3);

			return resultMap;
		}

		/**
		 * 파일 사이즈
		 * <pre>
		 * 사용예)
		 * CommonUtil.getChangeFileSize("1945456312")
		 * @param String
		 * @return
		 */
		public static String getChangeFileSize(String str) {
			String sFileSize = str;
			double fileSize = 0;

			if(!"".equals(sFileSize) ){
				DecimalFormat format = new DecimalFormat();
		        format.applyLocalizedPattern("0.#");
		        try {
		        	fileSize = Integer.parseInt(sFileSize);
		        }catch (Exception NumberFormatException) {
		        	fileSize = 0;
		        }
				if(Math.floor(fileSize / (1024 * 1024)) > 0){
					fileSize = fileSize / (1024 * 1024) ;
			        sFileSize = format.format(fileSize) + "MB";
				}else if(fileSize / 1024 > 0){
					fileSize = Math.round( fileSize / 1024 );
					sFileSize = format.format(fileSize) + "KB";
				}
			}
			return sFileSize;
		}



		public static String replaceXSS(String description) {
			if(description == null) {
				return "";
			}
			description = CommonUtil.convertXSS(description);

			return description;
		}

		 /**
	     * 객체의 클래스 네임을 추출한다(패키지 제외).
	     * @param  object(불러올 클래스 : this)
	     * @return className
	     */
	    public static String getClassName(Object object) {

	    	String fullName = object.getClass().getName();

	    	if(fullName == null) {
	    		return "";
	    	}

	    	String[] text = fullName.split("\\.");

			fullName = text[text.length - 1];
	    	return fullName;
	    }


		/**
		 * 메서드 : convertXSS
		 * 설명 :  Map 의 Cross Site Script를 변환
		 * 작성자 : 이기석
		 * 작성일 : 2016-04-04
		 * @param searchVO
		 * @return
		 */
		public static  Map<String,Object>  convertXSS (Map<String,Object> searchVO)
		{
			for (Iterator i = searchVO.keySet().iterator(); i.hasNext();) {
				Object key = i.next();
				try {
					String value = searchVO.get(key) == null ? "": (String) searchVO.get(key);
					searchVO.put((String) key, CommonUtil.convertXSS(value));
				} catch (ClassCastException e) {

				}
			}
			return searchVO;
		}


		public static  Map<String,Object>  reConvertXSS (Map<String,Object> searchVO)
		{
			for (Iterator i = searchVO.keySet().iterator(); i.hasNext();) {
				Object key = i.next();
				try {
					String value = searchVO.get(key) == null ? "": (String) searchVO.get(key);
					searchVO.put((String) key, CommonUtil.reConvertXSS(value));
				} catch (ClassCastException e) {

				}
			}
			return searchVO;
		}

		//reConvertXSS(Map<String,Object> searchVO) 가 안되어 수정 버전 만듦.
		public static  Map<String,Object> reConvertXSS2(Map<String,Object> searchVO)
		{
			Map<String,Object> rtnMap = new HashMap<>();
			for (Iterator i = searchVO.keySet().iterator(); i.hasNext();) {
				Object key = i.next();
				try {
					String value = searchVO.get(key) == null ? "": (String) searchVO.get(key);
					rtnMap.put((String) key, CommonUtil.reConvertXSS2(value));
				} catch (ClassCastException e) {

				}
			}
			return rtnMap;
		}



		public static String replaceSidoName(String text) {
			String result = "";

			if(text.equals("충북")) {
				result = "충청북도";
			} else if(text.equals("충남")) {
				result = "충청남도";
			} else if(text.equals("전북")) {
				result = "전라북도";
			} else if(text.equals("전남")) {
				result = "전라남도";
			} else if(text.equals("경북")) {
				result = "경상북도";
			} else if(text.equals("경남")) {
				result = "경상남도";
			} else {
				result = text;
			}

			return result;
		}

		public static String replaceCutSidoName(Object text) {
			String result = nvl(text, "");

			if(result.equals("서울특별시")) {
				result = "서울";
			} else if(result.equals("부산광역시")) {
				result = "부산";
			} else if(result.equals("인천광역시")) {
				result = "인천";
			} else if(result.equals("대구광역시")) {
				result = "대구";
			} else if(result.equals("대전광역시")) {
				result = "대전";
			} else if(result.equals("광주광역시")) {
				result = "광주";
			} else if(result.equals("울산광역시")) {
				result = "울산";
			} else if(result.equals("광주광역시")) {
				result = "광주";
			} else if(result.equals("제주도")) {
				result = "제주";
			} else if(result.equals("경기도")) {
				result = "경기";
			} else if(result.equals("경상남도")) {
				result = "경남";
			} else if(result.equals("경상북도")) {
				result = "경북";
			} else if(result.equals("충청남도")) {
				result = "충남";
			} else if(result.equals("충청북도")) {
				result = "충북";
			} else if(result.equals("전라남도")) {
				result = "전남";
			} else if(result.equals("전라북도")) {
				result = "전북";
			} else if(result.equals("강원도")) {
				result = "강원";
			} else if(result.equals("세종시")) {
				result = "세종";
			}

			return result;
		}

		public static String getFileExist(String strUrl) {
			URL url = null;
			URLConnection con = null;
			String strImgPath = null;
			try {
				url = new URL(strUrl);
				con = url.openConnection();
				java.net.HttpURLConnection http = (java.net.HttpURLConnection)con;

				if(http.getResponseCode() == 404){
					strImgPath = null;
				}else if(http.getResponseCode() == 200){
					if(strUrl.indexOf("https:") > -1) {
						strImgPath = strUrl;
					} else {
						strImgPath = strUrl;
//						strImgPath = strUrl.replaceAll("http:", "https:");
					}

				}
			} catch(Exception e){
				strImgPath = null;
			}

			return strImgPath;
		}

		/**
		 * 인코딩할 URL을 받아서 인코딩값을 얻는다.
		 * @param originUrl
		 * @return 인코딩URL
		 */
		public static String encodeUrl(String originUrl) {
			String encodedUrl = "";
			if (originUrl != null && originUrl.length() > 0) {
				try {
					encodedUrl = URLEncoder.encode(originUrl, "UTF-8");
				} catch (Exception e) {}
			}

			return encodedUrl;
		}

		/**
		 * 메서드명 : getClob
		 * 메서드 설명 :  CLOB Type의 Data를 String 형으로 변환한다.
		 * 작성자 : 이기석
		 * 작성일 : 2016-04-18
		 * @param clob
		 * @return
		 * @throws SQLException
		 * @throws IOException
		 */
		public static String clobToString(Clob clob) {

			if (clob == null) {
				return "";
			}

			String retVal = "";

			try {
				BufferedReader reader = new BufferedReader( clob.getCharacterStream());
				StringBuffer output = new StringBuffer();
				if (reader != null) {

					// Reader input = reader;//rs.getCharacterStream(3);
					char[] buffer = new char[1024];
					int byteRead;

					while ((byteRead = reader.read(buffer, 0, 1024)) != -1) {
						output.append(buffer, 0, byteRead);
					}
					reader.close();
					retVal = output.toString();

				}
			} catch (SQLException e1) {
				// TODO Auto-generated catch block
//				e1.printStackTrace();
				LOGGER.info(e1.getMessage());
			} catch (IOException e) {
				LOGGER.info(e.getMessage());
//				e.printStackTrace();
				// TODO Auto-generated catch block
			}

			return retVal;
		}

		public static  Map<String,Object>  clobToString (Map<String,Object> searchVO)
		{
			if(searchVO==null)
			{
				return new HashMap<String,Object>();
			}
			for (Iterator i = searchVO.keySet().iterator(); i.hasNext();) {
				Object key = i.next();
				String sValue = "";
				try {
					Clob value = (Clob) searchVO.get(key);
					sValue = clobToString(value);
				} catch (ClassCastException e) {
					sValue = searchVO.get(key)==null?"":searchVO.get(key).toString();


				}
				searchVO.put((String) key, sValue);
			}

			return searchVO;
		}

		/**
		 * 메서드명 : getMask
		 * 메서드 설명 : 문자열 Mask
		 * 작성자 : 이기석
		 * 작성일 : 2016-04-18
		 * 사용 예 :
		 * 이름 2번째 글자 마스크 처리
		 * String maskUserName = CommonUtil.getMask("홍길동", 1, 2 , "*");
		 * maskUserName : 홍*동
		 * @param str
		 * @param nIndex
		 * @param nLength
		 * @param padChar
		 * @return
		 */
		/*
		 * public static String getMask(String str, int nIndex, int nLength, String
		 * padChar) { StringUtils su = new StringUtils(); if(str==null ||
		 * "".equals(str)) { return ""; } if(nLength > str.length()) // 문자열 길이보다 클 경우 {
		 * nLength = str.length(); // 마스킹 길이 = 문자열 길이 }
		 * 
		 * if(nLength < 0) // 길이가 0 보다 작을 경우 { nLength = str.length(); // 마스킹 길이 = 문자열
		 * 길이 } String sFront = su.left(str, nIndex); // 앞글자 String sRPad = su.rightPad(
		 * sFront, nLength,padChar); // 범위만큼 마스킹 String sBack = su.right(str,
		 * str.length() - sRPad.length()); // 뒷글자
		 * 
		 * String sMasking = sRPad+sBack; // 문자열 마스킹 처리 + 뒷글자 return sMasking; }
		 */


	    
		/**
		 * 메서드명 : getReplyStr
		 * 메서드 설명 : 회신여부 String
		 * 작성자 : 박세준
		 * 작성일 : 2020-12-21
		 * 사용 예 :
		 * 말머리에 [회신 필요] or [회신 불필요]가 붙었을 경우 해당 String return
		 * String replyStr = CommonUtil.getReplyStr("[회신 필요]abcd");
		 * replyStr : 회신 필요
		 * @param str
		 * @return
		 */
		public static String getReplyStr(String str)
		{
			if(str == null  || "".equals(str)){
				return "";
			}
			str = str.replaceAll("^(\\[.*?[^\\]]\\]).*", "$1");
			if(str.indexOf("[회신 필요]") > -1){
				str = "회신 필요";
			}else if(str.indexOf("[회신 불필요]") > -1){
				str = "회신 불필요";
			}else{
				str = "";
			}

			return str;
		}

	    public static String replaceContentsTag(Object text) {

	    	String orgnContent = nvl(text, "");

			return orgnContent;
			//공통으로 처리
//			return replaceContentsTagNew(text);
		}

	   

	    public static String replaceHTML2(String htmltxt){
			String onlytxt = NVL(htmltxt);
			       onlytxt = onlytxt.replaceAll("<title.*</title>","");
			       onlytxt = onlytxt.replaceAll("<script.*</script>","");
			       onlytxt = onlytxt.replaceAll("<style.*</style>","");
			       onlytxt = onlytxt.replaceAll("\n"," ");
			       onlytxt = onlytxt.replaceAll("<h5>"," ");
			       onlytxt = onlytxt.replaceAll("<h5/>"," ");
			       onlytxt = onlytxt.replaceAll("<br>"," ");
			       onlytxt = onlytxt.replaceAll("<br/>"," ");
			       onlytxt = onlytxt.replaceAll("&nbsp;"," ");
			       onlytxt = onlytxt.replaceAll("<(/)?([a-zA-Z]*)(\\s[a-zA-Z]*=[^>]*)?(\\s)*(/)?>", "");

			       onlytxt = replaceNoHTML(onlytxt);

			return onlytxt;
		}

	    public static String replaceNoHTML(String p_str){
	        if ( p_str == null )
	        {
	            p_str = "";
	        }
	        else
	        {
	            p_str = p_str.trim();
	            p_str = replaceXSS(p_str);
	        }

	        return p_str;
	    }

	//키워드 나누기 한글자이상만
	public static ArrayList getKeywordArrayUpOne(String text, String separator){
		String[] textArray = text.split(separator);
		ArrayList resultTextList = new ArrayList();
		for(int i=0; i < textArray.length; i++) {
			if(textArray[i].length() > 1) {
				resultTextList.add(textArray[i].toString());
			}
		}

		return resultTextList;
	}

	/**
	 * 파일 이름 확인 및 경로 확인
	 * @param File
	 * @return 결과
	 * @throws IOException
	 */
	public static Map<String, Object> checkFileNameAndPath(File f) throws IOException {
		HashMap<String, Object> resultMap = new HashMap<String, Object>();
		String fileName = f.getName();
		resultMap.put("result", "Y");

		if (fileName.indexOf(";") > 0 || fileName.indexOf("%00") > 0 || fileName.indexOf("../") > 0 || fileName.indexOf("..\\") > 0) {
			resultMap.put("result", "N");
			resultMap.put("resultMessage", "common.fileDownload.errorFileName");
		}

		if ( ! f.getAbsolutePath().equals(f.getCanonicalPath()) ){
			resultMap.put("result", "N");
			resultMap.put("resultMessage", "common.fileDownload.errorFile");
		}

		return resultMap;
	}

	public static boolean checkIp(HttpServletRequest req){

		boolean chk = false;
		HttpServletRequest dddd = req;
		String remoteaddr = req.getRemoteAddr();
		String remoteip =dddd.getHeader("Proxy-Client-IP");
		if(remoteip == null || "".equals(remoteip) ){
    		remoteip = dddd.getHeader("WL-Proxy-Client-IP");
    		if(remoteip == null){
    			remoteip = dddd.getHeader("X-Forwared-For");
    			if(remoteip == null){
    				remoteip = remoteaddr;
    			}
    		}
    	}
		if("156.147.50.38".equals(remoteip)){
			chk = true;
		}
		if("156.147.50.39".equals(remoteip)){
			chk = true;
		}
		return chk;
	}


	
	public static String isNull(Object Obj){
		String result = "";
		if( Obj != null ){
			result = Obj.toString();
		}
		return result;
	}
	
	
	/**
	 * SQL Injection Filter
	 * @param String
	 * @return boolean
	 */
	public static boolean sqlInjectionFilter(String str){
		String regex;
		Pattern pattern;
		Matcher matcher;
		
		/* 특수문자 확인*/
		regex = "['\"\\-#()@;=*/+]";
		pattern = Pattern.compile("['\"\\-#()@;=*/+]");
		matcher = pattern.matcher(str);
		if(matcher.find()){
			return false;
		}

		regex = "([\\s|\\.]union |[\\s|\\.]select |[\\s|\\.]drop |[\\s|\\.]update |[\\s|\\.]from |[\\s|\\.]where |[\\s|\\.]join |[\\s|\\.]substr |[\\s|\\.]declare |[\\s|\\.]dual )";
		pattern = Pattern.compile(regex, Pattern.CASE_INSENSITIVE);
		matcher = pattern.matcher(str);
		if(matcher.find()){
			return false;
		}
		
		return true;
	}


	
}