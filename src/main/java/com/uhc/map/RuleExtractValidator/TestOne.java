package com.uhc.map.RuleExtractValidator;

public class TestOne {

	public TestOne() {
		// TODO Auto-generated constructor stub
	}

	public static void main(String args[])
	{
		boolean arr[] = {true, false , true, false, false, false};
		int n =arr.length;
		System.out.println(getMaxlength(arr, n));
	}
	
	static int getMaxlength(boolean arr[], int n) {
		int count = 1;
		int result = 0;
		for (int i = 0; i < n; i++) {
			if (arr[i] == false)
				count = 0;
			else {
				count++;

				result = Math.max(count, result);
			}
		}

		return result;
	}
	

}
