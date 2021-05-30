package com.Utilities;

import java.util.Random;

public class RandomClass {
	
	
	Random random = new Random();
	int rno = random.nextInt(2000);
	
	
	public  String RandomEmail() {
		String randomNo = "Raja" + rno;
		return randomNo; 
	}

}
