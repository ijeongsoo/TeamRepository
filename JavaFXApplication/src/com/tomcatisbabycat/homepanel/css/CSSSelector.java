/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.tomcatisbabycat.homepanel.css;

import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

/**
 *
 * @author ijeongsu
 */
public class CSSSelector {

	private static String springCSS = "spring.css";
	private static String summerCSS = "summer.css";
	private static String fallCSS = "fall.css";
	private static String winterCSS = "winter.css";

	public static String getSeasonCSS() {
		Calendar cal = Calendar.getInstance(Locale.KOREA);
		int month = cal.get(Calendar.MONTH) + 1;
		if (month == 3 || month == 4 || month == 5) {
			return springCSS;
		} else if (month == 6 || month == 7 || month == 8) {
			return summerCSS;
		} else if (month == 9 || month == 10 || month == 11) {
			return fallCSS;
		} else {
			return winterCSS;
		}
	}

}
