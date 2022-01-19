import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;


public class Duke {
    public static void main(String[] args) throws IOException {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello from\n" + logo);

        greet();
        addLineBreak();

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        while (true) {
            String instruct = br.readLine();
            if (instruct.equals("bye")) {
                endSession();
                break;
            } else {
                System.out.println(instruct);
            }
            addLineBreak();
        }

        br.close();
    }

    public static void greet() {
        String welcome = "Hi my name is Duke!";
        String assist = "How may I help you today?";
        System.out.println(welcome);
        System.out.println(assist);
    }

    public static void endSession() {
        String goodbye = "Adios! See you soon:)";
        System.out.println(goodbye);
    }

    public static void addLineBreak() {
        System.out.println("---------------------xx-------------------------");
    }
}
