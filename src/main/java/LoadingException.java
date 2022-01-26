public class LoadingException extends DukeException {
    public LoadingException(String message) {
        super(message);
    }

    @Override
    public String toString() {
        return "UH-OH!! seems like the file is not in the right format... (⊙.⊙) \n" +
                "don't worry! I'll start a new file for you!";
    }
}
