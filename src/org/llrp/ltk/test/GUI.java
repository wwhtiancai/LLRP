package org.llrp.ltk.test;

/*
 *wuweihong
 *2016-6-24
 */
import java.awt.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

public class GUI {
	private Frame f;
	private Button bn, bs, bw, be, bc;

	public static void main(String args[]) {
		GUI guiWindow2 = new GUI();
		guiWindow2.go();
	}

	public void go() {
		f = new Frame("Border Layout");
		bn = new Button("B1");
		bs = new Button("B2");
		be = new Button("B3");
		bw = new Button("B4");
		bc = new Button("B5");
		f.add(bn, BorderLayout.NORTH);
		f.addWindowListener(new MyWin());
		f.add(bs, BorderLayout.SOUTH);
		f.add(be, BorderLayout.EAST);
		f.add(bw, BorderLayout.WEST);
		f.add(bc, BorderLayout.CENTER);
		f.setSize(200, 200);
		f.setVisible(true);
	}

	class MyWin extends WindowAdapter {

		@Override
		public void windowClosing(WindowEvent e) {
			// TODO Auto-generated method stub
			// System.out.println("Window closing"+e.toString());
			System.out.println("ฮานุมห");
			System.exit(0);
		}
	}
}
