# User Guide

## Features 

* 3 types of tasks (todos, deadlines, events)
### Basic Task Management

* Create new tasks
* Mark tasks as done 
* Delete tasks

### Tag Tasks

* Tag tasks with multiple keywords
* Keywords are fully customizable

### Find Tasks

* Search for tasks via their name or tags


## Usage

### 1. `todo` - Create a new todo

Example of usage: 

`todo homework`

Expected outcome:
* Create a new task named 'homework'

```
Got it. I've added this task *quack*
  [T][] homework *quack*
Now you have 1 task in the list. *quack*
```

### 2. `deadline` - Create a new deadline

Example of usage:

`deadline report /by 2022-02-19 23:59`

Expected outcome:
* Create a new deadline called 'homework' due on 19th February 2022 11:59PM

```
Got it. I've added this task *quack*
  [D][] report (by: Feb 19 2022 23:59) *quack*
Now you have 2 tasks in the list. *quack*
```

### 3. `event` - Create a new event

Example of usage:

`event festival /on 2022-02-30 14:00`

Expected outcome:
* Create a new event called 'festival' happening on 30th February 2022 2:00PM

```
Got it. I've added this task *quack*
  [E][] festival (on: Feb 30 2022 14:00) *quack*
Now you have 3 tasks in the list. *quack*
```

### 4. `list` - List all current tasks

Example of usage:

`list`

Expected outcome:
* List all current tasks 

```
These are your tasks: *quack*
1. [T][] homework *quack*
2. [D][] report (by: Feb 19 2022 23:59) *quack*
3. [E][] festival (on: Feb 30 2022 14:00) *quack*
```

### 5. `mark` - Mark a task as done

Example of usage:

`mark 1`

Expected outcome:
* Marks task 1 as done

```
I've marked task 1 as done! *quack*
  1. [T][X] homework *quack*
```

### 6. `delete` - Delete a task

Example of usage:

`delete 2`

Expected outcome:
* Deletes task 2

```
I've deleted task 2! *quack*
  [D][] report (by: Feb 19 2022 23:59) *quack*
```

### 7. `find` - Finds a task

Example of usage:

`find festival`

Expected outcome:
* Finds any tasks with the word festival

```
These are your tasks: *quack*
1. [E][] festival (on: Feb 30 2022 14:00) *quack*
```

### 7. `tag` - Tags a task with a keyword

Example of usage:

`tag 2 fun`

Expected outcome:
* Tags task 2 with the keyword fun

```
Successfully tagged task 2 with #fun *quack*
```




