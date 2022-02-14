package duke.task.tasks;

public interface ITask {
    ITask cloneSelf();

    String switchMark(String instr);

    boolean hasWord(String word);

    String encode();
}
