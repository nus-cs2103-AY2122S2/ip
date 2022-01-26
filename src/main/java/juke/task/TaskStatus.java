package juke.task;

public enum TaskStatus {
    NOT_DONE("\u2610"),
    DONE("\u2612");
    
    private final String statusIcon;
    
    private TaskStatus(String statusIcon) {
        this.statusIcon = statusIcon;
    }
    
    public String getStatusIcon() {
        return this.statusIcon;
    }
}
