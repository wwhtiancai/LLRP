package org.llrp.ltk.test;

import java.awt.Button;
import java.awt.Color;
import java.awt.Frame;
import java.awt.Label;
import java.awt.Panel;
import java.awt.TextField;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import org.llrp.ltk.test.GUI.MyWin;

public class demo implements ActionListener {
	Frame nw = new Frame("登录");
	Panel nn = new Panel();
	Label a1 = new Label("用户名");
	Label a2 = new Label("密码   ");
	TextField b1 = new TextField("name     ");
	TextField b2 = new TextField("password");
	Button n1 = new Button("确定");

	public void run() {
		nn.add(a1);
		nn.add(b1);
		nn.add(a2);
		b2.setEchoChar('*');
		n1.addActionListener(this);
		nn.add(b2);
		nn.add(n1);
		nw.add(nn);
		nw.setBackground(Color.cyan);
		nw.setSize(300, 300);
		nw.addWindowListener(new MyWin());
		nw.setVisible(true);
	}

	public static void main(String[] args) {
		demo e1 = new demo();
		e1.run();
	}

	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == n1) {
			b1.setText("12312313");
			// Frame nw1=new Frame("信息");
			// TextField b3=new TextField("功能尚未开放");
			// nw1.add(b3);
			// nw1.setSize(200,200);
			// nw1.setVisible(true);
		}
	}

	class MyWin extends WindowAdapter {

		@Override
		public void windowClosing(WindowEvent e) {
			// TODO Auto-generated method stub
			// System.out.println("Window closing"+e.toString());
			System.out.println("我关了");
			System.exit(0);
		}
	}
}
