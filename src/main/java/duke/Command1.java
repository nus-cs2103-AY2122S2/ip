package main.java.duke;

public enum Command1 {
    
    bye("bye"), delete("delete"), list("list"),
        mark("mark"), unmark("unmark"), todo("todo"),
            deadline("deadline"), event("event");
    
    public final String value;
    
    Command1(String value) {
        this.value = value;
    }
    
    
}
