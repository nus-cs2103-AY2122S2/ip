public class Task
{
    private int status; // 1 indicates done and 0 indicates not done
    private String name;

    public Task(String name)
    {
        this.status = 0;
        this.name = name;
    }

    public int getStatus()
    {
        return status;
    }

    public String getName()
    {
        return name;
    }

    public void mark()
    {
        this.status = 1;
    }

    public void unmark()
    {
        this.status = 0;
    }
}
