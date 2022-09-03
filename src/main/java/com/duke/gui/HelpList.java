package com.duke.gui;

import java.util.ArrayList;

public class HelpList {
    public static final String TODO_COMMAND = "todo: Adds a ToDo to the tasklist";
    public static final String TODO_DESCRIPTION = "todo [description of todo]\n"
            + "eg: todo buy groceries";

    public static final String DEADLINE_COMMAND = "deadline: Adds a deadline to the tasklist";
    public static final String DEADLINE_DESCIPTION = "deadline [description of deadline] /by [DD/MM/YYYY HHmm]\n"
            + "eg: deadline quiz /by 05/12/2022 2359";

    public static final String EVENT_COMMAND = "event: Adds an event to the tasklist";
    public static final String EVENT_DESCRIPTION = "event [description of event] /at [location]\n"
            + "eg: event marathon /at stadium";

    public static final String LIST_COMMAND = "list: Displays the tasklist";
    public static final String LIST_DESCRIPTION = "list";

    public static final String MARK_COMMAND = "mark: Marks a task in the tasklist as done";
    public static final String MARK_DESCRIPTION = "mark [task number in the list]\n"
            + "eg: mark 1\n" + "The task number must be valid.";

    public static final String UNMARK_COMMAND = "unmark: Marks a task in the tasklist as NOT done";
    public static final String UNMARK_DESCRIPTION = "unmark [task number in the list]\n"
            + "eg: unmark 1\n" + "The task number must be valid.";

    public static final String DELETE_COMMAND = "delete: Deletes a task from the tasklist";
    public static final String DELETE_DESCRIPTION = "delete [task number in the list]\n"
            + "eg: delete 1\n" + "The task number must be valid.";

    public static final String CLEAR_COMMAND = "clearls: Deletes ALL tasks from the tasklist";
    public static final String CLEAR_DESCRIPTION = "clearls";

    public static final String FIND_COMMAND = "find: Finds tasks matching keywords";
    public static final String FIND_DESCRIPTION = "find [keyword(s)]\n" + "eg: find potato";

    public static final String BYE_COMMAND = "bye: Exits the program";
    public static final String BYE_DESCRIPTION = "Bye!";

    public static final String CHAT_COMMAND = "chat: Switch to the chat tab";
    public static final String CHAT_DESCRIPTION = "Continue chatting with Duke!";

    public static final String HELP_COMMAND = "help: Switches to the help tab / Finds a command in the help list";
    public static final String HELP_DESCRIPTION = "help [command]: "
            + "Brings you to the specified command in the help list\n"
            + "eg: help deadline\n"
            + "help: Switches to the help tab";

    public static ArrayList<String> getList() {
        ArrayList<String> list = new ArrayList<>();
        list.add(TODO_COMMAND);
        list.add(TODO_DESCRIPTION);
        list.add(DEADLINE_COMMAND);
        list.add(DEADLINE_DESCIPTION);
        list.add(EVENT_COMMAND);
        list.add(EVENT_DESCRIPTION);
        list.add(LIST_COMMAND);
        list.add(LIST_DESCRIPTION);
        list.add(MARK_COMMAND);
        list.add(MARK_DESCRIPTION);
        list.add(UNMARK_COMMAND);
        list.add(UNMARK_DESCRIPTION);
        list.add(DELETE_COMMAND);
        list.add(DELETE_DESCRIPTION);
        list.add(CLEAR_COMMAND);
        list.add(CLEAR_DESCRIPTION);
        list.add(FIND_COMMAND);
        list.add(FIND_DESCRIPTION);
        list.add(BYE_COMMAND);
        list.add(BYE_DESCRIPTION);
        list.add(CHAT_COMMAND);
        list.add(CHAT_DESCRIPTION);
        list.add(HELP_COMMAND);
        list.add(HELP_DESCRIPTION);
        return list;
    }
}
