import java.util.Random;
import java.util.Scanner;

public class ChatBot {

	private static final String BORDER =
		"**********************************************************************************************************************";
	private static final String[] GREETING_QUOTES = {
		"Welcome to my inn",
		"Pull up a chair by the hearth!",
		"Come in, and shut the door, it's cold out there!",
		"Don't be scared. Come in, have a seat!",
	};
	private static final Random RANDOM_INDEX_GENERATOR = new Random();
	private static final String[] GUIDE = {
		"list                                               View your task list",
		"todo <name of task>                                Add a todo to your task list",
		"deadline <name of task> /by <deadline of task>     Add a deadline to your task list",
		"event <name of task> /at <timestamp of task>       Add an event to your task list",
		"mark <index of task>                               Mark a task as completed in your task list",
		"unmark <index of task>                             Unmark a task in your task list",
		"bye                                                Exit the program",
	};

	public static void main(String[] args) throws ChatBotException {
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
					try {
						String response = taskList.summary();
						chat("Here you go!");
						System.out.println(response);
					} catch (Exception e) {
						handleError(e.getMessage());
					}
					break;
				case "mark":
				case "unmark":
					try {
						if (taskList.isEmpty()) {
							throw new ChatBotException(
								"Your task list is empty traveller! Add some tasks first before attempting to mark or unmark!"
							);
						} else if (input.length > 2) {
							throw new ChatBotException(
								"Thats too many inputs traveller! You only need to key in the index of the task you wish to mark or unmark!"
							);
						} else {
							String response = markOrUnmark(
								taskList,
								Integer.parseInt(input[1]) - 1,
								input[0].equals("mark")
							);
							chat(response);
						}
					} catch (ChatBotException e) {
						handleError(e.getMessage());
					} catch (NumberFormatException e) {
						handleError(
							"You should mark and unmark tasks using their index rather than title traveller!"
						);
					} catch (ArrayIndexOutOfBoundsException e) {
						handleError(
							"You need to key in the index of the task you wish to mark or unmark traveller!"
						);
					}

					break;
				case "todo":
					try {
						String response = taskList.addToDo(input);
						chat(response);
						printNumTasks(taskList.getNumTasks());
					} catch (ChatBotException e) {
						handleError(e.getMessage());
					}
					break;
				case "delete":
					try {
						if (taskList.isEmpty()) {
							throw new ChatBotException(
								"Your task list is empty traveller! Add some tasks first before attempting to delete!"
							);
						} else if (input.length > 2) {
							throw new ChatBotException(
								"Thats too many inputs traveller! You only need to key in the index of the task you wish to delete!"
							);
						} else {
							String response = taskList.delete(
								Integer.parseInt(input[1]) - 1
							);
							chat(response);
							printNumTasks(taskList.getNumTasks());
						}
					} catch (ChatBotException e) {
						handleError(e.getMessage());
					} catch (NumberFormatException e) {
						handleError(
							"You should delete tasks using their index rather than title traveller!"
						);
					} catch (ArrayIndexOutOfBoundsException e) {
						handleError(
							"You need to key in the index of the task you wish to delete traveller!"
						);
					}

					break;
				case "guide":
					chat(
						"Here is a list of commands that you can use traveller!"
					);
					for (int i = 0; i < GUIDE.length; i++) {
						System.out.println(
							String.format(
								"             %d. %s",
								i + 1,
								GUIDE[i]
							)
						);
					}
					System.out.println();
					break;
				default:
					String[] temp = rawInput.split("/");
					try {
						if (temp.length == 1) {
							String[] splitInput = temp[0].split(" ");
							String type = splitInput[0];
							if (temp[0].isBlank()) {
								throw new ChatBotException(
									"Don't be shy traveller! Type in a command and I will assist you!"
								);
							} else if (type.equals("deadline")) {
								if (splitInput.length == 1) {
									throw new ChatBotException(
										"You need to key in the title as well as due date and time of your deadline traveller!"
									);
								} else {
									throw new ChatBotException(
										"You need to include /by in your command to add a deadline traveller!"
									);
								}
							} else if (type.equals("event")) {
								if (splitInput.length == 1) {
									throw new ChatBotException(
										"You need to key in the title and timestamp of your event traveller!"
									);
								} else {
									throw new ChatBotException(
										"You need to include /at in your command to add an event traveller!"
									);
								}
							}
							throw new ChatBotException();
						} else if (temp.length > 2) {
							String type = temp[0].trim();
							if (type.equals("deadline")) {
								throw new ChatBotException(
									"The correct format for adding a deadline is deadline <name of task> /by <deadline of task>"
								);
							} else if (type.equals("event")) {
								throw new ChatBotException(
									"The correct format for adding an event is event <name of task> /at <timestamp of task>"
								);
							}
							throw new ChatBotException();
						} else {
							String response = taskList.add(
								temp[0].split(" "),
								temp[1].split(" ")
							);
							chat(response);
							printNumTasks(taskList.getNumTasks());
						}
					} catch (ChatBotException e) {
						handleError(e.getMessage());
					}
			}
		}
	}

	public static String markOrUnmark(
		TaskList taskList,
		int index,
		boolean mark
	)
		throws ChatBotException {
		if (taskList.isValidIndex(index).equals(true)) {
			if (mark) {
				return taskList.mark(index);
			} else {
				return taskList.unmark(index);
			}
		} else {
			throw new ChatBotException(
				"This is an invalid task index traveller! You can type list to check all task indexes!"
			);
		}
	}

	public static void greet() {
		System.out.println(BORDER + "\n");
		chat("Greetings, traveller!");
		// chat(getRandomGreetingQuote());
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

	public static void handleError(String errorMessage) {
		chat(errorMessage);
		chat("You can type guide for a list of valid commands to use!");
	}
}
