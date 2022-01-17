package main.java;

public class ToDo extends Task {
    String type = "D";


    ToDo(String name) {
        super(name);
    }

    @Override
    String display() {
        return "[" +this.type + "] " + "[" +this.done + "] " + this.name;
    }
}
