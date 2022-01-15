public class Deadline extends Task{
    public Deadline(String input, int number){
        super(input, number);
        String[] inputArr = input.split("/by ");
        name = inputArr[0].substring(8, inputArr[0].length());
        deadline = inputArr[1];
    }

    @Override
    public String toString(){
        String s = String.format("%d. [D][%s] %s (by: %s)\n", number+1, getStatus(), name, deadline);
        return s;
    }
}
