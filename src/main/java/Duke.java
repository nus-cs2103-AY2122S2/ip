import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

import Exceptions.*;
import commands.*;
import tasks.*;

public class Duke {
	private static ArrayList<Task> TASKLIST = new ArrayList<>();
	private static Storage storage;
	private static Ui ui;
	public Duke(String filePath) {
		storage = new Storage(filePath);
		ui = new Ui();

		try {
			TASKLIST = storage.load();
		} catch (IOException e) {
			System.out.println("An error occurred");
			e.printStackTrace();
		}
	}

	public void run(){
		//intro messages
		ui.welcomeMessage();
		Scanner sc = new Scanner(System.in);
		Command cmd;
		//main program loop
		while (true) {
			try {
				String input = sc.nextLine();
				cmd = processInput(input);
				cmd.execute();
				if (cmd.ends()) {
					break;
				}
			} catch (DukeException e) {
				e.printError();
			}
		}

		try{
			storage.saveFile(TASKLIST);
		} catch (IOException e) {
			System.out.println("An error occurred");
			e.printStackTrace();
		}
	}

	public static void main(String[] args) {
		new Duke("data/tasks.txt").run();
	}

	private static Command processInput(String input) throws DukeException {
		Command cmd = null;
		String[] inputSplit = input.split(" ", 2);
		String commandType = inputSplit[0].toLowerCase();
		switch (commandType) {
		case "bye":
			cmd = new ByeCommand();
			break;
		case "list":
			cmd = new ListCommand(TASKLIST);
			break;
		case "mark":
		case "unmark":
		case "delete":
			String detail = inputSplit[1];
			if (!isInteger(detail)) {
				throw new IncorrectValueException();
			}
			int inputVal = Integer.parseInt(detail);
			if (inputVal > TASKLIST.size()) {
				throw new OutOfRangeException();
			}
			switch (commandType) {
			case "mark":
				cmd = new MarkCommand(TASKLIST, inputVal);
				break;
			case "unmark":
				cmd = new UnMarkCommand(TASKLIST, inputVal);
				break;
			case "delete":
				cmd = new DeleteCommand(TASKLIST, inputVal);
				TASKLIST = cmd.getList();
				break;
			}
			break;
		case "todo":
		case "event":
		case "deadline":
			if (inputSplit.length < 2) {
				throw new EmptyDescriptionException(commandType);
			}
			cmd = new AddCommand(TASKLIST, commandType, inputSplit[1]);
			TASKLIST = cmd.getList();
			break;
		default:
			throw new UnknownCommandException();
		}
		return cmd;
	}

	private static boolean isInteger(String input) {
		for (int i = 0; i < input.length(); i++) {
			if (!Character.isDigit(input.charAt(i))) {
				return false;
			}
		}
		return true;
	}
}
