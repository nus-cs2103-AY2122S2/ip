import java.util.ArrayList;
import java.util.Scanner;

public class Duke {
    public static void main(String[] args) throws DukeException {
        String botName = "Duke";
        Printer.printDivider();
        System.out.println("    Hello, I'm " + botName + ".");
        System.out.println("    What can I do for you?");
        Printer.printDivider();
        Scanner inputScanner = new Scanner(System.in);
        String input = "";
        ArrayList<Task> taskList = new ArrayList<>();
        while (!input.equals("bye")) {
            input = inputScanner.nextLine();
            int firstSpaceIndex = input.indexOf(" ");
            String firstArg = "";
            if (firstSpaceIndex != -1) { // input is more than one word
                firstArg = input.substring(0, firstSpaceIndex);
            } else { // input is a single word
                firstArg = input;
            }

            String[] inputArray = input.split(" ");
            if (input.length() < 1) {
                try {
                    throw new DukeCommandException(firstArg);
                } catch (DukeCommandException e) {
                    System.out.println(e);
                }
            } else if (inputArray.length == 1) {
                if (firstArg.equals("list")) {
                    Printer.printTodo(taskList);
                } else if (firstArg.equals("bye")) {
                    Printer.printEndMessage();
                } else if (firstArg.equals("delete")) {
                    try {
                        throw new DukeMissingArgumentException("index");
                    } catch (DukeMissingArgumentException e) {
                        System.out.println(e);
                    }
                } else if (firstArg.equals("deadline") || firstArg.equals("event") || firstArg.equals("todo")) {
                    try {
                        throw new DukeMissingArgumentException("task description");
                    } catch (DukeMissingArgumentException e) {
                        System.out.println(e);
                    }
                } else if (firstArg.equals("mark")) {
                    try {
                        throw new DukeMissingArgumentException("index");
                    } catch (DukeMissingArgumentException e) {
                        System.out.println(e);
                    }
                } else if (firstArg.equals("unmark")) {
                    try {
                        throw new DukeMissingArgumentException("index");
                    } catch (DukeMissingArgumentException e) {
                        System.out.println(e);
                    }
                } else {
                    try {
                        throw new DukeCommandException(firstArg);
                    } catch (DukeCommandException e) {
                        System.out.println(e);
                    }
                }
            } else {
                if (firstArg.equals("mark")) {
                    taskList.get(Integer.parseInt(inputArray[1]) - 1).mark();
                } else if (firstArg.equals("unmark")) {
                    taskList.get(Integer.parseInt(inputArray[1]) - 1).unmark();
                } else if (firstArg.equals("delete")) {
                    if (inputArray.length > 2) {
                        try {
                            throw new DukeInvalidArgumentException("Too many arguments!");
                        } catch (DukeInvalidArgumentException e) {
                            System.out.println(e);
                        }
                    } else {
                        if (taskList.size() == 0) {
                            Printer.printDivider();
                            System.out.println("    No tasks are in the list right now!");
                            Printer.printDivider();
                        } else {
                            try {
                                int position = Integer.parseInt(inputArray[1]);
                                if (position > 0 && position <= taskList.size()) {
                                    Printer.echoForDelete(taskList.get(position - 1), taskList.size());
                                    taskList.remove(position - 1);
                                } else {
                                    try {
                                        throw new DukeInvalidArgumentException("Invalid task number");
                                    } catch (DukeInvalidArgumentException e) {
                                        System.out.println(e);
                                    }
                                }
                            } catch (NumberFormatException e) {
                                System.out.println("Second argument of delete must be a number!");
                            }
                        }
                    }

                } else if (firstArg.equals("deadline")) {
                    int indexOfBy = input.indexOf("\\by ");
                    if (indexOfBy == -1) {
                        try {
                            throw new DukeMissingArgumentException("\\by deadlineTime");
                        } catch (DukeMissingArgumentException e) {
                            System.out.println(e);
                        }
                    } else {
                        String content = input.substring(firstArg.length() + 1, indexOfBy - 1);
                        String by = input.substring(indexOfBy + 4);
                        Task taskObj = new Deadline(content, by);
                        Printer.echoForAdd(taskObj, taskList.size());
                        taskList.add(taskObj);
                    }
                } else if (firstArg.equals("event")) {
                    int indexOfAt = input.indexOf("\\at ");
                    if (indexOfAt == -1) {
                        try {
                            throw new DukeMissingArgumentException("\\at eventTime");
                        } catch (DukeMissingArgumentException e) {
                            System.out.println(e);
                        }
                    } else {
                        String content = input.substring(firstArg.length() + 1, indexOfAt - 1);
                        String at = input.substring(indexOfAt + 4);
                        Task taskObj = new Event(content, at);
                        Printer.echoForAdd(taskObj, taskList.size());
                        taskList.add(taskObj);
                    }
                } else if (firstArg.equals("todo")) {
                    String content = input.substring(firstArg.length() + 1);
                    Task taskObj = new ToDo(content);
                    Printer.echoForAdd(taskObj, taskList.size());
                    taskList.add(taskObj);
                } else {
                    try {
                        throw new DukeCommandException(firstArg);
                    } catch (DukeCommandException e) {
                        System.out.println(e);
                    }
                }
            }
        }
    }
}
