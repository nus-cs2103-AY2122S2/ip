# User Guide

## Features 

### Add tasks

There are three different types of tasks that can be added.
1. Deadline: contains task description and date of event
2. Event: contains task description and date of event
3. Todo: Contains task description

### View all tasks
Displays a list of the tasks that is stored.

### Search for tasks
Search for tasks by keywords in the description.

### Update task 
Can mark/unmark a task that is completed/incomplete.

### Delete task
Deletes task from the list of tasks stored.

## Usage

### `deadline` - Add deadline task

Add deadline task containing a description and date in YYYY-MM-DD format.

Example of usage: `deadline assignment 2 /by 2022-02-18`

Expected outcome:
```
Got it. I've added this task:
  [D][] assignment 2 (by: Feb 18 2022)
Now you have 1 tasks in the list.
```

### `event` - Add event task

Add event task containing a description and date.

Example of usage: `event valentine's day dinner /at 14 feb 7pm`

Expected outcome:
```
Got it. I've added this task:
  [E][] valentine's day dinner (at: 14 feb 7pm)
Now you have 2 tasks in the list.
```

### `todo` - Add todo task

Add todo task containing a description.

Example of usage: `todo read book`

Expected outcome:
```
Got it. I've added this task:
  [T][] read book
Now you have 3 tasks in the list.
```

### `list` - list tasks

Displays the list of tasks with its type, status, description, date (if any).

Example of usage: `list`

Expected outcome:
```
Here are the tasks in your list:
1.[D][] assignment 2 (by: Feb 18 2022)
2.[E][] valentine's day dinner (at: 14 feb 7pm)
3.[T][] read book
```

### `mark` - mark task as done

Marks a task as done according to the task number in the task list.

Example of usage: `mark 3`

Expected outcome:
```
Good job! I've marked this task as done:
[T][X] read book
```

### `unmark` - mark task as not done

Marks a task as not done according to the task number in the task list.

Example of usage: `unmark 3`

Expected outcome:
```
Okay, I've marked this task as not done yet:
[T][] read book
```

### `delete` - delete task 

Deletes task from the task list according to the task number in the task list.

Example of usage: `delete 3`

Expected outcome:
```
Noted. I've removed this task:
[T][] read book
Now you have 2 tasks in the list.
```

### `find` - find tasks

Displays a list of tasks with description containing the keywords.

Example of usage: `find dinner`

Expected outcome:
```
Here are the matching tasks in your list:
1.[E][] valentine's day dinner (at: 14 feb 7pm)
```

### `bye` - exit

Displays goodbye message.

Example of usage: `bye`

Expected outcome:
```
Okay, bye! Hope to see you again :)
```




