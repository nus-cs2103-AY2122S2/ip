package duke;

import duke.*;
import exceptions.DukeExceptions;
import exceptions.DukeInvalidInput;
import exceptions.DukeInvalidTodo;

import java.io.*;
import java.util.Collections;
import java.util.Scanner;
import java.util.ArrayList;

/**
 * Main method containing the Chat Bot.
 */
public class Duke {

    static Storage storage = new Storage();
    static TaskList taskList = new TaskList();
    static ArrayList<Task> lists = new ArrayList<Task>();
    public Duke() {
        try {
            storage.loadFromFile(lists);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Method containing the main execution of the Chat Bot.
     * @param
     * @throws DukeExceptions
     * @throws IOException
     */
    public static String main(String input) throws DukeExceptions, IOException {
        StringBuilder str = new StringBuilder();


        boolean isOver = false;
        int num = 0;

        //Ui.enterHalloumi(str);


            String[] textSplitOne = Parser.splitForwardSlash(input);
            String[] textSplit = Parser.splitSpace(textSplitOne[0]);
            String fullDesc = Parser.makeDesc(textSplit, textSplit.length);
            try {
                switch (textSplit[0]) {
                case "bye":
                    num = Ui.exitHalloumi(str);
                    storage.writeToFile(lists);
                    break;
                case "list":
                    Ui.printList(lists, lists.size(), str);
                    break;
                case "mark":
                    for(int i = 1; i < textSplit.length; i++) {
                        taskList.mark(textSplit[i], lists, str);

                    }
                    break;
                case "unmark":
                    for(int i = 1; i < textSplit.length; i++) {
                        taskList.unmark(textSplit[i], lists, str);

                    }
                    break;
                case "delete" :
                    ArrayList<Integer> array = new ArrayList<>();
                    for(int i = 1; i < textSplit.length; i++) {
                        Integer integer = Integer.parseInt(textSplit[i]);
                        array.add(integer);
                    }
                    Collections.sort(array, Collections.reverseOrder());
//                    System.out.println(array);
                    for(int i = 0; i < array.size(); i++) {
//                        System.out.println(textSplit[i]);
                        taskList.delete(String.valueOf(array.get(i)), lists, str);
                    }
                    break;
                case "todo":
                    try {
                        if(fullDesc.equals(" ") || fullDesc.equals("")) {
                            throw new DukeInvalidTodo();
                        }
                    }
                    catch(DukeInvalidTodo e) {
                        str.append(e.getMessage());
                    }
                    taskList.todo(fullDesc, lists, str);
                    break;
                case "event":
                    taskList.event(fullDesc, textSplitOne[1], lists, str);
                    break;
                case "deadline":
                    taskList.deadline(fullDesc, textSplitOne[1], lists, str);
                    break;
                case "find":
                    taskList.find(fullDesc, lists, str);
                    break;
                default:
                    throw new DukeInvalidInput();
                }
            } catch (DukeInvalidInput e) {
                str.append(e.getMessage());
            }

            isOver = true;

            assert isOver;
            return str.toString();
    }

    /**
     *
     * @param input the input from the user
     * @return the response by the program
     * @throws DukeExceptions
     */

    public String getResponse(String input) throws DukeExceptions, IOException {
        try {

            return main(input);
        }
        catch (DukeExceptions e) {
            return e.getMessage();
        }

    }

}

