
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.* ;

public class Duke {


    public static void main(String[] args) throws IOException {
        String greeting = "Hello! I'm Duke\nWhat can I do for you?";

        String separation = "\n******************************\n";
        System.out.println(separation + greeting + separation);
        ArrayList<Task> list_of_inputs = new ArrayList<>();

        while(true) {
            BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
            String inp = br.readLine();
            String[] temp = inp.split(" ");

            if (inp.equals("bye")) {
                System.out.println("Bye. Hope to see you again soon!");
                break;
            } else if (inp.equals("list")) {
                for (int i = 0; i < list_of_inputs.size(); i++) {

                    System.out.println((i+1) + ". [" +  (list_of_inputs.get(i)).getStatusIcon() + "] " + list_of_inputs.get(i).description);
                }
            } else if (temp[0].equals("mark")) {
                int curr_no = Integer.parseInt(temp[1]) - 1;
                Task task_done = list_of_inputs.get(curr_no);
                task_done.markedDone();
                System.out.println ("[" + task_done.getStatusIcon() + "]" + task_done.description );
            } else if (temp[0].equals("unmark")) {
                int curr_no = Integer.parseInt(temp[1]) - 1;
                Task task_undone = list_of_inputs.get(curr_no);
                task_undone.markedUndone();
                System.out.println ("[" + task_undone.getStatusIcon() + "] " + task_undone.description );
            } else {
                System.out.println("added:" + inp);
                list_of_inputs.add(new Task(inp));
            }
        }

    }


}
