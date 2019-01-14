package com.javarush.task.task24.task2401;

public class SelfInterfaceMarkerImpl implements SelfInterfaceMarker {
	public static int countOfPrint = 0;
	
	public void print(String s) {
		System.out.println(s);
		countOfPrint++;
	}
	
	public int getCountOfPrint() {
		return countOfPrint;
	}
}
