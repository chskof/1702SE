package chenhs.agree;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.Properties;

import org.apache.commons.dbcp.BasicDataSource;



public class DataBase {

		private static BasicDataSource ds; // 连接池对象-由DBCP提供

		static {
			Properties p = new Properties();
			try {
				//加载配置文件
				InputStream in =new FileInputStream("resources/jdbc.properties");
				p.load(in);
				String driver = p.getProperty("driver");
				String url = p.getProperty("url");
				String user = p.getProperty("user");
				String pwd = p.getProperty("password");
				String initsize = p.getProperty("initsize");
				String maxsize = p.getProperty("maxsize");
				//创建连接池
				ds = new BasicDataSource();
				//注册驱动
				ds.setDriverClassName(driver);  
				//使用这三个参数创建连接
				ds.setUrl(url);				
				ds.setUsername(user);
				ds.setPassword(pwd);
				//使用其他参数管理连接
				ds.setInitialSize(new Integer(initsize));
				ds.setMaxActive(new Integer(maxsize));
				
			} catch (IOException e) {
				e.printStackTrace();
				throw new RuntimeException("加载配置文件失败！",e);
			}
		}

		public static Connection getConnection() throws SQLException {
			return ds.getConnection();
		}
		
		public static void close(Connection conn) {
			if (conn != null);
			try {
				conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
				throw new RuntimeException("归还连接失败！", e);
			}
		}
		
		public static void rollback(Connection conn){
			if(conn!=null){
				try {
					conn.rollback();
				} catch (SQLException e) {
					e.printStackTrace();
					throw new RuntimeException("回滚失败",e);
				}
			}
		}
		
	
}
