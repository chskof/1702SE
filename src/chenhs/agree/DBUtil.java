package chenhs.agree;

import java.io.ByteArrayInputStream;
import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.junit.Test;


public class DBUtil {

	/**
	 * 获取数据库连接
	 * @return Connection
	 */
	public static Connection getConnection() throws SQLException {
		return DataBase.getConnection();
	}
	
	
	/**
	 * 关闭数据库连接
	 * @param trade
	 * @param conn
	 * @param ps
	 * @param rs
	 */
	public static void close( Connection conn, Statement ps,ResultSet rs) {

		if (rs != null) {
			try {
				rs.close();
			} catch (SQLException e) {

			}
		}
		if (ps != null) {
			try {
				ps.close();
			} catch (SQLException e) {

			}
		}
		if (conn != null) {
			try {
				conn.close();
			} catch (SQLException e) {

			}
		}
	}

	/**
	 * 生成insert插入语句
	 * @param String tableName 表名
	 * @param String[] colName 列名数组
	 * @return String sql
	 */
	public static String getInsertSql(String tableName, String[] colName) {
		if (colName == null || colName.length == 0) {
			return null;
		}
		StringBuffer insertSql = new StringBuffer();
		// 拼更新语句
		insertSql.append("insert into " + tableName + " (");
		insertSql.append(StringUtil.jion(colName, ","));
		insertSql.append(") values (");
		insertSql.append(StringUtil.jion("?", ",", colName.length));
		insertSql.append(")");
		return insertSql.toString();
	}

	/**
	 * 生成update更新语句
	 * @param String tableName 表名
	 * @param String[]  colNames  列名数组
	 * @param String[]  conditionKey 条件
	 * @return
	 */
	public static String getUpdateSql(String tableName, String[] colNames, String[] conditionKey) {
		StringBuffer updateSql = new StringBuffer();
		if (StringUtil.isNull(tableName) || colNames == null || colNames.length == 0)
			return null;
		updateSql.append("update " + tableName + " set ");
		// 拼更新语句
		String[] _colNames = new String[colNames.length];
		for (int i = 0; i < colNames.length; i++) {
			_colNames[i] = colNames[i] + " = ? ";
		}
		updateSql.append(StringUtil.jion(_colNames, ","));

		String whereSql = "";
		if (conditionKey != null && conditionKey.length > 0) {
			for (int i = 0; i < conditionKey.length; i++) {
				if (i == 0) {
					whereSql = conditionKey[i] + " = ? ";
				} else {
					whereSql = whereSql + " and " + conditionKey[i] + " = ? ";
				}
			}
			updateSql.append(" where " + whereSql);

		}
		return updateSql.toString();
	}

	
	/**
	 * 设置参数
	 * @param colValues  每行的值 Map
	 * @param colName    列名数组 String[]
	 * @param ps         预处理器
	 * @param start      起始参数
	 */
	public static void setParameter(Map colValues, String[] colName,PreparedStatement ps, int start) throws SQLException {
		for (int i = 0; i < colName.length; i++) {
			Object value = colValues.get(colName[i]);
			if (value == null) {
				ps.setObject(i + start, null);
			} else if (value instanceof String) {
				ps.setString(i + start, (String) value);
			} else if (value instanceof Character)
				ps.setString(i + start, ((Character) value).toString());
			else if (value instanceof Integer)
				ps.setInt(i + start, ((Integer) value).intValue());
			else if (value instanceof Double)
				ps.setDouble(i + start, ((Double) value).doubleValue());
			else if (value instanceof Date) {
				Date d = (Date) value;
				ps.setTimestamp(i + start, new Timestamp(d.getTime()) );  //Date转换成Timestamp
			} else if (value instanceof byte[]) {
				ByteArrayInputStream is = new ByteArrayInputStream((byte[]) value);
				ps.setBinaryStream(i + start, is, ((byte[]) value).length);
			}
		}
	}
	
	/**
	 * 执行信息插入操作
	 * @param trade
	 * @param tableName
	 * @param colValuesMap
	 * @return boolean
	 * @throws SQLException
	 */
	public static boolean executeInsert(String tableName,Map colValuesMap) throws SQLException {

		PreparedStatement ps = null;
		Connection conn = null;
		try {
			conn = DBUtil.getConnection();
			conn.setAutoCommit(false);
			ps = createInsertPreparedStatement(tableName, colValuesMap,conn);
			if (ps == null)
				return false;
			int recordNum = ps.executeUpdate();
			conn.commit();
			conn.setAutoCommit(true);
			return recordNum > 0;
		} finally {
			conn.setAutoCommit(true);
			close( conn, ps, null);
		}
	}
	
	
	
	
	/**
	 * 创建插入预处理器
	 * @param tableName   表名
	 * @param colValuesMap 行数据 Map
	 * @param conn   连接
	 * @return PreparedStatement
	 */
	@SuppressWarnings("unchecked")
	public static PreparedStatement createInsertPreparedStatement(String tableName, Map colValuesMap, 
			Connection conn)throws SQLException {
		String[] colName = (String[]) colValuesMap.keySet().toArray(new String[0]);
		String sql = getInsertSql( tableName, colName);
		if (sql == null)
			return null;
		PreparedStatement ps = conn.prepareStatement(sql.toString());
		setParameter(colValuesMap, colName, ps, 1);
		return ps;

	}
	
	/**
	 * 数据库批量插入 
	 * @param tableName  表名
	 * @param list 所有要插入的数据.格式为List<String[]>
	 */
	public static void executeInsert( String tableName, List list)throws SQLException, Exception {
		// 数据连接
		Connection con = null;
		Statement stat = null;
		ResultSet rs = null;
		PreparedStatement prep = null;
		String[] datas = null;

		try {
			con = DBUtil.getConnection();
			// 自动事务关闭
			con.setAutoCommit(false);
			// 首先要把数据库中的数据清空
			stat = con.createStatement();
			// 如果存在表,把里面的数据清空
//			try {
//				stat.executeUpdate("delete from " + tableName);
//			} catch (Exception e) {
//				throw e;
//			}

			// 获取表列名数据类型.
			rs = stat.executeQuery("select * from " + tableName);
			ResultSetMetaData rm = rs.getMetaData();

			int colCount = rm.getColumnCount();
			StringBuilder sb = new StringBuilder();
			sb.append("insert into ");
			sb.append(tableName);
			sb.append(" values (");
			for (int i = 0; i != colCount; i++) {
				sb.append("?");
				if (i != colCount - 1)
					sb.append(",");
			}
			sb.append(")");

			// 批量存入数据
			prep = con.prepareStatement(sb.toString());
			Iterator it = list.iterator();
			while (it.hasNext()) {
				datas = (String[]) it.next();
				for (int i = 0; i != colCount; i++) {
					if (datas.length - 1 < i
							|| (datas.length > i && "".equals(datas[i].trim()))) {
						if ("varchar".equals(rm.getColumnTypeName(i + 1)    //mysql数据库是varchar 其他数据库有可能是varchar2
								.toLowerCase())) {
							prep.setString(i + 1, null);
						} else {
							prep.setBigDecimal(i + 1, null);
						}
					} else {
						if ("varchar".equals(rm.getColumnTypeName(i + 1).toLowerCase())) {
							prep.setString(i + 1, datas[i].trim());
						} else {
							prep.setBigDecimal(i + 1, new BigDecimal(datas[i].trim()));
						}
					}
				}
				prep.addBatch();
			}
			prep.executeBatch();
			// 提交事务
			con.commit();
		} catch (Exception e) {
			throw e;
		} finally {

			if (null != rs)
				rs.close();
			if (null != stat)
				stat.close();
			if (null != prep)
				prep.close();
			if (null != con) {
				con.setAutoCommit(true);
				con.close();
			}
		}
	}
	
	/**
	 * 数据删除
	 * 参数分别为：tableName为删除的表名；conditionMap为删除的条件（表名不能为空、conditionMap可以为空）
	 * 返回类型为布尔类型，如果成功删除了至少一条记录，则返回true； 如果删除失败或数据库没满足条件的记录，则返回false
	 * 使用范例:
	 * tableName="employee"; Map conditon=new HashMap();
	 * condition.put("employeeNo","1234"); condition.put("employeeName",null);
	 * DataBase.executeDelete(this,tableName,condition);
	 * 相当于执行删除："delete from employee where employeeN0='1234' and employee isnull ";
	 */
	// 执行删除
	public static boolean executeDelete(String tableName,Map conditionMap) throws SQLException, Exception {
		boolean flag = false;
		StringBuffer deleteSql = new StringBuffer();
		if (conditionMap == null) {
			conditionMap = new HashMap();
		}
		Object[] colName = conditionMap.keySet().toArray();

		// 拼更新语句
		deleteSql.append("delete from " + tableName + " where ");
		for (int i = 0; i < conditionMap.size(); i++) {
			// 判断键值是否有空值
			if (conditionMap.get(colName[i]) != null) {
				deleteSql.append((String) colName[i] + " =? ");
			} else {
				deleteSql.append((String) colName[i] + " is null ");
			}
			if (i != conditionMap.size() - 1) {
				deleteSql.append("and ");
			}
		}
		// 更新数据库
		PreparedStatement prepared = null;
		Connection conn = null;
		try {
			conn = DataBase.getConnection(); // 取得数据库的连接
			prepared = conn.prepareStatement(deleteSql.toString()); // 预处理

			for (int j = 0, i = 0; j < conditionMap.size(); j++) {
				if (conditionMap.get(colName[j]) != null) {
					setParameter(i + 1, conditionMap.get(colName[j]), prepared);
					i++;
				}
			}
			int recordNum = prepared.executeUpdate();
			if (recordNum >= 0) {
				flag = true;
			} else {
				flag = false;
			}
			return flag;
		} catch (SQLException e) {
			return false;
		} finally {
			try {
				// 关闭语句和连接
				if (prepared != null) {
					prepared.close();
					prepared = null;
				}
				if (conn != null) {
					conn.close();
					conn = null;
				}
			} catch (SQLException e) {
				throw e;
			}
		}
	}
	
	/**
	 * 为PreparedStatement预处理器设置参数
	 */
	private static void setParameter(int index, Object param,PreparedStatement prepared) {
		try {
			if (param instanceof String)
				prepared.setString(index, (String) param);
			if (param instanceof Character)
				prepared.setString(index, ((Character) param).toString());
			if (param instanceof Integer)
				prepared.setInt(index, ((Integer) param).intValue());
			if (param instanceof Double)
				prepared.setDouble(index, ((Double) param).doubleValue());
			if (param instanceof Date)
				prepared.setDate(index, (java.sql.Date) param);
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * 数据查询 
	 * @return  List< Map < String,String > >  每个Map存储了一行数据
	 */
	public static List executeQuery(String sql) throws Exception {
		Connection conn = null;
		Statement stmt = null;
		ResultSet rs = null;
		try {
			conn = DataBase.getConnection();
			stmt = conn.createStatement();
			rs = stmt.executeQuery(sql);

			ResultSetMetaData meta = rs.getMetaData();
			int columnCount = meta.getColumnCount();
			List<Map> result = new ArrayList<Map>();
			while (rs.next()) {
				Map<String, String> map = new LinkedHashMap<String, String>();
				for (int i = 0; i < columnCount; i++) {
					String value = rs.getString(i + 1);
					map.put(meta.getColumnName(i + 1).toLowerCase(),
							(value == null) ? "" : value);
				}
				result.add(map);
			}
			return result;
		} catch (Exception e) {
			throw e;
		} finally {
			close(conn, stmt, rs);
		}
	}
	
	/**
	 * 查询数据
	 * @return List< String[] >    每个String[]数组存储了一行数据
	 */
	public static List<String[]> executeQueryList(String sql) throws Exception {
		Connection conn = null;
		Statement stmt = null;
		ResultSet rs = null;
		try {
			conn = DataBase.getConnection();
			stmt = conn.createStatement();
			rs = stmt.executeQuery(sql);
			ResultSetMetaData meta = rs.getMetaData();
			int columnCount = meta.getColumnCount();
			List<String[]> result = new ArrayList<String[]>();
			while (rs.next()) {
				String[] data = new String[columnCount];
				for (int i = 0; i < columnCount; i++) {
					String value = rs.getString(i + 1);
					data[i]=(value == null) ? "" : value;
				}
				result.add(data);
			}
			return result;
		} catch (Exception e) {
			throw e;
		} finally {
			close( conn, stmt, rs);
		}
	}
	
	/**
	 * 数据查询 按照指定顺序排序
	 * @param tableName
	 * @param conditionMap
	 * @param orderby
	 * @return List< Map >    
	 */
	public static List executeQuery( String tableName,Map conditionMap, String orderby) throws SQLException, Exception {
		List resultList = new ArrayList();
		ResultSetMetaData meta = null;
		ResultSet rs = null;
		String temp_array[];
		StringBuffer querySql = new StringBuffer();
		if (conditionMap == null) {
			conditionMap = new HashMap();
		}
		Object[] colName = conditionMap.keySet().toArray();
		// 拼查询语句
		querySql.append("select * from " + tableName + " where 1=1 ");
		for (int i = 0; i < conditionMap.size(); i++) {
			// 判断键值是否有空值
			if (conditionMap.get(colName[i]) != null) {
				querySql.append(" and " + (String) colName[i] + "=? ");
			} else {
				querySql.append(" and " + (String) colName[i] + " is null ");
			}

		}
		if (orderby != null && !"".equals(orderby)) {
			querySql.append(" order by  " + orderby);
		}
		// 查询数据库
		PreparedStatement prepared = null;
		Connection conn = null;
		try {
			conn = DataBase.getConnection();// 取得连接
			prepared = conn.prepareStatement(querySql.toString()); // 预处理
			for (int j = 0, i = 0; j < conditionMap.size(); j++) {
				if (conditionMap.get(colName[j]) != null) {
					setParameter(i + 1, conditionMap.get(colName[j]), prepared);
					i++;
				}
			}
			rs = prepared.executeQuery();

			// 得到表的元数据（列名）
			meta = rs.getMetaData();
			int maxColumn = meta.getColumnCount();
			temp_array = new String[maxColumn];
			for (int i = 0; i < maxColumn; i++) {
				temp_array[i] = meta.getColumnName(i + 1).toLowerCase();
				if (temp_array[i] == null) {
					temp_array[i] = new String("");
				}
			}
			// 取查询结果集
			while (rs.next()) {
				Map row = new HashMap();
				for (int i = 0; i < maxColumn; i++) {
					String colValue = null;
					colValue = rs.getString(temp_array[i]);
					if (colValue == null || colValue.equals("")) {
						row.put(temp_array[i].trim().toLowerCase(), new String(""));
					} else {
						row.put(temp_array[i].trim().toLowerCase(), colValue);
					}
				}
				resultList.add(row);
			}

		} catch (SQLException e) {
			throw e;
		} finally {
			close( conn, prepared, rs);
		}
		return resultList;
	}
}
