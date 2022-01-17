public enum TaskType {
    TODO("T"),
    DEADLINE("D"),
    EVENT("E");

    private String shorthand;
    TaskType(String shorthand) {
        this.shorthand = shorthand;
    }

    public String getShorthand() {
        return this.shorthand;
    }
}
