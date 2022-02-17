# Duke User Guide
<img width="278" alt="Ui" src="https://user-images.githubusercontent.com/69782911/154522477-e967f1cc-4352-4db4-a1e4-4f62c222c137.png">


## Features
#### Add tasks 
Duke allows users add tasks to the task list. 
There are three main types of tasks: 
- Todos 
- Events 
- Deadlines 

#### View tasks
Duke allows users to view the tasks in the task list. 

#### Mark/Unmark tasks 
Duke allows users to mark a task when the task is completed. Duke also allows
users to unmark the task if they made a mistake when marking the task. 

#### Delete tasks 
Users can delete tasks from Duke if there are too many tasks in the task list. 

#### Find tasks 
Users can find tasks quickly and easily just by keying in a simple search query.

#### Undo recent command 
Users can undo their most recent command if they made a typo in their task description. 

## Usage 

### `todo` - Add a todo task

Adds a todo to the task list. Useful for tasks with no imminent deadlines. 

Example of usage: 

`todo paynow money to friend`

Expected outcome:

Duke will add the todo to the task list.

```
We have added [T][] paynow money to friend to the list.
You now have a total of 9 tasks in your list! Subarashii!
```

### `event` - Add an event task

Adds an event to the task list. Useful for tasks that happens on a specific date.

Example of usage:

`event company dinner /at 12-12-2022`

Expected outcome:

Duke will add the event to the task list.

```
We have added [E][] company dinner (at: 12-Dec-2022) to the list.
You now have a total of 9 tasks in your list! Subarashii!
```

### `deadline` - Add a deadline task

Adds a deadline to the task list. Useful for tasks that needs to be completed by a specific date.

Example of usage:

`deadline CS3243 assignment /by 12-12-2022`

Expected outcome:

Duke will add the deadline to the task list.

```
We have added [D][] CS3243 assignment (by: 12-Dec-2022) to the list.
You now have a total of 9 tasks in your list! Subarashii!
```

### `mark` - Mark a task

Marks a task as completed. 

Example of usage:

`mark 1`

Expected outcome:

Duke will mark the first task as completed.

```
 Sugoi! I have marked this task as done!
 [T][X] paynow money to friend
```

### `unmark` - Unmark a task

Unmarks a completed task.

Example of usage:

`unmark 1`

Expected outcome:

Duke will unmark the first task.

```
 Daijoubu! I have unmarked this task for you!
 [T][ ] paynow money to friend
```

### `delete` - Deletes a task

Deletes a task in the list.

Example of usage:

`delete 1`

Expected outcome:

Duke will delete the first task in the list.

```
 Otsukaresamadeshita! You have finally completed one task!
 [T][ ] paynow money to friend
```


### `list` - list all tasks in the task list

List tasks in the task list.

Example of usage:

`list`

Expected outcome:

Duke will list all tasks in the task list. 

```
 1. [T][ ] paynow money to friend
```

### `find` - Finds queried task in task list

Duke will look at the descriptions of all the tasks and return a list of tasks 
containing a matching substring.

Example of usage:

`find eat breakfast`

Expected outcome:

Duke will print out the tasks that it found in the task list.

```
 This is what we found!
 1. [T][] eat breakfast
```



### `undo` - Undo the most recent command

Undoes the latest command the user has keyed in.

Example of usage:

`undo`

Expected outcome:

Duke will undo the latest command and revert the state of the task list.

```
 Add has been undone!
```

### `bye` - Exits the program

Exits the Duke program.

Example of usage:

`bye`

Expected outcome:

Duke will terminate the program and say farewell to the user.

```
 Sayonara! Hope to see you again soon!
```










