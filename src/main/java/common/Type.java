package common;

public enum Type {
    TODO,
    DEADLINE,
    EVENT;

    @Override
    public String toString() {
        switch(this) {
            case TODO: return "T";
            case DEADLINE: return "D";
            case EVENT: return "E";
            default: throw new IllegalArgumentException();
        }
    }
}
