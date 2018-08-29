package com.finix.utils;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class MobileRandomNum {


	public static String mobileRandomNum()
	{
		 List<Integer> numbers = new ArrayList<>();
		    for(int i = 0; i < 10; i++){
		        numbers.add(i);
		    }

		    Collections.shuffle(numbers);

		    String result = "";
		    for(int i = 0; i < 4; i++){
		        result += numbers.get(i).toString();
		    }
		    
		    return result;
	}

}
