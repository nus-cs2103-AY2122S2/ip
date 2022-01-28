package duke.tasks;

import duke.exceptions.DukeException;

import java.time.format.DateTimeFormatter;

public class Todo extends Task{

    public Todo(String taskName){
        this.taskName = taskName;
    }

    public char getType(){
        return 'T';
    }

    @Override
    public String getDateForSaving(){
        return String.format("%c\t%c\t%s\t%s\n",getType(),getDone(),getTaskName(),"None");
    }

    @Override
    public String getDate(){
        return null;
    }
}