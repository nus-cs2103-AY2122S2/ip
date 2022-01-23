public class Deadline extends Task {
    protected String by;

    public Deadline(String s) throws LilyException {
        super(findDescStart(s));
        this.by = s.substring(s.indexOf("/by") + 4);
    }

    private static String findDescStart(String s) throws LilyException {
        try {
            return s.substring(9, s.indexOf("/by") - 1);
        } catch (StringIndexOutOfBoundsException e) {
            throw new LilyException(e.getMessage());
        }
    }
    
    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: " + this.by + ")";
    }
} 