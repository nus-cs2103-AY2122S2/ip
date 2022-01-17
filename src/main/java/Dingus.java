import java.util.*;

public class Dingus {
	public static String startLine = "\n--------------------------------------------------------------------";
	public static String endLine = "--------------------------------------------------------------------\n";
	public static String greeting = "\nDingus:	Oh it's you again...\nDingus:	What kind of trouble would you inconvenience me with this time?\n";
	public static String[] storage = new String[100];
	public static int counter = 0;

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		// Greet
		System.out.println(startLine + greeting + endLine);

		while (sc.hasNext()) {
			String input = sc.nextLine().toLowerCase();

			// Exit
			if (input.equals("bye")) {
				System.out.println(startLine + "\nDingus:	Please don't ever bother me again, bye\n" + endLine);
				sc.close();
				return;
			}

			if (!input.equals("list")) {
				// Add
				storage[counter] = input;
				counter++;
				System.out.println(startLine + "\nadded:	" + input + "\n" + endLine);
			} else {
				// List
				String output = startLine;
				for (int i = 0; i < counter; i++) {
					int numbering = i + 1;
					output += "\n	" + numbering + ". " + storage[i];
				}
				output += "\n" + endLine;
				System.out.println(output);
			}
		}

		sc.close();
	}

}
