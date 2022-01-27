package lily;
import lily.Storage;
import lily.Ui;
import lily.TaskList;

import lily.task.Task;
import lily.task.Deadline;
import lily.task.Event;
import lily.task.Todo;
import lily.task.LilyException;

import java.io.IOException;
import java.util.LinkedList;
import java.util.Scanner;

/**
 * Makes sense of commands input by the user and executes them.
 * 
 * @author Hong Yi En, Ian
 * @version Jan 2022 (AY21/22 Sem 2)
 */
public class Parser {
    TaskList tasks;
    Ui ui;
    Storage st;

    public Parser(TaskList t, Ui ui, Storage st) {
        this.tasks = t;
        this.ui = ui;
        this.st = st;
    }

    // Lily accepts inputs from user
    public void parse() {
        userInteracting: while (true) {
            try {
                String sentence = ui.readCommand();
                String[] parsedSentence = sentence.split(" ");
                String command = parsedSentence[0];
                switch (command) {
                case "bye":
                    st.save(tasks);
                    break userInteracting;
    
                case "list":
                    if (list.isEmpty()) {
                        errorPretty("there's nothing in the list bro");
                    } else {
                        prettyPrint("you told me you had to\n" + printList());
                    }
                    break;
    
                case "mark":
                    int addIdx = Integer.parseInt(parsedSentence[1]) - 1;
                    /*
                        * if (list.isEmpty())
                        * throw new Error("you cant mark something that isn't there");
                        * else if (already marked)
                        * throw new error you've already finished this
                        * if input doesn't have an int, ask which number you want to mark also.
                        */
                    list.get(addIdx).mark();
                    String markMsg = "oh. you've finished it. okay\n"
                            + indent + (addIdx + 1) + "."
                            + list.get(addIdx).toString();
                    prettyPrint(markMsg);
                    break;
    
                case "unmark":
                    int delIdx = Integer.parseInt(parsedSentence[1]) - 1;
                    /*
                        * if (list.isEmpty())
                        * throw new Error("you can't unmark something thaj isn't there");
                        * else if (not marked yet)
                        * throw new error you havent done this
                        * if input doesn't have an int, ask which number you want to mark also.
                        */
                    list.get(delIdx).unmark();
                    String unmarkMsg = "hey, you gotta get it done later, okay?\n"
                            + indent + (delIdx + 1) + "."
                            + list.get(delIdx).toString();
                    prettyPrint(unmarkMsg);
                    break;
    
                case "todo":
                    try {
                        Todo t = new Todo(findTodoDescStart(sentence));
                        list.add(t);
                        taskAddedMsg(t);
                    } catch (LilyException le) {
                        errorPretty("Todo description cannot be empty!");
                    }
                    break;
                case "deadline":
                    /*
                        if user didn't type "/by" (byIdx == -1)
                            throw new Error "you didnt' type /by bro, try again"
                        if user didnt' type a desc
                            throw new error you didnt type a description man, try again
                    */
                    try {
                        String[] parsedDeadline = findDeadlineDescStart(sentence);
                        Deadline d = new Deadline(parsedDeadline[0], parsedDeadline[1]);
                        list.add(d);
                        taskAddedMsg(d);
                    } catch (LilyException le) { // need catch "no-/by" error
                        errorPretty("Deadline description cannot be empty!");
                    }
                    break;
                case "event":
                    /*
                        if user didn't type "/at" (atIdx == -1)
                            throw new Error "you didnt' type /at bro, try again"
                        if user didnt' type a desc
                            throew new error you didnt type a description man, try again
                    */
                    try {
                        String[] parsedEvent = findEventDescStart(sentence);
                        Event e = new Event(parsedEvent[0], parsedEvent[1]);
                        list.add(e);
                        taskAddedMsg(e);
                    } catch (LilyException le) { // need catch "no-/at" error
                        errorPretty("Event description cannot be empty!");
                    }
                    break;
    
                case "delete":
                case "remove":
                    try {
                        prettyPrint("hmph. then why did you make me track your\n"
                                + indent + list.remove(Integer.parseInt(parsedSentence[1]) - 1)
                                + "\n"
                                + indent + "anyway, now you're left with\n" + printList());
                    } catch (IndexOutOfBoundsException e) { // new error: catch no int input also
                    }
                    break;
    
                default:
                    errorPretty("sorry i don't understand what you meant by\n\n"
                            + indent + sentence + "\n\n"
                            + indent + "you can try these instead:\n" + listCommands());
                }

            } catch (LilyException le) {
                ui.showError("Description cannot be empty!");
            } catch (IOException ioe) {

            } catch (IndexOutOfBoundsException oob) {
                ui.showError("eh bro your list is shorter than that");
            }
        }
        ui.closeUi();
    }


    private static String findTodoDescStart(String s) throws LilyException {
        try {
            return s.substring(5); // "todo " is 5 char long
        } catch (StringIndexOutOfBoundsException e) {
            throw new LilyException(e.getMessage());
        }
    }

    private static String[] findDeadlineDescStart(String s) throws LilyException {
        String[] result = new String[2];
        try {
            result[0] = s.substring(9, s.indexOf("/by") - 1);
            result[1] = s.substring(s.indexOf("/by") + 4);
            return result;
        } catch (StringIndexOutOfBoundsException e) {
            throw new LilyException(e.getMessage());
        }
    }
    
    private static String[] findEventDescStart(String s) throws LilyException {
        String[] result = new String[2];
        try {
            result[0] = s.substring(6, s.indexOf("/at") - 1);
            result[1] = s.substring(s.indexOf("/at") + 4); // different exception is thrown if indexOf cant find /at
            return result;
        } catch (StringIndexOutOfBoundsException e) {
            throw new LilyException(e.getMessage());
        }
    }