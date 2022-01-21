public class Task {

  private String str;
  private boolean status;
  
  public Task(String str, boolean status) {
    this.str = str;
    this.status = status;
  }

  public Task(String str) {
    Task(str, false);
  }

  public void setStatus(boolean status) {
    this.status = status;
  }

  public String getStr() {
    return str;
  }

  public boolean getStatus() {
    return status;
  }

}