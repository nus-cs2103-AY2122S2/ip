public class Event extends Task{
    public Event(String input, int number){
        super(input, number);
        String[] inputArr = input.split("/at ");
        name = inputArr[0].substring(5, inputArr[0].length());
        deadline = inputArr[1];
    }
    @Override
    public String toString(){
        String s = String.format("%d. [E][%s] %s (at: %s)\n", number+1, getStatus(), name, deadline);
        return s;
    }
}
