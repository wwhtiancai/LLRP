package org.llrp.ltk.test;

import java.io.BufferedWriter;
import java.io.DataInputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.Socket;

import org.llrp.ltk.net.SystemCache;

public class SocketClient {

	public static void getSocket() {
		Socket socket = null;
		DataInputStream input = null;
		PrintWriter pw = null;
		SystemCache cache = new SystemCache();
		try {
			// 客户端socket指定服务器的地址和端口号
			socket = new Socket(cache.socketip, cache.socketport);
			System.out.println("Socket=" + socket);
			// 同服务器原理一样
			/*
			 * br = new BufferedReader(new InputStreamReader(
			 * socket.getInputStream()));
			 */

			pw = new PrintWriter(new BufferedWriter(new OutputStreamWriter(socket.getOutputStream())));

			pw.println("NET_DVR_ContinuousShoot");
			pw.flush();

			// 创建一个流套接字并将其连接到指定主机上的指定端口号
			// 读取服务器端数据
			input = new DataInputStream(socket.getInputStream());
			byte[] buffer;
			buffer = new byte[input.available()];
			if (buffer.length != 0) {
				// 读取缓冲区
				input.read(buffer);
				// 转换字符串
				String three = new String(buffer);
				System.out.println("内容=" + three);
			}

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				System.out.println("close......");
				input.close();
				pw.close();
				socket.close();
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
}
