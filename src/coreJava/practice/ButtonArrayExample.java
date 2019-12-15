package coreJava.practice;

import java.awt.BorderLayout;

import javax.swing.JFrame;
import javax.swing.UIManager;

public class ButtonArrayExample extends JFrame {

	public static void main(String[] args) {
		try {
			UIManager.setLookAndFeel("com.sun.java.swing.plaf.nimbus.nimbusLookAndFeel");
		}catch(Throwable e) {
			e.printStackTrace();
		}
		
		ButtonArrayExample frame = new ButtonArrayExample();
		frame.setVisible(true);
	}
	
	public ButtonArrayExample() {
		super();
		BorderLayout borderLayout = (BorderLayout) getContentPane().getLayout();
		borderLayout.setHgap(20);
		borderLayout.setVgap(10);
		setTitle("按钮数组实现计算器界面");
		setBounds(100,100,290,282);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
	}
}
