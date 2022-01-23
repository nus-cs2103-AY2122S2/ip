package backend;

import java.util.ArrayList;

public class InputDecoder {
    private Commands currentCommand = null;

    public InputDecoder() {}

    private String[] parseInput(String input) throws IllegalArgumentException {
        String[] commandSections = input.split(" ", 2);
        currentCommand = Commands.valueOf(commandSections[0]);
        return commandSections;
    }

    public void decode(String input) {
        String[] commandSections = null;
        try {
            commandSections = this.parseInput(input);
        } catch (IllegalArgumentException e) {
            System.out.println("Sorry I didnt catch that! Please make sure it is a valid command!");
        }
        switch (currentCommand) {

            // list
            case list: {
                TaskList.list();
                break;
            }

            // mark
            case mark: {
                int indexMarked = Integer.parseInt(commandSections[1]) - 1;
                TaskList.mark(indexMarked);
                break;
            }

            // unmark
            case unmark: {
                int indexUnmmarked = Integer.parseInt(commandSections[1]) - 1;
                TaskList.unmark(indexUnmmarked);
                break;
            }
            case delete: {
                int indexDelete = Integer.parseInt(commandSections[1]) - 1;
                TaskList.delete(indexDelete);
                break;
            }

            // generate tasks
            case todo: {
                String description = commandSections[1];
                TaskList.todo(description);
                break;
            }

            case deadline: {
                String actualTask = commandSections[1];
                String[] segments = actualTask.split(" /by ");
                String description = segments[0];
                String time = segments[1];
                TaskList.deadline(description,time);
                break;
            }

            case event: {
                String actualTask = commandSections[1];
                String[] segments = actualTask.split(" /at ");
                String description = segments[0];
                String time = segments[1];
                TaskList.event(description,time);
            }
            default: {
                System.out.println("Sorry I didnt understand you!");
            }
        }
    }
}