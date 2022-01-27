import bot.Bot;
import taskservice.FileSystemTaskService;

public class Duke {
    public static void main(String[] args) throws Exception {
        final Bot bot = new Bot(new FileSystemTaskService("/test"));
        bot.start(System.in, System.out);
    }
}
