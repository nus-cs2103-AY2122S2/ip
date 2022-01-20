public class Task {
    String Description;
    boolean isDone;

    public Task (String Description){
        this.Description = Description;
        this.isDone = false;
    }
    public void changeStatus(){
        this.isDone = !isDone;
    }

    public String getStatus(){
        if(this.isDone){
            return "X";
        } else {
            return " ";
        }
    }
    @Override
    public String toString(){
        return Description;
    }



}
