package coreJava.collect.map;

import java.util.HashMap;
import java.util.Map;

public class mapTest {
	public static void main(String[] args) {
		Map<Integer,String> students = new HashMap<Integer,String>();
		students.put(1, "chs");
		students.put(2, "abc");
		
		
		for(Integer Id : students.keySet()) {
			String name = students.get(Id);
		}
		
		for(Map.Entry<Integer, String> student:students.entrySet()) {
			Integer id = student.getKey();
			String name = student.getValue();
		}
		
	}
}
