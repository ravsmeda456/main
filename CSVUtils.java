package com.edyst.utils;

import java.text.SimpleDateFormat;
import java.util.Date;

public class CSVUtils {
	public static String getFileName() {
	    String[] array = new String[]{ "HH","mm","ss","dd","MM","yyyy" };
	    String file ="";
	    for (int i = 0; i < array.length; i++) {
		    Date date = new Date();  
		    SimpleDateFormat formatter = new SimpleDateFormat(array[i]);  
		    String strDate = formatter.format(date);  
		     file= file+strDate;
		     if(array[i]!="yyyy") {
		    	 file=file+"_";
		     }else {
		    	 file=file+"_Test_Log.csv";
		     }
	  
	    }
	    return file;
	    
	}
}
