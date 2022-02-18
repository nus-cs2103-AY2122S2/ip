# User Guide
**Duke Chatbot** is a desktop application for managing your
tasks. Even though it provides GUI, the application is optimized
to be used with CLI (Command Line Interface).

* [Quick start](#quick-start)
* [Features](#features)
* [Command summary](#command-summary)


## Quick start
1. Ensure you have Java `11` that supports `JavaFx` or above installed in your
   computer.
2. Download the latest `hashira-duke.jar` from [here](https://github.com/albertsutz/ip/releases/tag/v0.2).
3. Copy the file to the folder you want to use as the
   *home folder* for your Duke.
4. Double-click the file to start the app. The GUI
   similar to the below should appear in a few seconds.

![Duke GUI](Ui.png)

5. Folder `data` will be created as the local Duke storage in your home folder.
6. Type the command in the command box and press `Enter/Send` to
   execute it.
7. Refer to the [Features](#features) below for details of each
   command.


## Features
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
Here's the list of task!
     1. [T][X] train with Rengoku
     2. [E][ ] Dinner with parents (at: parent's house)
```

### Add a Todo Task
Add a Todo Task with description.

Format:

`todo [description]`

Example:

`todo code ip project`

Output:
```
Got it. I've added this task:
  [T][ ] code ip project
Now you have 1 tasks in the list.
```

### Add a Deadline Task
Add a Deadline Task with description and deadline in d/M/yyyy format.

Format:

`deadline [description] /by [deadline]`

Example:

`deadline submit assignment /by 20/2/2022`

Output:
```
Got it. I've added this task:
  [D][ ] submit assignment (by: February 20 2022)
Now you have 1 tasks in the list.

```

### Add an Event Task
Add an Event Task with description and place in `String` format.

Format:

`event [description] /at [place]`

Example:

`event finish hackathon /at RC4 L1`

Output:
```
Got it. I've added this task:
  [E][ ] attend workshop (at: RC4 L1)
You have 1 tasks in the list.
```

### Mark a Task as done
Mark a task of given the index as done <br>
Note: Task needs to be set into done manually by user, by giving the Task index.

Format:

`mark [index]`

Example:

`mark 1`

Output:
```
Nice! I've marked this task as done:
  [E][] finish hackathon (at: RC4 L1)
```

### Unmark a Task as not done
Mark a task of given the index as not done <br>
Note: Task can be set into undone by giving the Task index.

Format:

`unmark [index]`

Example:

`unmark 1`

Output:
```
OK, I've unmarked this task as not done:
  [E][X] finish hackathon (at: RC4 L1)
```

### Delete a Task
Delete a task given the index.

Format:

`delete [index]`

Example:

`delete 1`

Output:
```
Ok, I will remove this task:
   [T][X] Submit CS2103 Assignment
```

### Get a Simple Reminder of Upcoming Task
Get a list of upcoming task before a specified time range.

Format:

`reminder [num] [timeId]`<br>
Note: timeId consists of :
- "day" or "days"
- "week" or "weeks"
- "month" or "months"

Example
`reminder 1 day`

Output:
```aidl
Reminder! Here's your task that is due in under 1 day
1. [D][] help mother(by:February 18 2022)
```

### Exiting the application
Terminate Duke Chatbot.

Example:

`bye`

Output:<br>
```Bye. Hope to see you again soon!<br>
Let your heart ablaze!
```

### View Help Page
User can view all the commands using the help command.<br>

Example:<br>

`help`

Output:
```
      Here are the list of all commands I can do!
      1. list: List all of your task
      
      2. todo <task info>: Add a common todo task
      
      3. event <event info> /at <event place>: Add an event task with its place
      
      4. deadline <deadline info> /by <date>: Add a deadline task with a date
      
      5. delete <int:index>: Delete a task of index given
      
      6. mark <int:index>: Mark a task of index given as done
      
      7. unmark <int:index>: Mark a task of index given as not done
      
      8. find <keyword>: Find all tasks with reference to the keyword
      
      9. save: Save your tasks deliberately for future uses
      
      10. reminder <int:num> <String:id>: Get the list of tasks that are due before a time range
      
      Note: id can be in `days`, `weeks` or `months`
      
      10. bye: Terminate the program
```

## Command summary

| action   | Format, Examples                                                                                  |
|----------|---------------------------------------------------------------------------------------------------|
| list     | `list`                                                                                            |
| help     | `help`                                                                                             |
| deadline | `deadline [description] /by [deadline]`<br>e.g.,`deadline submit cs2103 IP /by 18/2/2022`         |
| event    | `event [description] /at [place]`<br>e.g.,`event hackathon /at SoC COM2`                          |
| todo     | `todo [description]`<br>e.g.,`todo coding with friends`                                           |
| delete   | `delete [index]`<br>e.g., `delete 1`                                                              |
| mark     | `mark [index]`<br>e.g., `mark 1`                                                                  |
| unmark   | `unmark [index]`<br>e.g., `unmark 1`                                                              |
| reminder | `reminder [num] [timeid]`<br>e.g., `reminder 1 day`<br/>`reminder 2 week`<br/> `reminder 3 month` |
| bye      | `bye`                                                                                             |