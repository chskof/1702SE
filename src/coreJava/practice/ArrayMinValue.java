package coreJava.practice;

import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.UIManager;
import javax.swing.border.EmptyBorder;

/**Example043
 * 获取一维数组最小值
 * */
public class ArrayMinValue extends JFrame {
	private static final long serialVersionUID = 1L;
	
	private JPanel pane;    //定义窗口面板
	private JTextField text_in; //定义输入框  单行文本
	private JLabel label_out;     //定义结果显示区 (标签)
	private JLabel label_tip;  //定义操作提示区(标签)
	
	public static void main(String[] args) {
		try {
			UIManager.setLookAndFeel("com.sun.java.swing.plaf.nimbus.NimbusLookAndFeel");
		}catch (Throwable e) {
			e.printStackTrace();
		}
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					ArrayMinValue frame = new ArrayMinValue();
					frame.setVisible(true);
				}catch(Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
	
	public ArrayMinValue() {
		//设置窗体的标题
		this.setTitle("获取一维数组最小值");   //窗口标题
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);  //关闭退出 设置用户在此窗体上发起 "X" 时默认执行的操作
		
		this.setBounds(500,300,450,170);  //设置窗口在屏幕上初始的坐标位置  长度及宽度
		
		pane = new JPanel();  //创建窗口面板
		pane.setBorder(new EmptyBorder(5,5,5,5));  // 边框设置
		
		this.setContentPane(pane);  //将窗口容器(面板)放入此对象
		pane.setLayout(null);  //设置容器的布局管理
		
		text_in = new JTextField(); //创建输入框
		text_in.setBounds(6, 36, 414, 30); // 设置文本输入框的坐标及长高
		pane.add(text_in);  // 将输入框放入面板中
		text_in.setColumns(10);  //设置此输入框的列数
		
		JButton button_calcul = new JButton("计算"); //定义并创建一个按钮
		button_calcul.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {  //内部类 监听
				 calculate(e);  //调用计算的方法
			}
		});
		button_calcul.setBounds(16,76,90,30);  //设置按钮的坐标及大小
		pane.add(button_calcul);   //将此按钮添加到面板中
		
		label_out = new JLabel("最小值");  //创建计算结果的文字区(标签)
		label_out.setBounds(116,82,304,18);  //结果的文字区(标签)的坐标及大小
		pane.add(label_out);  //将此标签添加到面板中
		
		label_tip =  new JLabel("请在文本框中输入多个数字参数，以空格分开:"); //创建操作提示的文字区域标签
		label_tip.setBounds(6,6,422,18);  //文字区(标签)的坐标及大小
		pane.add(label_tip);  //将此标签添加到面板中
	}
	
	protected void calculate(ActionEvent e) {
		String arrayStr = text_in.getText().trim(); //得到文本输入框中的字符串 
		for(int i =0 ;i<arrayStr.length();i++) {
			char charAt = arrayStr.charAt(i);
			if(!Character.isDigit(charAt) && charAt != ' ') {
				JOptionPane.showMessageDialog(null, "输入包含非数字内容");   //弹窗
				text_in.setText("");   //将此标签添加到面板中
				return;
			}
		}
		
		String[] numStrs = arrayStr.split(" {1,}");  //正则表达式 分割字符串
		int[] numArray = new int[numStrs.length];
		for(int i = 0;i<numArray.length;i++) {
			numArray[i] = Integer.valueOf(numStrs[i]);
		}
		int min = numArray[0];
		for(int j= 0;j<numArray.length;j++) {
			if(min > numArray[j]) {
				min = numArray[j];
			}
		}
		label_out.setText("数组中最小的数是："+min);  //设置文本输入框的文字
	}
}
