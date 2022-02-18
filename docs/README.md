# User Guide

Do you need a task manager? I have just the thing for you. Duke is a easy to use
task manager which allows you to add and store tasks in a task list. You can also mark task
as done or undone or delete them anytime you wish.

## User Set Up

1. Install the latest Duke jar, download [here](https://github.com/aweijun/ip)
2. Ensure you have Java 11 installed, download [here](https://www.oracle.com/java/technologies/downloads/)
3. Save Duke in any directory that your wish.
4. Launch Duke using `java - jar duke.jar`.
5. Profit!


## Features 

- Add and delete Task into your own personal task manager. 
- Mark task as complete or incomplete.
- Find task within your personal task manager.
- List all the task within your task manager

### Feature- Add Task 

Add any of the 3 type of task!

Format:
1. `todo <NAME>`
2. `deadline <NAME> /by <DATE> <TIME>`
3. `event <NAME> /by <DATE> <TIME>-<TIME>`

Note:
- `<DATE>` is formatted as `dd/mm/yyyy`
- `<TIME>` is formatted as `hhmm`

Sample usage:
```
    todo a
    deadline b /by 2/12/2019 1800
    event c /at 2/12/2019 1800-1900
```

Expected Output:

`todo a`

```
    [T][] a
```
`deadline b /by 2/12/2019 1800`
```
    [D][] b (by Dec 2 2019 6:00PM)
```
`event c /at 2/12/2019 1800-1900`
```
    [E][] c (at Dec 2 2019 6:00PM - 7:00PM)
```

### Feature- Delete Task

Delete any task based on index!

Format:
1. `delete <index>`

Note: `<index>` refers to the id displayed by list command is called.

Sample usage:
```
delete 1
```

Expected Output:
```
Noted. I've removed the task.
[T][] return book
Now you have 0 in the list
```


### Feature- List all Task

List out all the task within the list

Format: `list`

Sample usage:
```
list
```
Expected Output:
```
Here are the tasks in your list:
1. [T][] CS2103T IP
```


### Feature- Mark/UnMark

Mark or unmark tasks based on their completeness.

Format: 
1. `mark <index>`
2. `unmark <index>`

Sample usage:
```
mark 1
unmark 1
```

Expected Output:

`mark 1`

```
Nice! I've marked this task as done
1. [T][X] CS2103T IP
```

`unmark 1`

```
Nice! I've marked this task as not done yet
1. [T][] CS2103T IP
```

### Feature- Mark/UnMark

Find task based on its name.

Format: `find <NAME>`

Note: `<NAME>` can either be the task name
or a segment of the task name

Sample usage:
```
find CS2103T
```

Expected Output:

```
Here are the matching tasks in your list
1. [T][X] CS2103T IP
```

### Feature- Bye

Closes Duke program after displaying a exit message

Format: `bye`

Sample usage:
```
bye
```

Expected Output:

```
Bye! hope to see you agian
```
### Command Summary

| Action | Format | Example |
| ------------- | ------------- | ------------- |
| Todo  | `todo <NAME>`  | `todo a` | 
| Deadline | `deadline <NAME> /by <DATE> <TIME>`  | `deadline b /by 2/12/2019 1800`  | 
| Event | `event <NAME> /by <DATE> <TIME>-<TIME>`  | `event c /at 2/12/2019 1800-1900`  | 
| List | `list` | `list` | 
| Mark | `mark <INDEX>` | `mark 1` | 
| Unmark | `unmark <INDEX>` | `unmark 1` | 
| Remove | `removed <INDEX>` | `remove 1` | 
| Find | `find` | `find` | 
| Bye | `bye`  | `bye` | 