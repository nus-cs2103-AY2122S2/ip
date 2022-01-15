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
        String[] strArr = new String[100];
        int size = 1;
        while (!str.equals("bye")){
            if (str.equals("list")){
                for (int i = 1; i < size; i++) {
                    System.out.println(i + ". " + strArr[i]);
                }
                str = sc.nextLine();
            }
            else {
                System.out.println("added : " + str);
                strArr[size] = str;
                size++;
                str = sc.nextLine();
            }
        }
        System.out.println("Bye! Hope not to see you again :)");
        sc.close();
    }
}
