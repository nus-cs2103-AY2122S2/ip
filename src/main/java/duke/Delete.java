package duke;
public class Delete extends Task{

    public Delete(String description) {
        super(description);

    }

    @Override
    public String message() {
        return "Noted. I've deleted this task:\n" + super.message() ;
    }
}

