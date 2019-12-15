package coreJava.thread.synch;

public class SyncBankTest {
	public static final int  NACCOUNTS = 100;
	public static final double INITIAL_BALANCE = 1000;
	public static final double MAX_AMOUNT = 1000;
	public static final int DELAY = 10;
	
	public static void main(String[] args) {
		Bank bank = new Bank(NACCOUNTS,INITIAL_BALANCE);
		for(int i =0; i<NACCOUNTS;i++) {
			int fromAccount =i;
			Runnable r = () -> {
				try {
					while(true) {
						int toAccount =(int) (bank.size() * Math.random());
						double amout = MAX_AMOUNT * Math.random();
						bank.transfer(fromAccount, toAccount, amout);
						Thread.sleep((int) (DELAY * Math.random()));
					}
				}catch(InterruptedException e) {
					
				}
			};
			
			Thread t  = new Thread(r);
			t.start();
		}
	}
}
