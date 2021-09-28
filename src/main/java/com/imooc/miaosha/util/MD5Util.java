package com.imooc.miaosha.util;

import org.apache.commons.codec.digest.DigestUtils;


/**
 * @author zhou
 *
 * MD5 加密工具类
 */
public class MD5Util {

	/**
	 * MD5 加密
	 * @param src	源字符串
	 * @return
	 */
	public static String md5(String src) {
		return DigestUtils.md5Hex(src);
	}

	/**
	 * 拼接字符
	 */
	private static final String salt = "1a2b3c4d";

	/**
	 * 第一次 MD5
	 * @param inputPass		表单提交密码
	 * @return
	 */
	public static String inputPassToFormPass(String inputPass) {
		String str = "" + salt.charAt(0) + salt.charAt(2) + inputPass + salt.charAt(5) + salt.charAt(4);
		//System.out.println(str);
		return md5(str);
	}

	/**
	 * 第二次 MD5
	 * @param formPass		一次加密后的字符串
	 * @param salt			拼接字符
	 * @return
	 */
	public static String formPassToDBPass(String formPass, String salt) {
		String str = ""+salt.charAt(0) + salt.charAt(2) + formPass +salt.charAt(5) + salt.charAt(4);
		return md5(str);
	}

	/**
	 * 将表单提交密码经两次 MD5 加密
	 * @param inputPass		表单提交密码
	 * @param saltDB		拼接字符
	 * @return
	 */
	public static String inputPassToDbPass(String inputPass, String saltDB) {
		String formPass = inputPassToFormPass(inputPass);
		String dbPass = formPassToDBPass(formPass, saltDB);
		return dbPass;
	}
	
	public static void main(String[] args) {
		System.out.println(inputPassToFormPass("123456"));//d3b1294a61a07da9b49b6e22b2cbd7f9
//		System.out.println(formPassToDBPass(inputPassToFormPass("123456"), "1a2b3c4d"));
//		System.out.println(inputPassToDbPass("123456", "1a2b3c4d"));//b7797cce01b4b131b433b6acf4add449
	}
	
}
