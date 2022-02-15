# User Guide

Productivilisation is your own personal task tracker chatbot!

- [Quick Start](#quick-start)
- [Features](#features)
    - [Adding a Todo task: `todo`](#adding-a-todo-task-todo)
    - [Adding a Deadline task: `deadline`](#adding-a-deadline-deadline)
    - [Adding a Event task: `event`](#adding-an-event-event)
    - [Listing all tasks: `list`](#listing-all-tasks-list)
    - [Deleting a task: `delete`](#deleting-a-task-delete)
    - [Finding tasks by keywords: `find`](#finding-tasks-by-keywords-find)
    - [Marking a task: `mark`](#marking-a-task-mark)
    - [Unmarking a task: `unmark`](#unmarking-a-task-unmark)
    - [Specifying priority of task](#specifying-priority-of-task-pri)
    - [Exiting the program: `bye`](#exiting-the-program-bye)
    - [Saving the data](#saving-the-data)
- [FAQ](#faq)
- [Command Summary](#command-summary)

## Quick Start

1. Ensure you have Java 11 or above installed in your Computer.
2. Download the Productivilisation.jar from [here](https://github.com/DannyDakota/ip/releases/tag/A-Release).
3. Copy the file to the folder you want to use as the home folder.
4. Double-click the file to start being productive!

## Features


### Adding a ToDo task: `todo`

Creates a ToDo task in the task tracker

Format: `todo {TASK}`

Examples:
- `todo finish this`
- `todo eat chicken`

### Adding a Deadline task: `deadline`

Creates a Deadline task in the task tracker

Format: `deadline {TASK} /by {DATE} {TIME}`

- The date must in the format YYYY-MM-DD e.g. `2022-02-14`
- The time must be in the 24-hour format HHMM e.g. `1800`

Examples:
- `deadline finish assignment /by 2022-02-02 2100`
- `deadline submit iP /by 2022-02-22 1800`

### Adding a Event task: `event`

Creates an Event task with a date and start time in the task tracker

Format: `event {TASK} /at {DATE} {TIME}`

- The date must in the format YYYY-MM-DD e.g. `2022-02-14`
- The start time and end time must both be in the 24-hour format HHMM e.g. `1800`

Examples:
- `event watch anime /at 2022-02-02 1800`
- `event mother's birthday /at 2022-02-02 2000`

### Listing all tasks: `list`

Lists all tasks in the task tracker

Format: `list`

### Deleting a task: `delete`

Deletes a specified task from the task tracker

Format: `delete {TASK_NUMBER}`

- Deletes the task at the specified `INDEX`. The index refers to the index number shown in the displayed task list
- The index must be a positive integer 1, 2, 3, …​
- The index must be within the range of the number of tasks in the task list e.g. `delete 4` with 3 tasks in the list will result in the chatbot replying with `EH HULLO!! TASK DOES NOT EXIST! CHECK AGAIN HEHE`

Examples:
- `delete 1`

### Finding tasks by keywords: `find`

Finds all tasks that match a given keyword

Format: `find {KEYWORD}`

- The keyword is case-sensitive e.g. `Cat` will not match `cat`
- Only the task description is searched
- Full words will be matched e.g. `was` will match tasks with `wash`
- Only tasks matching the entire keyword input will be matched e.g. `wash car` will not match `wash clothes`

Example Usage:

`find assignment`

Expected outcome:

Finds all tasks with description containing `anime`

```
Here are the matching tasks in your list:
1. [E][X][H] event watch anime (at: Feb 2 2022 0900AM)
3. [D][ ][L] deadline anime assignment (by: Feb 2 2022 0900PM)
```

### Marking a task: `mark`

Marks a task as done

Format: `mark {TASK_NUMBER}`

- Marks the task at the specified `INDEX` as done. The index refers to the index number shown in the displayed task list
- The index must be a positive integer 1, 2, 3, …​
  The index must be within the range of the number of tasks in the task list e.g. `mark 20` with 2 tasks in the list will result in the chatbot replying with `EH HULLO!! TASK DOES NOT EXIST! CHECK AGAIN HEHE`

Examples:
- `mark 4`

### Unmarking a task: `unmark`

Marks a task as not done

Format: `unmark {TASK_NUMBER}`

- Marks the task at the specified index as not done. The index refers to the index number shown in the displayed task list
- The index must be a positive integer 1, 2, 3, …​
  The index must be within the range of the number of tasks in the task list e.g. `unmark 20` with 2 tasks in the list will result in the chatbot replying with `EH HULLO!! TASK DOES NOT EXIST! CHECK AGAIN HEHE`

Examples:
- `unmark 1`

### Specifying priority of task: `/pri`

Allows you to specify the task priority, allowing you to better manage your tasks according to the priority level

Format: `{TASK} /pri {LOW/MEDIUM/HIGH}`

- The priority extension works for ToDo, Deadline and Event tasks
- For Event tasks, format would be e.g `event CS2040S tutorial /at 2020-01-01 1000 /pri low`
- For Deadline tasks, likewise
- You can also choose not to specify priority by not specifying /pri

Example command usage and their outputs:

```
Command: todo eat lunch /pri medium
Output: [T][ ][M] eat lunch
Command: deadline thesis submission /by 2022-02-02 2000 /pri high
Output: [D][ ][H] thesis submission (by: Feb 2 2022 0800PM)
Command: event CS2040S tutorial /at 2022-01-01 1000 /pri low
Output: [E][ ][L] CS2040S tutorial (at: Jan 1 2022 1000AM)
```

### Exiting the program: `bye`

Exits the program

Format: `bye`

### Saving the data

Data in Productivilisation automatically saved in the hard disk on your computer after any changes to the task tracker

## FAQ

**Q:** How do I transfer my data to another Computer?

>**A:** Install the app in the other computer and overwrite the empty data file it creates with the file that contains the data file of your previous Productivilisation application

**Q:** Why should I use Productivilisation?

>**A:** Because I spent a long time on it :grinning:


## Command Summary

| Action             | Format, Examples                                                                   |
|--------------------|------------------------------------------------------------------------------------| 
| **Add to-do task** | `todo {TASK}` e.g. `todo watch anime`                                              |
| **Add deadline**   | `deadline {TASK} /by {DATE} {TIME}` e.g. `deadline fix specs /by 2021-02-15 2100`  |
| **Add event**      | `event {TASK} /at {DATE} {TIME}` e.g. `event wash car /at 2022-03-01 1800 to 1900` |
| **List**           | `list`                                                                             |
| **Delete**         | `delete {TASK_NUMBER}` e.g. `delete 2`                                             |
| **Find**           | `find KEYWORD` e.g. `find cat`                                                     |
| **Mark**           | `mark {TASK_NUMBER}` e.g. `mark 3`                                                 |
| **Unmark**         | `unmark {TASK_NUMBER}` e.g. `unmark 2`                                             |
| **Priority**       | `{TASK SYNTAX} /pri {HIGH/MEDIUM/LOW}` e.g. `[T][ ][M] eat lunch`                  |
| **Exit**           | `bye`                                                                              |

