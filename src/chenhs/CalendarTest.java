package chenhs;
import java.time.DayOfWeek;
import java.time.LocalDate;

public class CalendarTest {
	public static void main(String[] args)
	{
    // Date birthday = new Date();
	// System.out.println(birthday);

	//	LocalDate.now();
	//	System.out.println(LocalDate.now());
		
	
	 //   LocalDate.of(1994,05,31);
	//	System.out.println(LocalDate.of(1994,05,31));
		
	/*	LocalDate newYearsEve = LocalDate.now();
		int year = newYearsEve.getYear();
		int month = newYearsEve.getMonthValue();
		int day = newYearsEve.getDayOfMonth();
		System.out.println(year+"年"+month+"月"+day+"日");
	*/
		
	 /*   LocalDate newYearsEve = LocalDate.now();
		LocalDate aThosandDaysLater = newYearsEve.plusDays(1000);
		int year = aThosandDaysLater.getYear();
		int month = aThosandDaysLater.getMonthValue();
		int day = aThosandDaysLater.getDayOfMonth();
		System.out.println(year+"年"+month+"月"+day+"日");
	*/
		
	/*	LocalDate date = LocalDate.now();
		int month = date.getMonthValue();
		int today = date.getDayOfMonth();
		
		date = date.minusDays(today-1);
		DayOfWeek weekday = date.getDayOfWeek();
		int value = weekday.getValue();
		
		System.out.println(" ");
		
		for(int i = 1;i < value;i++)
			System.out.println("   ");
		
		while(date.getMonthValue() == month)
		{
			System.out.printf("%3d",date.getDayOfMonth());
			if (date.getDayOfMonth() == today)
				System.out.print("*");
			else 
				System.out.print("  ");
			date = date.plusDays(1);
			if (date.getDayOfWeek().getValue() == 1)
				System.out.println();
		}
		 
		if (date.getDayOfWeek().getValue() != 1)
			System.out.println();
		*/
		
		
		 LocalDate date = LocalDate.now();
	      int month = date.getMonthValue();
	      int today = date.getDayOfMonth();

	      date = date.minusDays(today - 1);    // Set to start of month
	      DayOfWeek weekday = date.getDayOfWeek();
	      int value = weekday.getValue();     // 1 = Monday, ... 7 = Sunday

	      System.out.println("Mon Tue Wed Thu Fri Sat Sun");
	      for (int i = 1; i < value; i++)
	         System.out.print("    ");
	      while (date.getMonthValue() == month)
	      {
	         System.out.printf("%3d", date.getDayOfMonth());
	         if (date.getDayOfMonth() == today)
	            System.out.print("*");
	         else
	            System.out.print(" ");
	         date = date.plusDays(1);
	         if (date.getDayOfWeek().getValue() == 1) System.out.println();
	      }
	      if (date.getDayOfWeek().getValue() != 1) System.out.println();
		
		
      }
}




/***************************************************************

Mon Tue Wed Thu Fri Sat Sun
          1     2      3      4   5   6 
  7   8   9  10  11  12  13 
 14  15  16  17  18  19  20 
 21  22  23  24  25  26  27 
 28  29* 30 

*******************/