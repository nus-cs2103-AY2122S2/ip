package dukeClasses;

import java.time.LocalDate;

public class ParsedCommand {
    private String command;
    private Integer index;
    private LocalDate dueDate;
    private String task;

    public ParsedCommand(String command) {
        this.command = command;
        this.index = null;
        this.dueDate = null;
        this.task = null;
    }

    public ParsedCommand(String command, Integer index) {
        this.command = command;
        this.index = index;
        this.dueDate = null;
        this.task = null;
    }

    public ParsedCommand(String command, String task) {
        this.command = command;
        this.index = index;
        this.dueDate = null;
        this.task = task;
    }

    public ParsedCommand(String command, String task, LocalDate dueDate) {
        this.command = command;
        this.index = index;
        this.dueDate = dueDate;
        this.task = task;
    }

    public String getCommand(){
        return this.command;
    }

    public Integer getIndex(){
        return this.index;
    }

    public LocalDate getDueDate(){
        return this.dueDate;
    }

    public String getTask(){
        return this.task;
    }


}
