import java.util.ArrayList;
import java.util.Scanner;

class Duke {
    public static void main(String[] args) {
        
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println(logo);

        System.out.println("Hello! I'm Duke");
        System.out.println("What can I do for you?");

        Scanner sc= new Scanner(System.in);
        String str = sc.nextLine();

        ArrayList<Task> tasks = new ArrayList<Task>();
        
    

        while(!str.equals("bye")) {
            if(!str.equals("list")) {
                tasks.add(str);
                System.out.println("added: " + str);
                

            } else {
                System.out.println("Here are the tasks in your list:");
                for(int i = 0; i < list.size(); i++) {
                    System.out.println((i+1) + ". " + list.get(i).getStatusIcon() + list.get(i));
                }

            }
            str = sc.nextLine();
        }
        
            
            System.out.println("Bye. Hope to see you again soon!");
            sc.close();
            
        
    }
}