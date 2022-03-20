package duke.command;

import duke.exception.DukeException;
import duke.storage.Storage;
import duke.task.Task;
import duke.task.TaskList;
import duke.ui.Ui;

public class TagCommand extends Command {
    public enum TagType {
        TAG,
        UNTAG;
    }

    private int index;
    private TagType tagType;
    private String tagName;

    /**
     * Instantiates a TagCommand with target index, tag type (whether to tag or untag), and name of the tag.
     *
     * @param index int Index of the Task to be tagged.
     * @param tagType TagType Whether the command is tag or untag.
     * @param tagName String Name of the tag.
     */
    public TagCommand(int index, TagType tagType, String tagName) {
        this.index = index;
        this.tagType = tagType;
        this.tagName = tagName;
    }

    /**
     * Executes the Tag command.
     *
     * @param taskList TaskList TaskList object.
     * @param ui       Ui Ui object.
     * @param storage  Storage Storage object.
     * @throws DukeException
     */
    @Override
    public void executeCommand(TaskList taskList, Ui ui, Storage storage) throws DukeException {
        Task targetTask = taskList.getTasks().get(index - 1);
        if (tagType.equals(TagType.TAG)) {
            targetTask.getTagList().addTag(tagName, targetTask);
        } else if (tagType.equals(TagType.UNTAG)) {
            targetTask.getTagList().deleteTag(tagName, targetTask);
        } else {
            throw new DukeException("Unknown error occurred");
        }
        storage.save(taskList);
    }
}
