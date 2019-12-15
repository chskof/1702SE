package coreJava.stream;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.List;

public class CountLongWords {
	public static void main(String[] args) throws IOException {
		String contents = new String(Files.readAllBytes(Paths.get("text.txt")),
				StandardCharsets.UTF_8);
		System.out.println("contents: "+contents);
		List<String> words = Arrays.asList(contents.split("\\PL+"));
		System.out.println("words: "+words);
		
		long count1 = 0;
		
		for(String w : words) {
			if(w.length()>3) count1++;
		}
		System.out.println("count1: "+count1);
		
		long count2 = words.stream().filter(w -> w.length()>3).count();
		System.out.println("count2: "+count2);
		
		long count3 = words.parallelStream().filter(w -> w.length()>3).count();
		System.out.println("count3:"+count3);
	}
	
}
