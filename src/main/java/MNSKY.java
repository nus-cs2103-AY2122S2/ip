import java.util.Scanner;

public class MNSKY {
    public static void print_mnsky(String msg) {
        System.out.print("MNSKY: ");
        System.out.println(msg);
    }
    public static void main(String[] args) {
        print_mnsky("Hi, I'm");
        print_mnsky("MM      MM  NN      NN   SSSSSSS   KK     KK  YY      YY");
        print_mnsky("MMMM  MMMM  NNNN    NN  SSSS       KK   KK      YY  YY");
        print_mnsky("MM  MM  MM  NN  NN  NN    SSSSS    KKKKK          YY");
        print_mnsky("MM      MM  NN    NNNN       SSSS  KK   KK        YY");
        print_mnsky("MM      MM  NN      NN  SSSSSSS    KK     KK      YY");
        print_mnsky("I can help!");

        String input = "";
        Scanner scan = new Scanner(System.in);

        while (!input.equals("bye")) {
            System.out.println();
            System.out.print("> ");
            input = scan.nextLine();

            if (input.equals("bye")) {
                print_mnsky("I can help!");
                System.out.println("[MNSKY is shutting itself down...]");
            } else {
                System.out.println(input);
                print_mnsky("I can help!");
            }
        }
    }
}
