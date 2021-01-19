package com.eshop.retail.utils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.function.Predicate;

public class DiscountUtil {

	public static <T> T applyFilterOnCondition(Predicate<T> condition, List<T> factors) {
		return factors.stream().filter(f -> condition.test(f)).findAny().orElse(null);
	}

	public static long findDifference(String start_date, String end_date) {

		SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");

		try {

			Date d1 = sdf.parse(start_date);
			Date d2 = sdf.parse(end_date);
			long difference_In_Time = d2.getTime() - d1.getTime();
			return (difference_In_Time / (1000l * 60 * 60 * 24 * 365));
		}

		// Catch the Exception
		catch (ParseException e) {
			e.printStackTrace();
		}

		return 0;
	}
}
