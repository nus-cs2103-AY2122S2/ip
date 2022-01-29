package duke;

import java.util.Scanner;

public class Ui {

    public Ui() {
    }

    public void welcome() {
        String logo = " ____              _"
                + "\n|  _ \\ _   _ _____| | ____ __\n"
                + "| | | | | | |  ___| |/ /\\ v /\n"
                + "| |_| | |_| | |___|   <  | |\n"
                + "|____/ \\___/\\_____|_|\\_\\ |_|\n";

        System.out.println(logo + "Hello! I'm Ducky! :)\n" + "I am a duke.task manager.\n" +
                "Type 'help' for more information on the commands you can give me.\n" +
                "What can I do for you today?\n" +
                "✧･ﾟ: *✧･ﾟ:* |\\__( o)> *:･ﾟ✧*:･ﾟ✧");
    }

    public void showLine() {
        System.out.println("✧･ﾟ: *✧･ﾟ:* |\\__( o)> *:･ﾟ✧*:･ﾟ✧");
    }

    public void showError(String errorMessage) {
        System.out.println(errorMessage);
    }

    public String readCommand(){
        Scanner myObj = new Scanner(System.in);
        return myObj.nextLine();
    };

    /**
     * Prints the 'help' response by Ducky. Usually called when the user says "help"
     */
    public void printHelp() {
        String helpResponse = "> Type 'list' to see what you have in your duke.task list\n" +
                "> Type 'todo <message>' to put a todo in your list\n" +
                "> Type 'deadline <message> /by <deadline>' to put a deadline in your list." +
                "\n\t - duke.task.Deadline must be in 'DD/MM/YYYY' format" +
                "\n> Type 'event <message> /at <date>' to put an event in your list" +
                "\n\t - Date must be in 'DD/MM/YYYY' format" +
                "\n> Type 'mark <x>' to mark a duke.task in your list" +
                "\n> Type 'unmark <x>' to unmark a duke.task in your list";
        ;
        System.out.println(helpResponse);
    }



}
