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

    @Override
    public String storeFormat(){
        return "E|" + super.done + "|" + super.s + "|" + this.time + "\n";
    }

}
