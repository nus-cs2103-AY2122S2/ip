import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Event extends Task{
    //private LocalDateTime date;
    /*
    public Event(String taskName) throws DukeException{
        super();
        String[] str =  taskName.split("/at");

        try {
            this.taskName = str[0].strip();
            if (this.taskName.equals("")) this.taskName=null;
        } catch (IndexOutOfBoundsException i){
            this.taskName=null;
        }
        try {
            this.date = Parser.parseDateTime(str[1].strip());
        } catch (IndexOutOfBoundsException i){
            this.date=null;
        }

        if (this.taskName == null){
            throw new InvalidTaskDescriptionException("Event");
        }
        if (this.date == null){
            throw new InvalidTaskDataTimeException("Event");
        }
    }

     */

    public Event(String taskName, LocalDateTime date){
        this.taskName = taskName;
        this.date = date;
    }

    public char getType(){
        return 'E';
    }

    @Override
    public String toString(){
        DateTimeFormatter formatted = DateTimeFormatter.ofPattern("d MMM yyyy, K:mma");

        return String.format("[%c][%c] %s (at: %s)",this.getType(),this.done,this.taskName,this.date.format(formatted));
    }
}
