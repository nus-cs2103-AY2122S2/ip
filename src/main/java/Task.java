public class Task {
    String name;
    int number;
    boolean done = false;
    public Task(String name, int number){
        this.name = name;
        this.number = number;
    }

    public void mark(){
        this.done = true;
    }

    public void unmark(){
        this.done = false;
    }

    public String getStatus(){
        if (this.done){
            return "X";
        } else {
            return " ";
        }
    }

    @Override
    public String toString(){
        String s = String.format("%d. [%s] %s\n", number+1, getStatus(), name);
        return s;
    }
}
