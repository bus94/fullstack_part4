package com.ss.spring.DI;

// 사칙연산을 계산하는 단순한 클래스
public class Calculator {
	public void add(int firstNum, int secondNum) {
		System.out.println("add()");
		int result = firstNum + secondNum;
		System.out.println(firstNum + " + " + secondNum + " = " + result);
	}

	public void sub(int firstNum, int secondNum) {
		System.out.println("sub()");
		int result = firstNum - secondNum;
		System.out.println(firstNum + " - " + secondNum + " = " + result);
	}
}