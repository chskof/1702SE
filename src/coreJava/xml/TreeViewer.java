package coreJava.xml;

import java.awt.*;
import java.awt.event.*;
import java.io.*;
import javax.swing.*;
import javax.swing.event.*;
import javax.swing.table.*;
import javax.swing.tree.*;
import javax.xml.parsers.*;
import org.w3c.dom.*;
import org.w3c.dom.CharacterData;

/**
 * 此程序将以树形模式展示XML文件
 */
public class TreeViewer
{
   public static void main(String[] args)
   {
	  //EventQueue 是一个与平台无关的类，它将来自于底层同位体类和受信任的应用程序类的事件列入队列。 
	  //它封装了异步事件指派机制，该机制从队列中提取事件  也就是说，不允许同时从该队列中指派多个事件。 
	  //指派顺序与它们排队的顺序相同。 也就是说，如果 AWTEvent A 比 AWTEvent B 先排入到 EventQueue 中，那么事件 B 不能在事件 A 之前被指派。
 
      EventQueue.invokeLater(new Runnable()  
         {
            public void run()
            {
               JFrame frame = new DOMTreeFrame();
               frame.setTitle("XML树形浏览");
               frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
               frame.setVisible(true);
            }
         });
   }
}

/**
 * 这个框架包含一个展示XML内容的树型
 */
class DOMTreeFrame extends JFrame
{
   private static final int DEFAULT_WIDTH = 400;
   private static final int DEFAULT_HEIGHT = 400;

   private DocumentBuilder builder;  //从 XML文档获取 DOM 文档实例。使用此类 可以从 XML 获取一个 Document

   public DOMTreeFrame()
   {
      setSize(DEFAULT_WIDTH, DEFAULT_HEIGHT);

      JMenu fileMenu = new JMenu("文件");
      JMenuItem openItem = new JMenuItem("打开");
      openItem.addActionListener(new ActionListener()
         {
            public void actionPerformed(ActionEvent event)
            {
               openFile();
            }
         });
      fileMenu.add(openItem);

      JMenuItem exitItem = new JMenuItem("退出");
      exitItem.addActionListener(new ActionListener()
         {
            public void actionPerformed(ActionEvent event)
            {
               System.exit(0);
            }
         });
      fileMenu.add(exitItem);
      
      JMenuBar menuBar = new JMenuBar();
      menuBar.add(fileMenu);
      this.setJMenuBar(menuBar);
   }
   //打开并加载文件
   public void openFile()
   {
      JFileChooser chooser = new JFileChooser();     //JFileChooser 为用户选择文件提供了一种简单的机制。
      chooser.setCurrentDirectory(new File("dom"));  //设置当前目录

      chooser.setFileFilter(new javax.swing.filechooser.FileFilter()
         {
            public boolean accept(File f)
            {
               return f.isDirectory() || f.getName().toLowerCase().endsWith(".xml");
            }

            public String getDescription()
            {
               return "XML files";
            }
         });
      int r = chooser.showOpenDialog(this);    //弹出一个 "Open File" 文件选择器对话框
      if (r != JFileChooser.APPROVE_OPTION) return;  //选择确认（yes、ok）后返回该值
      final File file = chooser.getSelectedFile();  //返回选中的文件

      new SwingWorker<Document, Void>(){
            protected Document doInBackground() throws Exception
            {
               if (builder == null)
               {
                  DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();  //XML树形解析器
                  builder = factory.newDocumentBuilder();
               }
               return builder.parse(file);  //将给定文件的内容解析为一个 XML 文档，并且返回一个新的 DOM Document 对象
            }

            protected void done()
            {
               try
               {
                  Document doc = get();
                  JTree tree = new JTree(new DOMTreeModel(doc));
                  tree.setCellRenderer(new DOMTreeCellRenderer());  //绘制解析的每个单元节点

                  setContentPane(new JScrollPane(tree)); //只要组件的内容超过视图大小就会显示水平和垂直滚动条。 
                  validate();  //验证此容器及其所有子组件。 
               }
               catch (Exception e)
               {
                  JOptionPane.showMessageDialog(DOMTreeFrame.this, e);
               }
            }
         }.execute();   // 调度此 SwingWorker 以便在 worker 线程上执行 .SwingWorker 被设计为只执行一次。
                        // 多次执行 SwingWorker 将不会调用两次 doInBackground 方法。 
   }

}

/** 此树形描述了XML文件的结构和内容 */
class DOMTreeModel implements TreeModel
{
   private Document doc;

   /** 树模型构造器 */
   public DOMTreeModel(Document doc)
   {
      this.doc = doc;
   }

   public Object getRoot()
   {
      return doc.getDocumentElement();
   }

   public int getChildCount(Object parent)
   {
      Node node = (Node) parent;
      NodeList list = node.getChildNodes();
      return list.getLength();
   }

   public Object getChild(Object parent, int index)
   {
      Node node = (Node) parent;
      NodeList list = node.getChildNodes();
      return list.item(index);
   }

   public int getIndexOfChild(Object parent, Object child)
   {
      Node node = (Node) parent;
      NodeList list = node.getChildNodes();
      for (int i = 0; i < list.getLength(); i++) {
         if (getChild(node, i) == child) return i;
      }
      return -1;
   }

   public boolean isLeaf(Object node)
   {
      return getChildCount(node) == 0;
   }

   public void valueForPathChanged(TreePath path, Object newValue)
   {
   }

   public void addTreeModelListener(TreeModelListener l)
   {
   }

   public void removeTreeModelListener(TreeModelListener l)
   {
   }

}

/** 这个类用来解析XML节点 */   //显示树中的条目
class DOMTreeCellRenderer extends DefaultTreeCellRenderer 
{
   public Component getTreeCellRendererComponent(JTree tree, Object value, boolean selected,
         boolean expanded, boolean leaf, int row, boolean hasFocus)
   {
      Node node = (Node) value;
      if (node instanceof Element) return elementPanel((Element) node);

      super.getTreeCellRendererComponent(tree, value, selected, expanded, leaf, row, hasFocus);
      if (node instanceof CharacterData) {
    	  setText(characterString((CharacterData) node));
      }else {
    	  setText(node.getClass() + ": " + node.toString());
      }
      return this;
   }

   public static JPanel elementPanel(Element e)
   {
      JPanel panel = new JPanel();
      panel.add(new JLabel("Element: " + e.getTagName()));
      final NamedNodeMap map = e.getAttributes();
      panel.add(new JTable(new AbstractTableModel(){
            public int getRowCount()
            {
               return map.getLength();
            }

            public int getColumnCount()
            {
               return 2;
            }

            public Object getValueAt(int rowIndex, int columnIndex )  //返回 columnIndex 和 rowIndex 位置的单元格值。 
            {
               return columnIndex  == 0 ? map.item(rowIndex).getNodeName() : map.item(rowIndex).getNodeValue();
            }
         }
      ));
      return panel;
   }

   public static String characterString(CharacterData node)
   {
      StringBuilder builder = new StringBuilder(node.getData());
      for (int i = 0; i < builder.length(); i++)
      {
         if (builder.charAt(i) == '\r')
         {
            builder.replace(i, i + 1, "\\r");
            i++;
         }
         else if (builder.charAt(i) == '\n')
         {
            builder.replace(i, i + 1, "\\n");
            i++;
         }
         else if (builder.charAt(i) == '\t')
         {
            builder.replace(i, i + 1, "\\t");
            i++;
         }
      }
      if (node instanceof CDATASection) builder.insert(0, "CDATASection: ");
      else if (node instanceof Text) builder.insert(0, "Text: ");   
      else if (node instanceof Comment) builder.insert(0, "Comment: "); //注释的内容，即起始 '<!--' 和结束 '-->' 之间的所有字符

      return builder.toString();
   }
}

