package com.test.springboot.test;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Test1 {

	public static void main(String[] args) {
		Pattern pattern=Pattern.compile("/asd");
		Matcher matcher=pattern.matcher("/asd");
		System.out.println(matcher.matches());
	}
}
