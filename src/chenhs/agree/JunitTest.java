package chenhs.agree;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

import org.junit.Test;

public class JunitTest {
	@Test
	public void test1() {
		
		Connection conn = null;
		try {
			conn = DataBase.getConnection();
			System.out.println(conn);
			Statement smt = conn.createStatement();
			String sql = "select * from contact ";
			ResultSet rs = smt.executeQuery(sql);
			ResultSetMetaData metaData = rs.getMetaData();
			int columns = metaData.getColumnCount();
			while(rs.next()){
				    for(int i=1;i<=columns;i++){
				     System.out.print(rs.getString(i));
				     System.out.print("\t");
				    }
				    System.out.println();
		           }

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			DataBase.close(conn);
		}
	}
	
	@Test
	public void test2() throws SQLException {
		Map<String,Object> inMap = new HashMap<String,Object>();
		inMap.put("name", "陈方宙"); //
		inMap.put("spell", "chenfangzhou"); //
		inMap.put("phone", "15070821065"); //
		inMap.put("weixin", "chskof"); //
		inMap.put("mail", "412603534@qq.com"); //
		inMap.put("birthday", 19920201); //
		inMap.put("sex", "男"); //
		inMap.put("address", "南昌"); //
		inMap.put("remark", "");
		DBUtil.executeInsert("contact", inMap);
	}
	
	@Test
	public void test3() throws Exception {
		int day = DateUtil.compareDateOnDay("20180610", "20180513");
		System.out.println(day);
	}
	
	
	@Test
	public void test4() throws SQLException, Exception {
		List<String[]> list = new ArrayList<String[]>();
		String[] data1 = {"陈方宙2","chenfangzhou","15070821065","ckskof","1050103569@qq.com","19940531","男","江西",""};
		String[] data2 = {"陈方宙3","chenfangzhou","15070821065","ckskof","1050103569@qq.com","19940531","男","江西",""};
		list.add(data1);
		list.add(data2);
		DBUtil.executeInsert("contact", list);
	}
	
	@Test
	public void test5() throws SQLException, Exception {
		Map<String,Object> conditionMap = new HashMap<String,Object>();
		conditionMap.put("spell", "chenfangzhou");
		DBUtil.executeDelete("contact", conditionMap);
	}
	
	@Test
	public void test6() throws Exception {
		String sql = "select * from contact  where address is not null";
		List<Map<String,String>> list =   DBUtil.executeQuery(sql);
		for (Map<String, String> map : list) {
			Set<Entry<String, String>> set =map.entrySet();
			for (Entry<String, String> entry : set) {
				System.out.print("KEY: "+entry.getKey()+" ,VALUE: "+entry.getValue());
				System.out.print("\t\t");
			}
			System.out.println();
		}
	}
	
	@Test
	public void test7() throws Exception {
		String sql = "select * from contact  where address is not null";
		List<String[]> list = DBUtil.executeQueryList(sql);
		for (String[] strings : list) {
			System.out.println(Arrays.toString(strings));
		}
	}
}


