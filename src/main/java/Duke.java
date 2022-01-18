public class Duke {
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
        String str = sc.next();

        while(!str.equals("bye")) {
            System.out.println(str); 
            str = sc.next();
            
        }
        
            
            System.out.println("Bye. Hope to see you again soon!");
            sc.close();
            
        
    }
}
    }
}
