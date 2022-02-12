# User Guide

## Features 

### Adding Tasks
There are 3 types of tasks that Duke supports;
1. Todo
2. Event
3. Deadline

#### Todo
To add a Task of type Todo, enter the following:   
```
todo {Task Name}
```
Expected Return:
```
added: {Task Name}
Number of tasks: X
```

#### Event
To add a Task of type Event, enter the following:
```
event {Task Name} /at {Time}
```
The time can be of any format, but will only 
be recognised by Duke if it follows the 
following format: yyyy-mm-ddTHH:mm   
E.g. 2022-02-18T23:59 will be understood by Duke 
as the 18th of Feb 2022 at 11:59PM

Expected Return:
```
added: {Task Name}
Number of tasks: X
```

#### Deadline
To add a Task of type Deadline, enter the following:
```
deadline {Task Name} /by {Time}
```
The time can be of any format, but will only
be recognised by Duke if it follows the
following format: yyyy-mm-ddTHH:mm   
E.g. 2022-02-18T23:59 will be understood by Duke
as the 18th of Feb 2022 at 11:59PM

Expected Return:
```
added: {Task Name}
Number of tasks: X
```

### Viewing Tasks
To view current tasks, enter the following:
```
list
```
This will list out all the tasks that are stored 
along with their indexes

Expected Return:
```
1.[T][x] {Task Name}
2.[D][] {Task Name} (by: {Time})
3.[E][x] {Task Name} (at: {Time})
```

### Completing Tasks

Duke also gives you the ability complete tasks

#### Mark

To mark a task as complete, enter the following:

```
mark {index of task}
```
Duke will mark the task as complete and it will
be shown by a `[x]` symbol when listing

Expected Return:
```
Marked as complete
[E][x] {Task Name} (at: {Time})
```

#### Unmark

To mark a task as incomplete, enter the following:

```
unmark {index of task}
```
Duke will mark the task as incomplete and it will
be shown by a `[]` symbol when listing

Expected Return:
```
Marked as incomplete
[E][] {Task Name} (at: {Time})
```

### Deletion of Tasks
In order to delete tasks, enter the following:
```
delete {index of task}
```
Duke will delete the task and it will no longer exist

Expected Return:
```
Noted. I've removed this task:
[T][] {Task Name}
You now have X tasks in the list.
```

### Searching for Tasks
To find tasks that are stored, enter the following:
```
find {search term}
```
Duke will return the tasks matching the search term 
and their corresponding indexes

Expected Return:
```
Here are the matching tasks in your list:
[T][] {Task Name}
```

### Sorting Tasks
To sort tasks, enter the following:
```
sort
```
Duke will return the tasks sorted by Time, if 
entered in the format specified, and then 
alphabetical order   
Note that the indexes of tasks will change
after this operation

Expected Return:
```
List has been updated. here is the new order:
1.[E][x] {Task Name} (at: {Time})
2.[D][] {Task Name} (by: {Time})
3.[T][x] {Task Name}
```

### Ending the Program
To end the program, enter the following:
```
bye
```
This will end the program and the window will close
