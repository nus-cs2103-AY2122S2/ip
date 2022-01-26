import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

import storage.Storage;
import ui.Ui;
import parser.Parser;
import exceptions.*;
import commands.*;
import tasks.*;

public class Duke {
//	private static ArrayList<Task> TASKLIST = new ArrayList<>();
	private TaskList tasks;
	private static Storage storage;
	private static Ui ui;

	public Duke(String filePath) {
		storage = new Storage(filePath);
		ui = new Ui();

		try {
			tasks = storage.load();
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
				ui.printLine();
				cmd = Parser.processInput(input);
				cmd.execute(tasks, ui, storage);
				tasks = cmd.getList();
				if (cmd.ends()) {
					break;
				}
			} catch (DukeException e) {
				ui.showError(e.getMessage());
			} finally {
				ui.printLine();
			}
		}
	}

	public static void main(String[] args) {
		new Duke("data/tasks.txt").run();
	}
}
