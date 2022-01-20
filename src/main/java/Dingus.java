import java.util.Scanner;
import java.util.ArrayList;
import java.util.List;

public class Dingus {
	public static String startLine = "\n--------------------------------------------------------------------";
	public static String endLine = "--------------------------------------------------------------------\n";
	public static String greeting = "\nDingus:	Oh it's you again...\nDingus:	What kind of trouble would you inconvenience me with this time?\n";
	public static List<Task> tasks = new ArrayList<>();

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		// Greet
		System.out.println(startLine + greeting + endLine);

		while (sc.hasNext()) {
			String input = sc.nextLine().toLowerCase();

			try {
				response(input);
			} catch (DingusException e) {
				System.out.println(startLine);
				System.out.println("<ERROR> " + e);
				System.out.println(endLine);
			}

			if (input.equals("bye")) {
				break;
			}
		}
		sc.close();
	}

	public static void response(String input) throws DingusException {
		String[] words = input.split(" ");
		Command command = Command.valueOf(words[0].toLowerCase());

		switch (command) {
			case bye:
				System.out.println(startLine);
				System.out.println("DINGUS:	Please don't ever bother me again, bye");
				System.out.println(endLine);
				break;
			case list:
				// Output List
				String output = startLine + "\nHere are the tasks in your list:";
				for (int i = 0; i < tasks.size(); i++) {
					int numbering = i + 1;
					output += "\n" + numbering + ". " + tasks.get(i);
				}
				output += "\n" + endLine;
				System.out.println(output);
				break;
			case todo:
				todo(input);
				break;
			case deadline:
				deadline(input);
				break;
			case event:
				event(input);
				break;
			case delete:
				delete(input);
				break;
			case mark:
				mark(input);
				break;
			case unmark:
				unmark(input);
				break;
			default:
				throw new DingusException("What kind of command is that?? I don't understand!");
		}
	}

	public static void deadline(String input) throws DingusException {
		// Adding Deadline
		String[] deadline = input.split("/");
		String description = deadline[0].substring(8);
		if (description.isEmpty()) {
			throw new DingusException("Is it even possible to have a deadline for NOTHING?");
		} else if (deadline.length == 1) {
			throw new DingusException("Is it even possible to have a deadline with no END DATE?");
		} else {
			Task currTask = new Deadline(description, deadline[1]);
			tasks.add(currTask);
			System.out.println(startLine);
			System.out.println("Stop troubling me, I've alread added this task:");
			System.out.println(currTask);
			System.out.println((tasks.size() > 1) ? "Can you even finish " + tasks.size() + " tasks?"
					: "Can you even finish " + tasks.size() + " task?");
			System.out.println(endLine);
		}
	}

	public static void todo(String input) throws DingusException {
		// Adding Todo
		String currTask = input.substring(4);
		Task curr = new Todo(currTask);
		if (currTask.isEmpty()) {
			throw new DingusException("So doing NOTHING is a task?");
		} else {
			tasks.add(curr);
		}
		System.out.println(startLine);
		System.out.println("Stop troubling me, I've already added this task:");
		System.out.println(curr);
		System.out.println((tasks.size() > 1) ? "Can you even finish " + tasks.size() + " tasks?"
				: "Can you even finish " + tasks.size() + " task?");
		System.out.println(endLine);
	}

	public static void event(String input) throws DingusException {
		// Adding Event
		String[] event = input.split("/");
		String description = event[0].substring(5);
		if (description.isEmpty()) {
			throw new DingusException("Is it even possible to have an event for NOTHING?");
		} else if (event.length == 1) {
			throw new DingusException("Is it even possible to have an event with no DATE?");
		} else {
			Task currTask = new Deadline(description, event[1]);
			tasks.add(currTask);
			System.out.println(startLine);
			System.out.println("Stop troubling me, I've already added this task:");
			System.out.println(currTask);
			System.out.println((tasks.size() > 1) ? "Can you even finish " + tasks.size() + " tasks?"
					: "Can you even finish " + tasks.size() + " task?");
			System.out.println(endLine);
		}
	}

	public static void delete(String input) throws DingusException {
		String[] info = input.split(" ");
		if (info.length == 1) {
			throw new DingusException("How am I suppose to delete something without knowing which task?");
		}

		try {
			Integer.parseInt(info[1]);
		} catch (NumberFormatException e) {
			throw new DingusException("Are you incapable of understanding what's an integer?");
		}

		if (Integer.parseInt(info[1]) > tasks.size() || Integer.parseInt(info[1]) < 0) {
			throw new DingusException("Can't you count? How am I supposed to delete something that doesn't exist?");
		} else {
			int pos = Integer.parseInt(info[1]) - 1;
			System.out.println(startLine);
			System.out.println("I've already deleted for you! You're welcome.");
			System.out.println(tasks.get(pos));
			tasks.remove(pos);
			System.out.println((tasks.size() > 1) ? "You have " + tasks.size() + " tasks left!"
					: "You have " + tasks.size() + " task left!");
			System.out.println(endLine);
		}

	}

	public static void mark(String input) throws DingusException {
		String[] info = input.split(" ");
		if (info.length == 1) {
			throw new DingusException("How am I suppose to mark something without knowing which task?");
		}

		try {
			Integer.parseInt(info[1]);
		} catch (NumberFormatException e) {
			throw new DingusException("Are you incapable of understanding what's an integer?");
		}

		if (Integer.parseInt(info[1]) > tasks.size() || Integer.parseInt(info[1]) < 0) {
			throw new DingusException("Can't you count? How am I supposed to mark something that doesn't exist?");
		} else {
			int pos = Integer.parseInt(info[1]) - 1;
			if (tasks.get(pos).isDone) {
				throw new DingusException("Your task has been marked before...");
			}
			tasks.get(pos).mark();
			System.out.println(startLine);
			System.out.println("Wasn't expecting you to finish that task, already marked for you!");
			System.out.println(tasks.get(pos));
			System.out.println((tasks.size() > 1) ? "You have " + tasks.size() + " tasks left!"
					: "You have " + tasks.size() + " task left!");
			System.out.println(endLine);
		}

	}

	public static void unmark(String input) throws DingusException {
		String[] info = input.split(" ");
		if (info.length == 1) {
			throw new DingusException("How am I suppose to unmark something without knowing which task?");
		}

		try {
			Integer.parseInt(info[1]);
		} catch (NumberFormatException e) {
			throw new DingusException("Are you incapable of understanding what's an integer?");
		}

		if (Integer.parseInt(info[1]) > tasks.size() || Integer.parseInt(info[1]) < 0) {
			throw new DingusException("Can't you count? How am I supposed to unmark something that doesn't exist?");
		} else {
			int pos = Integer.parseInt(info[1]) - 1;
			if (!tasks.get(pos).isDone) {
				throw new DingusException("Your task is already unmarked, why unmark it again...");
			}
			tasks.get(pos).unmark();
			System.out.println(startLine);
			System.out.println("Knew that you didnt't finish that task, already unmarked it for you!");
			System.out.println(tasks.get(pos));
			System.out.println((tasks.size() > 1) ? "You have " + tasks.size() + " tasks left!"
					: "You have " + tasks.size() + " task left!");
			System.out.println(endLine);
		}

	}
}
