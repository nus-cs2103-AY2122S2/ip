package duke.tasks;

import duke.exceptions.DukeException;

public class Todo extends Task{

    public Todo(String taskName){
        this.taskName = taskName;
    }

    public char getType(){
        return 'T';
    }
}