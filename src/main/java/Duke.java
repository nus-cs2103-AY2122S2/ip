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

    /**
     * Method containing the main execution of the Chat Bot.
     * @param args
     * @throws DukeExceptions
     * @throws IOException
     */
    public static void main(String[] args) throws DukeExceptions, IOException {
        Scanner sc = new Scanner(System.in);
        Storage storage = new Storage();
        TaskList taskList = new TaskList();
        ArrayList<Task> lists = new ArrayList<Task>();
        boolean isOver = false;
        int num = 0;

        storage.loadFromFile(lists);
        Ui.enterHalloumi();

        do {
            String text = sc.nextLine();
            String[] textSplitOne = Parser.splitForwardSlash(text);
            String[] textSplit = Parser.splitSpace(textSplitOne[0]);
            String fullDesc = Parser.makeDesc(textSplit, textSplit.length);
            try {
                switch (textSplit[0]) {
                case "bye":
                    num = Ui.exitHalloumi();
                    break;
                case "list":
                    Ui.printList(lists, lists.size());
                    break;
                case "mark":
                    for(int i = 1; i < textSplit.length; i++) {
                        taskList.mark(textSplit[i], lists);

                    }
                    break;
                case "unmark":
                    for(int i = 1; i < textSplit.length; i++) {
                        taskList.unmark(textSplit[i], lists);

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
                        taskList.delete(String.valueOf(array.get(i)), lists);
                    }
                    break;
                case "todo":
                    try {
                        if(fullDesc.equals(" ") || fullDesc.equals("")) {
                            throw new DukeInvalidTodo();
                        }
                    }
                    catch(DukeInvalidTodo e) {
                        System.err.println(e.getMessage());
                        continue;
                    }
                    taskList.todo(fullDesc, lists);
                    break;
                case "event":
                    taskList.event(fullDesc, textSplitOne[1], lists);
                    break;
                case "deadline":
                    taskList.deadline(fullDesc, textSplitOne[1], lists);
                    break;
                case "find":
                    taskList.find(fullDesc, lists);
                    break;
                default:
                    throw new DukeInvalidInput();
                }
            } catch (DukeInvalidInput e) {
                System.err.println(e.getMessage());
            }
        }
            while (num == 0) ;
            isOver = true;
            storage.writeToFile(lists);
            assert isOver;
    }
}

