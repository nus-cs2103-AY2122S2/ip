# User Guide

## Features 

### Create tasks
There are 3 types of tasks that can be created in the task list. They are:
#### 1. Todo Task
Description: Task without any date/time attached to it *e.g. cook dinner for friends*.

#### 2. Deadline Task
Description: Task that need to be done before a specific date/time *e.g. finish CS2103 iP by 2022-02-18 23:59*.

#### 3. Event Task
Description: Task that start at a specific time and ends at a specific time *e.g. tP meeting on 2022-02-19 20:00*.

### Tag Todo Task
Todo task could be tag (one word) to provide more information such as priority level *e.g. [urgent]*.

### Delete Task
All tasks could be deleted from the disk.

### Mark Task as Done ✅
All tasks created will have an initial state set as undone. 
User could mark task as done once the task is completed.

### Mark Task as Undone
User could mark task that was previously marked as done to undone.

### List all tasks in the disk 
User could view all tasks and their details (i.e. states, number of tasks left, date of event or deadline).

## Usage

### `todo` - Creates a todo task

Once todo task is created, it is saved to the disk. Todo task need to be followed by a task name.

Example of usage: 

`todo cook dinner for friend`

Expected outcome:
```
Got it. I've added this task:
    [T][ ] cook dinner for friend

Now you have X tasks in the list.    
```
*X refers to the number of tasks in the list

### `deadline` - Creates a deadline task

Once todo task is created, it is saved to the disk. Deadline task need to be followed by 
a task name, deadline date (YYYY-MM-DD), and deadline time HH:MM 24 hr format.

Example of usage:

`deadline CS2103 iP /by 2022-02-18 23:59`

Expected outcome:
```
Got it. I've added this task:
    [D][ ] CS2103 iP (by: FEBRUARY FRIDAY 2022 23:59)

Now you have X tasks in the list.    
```
*X refers to the number of tasks in the list

### `event` - Creates a todo task

Once todo task is created, it is saved to the disk. Event task need to be followed by 
a task name, event date (YYYY-MM-DD), and event time HH:MM 24 hr format.

Example of usage:

`event CS2103 tP meeting /at 2022-02-19 20:00`

Expected outcome:
```
Got it. I've added this task:
    [E][ ] CS2103 tP meeting (at: FEBRUARY SATURDAY 2022 20:00)

Now you have X tasks in the list.    
```
*X refers to the number of tasks in the list

### `tag` - Creates a tag for todo task

Any one word string tag could be given to a todo task. 
To tag, a task number needs to be specified, followed by a one word tag string.
A tag string can only support one word string.

Example of usage:

`tag 1 urgent`

Expected outcome:
```
OK, I've tagged this task: 
[T][ ] cook dinner for friend [urgent]    
```
### `delete` - Deletes task
Task would be deleted from the disk. Delete must be followed by
a task number.

Example of usage:

`delete 1`

Expected outcome:
```
Noted. I've removed this task: 
[T][ ] cook dinner for freind [urgent]

Now you have X tasks in the list.   
```
*X refers to the number of tasks in the list

### `mark` - Mark task as done

Mark is followed by a task number.

Example of usage:

`mark 1`

Expected outcome:
```
Nice! I've marked this task as done: 
[D][✅] CS2103 iP (by: FEBRUARY FRIDAY 2022 23:59)
```

### `unmark` - Unmark task as undone

Unmark is followed by a task number.

Example of usage:

`unmark 1`

Expected outcome:
```
OK, I've marked this task as not done yet:
[D][ ] CS2103 iP (by: FEBRUARY FRIDAY 2022 23:59)
```

### `list` - List all task in the disk

All task in the disk would be shown.

Example of usage:

`list`

Expected outcome:
```
Here are the tasks in your list:
1. [T][ ] cook dinner for friend [urgent]
2. [D][ ] CS2103 iP (by: FEBRUARY FRIDAY 2022 23:59)
3. [E][ ] CS2103 tP meeting (at: FEBRUARY SATURDAY 2022 20:00)
```

### `bye` - Exit the app

Close the app.

Example of usage:

`bye`

Expected outcome:
```
Bye. See you again next time! Have a nice day 😊!
```
