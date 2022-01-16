public class ToDo extends Task {
    public ToDo(String input, int number){
        super(input.substring(4,input.length()), number, null, "T");
    }

    @Override
    public String toString(){
        String s = String.format("%d. [T][%s] %s\n", number+1, getStatus(), name);
        return s;
    }
}
