/**
 * taobao.com Inc. Copyright (c) 2009-2012 All Rights Reserved.
 */
package org.springframework.ext.biz.helper;


import org.apache.commons.lang3.StringUtils;
import org.springframework.ext.common.consts.StringConst;
import org.springframework.ext.common.util.Utils;

import java.util.Map;

/**
 * 签名辅助类
 * 
 * @author only 2012-8-3
 */
public class SignatureHelper {

	/**
	 * 生成签名url
	 * 
	 * @param url
	 *            请求url
	 * @param params
	 *            参数集合
	 * @param prefix
	 *            签名前缀
	 * @param suffix
	 *            签名后缀
	 * @param signKey
	 *            签名参数key
	 * @return 签名url
	 */
	public static String getMd5Url(String url, Map<String, String> params, String prefix, String suffix, String signKey) {
		String queryString = Utils.treeMap2Str(params, StringConst.AND, StringConst.EQ);
		return getMd5Url(url, queryString, prefix, suffix, signKey);
	}

	/**
	 * 生成签名url
	 * 
	 * @param url
	 *            请求url
	 * @param queryString
	 *            参数串
	 * @param prefix
	 *            签名前缀
	 * @param suffix
	 *            签名后缀
	 * @param signKey
	 *            签名参数key
	 * @return 签名url
	 */
	public static String getMd5Url(String url, String queryString, String prefix, String suffix, String signKey) {
		if (StringUtils.isNotEmpty(queryString)) {
			String signVal = getMd5Sign(queryString, prefix, suffix);
			return getSignUrl(url, queryString, signKey, signVal);
		}
		return url;
	}

	/**
	 * 为参数集合params生成调用接口url（含md5签名），参数串形式为k1=v1&k2=v2&k3=v3
	 * 
	 * @param url
	 *            请求url
	 * @param params
	 *            参数集合
	 * @param signKey
	 *            签名参数key
	 * @param signVal
	 *            签名参数值
	 * @return 调用接口签名url
	 */
	public static String getSignUrl(String url, Map<String, String> params, String signKey, String signVal) {
		String queryString = Utils.treeMap2Str(params, StringConst.AND, StringConst.EQ);
		return getSignUrl(url, queryString, signKey, signVal);
	}

	/**
	 * 为查询串queryString生成调用接口url（含md5签名）
	 * 
	 * @param url
	 *            请求url
	 * @param queryString
	 *            查询串
	 * @param signKey
	 *            签名参数key
	 * @param signVal
	 *            签名参数值
	 * @return 调用接口签名url
	 */
	public static String getSignUrl(String url, String queryString, String signKey, String signVal) {
		StringBuilder sb = new StringBuilder(url);
		if (StringUtils.isNotEmpty(queryString)) {
			sb.append(queryString);
		}
		if (StringUtils.isNotEmpty(signKey) && StringUtils.isNotEmpty(signVal)) {
			sb.append(StringConst.AND).append(signKey).append(StringConst.EQ).append(signVal);
		}
		return sb.toString();
	}

	/**
	 * 对参数集合params做md5签名：签名原始字符串为： prefix + 参数串 + suffix
	 * 
	 * @param params
	 *            参数集合
	 * @param prefix
	 *            前缀
	 * @param suffix
	 *            后缀
	 * @return md5签名
	 */
	public static String getMd5Sign(Map<String, String> params, String prefix, String suffix) {
		String queryString = Utils.treeMap2Str(params, StringConst.AND, StringConst.EQ);
		return getMd5Sign(queryString, prefix, suffix);
	}

	/**
	 * 对查询串queryString做md5签名：签名原始字符串为：prefix + queryString + suffix
	 * 
	 * @param queryString
	 *            查询串
	 * @param prefix
	 *            前缀
	 * @param suffix
	 *            后缀
	 * @return md5签名
	 */
	public static String getMd5Sign(String queryString, String prefix, String suffix) {
		return CryptoHelper.md5Hex(prefix + queryString + suffix);
	}
}
