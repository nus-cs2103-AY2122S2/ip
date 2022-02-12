# User Guide For YQ Chatbot

## Features

### Feature - list tasks 

The `list` feature lists all the tasks that are left to do.

### Feature - todo tasks

The `todo` feature allows the addition of a task to be done.

### Feature - event tasks

The `event` feature allows the addition of an event that needs to be attended.

### Feature - deadline tasks

The `deadline` feature allows the addition of a task with a stipulated deadline.

### Feature - delete tasks

The `delete` feature allows a particular task in the list to be deleted.

### Feature - mark tasks

The `mark` feature allows a particular task in the list to be marked as done.

### Feature - unmark tasks

The `unmark` feature allows a particular task in the list to be unmarked as not done.

### Feature - find tasks

The `find` feature allows a particular task in the list to be searched for. 
Relevant results from the lists will be shown.

### Feature - closing the application

The `bye` command closes the application.

### Feature - Saving tasks

The chatbot automatically saves the tasks, even after closing the application.

### Feature - Retrieving past tasks

The chatbot automatically retrieves the past tasks in the list when the application is restarted again.

### Feature - Detecting duplicate task being added

The chatbot automatically checks if the task being added already exists in the list.
If it exists, it will not add it.

## Usage
For example, "deadline read book /by 2022-02-16".
For example, "event Attend meeting /at 2022-02-15".
### `Keyword` - Describe action
Example of usage of todo feature: 
todo - `todo Watch lectures`

Expected outcome:
Adds a task to be done.
```
Got it. I've added this task:
  [T][ ] Watch lectures
Now you have 1 tasks in the list.
```

Example of usage of event feature:
event - `event Attend meeting /at 2022-02-15`

Expected outcome:
Adds an event to be attended.
```
Got it. I've added this task:
  [E][ ] Attend meeting (at: Feb 15 2022)
Now you have 2 tasks in the list.
```
Example of usage of deadline feature:
deadline - `deadline read book /by 2022-02-16`

Expected outcome:
Adds a task with a stipulated deadline.
```
Got it. I've added this task:
  [D][ ] read book (by: Feb 16 2022)
Now you have 3 tasks in the list.
```
Example of usage of list feature:
list - `list`

Expected outcome:
Adds an event to be attended.
```
Here are the tasks in your list:
1.[T][ ] Watch lectures
2.[E][ ] Attend meeting (at: Feb 15 2022)
3.[D][ ] read book (by: Feb 16 2022)
```
Example of usage of delete feature:
delete - `delete 1`

Expected outcome:
Deletes the first event.
```
Noted. I've removed this task:
1.[T][ ] Watch lectures
Now you have 2 tasks in the list.
```
Example of usage of delete feature:
mark - `mark 1`

Expected outcome:
Marks the first task as done.
```
Nice! I've marked this task as done:
[E][X] Attend meeting (at: Feb 15 2022)
```
Example of usage of delete feature:
unmark - `unmark 1`

Expected outcome:
Unmarks the first task as not done.
```
OK, I've marked this task as not done yet:
[E][] Attend meeting (at: Feb 15 2022)
```
Example of usage of delete feature:
find - `find meeting`

Expected outcome:
Returns the first task as it is a relevant search.
```
Here are the matching tasks in your list:
1.[E][] Attend meeting (at: Feb 15 2022)
```