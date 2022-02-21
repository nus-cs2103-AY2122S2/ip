
package util;

import contact.ContactList;
import task.TaskList;



public class Ui {

    private final Parser parser = new Parser();

    /**
     * Processes the user message and provides a response
     * @param input input command
     * @param tasks TaskList for tasks to be stored in
     * @return a response message to the user
     */
    public String processInput(String input, TaskList tasks) {
        parser.parse(input);
        String task = parser.getTask();
        String item = parser.getItem();

        return tasks.execute(task, item);


    }

    public String processContacts(String input, ContactList contacts) {
        parser.parse(input);
        String task = parser.getTask();
        String item = parser.getItem();

        return contacts.execute(task, item);
    }

    public String manageContacts() {
        return "Switched to manage contacts!";
    }

    public String manageTasks() {
        return "Switched to manage tasks!";
    }

    /**
     * Greets the user
     * @return greet message
     */
    public String greet() {
        return "Yawn... You woke me up! Urgh\n"
                + "I'm Snorlax! The manifestation of your laziness so that you will be stop procrastinating!\n"
                + "What do you need?";
    }

    /**
     * Displays the exit message to the user
     * @return exit message
     */

    public String exit() {
        return "Bye! Back to sleep! I hope you'll continue to be productive when I'm asleep :D";
    }


}
