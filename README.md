# duke.Duke project template

This is a project template for a greenfield Java project. It's named after the Java mascot _Duke_. Given below are instructions on how to use it.

## Setting up in Intellij

Prerequisites: JDK 11, update Intellij to the most recent version.

1. Open Intellij (if you are not in the welcome screen, click `File` > `Close Project` to close the existing project first)
1. Open the project into Intellij as follows:
   1. Click `Open`.
   1. Select the project directory, and click `OK`.
   1. If there are any further prompts, accept the defaults.
1. Configure the project to use **JDK 11** (not other versions) as explained in [here](https://www.jetbrains.com/help/idea/sdk.html#set-up-jdk).<br>
   In the same dialog, set the **Project language level** field to the `SDK default` option.
3. After that, locate the `src/main/java/duke.Duke.java` file, right-click it, and choose `Run duke.Duke.main()` (if the code editor is showing compile errors, try restarting the IDE). If the setup is correct, you should see something like the below as the output:
   ```
   Hello from
    ____        _        
   |  _ \ _   _| | _____ 
   | | | | | | | |/ / _ \
   | |_| | |_| |   <  __/
   |____/ \__,_|_|\_\___|
   ```

# DDXTodo

> Your time is limited, so don't waste it living someone else's life. - [Steve Jobs](https://news.stanford.edu/2005/06/14/jobs-061505/#:~:text=Your%20time%20is%20limited%2C%20so,follow%20your%20heart%20and%20intuition.)

**DDXTodo** frees your mind of having to remember things you need to do and helps you live a more efficient life.

 It's,
- text-based
- easy to learn
- ~~FAST~~ SUPER FAST to use

All you need to do is,

1. download it from [here](https://github.com/ddx-510/ip/releases/tag/A-Jar).
2. double-click it.
3. add your tasks.
4. let it manage your tasks for you 

And it is FREE!

Features:

- [x]  Managing tasks
- [x]  Managing deadlines
- [x]  Event Reminders
- [ ] Categorize tasks (Coming soon)

---

If you Java programmer, you can use it to practice Java too. Here's the `main` method:

``` java
public class Main {
    public static void main(String[] args) {
        Application.launch(MainApp.class, args);
    }
}
```
