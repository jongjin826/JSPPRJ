package com.tunea.jspprj;

import javax.servlet.ServletRequestEvent;
import javax.servlet.ServletRequestListener;

public class TuneaRequestListener implements ServletRequestListener {

	@Override
	public void requestDestroyed(ServletRequestEvent arg0) {
		System.out.println("요청종료 Remote IP =" +  arg0.getServletRequest().getRemoteAddr());
	}

	@Override
	public void requestInitialized(ServletRequestEvent arg0) {
		System.out.println("요청 초기화 Remote IP =" +  arg0.getServletRequest().getRemoteAddr());
	}

}
