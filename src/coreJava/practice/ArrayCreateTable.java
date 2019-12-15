package coreJava.practice;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.UIManager;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumn;
import javax.swing.table.TableColumnModel;

/**
 * Example045
 * @author chs
 * @version 20171119
 */
public class ArrayCreateTable extends JFrame {
	private static final long serialVersionUID = 1L;
	
	private JPanel mainPane;
	private JScrollPane scrollPane;
	private JTable table;
	
	public static void main(String[] args) {
		try {
			UIManager.setLookAndFeel("com.sun.java.swing.plaf.nimbus.NimbusLookAndFeel");
		}catch(Throwable e){
			e.printStackTrace();
		}
		
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					ArrayCreateTable frame = new ArrayCreateTable();
					frame.setVisible(true);
				}catch(Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
	
	public ArrayCreateTable() {
		setTitle("用数组设置JTable表格的列名与列宽");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100,100,557,300);
		mainPane = new JPanel();
		mainPane.setBorder(new EmptyBorder(5,5,5,5));
		mainPane.setLayout(new BorderLayout(0,0));
		setContentPane(mainPane);
		mainPane.add(getScrollPane(),BorderLayout.CENTER);
	}
	
	private JScrollPane getScrollPane() {
		if(scrollPane == null) {
			scrollPane = new JScrollPane();
			scrollPane.setViewportView(getTable());
		}
		return scrollPane;
	}
	
	private JTable getTable() {
		if(table == null) {
			table = new  JTable();
			//定义列名数组
			String[] columns = {"星期一", "星期二", "星期三", 
					"星期四", "星期五", "星期六","星期日" };
			//定义列宽数组
			int[] columnWidth= {10,20,30,40,50,60,70};
			//创建是表格数据模型
			DefaultTableModel  model = new DefaultTableModel(columns,15);
			table.setModel(model);
			TableColumnModel columnModel = table.getColumnModel();
			//获取列模型
			int count = columnModel.getColumnCount(); //获取列数量
			for (int i = 0; i < count; i++) { //遍历列
				TableColumn column = columnModel.getColumn(i);
				column.setPreferredWidth(columnWidth[i]);
			}
		}
		return table;
	}
	
}
