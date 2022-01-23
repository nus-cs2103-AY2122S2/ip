public class Deadline extends Task{
    String date;

    public Deadline(String taskName, String date){
        super(taskName);
        this.date = addChar("(" + date + ")", ':', 3);
    }

    public String addChar(String str, char ch, int position) {
        StringBuilder sb = new StringBuilder(str);
        sb.insert(position, ch);
        return sb.toString();
    }

    @Override public void printTask(){
        System.out.print("[D]");

        if(this.done){
            System.out.print("[X] " + this.taskName + " ");
        } else {
            System.out.print("[ ] " + this.taskName + " ");
        }

        System.out.println(this.date);
    }
}
