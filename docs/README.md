# User Guide for Duke

## Features 

### List - `list`

Lists all available tasks saved.

**Usage**:

`list`

```
returns a list of all tasks. eg

List:
1. T | [X] Run

```

### Tasks 

Duke accepts various types of tasks! These include

#### Todo - `todo`
* A Todo task is a task with no given date or deadline...its just something to do.

**Usage**:

`todo [task description]`

```
creates a todo task. Eg: 
T | [ ] Run

```

#### Event - `event`
* Event tasks are tasks that are associated with a specific day or time.

**Usage**:

`event [task description] [/at] [dd-MM-yyyy HHmm]_to_[dd-MM-yyyy HHmm]`

```
creates an event task. Eg: 
E | [ ] Run (at: 31-01-2022 1800 to 31-01-2022 21000)

```

#### Deadline - `deadline`
* Deadline tasks are tasks that need to be completed by a certain date or time.

**Usage**:

`deadline [task description] [/by] [dd-MM-yyyy HHmm]`

```
creates a deadline task. Eg: 
D | [ ] Run (by: 24-04-2022 1400)

```


### Mark and Unmark - `mark` and `unmark`

Mark and Unmark allows users to moniter task completeness.

**Usage**:

`mark [task index]`

```
Marks a task as complete. Eg: 
T | [X] Run

```

`unmark [task index]`

```
Marks a task as incomplete. Eg: 
T | [ ] Run

```

### Find - `find`

Find allows users to narrow the search for a task by their specified keyword.

**Usage**:

`find [task description]`

```
Finds tasks containing the specified task description. Eg: 
'find run' returns 

List:
1. T | [X] run
2. T | [ ] run errands
3. T | [ ] run away

```

### Statistics - `stats`

Statistics allow users to see their completed tasks over a given period of time.

`stats [day index]`

```
Lists tasks completed [day index] days before the current day. Eg: 
'stats 7' returns all the tasks completed in the past 7 days.

List:
1. T | [X] run

```

### Delete - `delete`

Delete allows users to delete tasks.

`delete [task index]`

```
Deletes selected task index. Eg: 
'delete 1' deletes the first task 

List:
1. T | [ ] run errands
2. T | [ ] run away

```
