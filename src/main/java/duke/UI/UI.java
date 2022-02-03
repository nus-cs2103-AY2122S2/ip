package duke.UI;

public class UI {

    /**
     * Prints to both terminal and GUI the greeting message sent when first starting up the application.
     * Can be customised by user.
     *
     * @return Greeting string to be printed in GUI.
     */
    public static String printGreeting() {
        String result = "Pika Pika! \nPikachu says hi! \nTo see available commands, type 'commands'.\n";
        //Prints the Pikachu ASCII on top of the greeting message in terminal
        System.out.printf("                                             ,-.\n");
        System.out.printf("                                          _.|  '\n");
        System.out.printf("                                        .'  | /\n");
        System.out.printf("                                      ,'    |'\n");
        System.out.printf("                                     /      /\n");
        System.out.printf("                       _..----\"\"---.'      /\n");
        System.out.printf(" _.....---------...,-\"\"                  ,'\n");
        System.out.printf(" `-._  \\                                /\n");
        System.out.printf("     `-.+_            __           ,--. .\n");
        System.out.printf("          `-.._     .:  ).        (`--\"| \\\n");
        System.out.printf("               7    | `\" |         `...'  \\\n");
        System.out.printf("               |     `--'     '+\"        ,\". ,\"\"-\n");
        System.out.printf("               |   _...        .____     | |/    '\n");
        System.out.printf("          _.   |  .    `.  '--\"   /      `./     j            Pika Pika!\n");
        System.out.printf("         \\' `-.|  '     |   `.   /        /     /             Pikachu says hi!\n");
        System.out.printf("         '     `-. `---\"      `-\"        /     /\n");
        System.out.printf("          \\       `.                  _,'     /\n");
        System.out.printf("           \\        `                        .\n");
        System.out.printf("            \\                                j\n");
        System.out.printf("             \\                              /\n");
        System.out.printf("              `.                           .\n");
        System.out.printf("                +                          \\\n");
        System.out.printf("                |                           L\n");
        System.out.printf("                |                           |\n");
        System.out.printf("                |  _ /,                     |\n");
        System.out.printf("                | | L)'..                   |\n");
        System.out.printf("                | .    | `                  |\n");
        System.out.printf("                '  \\'   L                   '\n");
        System.out.printf("                 \\  \\   |                  j\n");
        System.out.printf("                  `. `__'                 /\n");
        System.out.printf("                _,.--.---........__      /\n");
        System.out.printf("               ---.,'---`         |   -j\"\n");
        System.out.printf("                .-'  '....__      L    |\n");
        System.out.printf("              \"\"--..    _,-'       \\ l||\n");
        System.out.printf("                  ,-'  .....------. `||'\n");
        System.out.printf("               _,'                /\n");
        System.out.printf("             ,'                  /\n");
        System.out.printf("            '---------+-        /\n");
        System.out.printf("                     /         /\n");
        System.out.printf("                   .'         /\n");
        System.out.printf("                 .'          /\n");
        System.out.printf("               ,'           /\n");
        System.out.printf("             _'....----\"\"\"\"\" \n\n");
        System.out.println(result);

        return result;
    }

    /**
     * Prints to both terminal and GUI the list of available user commands.
     * Can be customised by user.
     *
     * @return String of list of commands to be printed in GUI.
     */
    public static String printCommands() {
        String result;
        result = ("The available commands are:\ncommands: See list of commands\n"
                + "list: See list of current tasks\n");
        result += ("mark: Mark a task as completed. Syntax: mark <index of task>\n");
        result += ("unmark: Mark a task as not completed. Syntax: unmark <index of task>\n");
        result += ("delete: Delete a task. Syntax: delete <index of task>\n");
        result += ("todo: Create a todo task. Syntax: todo <taskname>\n");
        result += ("deadline: Create a deadline. Syntax: deadline <taskname> /<yyyy-mm-dd hhmm of deadline>\n");
        result += ("event: Create an event. Syntax: event <taskname> "
                + "/<yyyy-mm-dd hhmm of start> <yymmdd hhmm of end>\n");
        result += ("find: Search for tasks containing a given keyword. Syntax: find <keyword>\n");

        //Prints commands in terminal
        System.out.println(result);

        return result;
    }

    /**
     * Prints to both terminal and GUI the goodbye message sent when closing the application.
     * Can be customised by user.
     *
     * @return Goodbye string to be printed in GUI.
     */
    public static String printGoodbye() {
        String result = "Pika pika! \nPikachu says bye!";

        //Prints goodbye in terminal
        System.out.println(result);

        return result;
    }
}
