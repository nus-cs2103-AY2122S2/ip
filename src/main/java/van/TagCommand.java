package van;

public class TagCommand implements Command {
    private boolean isAddTag;
    private String tag;
    private int index;

    public TagCommand(boolean isAddTag,int index, String tag) {
        this.isAddTag = isAddTag;
        this.index = index;
        this.tag = tag;
    }

    public boolean executeCommand(Ui ui, TaskList taskList, Storage storage) {
        try {
            if (index > taskList.getTaskCount()) {
                throw new VanException("Task number out of range");
            }
            if (isAddTag) {
                taskList.addTag(index, tag);
            } else {
                taskList.deleteTag(index, tag);
            }
            ui.tagMessage(isAddTag);
        } catch (VanException ex) {
            System.out.println(ex);
        }
        return false;
    }
}