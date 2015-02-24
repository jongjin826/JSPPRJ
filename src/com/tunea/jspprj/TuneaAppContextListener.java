package com.tunea.jspprj;

import javax.servlet.ServletContext;
import javax.servlet.ServletContextAttributeEvent;
import javax.servlet.ServletContextAttributeListener;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

public class TuneaAppContextListener implements ServletContextListener, ServletContextAttributeListener {

	@Override
	public void contextDestroyed(ServletContextEvent arg0) {
		System.out.println("어플리케이션 종료됨");		
	}

	@Override
	public void contextInitialized(ServletContextEvent arg0) {
		ServletContext ctx = arg0.getServletContext();
		System.out.println("어플리케이션 초기화 됨");		
	}

	@Override
	public void attributeAdded(ServletContextAttributeEvent arg0) {
		System.out.println("어플리케이션 속성 "+arg0.getName() + "이 값 "+arg0.getValue()+"으로 추가됨");
	}

	@Override
	public void attributeRemoved(ServletContextAttributeEvent arg0) {

		System.out.println("어플리케이션 속성 "+arg0.getName() + "이 값 "+arg0.getValue()+"으로 제거됨");
	}

	@Override
	public void attributeReplaced(ServletContextAttributeEvent arg0) {

		System.out.println("어플리케이션 속성 "+arg0.getName() + "이 값 "+arg0.getValue()+"으로 변경됨");
	}

}
