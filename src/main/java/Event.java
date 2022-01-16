public class Event extends Task{
    private String date;
    public Event(String taskName) throws InvalidTaskDataTimeException,InvalidTaskDescriptionException{
        super();
        String[] str =  taskName.split("/at");

        try {
            this.taskName = str[0].strip();
            if (this.taskName.equals("")) this.taskName=null;
        } catch (IndexOutOfBoundsException i){
            this.taskName=null;
        }
        try {
            this.date = str[1].strip();
            if (this.date.equals("")) this.date=null;
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

    public char getType(){
        return 'E';
    }

    @Override
    public String toString(){
        String s = String.format("[%c][%c] %s (at: %s)",this.getType(),this.done,this.taskName,this.date);
        return s;
    }
}
