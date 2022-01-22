package tasks;

public class Task {
    public String name;
    public boolean isDone;
    public String info;

    public Task(String name) {
        this.name = name;
        this.isDone = false;
    }

    public void mark() { System.out.printf("Error! Mark method has not been overridden!"); }
    public void unmark() { System.out.printf("Error! Unmark method has not been overridden!"); }
    
}
