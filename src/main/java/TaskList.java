import java.util.ArrayList;

public class TaskList {
    ArrayList<Task> actions = new ArrayList<Task>();

    public boolean add(TaskType type, String action) throws EmptyDescriptionException{
        if (action.equals("")) {
            throw new EmptyDescriptionException("ToDo cannot be empty");
        }
        int indexOfTime;
        String description;
        String time;
        switch (type) {
            case TODO :
                return actions.add(new ToDo(action));
            case DEADLINE:
                indexOfTime = action.indexOf("/by");
                description = action.substring(0,indexOfTime);
                time = action.substring(indexOfTime+4);
                return actions.add(new Deadlines(description,time));
            case EVENTS:
                indexOfTime = action.indexOf("/at");
                description = action.substring(0, indexOfTime);
                time = action.substring(indexOfTime + 4);
                return actions.add(new Events(description,time));
        }
         return false;
    }

    public void print(){
        for (int i = 0; i < actions.size(); i++) {
            System.out.print(i + 1);
            System.out.print(".");
            actions.get(i).print();
        }
    }

    public void print(int i){
        actions.get(i - 1).print();
    }

    public void markComplete(int index){
        this.actions.get(index - 1).markCompleted();
    }

    public void markIncomplete(int index){
        this.actions.get(index - 1).markIncompleted();
    }

    public int getLength() {
        return this.actions.size();
    }

    public void delete(int i) {
        actions.remove(i-1);
    }
}
