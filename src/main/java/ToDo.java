class ToDo extends Task {
  public ToDo(String name) {
    super(name);
  }

  @Override
  String toSave() {
    int doneBit = isDone ? 1 : 0;
    return String.format("T,.,%d,.,%s\n", doneBit, name);
  }

  @Override
  public String toString() {
    return String.format("[T]%s", super.toString());
  }
}
