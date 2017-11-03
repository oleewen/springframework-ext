/**
 * taobao.com Inc. Copyright (c) 2009-2012 All Rights Reserved.
 */
package org.springframework.ext.biz.helper;

import org.apache.commons.codec.binary.Base64;
import org.apache.commons.codec.binary.Hex;
import org.apache.commons.codec.digest.DigestUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.ext.common.consts.StringConst;

import javax.crypto.Cipher;
import javax.crypto.KeyGenerator;
import javax.crypto.Mac;
import javax.crypto.spec.SecretKeySpec;
import java.security.Key;
import java.security.SecureRandom;

/**
 * 加密解密算法辅助类
 *
 * @author only 2013-1-4
 */
public class CryptoHelper {

	/**
	 * MD5加密
	 * <p/>
	 * <pre>
	 * MD5:即Message-Digest Algorithm 5（信息-摘要算法 5），用于确保信息传输完整一致。
	 * MD5是计算机广泛使用的杂凑算法之一（又译摘要算法、哈希算法），主流编程语言普遍已有MD5实现。
	 * MD5将数据（如汉字）运算为另一固定长度值，是杂凑算法的基础原理，MD5的前身有MD2、MD3和MD4。
	 * MD5的作用是让大容量信息在用数字签名软件签署私人密钥前被"压缩"成一种保密的格式（就是把一个任意长度的字节串变换成一定长的十六进制数字串）
	 * </pre>
	 *
	 * @param str 需要签名的字符串
	 * @return md5加密串（16进制）
	 */
	public static String md5Hex(String str) {
		if (StringUtils.isBlank(str)) {
			return StringConst.EMPTY;
		}
		// 直接是apache的库
		return DigestUtils.md5Hex(str);
	}

	/**
	 * MD5加密
	 * <p/>
	 * <pre>
	 * MD5:即Message-Digest Algorithm 5（信息-摘要算法 5），用于确保信息传输完整一致。
	 * MD5是计算机广泛使用的杂凑算法之一（又译摘要算法、哈希算法），主流编程语言普遍已有MD5实现。
	 * MD5将数据（如汉字）运算为另一固定长度值，是杂凑算法的基础原理，MD5的前身有MD2、MD3和MD4。
	 * MD5的作用是让大容量信息在用数字签名软件签署私人密钥前被"压缩"成一种保密的格式（就是把一个任意长度的字节串变换成一定长的十六进制数字串）
	 * </pre>
	 *
	 * @param bytes 需要签名的字节数组
	 * @return md5加密串（16进制）
	 */
	public static String md5Hex(byte[] bytes) {
		if (bytes == null || bytes.length == 0) {
			return StringConst.EMPTY;
		}
		// 直接是apache的库
		return DigestUtils.md5Hex(bytes);
	}

	/**
	 * MD5加密
	 * <p/>
	 * <pre>
	 * MD5:即Message-Digest Algorithm 5（信息-摘要算法 5），用于确保信息传输完整一致。
	 * MD5是计算机广泛使用的杂凑算法之一（又译摘要算法、哈希算法），主流编程语言普遍已有MD5实现。
	 * MD5将数据（如汉字）运算为另一固定长度值，是杂凑算法的基础原理，MD5的前身有MD2、MD3和MD4。
	 * MD5的作用是让大容量信息在用数字签名软件签署私人密钥前被"压缩"成一种保密的格式（就是把一个任意长度的字节串变换成一定长的十六进制数字串）
	 * </pre>
	 *
	 * @param str 需要签名的字符串
	 * @return md5加密串
	 */
	public static byte[] md5(String str) {
		if (StringUtils.isBlank(str)) {
			return new byte[0];
		}
		// 直接是apache的库
		return DigestUtils.md5(str);
	}

	/**
	 * MD5加密
	 * <p/>
	 * <pre>
	 * MD5:即Message-Digest Algorithm 5（信息-摘要算法 5），用于确保信息传输完整一致。
	 * MD5是计算机广泛使用的杂凑算法之一（又译摘要算法、哈希算法），主流编程语言普遍已有MD5实现。
	 * MD5将数据（如汉字）运算为另一固定长度值，是杂凑算法的基础原理，MD5的前身有MD2、MD3和MD4。
	 * MD5的作用是让大容量信息在用数字签名软件签署私人密钥前被"压缩"成一种保密的格式（就是把一个任意长度的字节串变换成一定长的十六进制数字串）
	 * </pre>
	 *
	 * @param bytes 需要签名的字节数组
	 * @return md5加密串
	 */
	public static byte[] md5(byte[] bytes) {
		if (bytes == null || bytes.length == 0) {
			return new byte[0];
		}
		// 直接是apache的库
		return DigestUtils.md5(bytes);
	}

	/**
	 * SHA加密
	 * <p/>
	 * <pre>
	 * SHA是一种数据加密算法，该算法经过加密专家多年来的发展和改进已日益完善，现在已成为公认的最安全的散列算法之一，并被广泛使用。
	 * 该算法的思想是接收一段明文，然后以一种不可逆的方式将它转换成一段（通常更小）密文，也可以简单的理解为取一串输入码（称为预映射或信息），
	 * 并把它们转化为长度较短、位数固定的输出序列即散列值（也称为信息摘要或信息认证代码）的过程。
	 * 散列函数值可以说时对明文的一种“指纹”或是“摘要”所以对散列值的数字签名就可以视为对此明文的数字签名。
	 * </pre>
	 *
	 * @param str 需要签名的字节数组
	 * @return sha加密串
	 */
	public static String shaHex(String str) {
		if (StringUtils.isBlank(str)) {
			return StringConst.EMPTY;
		}
		// 直接是apache的库
		return DigestUtils.sha1Hex(str);
	}

	/**
	 * SHA加密
	 * <p/>
	 * <pre>
	 * SHA是一种数据加密算法，该算法经过加密专家多年来的发展和改进已日益完善，现在已成为公认的最安全的散列算法之一，并被广泛使用。
	 * 该算法的思想是接收一段明文，然后以一种不可逆的方式将它转换成一段（通常更小）密文，也可以简单的理解为取一串输入码（称为预映射或信息），
	 * 并把它们转化为长度较短、位数固定的输出序列即散列值（也称为信息摘要或信息认证代码）的过程。
	 * 散列函数值可以说时对明文的一种“指纹”或是“摘要”所以对散列值的数字签名就可以视为对此明文的数字签名。
	 * </pre>
	 *
	 * @param bytes 需要签名的字节数组
	 * @return sha加密串
	 */
	public static String shaHex(byte[] bytes) {
		if (bytes == null || bytes.length == 0) {
			return StringConst.EMPTY;
		}
		// 直接是apache的库
		return DigestUtils.sha1Hex(bytes);
	}

	/**
	 * SHA加密
	 * <p/>
	 * <pre>
	 * SHA是一种数据加密算法，该算法经过加密专家多年来的发展和改进已日益完善，现在已成为公认的最安全的散列算法之一，并被广泛使用。
	 * 该算法的思想是接收一段明文，然后以一种不可逆的方式将它转换成一段（通常更小）密文，也可以简单的理解为取一串输入码（称为预映射或信息），
	 * 并把它们转化为长度较短、位数固定的输出序列即散列值（也称为信息摘要或信息认证代码）的过程。
	 * 散列函数值可以说时对明文的一种“指纹”或是“摘要”所以对散列值的数字签名就可以视为对此明文的数字签名。
	 * </pre>
	 *
	 * @param str 需要签名的字节数组
	 * @return sha加密串
	 */
	public static byte[] sha(String str) {
		if (StringUtils.isBlank(str)) {
			return new byte[0];
		}
		// 直接是apache的库
		return DigestUtils.sha1(str);
	}

	/**
	 * SHA加密
	 * <p/>
	 * <pre>
	 * SHA是一种数据加密算法，该算法经过加密专家多年来的发展和改进已日益完善，现在已成为公认的最安全的散列算法之一，并被广泛使用。
	 * 该算法的思想是接收一段明文，然后以一种不可逆的方式将它转换成一段（通常更小）密文，也可以简单的理解为取一串输入码（称为预映射或信息），
	 * 并把它们转化为长度较短、位数固定的输出序列即散列值（也称为信息摘要或信息认证代码）的过程。
	 * 散列函数值可以说时对明文的一种“指纹”或是“摘要”所以对散列值的数字签名就可以视为对此明文的数字签名。
	 * </pre>
	 *
	 * @param bytes 需要签名的字节数组
	 * @return sha加密串
	 */
	public static byte[] sha(byte[] bytes) {
		if (bytes == null || bytes.length == 0) {
			return new byte[0];
		}
		// 直接是apache的库
		return DigestUtils.sha1(bytes);
	}

	/**
	 * 加密key
	 *
	 * @param algorithm 算法:支持AES，ARCFOUR，Blowfish，DES，DESede，Hmac系列，RC2。
	 *                  详见http://docs.oracle.com/javase /6/docs/technotes/guides/
	 *                  security/SunProviders.html
	 * @param seed      种子，增加随机性，更为安全
	 * @return hmac加密key
	 */
	public static Key key(String algorithm, String seed) {
		if (StringUtils.isBlank(algorithm)) {
			return null;
		}
		if (StringUtils.isBlank(seed)) {
			seed = algorithm;
		}
		try {
			KeyGenerator keyGenerator = KeyGenerator.getInstance(algorithm);
			keyGenerator.init(new SecureRandom(base64Encode(seed)));
			return keyGenerator.generateKey();
		} catch (Exception e) {
			return null;
		}
	}

	/**
	 * 将安全密钥key转换为字符串形式
	 *
	 * @param key 安全密钥
	 * @return 字符串密钥
	 */
	public static String key2str(Key key) {
		if (key != null) {
			byte[] bytes = key.getEncoded();
			if (bytes != null && bytes.length > 0) {
				return base64EncodeStr(bytes);
			}
		}
		return StringConst.EMPTY;
	}

	/**
	 * 将字符串安全密钥key转换为Key对象
	 *
	 * @param key       字符串安全密钥
	 * @param algorithm 使用的算法
	 * @return 字符串密钥
	 */

	public static Key str2key(String key, String algorithm) {
		if (StringUtils.isBlank(key) || StringUtils.isBlank(algorithm)) {
			return null;
		}
		return new SecretKeySpec(base64Decode(key), algorithm);
	}

	/**
	 * HMAC加密
	 * <p/>
	 * <pre>
	 * HMAC(Hash Message Authentication Code，散列消息鉴别码)基于密钥的Hash算法的认证协议。
	 * 消息鉴别码实现鉴别的原理是，用公开函数和密钥产生一个固定长度的值作为认证标识，用这个标识鉴别消息的完整性。
	 * 使用一个密钥生成一个固定大小的小数据块，即MAC，并将其加入到消息中，然后传输。接收方利用与发送方共享的密钥进行鉴别认证等。
	 * </pre>
	 *
	 * @param str 需要签名的字节数组
	 * @param key 密钥
	 * @return hmac加密串
	 */
	public static String hmacHex(String str, Key key) {
		return hmacHex(str.getBytes(), key);
	}

	/**
	 * HMAC加密
	 * <p/>
	 * <pre>
	 * HMAC(Hash Message Authentication Code，散列消息鉴别码)基于密钥的Hash算法的认证协议。
	 * 消息鉴别码实现鉴别的原理是，用公开函数和密钥产生一个固定长度的值作为认证标识，用这个标识鉴别消息的完整性。
	 * 使用一个密钥生成一个固定大小的小数据块，即MAC，并将其加入到消息中，然后传输。接收方利用与发送方共享的密钥进行鉴别认证等。
	 * </pre>
	 *
	 * @param bytes 需要签名的字节数组
	 * @param key   密钥
	 * @return hmac加密串
	 */
	public static String hmacHex(byte[] bytes, Key key) {
		return new String(Hex.encodeHex(hmac(bytes, key)));
	}

	/**
	 * HMAC加密
	 * <p/>
	 * <pre>
	 * HMAC(Hash Message Authentication Code，散列消息鉴别码)基于密钥的Hash算法的认证协议。
	 * 消息鉴别码实现鉴别的原理是，用公开函数和密钥产生一个固定长度的值作为认证标识，用这个标识鉴别消息的完整性。
	 * 使用一个密钥生成一个固定大小的小数据块，即MAC，并将其加入到消息中，然后传输。接收方利用与发送方共享的密钥进行鉴别认证等。
	 * </pre>
	 *
	 * @param str 需要签名的字节数组
	 * @param key 密钥
	 * @return hmac加密串
	 */
	public static byte[] hmac(String str, Key key) {
		return hmac(str.getBytes(), key);
	}

	/**
	 * HMAC加密：默认使用的是HmacMD5算法
	 * <p/>
	 * <pre>
	 * HMAC(Hash Message Authentication Code，散列消息鉴别码)基于密钥的Hash算法的认证协议。
	 * 消息鉴别码实现鉴别的原理是，用公开函数和密钥产生一个固定长度的值作为认证标识，用这个标识鉴别消息的完整性。
	 * 使用一个密钥生成一个固定大小的小数据块，即MAC，并将其加入到消息中，然后传输。接收方利用与发送方共享的密钥进行鉴别认证等。
	 * </pre>
	 *
	 * @param bytes 需要签名的字节数组
	 * @param key   密钥
	 * @return hmac加密串
	 */
	public static byte[] hmac(byte[] bytes, Key key) {
		if (bytes == null || bytes.length == 0) {
			return new byte[0];
		}
		try {
			Mac mac = Mac.getInstance(key.getAlgorithm());
			mac.init(key);
			return mac.doFinal(bytes);
		} catch (Exception e) {
			return new byte[0];
		}
	}

	/**
	 * 按BASE64编码字符串
	 * <p/>
	 * <pre>
	 * Base64是一种基于64个可打印字符来表示二进制数据的表示方法。由于2的6次方等于64，所以每6个位元为一个单元，对应某个可打印字符。
	 * 三个字节有24个位元，对应于4个Base64单元，即3个字节需要用4个可打印字符来表示。它可用来作为电子邮件的传输编码。
	 * 在Base64中的可打印字符包括字母A-Z、a-z、数字0-9 ，这样共有62个字符，此外两个可打印符号在不同的系统中而不同。
	 * Base64常用于在通常处理文本数据的场合，表示、传输、存储一些二进制数据。包括MIME的email，email via MIME, 在XML中存储复杂数据。
	 * </pre>
	 *
	 * @param str 字符串
	 * @return BASE64编码字符串
	 */
	public static String base64EncodeStr(String str) {
		return base64EncodeStr(str.getBytes());
	}

	/**
	 * 按BASE64编码字节数组
	 * <p/>
	 * <pre>
	 * Base64是一种基于64个可打印字符来表示二进制数据的表示方法。由于2的6次方等于64，所以每6个位元为一个单元，对应某个可打印字符。
	 * 三个字节有24个位元，对应于4个Base64单元，即3个字节需要用4个可打印字符来表示。它可用来作为电子邮件的传输编码。
	 * 在Base64中的可打印字符包括字母A-Z、a-z、数字0-9 ，这样共有62个字符，此外两个可打印符号在不同的系统中而不同。
	 * Base64常用于在通常处理文本数据的场合，表示、传输、存储一些二进制数据。包括MIME的email，email via MIME, 在XML中存储复杂数据。
	 * </pre>
	 *
	 * @param bytes 字节数组
	 * @return BASE64编码字符串
	 */
	public static String base64EncodeStr(byte[] bytes) {
		return new String(base64Encode(bytes));
	}

	/**
	 * 按BASE64编码字符串
	 * <p/>
	 * <pre>
	 * Base64是一种基于64个可打印字符来表示二进制数据的表示方法。由于2的6次方等于64，所以每6个位元为一个单元，对应某个可打印字符。
	 * 三个字节有24个位元，对应于4个Base64单元，即3个字节需要用4个可打印字符来表示。它可用来作为电子邮件的传输编码。
	 * 在Base64中的可打印字符包括字母A-Z、a-z、数字0-9 ，这样共有62个字符，此外两个可打印符号在不同的系统中而不同。
	 * Base64常用于在通常处理文本数据的场合，表示、传输、存储一些二进制数据。包括MIME的email，email via MIME, 在XML中存储复杂数据。
	 * </pre>
	 *
	 * @param str 字符串
	 * @return BASE64编码字节数组
	 */
	public static byte[] base64Encode(String str) {
		return base64Encode(str.getBytes());
	}

	/**
	 * 按BASE64编码字节数组
	 * <p/>
	 * <pre>
	 * Base64是一种基于64个可打印字符来表示二进制数据的表示方法。由于2的6次方等于64，所以每6个位元为一个单元，对应某个可打印字符。
	 * 三个字节有24个位元，对应于4个Base64单元，即3个字节需要用4个可打印字符来表示。它可用来作为电子邮件的传输编码。
	 * 在Base64中的可打印字符包括字母A-Z、a-z、数字0-9 ，这样共有62个字符，此外两个可打印符号在不同的系统中而不同。
	 * Base64常用于在通常处理文本数据的场合，表示、传输、存储一些二进制数据。包括MIME的email，email via MIME, 在XML中存储复杂数据。
	 * </pre>
	 *
	 * @param bytes 字节数组
	 * @return BASE64编码字节数组
	 */
	public static byte[] base64Encode(byte[] bytes) {
		if (bytes == null) {
			return new byte[0];
		}
		return Base64.encodeBase64(bytes);
	}

	/**
	 * 按BASE64编码字符串
	 * <p/>
	 * <pre>
	 * Base64是一种基于64个可打印字符来表示二进制数据的表示方法。由于2的6次方等于64，所以每6个位元为一个单元，对应某个可打印字符。
	 * 三个字节有24个位元，对应于4个Base64单元，即3个字节需要用4个可打印字符来表示。它可用来作为电子邮件的传输编码。
	 * 在Base64中的可打印字符包括字母A-Z、a-z、数字0-9 ，这样共有62个字符，此外两个可打印符号在不同的系统中而不同。
	 * Base64常用于在通常处理文本数据的场合，表示、传输、存储一些二进制数据。包括MIME的email，email via MIME, 在XML中存储复杂数据。
	 * </pre>
	 *
	 * @param str 字符串
	 * @return BASE64编码字符串
	 */
	public static String base64DecodeStr(String str) {
		return base64DecodeStr(str.getBytes());
	}

	/**
	 * 按BASE64编码字节数组
	 * <p/>
	 * <pre>
	 * Base64是一种基于64个可打印字符来表示二进制数据的表示方法。由于2的6次方等于64，所以每6个位元为一个单元，对应某个可打印字符。
	 * 三个字节有24个位元，对应于4个Base64单元，即3个字节需要用4个可打印字符来表示。它可用来作为电子邮件的传输编码。
	 * 在Base64中的可打印字符包括字母A-Z、a-z、数字0-9 ，这样共有62个字符，此外两个可打印符号在不同的系统中而不同。
	 * Base64常用于在通常处理文本数据的场合，表示、传输、存储一些二进制数据。包括MIME的email，email via MIME, 在XML中存储复杂数据。
	 * </pre>
	 *
	 * @param bytes 字节数组
	 * @return BASE64编码字符串
	 */
	public static String base64DecodeStr(byte[] bytes) {
		return new String(base64Decode(bytes));
	}

	/**
	 * 按BASE64编码字符串
	 * <p/>
	 * <pre>
	 * Base64是一种基于64个可打印字符来表示二进制数据的表示方法。由于2的6次方等于64，所以每6个位元为一个单元，对应某个可打印字符。
	 * 三个字节有24个位元，对应于4个Base64单元，即3个字节需要用4个可打印字符来表示。它可用来作为电子邮件的传输编码。
	 * 在Base64中的可打印字符包括字母A-Z、a-z、数字0-9 ，这样共有62个字符，此外两个可打印符号在不同的系统中而不同。
	 * Base64常用于在通常处理文本数据的场合，表示、传输、存储一些二进制数据。包括MIME的email，email via MIME, 在XML中存储复杂数据。
	 * </pre>
	 *
	 * @param str 字符串
	 * @return BASE64编码字节数组
	 */
	public static byte[] base64Decode(String str) {
		return base64Decode(str.getBytes());
	}

	/**
	 * 按BASE64编码字节数组
	 * <p/>
	 * <pre>
	 * Base64是一种基于64个可打印字符来表示二进制数据的表示方法。由于2的6次方等于64，所以每6个位元为一个单元，对应某个可打印字符。
	 * 三个字节有24个位元，对应于4个Base64单元，即3个字节需要用4个可打印字符来表示。它可用来作为电子邮件的传输编码。
	 * 在Base64中的可打印字符包括字母A-Z、a-z、数字0-9 ，这样共有62个字符，此外两个可打印符号在不同的系统中而不同。
	 * Base64常用于在通常处理文本数据的场合，表示、传输、存储一些二进制数据。包括MIME的email，email via MIME, 在XML中存储复杂数据。
	 * </pre>
	 *
	 * @param bytes 字节数组
	 * @return BASE64编码字节数组
	 */
	public static byte[] base64Decode(byte[] bytes) {
		if (bytes == null) {
			return new byte[0];
		}
		return Base64.decodeBase64(bytes);
	}

	/**
	 * 加密算法：支持AES，ARCFOUR，Blowfish，DES，DESede，Hmac系列，RC2，RSA，PBE系列。
	 *
	 * @param str 待加密的字符串
	 * @param key 算法key
	 * @return 加密字节数组
	 */
	public static String encryptStr(String str, Key key) {
		return encryptStr(str.getBytes(), key);
	}

	/**
	 * 加密算法：支持AES，ARCFOUR，Blowfish，DES，DESede，Hmac系列，RC2，RSA，PBE系列。
	 *
	 * @param bytes 待加密的字节数组
	 * @param key   算法key
	 * @return 加密字节数组
	 */
	public static String encryptStr(byte[] bytes, Key key) {
		byte[] result = encrypt(bytes, key);
		if (result != null) {
			return base64EncodeStr(result);
		}
		return StringConst.EMPTY;
	}

	/**
	 * 加密算法：支持AES，ARCFOUR，Blowfish，DES，DESede，Hmac系列，RC2，RSA，PBE系列。
	 *
	 * @param str 待加密的字符串
	 * @param key 算法key
	 * @return 加密字节数组
	 */
	public static byte[] encrypt(String str, Key key) {
		return encrypt(str.getBytes(), key);
	}

	/**
	 * 加密算法：支持
	 *
	 * @param bytes 待加密的字节数组
	 * @param key   算法key
	 * @return 加密字节数组
	 */
	public static byte[] encrypt(byte[] bytes, Key key) {
		return cipher(bytes, Cipher.ENCRYPT_MODE, key);
	}

	/**
	 * 解密算法：支持AES，ARCFOUR，Blowfish，DES，DESede，Hmac系列，RC2，RSA，PBE系列。
	 *
	 * @param str 待解密的字符串
	 * @param key 算法key
	 * @return 解密字节数组
	 */
	public static String decryptStr(String str, Key key) {
		return decryptStr(base64Decode(str), key);
	}

	/**
	 * 解密算法：支持AES，ARCFOUR，Blowfish，DES，DESede，Hmac系列，RC2，RSA，PBE系列。
	 *
	 * @param bytes 待解密的字节数组
	 * @param key   算法key
	 * @return 解密字节数组
	 */
	public static String decryptStr(byte[] bytes, Key key) {
		byte[] result = decrypt(bytes, key);
		if (result != null) {
			return new String(result);
		}
		return StringConst.EMPTY;
	}

	/**
	 * 解密算法：支持
	 *
	 * @param str 待解密的字符串
	 * @param key 算法key
	 * @return 解密字节数组
	 */
	public static byte[] decrypt(String str, Key key) {
		return decrypt(base64Decode(str), key);
	}

	/**
	 * 解密算法：支持AES，ARCFOUR，Blowfish，DES，DESede，Hmac系列，RC2，RSA，PBE系列。
	 *
	 * @param bytes 待解密的字节数组
	 * @param key   算法key
	 * @return 解密字节数组
	 */
	public static byte[] decrypt(byte[] bytes, Key key) {
		return cipher(bytes, Cipher.DECRYPT_MODE, key);
	}

	/**
	 * 加密解密算法：支持AES，ARCFOUR，Blowfish，DES，DESede，Hmac系列，RC2，RSA，PBE系列。
	 *
	 * @param bytes 待加密或解密的字节数组
	 * @param mode  加密解密模式
	 * @param key   算法key
	 * @return 加密或解密字节数组
	 */
	public static byte[] cipher(byte[] bytes, int mode, Key key) {
		try {
			if (bytes != null && bytes.length > 0 && key != null) {
				Cipher cipher = Cipher.getInstance(key.getAlgorithm());
				cipher.init(mode, key);
				return cipher.doFinal(bytes);
			}
		} catch (Exception e) {
			return null;
		}
		return null;
	}
}
