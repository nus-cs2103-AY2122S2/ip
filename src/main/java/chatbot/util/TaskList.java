package chatbot.util;

import chatbot.datetime.Timestamp;
import chatbot.exception.ChatBotException;
import chatbot.task.Deadline;
import chatbot.task.Event;
import chatbot.task.Task;
import chatbot.task.ToDo;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.*;
import java.util.stream.Collectors;

public class TaskList {

    private static final String TODO = "todo";
    private static final String DEADLINE = "deadline";
    private static final String EVENT = "event";

    private final List<Task> list;
    private final Set<String> set;
    private final Set<String> validTypes;

    public TaskList() {
        this.list = new ArrayList<>();
        this.set = new HashSet<>();

        this.validTypes = new HashSet<>();
        this.validTypes.add(TODO);
        this.validTypes.add(DEADLINE);
        this.validTypes.add(EVENT);
    }

    public Task getTask(int index) {
        return list.get(index);
    }

    public int getNumTasks() {
        return list.size();
    }

    public boolean isEmpty() {
        return list.isEmpty();
    }

    public Boolean isValidIndex(int index) {
        return index >= 0 && index < list.size();
    }

    public void load(File f) throws ChatBotException {
        try (Scanner sc = new Scanner(f)) {
            while (sc.hasNext()) {
                String[] data = sc.nextLine().split("&");
                String type = data[0];
                String done = data[1];
                String title = data[2];
                String datetime = data.length == 4 ? data[3] : "";
                switch (type) {
                case "T":
                    list.add(new ToDo(title, done));
                    set.add(title);
                    break;
                case "D":
                    list.add(
                            new Deadline(title, done, new Timestamp(datetime))
                    );
                    set.add(title);
                    break;
                case "E":
                    list.add(
                            new Event(title, done, new Timestamp(datetime))
                    );
                    set.add(title);
                    break;
                default:
                    System.out.println("error");
                }
            }
        } catch (FileNotFoundException e) {
            throw new ChatBotException(
                    "I couldn't find your save file traveller! "
                            + "You can create it in the data directory and name it data.txt."
            );
        } catch (ChatBotException e) {
            throw new ChatBotException(
                    "One of your saved tasks was formatted incorrectly traveller! I've removed it from your list."
            );
        }
    }

    public void save(File f) throws ChatBotException {
        try (FileWriter fw = new FileWriter(f)) {
            for (Task t : list) {
                String type = t.getType();
                String title = t.getTitle();
                String done = t.isCompleted();
                Timestamp datetime = t.getDateTime();

                String data = String.format("%s&%s&%s", type, done, title);
                if (datetime != null) {
                    data =
                            data.concat(
                                    String.format("&%s", datetime.saveString())
                            );
                }
                fw.write(data);
                fw.write(System.lineSeparator());
            }
        } catch (IOException e) {
            throw new ChatBotException(
                    "Oops! Something went wrong while writing to your save file traveller!"
            );
        }
    }

    public String add(String[] titleArgs, String[] otherArgs)
            throws ChatBotException {
        String type = titleArgs[0];
        if (!validTypes.contains(type)) {
            throw new ChatBotException();
        }
        if (titleArgs.length <= 1) {
            throw new ChatBotException(
                    String.format(
                            "You need to key in the title of your %s traveller!",
                            type
                    )
            );
        }
        if (otherArgs.length <= 1) {
            throw new ChatBotException(
                    String.format(
                            "You need to key in %s traveller!",
                            type.equals(DEADLINE)
                                    ? "the due date and time of your deadline"
                                    : "the timestamp of your event"
                    )
            );
        }

        String title = combineArgs(titleArgs);
        String other = combineArgs(otherArgs);

        if (set.contains(title)) {
            throw new ChatBotException(
                    String.format(
                            "This %s is already in your task list traveller!",
                            type
                    )
            );
        } else {
            set.add(title);
            switch (type) {
            case DEADLINE:
                if (!otherArgs[0].equals("by")) {
                    throw new ChatBotException(
                            "The correct format for adding a deadline is "
                                    + "deadline <name of task> /by <timestamp of task>"
                    );
                } else {
                    Timestamp by = new Timestamp(other);
                    Deadline deadline = new Deadline(title, by);
                    list.add(deadline);
                    return String.format(
                            "This deadline has been added to your task list!%n%n             %s",
                            deadline
                    );
                }
            case EVENT:
                if (!otherArgs[0].equals("at")) {
                    throw new ChatBotException(
                            "The correct format for adding an event is "
                                    + "event <name of task> /at <timestamp of task>"
                    );
                } else {
                    Timestamp at = new Timestamp(other);
                    Event event = new Event(title, at);
                    list.add(event);
                    return String.format(
                            "This event has been added to your task list!%n%n             %s",
                            event
                    );
                }
            default:
                throw new ChatBotException();
            }
        }
    }

    public String addToDo(String[] args) throws ChatBotException {
        if (!validTypes.contains(args[0])) {
            throw new ChatBotException();
        }
        if (args.length <= 1) {
            throw new ChatBotException(
                    "You need to key in the title of your todo traveller!"
            );
        }

        String title = combineArgs(args);
        if (set.contains(title)) {
            throw new ChatBotException(
                    "This todo is already in your task list traveller!"
            );
        } else {
            set.add(title);
            ToDo todo = new ToDo(title);
            list.add(todo);
            return String.format(
                    "This todo has been added to your task list!%n%n             %s",
                    todo
            );
        }
    }

    public String delete(int index) throws ChatBotException {
        if (!isValidIndex(index).equals(true)) {
            throw new ChatBotException(
                    "This is an invalid task index traveller! You can type list to check all task indexes!"
            );
        }

        Task removedTask = list.remove(index);
        return String.format(
                "This task has successfully been removed from your task list!%n%n             %s",
                removedTask
        );
    }

    private String combineArgs(String[] args) {
        String title = "";
        for (int i = 1; i < args.length; i++) {
            if (i != args.length - 1) {
                title = title.concat(args[i]).concat(" ");
            } else {
                title = title.concat(args[i]);
            }
        }
        return title;
    }

    public String summary() throws ChatBotException {
        if (isEmpty()) {
            throw new ChatBotException(
                    "Your task list is empty traveller! Add some tasks first!"
            );
        }

        return listAsString(list);
    }

    public String getTasksOnDate(Timestamp date) {
        List<Task> filtered = list
                .stream()
                .filter(t -> date.equals(t.getDateTime()))
                .collect(Collectors.toList());
        if (filtered.isEmpty()) {
            return "You have no tasks on this date traveller!";
        } else {
            return listAsString(filtered);
        }
    }

    private String listAsString(List<Task> tasks) {
        String res = "";
        for (int i = 0; i < tasks.size(); i++) {
            String taskString = String.format(
                    "             %d. %s%n",
                    i + 1,
                    tasks.get(i)
            );
            res = res.concat(taskString);
        }
        return res;
    }

    public String mark(int index) {
        Task task = list.get(index);
        if (task.isCompleted().equals("X")) {
            return "This task was already completed! No need to mark it again.";
        } else {
            task.mark();
            return String.format(
                    "This task has been marked as completed in your task list!%n%n             %s",
                    task
            );
        }
    }

    public String unmark(int index) {
        Task task = list.get(index);
        if (!task.isCompleted().equals("X")) {
            return "This task has not been completed yet! No need to unmark it.";
        } else {
            task.unmark();
            return String.format(
                    "This task has been unmarked in your task list!%n%n             %s",
                    task
            );
        }
    }
}
