import java.io.*;
import java.util.ArrayList;

public class Duke {
    public static void main(String[] args) throws IOException {

        ArrayList<String> arrayList = new ArrayList<>();

        System.out.println("Hello! I'm Duke \nWhat can I do for you?");

        while (true) {
            BufferedReader br = new BufferedReader(new
                    InputStreamReader(System.in));

            PrintWriter pr = new PrintWriter(new BufferedWriter((new OutputStreamWriter((System.out)))));

            String userInput = br.readLine();

            if (userInput.equals("bye")) {
                pr.print("Bye. Hope to see you again soon!");
                pr.close();
                break;
            } else if (userInput.equals("list")){
                for (int i = 0; i < arrayList.size(); i++) {
                    pr.print((i+1) + "." + arrayList.get(i));
                    pr.print("\n");
                }
                pr.flush();
            } else {
                arrayList.add(userInput);
                pr.print("added: " + userInput + "\n");
                pr.flush();
            }
        }
    }
}
