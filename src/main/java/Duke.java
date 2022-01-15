import java.util.Scanner;

public class Duke {
    public static void main(String[] args) {
        String logo = "       __  \n"
                + "(____()'`; \n"
                + "/,    /` \n"
                + "\\\\\"--\\\\\n";

        System.out.println("Woof, I am (supposed to look like) a dog bot. \n" + logo + "\n" +  "What do you want from me?\n");
        Scanner sc = new Scanner(System.in);
        String str = sc.nextLine();
        while (!str.equals("bye")){
            System.out.println(str);
            str = sc.nextLine();
        }
        System.out.println("Bye! Hope not to see you again :)");
        sc.close();
    }
}
