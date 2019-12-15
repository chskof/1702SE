package coreJava.collect.treeSet;

import java.util.NavigableSet;
import java.util.SortedSet;
import java.util.TreeSet;

public class TreeSetTest {
	public static void main(String[] args) {
		SortedSet<Item> parts = new TreeSet<>();
		parts.add(new Item("Tosater",1234));
		parts.add(new Item("Widget",4562));
		parts.add(new Item("Modem",9912));
		System.out.println(parts);
		
		NavigableSet<Item> sortByDescription = new TreeSet<>(( x, y)->(x.getDescription().compareTo(y.getDescription())));
	//  NavigableSet<Item> sortByDescription = new TreeSet<>(Comparator.comparing(Item::getDescription));
		
		sortByDescription.addAll(parts);
		System.out.println(sortByDescription);
	}
}
