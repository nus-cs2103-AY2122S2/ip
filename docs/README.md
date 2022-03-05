# User Guide
Duke is a ChatBot that helps you keep track of your tasks.

## Quick Start
1. Ensure you have Java 11 or above installed in your Computer.
2. Download the latest Duke.jar from [[here]](https://github.com/bryans17/ip/releases).
3. Copy the file to the directory you want to use as the home directory.
4. Double click the file to start Duke.

## Features

### Exit and save tasks
Exiting the program when you are done using Duke and automatically saves any changes to the disk.

### Add Tasks
Add any task of your choice under 3 main categories:
1) `todo` : general to do tasks.
2) `deadline` : tasks with a deadline.
3) `event` : specific events occurring.

### Delete Tasks
Delete any task which you do not want Duke to track any longer.

### List Tasks
List out the current tasks in Duke.

### Find Tasks
Find all tasks in Duke containing any words you want.

### Mark/Unmark Tasks
Mark/Unmark your tasks.

### Sort Tasks
Sort your tasks in Duke, based on the following order: todo, deadline, event.

## Usage

### `todo [description]` - Add a `todo` task

Adds a `todo` task into Duke.

Example :
`todo go to sleep`

Outcome:
 ```
 Got it. I've added this taks:
	 [T][] go to sleep
Now you have 1 in the list.
 ```

### `deadline [description] /by [deadline]` - Add a `deadline` task

Adds a `deadline` task into Duke. `[deadline]` must be inputted as `yyyy-MM-dd HHmm` format.

Example :
`deadline cs2103 week 6 quiz /by 2022-02-18 1010`

Outcome:
 ```
 Got it. I've added this taks:
	 [D][] cs2103 week 6 quiz (by: Feb 18 2022 10:10)
Now you have 2 in the list.
 ```

### `event [description] /at [date]` - Add a `deadline` task

Adds a `event` task into Duke. `[date]` must be inputted as `yyyy-MM-dd HHmm` format.

Example :
`event cry myself to sleep /at 2022-02-18 2359`

Outcome:
 ```
 Got it. I've added this taks:
	 [E][] event cry myself to sleep (by: Feb 18 2022 23:59)
Now you have 3 in the list.
 ```

### `delete [index]` - Delete a task

Deletes the task at the index specified by `[index]` from the list. `[index]` should be $\ge 1$ and $\le$ the number of tasks in the list when `delete` was called.

Example of usage:

`delete 2`

Expected outcome:

```
Noted. I've removed this task:
    [D][] cs2103 week 6 quiz (by: Feb 18 2022 10:10)
Now you have 2 tasks in the list
```

### `list` - Lists tasks in Duke

Lists all the task currently in Duke.

Example of usage:

`list`

Expected outcome:
```
Here are the tasks in your list:
1. [T][] go to sleep
2. [E][] event cry myself to sleep (by: Feb 18 2022 23:59)
```

### `mark [index]` - Marks a task as done

Marks the task at the index specified by `[index]` as done. `[index]` should be $\ge 1$ and $\le$ the number of tasks in the list when `mark` was called.

Example of usage:

`mark 1`

Expected outcome:
```
Nice! I've marked this task as done:
    [T][X] go to sleep
```

### `unmark [index]` - unmarks a task

Unmarks the task at the index specified by `[index]`. `[index]` should be $\ge 1$ and $\le$ the number of tasks in the list when `mark` was called.

Example of usage:

`unmark 1`

Expected outcome:
```
OK, I've marked this task as not done yet:
    [T][] go to sleep
```
### `sort` - Sort the tasks

Sorts the tasks in Duke according to the following sort order:
1) `todo`
2) `deadline`
3) `event`
   If the tasks are of the same type, e.g. both are `todo` tasks, then they are sorted alphanumerically based on their description strings.
   Note that sort returns a new sorted list to be displayed and leaves the original list unchanged.

Example of usage:

`sort`

Expected outcome:
```
Here are your sorted Tasks:
1. [T][] go to sleep
2. [E][] event cry myself to sleep (by: Feb 18 2022 23:59)
```
### `bye` - Exit Duke
Exits the Duke program and saves tasks to to disk.

Note that Duke only saves tasks to the disk when you enter `bye`
and not when you change any tasks. This is a design decision to save number of file accesses.

Example of usage:
`bye`

Expected outcome:
Program closes and your data file is updated.

