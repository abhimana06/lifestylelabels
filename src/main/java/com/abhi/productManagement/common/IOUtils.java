package com.abhi.productManagement.common;

import java.io.PrintWriter;
import java.io.StringWriter;

public class IOUtils {

	public static String getPrintStackTrace(Throwable e) {
		StringWriter sw = new StringWriter();
		PrintWriter pw = new PrintWriter(sw);
		e.printStackTrace(pw);
		return sw.toString();
	}
}
