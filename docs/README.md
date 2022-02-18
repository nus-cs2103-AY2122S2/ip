# User Guide

Welcome to Duke's User Guide.

## Features 

Duke's features are designed to allow you to keep track of your tasks

Currently with Duke, you can perform the following:
1. Add a Task 
2. Delete a Task
3. Mark a Task as complete or incomplete
4. List out the current tasks
5. View the tasks scheduled on a specific day

### Feature 1 - Add a Task

Tasks can be categorised into three main types:
a. todos
b. deadlines
c. events 

### Feature 2 - Delete a Task

Tasks can be deleted from the list 

### Feature 3 - Mark a Task as complete or incomplete

Individual Tasks in the list can be updated to be complete or incomplete

### Feature 4 - List out the current tasks

All tasks previously added into the list can be viewed in chronological order of when it was added

### Feature 2 - View the tasks scheduled on a specific day

Tasks that have dates associated (i.e deadlines and events) can be viewed by searching the day (YYYY-MM-DD) that they were scheduled 


## Usage

### `todo` - Adds a todo task

Adds a todo task to the list

Example of usage: 

`todo <name of task>`

Expected outcome:

```
I have added the following task:
[T][ ] <name of task>
```

### `deadline` - Adds a deadline task

Adds a deadline task to the list

Example of usage: 

`deadline <name of task> /by <date in YYYY/MM/DD> <time in HHmm>`
`deadline assignment 1 /by 2022-10-10 1800`

Expected outcome:

```
Got it. I've added this task:
[D][ ] assignment 1 (by: Oct 10 2022 6:00pm) 
```
### `event` - Adds a event task

Adds a event task to the list

Example of usage: 

`event <name of task> /by <date in YYYY/MM/DD> <time in HHmm>`
`event assignment 2 /by 2022-10-10 1900`

Expected outcome:

```
Got it. I've added this task:
[E][ ] assignment 2 (2022-10-10 7:00pm)
```

### `delete` - Deletes a task

Deletes a task from the list

Example of usage: 

`delete 2`

Expected outcome:

```
Noted. I've removed this task:
[E][ ] assignment 2 (by: 2022-10-10 6:00pm) 
Now you have 1 tasks left in the list.
```

### `mark` - Marks a task as complete

Marks a task in the list as complete

Example of usage: 

`mark 1`

Expected outcome:

```
Nice! I've marked this task as done: 
[E][ ] assignment 2 (2022-10-10 7:00pm)
```

### `unmark` - Marks a task as incomplete

Marks a task in the list as incomplete

Example of usage: 

`unmark 1`

Expected outcome:

```
OK, I've marked this task as not done yet:
[E][ ] assignment 2 (2022-10-10 7:00pm)
```

### `list` - Lists out all tasks

List out all the tasks present in the list

Example of usage: 

`list`

Expected outcome:

```
Here are the items in your list:
1. [D][ ] assignment 1 (2022-10-10 6:00pm)
2. [E][ ] assignment 2 (2022-10-10 7:00pm)
```
### `viewSchedule` - Lists out the tasks on the specified day

List out all the tasks in the list that are scheduled to occur within the date given

Example of usage: 

`viewSchedule 2022-10-10`

Expected outcome:

```
1. [D][ ] assignment 1 (2022-10-10 6:00pm)
2. [E][ ] assignment 2 (2022-10-10 7:00pm)
```


