public class ToDo extends Task {
    public ToDo(String description) {
        super(description);
    }

    @Override
    public String getSaveToFileFormat() {
        return "T," + super.getSaveToFileFormat() + ",," + getStatusIcon();    
    }
    
    @Override
    public String toString() {
        return "[T]" + super.toString();
    }
}