import java.io.BufferedReader;
import java.io.InputStreamReader;

public class Dingus {
	public static String greeting = "oh, it's you again?\nhow would you like to inconvenience me today?";
	public static String line = "\n-----------------------------------------------------\n";

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		System.out.println(line + greeting + line);

		while (true) {
			Input userInput = new Input();
			userInput.input(br.readLine());

			if (userInput.input.equals("bye")) {
				System.out.println(line + "Please leave me alone and bother someone else with your issues next time" + line);
				return;
			}
			System.out.println(line + "\n" + "	" + userInput + "\n" + line);
		}
	}
}
