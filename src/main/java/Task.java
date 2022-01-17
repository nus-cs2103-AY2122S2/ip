public class Task {
  protected boolean status;
  protected String description;

  public Task(String description) {
    this.description = description;
    this.status = false;
  }

  public void setStatus(boolean status) {
    this.status = status;
  }

  public boolean getStatus() {
    return status;
  }

  public String getDescription() {
    return description;
  }

  public void setDescription(String description) {
    this.description = description;
  }

  public String getStatusIcon() {
    return (status ? "X" : " "); // mark done task with X
  }
}
