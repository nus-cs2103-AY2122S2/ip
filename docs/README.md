# Duked App User Guide
**Duked App** is a desktop application to manage your tasks efficiently. This application is highly optimized for those who are comfortable using the CLI (typing) in the GUI.

* [Quick start](#quick-start)
* [Features](#features)
* [Command summary](#command-summary)


## Quick start
1. Ensure you have Java `11` that supports `JavaFx` or above installed in your
   computer.
2. Download the latest `duke.jar` from [here](https://github.com/ThePrevailingOne/ip/releases/tag/BCD-Extension).
3. Copy the file to the folder you want to use as the
   *home folder* for your Duke.
4. Double-click the file to start the app. The GUI
   similar to the below should appear in a few seconds.

![Duke GUI](Ui.png)

5. Type the command in the command box and press `Enter/Send` to
   execute it.
6. Refer to the [Features](#features) below for details of each
   command.


## Features
The app will autosave any changes made to the list of tasks.

### Listing all tasks
List all your tasks available.
Tasks may include:
- todos,
- deadlines,
- and events.

Command example:

`list`

Output:
```
Here is your current tasks:
     1. [T][X] make a todo
     2. [E][ ] Do not exist (at: Wednesday, 12 Dec 1212 10:00)
```

### Add a Todo Task
Add a Todo Task with description.

Format:

`todo [description]`

Example:

`todo make a todo`

Output:
```
Got it. I've added this task:
  [T][ ] make a todo
You currently have 12 tasks in the list.
```

### Add a Deadline Task
Add a Deadline Task with description and deadline in dd/MM/yyyy HHmm format.
See [here for more about the date formatting](https://www.baeldung.com/java-datetimeformatter).

Format:

`deadline [description] /by [deadline]`

Example:

`deadline submit assignment /by 20/2/2022 1212`

Output:
```
Got it. I've added this task:
  [D][ ] submit assignment (by: Monday, 20 Feb 2022, 12:12)
You currently have 12 tasks in the list.
```

### Add an Event Task
Add a Deadline Task with description and event time in dd/MM/yyyy HHmm format.
See [here for more about the date formatting](https://www.baeldung.com/java-datetimeformatter).

Format:

`event [description] /at [event]`

Example:

`event finish hackathon /at 20/2/2022 1212`

Output:
```
Got it. I've added this task:
  [E][ ] finish hackathon (at: Monday, 20 Feb 2022, 12:12)
You currently have 12 tasks in the list.
```

### Mark a Task as done
Mark a task of given the index as done <br>
Note: Task needs to be set into done manually by user, by giving the Task index. Task index can be obtained through the command `list`.

Format:

`mark [index]`

Example:

`mark 1`

Output:
```
Nice! I've marked this task as done:
  [E][X] finish hackathon (at: Monday, 20 Feb 2022, 12:12)
```

### Unmark a Task as not done
Mark a task of given the index as not done <br>
Note: Task needs to be set into not done manually by user, by giving the Task index. Task index can be obtained through the command `list`.

Format:

`unmark [index]`

Example:

`unmark 1`

Output:
```
Nice! I've marked this task as not done:
  [E][ ] finish hackathon (at: Monday, 20 Feb 2022, 12:12)
```

### Delete a Task
Delete a task given the index. Task index can be obtained through the command `list`.

Format:

`delete [index]`

Example:

`delete 1`

Output:
```
Got it. I've removed this task:
   [T][X] Submit CS2103 Assignment
```
### Sorting the tasks
Display a sorted list of the tasks alphabetically by their description.

Format:

`sort`

Example:

`sort`

Output:
```
Here are the sorted tasks:
   1. [T][X] Procrastinate CS2103 Assignment
   2. [T][X] Submit CS2103 Assignment
You have 12 sorted results.
```
### Sorting the deadlines
Display a sorted list of the deadlines by their deadlines from the earliest.

Format:

`sortdeadline`

Example:

`sortdeadline`

Output:
```
Here are the sorted tasks:
   1. [D][X] Procrastinate CS2103 Assignment (by: Monday, 20 Feb 2022, 12:12)
   2. [D][X] Submit CS2103 Assignment (by: Tuesday, 21 Feb 2022, 12:12)
You have 2 sorted results.
```

### Sorting the events
Display a sorted list of the events by their event time from the earliest.

Format:

`sortevent`

Example:

`sortevent`

Output:
```
Here are the sorted tasks:
   1. [E][X] Procrastinate CS2103 Assignment (at: Monday, 20 Feb 2022, 12:12)
   2. [E][X] Submit CS2103 Assignment (at: Tuesday, 21 Feb 2022, 12:12)
You have 2 sorted results.
```

### Exiting the application
Terminate Duked App.

Example:

`bye`

Output:<br>
```
Bye. Hope to see you again soon!
```

## Command summary

| action   | Format, Examples                                                                                  |
|----------|---------------------------------------------------------------------------------------------------|
| list     | `list`                                                                                         |
| deadline | `deadline [description] /by [deadline]`<br>e.g.,`deadline submit cs2103 IP /by 18/2/2022 1212`         |
| event    | `event [description] /at [event time]`<br>e.g.,`event hackathon /at 18/2/2022 1212`                          |
| todo     | `todo [description]`<br>e.g.,`todo coding with friends`                                           |
| delete   | `delete [index]`<br>e.g., `delete 1`                                                              |
| mark     | `mark [index]`<br>e.g., `mark 1`                                                                  |
| unmark   | `unmark [index]`<br>e.g., `unmark 1`                                                              |
| bye      | `bye`                                                                                             |
