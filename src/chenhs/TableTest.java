package chenhs;

import java.awt.EventQueue;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.UIManager;
import javax.swing.table.DefaultTableModel;

public class TableTest extends JFrame {
	private JTable table; 
	private JComboBox conditionName;
	private JComboBox conditionOperation;
	private JTextField conditionContent;
	
	public static void main(String[] args) { 
		try {
			/*Nimbus 为其应用程序提供了一个完美的界面。
                              而且 Nimbus 完全使用 Java 2D 矢量图型而不是静态位图，所以非常小（只有56KB!）*/
			UIManager.setLookAndFeel("com.sun.java.swing.plaf.windows.WindowsClassicLookAndFeel");
		} catch (Exception e) {
			e.printStackTrace();
		}
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					TableTest frame = new TableTest();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	
	
	public TableTest() {
		super();
		setTitle("商品信息查询");
//		JPanel panel = new JPanel();
//		add(panel);
		getContentPane().setLayout(new GridBagLayout());
		setBounds(0, 0, 650, 600);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		
		//表格模型
		table = new JTable();
		table.setEnabled(false);
		table.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
		
		final DefaultTableModel dftm = (DefaultTableModel) table.getModel();
		String[] tableHeads = new String[] {"客户ID","商品名称","简称","产地","单位","规格","包装","批号"};
		dftm.setColumnIdentifiers(tableHeads);
		dftm.addRow(new String[] {"123","铅笔","笔","江西南昌","南昌","12","塑料","1654654"});
		dftm.addRow(new String[] {"456","铅笔","笔","江西南昌","南昌","12","塑料","1654654"});
		//滚动面板
		final JScrollPane scrollPane = new JScrollPane(table);
		final GridBagConstraints gridBagConstraints_6 = new GridBagConstraints();
		gridBagConstraints_6.weightx = 1.0;
		gridBagConstraints_6.anchor = GridBagConstraints.NORTH;
		gridBagConstraints_6.insets = new Insets(0,10,0,10);
		gridBagConstraints_6.fill = GridBagConstraints.BOTH;
		gridBagConstraints_6.gridwidth = 6;
		gridBagConstraints_6.gridy = 1;
		gridBagConstraints_6.gridx = 0;
		getContentPane().add(scrollPane,gridBagConstraints_6);
		
		//选择查询条件标签  条件名称下拉列表
		setupComponet(new JLabel("选择查询条件:"),0,0,1,1,false);
		conditionName = new JComboBox();
		conditionName.setModel(new DefaultComboBoxModel(new String[]{"商品名称","供应商全称","产地","规格"}));
		setupComponet(conditionName, 1, 0, 1, 30, true);
		
		//条件运算下拉列表
		conditionOperation = new JComboBox();
		conditionOperation.setModel(new DefaultComboBoxModel(new String[]{"等于","包含"}));
		setupComponet(conditionOperation, 2, 0, 1, 30, true);
		
		//条件内容文本框
		conditionContent = new JTextField();
		setupComponet(conditionContent, 3, 0, 1, 140, true);
		
		//查询按钮
		final JButton queryButton  = new  JButton();
//		queryButton.addActionListener(new QueryAction(dftm));
	}
	
	// 设置组件位置并添加到容器中
	private void setupComponet(JComponent component, int gridx, int gridy,
			int gridwidth, int ipadx, boolean fill) {
		final GridBagConstraints gridBagConstrains = new GridBagConstraints();
		gridBagConstrains.gridx = gridx;
		gridBagConstrains.gridy = gridy;
		if (gridwidth > 1)
			gridBagConstrains.gridwidth = gridwidth;
		if (ipadx > 0)
			gridBagConstrains.ipadx = ipadx;
		gridBagConstrains.insets = new Insets(5, 1, 3, 1);
		if (fill)
			gridBagConstrains.fill = GridBagConstraints.HORIZONTAL;
		getContentPane().add(component, gridBagConstrains);
	}
	
	private final class QueryAction implements ActionListener{
		private final DefaultTableModel dftm;
		private QueryAction(DefaultTableModel dftm) {
			this.dftm  = dftm;
		}
		
		public void actionPerformed(final ActionEvent e) {
			
		}
		
	}
}