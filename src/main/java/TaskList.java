import java.util.ArrayList;

public class TaskList {
    ArrayList<Task> actions = new ArrayList<Task>();

    public boolean add(TaskType type, String[] inputs) throws EmptyDescriptionException{
        if (inputs[0].equals("")) {
            throw new EmptyDescriptionException("ToDo cannot be empty");
        }

        boolean isAddSuccess = false;
        switch (type) {
        case TODO :
            isAddSuccess = actions.add(new ToDo(inputs[0]));
            break;
        case DEADLINE:
            isAddSuccess = actions.add(new Deadlines(inputs[0],inputs[1]));
            break;
        case EVENTS:
            isAddSuccess = actions.add(new Events(inputs[0],inputs[1]));
            break;
        }

        if (isAddSuccess) {
            DataStore.saveData(type,inputs);
        }

        return isAddSuccess;
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
