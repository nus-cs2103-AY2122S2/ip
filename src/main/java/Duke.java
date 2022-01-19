import java.util.Scanner;

public class Duke {
    
    //Level 1
    /*
    public static void main(String[] args) {
        // String logo = " ____        _        \n"
        //         + "|  _ \\ _   _| | _____ \n"
        //         + "| | | | | | | |/ / _ \\\n"
        //         + "| |_| | |_| |   <  __/\n"
        //         + "|____/ \\__,_|_|\\_\\___|\n";
        // System.out.println("Hello from\n" + logo);

        String hyphenate = "    ____________________________________________________________";
        System.out.println(hyphenate + "\n    Hello! I'm DockerHawker\n    I am your personal assistant! what can I do for you?\n" + hyphenate);

        System.out.println("");
        Scanner sc = new Scanner(System.in);
        String command = sc.nextLine();
        while (!command.equals("bye")){
            System.out.println(hyphenate + "\n    " + command + "\n" + hyphenate);
            System.out.println("");
            command = sc.nextLine();
        }
        System.out.println(hyphenate + "\n    Bye. Let's play some video games next time!\n" + hyphenate);
        sc.close();
    }
    */


    //Level 2
    /*
    public static void main(String[] args) {
        String hyphenate = "    ____________________________________________________________";
        System.out.println(hyphenate + "\n    Hello! I'm DockerHawker\n    I am your personal assistant! what can I do for you?\n" + hyphenate);

        Scanner sc = new Scanner(System.in);
        System.out.println("");
        String command = sc.nextLine();
        String[] commandStorage = new String[100];
        int ID = 0;

        while (!command.equals("bye")){
            if (command.equals("list")){
                System.out.println(hyphenate);
                for (int i=0; i<ID; i++){
                    System.out.println("    " + (i+1) + ". " + commandStorage[i]);
                }
                System.out.println(hyphenate);
            }else{
                commandStorage[ID] = command; 
                System.out.println(hyphenate + "\n    added: " + command + "\n" + hyphenate);
                ID++;
            }
            System.out.println("");
            command = sc.nextLine();
        }
        System.out.println(hyphenate + "\n    Bye. Let's play some video games next time!\n" + hyphenate);
        sc.close();
    }*/
    
    //Level 3
    public static void main(String[] args) {
        String hyphenate = "    ____________________________________________________________";
        System.out.println(hyphenate + "\n    Hello! I'm DockerHawker\n    I am your personal assistant! what can I do for you?\n" + hyphenate);

        Scanner sc = new Scanner(System.in);
        System.out.println("");
        String command = sc.nextLine();
        String[] commandStorage = new String[100];
        boolean[] commandMark = new boolean[100];
        int ID = 0;

        while (!command.equals("bye")){
            if (command.equals("list")){
                System.out.println(hyphenate);
                for (int i=0; i<ID; i++){
                    System.out.println("    " + (i+1) + "." + "[" + (commandMark[i]==true?"X":" ") + "]" + " " + commandStorage[i]);
                }
                System.out.println(hyphenate);
            } else if (command.split(" ")[0].equals("mark")){
                int markIndex = Integer.parseInt(command.split(" ")[1]) - 1; //Zero-indexed
                commandMark[markIndex] = true;
                System.out.println(hyphenate);
                System.out.println("    Nice! I've marked this task as done:");
                System.out.println("       [" + (commandMark[markIndex]==true?"X":" ") + "] " + commandStorage[markIndex]);
                System.out.println(hyphenate);
            } else if (command.split(" ")[0].equals("unmark")){
                int markIndex = Integer.parseInt(command.split(" ")[1]) - 1; //Zero-indexed
                commandMark[markIndex] = false;
                System.out.println(hyphenate);
                System.out.println("    Okay, I've marked this task as not done yet:");
                System.out.println("       [" + (commandMark[markIndex]==true?"X":" ") + "] " + commandStorage[markIndex]);
                System.out.println(hyphenate);
            } else {
                commandStorage[ID] = command; 
                System.out.println(hyphenate + "\n    added: " + command + "\n" + hyphenate);
                ID++;
            }
            System.out.println("");
            command = sc.nextLine();
        }
        System.out.println(hyphenate + "\n    Bye. Let's play some video games next time!\n" + hyphenate);
        sc.close();
    }
}
