# User Guide

Hello I am duke.Duke.

I can keep track of your tasks, deadlines and events for you, and am your own personal tasks manager.

## Features 

### Feature- Allowed tasks

Three kinds of tasks are currently handled by duke.Duke :

1. **ToDo** : A simple task with a description that serves as a reminder for the user to complete this task.
2. **Deadline** : A task that needs to be completed by a specific deadline.
3. **Event** : An event, basically a task that needs to be done/is occuring at a specific time or date.


## Usage

### `todo` - Adds a ToDo task

Following this keyword with a description will add a todo task to the list.

Example of usage: 

`todo go bowling`

Expected outcome:

Adds the task of going bowling

```
Got it. I've added this task:
[T] [ ] go bowling
```

### `event` - Adds an Event task

Following this keyword with a description, "at/" and a specific keyword will add the given event with the given time to the list.

Example of usage: 

`event birthday party /at 2022-02-02`

Expected outcome:

Adds the birthday party event for the given date.

```
Got it. I've added this task:
[E] [ ] birthday party  (at: Feb 2 2022)
```

### `deadline` - Adds a Deadline task

Following this keyword with a description, "by/" and a specific keyword will add the given task with the given deadline to the list.

Example of usage: 

`deadline finish book /by 2022-02-02`

Expected outcome:

Adds the birthday party event for the given date.

```
Got it. I've added this task:
[D] [ ] finish book  (by: Feb 2 2022)
```

### `list` - Displays all the task in the user's list

This keyword returns all the tasks in the user's list

Example of usage: 

`list`

Expected outcome:

List of all the tasks in the user's list

```
Here are the tasks in your list:
1. [T] [X] read book
2. [T] [ ] go
3. [T] [ ] be happy
```

### `find` - Search for a specific task

This keyword returns all the list in the user's list

Example of usage: 

`find book`

Expected outcome:

List of all the tasks that match the entered keyword

```
Here are the matching tasks in your list:
1. [T] [X] read book
```

### `delete` - Delete a specific task

This keyword deletes a specific task from the list

Example of usage: 

`delete 3`

Expected outcome:

the task at the given number on the list is deleted.

```
Noted. I've removed this task: 
  [T] [ ] be happy
Now you have 2 tasks in the list.
```

### `mark` - Mark a specific task as completed

This keyword makrs a specific task from the list as completed

Example of usage: 

`mark 2`

Expected outcome:

the task at the given number on the list is marked as completed.

```
Nice! I've marked this task as done:
[T] [X] go
```

### `unmark` - Mark a specific task as incomplete

This keyword makrs a specific task from the list as incomplete

Example of usage: 

`unmark 2`

Expected outcome:

the task at the given number on the list is marked as incomplete.

```
OK, I've marked this task as not done yet:
[T] [ ] go
```

### `bye` - Quits the application

Will quit the application

Example of usage: 

`bye`

Expected outcome:

This is going to quit the application

```
Bye. Hope to see you soon.
```
