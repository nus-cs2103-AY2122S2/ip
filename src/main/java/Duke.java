import java.util.Scanner;

public class Duke {
    public static void main(String[] args) {
        String[] listOfThings = new String[100];
        int counter = 0;

        String line = "____________________________________________________________";
        String indentation = "    ";
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello from\n" + logo);

        System.out.println(indentation + line);
        System.out.println(indentation + "Hello! I'm Duke\n" + indentation + "What can I do for you?");
        System.out.println(indentation + line);


        Scanner sc= new Scanner(System.in);
        String str = " ";
        while(true){
            str = sc.nextLine();
            if (str.equals("list")) {
                System.out.println(indentation + line);

                for (int i = 0; i <  counter; i++) {
                    String tempNum = String.valueOf(i+1);
                    System.out.println(indentation + tempNum + ": " + listOfThings[i]);
                }
                System.out.println(indentation + line);

            } else if (str.equals("blah")) {
                System.out.println(indentation + line);
                System.out.println(indentation + "blah");
                System.out.println(indentation + line);
            } else if(str.equals("bye")) {
                System.out.println(indentation + line);
                System.out.println(indentation + "Bye. Hope to see you again soon!");
                System.out.println(indentation + line);
                break;
            } else {
                System.out.println(indentation + line);
                System.out.println(indentation + "added: " + str);
                System.out.println(indentation + line);
                listOfThings[counter] = str;
                counter++;
            }
        }
    }
}
