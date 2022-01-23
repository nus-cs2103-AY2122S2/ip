public class Todo extends Task {
    public Todo(String s) throws LilyException {
        super(findDescStart(s));
    }
    
    @Override
    public String toString() {
        return "[T]" + super.toString();
    }

    private static String findDescStart(String s) throws LilyException {
        try {
            return s.substring(5); // "todo " is 5 char long
        } catch (StringIndexOutOfBoundsException e) {
            throw new LilyException(e.getMessage());
        }
    }
} 