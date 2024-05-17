package com.example.ZVnMobile.utils;

import org.springframework.stereotype.Component;

@Component
public class PageUtils {
	public long getPageCount(long pageTotal, int numberInPage) {
		long pageCount = 0;
		if (pageTotal == 0) {
			pageCount = 0;
		} else {
			if (pageTotal % numberInPage == 0) {
				pageCount = pageTotal / numberInPage;
			} else {
				pageCount = pageTotal / numberInPage + 1;
			}
		}
		return pageCount;
	}
}
