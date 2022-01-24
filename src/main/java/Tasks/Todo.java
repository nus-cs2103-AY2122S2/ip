package Tasks;

import Duke.*;

public class Todo extends Task {
    public Todo(String description) {
        super(description);
        super.saveFormat = "T," + this.description + "," + super.isDone;
    }
    public Todo(String saveFormat, boolean blean) throws DukeException {
        super(saveFormat);
        try {
            String[] strArr = description.split(",");
            this.description = strArr[1];
            if (Boolean.parseBoolean(strArr[2])){
                super.setDone();
            }
            super.saveFormat = strArr[0] + "," + strArr[1];
        } catch (ArrayIndexOutOfBoundsException e) {
            throw new DukeException();
        }
    }
    @Override
    public String toString(){
        return "T " + "| " + super.toString();
    }
}
