import java.util.ArrayList;

public class ParsedAnswerHandler {
    ParsedAnswer pa;

    ParsedAnswerHandler(ParsedAnswer pa) {
        this.pa = pa;
    }

    void execute() {
        // do a thing depending on what the answer is
        switch (pa.getCommand()) {
            case "bye":
                System.out.println("Have a nice day.");
                System.exit(0);
                break;

            case "list":
                ArrayList<Task> taskArrayList = Storage.taskList;
                taskArrayList.forEach(System.out::println);
                break;
        }
    }
}
