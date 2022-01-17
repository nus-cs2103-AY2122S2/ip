package main.java;

public class Deadline extends Task{
    String type = "D";
    String info;

    Deadline(String name, String info) {
        super(name);
        this.info = info;
    }

    @Override
    String display() {
        return "[" +this.type + "] " + "[" +this.done + "] " + this.name + "(by " + info + ")";
    }
}
