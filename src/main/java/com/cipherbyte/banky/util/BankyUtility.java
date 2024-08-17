package com.cipherbyte.banky.util;

import java.math.BigDecimal;
import java.math.RoundingMode;

public class BankyUtility {

	public static BigDecimal parseAmount(String amount)	{
		return new BigDecimal(amount).setScale(2, RoundingMode.HALF_UP);
	}
}
