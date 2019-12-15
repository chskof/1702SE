package chenhs.agree;

import java.io.UnsupportedEncodingException;

public class PictureUtil {
	private static String FLAG = "90^$AN!CXF*M";

	private static String FLAG2 = "90^$AN!CXF*M.";

	public static String format(String picture, String input) {
		int length = 0;
		boolean left = false;
		if ((picture == null) || (picture.length() == 0)) {
			return input;
		}

		if (picture.charAt(0) == '-') {
			left = true;
			picture = picture.substring(1);
		}
		int index = picture.indexOf("\"");
		if (index == -1)
			length = Integer.parseInt(picture);
		else if (index == 0)
			length = picture.lastIndexOf("\"") - picture.indexOf("\"") - 1;
		else {
			length = Integer.parseInt(picture.substring(0, index));
		}

		picture = picture.substring(picture.indexOf("\"") + 1, picture.lastIndexOf("\""));

		if (picture.length() > 1) {
			if (!checkformat(picture, input, false)) {
				return "";
			}

			if (picture.indexOf('.') != -1) {
				int l = 0;
				for (int i = picture.indexOf('.'); i < picture.length(); i++) {
					if (FLAG.indexOf(picture.charAt(i)) != -1) {
						l++;
					}
				}
				boolean sign = false;
				if ((input.length() > 1) && (input.charAt(0) == '-')) {
					sign = true;
					input = input.substring(1);
				}
				while (input.length() < l + 1) {
					input = "0" + input;
				}
				if (sign) {
					input = '-' + input;
				}
			}
			for (int i = 0; i < input.length(); i++) {
				if (FLAG.indexOf(picture.charAt(picture.length() - 1 - i)) == -1) {
					input = input.substring(0, input.length() - i) + picture.charAt(picture.length() - 1 - i)
							+ input.substring(input.length() - i);
				}
			}

			if ((input.length() > 1) && (input.charAt(0) == '-')) {
				if (FLAG2.indexOf(picture.charAt(picture.length() - input.length() + 1)) == -1)
					input = "-" + input.substring(2);
				else if (picture.charAt(picture.length() - input.length() + 1) == '.')
					input = "-" + input.substring(1);
			}
		} else {
			if (input.length() > length)
				return "";
			for (int i = 0; i < input.length(); i++) {
				if (!checkdigit(picture.charAt(0), input.charAt(i), i)) {
					return "";
				}
			}
		}
		if (picture.charAt(0) == '*') {
			input = '*' + input;
		}
		if (picture.charAt(0) == '$') {
			input = '$' + input;
		}
		if (picture.charAt(0) == '^')
			input = input.toUpperCase();
		try {
			if (left) {
				while (input.getBytes("GB18030").length < length) {
					input = input + ' ';
				}
			} else {
				while (input.getBytes("GB18030").length < length) {
					input = ' ' + input;
				}
			}

		} catch (UnsupportedEncodingException localUnsupportedEncodingException) {
		}

		return input;
	}

	private static boolean checkformat(String picture, String input, boolean withpoint) {
		if (input.indexOf('.') != -1) {
			if (!withpoint) {
				return false;
			}
			int l = 0;
			for (int i = picture.indexOf('.'); i < picture.length(); i++) {
				if (FLAG.indexOf(picture.charAt(i)) != -1) {
					l++;
				}
			}
			if (input.indexOf('.') == -1) {
				input = input + '.';
			}
			if (input.length() - input.indexOf('.') - 1 > l) {
				input = input.substring(0, input.indexOf('.') + 1 + l);
			}
			while (input.length() - input.indexOf('.') - 1 < l) {
				input = input + "0";
			}
			input = input.substring(0, input.indexOf('.')) + input.substring(input.indexOf('.') + 1);
		}

		String picture0 = "";
		for (int i = 0; i < picture.length(); i++) {
			if (FLAG.indexOf(picture.charAt(i)) != -1) {
				picture0 = picture0 + picture.charAt(i);
			}
		}
		if (input.length() > picture0.length()) {
			return false;
		}
		return checktype(picture0, input);
	}

	private static boolean checktype(String picture, String input) {
		for (int i = 0; i < input.length(); i++) {
			if (!checkdigit(picture.charAt(picture.length() - 1 - i), input.charAt(input.length() - 1 - i),
					input.length() - 1 - i)) {
				return false;
			}
		}
		return true;
	}

	private static boolean checkdigit(char picture, char input, int index) {
		switch (picture) {
		case '$':
		case '*':
		case '0':
		case '9':
			if (((input > '9') || (input < '0')) && (input != '-')) {
				return false;
			}
			if ((input != '-') || (index == 0))
				break;
			return false;

		case 'A':
			if (((input >= 'a') && (input <= 'z')) || ((input >= 'A') && (input <= 'Z')))
				break;
			return false;

		case '!':
		case 'N':
			if (((input >= 'a') && (input <= 'z')) || ((input >= 'A') && (input <= 'Z'))
					|| ((input >= '0') && (input <= '9')))
				break;
			return false;

		case 'C':
			if (input >= '㐀')
				break;
			return false;

		case 'F':
			if (input >= 'Ā')
				break;
			return false;

		}

		return true;
	}

	public static String format_withpoint(String picture, String input) {
		int length = 0;
		boolean left = false;
		if ((picture == null) || (picture.length() == 0)) {
			return input;
		}

		if (picture.charAt(0) == '-') {
			left = true;
			picture = picture.substring(1);
		}
		int index = picture.indexOf("\"");
		if (index == -1)
			length = Integer.parseInt(picture);
		else if (index == 0)
			length = picture.lastIndexOf("\"") - picture.indexOf("\"") - 1;
		else {
			length = Integer.parseInt(picture.substring(0, index));
		}

		picture = picture.substring(picture.indexOf("\"") + 1, picture.lastIndexOf("\""));

		if (picture.length() > 1) {
			if (!checkformat(picture, input, true)) {
				return "";
			}

			if (picture.indexOf('.') != -1) {
				int l = 0;
				for (int i = picture.indexOf('.'); i < picture.length(); i++) {
					if (FLAG.indexOf(picture.charAt(i)) != -1) {
						l++;
					}
				}
				if (input.indexOf('.') == -1) {
					input = input + '.';
				}
				if (input.length() - input.indexOf('.') - 1 > l) {
					input = input.substring(0, input.indexOf('.') + 1 + l);
				}
				while (input.length() - input.indexOf('.') - 1 < l) {
					input = input + "0";
				}
			}
			for (int i = 0; i < input.length(); i++) {
				if (FLAG2.indexOf(picture.charAt(picture.length() - 1 - i)) == -1) {
					input = input.substring(0, input.length() - i) + picture.charAt(picture.length() - 1 - i)
							+ input.substring(input.length() - i);
				}
			}

			if ((input.length() > 1) && (input.charAt(0) == '-')
					&& (FLAG2.indexOf(picture.charAt(picture.length() - input.length() + 1)) == -1)) {
				input = "-" + input.substring(2);
			}
		} else {
			if (input.length() > length)
				return "";
			for (int i = 0; i < input.length(); i++) {
				if (!checkdigit(picture.charAt(0), input.charAt(i), i)) {
					return "";
				}
			}
		}
		if (picture.charAt(0) == '*') {
			input = '*' + input;
		}
		if (picture.charAt(0) == '$') {
			input = '$' + input;
		}
		if (picture.charAt(0) == '^')
			input = input.toUpperCase();
		try {
			if (left) {
				while (input.getBytes("GB18030").length < length) {
					input = input + ' ';
				}
			} else {
				while (input.getBytes("GB18030").length < length) {
					input = ' ' + input;
				}
			}

		} catch (UnsupportedEncodingException localUnsupportedEncodingException) {
		}

		return input;
	}

	public static void main(String[] args) {
		String picture = "\"999,999,999,999.99\"";
		String input = "1";
		System.out.println("[" + format(picture, input) + "]");
		picture = "-\"999,999,999,999.99\"";
		input = "123456789";
		System.out.println("[" + format(picture, input) + "]");
		System.out.println("[" + format_withpoint(picture, input) + "]");
		System.out.println("[" + format_withpoint(picture, "1234.567") + "]");
		System.out.println("[" + format_withpoint(picture, "1234.5") + "]");
		System.out.println("[" + format_withpoint(picture, "1234.56") + "]");
		picture = "30\"M\"";
		input = "我文wwwwww我";
		System.out.println("[" + format(picture, input) + "]");
		picture = "-30\"M\"";
		input = "我文wwwwww我";
		System.out.println("[" + format(picture, input) + "]");
		picture = "\"***,***,***,***.**\"";
		input = "123456789";
		System.out.println("[" + format(picture, input) + "]");
		picture = "\"999#999#999#999.99\"";
		input = "123456789";
		System.out.println("[" + format(picture, input) + "]");
		picture = "\"$$$,$$$,$$$,$$$.$$\"";
		input = "123456789";
		System.out.println("[" + format(picture, input) + "]");
		picture = "\"XXXXXXXXXXXXXXXXXX\"";
		input = "我e010101";
		System.out.println("[" + format(picture, input) + "]");

		picture = "-\"999,999,999,999.99\"";
		input = "-23456789";
		System.out.println("[" + format(picture, input) + "]");
		picture ="\"9999/99/99\"";
		input = "20180817";
		System.out.println("[" + format(picture, input) + "]");
	}
}