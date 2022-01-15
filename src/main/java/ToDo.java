public class ToDo extends Task {
    public ToDo(String input, int number){
        super(input, number);
        name = name.substring(4, name.length());
    }

    @Override
    public String toString(){
        String s = String.format("%d. [T][%s] %s\n", number+1, getStatus(), name);
        return s;
    }
}
