import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        Echo echo = new Echo();
        echo.hello();

        String s = sc.nextLine();
        while (!s.equals("bye")) {
            try {
                echo.read(s);
            } catch (EchoException e) {
                echo.printFormat(e.getMessage());
            }
            s = sc.nextLine();
        }
        echo.exit();
    }
}
