package duke.output;

public class BooleanOutput extends Output {
    private boolean bool;
    
    public BooleanOutput(String text, boolean bool) {
        super(text);
        this.bool = bool;
    }

    public boolean getBoolean() {
        return bool;
    }

    @Override
    public String getMessage() {
        return this.text;
    }
}
