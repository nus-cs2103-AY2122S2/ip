public class DeadlineIllegalArgumentException extends DukeIllegalArgumentException {
    public DeadlineIllegalArgumentException(String message) {
        super(message);
    }

    @Override
    public String toString() {
        return "UH-OH!! you gotta fill in the description and deadline to create a valid deadline (> <ლ)";
    }
}
