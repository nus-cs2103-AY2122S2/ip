# User Guide
## Features
### Feature-Add Tasks
Add 3 types of tasks to Duke - Todo tasks, events and deadlines.

**Task types**  
Todo Task - simple task with a name that needs to be done anytime  
Event - Task that occurs at a specific time and/or date  
Deadline - Task that needs to be completed by a specific time and/or date


### Feature-Mark Tasks
Mark tasks as complete as you finish them.
You can also unmark tasks that you may have mistakenly marked as done!

## Usage

### `todo <task name>` - Add a todo task

Adds a todo task to Duke.

Example of usage: 

`todo read book`

Expected outcome:

Adds the task called "read book" to Duke.

```
Got it. I've added this task:
  [] read book 
Now you have 1 task in the list.
```

### `event <task name> /at <date and/or time>` - Add a event task

Adds an event to Duke.

Example of usage:

`event wedding /at 2022-04-04 12:00PM`

Expected outcome:

Adds the event called "wedding" to Duke, which takes place on 2022-04-04 at 12:00PM.

```
Got it. I've added this task:
  [E][] wedding (at: 2022-04-04 12:00PM) 
Now you have 2 tasks in the list.
```

### `deadline <task name> /by <date and/or time>` - Add a deadline task

Adds a deadline to Duke.

Example of usage:

`deadline submit assignment /by 2022-04-04 12:00PM`

Expected outcome:

Adds the deadline "submit asssignment" to Duke which needs to be completed by 2022-04-04 at 12:00PM.

```
Got it. I've added this task:
  [D][] submit assignment (by: 2022-04-04 12:00PM) 
Now you have 3 tasks in the list.
```

### `list` - List tasks

Lists out all the tasks currently logged in Duke.

Example of usage:

`list`

Expected outcome:

Adds the task called "read book" to Duke.

```
Here's a list of your items:
1.[] read book
2.[E][] wedding (at: 2022-04-04 12:00PM) 
3.[D][] submit assignment (by: 2022-04-04 12:00PM) 
```

### `mark <task number>` - Mark a task as done

Mark the task as done using its number from the list of tasks.

Example of usage:

`mark 1`

Expected outcome:

Marks the task with number 1 from the list,"read book", as complete.

```
Nice! I've marked this task as done:
[X] read book
```

### `unmark <task number>` - Unmark a task as done

Unmark the task as done using its number from the list of tasks.

Example of usage:

`unmark 1`

Expected outcome:

Unmarks the task with number 1 from the list,"read book", as complete.

```
Ok, I've marked this task as not done yet:
[] read book
```

### `delete <task number>` - Delete a task

Delete the task using its number from the list of tasks.

Example of usage:

`delete 3`

Expected outcome:

Deletes the task with number 1 from the list, "submit assignment".

```
Noted. I've remove this task:
[D][] submit assignment (by: 2022-04-04 12:00PM)
Now you have 2 tasks left in the list.
```

### `find <keyword>` - Find a task

Find tasks with the given keyword in their name.

Example of usage:

`find book`

Expected outcome:

Finds the tasks with the keyword "book" in their name, in this case, only one task, "read book" will match.

```
Here are the matching tasks in your list:
1.[] read book
```

### `sort` - Sort the tasks in the list

Sorts the tasks in the list by alphabetical order.

Example of usage:

`sort`

Expected outcome:

```
Here's are your items sorted by name:
1.[] read book
2.[D][] submit assignment (by: 2022-04-04 12:00PM) 
3.[E][] wedding (at: 2022-04-04 12:00PM) 
```

### `bye` - Exit Duke

Closes the application.

Example of usage:

`bye`

Expected outcome:

Duke window closes immediately.

