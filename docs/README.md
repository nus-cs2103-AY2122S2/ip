# User Guide
User Guide for using **DukeGenie** to keep track of tasks

## Features 
1. Add Todo Task
2. Add Deadline Task
3. Add Event Task
4. Show list of Tasks
5. delete task
6. mark task as done
7. unmark task as done
8. find task


### Feature- Add Todo Task

-Add Todo task for user to keep track of task to be done

## Feature- Add Deadline Task

-Add Deadline task for user to keep track of task that has a deadline

## Feature- Add Event Task

-Add Event task for user to keep track of events at a certain time

## Feature- Show list of Tasks

-Shows the user all the tasks in memory currently

## Feature- Delete Tasks

-Allows the user to delete task from memory

## Feature- Mark Task as Done

-Allows the user to mark a task as done

## Feature- Mark Task as Undone

-Allows the user to mark a task as undone

## Feature- Find task

-Allows the user to find tasks matching a keyword


## Usage

### `todo` - add todo task


Example of usage: 

`todo eat lunch`

Expected outcome:

*eat lunch* task will be added to tasklist

```
Added to my brain master:
[T][] eat lunch
Currently I have 1 thing in my brain
```

### `deadline` - add deadline task


Example of usage: 

`deadline submit report /by 20/02/2022 0900`

Expected outcome:

*submit report* task will be added to tasklist

```
Added to my brain master:
[D][] submit report (by 20 FEB 2022 0900)
Currently I have 1 thing in my brain
```

### `event` - add event task


Example of usage: 

`event party at home /at 3pm`

Expected outcome:

*party at home* task will be added to tasklist

```
Added to my brain master:
[E][] party at home (at 3pm)
Currently I have 1 thing in my brain
```

### `list` - show list of tasks


Example of usage: 

`list`

Expected outcome:

All tasks in tasklist will be shown

```
1. [D][] submit report (by: 18 Feb 2022 1400)
2. [E][] party at home (at 3pm)
```

### `delete` - delete task from tasklist


Example of usage: 

`delete 1`

Expected outcome:

*task 1* will be deleted from tasklist

```
Yes master. The task 
[D][] submit report (by: 18 Feb 2022 1400)
has been removed.
Now you have 2 tasks left master.
```

### `mark` - mark task as done


Example of usage: 

`mark 1`

Expected outcome:

*task 1* will be marked as done

```
You could have gotten me to help you.
This task has been marked done
[D][X] submit report (by: 18 Feb 2022 1400)
```

### `unmark` - unmark task as done


Example of usage: 

`unmark 1`

Expected outcome:

*task 1* will be unmarked as done

```
You probably need more genies to help you.
This task has been marked as not done
[D][X] submit report (by: 18 Feb 2022 1400)

```

### `find` - find task


Example of usage: 

`find report`

Expected outcome:

*submit report* task will be shown to the user

```
[D][] submit report (by: 18 Feb 2022 1400)

```


