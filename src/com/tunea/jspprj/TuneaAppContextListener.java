package com.tunea.jspprj;

import javax.servlet.ServletContext;
import javax.servlet.ServletContextAttributeEvent;
import javax.servlet.ServletContextAttributeListener;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

public class TuneaAppContextListener implements ServletContextListener, ServletContextAttributeListener {

	@Override
	public void contextDestroyed(ServletContextEvent arg0) {
		System.out.println("���ø����̼� �����");		
	}

	@Override
	public void contextInitialized(ServletContextEvent arg0) {
		ServletContext ctx = arg0.getServletContext();
		System.out.println("���ø����̼� �ʱ�ȭ ��");		
	}

	@Override
	public void attributeAdded(ServletContextAttributeEvent arg0) {
		System.out.println("���ø����̼� �Ӽ� "+arg0.getName() + "�� �� "+arg0.getValue()+"���� �߰���");
	}

	@Override
	public void attributeRemoved(ServletContextAttributeEvent arg0) {

		System.out.println("���ø����̼� �Ӽ� "+arg0.getName() + "�� �� "+arg0.getValue()+"���� ���ŵ�");
	}

	@Override
	public void attributeReplaced(ServletContextAttributeEvent arg0) {

		System.out.println("���ø����̼� �Ӽ� "+arg0.getName() + "�� �� "+arg0.getValue()+"���� �����");
	}

}
