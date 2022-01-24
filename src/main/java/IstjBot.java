import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

import java.time.LocalDate;
import java.time.format.DateTimeParseException;

import java.util.Scanner;
import java.util.ArrayList;

public class IstjBot {
    private static boolean doneChatting = false;
    private static final ArrayList<Task> tasks = new ArrayList<>();
    private static final StringBuilder list =
            new StringBuilder("As an IstjBot, I present you the task(s) in your list:");
    private static final StringBuilder searchList =
            new StringBuilder("As an IstjBot, I present you the task(s) with that date.");

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        // Initialization
        loadFile();
        String welcomeMessage = "Hello! I'm IstjBot. \n" + "What can I do for you?";
        printResponse(welcomeMessage);

        while (!doneChatting) {
            try {
                String request = sc.nextLine();
                String[] requestInfo = request.split(" ");
                Command command = Command.stringToCommand(requestInfo[0]);

                switch (command) {
                case TODO:
                case DEADLINE:
                case EVENT:
                    if (requestInfo.length == 1) {
                        throw new BotException("As an IstjBot, I cannot find any description for your task.");
                    }
                    addTask(command, requestInfo);
                    // break required for each case
                    break;

                case DATE:
                    if (requestInfo.length != 2) {
                        throw new BotException("As an IstjBot, I cannot search for a task with that command.");
                    }
                    for (Task task : tasks) {
                        task.getDate().ifPresent(date -> {
                            if (date.isEqual(LocalDate.parse(requestInfo[1]))) {
                                searchList.append("\n" + task);
                            }
                        });
                    }
                    printResponse(searchList.toString());

                    // Reset list
                    searchList.setLength(0);
                    searchList.append("As an IstjBot, I present you the task(s) with that date.");
                    break;

                case MARK:
                case UNMARK:
                case DELETE:
                    if (requestInfo.length != 2) {
                        throw new BotException("As an IstjBot, I cannot modify your task with that command.");
                    }
                    modifyTask(command, Integer.parseInt(requestInfo[1]));
                    break;

                case LIST:
                    if (requestInfo.length > 1) {
                        throw new BotException("As an IstjBot, I cannot understand more than list.");
                    }

                    if (tasks.size() != 0) {
                        list.append("\n");
                    }
                    for (int i = 1; i <= tasks.size(); i++) {
                        list.append(i != 1
                                ? "\n" + i + ". " + tasks.get(i - 1).toString()
                                : i + ". " + tasks.get(i - 1).toString());
                    }
                    printResponse(list.toString());

                    // Reset list
                    list.setLength(0);
                    list.append("As an IstjBot, I present you the task(s) in your list:");
                    break;

                case BYE:
                    if (requestInfo.length > 1) {
                        throw new BotException("As an IstjBot, I cannot understand more than bye.");
                    }
                    doneChatting = true;
                    printResponse("Bye, I, IstjBot, will be organizing your tasks until you come back.");
                    sc.close();
                    break;

                }

            } catch(NumberFormatException e) {
                printResponse("As an IstjBot, I don't think that is a proper index.");

            } catch (DateTimeParseException e) {
                printResponse("As an IstjBot, I don't think that is a proper date you entered.");

            } catch (BotException e) {
                printResponse(e.getMessage());
            }
        }
    }

    public static void printResponse(String request) {
        String line = "*_______________________________________________________________* \n";
        System.out.println(line + request + "\n" + line);
    }

    public static void addTask(Command command, String[] requestInfo) throws BotException {
        String description = "";
        int modifier = -1;
        boolean modifierFound = false;
        String modifierMessage = "";

        for (int i = 1; i < requestInfo.length; i++) {
            if (command == Command.DEADLINE && requestInfo[i].equals("/by")) {
                modifier = i;
                modifierFound = true;
                break;
            } else if (command == Command.EVENT && requestInfo[i].equals("/at")) {
                modifier = i;
                modifierFound = true;
                break;
            }
            if (i == 1) {
                description += requestInfo[i];
            } else {
                description += " " + requestInfo[i];
            }
        }

        // Error handle for modifier and description
        if (description.equals("")) {
            throw new BotException("As an IstjBot, I cannot add a task with no description.");
        } else if (!modifierFound && (command == Command.DEADLINE || command == Command.EVENT)) {
            throw new BotException("As an IstjBot, I cannot add a special task with no timing attached.");
        }

        if (command == Command.DEADLINE || command == Command.EVENT) {
            for (int i = modifier + 1; i < requestInfo.length; i++) {
                modifierMessage += i == requestInfo.length - 1 ? requestInfo[i] : requestInfo[i] + " ";
            }
        }

        // Error handle for modifier message
        if (modifierMessage.equals("") && (command == Command.DEADLINE || command == Command.EVENT)) {
            throw new BotException("As an IstjBot, I cannot add a special task with no timing attached.");
        }

        Task taskAdded;
        if (command == Command.TODO) {
            taskAdded = new Todo(description);
        } else if (command == Command.DEADLINE) {
            taskAdded = new Deadline(description, modifierMessage);
        } else {
            taskAdded = new Event(description, modifierMessage);
        }
        tasks.add(taskAdded);
        writeToFile(tasks);

        String messageStart = "As an IstjBot, I will add this task right now. \n";
        String messageLast = "Now you have " + tasks.size() + " ";
        String plural = tasks.size() > 1 ? "tasks" : "task";
        messageLast += plural + " in the list.";
        printResponse(messageStart + taskAdded + "\n" + messageLast);
    }

    public static void modifyTask(Command command, int taskNumber) throws BotException {
        if (taskNumber < 1 || taskNumber > tasks.size()) {
            throw new BotException("As an IstjBot, I cannot find a task with that index.");
        }

        Task taskModified = tasks.get(taskNumber - 1);
        switch (command) {
        case MARK:
            tasks.get(taskNumber - 1).mark();
            printResponse("As an IstjBot, I've marked this task as done: \n" +
                    taskModified.toString());
            break;

        case UNMARK:
            tasks.get(taskNumber - 1).unmark();
            printResponse("As an IstjBot, I've unmarked this task: \n" +
                    taskModified.toString());
            break;

        case DELETE:
            tasks.remove(taskNumber - 1);
            String messageStart = "As an IstjBot, I've removed this task: \n";
            String messageLast = "Now you have " + tasks.size() + " ";
            String plural = tasks.size() > 1 ? "tasks" : "task";
            messageLast += plural + " in the list.";
            printResponse(messageStart + taskModified.toString() + "\n" + messageLast);
            break;
        }
        writeToFile(tasks);
    }

    public static void loadFile() {
        try {
            File folder = new File("data");
            if (!folder.exists()) {
                folder.mkdir();
            }
            File file = new File("data/IstjBot.txt");
            if (!file.exists()) {
                file.createNewFile();
            }
            Scanner sc = new Scanner(file);
            while (sc.hasNext()) {
                String line = sc.nextLine();
                String[] taskInfo = line.split(" / ");

                Task taskAdded;
                // Later use static variable
                Command command = Command.stringToCommand(taskInfo[0]);
                boolean isMarked = Integer.parseInt(taskInfo[1]) == 1;

                switch (command) {
                case TODO:
                    taskAdded = new Todo(taskInfo[2]);
                    tasks.add(taskAdded);
                    if (isMarked) {
                        taskAdded.mark();
                    }
                    break;

                case DEADLINE:
                    taskAdded = new Deadline(taskInfo[2], taskInfo[3]);
                    tasks.add(taskAdded);
                    if (isMarked) {
                        taskAdded.mark();
                    }
                    break;

                case EVENT:
                    taskAdded = new Event(taskInfo[2], taskInfo[3]);
                    tasks.add(taskAdded);
                    if (isMarked) {
                        taskAdded.mark();
                    }
                    break;
                }
            }
        } catch (IOException e) {
            System.out.println(e.getMessage());

        } catch (BotException e) {
            printResponse(e.getMessage());
        }
    }

    public static void writeToFile(ArrayList<Task> tasks) {
        try {
            StringBuilder str = new StringBuilder();
            for (Task task : tasks) {
                str.append(task.toTxtString() + "\n");
            }
            FileWriter fw = new FileWriter("data/IstjBot.txt");
            fw.write(str.toString());
            fw.close();

        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }
}
