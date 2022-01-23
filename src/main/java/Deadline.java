class Deadline extends Task{
    private String metaInfo;
    
    public Deadline(String description, String metaInfo) {
        super(description);
        this.metaInfo = metaInfo;
    }

    @Override
    public String getTaskIcon() {
        return "[D]";
    }
}