public class Parser {

    public static Task parse(String nextLine) {
        String[] strArr = nextLine.split(" \\| ");
        String[] subStrArr;
        String command = strArr[0];
        boolean isDone = strArr[1].equals("1") ? true : false;
        switch (command) {
            case "T":
                return new Todo(strArr[2], isDone);
            case "D":
                return new Deadline(strArr[2], strArr[3], isDone);
            case "E":
                return new Event(strArr[2], strArr[3], isDone);
            default:
                return null;
        }
    }
}
