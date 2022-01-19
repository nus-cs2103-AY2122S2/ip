import java.util.Scanner;

public class Duke {

    public static String[] strArray = new String[100];
    public static int index = 0;

    public static void main(String[] args) {

        System.out.println("hello im duke wassup \n what can I help you?");
        Scanner sc = new Scanner(System.in);
        String echo = sc.nextLine();
        while(!echo.equals( "bye") ){
            if(echo.equals("list")){

                for(int i = 0; i < index; i++){
                    int num = i + 1;
                    System.out.println(num + " " + strArray[i]);
                }
            } else {
                strArray[index] = echo;
                System.out.println("added " + echo);
                index++;
            }
            echo = sc.nextLine();
        }

        System.out.println("byebye see u!");
        sc.close();
    }

}
