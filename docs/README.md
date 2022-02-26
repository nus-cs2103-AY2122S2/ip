# User Guide

Duke is a **Personal Assistant ChatBot desktop application** that can help you to
keep track of various tasks.

![](Ui.png)

## Table of Contents

1. [Quick Start](#quick-start)
2. [Features](#features)
3. [Usage](#usage)
4. [Command Summary](#command-summary)

## Quick Start

1. Ensure that you have ``Java 11`` installed on your Computer.

2. Download the latest release of ```duke.jar``` from [release](https://github.com/dannytayjy/ip/releases).

3. Double-click on the file to start the app.

4. Type in the command in the text box.

5. Press the ```Enter``` key or click on the ```Send``` button to send the command over to Duke.

## Features

### Create tasks

Duke allows tracking of 3 types of tasks:
- ToDo: Tasks without any date/time attached
- Deadline: Tasks that need to be done before a specific date/time
- Event: Tasks that start at a specific date/time and ends at a specific time

### List tasks

Duke can show all the tasks which are currently tracked.

### Change completion status of tasks

Duke can mark a task as done/undone.

### Delete task

Duke can delete any task that user does not want to track anymore.

### Save tasks automatically

Duke automatically saves any changes made into a memory file called `tasks.txt` locally in a folder called `data` and retrieved next time.

### Print tasks occurring on a date

Duke can find tasks occurring on a specified date.

### Find tasks containing a keyword

Duke can find tasks with find tasks by searching for a keyword in the description.

## Usage

### Add a ToDo task: `todo`

Adds a ToDo task to the task list

Format: `todo <description>`
* `description` refers to the description of the ToDo task

Example of usage: `todo Change bedsheet`

Expected outcome:
```
Got it. I've added this task:
     [T][ ] Change bedsheet
Now you have 1 task in the list.
```

### Add a Deadline task: `deadline`

Adds a Deadline task to the task list

Format: `deadline <description> /by <date time>`
* `description` refers to the description of the Deadline task
* `date` refers to the date by which the Deadline task needs to be completed
* `time` refers to the time by which the Deadline task needs to be completed, which can be optional
* For proper recognition of `date time`, use yyyy-MM-dd hhmm format (e.g. 2022-01-21 1800)

Example of usage: `deadline CS2102 Week 6 Tutorial Q3b /by 2022-02-15 0900`

Expected outcome:
```
Got it. I've added this task:
     [D][ ] CS2102 Week 6 Tutorial Q3b (by: Feb 15 2022, 09:00 AM)
Now you have 2 tasks in the list.
```

### Add an Event task: `event`

Adds an Event task to the task list

Format: `event <description> /at <date time>`
* `description` refers to the description of the Event task
* `date` refers to the date at which the Event task is taking place
* `time` refers to the time at which the Event task is taking place, which can be optional
* For proper recognition of `date time`, use yyyy-MM-dd hhmm format (e.g. 2022-01-21 1800)

Example of usage: `event Meet-up with Fabian /at 2022-02-20 1130`

Expected outcome:
```
Got it. I've added this task:
     [E][ ] Meet-up with Fabian (at: Feb 20 2022, 11:30 AM)
Now you have 3 tasks in the list.
```

### List down all tasks: `list`

Displays all the tasks the user currently have and their completion status.
* `[✓]` indicates the task is marked as done
* `[ ]` indicates the task is not markes as done yet

Format: `list`

Example of usage: `list`

Expected outcome:
```
Here are the tasks in your list:
[Legend: T = todo, D = deadline, E = event]

1.   [T][ ] Change bedsheet
2.   [D][ ] CS2102 Week 6 Tutorial Q3b (by: Feb 15 2022, 09:00 AM)
3.   [E][ ] Meet-up with Fabian (at: Feb 20 2022, 11:30 AM)
```

### Mark a task as done: `mark`

Mark an existing task as done with a check mark. <br>
By default, tasks when added are initially marked as undone.

Format `mark INDEX`
* `INDEX` refers to the specified numbering of the task in the List of tasks or filtered tasks
* `INDEX` must be a positive integer 1, 2, 3, ...
* `INDEX` must be valid, i.e., cannot mark task 7 as done if there are only 6 tasks in the List of tasks or filtered tasks

Example of usage: `mark 1`

Expected outcome:
```
Nice! I've marked this task as done:
     [T][✓] Change bedsheet
```

### Mark a task as not done yet: `unmark`

Mark an existing task as not done yet by removing the check mark.

Format: `unmark INDEX`
* `INDEX` refers to the specified numbering of the task in the List of tasks or filtered tasks
* `INDEX` must be a positive integer 1, 2, 3, ...
* `INDEX` must be valid, i.e., cannot mark task 7 as done if there are only 6 tasks in the List of tasks or filtered tasks

Example of usage: `unmark 1`

Expected outcome:
```
OK, I've marked this task as not done yet:
     [T][ ] Change bedsheet
```
### Delete a task: `delete`

Delete the specified task from Duke.

Format `delete INDEX`
* `INDEX` refers to the specified numbering of the task in the List of tasks or filtered tasks
* `INDEX` must be a positive integer 1, 2, 3, ...
* `INDEX` must be valid, i.e., cannot delete task 7 if there are only 6 tasks in the List of tasks or filtered tasks

Example of usage: `delete 1`
```list``` followed by ```delete 1``` deletes the 1st task in Duke.

Expected outcome:
```
Noted. I've removed this task:
     [T][ ] Change bedsheet
Now yoy have 2 tasks in the list.
```
### Print deadlines/events on a specific date: `print`

Print deadlines/events occurring on a specific date.

Format: `print /on <date>`
* The search is not case-sensitive.
* It will search any tasks with the description that contains the keyword partially

Example of usage: `print /on 2022-02-20`

Expected outcome:
```
Here is the task on this date (Feb 20 2022):
[Legend: T = todo, D = deadline, E = event]

1.   [E][ ] Meet-up with Fabian (at: Feb 20 2022, 1130 AM)
```

### Locating tasks by keyword: `find`

Finds existing tasks whose descriptions contain the given keyword.

Format: `find <keyword>`
* The search is not case-sensitive.
* It will search any tasks with the description that contains the keyword partially

Example of usage: `find CS2102`

Expected outcome:
```
Here is the matching task in your list:
[Keyword Search: CS2102]
[Legend: T = todo, D = deadline, E = event]

1.   [D][ ] CS2102 Week 6 Tutorial Q3b (by: Feb 15 2022, 0900 AM)
```

### Exiting the application: `bye`

Exits the application.

Format `bye`

## Command Summary

| Action       | Format, Examples                                                                                                                         |
|:-------------|:-----------------------------------------------------------------------------------------------------------------------------------------|
| **todo**     | `todo <description>` <br> e.g. `todo Change bedsheet`                                                                                    |
| **deadline** | `deadline <description> /by <date time>` <br> e.g. `deadline CS2102 Week 6 Tutorial Q3b /by 2022-02-15 0900`                             |
| **event**    | `event <description> /at <date time>` <br> e.g. ``event Meet-up with Fabian /at 2022-02-20 1130``                                        |
| **list**     | `list`                                                                                                                                   |
| **mark**     | `mark INDEX` <br> e.g. `mark 1`                                                                                                          |
| **unmark**   | `unmark INDEX` <br> e.g. `unmark 1`                                                                                                      |
| **delete**   | `delete INDEX` <br> e.g. `delete 1`                                                                                                      |
| **print**    | `print /on <date>` <br> e.g. `print /on 2022-02-20`                                                                                      |
| **find**     | `find KEYWORD` <br> e.g. `find CS2103`                                                                                                   |
| **help**     | `help`                                                                                                                                   |
| **bye**      | `bye`                                                                                                                                    |
