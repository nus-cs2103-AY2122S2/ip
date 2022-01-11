import java.time.LocalDate;

public class ToDos extends Task{
    public ToDos(String objective) {
        super(objective);
    }
    @Override
    public boolean sameTime(String date) {
        return false;
    }

    @Override
    public String toString() {
        return "[T]" + super.toString();
    }
}
