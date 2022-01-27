public class Parser {
    protected String string;

    Parser(String string) {
        this.string =  string;
    }

    Task parse() {
        Task t = new Task("null");
        switch (string.substring(0,3)) {
        case "[T]":
            t = new Todo(string.substring(7));
            break;
        case "[D]":
            String[] arr = string.split("\\(by: ");
            t = new Deadline(arr[0].substring(7), arr[1].split("\\)")[0]);
            break;
        case "[E]":
            String[] arr2 = string.split("\\(at: ");
            t = new Deadline(arr2[0].substring(7), arr2[1].split("\\)")[0]);
            break;
        }
        return t;
    }
}
