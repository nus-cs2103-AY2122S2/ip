package duke;
import java.io.*;
import java.util.*;
import duke.tasks.*;
import duke.tasklist.*;

public class Duke {

    public static void main(String[] args) throws IOException {
        
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
        System.out.printf("             _'....----\"\"\"\"\" \n");

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
        System.out.println("Pika pika! \nPikachu says bye!");
        sc.close();
    }
}
