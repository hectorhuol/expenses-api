package com.financial.analisys.expenses.api.utils;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class FinancialUtils {
	private static final String DATE_PATTERN="yyyy-MM-dd HH:mm";
	private static DateTimeFormatter formatter = DateTimeFormatter.ofPattern(DATE_PATTERN);
	
	public static LocalDateTime getLocalDateTime(String date){
		return LocalDateTime.parse(date, formatter);
	}
	
	public static String getLocalDateTimeString(LocalDateTime date){
		return date.format(formatter);
	}

}
