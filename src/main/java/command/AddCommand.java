package command;

import exception.DukeException;
import task.*;
import utility.Input;

import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class AddCommand extends Command {
    String type;
    String name;
    String time;
    DukeException dukeException;

    public AddCommand(String command){
        super(command);
        StringTokenizer stringToken = new StringTokenizer(command);
        String prefix = stringToken.nextToken();

        List<String> strArray = new ArrayList<>();
        String taskTime = "";
        while(stringToken.hasMoreTokens()){
            String nextToken = stringToken.nextToken();
            if(prefix.equals("todo") || nextToken.charAt(0) != '/') {
                strArray.add(nextToken);
            } else {
                taskTime = nextToken.substring(1);
                break;
            }
        }

        while(stringToken.hasMoreTokens()){
            taskTime = taskTime.concat(" " + stringToken.nextToken());
        }
        String taskName = String.join(" ", strArray);
        if(taskName.equals("")){
            this.dukeException = new DukeException("input something pls");
            return;
        }
        this.type = prefix;
        this.name = taskName;
        this.time = taskTime;


    }

    @Override
    public void execute(TaskList tasks, Input input) throws DukeException {
        if(this.dukeException != null) {
            throw this.dukeException;
        }

        Task t;
        if(this.type.equals("todo")) {
            t = new Todo(this.name);
        } else if(this.type.equals("deadline")){
            t = new Deadline(this.name, this.time);
        } else if(this.type.equals("event")) {
            t = new Event(this.name, this.time);
        } else {
            throw new DukeException("invalid type");
        }
        tasks.add(t);

        input.print("i added this task: ");
        input.print(t.toString());
    }

    @Override
    public boolean isExit() {
        return false;
    }


}
