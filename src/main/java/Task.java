package main.java;

public class Task {
    String done = " ";
    String name;

    Task(String name) {
        this.name = name;
    }

    void setMark(boolean bool) {
        if (bool) {
            this.done = "X";
            this.MarkResponse(done.equals("X"));
        } else {
            this.done = " ";
            this.MarkResponse(done.equals("X"));
        }
    }


    void MarkResponse(boolean val) {
        System.out.println(
                "____________________________________________________________"
        );
        if (val) {
            System.out.println("Nice! I've marked this task as done: \n");
        } else {
            System.out.println("OK, I've marked this task as not done yet: \n");
        }

        System.out.println(this.display());
        System.out.println(
                "____________________________________________________________"
        );
    }

    String display() {
        return "[" +this.done + "] " + this.name;
    }
}
