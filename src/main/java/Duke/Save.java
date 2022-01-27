package Duke;

import enums.Command;
import enums.Type;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class Save {
	private static final String DUKE_PATHNAME = "src/main/java/Duke/data/duke.txt";
	private ArrayList<String> inputsToBeProcessed = new ArrayList<>(100);
	private ArrayList<Task> tasks = new ArrayList<>(100);
	private int count;

	Save() {
		load();
	}

	public void load() {
		readAndAdd();
		process(inputsToBeProcessed);
	}

	public void save() {
		try {
			FileWriter fw = new FileWriter(DUKE_PATHNAME);
			String textToWrite = simplify();
			fw.write(textToWrite);
			fw.close();
		} catch (IOException e) {
			System.out.println("\t____________________________________________________________");
			System.out.println("\t Hmm.. The file cannot be written to. Try changing permissions.");
			System.out.println("\t____________________________________________________________");
		}
	}

	public void readAndAdd() {
		try {
			File file = new File(DUKE_PATHNAME);
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
			File fileToCreate = new File(fileDir, "duke.txt");
			try {
				FileWriter createdFile = new FileWriter(fileToCreate);
			} catch (IOException e1) {
				System.out.println("File cannot be created");
			}
		}
	}

	public void addToList(Type type, String status, String name) {
		if (type == Type.T) {
			inputsToBeProcessed.add("todo " + name);
			this.count++;
			if (status.equals("1")) {
				inputsToBeProcessed.add("mark " + this.count);
			}
		}
	}

	public void addToList(Type type, String status, String name, String date) {
		switch (type) {
		case D:
			inputsToBeProcessed.add("deadline " + name + " /by " + date);
			this.count++;
			if (status.equals("1")) {
				inputsToBeProcessed.add("mark " + this.count);
			}
			break;
		case E:
			inputsToBeProcessed.add("event " + name + " /at " + date);
			this.count++;
			if (status.equals("1")) {
				inputsToBeProcessed.add("mark " + this.count);
			}
			break;
		default:
		}
	}

	public void process(ArrayList<String> taskList) {

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
				this.tasks.get(taskNumMark).mark();
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
				String[] tokensEvent = s.split("/at ");
				String time = tokensEvent[1];

				String[] tokensNameEvent = name.split("/");
				String eventName = tokensNameEvent[0];
				Event event = new Event(eventName, time);
				tasks.add(event);
				break;
			case DEADLINE:
				String[] tokensDeadline = s.split("/by ");
				String date = tokensDeadline[1];

				String[] tokensNameDeadline = name.split("/");
				String deadlineName = tokensNameDeadline[0];
				Deadline deadline = new Deadline(deadlineName, date);
				tasks.add(deadline);
				break;
			}

		}
	}

	public String simplify() {
		String s = "";
		for (int i = 0; i < this.tasks.size(); i++) {
			Task task = this.tasks.get(i);
			if (task instanceof Todo) {
				Todo todo = (Todo) task;
				s = s + "T / " + (todo.getStatus().equals("[X]") ? "1 / " : "0 / ") + todo.getName();
			} else if (task instanceof Event) {
				Event event = (Event) task;
				s = s + "E / " + (event.getStatus().equals("[X]") ? "1 / " : "0 / ") + event.getName() + "/ "
						+ event.getTime();
			} else {
				Deadline deadline = (Deadline) task;
				s = s + "D / " + (deadline.getStatus().equals("[X]") ? "1 / " : "0 / ") + deadline.getName()
						+ "/ " + deadline.getUnconvertedDate();
			}
			s += "\n";
		}
		return s;
	}

	public ArrayList<Task> savedTasks() {
		return this.tasks;
	}
}
