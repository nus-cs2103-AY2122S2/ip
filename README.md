# Duke project template

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
3. After that, locate the `src/main/java/Duke.java` file, right-click it, and choose `Run Duke.main()` (if the code editor is showing compile errors, try restarting the IDE). If the setup is correct, you should see something like the below as the output:
   ```
   Hello from
    ____        _        
   |  _ \ _   _| | _____ 
   | | | | | | | |/ / _ \
   | |_| | |_| |   <  __/
   |____/ \__,_|_|\_\___|
   ```


# Conan
**_Conan_** is a task manager, who can hep you manage your day easily :v:.
> "The key is not spending time, but investing it." - Stephen R. Covey [(Source)](https://www.inc.com/dan-scalco/22-time-management-quotes-to-inspire-you-to-achieve-your-goals.html) </br>

Conan draws inspiration from [Project Duke](https://nus-cs2103-ay2122s2.github.io/website/se-book-adapted/projectDuke/index.html), that was designed to help learn Software Engineering Techniques.

## Features
+ Run _**Conan**_ on your command line.
+ _**Conan**_ can remember your tasks from previous runs and can recollect them if you want to. :wink:

## User guide

Once you run the program in your Command line, Conan will introduce himself and ask for your name.
Once you are acquainted you could tell Conan the tasks you want to do.

#### Tasks
There are :four: kinds of tasks that Conan can store.
1. **Todo** - Keeps track of the task that do not have to be completed without sometime. </br> Enter : todo {Task} in the command line for such commands.
2. **Event** - Any event that you have to attend an certain date and time, Conan can keep track of that for you. :stopwatch:
   Enter : event {Task} /by YYYY-MM-DD HHMM
3. **Deadline** - Tasks that need to be completed within a time frame. :hourglass:
   Enter : deadline {Task} /by YYYY-MM-DD HHMM
4. **Task** - any general tasks to keep track of.
   Enter : {Task , more than 2 words}

**Note**: Time argument HHMM is not necessary to enter.

#### Operations
+ list : To view the list of tasks lined up.
+ mark {Task Number}: Mark that task as complete.
+ unmark {Task Number}: Mark that task as not complete.
+ delete {Task Number}: Delete that task from the list.
+ due-on {YYYY-MM-DD}: To view tasks that are due on this date.
+ due-before {YYYY-MM-DD}: To view tasks that are due before this date.
+ find {Keyword}: To find tasks containing that keyword.
+ bye: To close the program. Conan stores all your tasks so that you can visit them later.

#### Remembering Tasks
Conan has the ability to remember tasks from your previous runs. If you want to access them, then run the program under the same name you used last time. :smile_cat:
And then type **yes** if you want to continue from last time, otherwise press **no**.

#### Abilities

- [X] Remember Tasks from previous runs, of a single user.
- [ ] Be able to save data of different users on the same Pc.
***
#### For programmers
Open IDE and run `Main.main()`;
```java
public class Main {
    public static void main(String[] args) {
       // Code implementation
    }
}
```
