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
                    "1. normal task (without deadline) \n- todo [taskname]                                     \n" +
                    "2. normal task (with deadline) \n- deadline [taskname] /by [date&time]    \n" +
                    "3. special event (specified time)  \n- event [taskname] /at [date&time]        \n" +
                    "                                                                   \n" +
                    "Note for [date&time]: enter it strictly in the following format    \n" +
                    "dd/mm/yyyy [24h] | eg: 31-12-1969 1830                             \n" +
                    "\n" + 
                    "4. delete a task\n- delete [index]                                     \n" +
                    "5.tag a task\n- tag [index] [tagname]                                 \n" +
                    "6.mark a task as done\n- mark [index]                                 \n" +
                    "7.mark a task as undone\n- unmark [index]                             \n" +
                    "8.exit Juke\n- bye                                                    \n";
    static String firstPrompt = "\n" +
            "                                       \n" +
            " ,-----. ,--.  ,--.,------.            \n" +
            "'  .-.  '|  ,'.|  ||  .---'            \n" +
            "|  | |  ||  |' '  ||  `--,             \n" +
            "'  '-'  '|  | `   ||  `---.            \n" +
            " `-----' `--'  `--'`------'  ,--.      \n" +
            " ,---. ,--.,--.,--,--,  ,---.|  ,---.  \n" +
            "| .-. ||  ||  ||      \\| .--'|  .-.  | \n" +
            "| '-' ''  ''  '|  ||  |\\ `--.|  | |  | \n" +
            "|  |-'  `----' `--''--' `---'`--' `--' \n" +
            "`--'                                   \n";

    String border = "\n";

}
