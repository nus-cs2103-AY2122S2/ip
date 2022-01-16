public class Event extends Task{
    private String date;
    public Event(String taskName){
        super(taskName);
        String[] str =  taskName.split("/at");
        this.taskName=str[0].strip();
        this.date=str[1].strip();
        // TODO handle invalid/empty date
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
