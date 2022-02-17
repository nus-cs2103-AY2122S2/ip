# User Guide

## Features 

### Add tasks (To Do, Deadline, Event)

Add any upcoming task that you have. Duke will help you remember it!

### Mark your tasks

When you have done your tasks, you can mark it as done! The reverse can also be done.

### List all (or some) of your tasks

You can ask Duke to list all your tasks or find tasks with specific keywords. Of course, they are all numbered for your convenient.

### Delete tasks

You can delete your tasks if they are done or you have created them by mistake.

### Save your tasks

All your tasks are automatically saved once you add/modify/delete them. Duke can remember your tasks forever! (given that the file exists, of course)

## Usage

### `todo` - Adds a to do task

Duke will add a to do task for you. It will be labeled as T when you try to list it.

`todo [DESCRIPTION]`

`[DESCRIPTION]` is a case-sensitive string describing the to do task.

Example of usage: 

`todo Drink water`

Expected outcome:

Adds a to do task with the description "Drink water".

```
Added a to do task.
  [T][ ] Drink water
You have 1 task(s) in the list.
```

### `deadline` - Adds a deadline

Duke will add a deadline for you. It will be labeled as D when you try to list it.

`deadline [DESCRIPTION] /by [DATE_AND_TIME]`

`[DESCRIPTION]` is a case-sensitive string describing the deadline
`[DATE_AND_TIME]` is a date and time formatted as YYYY-MM-DDTHH:mm:ss

Example of usage: 

`todo Homework /by 2022-02-18T23:59:59`

Expected outcome:

Adds a deadline with the description "Homework" which is due by 18 Feb 2022, 23:59:59.

```
Added a deadline.
  [D][ ] Homework (by: Feb 18 2022 23:59:59)
You have 2 task(s) in the list.
```

### `event` - Adds an event

Duke will add an event for you. It will be labeled as E when you try to list it.

`event [DESCRIPTION] /at [DATE_AND_TIME]`

`[DESCRIPTION]` is a case-sensitive string describing the event
`[DATE_AND_TIME]` is a date and time formatted as YYYY-MM-DDTHH:mm:ss

Example of usage: 

`event CS2103T lecture /at 2022-02-18T14:00:00`

Expected outcome:

Adds an event with the description "CS2103T lecture" at 18 Feb 2022, 23:59:59.

```
Added an event.
  [E][ ] CS2103T lecture (by: Feb 18 2022 14:00:00)
You have 3 task(s) in the list.
```

### `list` - Lists you tasks

Duke will list all your tasks.

Example of usage: 

`list`

Expected outcome:

Lists all your tasks.

```
The tasks on your list. Get it done!
  1. [T][ ] Drink water
  2. [D][ ] Homework (by: Feb 18 2022 23:59:59)
  3. [E][ ] CS2103T lecture (by: Feb 18 2022 14:00:00)
```

### `find` - Finds tasks

Duke will list your tasks with the specified keyword.

`find [KEYWORD]`

`[KEYWORD]` is a case-sensitive keyword of the task you want to find

Example of usage: 

`find water`

Expected outcome:

Lists your tasks with the specified keyword.

```
Here are the task(s) that contain(s) your keyword.
  1. [T][ ] Drink water
```

### `delete` - Deletes a task

Duke will delete a task for you.

`delete [INDEX]`

`[INDEX]` is an integer representing the task number when you use the list or find commands

Example of usage: 

`delete 1`

Expected outcome:

Deletes the first task on your list.

```
This task has been removed:
  [T][ ] Drink water
Now you have 2 task(s).
```

### `mark` - Marks a task as done

Duke will mark a task as done.

`mark [INDEX]`

`[INDEX]` is an integer representing the task number when you use the list or find commands

Example of usage: 

`mark 1`

Expected outcome:

Marks the first task on your list as done.

```
Good job! This task is done:
  1. [D][X] Homework (by: Feb 18 2022 23:59:59)
```

### `unmark` - Marks a task as not done

Duke will mark a task as not done.

`unmark [INDEX]`

`[INDEX]` is an integer representing the task number when you use the list or find commands

Example of usage: 

`unmark 1`

Expected outcome:

Marks the first task on your list as not done.

```
Hurry up and get it done!
  1. [D][ ] Homework (by: Feb 18 2022 23:59:59)
```

### `help` - Displays the help page

Duke will list out all the commands you can do.

Example of usage: 

`help`

Expected outcome:

A list of valid commands.

```
Here are the commands you can give to Duke:
1. todo [TO_DO_TASK] (To add a to do task)
2. deadline [DEADLINE] /by [YYYY-MM-DDTHH:mm:ss] (To add a deadline)
3. event [EVENT] /at [YYYY-MM-DDTHH:mm:ss] (To add an event)
4. list (To list all your tasks)
5. find [KEYWORD] (To find tasks with the specified keyword)
6. delete [TASK_NUMBER] (To delete a task by its number)
7. mark [TASK_NUMBER] (To mark a task as done)
8. unmark [TASK_NUMBER] (To mark a task as not done)
9. help (To display this help page)
10. bye (To exit duke)
```

### `bye` - Exits the Duke program

Duke will exit its program, saving all your tasks.

Example of usage: 

`bye`

Expected outcome:

A goodbye message

```
Bye. Hope to see you again soon!
```
