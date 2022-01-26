package task;

public abstract class Task {
    String Description;
    boolean isDone;
    String tag;

    public Task (String Description, String tag){
        this.Description = Description;
        this.isDone = false;
        this.tag = tag;
    }
    public void changeStatus(){
        this.isDone = !isDone;
    }

    public String getStatus(){
        if(this.isDone){
            return "[X]";
        } else {
            return "[]";
        }
    }
    @Override
    public String toString(){
        return Description;
    }

    public String getTag(){
        return tag;
    }




}
