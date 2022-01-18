public class Todos extends Task {

    public Todos(String description) {
        super(description);

    }

    @Override
    public String message() {
        return "[T]" + super.message() ;
    }
}
