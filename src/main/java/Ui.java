public class Ui {

    public void print(Object str) {
        System.out.println(str);
        System.out.println("_".repeat(100));
    }

    public void println(Object str) {
        System.out.println(str);
    }

    public void printBye(){
        this.print("Bye. Hope I've motivated you as much as I could have, and SMILE :D");
    }

    public void throwDukeException(String message) throws DukeException {
        throw new DukeException(message);
    }


}
