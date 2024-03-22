# User Guide

Cygnus is a bot that manages your tasks. You can add, delete, recur tasks. You can also mark the task as done or not done.

## Features

Log the tasks that you need to keep track of

### Feature-Adding Tasks

Add tasks to keep track of.

### Feature-Deleting Tasks

Remove tasks that are overdue or done.

### Feature-Finding Tasks

Find the tasks you are looking for amongst all the tasks you have added.

### Feature-Marking/Unmarking Tasks

You can mark tasks as done or not done.

### Feature-Recurring Tasks

You can create recurring tasks and recur them.


## Usage

>Note: Commands words are **NOT** case-sensitive.

### `todo` - Creates and add the todo task into your list.

To create a `ToDo` task:

1. type in `todo (argument)` where argument is the task with the description you are creating.

2. Hit 'Enter' or click the send button.

Example of usage:

`todo homework`

Expected outcome

```
Got it. I've added this task:
  [T][] homework
Now you have 1 tasks in your list.
```

Now you have added the todo task 'homework' to your list which now has a total of 1 task.

### `deadline` - Creates and add the deadline task into your list.

To create a `Deadline` task:

1. type in `deadline (argument) /by (due date)` where argument is the task with the description you are creating and due date is the date you have to complete the task by. `due date` have to be in the form "YYYY-MM-DD" where Y represent the year digit, M represent the month digit and D represent the days digit

2. Hit 'Enter' or click the send button.

Example of usage:

`deadline gym /by 2022-01-22`

Expected outcome


```
Got it. I've added this task:
  [D][] gym (by: Jan 22 2022)
Now you have 1 tasks in your list.
```
Now you have added the deadline task `gym` with a due date of `Jan 22 2022` to your list which now has a total of 1 task.

### `event` - Creates and add the event task into your list.

To create a `Event` task:

1. type in 'event (argument) /on (date)' where argument is the task with the description you are creating and date is the date of the event. `date` have to be in the form "YYYY-MM-DD" where Y represent the year digit, M represent the month digit and D represent the days digit

2. Hit 'Enter' or click the send button.

Example of usage:

`event party /on 2022-03-15`

Expected outcome

```
Got it. I've added this task:
  [E][] party (on: Mar 15 2022)
Now you have 1 tasks in your list.
```
Now you have added the event task `party` that occurs on the date of `Jan 22 2022` to your list which now has a total of 1 task.

### Recurring tasks - tasks that occur again in a given period of time.
>Note: **ONLY** deadline and event tasks can be recurred. 

To add a recurring task:

1. type in `(task) (description) /(preposition) (data) /recur (number) (days/months/weeks/years)`. 
where:
 - `task` - 'event' or 'deadline'.
 - `description` - description of the task. 
 - `preposition` - `on` for recurring events, `by` for recurring deadlines.
 - `date` - date in the form of "YYYY-MM-DD" where Y represent the year digit, M represent the month digit and D represent the days digit.
 - `number` - the number for the recurring period.
 - `days/months/weeks/years` - the label to indicate how long the recurring period is. `days` is for days,`weeks` is for weeks, `months` is for months, `years` is for years.

2. Hit 'Enter' or click the send button.

Example of usage:

`event project meeting /on 2022-01-15 /recur 1 weeks`

Expeceted outcome

```
Got it. I've added this task:
  [E][] project meeting every 1 weeks (on Jan 15 2022)
```

The event task with description `project meeting`, recurring period of `1 weeks` and the first meeting date of `Jan 15 2022` is created.

### `delete` - Deletes an existing task in the list.

To delete a task:

1. type in `delete (index)` where index is the index number of the task in the list.

2. Hit 'Enter' or click the send button.

Example of usage:

`delete 1`

Expeceted outcome

```
Noted. I've removed this task:
  [T][] homework
```
The 1st task in the list (which is a todo task with description `homework` in this case) is now deleted from the list.

### `mark` or `unmark` - Marks or unmarks the task in the list

To mark a task as done:

1. type in `mark (index)` where index is the index number of the task in the list.

2. Hit 'Enter' or click the send button.

Example of usage of `mark`:

`mark 1` 

Expected outcome

```
Nice! I've marked this task as done:
  [T][X] homework
```

The 1st task in the list (which is a todo task with description `homework` in this case) is now marked as compeleted.


Example of usage of `unmark`:

`unmark 1`

Expected outcome

```
Ok! I've marked this task as not done yet:
  [T][] homework
```

The 1st task in the list (which is a todo task with description `homework` in this case) is now marked as not completed.

### `find` - Find the tasks in your list.

To find tasks in  your list: 

1. type in `find (argument)` where argument is the task with the description you are finding for.

2. Hit 'Enter' or click the send button.

Example of usage: 

`find wedding`

Expected outcome:

```
Here are the matching tasks in your list:
  [T][] attend cousin'wedding
  [E][] attend best friend's wedding (on Mar 02 2022)
```
You will see tasks that have task descriptions containing the word `wedding`.

### `list` - Lists all the tasks in the list.

To list tasks in the list:

1. type in `list`.

2. Hit 'Enter' or click the send button.

Example of usuage of `list`:

`list`

Expected outcome

```
Here are the tasks in your list:
  [T][] homework
  [E][] attend lecture (on Mar 22 2022)
```

The list (in this case) contains 2 tasks:
First task: Todo task with description `homework`.
Second task: Event task with description `attend lecture` and occurs on `Mar 22 2022`.

### `recur` - Recurs a task 

Note: `recur` returns the same task if the task cannot be recurred.

To recur a reccuring task:

1. type in `recur (index)` where index is the index number of the task in the list that is being recurred.

2. Hit 'Enter' or click the send button.

Example of usuage:

`recur 1`

Expected outcome

```
I have recurred the task to as follow:
  [E][] project meeting every 1 weeks (on: June 15 2022)
```

The task(in this case an `event` task) has a recurring period of 1 week. The task now has recurred 1 week. Originally the task is printed as (`[E][] project meeting every 1 weeks (on: June 08 2022)`.