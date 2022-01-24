package tasks;

public abstract class Task {
	protected String detail;
	protected boolean marked;

	public abstract String getType();

	public abstract String getDate();

	public void mark() {
		this.marked = true;
	}

	public void unmark() {
		this.marked = false;
	}

	public String getDetail(){
		return  detail;
	}

	public String getMark(){
		if(marked){
			return "1";
		} else {
			return "0";
		}
	}

	public Task(String detail) {
		this.detail = detail;
		this.marked = false;
	}
}
