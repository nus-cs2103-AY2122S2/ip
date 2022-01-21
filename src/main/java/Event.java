public class Event extends Task{
    String date;

    public Event(String taskName, String date){
        super(taskName);
        this.date = addChar("(" + date + ")", ':', 3);
    }

    public String addChar(String str, char ch, int position) {
        StringBuilder sb = new StringBuilder(str);
        sb.insert(position, ch);
        return sb.toString();
    }

    @Override public void printTask(){
        //print task type
        System.out.print("[E]");
        //print task done symbol
        if(this.done){
            System.out.print("[X] " + this.taskName + " ");
        } else {
            System.out.print("[ ] " + this.taskName + " ");
        }
        System.out.println(this.date);
    }
}
