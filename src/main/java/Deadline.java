public class Deadline extends Task{
    private String date;
    public Deadline(String taskName){
        super(taskName);
        String[] str =  taskName.split("/by");
        this.taskName=str[0].strip();
        this.date=str[1].strip();
        // TODO handle invalid/empty date
    }

    public char getType(){
        return 'D';
    }

    @Override
    public String toString(){
        String s = String.format("[%c][%c] %s (by: %s)",this.getType(),this.done,this.taskName,this.date);
        return s;
    }
}
