package com.tunea.jspprj;

import javax.servlet.ServletRequestEvent;
import javax.servlet.ServletRequestListener;

public class TuneaRequestListener implements ServletRequestListener {

	@Override
	public void requestDestroyed(ServletRequestEvent arg0) {
		System.out.println("��û���� Remote IP =" +  arg0.getServletRequest().getRemoteAddr());
	}

	@Override
	public void requestInitialized(ServletRequestEvent arg0) {
		System.out.println("��û �ʱ�ȭ Remote IP =" +  arg0.getServletRequest().getRemoteAddr());
	}

}
