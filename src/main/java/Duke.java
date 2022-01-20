import java.util.Scanner;

public class Duke {

    public static Task[] strArray = new Task[100];
    public static int index = 0;

    public static void main(String[] args) {

        System.out.println("hello im duke wassup \n what can I help you?");
        Scanner sc = new Scanner(System.in);
        String echo = sc.nextLine();
        while(!echo.equals( "bye") ){
            if(echo.equals("list")){
                for(int i = 0; i < index; i++){
                    int num = i + 1;
                    String status = strArray[i].getStatus();
                    String tag = strArray[i].getTag();
                    System.out.println("here are the stuffs in your list: ");
                    System.out.println(num + status + strArray[i]);
                    System.out.println("Now you have " + Duke.index + " tasks in your list");
                }
            } else if(echo.substring(0,4).equals("mark")){
                int index = Integer.parseInt(echo.substring(echo.length()-1));
                strArray[index - 1].changeStatus();
                System.out.println("I have marked: " +
                         strArray[index - 1].getStatus()  + strArray[index - 1] );
            } else if(echo.substring(0,6).equals("unmark")){
                int index = Integer.parseInt(echo.substring(echo.length()-1));
                strArray[index - 1].changeStatus();
                System.out.println("I have unmarked: " +
                         strArray[index - 1].getStatus()  + strArray[index - 1] );
            } else if(echo.substring(0,4).equals("todo")) {
                Todo t = new Todo(echo);
                strArray[index] = t;
                System.out.println("ok i have added this task: ");
                System.out.println(t.getTag() + t.getStatus() + " " + echo);
                index++;
            }
            else if(echo.substring(0,8).equals("deadline")){
                String time = echo.substring(echo.lastIndexOf("/") + 1);
                Deadline t = new Deadline(echo, time);
                strArray[index] = t;
                System.out.println("ok i have added this task: ");
                System.out.println(t.getTag() + t.getStatus() + t + " by " + t.time);
                index++;
            } else if(echo.substring(0,5).equals("event")) {
                String time =echo.substring(echo.lastIndexOf("/") + 1);
                Event t = new Event(echo, time);
                strArray[index] = t;
                System.out.println("ok i have added this task: ");
                System.out.println(t.getTag() + t.getStatus() + "at" + t.getTime());
                index++;
            }
            echo = sc.nextLine();
        }

        System.out.println("byebye see u!");
        sc.close();
    }

}
