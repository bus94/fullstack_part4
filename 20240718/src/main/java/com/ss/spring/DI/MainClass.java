package com.ss.spring.DI;

import org.springframework.context.support.AbstractApplicationContext;
import org.springframework.context.support.GenericXmlApplicationContext;

public class MainClass {
	public static void main(String[] args) {
		/*
		 * // 자바에서 사용하던 방식 MyCalculator myCalculator = new MyCalculator();
		 * myCalculator.setFirstNum(8); myCalculator.setSecondNum(5);
		 * 
		 * Calculator calculator = new Calculator();
		 * 
		 * // 의존성 주입! myCalculator.setCalculator(calculator);
		 * myCalculator.setCalculator(new Calculator());
		 */

		// xml 파일에서 설정한 bean을 얻어온다.
		// applicationCTX.xml이 위치한 경로
		// src/main/resources를 설정파일의 위치를 나타내는 문자열을 저장하는 변수이름
		// classpath 라고 부른다.
		String configLocation = "classpath:applicationCTX.xml";

		// GenericXmlApplicationContext
		// 클래스 생성자의 인수로 bean을 설정한 xml 파일의 경로와 이름을 넘겨주면 xml 파일을 읽어서 자바 객체로 변환한 후 부모 클래스인
		// AbstractApplicationContext
		// - bean 객체들을 관리하고 생명주기, 전달해야되는 메시지 소스를 처리하고 관리하는 인터페이스 객체다.
		AbstractApplicationContext ctx = new GenericXmlApplicationContext(configLocation);

		// getBean("얻어올 bean id",bean을 생성한 클래스 이름.class)
		MyCalculator myCalculator = ctx.getBean("myCalculator", MyCalculator.class);
		System.out.println(myCalculator);
		myCalculator.add();
		myCalculator.sub();

	}
}
