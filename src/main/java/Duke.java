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
	public Duke(String filePath) {
		storage = new Storage(filePath);
		try {
			TASKLIST = storage.load();
		} catch (IOException e) {
			System.out.println("An error occurred");
			e.printStackTrace();
		}
	}

	public static void main(String[] args) {
		new Duke("data/tasks.txt");
		//intro messages
		Scanner sc = new Scanner(System.in);
		String logo = " ____        _        \n"
				+ "|  _ \\ _   _| | _____ \n"
				+ "| | | | | | | |/ / _ \\\n"
				+ "| |_| | |_| |   <  __/\n"
				+ "|____/ \\__,_|_|\\_\\___|\n";
		System.out.println("Hello from\n" + logo);
		Command cmd = new WelcomeCommand();
		cmd.execute();

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

//	private static void saveFile() throws IOException {
//		FileWriter fw = new FileWriter("data/tasks.txt");
//		for (int i =0; i < TASKLIST.size(); i++){
//			Task t = TASKLIST.get(i);
//			String type = t.getType();
//			String mark = t.getMark();
//			String detail = t.getDetail();
//			switch (type){
//			case"T":
//				fw.write(type + " | " +  mark + " | " + detail + "\n");
//				break;
//			case"E":
//			case"D":
//				String date = t.getDate();
//				fw.write(type + " | "  + mark + " | " + detail + " | " + date + "\n");
//				break;
//			}
//		}
//		fw.close();
//	}

	private static boolean isInteger(String input) {
		for (int i = 0; i < input.length(); i++) {
			if (!Character.isDigit(input.charAt(i))) {
				return false;
			}
		}
		return true;
	}
}
