public class DukeException extends Exception{
    private String desc;
    public DukeException(String s){
        super(s);
    }
    public String toString(){
        String s = "Duke Exception Occured:\n";
        s += this.desc;
        return s;
    }
}
