package duke;
import java.io.*;
import java.util.*;

import duke.tasks.*;
import duke.tasklist.*;
import duke.UI.*;

public class Duke {

    public static void main(String[] args) throws IOException {
        UI.printGreeting();
        UI.printCommands();
        Scanner sc = new Scanner(System.in);
        String currInput = null;
        Pikachu pikachu = new Pikachu();
        Storage storage = new Storage("Tasklist.txt", pikachu);
        storage.readTaskList();

        //Start accepting commands
        while (true) {
            currInput = sc.nextLine(); //scan in user input
            if (currInput.toLowerCase().equals("bye")) break; //if user input == bye, exit programme

            System.out.println("________________________________________________________________");
            System.out.println("Pikachu says:");
            pikachu.parseInput(currInput); //passes the current input to pikachu
            System.out.println("________________________________________________________________");
        }

        storage.writeTaskList();
        UI.printGoodbye();
        sc.close();
    }
}
