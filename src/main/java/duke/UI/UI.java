package duke.UI;

public class UI {

    public static void printGreeting() {
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
    }

    public static void printCommands() {
        System.out.println("The available commands are:\ncommands: See list of commands\n"
                + "list: See list of current tasks");
        System.out.println("mark: Mark a task as completed. Syntax: mark <index of task>");
        System.out.println("unmark: Mark a task as not completed. Syntax: unmark <index of task>");
        System.out.println("delete: Delete a task. Syntax: delete <index of task>");
        System.out.println("todo: Create a todo task. Syntax: todo <taskname>");
        System.out.println("deadline: Create a deadline. Syntax: deadline <taskname> /<yyyy-mm-dd hhmm of deadline>");
        System.out.println("event: Create an event. Syntax: event <taskname> "
                + "/<yyyy-mm-dd hhmm of start> <yymmdd hhmm of end>");
        System.out.println("find: Search for tasks containing a given keyword. Syntax: find <keyword>");
    }

    public static void printGoodbye() {
        System.out.println("Pika pika! \nPikachu says bye!");
    }
}
