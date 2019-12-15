package coreJava.Annotation;

import java.awt.EventQueue;

import javax.swing.JFrame;

public class ButtonTest {
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable(){
			public void run() {
				ButtonFrame frame = new ButtonFrame();
				frame.setTitle("按钮测试");
				frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
				frame.setVisible(true);
			}
		});
	}
}
