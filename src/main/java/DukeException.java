public class DukeException extends Exception{
    public DukeException(String errMessage){
        super(errMessage);
    }

    @Override
    public String getMessage(){
        return "i dont understand what you mean!";
    }

}
