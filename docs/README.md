# User Guide
**Chad-Duke** is a desktop application for managing your
tasks. Even though it provides GUI, the application is optimized
to be used with CLI (Command Line Interface).

* [Quick start](#quick-start)
* [Features](#features)
* [Command summary](#command-summary)


## Quick start
1. Ensure you have Java `11` or above installed in your
   computer.
2. Download the latest `chad_duke.jar` from [here](https://github.com/zunedz/ip/releases/tag/v0.2).
3. Copy the file to the folder you want to use as the
   *home folder* for your Duke.
4. Double-click the file to start the app. The GUI 
   similar to the below should appear in a few seconds.
   
![Duke GUI](Ui.png)

5. Folder `data` will be created as the local storage in your current directory or in your home folder.
6. Type the command in the command box and press `Enter/Send` to
   execute it.
7. Refer to the [Features](#features) below for details of each
   command.
   

## Features
### Listing all tasks
List all tasks available, including todos, deadlines, and events.

Example:

`list`

Output:
```
Here are the tasks in your list!
     1. [T][X] eat breakfast
     2. [D][ ] CS2103T increment (by: Sep 18 2021)
```

### Add a Todo Task
Add a Todo Task with description.

Format:

`todo [description]`

Example:

`todo eat breakfast`

Output:
```
Got it. I've added this task:
  [T][ ] eat breakfast
Now you have 1 tasks in the list.
```

### Add a Deadline Task
Add a Deadline Task with description and deadline in yyyy-MM-dd format.

Format:

`deadline [description] /by [deadline]`

Example:

`deadline CS2103T increment /by 2021-09-18`

Output:
```
Got it. I've added this task:
  [D][ ] CS2103T increment (by: Sep 18 2021)
Now you have 1 tasks in the list.

```

### Add an Event Task
Add an Event Task with description and time in yyyy-MM-dd format.

Format:

`event [description] /at [time]`

Example:

`event attend workshop /at 2021-09-19`

Output:
```
Got it. I've added this task:
  [E][ ] attend workshop (at: Sep 19 2021)
Now you have 1 tasks in the list.
```

### Mark a Task as done
Task needs to be set into done manually by user, by giving the Task index.

Format:

`mark [index]`

Example:

`mark 1`

Output:
```
Nice! I've marked this task as done:
  [E][X] attend workshop (at: Sep 19 2021)
```

### Unmark a Task as not done
Task can be set into undone by giving the Task index.

Format:

`unmark [index]`

Example:

`unmark 1`

Output:
```
OK, I've unmarked this task as not done yet:
  [E][X] attend workshop (at: Sep 19 2021)
```

### Delete a Task
Delete a Task by specifying its index.

Format:

`delete [index]`

Example:

`delete 1`

Output:
```
Got it! I've removed this task:
  [E][X] attend workshop (at: Sep 19 2021)
Now you have 0 tasks in the list.

```

### Undo an action
Undo the latest action and restore the previous version of Task list.

Example:

`undo`

Output:
```
Undo'd, now Here are the tasks in your list!
1. [E][X] attend workshop (at: Sep 19 2021)
```

### Listing available commands
Show the list of available commands and the format for each command.

Example:

`help`

Output:
List of all commands.

### Exiting the application
Exit Duke Chatbot.

Example:

`bye`

Output:
Duke application will be closed.


## Command summary

|action  |Format, Examples|
|--------|----------------|
|list    |`list`|
|deadline|`deadline [description] /by [deadline]`<br>e.g.,`deadline submit assignment /by 2021-09-18`|
|event   |`event [description] /at [time]`<br>e.g.,`event attend workshop /at 2021-09-19`|
|todo    |`todo [description]`<br>e.g.,`todo buy pens`|
|delete  |`delete [index]`<br>e.g., `delete 1`|
|mark    |`mark [index]`<br>e.g., `mark 1`|
|unmark  |`unmark [index]`<br>e.g., `unmark 1`|
|undo    |`undo`|
|help    |`help`|
|bye     |`bye`|

