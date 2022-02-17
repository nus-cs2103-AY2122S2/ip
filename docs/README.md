# User Guide
Duke is a interactive chatbot with UI.
> “Software and cathedrals are much the same; first we build them, then we pray.”

Duke is a task tracker that can track three categories of tasks:
- Todo
- Deadline
- Event

## Features 
1) `todo`
2) `deadline`
3) `event`
4) `list`
5) `delete`
6) `mark`
7) `unmark`
8) `snooze`
9) `bye`

## Usage

### `todo NAME` - creates a Todo task with name NAME

Example of usage: 

`todo work`

Expected outcome:

Expected outcome:
Creates a new Todo object called work
*Take note of the space between todo and NAME!

### `deadline NAME /by DATE TIME(optional)` - creates a Deadline task with name NAME at DATE and TIME

Example of usage:
```
deadline report submission /by 2022-02-14 23:59
deadline report submission /by 2022-02-14
```

Expected outcome:
Creates a new Deadline object with DATE and TIME(optional)
*Format of date: yyyy-mm-dd
*Format of time: hh:mm

### `event NAME /at DATE TIME(optional)` - creates a Event task with name NAME at DATE and TIME

Example of usage:
```
event meeting /at 2022-02-12 01:00
event meeting /at 2022-02-12
```

Expected outcome:
Creates a new Event object with DATE and TIME(optional)
*Format of date: yyyy-mm-dd
*Format of time: hh:mm

### `list` - lists out all tasks

Example of usage:
```
list
```

Expected outcome:
A list of all tasks in their respective formats

### `delete INDEX` - deletes task at specific INDEX

Example of usage:
```
delete 1
```

Expected outcome:
Delete task from tasklist and prints the deleted task
*Recommended to use alongside `list` command so that you get the right index
*Important to delete a valid index - Do not go out of bounds!

### `mark INDEX` - marks task as done

Example of usage:
```
mark 0
```

Expected outcome:
Marks task and prints the marked task
*Recommended to use alongside `list` command so that you get the right index

### `unmark INDEX` - marks task as undone

Example of usage:
```
unmark 0
```

Expected outcome:
Marks task and prints the marked task
*Recommended to use alongisde `list` command so that you get the right index

### `snooze NAME DATE /t NEWDATE NEWTIME` - changes the date & time of task

Example of usage:
```
snooze meeting 2022-02-12 /t 2022-03-20 23:59
```

Expected outcome:
Sets new task date and time and prints new date and time for task

__Keeping track of the exact time is difficult__, which is why all you need to input is the date of the task and not the time.
*Getting the exact name and date of the task is important


