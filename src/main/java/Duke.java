import java.io.*;

public class Duke {
    public static void main(String[] args) throws IOException {
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
            } else {
                pr.print(userInput);
                pr.flush();
            }
        }
    }
}
