package org.llrp.ltk.net;

import java.net.SocketAddress;

import org.apache.mina.common.IoHandler;
import org.apache.mina.common.IoService;
import org.apache.mina.common.IoServiceConfig;
import org.apache.mina.common.IoServiceListener;
import org.apache.mina.common.IoSession;


public class IoListener implements IoServiceListener{

	@Override
	public void serviceActivated(IoService arg0, SocketAddress arg1, IoHandler arg2, IoServiceConfig arg3) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void serviceDeactivated(IoService arg0, SocketAddress arg1, IoHandler arg2, IoServiceConfig arg3) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void sessionCreated(IoSession arg0) {
		// TODO Auto-generated method stub
	}

	@Override
	public void sessionDestroyed(IoSession arg0) {
		// TODO Auto-generated method stub
		
	}  
}