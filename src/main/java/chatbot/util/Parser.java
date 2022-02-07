package chatbot.util;

import chatbot.datetime.Timestamp;
import chatbot.exception.ChatBotException;
import chatbot.list.ContactList;
import chatbot.list.TaskList;


/**
 * Represents an interpreter for user inputs.
 */
public class Parser {

    private final Ui ui;
    private final Storage tasksStorage;
    private final Storage contactsStorage;
    private final TaskList taskList;
    private final ContactList contactList;

    /**
     * Instantiates a new Parser.
     *
     * @param ui The ChatBot UI handler.
     * @param tasksStorage The handler for loading tasks from and saving tasks to the save file.
     * @param taskList user's The task list.
     */
    public Parser(Ui ui, Storage tasksStorage, Storage contactsStorage, TaskList taskList, ContactList contactList) {
        this.ui = ui;
        this.tasksStorage = tasksStorage;
        this.contactsStorage = contactsStorage;
        this.taskList = taskList;
        this.contactList = contactList;
    }

    /**
     * Parses the input given by the user and attempts to carry out the appropriate commands.
     *
     * @param rawInput The input given by the user.
     * @return The boolean indicating whether the ChatBot should continue prompting the user for inputs or not.
     */
    public String parse(String rawInput) {
        String[] input = rawInput.split(" ");
        String response;
        switch (input[0]) {
        case "bye":
            return ui.bye();
        case "list":
            try {
                return list(input, taskList, contactList);
            } catch (ChatBotException e) {
                return ui.error(e.getMessage());
            }
        case "get":
            try {
                if (input.length > 2) {
                    return "That's too many inputs traveller! You only need to key in the "
                                    + "date or timestamp for which you want to view your tasks!";
                }
                Timestamp date = new Timestamp(input[1]);
                return taskList.getTasksOnDate(date);
            } catch (ChatBotException e) {
                return ui.error(e.getMessage());
            }
        case "find":
            return taskList.getTasksByKeyword(input);
        case "mark":
        case "unmark":
            try {
                if (taskList.isEmpty()) {
                    return "Your task list is empty traveller! "
                                    + "Add some tasks first before attempting to mark or unmark!";
                } else if (input.length > 2) {
                    return "That's too many inputs traveller! "
                            + "You only need to key in the index of the task you wish to mark or unmark!";
                }
                response = markOrUnmark(
                        Integer.parseInt(input[1]) - 1,
                        input[0].equals("mark")
                );
                return response;
            } catch (ChatBotException e) {
                return ui.error(e.getMessage());
            } catch (NumberFormatException e) {
                return ui.error(
                        "You should mark and unmark tasks using their index rather than title traveller!"
                );
            } catch (ArrayIndexOutOfBoundsException e) {
                return ui.error(
                        "You need to key in the index of the task you wish to mark or unmark traveller!"
                );
            }
        case "todo":
            try {
                response = createTaskResponse(taskList.addToDo(input));
                tasksStorage.saveChanges(taskList);
                return response;
            } catch (ChatBotException e) {
                return ui.error(e.getMessage());
            }
        case "contact":
            try {
                response = createContactResponse(contactList.add(input));
                contactsStorage.saveChanges(contactList);
                return response;
            } catch (ChatBotException e) {
                return ui.error(e.getMessage());
            }
        case "delete":
            try {
                String type = input[1];
                if (input.length > 3) {
                    throw new ChatBotException(
                            "That's too many inputs traveller! You only need to key in the index of the"
                                    + String.format("%s you wish to delete!", type)
                    );
                }
                if (type.equals("task")) {
                    response = createTaskResponse(
                            taskList.delete(Integer.parseInt(input[2]) - 1)
                    );
                    tasksStorage.saveChanges(taskList);
                } else if (type.equals("contact")) {
                    response = createContactResponse(
                            contactList.delete(Integer.parseInt(input[2]) - 1)
                    );
                    contactsStorage.saveChanges(contactList);
                } else {
                    throw new ChatBotException("You can only delete a task or contact traveller!");
                }
                return response;
            } catch (ChatBotException e) {
                return ui.error(e.getMessage());
            } catch (NumberFormatException e) {
                return ui.error(
                        "You should delete items using their index traveller!"
                );
            } catch (ArrayIndexOutOfBoundsException e) {
                return ui.error(
                        "You need to specify which list to delete from and the index of the item traveller!"
                );
            }
        case "guide":
            return ui.printGuide();
        default:
            String[] temp = rawInput.split("/", 2);
            try {
                if (temp.length == 1) {
                    String[] splitInput = temp[0].split(" ");
                    String type = splitInput[0];
                    if (temp[0].isBlank()) {
                        throw new ChatBotException(
                                "Don't be shy traveller! Type in a command and I will assist you!"
                        );
                    } else if (type.equals("deadline")) {
                        if (splitInput.length == 1) {
                            throw new ChatBotException(
                                "You need to key in the title as well as "
                                        + "due date and time of your deadline traveller!"
                            );
                        } else {
                            throw new ChatBotException(
                                    "You need to include /by in your command to add a deadline traveller!"
                            );
                        }
                    } else if (type.equals("event")) {
                        if (splitInput.length == 1) {
                            throw new ChatBotException(
                                    "You need to key in the title and timestamp of your event traveller!"
                            );
                        } else {
                            throw new ChatBotException(
                                    "You need to include /at in your command to add an event traveller!"
                            );
                        }
                    }
                    throw new ChatBotException();
                }
                response = createTaskResponse(
                        taskList.add(
                                temp[0].split(" "),
                                temp[1].split(" ")
                        )
                );
                tasksStorage.saveChanges(taskList);
                return response;
            } catch (ChatBotException e) {
                return ui.error(e.getMessage());
            }
        }
    }

    private String list(String[] input, TaskList taskList, ContactList contactList) throws ChatBotException {
        if (input.length == 1) {
            throw new ChatBotException("You need to choose whether you want to list tasks or contacts traveller!");
        }

        switch(input[1]) {
        case "tasks":
            return taskList.summary();
        case "contacts":
            return contactList.summary();
        default:
            throw new ChatBotException();
        }
    }

    private String markOrUnmark(int index, boolean mark)
            throws ChatBotException {
        if (taskList.isInvalidIndex(index)) {
            throw new ChatBotException(
                    "This is an invalid task index traveller! You can type list to check all task indexes!"
            );
        }
        String response;
        if (mark) {
            response = taskList.mark(index);
        } else {
            response = taskList.unmark(index);
        }
        tasksStorage.saveChanges(taskList);
        return response;
    }

    private String createTaskResponse(String message) {
        return message.concat("\n").concat(ui.printNumItems(taskList));
    }

    private String createContactResponse(String message) {
        return message.concat("\n").concat(ui.printNumItems(contactList));
    }
}
