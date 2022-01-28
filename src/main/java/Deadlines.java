public class Deadlines extends Task{

    public String time;

    public Deadlines(String s, String time){
        super(s);
        this.time = time;
    }

    @Override
    public String show(){
        if(super.done){
            return "[D][X] " + s + " (by: " + time + ")";
        }
        else{
            return "[D][ ] " + s + " (by: " + time + ")";
        }
    }

    @Override
    public String storeFormat(){
        return "D|" + super.done + "|" + super.s + "|" + this.time + "\n";
    }
}
