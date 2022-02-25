# Duke User Guide

An application which aims to help you to manage your tasks through the use of Command Line Interface (CLI). 
With a variety of features, this will help the users to sort their tasks in an efficient manner.

## Features 

1. Add task
   - You can add task to this application.
2. Delete task
   - You can delete the tasks you previously added.
3. Find task
   - You can find the tasks which have the common keyword.
4. Mark/Unmark task
   - You can mark/unmark task to indicate whether it has been done.
5. View task
   - You can view all the tasks on a specific day too.
6. List task
   - You can list all the tasks you added.

## Usage

### 1. Add Task
There are three different types of tasks - Todo, Deadline and Event.
Todo is for tasks without time while Deadline and Event have time component.

**Add Todo**

Format:

- `todo TASK`
- TASK are the tasks to be added. It cannot be null/empty.

Example of usage: 

`todo help kris do cs2103`

Expected outcome:

```
Got it. I've added this task:
[T][ ] help kris do cs2103
Now you have 1 tasks in the list.
```
**Add Deadline**

Format:

- `deadline TASK /by DATE TIME`
- TASK are the tasks to be added. It cannot be null/empty.
- DATE are the date which the tasks are due by. It should be entered with this format: `DD/MM/YYYY`. It cannot be null/empty.
- TIME are the time which the tasks are due by. It should be entered with this format: `HHMM`. It cannot be null/empty.

Example of usage: 

`deadline help kris do cs2103 /by 18/2/2022 2359`

Expected outcome:

```
Got it. I've added this task:
[D][ ] help kris do cs2103 (by: Feb 18 2022 11:59 PM)
Now you have 1 tasks in the list.
```

**Add Event**

Format:

- `event TASK /at DATE TIME`
- TASK are the tasks to be added. It cannot be null/empty.
- DATE are the date which the tasks are due by. It should be entered with this format: `DD/MM/YYYY`. It cannot be null/empty.
- TIME are the time which the tasks are due by. It should be entered with this format: `HHMM`. It cannot be null/empty.

Example of usage: 

`event help kris do cs2103 /at 18/2/2022 1500`

Expected outcome:

```
Got it. I've added this task:
[E][ ] help kris do cs2103 (by: Feb 18 2022 3:00 PM)
Now you have 1 tasks in the list.
```

### 2. Delete Task

Format:

- `delete TASK_INDEX`
- TASK_INDEX are the index of the tasks. The index can be viewed by typing `list`. It cannot be null/empty.

Example of usage: 

`delete 1`

Expected outcome:

```
Noted.I've removed this task:
[T][ ] help kris do cs2103
Now you have 2 tasks in the list.
```

### 3. Find Task

Format:

- `find KEYWORD`
- KEYWORD are the keyword found in the tasks. It cannot be null/empty

Example of usage: 

`find cs2103`

Expected outcome:

```
Here are the matching tasks in your list:
1.[D][ ] help kris do cs2103 (by: Feb 18 2022 11:59 PM)
2.[E][ ] help kris do cs2103 (at: Feb 18 2022 3:00 PM)
```

### 4. Mark/Unmark Task

**Mark Task**

Format:

- `mark TASK_INDEX`
- TASK_INDEX are the index of the tasks. The index can be viewed by typing `list`. It cannot be null/empty.

Example of usage: 

`mark 1`

Expected outcome:

```
Nice!I've marked this task as done:
[D][X] help kris do cs2103 (by: Feb 18 2022 11:59 PM)
```

**Unmark Task**

Format:

- `unmark TASK_INDEX`
- TASK_INDEX are the index of the tasks. The index can be viewed by typing `list`. It cannot be null/empty.

Example of usage: 

`unmark 1`

Expected outcome:

```
OK,I've marked this task as not done yet:
[D][ ] help kris do cs2103 (by: Feb 18 2022 11:59 PM)
```
### 5. View Task

Format:

- `view DATE`
- DATE are the date which the tasks are due by. It should be entered with this format: `DD/MM/YYYY`. It cannot be null/empty.

Example of usage: 

`view 18/2/2022`

Expected outcome:

```
Your task for the day,Feb 18 2022:
1.[E][ ] help kris do cs2103 (at: Feb 18 2022 3:00 PM)
2.[D][ ] help kris do cs2103 (by: Feb 18 2022 11:59 PM)
```
### 6. List Task

Format:

- `list`

Example of usage: 

`list`

Expected outcome:

```
Here are the tasks in your list:
1.[D][ ] help kris do cs2103 (by: Feb 18 2022 11:59 PM)
2.[E][ ] help kris do cs2103 (by: Feb 18 2022 3:00 PM)
```

