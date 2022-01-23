
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Decoder {
    public Decoder() {
    }

    public Task decode(String tasks) {

        String[] args = tasks.split(" \\| ");
//        System.out.println(args.length);
//        for (int i = 0; i < args.length; i++) {
//            System.out.println(args[i]);
//        }
        String taskType = args[0];
        Boolean isDone = args[1].contains("X");
        String taskDescription = args[2];
        String taskDate = null;
        if (args.length == 4) {
            try {
                SimpleDateFormat dateFormatter = new SimpleDateFormat("MMM dd yyyy");
                Date dateObject = dateFormatter.parse(args[3]);
                dateFormatter.applyPattern("yyyy-MM-dd");
                taskDate = dateFormatter.format(dateObject);;
            }
            catch (ParseException e) {
                System.out.println(e);
            }
        }
        Task task = null;
        switch (taskType) {
            case "E":
                task = new Event(taskDescription, taskDate);
                break;
            case "D":
                task = new Deadline(taskDescription, taskDate);
                break;
            case "T":
                task = new Todo(taskDescription);
                break;

        }

        if (isDone) {
            task.isDone = true;
        }
        return task;
    }
}
