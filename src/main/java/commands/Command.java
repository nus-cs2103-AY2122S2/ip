package commands;

import tasks.Task;

import java.util.ArrayList;

public abstract class Command {
	public final static String INDENT = "     ";

	public abstract void execute();

	public abstract boolean ends();

	public abstract ArrayList<Task> getList();

	public static void printFormatted(String[] msg) {

		for (int i = 0; i < msg.length; i++) {
			if (msg[i] != null) {
				System.out.println(INDENT + msg[i]);
			}
		}
	}
}
