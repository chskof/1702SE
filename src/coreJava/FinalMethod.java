package coreJava;

class Parents{
	private final void doit() {
		System.out.println("父类.doit()");
	}
	final void doit2() {
		System.out.println("父类.doit2()");
	}
	public void doit3() {
		System.out.println("父类.doit3()");
	}
}

class Sub extends Parents{
	public void doit() {
		System.out.println("子类.doit()");
	}
//	final void doit2() {
//		System.out.println("子类.doit2()");
//	}
	public void doit3() {
		System.out.println("子类.doit3()");
	}
}

public class FinalMethod {
	public static void main(String[] args) {
		Sub s = new Sub();
		s.doit();     //子类.doit()
		Parents p = s;  //
	//	p.doit();     
		p.doit2();   //父类.doit2()
		p.doit3();   //子类.doit3()
	}
}
