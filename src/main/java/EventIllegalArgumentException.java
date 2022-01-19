public class EventIllegalArgumentException extends DukeIllegalArgumentException {
    public EventIllegalArgumentException(String message) {
        super(message);
    }

    @Override
    public String toString() {
        return "UH-OH!! you gotta fill in the description and time to create a valid event (> <ლ)";
    }
}
