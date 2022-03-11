# User Guide
Hello this is Duke!

It will help you keep track of your tasks, set up deadlines, events and save them on your device

## Features 

### Feature - Managing Tasks

Three kind of tasks are currently handled by the Duke:

1. ToDo : A simple task with just a task description
a way for the user to add reminder tasks or daily goals.

2. Event : An event is a task that is to occur at a particular date eg. a meeting or a family excursion

3. Deadline : A task that is to be completed by a certain date/time like an assignment "deadline".


## Usage

### `todo` - Add a ToDo task

Adds a todo task to the list. 

Example of usage:

`todo dance tonight`

Expected outcome:

Adds the task to the duke list and displays a message upon successful addition.

```
Got it. I've added this task:
[T][ ] dance tonight
```

### `deadline` - Add a Deadline task

Adds a deadline task to the list. The date entered must be in YYYY-MM-DD format.

Example of usage:

`deadline read a book /by 2022-02-02`

Expected outcome:

Add the deadline task to the task list along with the time

```
Got it. I've added this task
[D][ ] read a book (by: Feb 02 2022)
```

### `event` - Add an Event task

Adds an event task to the list. The date entered must be in YYYY-MM-DD format.

Example of usage:

`event guitar lesson /at 2019-10-09`

Expected outcome:

Adds the event to the duke list with the given time 
and displays a message upon successful addition.

```
Got it. I've added this task:
[E][ ] guitar lesson (at: Oct 09 2019)
```

### `delete` - Delete tasks

Deletes the tasks mentioned by the user from the list.

Example of usage:

`delete 3 5`

Expected outcome:

Deletes the 3rd and the 5th task in the list and displays the following message.

```
Noted. I've removed these tasks:
[T][ ] do the quiz
[T][ ] do the assignment
Now you have 4 tasks in the list.
```

### `mark` - Marks a task done

Marks task/tasks as mentioned by the user.

Example of usage:

`mark 5 6`

Expected outcome:

The 5th and the 6th task in the list is marked as done.

```
Nice! I've marked these tasks as done:
[D][X] CS3243 project (by: Sep 19 2021)
[T][X] do the assignment
```

### `unmark` - unmarks a task

Unmarks task/tasks as mentioned by the user.

Example of usage:

`unmark 5 6`

Expected outcome:

The 5th and 6th task in the list is marked as not done.

```
Nice! I've marked these tasks as done:
[D][ ] CS3243 project (by: Sep 19 2021)
[T][ ] do the assignment
```
### `find` - Find a task containing the given keyword

Displays all the tasks that contain the keyword entered by the user.

Example of usage:

`find finish`

Expected outcome:

Will display tasks which contain the keyword new in their description.

```
Noted. I have found these tasks matching your description
[D][X] finish IP (by: Sep 09 2019)
```

### `list` - Display all the tasks

Displays all the tasks that are added by the user or were stored previously.

Example of usage:

`list`

Expected outcome:

All the tasks in the list are displayed to the user along with the type of tasks,
whether they are marked and also the time related to the task.

```
1. [D][X] finish IP (by: Sep 09 2019)
2. [T][ ] read a book
3. [E][ ] guitar lesson (at: Oct 09 2019)
```
### `bye` - Quits duke

Quits duke and stores all the data in the respective file

Example of usage: 

`bye`

Expected outcome:

Displays the message telling the user to quit the app.

```
Bye. Hope to see you again soon!
```
