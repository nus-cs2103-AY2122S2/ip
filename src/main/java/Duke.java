import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Scanner;

import contact.ContactList;
import task.TaskList;
import util.Storage;
import util.Ui;

public class Duke {
    private final Storage storage;
    private final TaskList tasks;
    private final ContactList contacts;
    private final Ui ui;

    private boolean isNewMessage = true;
    private boolean isManagingContacts = false;


    /**
     * initialise the util classes of Duke
     */


    public Duke() {
        ui = new Ui();
        storage = new Storage("data/duke.txt", "data/contacts.txt");
        File newFile = new File("data/duke.txt");
        tasks = new TaskList();
        contacts = new ContactList();

        try {
            if (newFile.exists()) {
                storage.loadTaskFile(tasks.getTaskList());
                storage.loadContacts(contacts.getContactList());
            }
        } catch (FileNotFoundException e) {
            System.out.println("Please create the text file data/duke.txt and data/contact.txt");
        }


    }


    /**
     * Runs the program
     */

    public void run() {
        Scanner sc = new Scanner(System.in);

        String userInput = sc.nextLine();

        while (!userInput.equals("bye")) {

            ui.processInput(userInput, tasks);
            userInput = sc.nextLine();
        }

        ui.exit();
        sc.close();
    }

    public static void main(String[] args) {
        new Duke().run();
    }

    /**
     *
     * @param input command for Duke
     * @return response based on the input string
     */

    public String getResponse(String input) {

        if (isNewMessage) {
            isNewMessage = false;
            return ui.greet();
        } else if (input.equals("manage contacts")) {
            isManagingContacts = true;
            return ui.manageContacts();
        } else if (input.equals("manage tasks")) {
            isManagingContacts = false;
            return ui.manageTasks();
        } else if (input.equals("bye")) {
            try {
                storage.writeToTaskFile(tasks.getTaskList());
                storage.writeToContactFile(contacts.getContactList());
                return ui.exit();
            } catch (IOException e) {
                return "Please create the text files data/contacts.txt and data/duke.txt to save the files";
            }
        } else {
            if (isManagingContacts) {
                return ui.processContacts(input, contacts);
            } else {
                return ui.processInput(input, tasks);
            }

        }
    }






}




