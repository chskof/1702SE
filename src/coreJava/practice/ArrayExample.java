package coreJava.practice;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.Random;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.UIManager;
import javax.swing.border.EmptyBorder;
import javax.swing.border.TitledBorder;
/***
 * Example044  抽奖
 * @author chs
 * @version 20171028
 */
public class ArrayExample extends JFrame {
	private static final long serialVersionUID = 1L;
	
	private JPanel mainPane;
	private JTextField nameField;
	private JTextArea personnelArea;
	private JTextArea resultArea;
	
	public static  void main(String[] args) {
		try {
			/*Nimbus 为其应用程序提供了一个完美的界面。
                              而且 Nimbus 完全使用 Java 2D 矢量图型而不是静态位图，所以非常小（只有56KB!）*/
			UIManager.setLookAndFeel("com.sun.java.swing.plaf.nimbus.NimbusLookAndFeel");
		} catch (Exception e) {
			e.printStackTrace();
		}
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					ArrayExample frame = new ArrayExample();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
	
	public ArrayExample() {
		setTitle("利用数组随机抽取幸运观众");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 520, 300);
		mainPane = new JPanel();
		mainPane.setBorder(new EmptyBorder(5,5,5,5));
		setContentPane(mainPane);
		mainPane.setLayout(null);
		
		JPanel inputPanel = new JPanel();
		inputPanel.setBorder(new TitledBorder(null,
				 "输入姓名按回车键添加：",
                 TitledBorder.LEADING, TitledBorder.TOP, null,
                 new Color(59,59,59)));
		inputPanel.setBounds(10, 10, 200, 242);
		mainPane.add(inputPanel);
		inputPanel.setLayout(new BorderLayout(0,5));
		nameField = new JTextField();
		nameField.addKeyListener(new KeyAdapter() {
			public void keyPressed(KeyEvent e) {
				do_textField_keyPressed(e);
			}
		});
		inputPanel.add(nameField,BorderLayout.NORTH);
		nameField.setColumns(10);
		JScrollPane inputScrollPane = new JScrollPane();
		inputPanel.add(inputScrollPane);
		personnelArea = new JTextArea();
		personnelArea.setEditable(false);
		inputScrollPane.setViewportView(personnelArea);
		JPanel outPanel  = new JPanel();
		outPanel.setBorder(new TitledBorder(null,
                 "选取观众人员：",
                        TitledBorder.LEADING, TitledBorder.TOP, null, new Color(59, 59,
                        59)));
		outPanel.setBounds(210, 10, 219, 242);
		mainPane.add(outPanel);
		outPanel.setLayout(new BorderLayout(0,0));
		JScrollPane outScrollPane = new JScrollPane();
		outPanel.add(outScrollPane);
		resultArea  = new JTextArea();
		resultArea.setEditable(false);
		resultArea.setLineWrap(true);
		outScrollPane.setViewportView(resultArea);
		
		JButton button_draw = new JButton("抽取");
		button_draw.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				button_submit_conlick(e);
			}
		});
		button_draw.setBounds(430, 164, 63, 25);
		mainPane.add(button_draw);
		
	    JButton button_quit = new JButton("退出");
	    button_quit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				button_quit_conlick(e);  
			}
	    });
	    button_quit.setBounds(430, 215, 63, 25);
	    mainPane.add(button_quit);
	}
	
	protected void do_textField_keyPressed(KeyEvent e) {
		if(e.getKeyChar() != '\n') return ; // 不是回车字符不做处理
		String name =nameField.getText();
		if(name.isEmpty()) return; //如果文本框中没有字符串不做处理
		personnelArea.append(name+"\n"); //把输入人名与回车符添加发到员列表
		nameField.selectAll(); //选择文本框所有字符
	}
	
	/**抽取*/
	protected void button_submit_conlick(ActionEvent e) {   
		String perstring  = personnelArea.getText(); // 获取人员列表文本
		String[] personnelArray = perstring.split("\n{1,}");//获取人员数组
		int index =(int) (Math.random()*personnelArray.length); //生成随机数组索引
	/*	Random random = new Random();
		random.nextInt(2); */
		//定义包含格式参数的中奖信息
		String formatArg = "本次抽取观众人员：\n\t%1$s\n恭喜%1$s成为本次观众抽奖的大奖得主。"
                + "\n\n我们将为%1$s颁发：\n\t过期的酸奶二十箱。";
		//为中奖信息添加人员参数
		String info = String.format(formatArg, personnelArray[index]);
		resultArea.setText(info);  //在文本域显示中间信息
	}
	/**退出*/
	protected void button_quit_conlick(ActionEvent e) {
		dispose();  //释放由此 Window、其子组件及其拥有的所有子组件所使用的所有本机屏幕资源
	}
}
