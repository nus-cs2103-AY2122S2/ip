import exceptions.DukeExceptions;
import exceptions.DukeInvalidInput;
import exceptions.DukeInvalidTodo;

import java.io.*;
import java.util.Objects;
import java.time.LocalDate;
import java.util.Scanner;
import java.util.ArrayList;

public class Duke {

    public static void main(String[] args) throws DukeExceptions, IOException {
        Scanner sc = new Scanner(System.in);
        Storage storage = new Storage();
        TaskList taskList = new TaskList();
        ArrayList<Task> lists = new ArrayList<Task>();
        int num = 0;

        storage.loadFromFile(lists);
        taskList.enterHalloumi();

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
                        taskList.mark(textSplit[1], lists);
                        break;
                    case "unmark":
                        taskList.unmark(textSplit[1], lists);
                        break;
                    case "delete" :
                        taskList.delete(textSplit[1], lists);
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
                    default:
                        throw new DukeInvalidInput();

                }
            } catch (DukeInvalidInput e) {
                System.err.println(e.getMessage());
            }
        }
            while (num == 0) ;
            storage.writeToFile(lists);
    }
}

