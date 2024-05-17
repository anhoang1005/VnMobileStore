package com.example.ZVnMobile.utils;

import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;

import org.springframework.stereotype.Component;

@Component
public class ValidateUtils {
	public Date convertStringToDate(String dateString) {
		LocalDate localDate = LocalDate.parse(dateString);
		return Date.from(localDate.atStartOfDay(ZoneId.systemDefault()).toInstant());
	}
}
