# User Guide

Tsohg is a task/event manager application that works on any platform

## Quick Start

1. Ensure you have Java 11 or above installed in your Computer.

2. Download the latest [tsohg.jar](https://github.com/MechFroG88/ip/releases/tag/v0.2).

3. Copy the file to the folder you want to use as the home folder for your Tsohg.

4. Run the app with the command `java -jar tsohg.jar`

5. A file named `data.txt` will be created at the same directory to store the application data

## Features 

### Adding Task

Tsohg support 3 different type of tasks:

1. Todo : A normal task
    - Input format : `todo [task_name]`
    - Example input : `todo read book`
2. Deadline : Task that has to be done by certain date
    - Input format : `deadline [task_name] /by [date]`
    - Example input : `deadline assignment /by 2022-12-31`
3. Event : Task that has happens on certain date
    - Input format : `event [task_name] /at [date]`
    - Example input : `event exam /at 2022-12-31`

Note that the date format are in `yyyy-mm-dd`

Example output for todo task:

```
Got it. I've added this task:
[T][L][ ] read book
Now you have 1 tasks in the list
```

### Listing Task

List all the tasks recorded in the application

Input format : `list`
  
Example input : 

```
list
```

Example output :

```
Here are the tasks in your list:
1.[T][L][ ] read book
2.[D][L][ ] assignment (by : Dec 31 2022)
3.[E][L][ ] exam (at : Dec 31 2022)
```

### Deleting Task

Delete a task that is not relavent anymore.

Input format : `delete [index]`

Example input : 

```
delete 1
```

Example output :

```
Noted. I've removed this task:
[T][L][ ] read book
Now you have 0 tasks in the list.
```

### Marking Task

Mark a task as finished.

Input format : `mark [index]`

Example input : 

```
mark 1
```

Example output :

```
Nice! I've marked this task as done:
[T][L][X] read book
```

### Unmarking Task

Unmark a task from finished.

Input format : `unmark [index]`

Example input : 

```
unmark 1
```

Example output :

```
OK, I've marked this task as not done yet:
[T][L][ ] read book
```

### Find Task

Find a task from the list based on matching keyword.

Input format : `find [text]`

Example input : 

```
find book
```

Example output :

```
Here are the matching tasks in your list:
1.[T][L][ ] readbook
2.[T][L][ ] writebook
3.[T][L][ ] bookpop
```

### Change Task's Priority

Change the priority of the Task.

`[L]` indicate low priority

`[H]` indicate high priority

The default priority of all tasks is low

Input format : `priority [index]`

Example input : 

```
priority 1
```

Example output :

```
Here are the matching tasks in your list:
[T][H][ ] readbook
```