public class Events extends Task {

    public String time;

    public Events (String s, String time){
        super(s);
        this.time = time;
    }

    @Override
    public String show(){
        if(super.done){
            return "[E][X] " + s + " (at: " + time + ")";
        }
        else{
            return "[E][ ] " + s + " (at: " + time + ")";
        }
    }
}
