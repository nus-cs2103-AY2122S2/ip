public class Ui {

    public Ui() {}

    public void display(String s) {
        System.out.println(s);
    }

    public void greetings() {
        String logo = " .d8888b.\n"
                + "d88P  Y88b\n"
                + "888    888\n"
                + "888         .d88b.  88888b.  88888b.   .d88b.  888d888\n"
                + "888        d88\"\"88b 888 \"88b 888 \"88b d88\"\"88b 888P\"\n"
                + "888    888 888  888 888  888 888  888 888  888 888\n"
                + "Y88b  d88P Y88..88P 888  888 888  888 Y88..88P 88\n"
                + " \"Y8888P\"   \"Y88P\"  888  888 888  888  \"Y88P\"  888\n";
        print("\n" + logo + "\n" + Connor.CURRENT_VERSION);
        print(Connor.LINE);
        print("Hi, my name is Connor! I'm your personalised android assistant.\n"
                + "Loading your current tasks...");
        print(Connor.LINE);
    }

    private void print(String s) {
        System.out.println(s);
    }

}
