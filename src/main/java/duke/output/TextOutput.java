package duke.output;

public class TextOutput extends Output {

    public TextOutput(String text) {
        super(text);
    }

    @Override
    public String getMessage() {
        return this.text;
    }
}
