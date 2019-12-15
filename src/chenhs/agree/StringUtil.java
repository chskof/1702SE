package chenhs.agree;

import java.io.IOException;
import java.security.MessageDigest;
import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.StringTokenizer;


public class StringUtil {
	/**
	 * 999,999,999,999.80
	 */
	public static final NumberFormat LOCAL_NUMBERFORMAT = NumberFormat
			.getNumberInstance();

	/**
	 * 999999999999.80
	 */
	public static final NumberFormat LOCAL_NUMBERFORMAT_NOGROUP = NumberFormat
			.getNumberInstance();
	static {
		LOCAL_NUMBERFORMAT.setMinimumFractionDigits(2);
		LOCAL_NUMBERFORMAT_NOGROUP.setGroupingUsed(false);
		LOCAL_NUMBERFORMAT_NOGROUP.setMinimumFractionDigits(2);
	}

	/**
	 * 将double转换成当前语言环境的数字通用格式<br>
	 * 最少两位小数<br>
	 * 如：99.99999999998e10 --> 999,999,999,999.80
	 * 
	 * @param number
	 *            double类型的数字
	 * @return 返回如:99,999,999,999.98
	 * @author wang.wh
	 * @see StringUtilEx#double2StringNoGroup
	 */
	public static String double2String(double number) {
		return LOCAL_NUMBERFORMAT.format(number);
	}

	/**
	 * 将double转换成当前语言环境的数字通用格式<br>
	 * 字符串没有使用分组，最少两位小数<br>
	 * 如：99.99999999998e10 --> 999999999999.80
	 * 
	 * @param number
	 *            double类型的数字
	 * @return 返回如:99999999999.98
	 * @author wang.wh 2010-12-22 njcb
	 * @see StringUtilEx#double2String
	 */
	public static String double2StringNoGroup(double number) {
		return LOCAL_NUMBERFORMAT_NOGROUP.format(number);
	}

	// 截取字符串的中间字符串
	public static String getMiddleString(String ss, String strStart,
			String endStart) {

		int start = ss.indexOf(strStart);
		int end = ss.indexOf(endStart);
		if (start == -1 || end == -1 || start >= end)
			return "";

		String s = ss.substring(start);
		s = s.substring(strStart.length(), s.indexOf(endStart));
		return s;
	}

	/**
	 * 将数组转换成字符串
	 * 
	 * @param strArr
	 * @param split
	 * @return
	 */
	public static String jion(String[] strArr, String split) {
		if (strArr == null)
			return "";
		String _str = "";
		for (int i = 0; i < strArr.length; i++) {
			if (_str.equals(""))
				_str = strArr[i];
			else
				_str += split + strArr[i];
		}
		return _str;
	}

	public static String jion(String str, String split, int n) {
		if (str == null)
			return "";
		String _str = "";
		for (int i = 0; i < n; i++) {
			if (_str.equals(""))
				_str = str;
			else
				_str += split + str;
		}
		return _str;
	}

	/**
	 * 判断字符串是否为空
	 * 
	 * @param p
	 * @return
	 */
	public static boolean isNull(String p) {
		if (p == null || p.equalsIgnoreCase("")) {
			return true;
		} else {
			return false;
		}
	}

	/*
	 * 获取随机数
	 */
	public static String random(int length) {
		int range = 10;
		String ret = "";
		Random rand = new Random();
		for (int i = 0; i < length; i++) {
			int temp = rand.nextInt(range);
			ret = ret + temp;
		}
		return ret;
	}

	/**
	 * 在制定的范围内，获取随机数
	 * 
	 * @param range
	 * @param count
	 * @return
	 */
	public static String[] random(int range, int count) {
		if (count > range) {
			return null;
		}
		String[] randstr = new String[count];
		List list = new ArrayList();
		for (int i = 0; i < range; i++) {
			list.add(String.valueOf(i));
		}
		Random rand = new Random();
		int n = 0;
		for (int i = 0; i < count; i++) {
			int j = rand.nextInt(list.size());
			randstr[n] = (String) list.get(j);
			list.remove(list.get(j));
			n++;
		}
		return randstr;
	}

	/**
	 * MD5加密
	 * 
	 * @param s
	 * @return
	 */
	public static String EncMD5(String s) {

		char hexDigits[] = { '0', '1', '2', '3', '4', '5', '6', '7', '8', '9',
				'a', 'b', 'c', 'd', 'e', 'f' };
		try {
			byte[] strTemp = s.getBytes();
			MessageDigest mdTemp = MessageDigest.getInstance("MD5");
			mdTemp.update(strTemp);
			byte[] md = mdTemp.digest();
			int j = md.length;
			char str[] = new char[j * 2];
			int k = 0;
			for (int i = 0; i < j; i++) {
				byte byte0 = md[i];
				str[k++] = hexDigits[byte0 >>> 4 & 0xf];
				str[k++] = hexDigits[byte0 & 0xf];
			}
			return new String(str);
		} catch (Exception e) {
			return null;
		}

	}

	public static String arrayToString(String strArray[]) {
		StringBuffer buffer = new StringBuffer();
		for (int i = 0; i < strArray.length; i++) {
			if ("".equals(buffer.toString())) {
				buffer.append(strArray[i]);
			} else {
				buffer.append("," + strArray[i]);
			}
		}

		return buffer.toString();
	}

	public static String[] stringToArray(String str, String sep_str) {
		StringTokenizer strToken = new StringTokenizer(str, sep_str);
		int tokenCount = strToken.countTokens();
		String str_array[] = new String[tokenCount];
		for (int i = 0; i < tokenCount; i++) {
			str_array[i] = strToken.nextToken();
		}

		return str_array;
	}

	public static String transferGBK(String s) throws Exception {
		if (s == null || "".equals(s)) {
			return "";
		}
		return new String(s.getBytes("iso8859-1"), "gbk");
	}

	public static String transferISO(String s) throws Exception {
		if (s == null || "".equals(s)) {
			return "";
		}
		return new String(s.getBytes("gbk"), "iso8859-1");
	}

	public static int parseInt(String str) {
		if (StringUtil.isNull(str)) {
			return 0;
		}
		return Integer.parseInt(str);
	}

	public static double parseDouble(String str) {
		if (StringUtil.isNull(str)) {
			return 0.00;
		}
		return Double.parseDouble(str);
	}

	/**
	 * 金额小数点后保留number位
	 * 
	 * @param str
	 *            金额，不带,
	 * @param number
	 *            小数点保留位数
	 * @return
	 */
	public static String getMoneyText(String str, int number) {
		if (isNull(str)) {
			return "";
		}
		StringBuffer sb = new StringBuffer();
		if (str.indexOf('.') > 0) {
			int m = str.length() - str.indexOf('.') - 1;
			if (m >= number) {
				// for (int i = 0; i < number; i++) {
				// sb.append("0");
				// }
				sb.append(str.substring(0, str.indexOf('.') + 1 + number));
			} else {
				sb.append(str);
				for (int i = 0; i < number - m; i++) {
					sb.append("0");
				}
			}
		} else {
			sb.append(str + ".");
			for (int i = 0; i < number; i++) {
				sb.append("0");
			}
		}
		return sb.toString();
	}

	public static String leftFillZero(String str, int number) {
		while (str.length() < number) {
			str = "0" + str;
		}
		return str;
	}


	/**
	 * 二进制数组转换成16进制的字符串
	 */
	public static String hexToStr(byte[] b) {
		StringBuffer sb = new StringBuffer();
		for (int i = 0; i < b.length; i++) {
			String s = Integer.toHexString(b[i] & 0xFF);
			if (s.length() == 1) {
				s = "0" + s;
			}
			sb.append(s);
		}
		return sb.toString();
	}

	/**
	 * 16进制的字符串换成二进制数组转
	 */
	public static byte[] strToHex(String hexStr) {
		byte[] b2 = new byte[hexStr.length() / 2];
		for (int i = 0; i < b2.length; i++) {
			b2[i] = (byte) Integer.parseInt(hexStr.substring(i * 2, i * 2 + 2),
					16);
		}
		return b2;
	}

	/**
	 * 左补齐
	 * @param str    原字符串
	 * @param length 补齐长度
	 * @param sp     用来补齐的字符
	 */
	public static String fillLeft(String str, int length, String sp) {
		if("".equals(sp))sp=" ";
		while (str.length() < length) {
			str = sp + str;
		}
		return str;
	}
	
	
	/**
     * 将数字金额转换为大写汉字金额
     * @param 金额
     * @since 由f表的金额转换算法移植过来
     * @author wyn
     * @return 汉字金额
     */
	private static final char[] chineseDigit = { '零', '壹', '贰', '叁', '肆', '伍',
			'陆', '柒', '捌', '玖' };
	
	private static final char[] chineseDigitUnit = { '拾', '佰', '仟', '万', '拾',
		'佰', '仟', '亿', '拾', '佰', '仟', '万', '拾' };

	private static final char[] chineseMoney = { '圆', '拾', '佰', '仟', '万', '拾',
		'佰', '仟', '亿', '拾', '佰', '仟', '万', '拾' };
	
	/**
	 * 数字金额转成中文大写 以分结尾   12345 壹佰贰拾叁圆肆角伍分    123.45 壹佰贰拾叁圆肆角伍分
     * 金额转汉字大写： 如在代码标题栏中写 "10,币种"，代表用数组第十个元素中的值在CONVERT中按"币种"查找中文含意, 然后与金额转汉字大写连接，如：美元壹仟贰佰元
     * 1234.50壹仟贰佰叁拾肆圆伍角整 100.0111壹佰圆零壹分 123456789012345壹万贰仟叁佰肆拾伍亿陆仟柒佰捌拾玖万零壹佰贰拾叁圆肆角伍分 123壹圆贰角叁分
     * 1234567壹万贰仟叁佰肆拾伍圆陆角柒分 1234567890壹仟贰佰叁拾肆万伍仟陆佰柒拾捌圆玖角整 1 壹分 12 壹角贰分 101 壹圆零壹分 100 壹圆整 50 伍角整 0
     * 零元整 50500 伍佰零伍圆整 123456789002345 壹万贰仟叁佰肆拾伍亿陆仟柒佰捌拾玖万零贰拾叁圆肆角伍分 100000000002345 壹万亿零贰拾叁圆肆角伍分
     * 100000000000045 壹万亿圆肆角伍分
     */
    public static String format_C(String s) throws Exception
    {
        // 去掉左边多余的“0”    
    	StringBuffer resBuffer = new StringBuffer();
    	
    	try{
	        StringBuffer sBuffer = new StringBuffer(s.trim());
	        while (sBuffer.length() > 1 && sBuffer.charAt(0) == '0')
	        {
	            sBuffer.deleteCharAt(0);
	        }
	        s = sBuffer.toString();
	
	        // 把“角、分”分离出来
	        String s角分;
	        String s元;
	        int index = s.indexOf('.');
	        if (index >= 0)
	        {
	            // 有小数点，表示以元为单位。
	         //   s角分 = StringUtils.rightPad(s.substring(index + 1), 2, '0');
	        	s角分=s.substring(index + 1)+"000";
	        	
	            while (s角分.length() > 2)
	            {
	                // 保留2位，截掉多余的数字
	                s角分 = s角分.substring(0, 2);
	            }
	            s元 = s.substring(0, index);
	        } else
	        {
	            // 无小数点，表示以分为单位。
	            while (s.length() < 2)
	                s = '0' + s;
	            s角分 = s.substring(s.length() - 2);
	            s元 = s.substring(0, s.length() - 2);
	        }
	    
	        if (s角分.charAt(1) == '0')
	        {
	            resBuffer.insert(0, "整");
	        } else
	        {
	            resBuffer.insert(0, "分");
	            resBuffer.insert(0, chineseDigit[Integer.parseInt(""
	                    + s角分.charAt(1))]);
	        }
	
	        if (s角分.charAt(0) == '0')
	        {
	            if (resBuffer.charAt(0) != '整')
	                resBuffer.insert(0, "零");
	        } else
	        {
	            resBuffer.insert(0, "角");
	            resBuffer.insert(0, chineseDigit[Integer.parseInt(""
	                    + s角分.charAt(0))]);
	        }
	        for (int i = 0; i < s元.length(); i++)
	        {
	            index = s元.length() - 1 - i;
	            char ch = s元.charAt(index);
	            if (i % 4 == 0 || ch != '0')
	                resBuffer.insert(0, chineseMoney[i]);
	            // if(ch=='0')
	            resBuffer.insert(0, chineseDigit[Integer.parseInt("" + ch)]);
	            index--;
	        }
	
	        // 去掉左边多余的“零”
	        while (resBuffer.charAt(0) == '零')
	        {
	            resBuffer.deleteCharAt(0);
	        }
	
	        // 去掉中间多余的“零”
	        index = 0;
	        while (index < resBuffer.length())
	        {
	            if (resBuffer.charAt(index) == '零')
	            {
	                if (!isChineseDigit(resBuffer.charAt(index + 1))
	                        || resBuffer.charAt(index + 1) == '零')
	                {
	                    resBuffer.deleteCharAt(index);
	                    continue;
	                }
	            }
	            index++;
	        }
	
	        // 去掉多余的“百千万亿”
	        index = 0;
	        while (index < resBuffer.length())
	        {
	            if (isChineseDigitUnit(resBuffer.charAt(index)))
	            {
	                if ((index + 1) < resBuffer.length()
	                        && isChineseDigitUnit(resBuffer.charAt(index + 1))
	                        && abnormal(resBuffer.charAt(index), resBuffer
	                                .charAt(index + 1)))
	                {
	                    resBuffer.deleteCharAt(index + 1);
	                    continue;
	                }
	            }
	            index++;
	        }
	        if (resBuffer.length() == 1 && resBuffer.charAt(0) == '整')
	        {
	            // TODO 到底应该用哪个“圆”“元”
	            resBuffer.insert(0, "零元");
	        }
    	}catch (Exception e) {
			// TODO: handle exception
    		return s;
		}
        return resBuffer.toString();
    }
    
    private static boolean isChineseDigitUnit(char ch)
    {
        for (int i = 0; i < chineseDigitUnit.length; i++)
        {
            if (ch == chineseDigitUnit[i])
                return true;
        }
        return false;
    }
	
    private static boolean isChineseDigit(char ch)
    {
        for (int i = 0; i < chineseDigit.length; i++)
        {
            if (ch == chineseDigit[i])
                return true;
        }
        return false;
    }
    
    /**
     * 判断是否有不合理的读法 例如“万亿”“千万”“千亿”是合法的，“亿万”“万千”“亿千”是非法的
     * 
     * @param ch1
     * @param ch2
     * @return
     */
    private static boolean abnormal(char ch1, char ch2)
    {
        int i1 = 0, i2 = 0;
        for (int i = 0; i < chineseDigitUnit.length; i++)
        {
            if (chineseDigitUnit[i] == ch1)
            {
                i1 = i;
                break;
            }
        }
        for (int i = 0; i < chineseDigitUnit.length; i++)
        {
            if (chineseDigitUnit[i] == ch2)
            {
                i2 = i;
                break;
            }
        }
        if (i1 > i2)
            return true;
        else
            return false;
    }
    
    public static void main(String[] args) throws Exception {
		String dx = format_C("12345");
		System.out.println(dx);
	}
    
}

