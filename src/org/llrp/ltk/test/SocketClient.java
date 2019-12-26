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
			// �ͻ���socketָ���������ĵ�ַ�Ͷ˿ں�
			socket = new Socket(cache.socketip, cache.socketport);
			System.out.println("Socket=" + socket);
			// ͬ������ԭ��һ��
			/*
			 * br = new BufferedReader(new InputStreamReader(
			 * socket.getInputStream()));
			 */

			pw = new PrintWriter(new BufferedWriter(new OutputStreamWriter(socket.getOutputStream())));

			pw.println("NET_DVR_ContinuousShoot");
			pw.flush();

			// ����һ�����׽��ֲ��������ӵ�ָ�������ϵ�ָ���˿ں�
			// ��ȡ������������
			input = new DataInputStream(socket.getInputStream());
			byte[] buffer;
			buffer = new byte[input.available()];
			if (buffer.length != 0) {
				// ��ȡ������
				input.read(buffer);
				// ת���ַ���
				String three = new String(buffer);
				System.out.println("����=" + three);
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
