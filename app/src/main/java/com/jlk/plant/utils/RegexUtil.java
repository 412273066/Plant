package com.jlk.plant.utils;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class RegexUtil {
	/**
	 * 按照指定正则表达式字符串提取
	 * 
	 * @param str
	 * @param regex
	 * @return
	 */
	public static String getString(String str, String regex) {
		Pattern pattern = Pattern.compile(regex);
		Matcher matcher = pattern.matcher(str);
		if (!matcher.find()) {
			return null;
		}
		return matcher.group(0);
	}

	/**
	 * 是否存在符合指定正则表达式的字符串
	 * 
	 * @param str
	 * @param regex
	 * @return
	 */
	public static boolean isExist(String str, String regex) {
		Pattern pattern = Pattern.compile(regex);

		Matcher matcher = pattern.matcher(str);

		return matcher.matches();

	}

	/**
	 * 字符串中符合正则表达式的字符替换成指定字符
	 * 
	 * @param original
	 * @param regex
	 * @param replace
	 * @return
	 */
	public static String charReplace(String original, String regex,
			String replace) {
		Pattern pattern = Pattern.compile(regex);
		Matcher matcher = pattern.matcher(original);
		String str = matcher.replaceAll(replace);
		return str;
	}
}
