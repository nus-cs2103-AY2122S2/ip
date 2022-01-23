public class Event extends Task {
    protected String at;

    /*
        if user didn't type "/at" (atIdx == -1)
            throw new Error "you didnt' type /at bro, try again"
        if user didnt' type a desc
            throew new error you didnt type a description man, try again
    */
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