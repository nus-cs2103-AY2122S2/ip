import java.util.Scanner;

public class Duke {
    public static void main(String[] args) {
        
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
        String currInput = "";

        while (true) {
            currInput = sc.nextLine().toLowerCase();
            if (currInput.equals("bye")) break;
            System.out.println("    ________________________________________________________________");
            System.out.println("    Pikachu says "+currInput);
            System.out.println("    ________________________________________________________________");
        }
        System.out.println("Pika pika! \nPikachu says bye!");
    }
}
