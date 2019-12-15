package coreJava.stream;

import java.util.Arrays;
import java.util.List;
import java.util.function.Predicate;

public class Lambda {
	
	public static void main(String[] args) {
		List<String> languages = Arrays.asList("Java", "Python", "scala", "Shell", "R");
		
		System.out.println("Language starts with J: ");
		filterTest(languages, x -> x.startsWith("J"));
		
		System.out.println("\nLanguage ends with a: ");
		filterTest(languages, x -> x.endsWith("a"));
		
		System.out.println("\nAll languages: ");
		filterTest(languages, x -> true);
		
		System.out.println("\nNo languages: ");
		filterTest(languages, x -> false);
		
		System.out.println("\nLanguage length bigger three: ");
		filterTest(languages, x -> x.length() > 4);
	}
	
	public static void filterTest(List<String> languages, Predicate<String> condition) {
		languages.stream().filter(x -> condition.test(x)).forEach(x -> System.out.println(x + " "));
	}
}
