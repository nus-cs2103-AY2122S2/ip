package Duke;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

import enums.Command;
import enums.Type;
import exceptions.DukeException;
import exceptions.InvalidCommandException;

public class Duke {
	public static final String DUKE_PATHNAME = "src/main/java/Duke/data/duke.txt";
	public static ArrayList<String> inputsToBeProcessed = new ArrayList<>(100);
	public static ArrayList<Task> tasks = new ArrayList<>(100);
	public static int count = 0;

	public static void readAndAdd(String pathname) {
		try {
			File file = new File(pathname);
			Scanner fileScanner = new Scanner(file);

			while (fileScanner.hasNext()) {
				String line = fileScanner.nextLine();
				String[] tokens = line.split(" / ");
				String type = tokens[0];
				String status = tokens[1];
				String name = tokens[2];

				Type typeEnum = Type.valueOf(type.toUpperCase());

				if (type.equals("D") || type.equals("E")) {
					String date = tokens[3];
					addToList(typeEnum, status, name, date);
				} else {
					addToList(typeEnum, status, name);
				}
			}
		} catch (FileNotFoundException e) {
			System.out.println("\t____________________________________________________________");
			System.out.println("\t☹ Oops! The path you provided does not exist!");
			System.out.println("\t☹ I have created the file for you!");
			System.out.println("\t____________________________________________________________");
			File fileDir = new File("src/main/java/Duke/data");
			fileDir.mkdirs();
			File file = new File(fileDir, "duke.txt");
			try {
				FileWriter createdFile = new FileWriter(file);
			} catch (IOException e1) {
				System.out.println("File cannot be created");
			}
		}
	}

	public static void addToList(Type type, String status, String name) {
		if (type == Type.T) {
			inputsToBeProcessed.add("todo " + name);
			count++;
			if (status.equals("1")) {
				inputsToBeProcessed.add("mark " + count);
			}
		}
	}

	public static void addToList(Type type, String status, String name, String date) {
		switch (type) {
		case D:
			inputsToBeProcessed.add("deadline " + name + " /by " + date);
			count++;
			if (status.equals("1")) {
				inputsToBeProcessed.add("mark " + count);
			}
			break;
		case E:
			inputsToBeProcessed.add("event " + name + " /at " + date);
			count++;
			if (status.equals("1")) {
				inputsToBeProcessed.add("mark " + count);
			}
			break;
		default:
		}
	}

	public static void process(ArrayList<String> taskList) {

		for (String s : taskList) {
			String[] tokens = s.split(" ");
			String command = tokens[0];

			Command inputCommand = Command.valueOf(command.toUpperCase());

			int sizeOfInputArr = tokens.length;

			String name = "";
			for (int i = 1; i < sizeOfInputArr - 1; i++) {
				name = name.concat(tokens[i]);
				name = name.concat(" ");
			}
			name = name.concat(tokens[sizeOfInputArr - 1]); // to eliminate white space at the end

			switch (inputCommand) {
			case MARK:
				String markStr = tokens[1];
				int taskNumMark = Integer.parseInt(markStr) - 1;
				tasks.get(taskNumMark).mark();
				break;
			case UNMARK:
				String unmarkStr = tokens[1];
				int taskNumUnmark = Integer.parseInt(unmarkStr) - 1;
				tasks.get(taskNumUnmark).unmark();
				break;
			case TODO:
				Todo todo = new Todo(name);
				tasks.add(todo);
				break;
			case EVENT:
				String[] tokensEvent = s.split("/");
				String time = tokensEvent[1];

				String[] tokensNameEvent = name.split("/");
				String eventName = tokensNameEvent[0];
				Event event = new Event(eventName, time);
				tasks.add(event);
				break;
			case DEADLINE:
				String[] tokensDeadline = s.split("/");
				String date = tokensDeadline[1];

				String[] tokensNameDeadline = name.split("/");
				String deadlineName = tokensNameDeadline[0];
				Deadline deadline = new Deadline(deadlineName, date);
				tasks.add(deadline);
				break;
			}

		}
	}

	public static String simplify(ArrayList<Task> tasks) {
		String s = "";
		for (int i = 0; i < count; i++) {
			Task task = tasks.get(i);
			if (task instanceof Todo) {
				Todo todo = (Todo) task;
				s = s + "T / " + (todo.getStatus().equals("[X]") ? "1 / " : "0 / ") + todo.getName();
			} else if (task instanceof Event) {
				Event event = (Event) task;
				String[] timeTokens = event.getTime().split(" ", 2);
				s = s + "E / " + (event.getStatus().equals("[X]") ? "1 / " : "0 / ") + event.getName() + "/ "
						+ timeTokens[1];
			} else {
				Deadline deadline = (Deadline) task;
				String[] dateTokens = deadline.getDate().split(" ", 2);
				s = s + "D / " + (deadline.getStatus().equals("[X]") ? "1 / " : "0 / ") + deadline.getName()
						+ "/ " + dateTokens[1];
			}
			s += "\n";
		}
		return s;
	}

	public static void appendToFile(String pathname, String textToAppend) {
		try {
			FileWriter fw = new FileWriter(pathname);
			fw.write(textToAppend);
			fw.close();
		} catch (IOException e) {
			System.out.println("\t____________________________________________________________");
			System.out.println("\t Hmm.. The file cannot be written to. Try changing permissions.");
			System.out.println("\t____________________________________________________________");
		}
	}

	public static void main(String[] args) throws DukeException, IndexOutOfBoundsException, IllegalArgumentException {
		System.out.println("\t____________________________________________________________");
		System.out.println("\t Woof! I'm Wonka!\n\t How may I be of service?");
		System.out.println("\t____________________________________________________________");

		readAndAdd(DUKE_PATHNAME);
		process(inputsToBeProcessed);

		Scanner sc = new Scanner(System.in);

		boolean Running = true;

		while (Running) {
			String input = sc.nextLine();
			String[] tokens = input.split("\\s+");
			String command = tokens[0];

			try {
				Command inputCommand = Command.valueOf(command.toUpperCase());

				int sizeOfInputArr = tokens.length;

				String name = "";
				for (int i = 1; i < sizeOfInputArr - 1; i++) {
					name = name.concat(tokens[i]);
					name = name.concat(" ");
				}
				name = name.concat(tokens[sizeOfInputArr - 1]); // to eliminate white space at the end

				try {
					switch (inputCommand) {
					case BYE:
						System.out.println("\t Woof woof! Hope to see you again soon!");
						Running = false;
						break;
					case LIST:
						System.out.println("\t____________________________________________________________");
						System.out.println("\t Here are the tasks in your list:");
						for (int taskCount = 0; taskCount < count; taskCount++) {
							Task mainTask = tasks.get(taskCount);
							System.out.println("\t " + (taskCount + 1) + "." + mainTask.track()
									+ mainTask.getStatus() + " " + mainTask);
						}
						System.out.println("\t____________________________________________________________");
						break;
					case MARK:
						String markStr = tokens[1];
						int taskNumMark = Integer.parseInt(markStr) - 1;
						try {
							tasks.get(taskNumMark).mark();
							System.out.println("\t____________________________________________________________");
							System.out.println("\t Nice! I've marked this task as done:");
							System.out.println("\t\t" + tasks.get(taskNumMark).track()
									+ tasks.get(taskNumMark).getStatus() + " " + tasks.get(taskNumMark));
							System.out.println("\t____________________________________________________________");
						} catch (IndexOutOfBoundsException e) {
							System.out.println("\t____________________________________________________________");
							System.out.println("\t☹ Woof Woof!!! This task cannot be found with my Wonka eyes!!!");
							System.out.println("\t____________________________________________________________");
							continue;
						}
						break;
					case UNMARK:
						String unmarkStr = tokens[1];
						int taskNumUnmark = Integer.parseInt(unmarkStr) - 1;
						try {
							tasks.get(taskNumUnmark).unmark();
							System.out.println("\t____________________________________________________________");
							System.out.println("\t OK, I've marked this task as not done yet:");
							System.out.println("\t\t" + tasks.get(taskNumUnmark).track()
									+ tasks.get(taskNumUnmark).getStatus() + " " + tasks.get(taskNumUnmark));
							System.out.println("\t____________________________________________________________");
						} catch (IndexOutOfBoundsException e) {
							System.out.println("\t____________________________________________________________");
							System.out.println("\t☹ Woof Woof!!! This task cannot be found with my Wonka eyes!!!");
							System.out.println("\t____________________________________________________________");
							continue;
						}
						break;
					case TODO:
						Todo todo = new Todo(name);
						tasks.add(todo);
						System.out.println("\t____________________________________________________________");
						System.out.println("\t Got it. I've added this task:");
						System.out.println("\t\t " + todo.track() + todo.getStatus() + " " + todo);
						System.out.println("\t Now you have " + (count + 1) + " tasks in the list.");
						System.out.println("\t____________________________________________________________");
						count++;
						break;
					case EVENT:
						try {
							String[] tokensEvent = input.split("/at ");
							String time = tokensEvent[1];

							String[] tokensNameEvent = name.split("/");
							String eventName = tokensNameEvent[0];
							Event event = new Event(eventName, time);
							tasks.add(event);

							System.out.println("\t____________________________________________________________");
							System.out.println("\t Got it. I've added this task:");
							System.out.println("\t\t " + event.track() + event.getStatus() + " " + event);
							System.out.println("\t Now you have " + (count + 1) + " tasks in the list.");
							System.out.println("\t____________________________________________________________");
							count++;
						} catch (ArrayIndexOutOfBoundsException e) {
							System.out.println("\t____________________________________________________________");
							System.out.println("\t☹ Woof Woof!!! Please specify a date!!!");
							System.out.println("\t____________________________________________________________");
							continue;
						}
						break;
					case DEADLINE:
						try {
							String[] tokensDeadline = input.split("/by ");
							String date = tokensDeadline[1];

							String[] tokensNameDeadline = name.split("/");
							String deadlineName = tokensNameDeadline[0];
							Deadline deadline = new Deadline(deadlineName, date);

							tasks.add(deadline);

							System.out.println("\t____________________________________________________________");
							System.out.println("\t Got it. I've added this task:");
							System.out.println("\t\t " + deadline.track() + deadline.getStatus() + " " + deadline);
							System.out.println("\t Now you have " + (count + 1) + " tasks in the list.");
							System.out.println("\t____________________________________________________________");
							count++;
						} catch (ArrayIndexOutOfBoundsException e) {
							System.out.println("\t____________________________________________________________");
							System.out.println("\t☹ Please provide the date and time in the format YYYY-MM-DD " +
									"HHMM");
							System.out.println("\t____________________________________________________________");
							continue;
						}
						break;
					case DELETE:
						int toBeDeleted = Integer.parseInt(tokens[1]) - 1;

						if (tokens.length == 1) {
							throw new InvalidCommandException(
									"\t☹ Woof Woof!!! You haven't provided me a task number to delete!!!");
						}

						try {
							Task deleteTask = tasks.get(toBeDeleted);
							tasks.remove(toBeDeleted);
							count--;

							System.out.println("\t____________________________________________________________");
							System.out.println("\t Noted. I've removed this task:");
							System.out.println("\t\t " + deleteTask.track() + deleteTask.getStatus() + " "
									+ deleteTask);
							System.out.println("\t Now you have " + count + " tasks in the list.");
							System.out.println("\t____________________________________________________________");

						} catch (IndexOutOfBoundsException e) {
							System.out.println("\t____________________________________________________________");
							System.out.println("\t☹ Woof Woof!!! This task cannot be found with my Wonka eyes!!!");
							System.out.println("\t____________________________________________________________");
							continue;
						}
						break;
					default:
						throw new InvalidCommandException("\t☹ Woof Woof!!! This command is unidentifiable!!!");
					}
				} catch (DukeException e) {
					System.out.println("\t____________________________________________________________");
					System.out.println(e.getMessage());
					System.out.println("\t____________________________________________________________");
				}
			} catch (IllegalArgumentException e) {
				System.out.println("\t____________________________________________________________");
				System.out.println("\t ☹ Woof Woof!!! You haven't given me enough information for this action!!!");
				System.out.println("\t____________________________________________________________");
			}
		}
		sc.close();
		String textToAppend = simplify(tasks);
		appendToFile(DUKE_PATHNAME, textToAppend);
		tasks.clear();
	}
}
