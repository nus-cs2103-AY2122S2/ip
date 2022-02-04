import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.FileNotFoundException;

public class Duke {
    public static void main(String[] args) {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|";
        System.out.println(logo);

        System.out.println(output("Hello! I'm Duke by A0221330A.\n    What can I do for you?"));

        ArrayList<Task> list = new ArrayList<>();

        String FILE_PATH = "data/TaskList.txt";
        File file = new File(FILE_PATH);
        try {
                Scanner sc = new Scanner(file);
                while (sc.hasNextLine()) {
                    List<String> input = Arrays.asList(sc.nextLine().split(" \\| "));
                    switch (input.get(0)) {
                        case "T":
                            ToDo newTodo = new ToDo(input.get(2).trim());
                            list.add(newTodo);
                            if (input.get(1).equals("1")) {
                                list.set(list.indexOf(newTodo), newTodo.mark());
                            }
                            break;
                        case "D":
                            Deadline newDeadline = new Deadline(input.get(2).trim(), input.get(3).trim());
                            list.add(newDeadline);
                            if (input.get(1).equals("1")) {
                                list.set(list.indexOf(newDeadline), newDeadline.mark());
                            }
                            break;
                        case "E":
                            Event newEvent = new Event(input.get(2).trim(), input.get(3).trim());
                            list.add(newEvent);
                            if (input.get(1).equals("1")) {
                                list.set(list.indexOf(newEvent), newEvent.mark());
                            }
                            break;
                    }
                }
                sc.close();
        } catch (FileNotFoundException e) {
            if (file.getParentFile().mkdirs()){
                try {
                    if (file.createNewFile()) {
                        System.out.println(output("Created new data file"));
                    }
                } catch (IOException err) {
                    System.out.println(output("Error in loading file"));
                }
            }
        }

        boolean exit = false;
        while (!exit) {
            Scanner sc = new Scanner(System.in);
            while (!exit && sc.hasNextLine()) {
                List<String> input = Arrays.asList(sc.nextLine().split(" "));
                switch (input.get(0)) {
                    case "bye":
                        System.out.println(output("Bye. Hope to see you again soon!"));
                        exit = true;
                        break;
                    case "list":
                        StringBuilder text = new StringBuilder();
                        text.append("Here are the tasks in your list:\n");
                        for (int i = 0; i < list.size(); i++) {
                            text.append("    ").append(i + 1).append(". ")
                                    .append(list.get(i))
                                    .append("\n");
                        }
                        text.delete(text.length() - 1, text.length());
                        System.out.println(output(text.toString()));
                        break;
                    case "mark":
                        try {
                            int markIndex = Integer.parseInt(input.get(1)) - 1;
                            list.set(markIndex, list.get(markIndex).mark());
                            saveTaskList(list);
                            System.out.println(output("Nice! I've marked this task as done:\n        "
                                    + list.get(markIndex)));
                        } catch (IndexOutOfBoundsException | NumberFormatException ex) {
                            DukeException exception = new DukeException("OOPS!!! invalid index.");
                            System.out.println(output(exception.toString()));
                        }
                        break;
                    case "unmark":
                        try {
                            int unmarkIndex = Integer.parseInt(input.get(1)) - 1;
                            list.set(unmarkIndex, list.get(unmarkIndex).unmark());
                            saveTaskList(list);
                            System.out.println(output("OK, I've marked this task as not done yet:\n        "
                                    + list.get(unmarkIndex)));
                        } catch (IndexOutOfBoundsException | NumberFormatException ex) {
                            DukeException exception = new DukeException("OOPS!!! invalid index.");
                            System.out.println(output(exception.toString()));
                        }
                        break;
                    case "todo":
                        StringBuilder todo = new StringBuilder();
                        if (input.size() == 1) {
                            DukeException exception = new DukeException("OOPS!!! The description of a todo cannot be empty.");
                            System.out.println(output(exception.toString()));
                        } else {
                            for (int i = 1; i < input.size(); i++) {
                                todo.append(input.get(i)).append(" ");
                            }
                            list.add(new ToDo(todo.toString()));
                            saveTaskList(list);
                            System.out.println(output("Got it. I've added this task: \n        "
                                    + list.get(list.size() - 1) + "\n    Now you have " + list.size() + " tasks in the list."));
                        }
                        break;

                    case "deadline":
                        StringBuilder deadline = new StringBuilder();
                        StringBuilder deadlineBy = new StringBuilder();
                        int byIndex = input.indexOf("/by");
                        if (input.size() == 1) {
                            DukeException exception = new DukeException("OOPS!!! The description of a deadline cannot be empty.");
                            System.out.println(output(exception.toString()));
                        } else if (byIndex == -1) {
                            DukeException exception = new DukeException("OOPS!!! The datetime of a deadline cannot be empty.");
                            System.out.println(output(exception.toString()));
                        } else {
                            for (int i = 1; i < byIndex; i++) {
                                deadline.append(input.get(i)).append(" ");
                            }
                            for (int i = byIndex + 1; i < input.size(); i++) {
                                deadlineBy.append(input.get(i)).append(" ");
                            }
                            list.add(new Deadline(deadline.toString(), deadlineBy.toString()));
                            saveTaskList(list);
                            System.out.println(output("Got it. I've added this task: \n        "
                                    + list.get(list.size() - 1) + "\n    Now you have " + list.size() + " tasks in the list."));
                        }
                        break;
                    case "event":
                        int atIndex = input.indexOf("/at");
                        if (input.size() == 1) {
                            DukeException exception = new DukeException("OOPS!!! The description of a event cannot be empty.");
                            System.out.println(output(exception.toString()));
                        } else if (atIndex == -1) {
                            DukeException exception = new DukeException("OOPS!!! The datetime of a event cannot be empty.");
                            System.out.println(output(exception.toString()));
                        } else {
                            StringBuilder event = new StringBuilder();
                            for (int i = 1; i < atIndex; i++) {
                                event.append(input.get(i)).append(" ");
                            }
                            StringBuilder eventAt = new StringBuilder();
                            for (int i = atIndex + 1; i < input.size(); i++) {
                                eventAt.append(input.get(i)).append(" ");
                            }
                            list.add(new Event(event.toString(), eventAt.toString()));
                            saveTaskList(list);
                            System.out.println(output("Got it. I've added this task: \n        "
                                    + list.get(list.size() - 1) + "\n    Now you have " + list.size() + " tasks in the list."));
                        }
                        break;
                    case "delete":
                        try {
                            int deleteIndex = Integer.parseInt(input.get(1)) - 1;
                            Task delete = list.get(deleteIndex);
                            list.remove(deleteIndex);
                            saveTaskList(list);
                            System.out.println(output("Noted. I've removed this task: \n        "
                                    + delete + "\n    Now you have " + list.size() + " tasks in the list."));
                        } catch (IndexOutOfBoundsException | NumberFormatException ex) {
                            DukeException exception = new DukeException("OOPS!!! invalid index.");
                            System.out.println(output(exception.toString()));
                        }
                        break;
                    default:
                        DukeException exception = new DukeException("OOPS!!! I'm sorry, but I don't know what that means :-(");
                        System.out.println(output(exception.toString()));
                        break;
                }
            }
        }
    }

    public static String output(String text) {
        String line = "    ____________________________________________________________";
        return line + "\n    " + text + "\n" + line;
    }

    public static void saveTaskList(ArrayList<Task> list) {
        String FILE_PATH = "data/TaskList.txt";
        try {
            FileWriter writer = new FileWriter(FILE_PATH);
            for (Task task : list) {
                writer.write(task.saveData());
                writer.write(System.lineSeparator());
            }
            writer.close();
        } catch (IOException e) {
            System.out.println("Error happened cannot save to file");
        }
    }
}
