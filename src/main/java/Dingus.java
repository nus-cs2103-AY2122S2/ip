import java.util.*;

public class Dingus {
	public static String startLine = "\n--------------------------------------------------------------------";
	public static String endLine = "--------------------------------------------------------------------\n";
	public static String greeting = "\nDingus:	Oh it's you again...\nDingus:	What kind of trouble would you inconvenience me with this time?\n";

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		// Greet
		System.out.println(startLine + greeting + endLine);

		// Echo
		while (sc.hasNext()) {
			String input = sc.nextLine().toLowerCase();

			// Exit
			if (input.equals("bye")) {
				System.out.println(startLine + "\nDingus:	Please don't ever bother me again, bye\n" + endLine);
				return;
			}
			System.out.println(startLine + "\nDingus:	" + input + "\n" + endLine);
		}

	}

}
