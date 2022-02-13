package li.zhongfu.cs2103.chatbot;

import java.io.IOException;

import li.zhongfu.cs2103.chatbot.ui.TextUi;

public class Main {
    public static void main(String[] args) throws IOException {
        Duke duke = new Duke("Duke");
        TextUi ui = new TextUi(duke);
        ui.run();
    }
}
