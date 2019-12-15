package coreJava.ioFile;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.Date;
import java.util.GregorianCalendar;

/**
 * 序列化提供了一种克隆对象的简便途径,只要对应的额类是序列化的即可:直接将对象序列化到输出流中, 然后将其读回.这样产生的新对象是对现有对象的一个深拷贝.
 */
public class SerialCloneTest {
	public static void main(String[] args) {
		Staff harry = new Staff("Harry Hacker", 35000, 1989, 10, 1);
		Staff harry2 = (Staff) harry.clone();

		harry.raiseSalary(10);

		System.out.println(harry);
		System.out.println(harry2);
	}
}

class SerialCloneable implements Cloneable, Serializable {
	private static final long serialVersionUID = 1L;

	public Object clone() {
		try {
			ByteArrayOutputStream bout = new ByteArrayOutputStream();
			ObjectOutputStream out = new ObjectOutputStream(bout);
			out.writeObject(this);

			ByteArrayInputStream bin = new ByteArrayInputStream(bout.toByteArray());
			ObjectInputStream in = new ObjectInputStream(bin);
			Object ret = in.readObject();
			in.close();

			return ret;
		} catch (Exception e) {
			return null;
		}
	}
}

class Staff extends SerialCloneable {

	private static final long serialVersionUID = 1L;
	
	private String name;
	private double salary;
	private Date hireDay;

	public Staff(String n, double s, int year, int month, int day) {
		name = n;
		salary = s;
		GregorianCalendar calendar = new GregorianCalendar(year, month - 1, day);
		hireDay = calendar.getTime();
	}

	public String getName() {
		return name;
	}

	public double getSalary() {
		return salary;
	}

	public Date getHireDay() {
		return hireDay;
	}

	public void raiseSalary(double byPercent) {
		double raise = salary * byPercent / 100;
		salary += raise;
	}

	public String toString() {
		return getClass().getName() + "[name=" + name + ",salary=" + salary + ",hireDay=" + hireDay + "]";
	}
}