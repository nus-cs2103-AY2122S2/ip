import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;


public class Duke {
    public static ArrayList <String > manager = new ArrayList<>();

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
//        ArrayList<String> manager = new ArrayList<String>();

        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello from\n" + logo);

        greet();
        addLineBreak();

        while (true) {
            String instruct = br.readLine();
            if (instruct.equals("bye")) {
                endSession();
                break;
            } else if (instruct.equals("list")) {
                reportList();
            } else {
                addTask(instruct);
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

    public static void reportList() {
        int len = manager.size();
        for (int i = 0; i < len; i++) {
            System.out.println((i + 1) + ": " + manager.get(i));
        }
    }

    public static void addTask(String instruct) {
        manager.add(instruct);
        System.out.println("added: " + instruct);
    }
}
