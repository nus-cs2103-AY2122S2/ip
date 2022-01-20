import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Deadline extends Task{
<<<<<<< HEAD

=======
    private LocalDateTime date;
>>>>>>> branch-level-8
    public Deadline(String taskName) throws InvalidTaskDescriptionException,InvalidTaskDataTimeException{
        super();
        String[] str =  taskName.split("/by");

        try {
            this.taskName = str[0].strip();
            if (this.taskName.equals("")) this.taskName=null;
        } catch (IndexOutOfBoundsException i){
            this.taskName=null;
        }
        try {
            this.date = str[1].strip();
            if (this.date.equals("")) this.date=null;
            this.date = Task.parseDateTime(str[1].strip());
        } catch (IndexOutOfBoundsException i){
            this.date=null;
        }

        if (this.taskName == null){
            throw new InvalidTaskDescriptionException("Deadline");
        }
        if (this.date == null){
            throw new InvalidTaskDataTimeException("Deadline");
        }
    }

    public char getType(){
        return 'D';
    }

    @Override
    public String toString(){
        String s = String.format("[%c][%c] %s (by: %s)",this.getType(),this.done,this.taskName,this.date);
        DateTimeFormatter formatted = DateTimeFormatter.ofPattern("d MMM yyyy, K:mma");

        String s = String.format("[%c][%c] %s (by: %s)",this.getType(),this.done,this.taskName,this.date.format(formatted));
        return s;
    }
}
