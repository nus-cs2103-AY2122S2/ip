public class Task {
    private String info;

    public Task(String info){
        this.info = info;
    }

    @Override
    public String toString(){
        return this.info;
    }
}
