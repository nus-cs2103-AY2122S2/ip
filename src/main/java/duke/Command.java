package main.java.duke;

public enum Command {
    
    bye("bye"), delete("delete"), list("list"),
        mark("mark"), unmark("unmark"), todo("todo"),
            deadline("deadline"), event("event");
    
    public final String value;
    
    Command(String value) {
        this.value = value;
    }
    
    
}
