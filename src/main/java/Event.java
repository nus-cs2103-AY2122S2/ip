public class Event extends Task {
    protected String at;

    public Event(String s) throws LilyException {
        super(findDescStart(s));
        this.at = s.substring(s.indexOf("/at") + 4); // different exception is thrown if indexOf cant find /at
    }

    private static String findDescStart(String s) throws LilyException {
        try {
            return s.substring(6, s.indexOf("/at") - 1);
        } catch (StringIndexOutOfBoundsException e) {
            throw new LilyException(e.getMessage());
        }
    }
    
    @Override
    public String toString() {
        return "[E]" + super.toString() + " (at: " + this.at + ")";
    }
} 