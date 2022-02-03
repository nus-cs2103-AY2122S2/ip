package duke.command;

import duke.exception.DukeException;
import duke.task.*;
import duke.utility.Storage;
import duke.utility.UI;

import java.time.LocalDateTime;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

/**
 * Add new duke.task for duke.command class
 */
public class AddCommand extends Command {
    String type;
    String name;
    String by;
    DukeException dukeException;
    LocalDateTime taskTime;

    public AddCommand(String command){
        super(command);
        StringTokenizer stringToken = new StringTokenizer(command);
        String prefix = stringToken.nextToken();
        List<String> strArray = new ArrayList<>();
        String by = "";
        while(stringToken.hasMoreTokens()){
            String nextToken = stringToken.nextToken();
            if(prefix.equals("todo") || nextToken.charAt(0) != '/') {
                strArray.add(nextToken);
            } else {
                by = nextToken.substring(1);
                break;
            }
        }
        String taskName = String.join(" ", strArray);
        if(taskName.equals("")){
            this.dukeException = new DukeException("input something pls");
            return;
        }

        String date = "";
        String time = "";
        LocalDateTime dateTime = LocalDateTime.now();
        if(stringToken.hasMoreTokens()) {
            date = stringToken.nextToken();
        }
        if(stringToken.hasMoreTokens()) {
            time = stringToken.nextToken();
        }

        if(prefix.equals("deadline") || prefix.equals("event")) {
            String hour = time.substring(0,2);
            String min = time.substring(2);
            try {
                dateTime = LocalDateTime.parse(date + "T" + hour + min + ":00");
            } catch (DateTimeParseException e) {
                this.dukeException = new DukeException("wrong time format: use yyyy-mm-dd hh:mm format");
            }
        }

        this.type = prefix;
        this.name = taskName;
        this.taskTime = dateTime;
        this.by = by;
    }

    @Override
    public String execute(TaskList tasks, UI ui, Storage storage) throws DukeException {
        if(this.dukeException != null) {
            throw this.dukeException;
        }
        Task t;
        if(this.type.equals("todo")) {
            t = new Todo(this.name);
        } else if(this.type.equals("deadline")){
            t = new Deadline(this.name, this.taskTime);
        } else if(this.type.equals("event")) {
            t = new Event(this.name, this.taskTime);
        } else {
            throw new DukeException("invalid type");
        }
        tasks.add(t);
        ui.print("i added this duke.task: ");
        ui.print(t.toString());
        storage.save(tasks);
        return null;
    }

    /**
     * @return false for addCommand as it has not exit loop yet
     */
    @Override
    public boolean isExit() {
        return false;
    }

}
