public class ToDos extends Task {
    public ToDos(String s){
        super(s);
    }

    @Override
    public String show(){
        if(super.done){
            return "[T][X] " + super.s;
        }
        else{
            return "[T][ ] " + super.s;
        }
    }

    @Override
    public String storeFormat(){
        return "T" + "|" + super.done + "|" + super.s + "\n";
    }

}
