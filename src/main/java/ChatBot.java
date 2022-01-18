import java.util.Random;
import java.util.Scanner;

public class ChatBot {

	private static final String BORDER =
		"*************************************************************************************";
	private static final String[] GREETING_QUOTES = {
		"Welcome to my inn",
		"Pull up a chair by the hearth!",
		"Come in, and shut the door, it's cold out there!",
		"Don't be scared. Come in, have a seat!",
	};
	private static final Random RANDOM_INDEX_GENERATOR = new Random();

	public static void main(String[] args) {
		greet();

		TaskList taskList = new TaskList();
		Scanner sc = new Scanner(System.in);
		Boolean loop = true;

		while (loop.equals(true)) {
			prompt();
			System.out.print("\nYou: ");
			String rawInput = sc.nextLine();
			String[] input = rawInput.split(" ");
			System.out.println();
			switch (input[0]) {
				case "bye":
					chat("Goodbye traveller! Hope to see you again soon!");
					System.out.println(BORDER);
					sc.close();
					loop = false;
					break;
				case "list":
					if (taskList.isEmpty()) {
						chat("Your task list is currently empty!");
					} else {
						chat("Here you go!");
						taskList.summary();
					}
					break;
				case "mark":
					markOrUnmark(
						taskList,
						Integer.parseInt(input[1]) - 1,
						true
					);
					break;
				case "unmark":
					markOrUnmark(
						taskList,
						Integer.parseInt(input[1]) - 1,
						false
					);
					break;
				case "todo":
					chat(taskList.addToDo(input));
					printNumTasks(taskList.getNumTasks());
					break;
				default:
					String[] temp = rawInput.split("/");
					try {
						if (temp.length != 2) {
							throw new IllegalArgumentException();
						} else {
							chat(
								taskList.add(
									temp[0].split(" "),
									temp[1].split(" ")
								)
							);
							printNumTasks(taskList.getNumTasks());
						}
					} catch (IllegalArgumentException e) {
						chat("Thats an invalid input traveller!");
					}
			}
		}
	}

	public static void markOrUnmark(
		TaskList taskList,
		int index,
		boolean mark
	) {
		try {
			if (taskList.isValidIndex(index).equals(true)) {
				if (mark) {
					chat(taskList.mark(index));
				} else {
					chat(taskList.unmark(index));
				}
			} else {
				throw new NumberFormatException();
			}
		} catch (NumberFormatException e) {
			chat("Something went wrong traveller.");
			chat(
				"Ensure that you are entering a valid index for the task you wish to unmark"
			);
		}
	}

	public static void greet() {
		System.out.println(BORDER + "\n");
		chat("Greetings, traveller!");
		chat(getRandomGreetingQuote());
		chat(
			"I'm the innkeeper and im here to help you with whatever you need."
		);
	}

	public static void chat(String message) {
		System.out.print("Innkeeper: ");
		System.out.println(message + "\n");
	}

	public static void prompt() {
		System.out.println(BORDER + "\n");
		chat("What can I do for you today?");
		System.out.println(BORDER);
	}

	public static String getRandomGreetingQuote() {
		int randomIndex = RANDOM_INDEX_GENERATOR.nextInt(
			GREETING_QUOTES.length
		);
		return GREETING_QUOTES[randomIndex];
	}

	public static void printNumTasks(int numTasks) {
		chat(
			String.format(
				"You now have %d %s!",
				numTasks,
				numTasks == 1 ? "task" : "tasks"
			)
		);
	}
}
