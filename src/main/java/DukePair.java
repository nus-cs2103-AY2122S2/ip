public class DukePair {
    private String key;
    private Boolean value;

    public DukePair(String key, Boolean value) {
        this.key = key;
        this.value = value;
    }

    public void setValue(Boolean var) {
        this.value = var;
    }

    @Override
    public String toString() {
        if (this.value) {
            return "[X" + "] " + this.key;
        } else {
            return "[ " + "] " + this.key;
        }
    }
}
