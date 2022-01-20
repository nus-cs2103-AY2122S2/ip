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
                    System.out.println(num + " [" + status + "] " + strArray[i]);
                }
            } else if(echo.substring(0,4).equals("mark")){
                int index = Integer.parseInt(echo.substring(echo.length()-1));
                strArray[index - 1].changeStatus();
                System.out.println("I have marked: " +
                        " [" + strArray[index - 1].getStatus() + "] " + strArray[index - 1] );
            } else if(echo.substring(0,6).equals("unmark")){
                int index = Integer.parseInt(echo.substring(echo.length()-1));
                strArray[index - 1].changeStatus();
                System.out.println("I have unmarked: " +
                        " [" + strArray[index - 1].getStatus() + "] " + strArray[index - 1] );
            }
            else {
                Task t = new Task(echo);
                strArray[index] = t;
                System.out.println("added " + echo);
                index++;
            }
            echo = sc.nextLine();
        }

        System.out.println("byebye see u!");
        sc.close();
    }

}
