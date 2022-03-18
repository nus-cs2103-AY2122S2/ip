package juke;

public class Outputs {

    public Outputs() {

    }

    String greeting = "Welcome, fellow user, to\n";
    String logo =         "\n" +
            "       _       _        \n" +
            "      | |     | |       \n" +
            "      | |_   _| | _____ \n" +
            "  _   | | | | | |/ / _ \\\n" +
            " | |__| | |_| |   <  __/\n" +
            "  \\____/ \\__,_|_|\\_\\___|\n" +
            "                        \n" +
            "                        \n";

    String instructions = "";

    String help =
                                "Features | Commands                                      \n" +
                    "normal task |  todo [taskname]                                     \n" +
                    "normal task with deadline | deadline [taskname] /by [date&time]    \n" +
                    "event task with deadline | event [taskname] /at [date&time]        \n" +
                    "                                                                   \n" +
                    "Note for [date&time]: enter it strictly in the following format    \n" +
                    "dd/mm/yyyy [24h] | eg: 31-12-1969 1830                             \n" +
                    "\n" + 
                    "delete a task|  delete [index]                                     \n" +
                    "tag a task|  tag [index] [tagname]                                 \n" +
                    "mark a task as done | mark [index]                                 \n" +
                    "mark a task as undone | unmark [index]                             \n" +
                    "exit Juke | bye                                                    \n"
    String firstPrompt =                 "     Hello! I'm Juke\n" +
            "     What can I do for you?\n";

    String border = "\n";

}
