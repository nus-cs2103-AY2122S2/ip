import java.io.*;
import java.util.ArrayList;
import java.util.Scanner;

public class Duke {
    public static final String botName = "duke";

    public enum Command {
        BYE,
        LIST,
        MARK,
        UNMARK,
        DELETE,
        DEADLINE,
        EVENT,
        TODO;

        public boolean equals(String input) {
            if (input.equalsIgnoreCase(this.name())) {
                return true;
            } else {
                return false;
            }
        }
    }

    public static void saveAsTextFile(ArrayList<Task> tasks) {
        System.out.println();
        String filename = botName + ".txt";
        String path = "./data";
        File file = new File(path, filename);
        System.out.println(file.toString());
        BufferedWriter bufferedWriter = null;
        try {
            if (!file.exists()) {
                file.createNewFile();
            }
            FileWriter fileWriter = new FileWriter(file);
            bufferedWriter = new BufferedWriter(fileWriter);
            for (Task task : tasks) {
                bufferedWriter.write(task.toString() + "\n");
            }
            bufferedWriter.close();
        } catch (FileNotFoundException e) {
            System.out.println("    File error: not found");
        } catch (IOException e) {
            e.printStackTrace();
            System.out.println("    Error: cannot save file");
        }
    }

    public static void addTask(ArrayList<Task> taskList, Task task, Command command, String[] inputArray) {
        // divided in case I need to add other features like parsing date
        if (command.equals(Command.DEADLINE)) {
            taskList.add(task);
            Printer.echoForAdd(task, taskList.size());
            saveAsTextFile(taskList);
        } else if (command.equals(Command.EVENT)) {
            taskList.add(task);
            Printer.echoForAdd(task, taskList.size());
            saveAsTextFile(taskList);
        } else if (command.equals(Command.TODO)) {
            taskList.add(task);
            Printer.echoForAdd(task, taskList.size());
            saveAsTextFile(taskList);
        } else {
            try {
                throw new DukeCommandException(command.name());
            } catch (DukeCommandException e) {
                System.out.println(e.toString());
            }
        }
    }

    public static void removeTask(ArrayList<Task> taskList, String[] inputArray) {
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
                        taskList.remove(position - 1);
                        Printer.echoForDelete(taskList.get(position - 1), taskList.size());
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
    }

    public static void main(String[] args) {
        Printer.printDivider();
        System.out.println("    Hello, I'm " + botName + ".");
        System.out.println("    What can I do for you?");
        Printer.printDivider();
        Scanner inputScanner = new Scanner(System.in);
        String input = "";
        ArrayList<Task> taskList = new ArrayList<>();
        while (!Command.BYE.equals(input)) {
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
                if (Command.LIST.equals(firstArg)) {
                    Printer.printTodo(taskList);
                } else if (Command.BYE.equals(firstArg)) {
                    Printer.printEndMessage();
                } else if (Command.DELETE.equals(firstArg)) {
                    try {
                        throw new DukeMissingArgumentException("index");
                    } catch (DukeMissingArgumentException e) {
                        System.out.println(e);
                    }
                } else if (Command.DEADLINE.equals(firstArg) || Command.EVENT.equals(firstArg) || Command.TODO.equals(firstArg)) {
                    try {
                        throw new DukeMissingArgumentException("task description");
                    } catch (DukeMissingArgumentException e) {
                        System.out.println(e);
                    }
                } else if (Command.MARK.equals(firstArg)) {
                    try {
                        throw new DukeMissingArgumentException("index");
                    } catch (DukeMissingArgumentException e) {
                        System.out.println(e);
                    }
                } else if (Command.UNMARK.equals(firstArg)) {
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
                if (Command.MARK.equals(firstArg)) {
                    taskList.get(Integer.parseInt(inputArray[1]) - 1).mark(taskList);
                } else if (Command.UNMARK.equals(firstArg)) {
                    taskList.get(Integer.parseInt(inputArray[1]) - 1).unmark(taskList);
                } else if (Command.DELETE.equals(firstArg)) {
                    removeTask(taskList, inputArray);
                } else if (Command.DEADLINE.equals(firstArg)) {
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
                        addTask(taskList, taskObj, Command.DEADLINE, inputArray);
                    }
                } else if (Command.EVENT.equals(firstArg)) {
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
                        addTask(taskList, taskObj, Command.DEADLINE, inputArray);
                    }
                } else if (Command.TODO.equals(firstArg)) {
                    String content = input.substring(firstArg.length() + 1);
                    Task taskObj = new ToDo(content);
                    addTask(taskList, taskObj, Command.DEADLINE, inputArray);
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
