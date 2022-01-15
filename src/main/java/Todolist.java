public class Todolist {

    private Todo[] todos;

    public Todolist() {
        this.todos = new Todo[100];
    }

    public void addTodo(Todo t) {
        for (int i = 0; i < this.todos.length; i++) {
            if (this.todos[i] == null) {
                this.todos[i] = t;
                return;
            }
        }
    }

    @Override
    public String toString() {
        StringBuilder allTodos = new StringBuilder();
        for (int i = 0; i < this.todos.length; i++) {
            if (this.todos[i] != null) {
                allTodos.append(i + 1)
                        .append(". ")
                        .append(this.todos[i].toString())
                        .append("\n");
            } else {
                if (allTodos.length() == 0) {
                    return "No entries found, start by adding one!";
                } else {
                    break;
                }
            }
        }
        return allTodos.toString();
    }
}
