import java.io.*;
import java.util.*;
import tasks.*;
//import duke.Pikachu;

public class Duke {

    public static void readTaskList(Pikachu pikachu) throws IOException {
        String dirName = "./src/main/java/tasklist";
        String fileName = "./src/main/java/tasklist/Tasklist.txt";

        //Create directory & file
        File directory = new File(dirName);
        if (!directory.exists()) directory.mkdir();
        File tasklist = new File(fileName);
        tasklist.createNewFile(); //if file already exists, will do nothing

        FileReader fw = new FileReader(fileName);
        BufferedReader br = new BufferedReader(fw);

        //Reading of lines
        String currLine = null;
        while ((currLine = br.readLine()) != null) {
            //System.out.println("For debugging. Am I running too many times?");
            String[] split = currLine.split(",");
            if (split[0].equals("T")) {
                ToDo t = new ToDo(split[2]);
                pikachu.inputList.add(t);
                if (split[1].equals("1")) t.mark(); 
            } else if (split[0].equals("D")) {
                Deadline d = new Deadline(split[2], split[3]);
                pikachu.inputList.add(d);
                if (split[1].equals("1")) d.mark(); 
            } else if (split[0].equals("E")) {
                Event e = new Event(split[2], split[3]);
                pikachu.inputList.add(e);
                if (split[1].equals("1")) e.mark(); 
            }
        }

        fw.close();
        br.close();
    }

    public static void writeTaskList(Pikachu pikachu) throws IOException {
        FileWriter fw = new FileWriter("./src/main/java/tasklist/Tasklist.txt", false);

        //Writing of tasks into tasklist
        for (Task t : pikachu.inputList) {
            fw.write(t.info + "\n");
        }

        fw.close();
    }

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
        String currInput = "";
        Pikachu pikachu = new Pikachu();
        readTaskList(pikachu);

        //Start accepting commands
        while (true) {
            currInput = sc.nextLine(); //scan in user input
            if (currInput.toLowerCase().equals("bye")) break; //if user input == bye, exit programme

            System.out.println("________________________________________________________________");
            System.out.println("Pikachu says:");
            pikachu.parseInput(currInput); //passes the current input to pikachu
            System.out.println("________________________________________________________________");
        }

        writeTaskList(pikachu);
        System.out.println("Pika pika! \nPikachu says bye!");
        sc.close();
    }
}
