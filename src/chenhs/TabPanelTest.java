package chenhs;

import static java.awt.BorderLayout.CENTER;

import java.awt.BorderLayout;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.Graphics;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Image;
import java.awt.Insets;
import java.awt.image.BufferedImage;
import java.io.File;
import java.net.URL;

import javax.imageio.ImageIO;
import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTabbedPane;
import javax.swing.JTextField;
import javax.swing.JToolBar;
import javax.swing.UIManager;
import javax.swing.border.EtchedBorder;

 

public class TabPanelTest extends JFrame {
	private JPanel frameContentPane;
	private ToolBar toolBar;
	private TabPane tabPane;
	
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
					TabPanelTest frame = new TabPanelTest();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}


	
	
	public TabPanelTest() {
		super();
		setTitle("测试");
		getContentPane().setLayout(new GridBagLayout());
		setBounds(100, 100, 650, 600);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		
		this.setContentPane(getFrameContentPane());// 设置内容面板
	}
	
	
	private JPanel getFrameContentPane() {// 获得内容面板的方法
		if (frameContentPane == null) {// 内容面板对象为空
			frameContentPane = new JPanel();// 创建内容面板
			frameContentPane.setLayout(new BorderLayout());// 设置内容面板的布局
			frameContentPane.add(getTabPane(), CENTER);// 背景面板置于内容面板的中部
			frameContentPane.add(getJJToolBar(), BorderLayout.WEST);// 
			
		}
		return frameContentPane;
	}
	
	private ToolBar getJJToolBar() {
		if (toolBar == null) {// 工具栏对象为空
			toolBar = new ToolBar(tabPane);// 创建工具栏对象
			toolBar.setCursor(new Cursor(Cursor.HAND_CURSOR));// 设置光标图像
		}
		return toolBar;
	}
	
	private JTabbedPane getTabPane() {
		if(tabPane == null) {
		   tabPane = new TabPane();
		}
		return tabPane;
	}
	
	
}


class TabPane extends JTabbedPane{
	private final Image backImage;// 背景图片
	public TabPane() {
		super();
		URL url = TabPane.class.getResource("/res/back.jpg");// 获得背景图片的路径
		backImage = new ImageIcon(url).getImage();// 获得背景图片
	}
	
//	@Override
//	protected void paintComponent(Graphics g) {// 重写绘制组件的方法
////		int width = getWidth();// 定义桌面面板的宽度
////		int height = this.getHeight();// 定义桌面面板的高度
////		g.drawImage(backImage, 5, 100, 300, 300, this);// 绘制背景图片
//	}
}


class ToolBar extends JToolBar {

	private TabPane tabPane;
	
	
	/**
	 * 缺省构造函数
	 */
	public ToolBar() {
	}

	/**
	 * 完整构造函数
	 */
	public ToolBar(TabPane tabPane) {
		super();// 调用父类JToolBar的构造器
		initialize();// 界面初始化方法
		this.tabPane = tabPane;
	}

	/**
	 * 界面初始化方法
	 */
	private void initialize() {
		setSize(new Dimension(600, 24));// 设置工具条的宽高
		setBorder(BorderFactory.createEtchedBorder(EtchedBorder.LOWERED));// 设置工具条的边框
		add(createToolButton());// 
		add(createToolButton2());// 向工具条中添加进货单
	}

	/**
	 * 创建工具栏按钮的方法
	 * 
	 * @return javax.swing.JButton
	 */
	private JButton createToolButton() {
		JButton button = new JButton();// 工具栏按钮
		button.setText("测试");// 设置工具栏按钮的文本内容
		button.setToolTipText("测试");// 设置工具栏按钮的悬浮效果
//		button.setIcon();// 设置工具栏按钮的图标
		button.setFocusable(false);// 让工具栏按钮失去焦点
		button.addActionListener(new java.awt.event.ActionListener() {// 为工具栏按钮添加动作事件的监听
			public void actionPerformed(java.awt.event.ActionEvent e) {
				tabPane.addTab("商品信息添加", null, new ShangPinTianJiaPanel(), "商品添加");
			}
		});
		return button;
	}
	
	private JButton createToolButton2() {
		JButton button = new JButton();// 工具栏按钮
		button.setText("测试2");// 设置工具栏按钮的文本内容
		button.setToolTipText("测试2");// 设置工具栏按钮的悬浮效果
//		button.setIcon();// 设置工具栏按钮的图标
		button.setFocusable(false);// 让工具栏按钮失去焦点
		button.addActionListener(new java.awt.event.ActionListener() {// 为工具栏按钮添加动作事件的监听
			public void actionPerformed(java.awt.event.ActionEvent e) {
				tabPane.remove(0);
			}
		});
		return button;
	}

}

 class ShangPinTianJiaPanel extends JPanel {// 商品添加面板

	private JComboBox gysQuanCheng;// “供应商全称”下拉列表
	private JTextField beiZhu;// “备注”文本框
	private JTextField wenHao;// “批准文号”文本框
	private JTextField piHao;// “批号”文本框
	private JTextField baoZhuang;// “包装”文本框
	private JTextField guiGe;// “规格”文本框
	private JTextField danWei;// “单位”文本框
	private JTextField chanDi;// “产地”文本框
	private JTextField jianCheng;// “简称”文本框
	private JTextField quanCheng;// “商品名称”文本框
	private JButton resetButton;// “重置”按钮

	public ShangPinTianJiaPanel() {// 商品添加面板
		setLayout(new GridBagLayout());// 设置商品添加面板的布局为网格布局
		setBounds(10, 10, 550, 400);// 设置商品添加面板的位置与宽高
		setupComponent(new JLabel("商品名称："), 0, 0, 1, 1, false);// 设置“商品名称”标签的位置并添加到容器中
		quanCheng = new JTextField();// “商品名称”文本框
		setupComponent(quanCheng, 1, 0, 3, 1, true);// 设置“商品名称”文本框的位置并添加到容器中
		setupComponent(new JLabel("简称："), 0, 1, 1, 1, false);// 设置“简称”标签的位置并添加到容器中
		jianCheng = new JTextField();// “简称”文本框
		setupComponent(jianCheng, 1, 1, 3, 10, true);// 设置“简称”文本框的位置并添加到容器中
		setupComponent(new JLabel("产地："), 0, 2, 1, 1, false);// 设置“产地”标签的位置并添加到容器中
		chanDi = new JTextField();// “产地”文本框
		setupComponent(chanDi, 1, 2, 3, 300, true);// 设置“产地”文本框的位置并添加到容器中
		setupComponent(new JLabel("单位："), 0, 3, 1, 1, false);// 设置“单位”标签的位置并添加到容器中
		danWei = new JTextField();// “单位”文本框
		setupComponent(danWei, 1, 3, 1, 130, true);// 设置“单位”文本框的位置并添加到容器中
		setupComponent(new JLabel("规格："), 2, 3, 1, 1, false);// 设置“规格”标签的位置并添加到容器中
		guiGe = new JTextField();// “规格”文本框
		setupComponent(guiGe, 3, 3, 1, 1, true);// 设置“规格”文本框的位置并添加到容器中
		setupComponent(new JLabel("包装："), 0, 4, 1, 1, false);// 设置“包装”标签的位置并添加到容器中
		baoZhuang = new JTextField();// “包装”文本框
		setupComponent(baoZhuang, 1, 4, 1, 1, true);// 设置“包装”文本框的位置并添加到容器中
		setupComponent(new JLabel("批号："), 2, 4, 1, 1, false);// 设置“批号”标签的位置并添加到容器中
		piHao = new JTextField();// “批号”文本框
		setupComponent(piHao, 3, 4, 1, 1, true);// 设置“批号”文本框的位置并添加到容器中
		setupComponent(new JLabel("批准文号："), 0, 5, 1, 1, false);// 设置“批准文号”标签的位置并添加到容器中
		wenHao = new JTextField();// “批准文号”文本框
		setupComponent(wenHao, 1, 5, 3, 1, true);// 设置“批准文号”文本框的位置并添加到容器中
		setupComponent(new JLabel("供应商全称："), 0, 6, 1, 1, false);// 设置“供应商全称”标签的位置并添加到容器中
		gysQuanCheng = new JComboBox();// “供应商全称”下拉列表
		gysQuanCheng.setMaximumRowCount(5);// 设置“供应商全称”下拉列表显示的最大行数
		setupComponent(gysQuanCheng, 1, 6, 3, 1, true);// 设置“供应商全称”下拉列表的位置并添加到容器中
		setupComponent(new JLabel("备注："), 0, 7, 1, 1, false);// 设置“备注”标签的位置并添加到容器中
		beiZhu = new JTextField();// “备注”文本框
		setupComponent(beiZhu, 1, 7, 3, 1, true);// 设置“备注”文本框的位置并添加到容器中
		final JButton tjButton = new JButton();// “添加”按钮
		
	}

	private void setupComponent(JComponent component, int gridx, int gridy, int gridwidth, int ipadx, boolean fill) {// 设置组件的位置并添加到容器中
		final GridBagConstraints gridBagConstrains = new GridBagConstraints();// 创建网格限制对象
		gridBagConstrains.gridx = gridx;// 设置组件位于网格的横向索引为gridx
		gridBagConstrains.gridy = gridy;// 设置组件位于网格的纵向索引为gridy
		gridBagConstrains.insets = new Insets(5, 1, 3, 1);// 组件彼此的间距
		if (gridwidth > 1)// 组件横跨网格数大于1
			gridBagConstrains.gridwidth = gridwidth;// 设置组件横跨网格数为gridwidth
		if (ipadx > 0)// 组件横向填充的大小大于0
			gridBagConstrains.ipadx = ipadx;// 设置组件横向填充的大小
		if (fill)// 组件占据空白区域
			gridBagConstrains.fill = GridBagConstraints.HORIZONTAL;// 组件水平扩大以占据空白区域
		add(component, gridBagConstrains);// 添加组件
	}
	
	@Override
	protected void paintComponent(Graphics g) {// 重写绘制组件的方法
		int width = getWidth();// 定义桌面面板的宽度
		int height = this.getHeight();// 定义桌面面板的高度
		
		URL url = TabPane.class.getResource("/res/back.png");// 获得背景图片的路径
		Image backImage = new ImageIcon(url).getImage();// 获得背景图片
		
		g.drawImage(backImage, 0, 0, width, height, this);// 绘制背景图片
	}
}