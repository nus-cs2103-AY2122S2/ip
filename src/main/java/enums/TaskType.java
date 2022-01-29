package enums;

public enum TaskType {
    TODO("T"), DEADLINE("D"), Event("E");

    private String icon;

    private TaskType(String icon) {
        this.icon = icon;
    }

    @Override
    public String toString() {
        return icon;
    }
}
