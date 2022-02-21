# Task List Assistant 
Task List Assistant helps you keep track of your tasks and more!

## Description
An advanced task list assistant that is flexible and expandable.

![image](docs/Ui.png)

### Features 👓
Currently Supported Types of Tasks: 😊
- Todo Task (normal task with a description)
- Event Task (event task with a date)
- Deadline Task (deadline task with a deadline)
- Supports exporting and importing the task list with a file

### Downloads 🔽
- [Releases](https://github.com/Kidsnd274/ip/releases)

### Usage ⌨
Command List: 📜
- list
- todo (description)
- event (description) /at (date in YYYY-MM-DD format)
- deadline (description) /by (date in YYYY-MM-DD format)
- mark (task number)
- unmark (task number)
- find (keyword)
- delete (task number)
- postpone (task number) (date in YYYY-MM-DD format)
- exit

Some example commands:
```
list
todo borrow book
deadline return book /by 1999-03-03
event project meeting at /at 2022-01-24
list
mark 2
mark 3
list
mark 1
unmark 2
list
quit
```

Exporting and importing task lists:
1. Duke saves to task.txt for every modification to the task list
2. Duke will immediately import tasks from task.txt on startup

### Some code snippets: 👩‍💻
Startup run code:
```java
 public CommandResult getResponse(String string) {
     Command parsedCommand = parser.parseCommand(string);
     CommandResult runCommand = parsedCommand.runCommand();

     if (ExitCommand.isExitCommand(parsedCommand)) { // Checking for exit
     Platform.exit();
     }

     // Flush to disk if modified
     if (runCommand.isModified()) {
     fh.exportTasks(taskList.getList());
     }

     return runCommand;
     }
}
 ```
~~Hi~~ Bye **(Honestly don't know what I'm supposed to add to bold, italic and strikethrough)**

## Credits:
User Icon: <a href="https://www.flaticon.com/free-icons/user" title="user icons">User icons created by Freepik - Flaticon</a>\
Robot Icon: <a href="https://www.flaticon.com/free-icons/robot" title="robot icons">Robot icons created by Good Ware - Flaticon</a>






# OLD README

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
