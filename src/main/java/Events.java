package main.java;

public class Events extends Task {

    String type = "E";
    String info;

    Events(String name, String info) {
        super(name);
        this.info = info;
    }

    @Override
    String display() {
        return "[" +this.type + "] " + "[" +this.done + "] " + this.name + "(at " + info + ")";
    }

}
