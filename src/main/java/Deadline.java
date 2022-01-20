public class Deadline extends Task{

    protected String date;

    public Deadline(String desc, boolean isComp, String date){
        super(desc, isComp);
        this.date = date;
    }

    @Override
    public String toString(){
        String temp = "[D] " + super.toString() + " (by: " + date + ")";
        return temp;
    }
}
