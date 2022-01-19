import java.util.Scanner;

public class Duke {
    public static void main(String[] args) {
        System.out.println("hello im duke wassup");
        Scanner sc = new Scanner(System.in);
        String echo = sc.nextLine();
        while(!echo.equals( "bye")){
            System.out.println(echo);
            echo = sc.nextLine();
        }

        System.out.println("byebye see u!");
        sc.close();
    }

}
