
public class Duke {

    enum functions {
        todo,
        event,
        deadline,
        list,
        mark,
        unmark,
        delete,
        bye
    }

    public static void main(String[] args) throws DukeException {

        BH bh = new BH();
        bh.load();
        bh.greet();
        bh.run();
        bh.save();

    }
}
