package com.dhr.util;

import java.util.UUID;

/**
 * 随机生成验证码
 * @author Mr DU
 *
 */
public class UUIDUtils {
	public static String getUUID() {
		String uuid = UUID.randomUUID().toString();
		return uuid = uuid.replaceAll("-", "").toLowerCase();
	}
}
