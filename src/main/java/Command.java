public abstract class Command {
    public String directory;
    public String filePath;
    TaskList taskList = new TaskList(directory,filePath);


    Command (String directory, String filePath) {
        this.directory = directory;
        this.filePath = filePath;

    }

}
