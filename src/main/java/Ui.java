public class Ui {
    public static final String HORIZONTAL_LINE = "\t____________________________________________________________";
    public static final String LOGO = "\t  ____       _       _____   _____\n" +
            "\t |  _\"\\  U  /\"\\  u  |\"_  / u |\"_  / u\n" +
            "\t/| | | |  \\/ _ \\/   U / / /  U / / /\n" +
            "\tU| |_| |\\ / ___ \\   \\/ /_   \\/ /_\n" +
            "\t |____/ u/_/   \\_\\  /____|  /____|\n" +
            "\t  |||_    \\\\    >>  _//<<,- _//<<,-\n" +
            "\t (__)_)  (__)  (__)(__) (_/(__) (_/";

    public void startUp() {
        System.out.println(HORIZONTAL_LINE + "\n\tHello from\n" + LOGO + "\n\n"
                + "\tGood day!\n\tWhat can I do for you?\n" + HORIZONTAL_LINE);
    }
}
