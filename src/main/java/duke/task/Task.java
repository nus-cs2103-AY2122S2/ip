package duke.task;

import java.io.Serializable;

public class Task implements Serializable {
    private String name;
    private boolean isMarked = false;

    public Task(String name) {
        this.name = name;
    }

    public void mark(){
        isMarked = true;
    }

    public void unmark(){
        isMarked = false;
    }

    @Override
    public String toString(){
       return isMarked ? "[X] " + name : "[ ] " + name;
    }
}
