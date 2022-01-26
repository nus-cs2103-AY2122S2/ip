package Duke;

import java.util.ArrayList;
import java.util.Scanner;

import enums.Command;
import exceptions.DukeException;
import exceptions.InvalidCommandException;

public class Duke {
	public static void main(String[] args) throws DukeException, IndexOutOfBoundsException, IllegalArgumentException {
		System.out.println("\t____________________________________________________________");
		System.out.println("\t Woof! I'm Wonka!\n\t How may I be of service?");
		System.out.println("\t____________________________________________________________");

		Save.load();
		ArrayList<Task> tasks = Save.tasks;
		int count = Save.count;

		Scanner sc = new Scanner(System.in);

		boolean Running = true;

		while (Running) {
			String input = sc.nextLine();
			String[] tokens = input.split(" ");
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
		Save.save();
		tasks.clear();
	}
}
