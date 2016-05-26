import java.awt.List;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Scanner;

//lab 5
// Nick Townsend
// 	• Reads in the Shakespeare poems file and processes it a line at a
//		time (as a string).
//	• Splits the line into a String array made up of tokens (words).
//		Assume the words in the line are separated by 1 or more
//		whitespace characters.
//	• Examine each word in the array looking for words that contain 3
//		vowels in a row.
//	• Write all words that match the prior condition to a Map data
//		structure made up of the word as the key and the value = 1.
//	• However, prior to putting the word in the Map, check if that word
//		already exists. If it does, rewrite it incrementing the value by +1.
//	• When you have processed all lines in the file, using an iterator print
//		out the first 11 words in the map. 

public class RegExTester {

	public static void main(String[] args) throws IOException {

		Map<String, Integer> newmap = new HashMap<String, Integer>();
		Scanner sc = new Scanner(
				new File(
						"D:\\Users\\Nick\\workspace\\Welcome\\src\\Shakespeare\\poems.txt"));
		int counter;
		String temp;
		while (sc.hasNextLine()) {
			temp = sc.nextLine();
			String[] arr = temp.split(" ");

			for (String num : arr) {

				if (num.matches(".*[aeiou]{3}.*")) {

					if (newmap.containsKey(num)) {
						counter = newmap.get(num);
						newmap.put(num, counter + 1);
					} else {
						newmap.put(num, 1);
					}
				}
			}

		}
// http://stackoverflow.com/questions/12739971/huge-hashtable-sorting-number-of-values-553685
// was very helpful for last portion of lab.		
		
		ArrayList<Entry<String, Integer>> entries = new ArrayList<Map.Entry<String, Integer>>(
				newmap.entrySet());
		
		ArrayList<Entry<String, Integer>> low = new ArrayList<Map.Entry<String, Integer>>(
				newmap.entrySet());

		Collections.sort(entries, new Comparator<Map.Entry<String, Integer>>() {

			@Override
			public int compare(Entry<String, Integer> first,
					Entry<String, Integer> second) {

				return second.getValue() - first.getValue();
			}

		});
		
		Collections.sort(low, new Comparator<Map.Entry<String, Integer>>() {

			@Override
			public int compare(Entry<String, Integer> first,
					Entry<String, Integer> second) {

				return second.getValue() - first.getValue();
			}

		});

		for (Map.Entry<String, Integer> entry : entries.subList(0, 11))
			System.out.println(String.format("%s: %d", entry.getKey(),
					entry.getValue()));
		
		System.out.println("\n");
		System.out.println("Least Used");
		System.out.println("\n");

		
		for (Map.Entry<String, Integer> entry : low.subList(69, 80))
			System.out.println(String.format("%s: %d", entry.getKey(),
					entry.getValue()));
		

	}
}