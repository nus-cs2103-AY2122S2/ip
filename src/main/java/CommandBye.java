class CommandBye extends Command {
    Over over;
    public CommandBye(Over over) {
        this.over = over;
    }

    @Override
    public void execute() {
        Ui.wrapPrint(Response.RESPONSE_GOODBYE);
        over.setOver();
    }
}
