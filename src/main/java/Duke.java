import java.io.*;
import java.util.ArrayList;
import java.util.StringTokenizer;
import java.util.Scanner;

public class Duke {
    public static void main(String[] args) {
        String logo = " ____        _        \n"
                    + "|  _ \\ _   _| | _____ \n"
                    + "| | | | | | | |/ / _ \\\n"
                    + "| |_| | |_| |   <  __/\n"
                    + "|____/ \\__,_|_|\\_\\___|\n";

        ArrayList<String> tasks = new ArrayList<String>();
        System.out.println("Hello! I'm Ducky! :) \nWhat can I do for you?" +
                "\n-----------------");

        while(true){
            Scanner myObj = new Scanner(System.in);
            String response = myObj.nextLine();
            if (response.equals("bye")){
                String byeResponse = "Bye. Hope to see you again soon! " +
                        "\n-----------------";
                System.out.println(byeResponse);
                break;
            }
            else if(response.equals("list")){
                for (int i = 0; i < tasks.size(); i++) {
                    System.out.println(i+1 +". " + tasks.get(i));
                }
            }
            else {
                tasks.add(response);
                System.out.println("added: " + response + "\n-----------------");
            }
        }
    }
}

