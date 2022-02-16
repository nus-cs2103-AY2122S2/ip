# User Guide for Duke the Angry Bird

## App Overview
![image](./Ui.png)

## Features 

### Feature - list task

The `list-task` feature lists down all the tasks added previously.

### Feature - list tag

The `list-tag` feature lists down all the tags added previously.

### Feature - todo

The `todo` feature adds a task to be completed to the task list.

### Feature - deadline

The `deadline` feature adds a task with a specific deadline to the task list.

### Feature - event

The `event` feature adds an event to be attended to the task list.

### Feature - mark

The `mark` feature marks a specific task as completed.

### Feature - unmark

The `unmark` feature marks a specific task as uncompleted.

### Feature - find

The `find` feature finds the position of a specific task in the list.

### Feature - delete

The `delete` feature deletes a specific task.

### Feature - add tag

The `add-tag` feature adds a new tag to the existing tag list.

### Feature - tag

The `tag` feature tags an existing task.

### Feature - bye

The `bye` feature exits the program.

## Usage

### Listing all tasks: `list-task`

Shows all tasks in a list.

Example of usage: 

`list-task`

Expected outcome: Displays a list of tasks previously added.


```
Here are the tasks in your list:
1. [T][] Science Homework
2. [D][] Grocery Shopping (by: Jan 02 2022)
3. [E][] Concert (at: Jan 02 2022)
```
### Listing all tags: `list-tag`

Shows all tags in a list.

Example of usage:

`list-tag`

Expected outcome: Displays a list of tags previously added.


```
Here are the tags in your list:
1. #Urgent
2. #Priority
```

### Adding a todo task: `todo`

Adds a task to be completed.

Example of usage:

`todo Science Homework`

Expected outcome: Displays a message that indicates that the task is added.


```
Got it. I've added this task:
[T][] Science Homework
Now you have 2 tasks in your list.
```

### Adding a deadline task: `deadline`

Adds a task with a specific deadline.

Example of usage:

`deadline Grocery Shopping /by 2022-03-01`

Expected outcome: Displays a message that indicates that the task is added.


```
Got it. I've added this task:
[D][] Grocery Shopping (by: Mar 01 2022)
Now you have 2 tasks in your list.
```

### Adding an event task: `event`

Adds a event task to be attended.

Example of usage:

`deadline Concert /at 2022-03-01`

Expected outcome: Displays a message that indicates that the task is added.

```
Got it. I've added this task:
[E][] Concert (at: Mar 01 2022)
Now you have 2 tasks in your list.
```

### Marking a specific task: `mark`

Mark an existing task as completed.

Example of usage:

`mark 1`

Expected outcome: Displays a message that indicates that the task is marked.

```
Nice! I've marked this task as done:
[E][X] Concert (at: Mar 01 2022)
```

### Unmarking a specific task: `unmark`

Mark an existing task as not completed.

Example of usage:

`unmark 1`

Expected outcome: Displays a message that indicates that the task is unmarked.

```
Okay! I've marked this task as not done:
[E][] Concert (at: Mar 01 2022)
```

### Finding a specific task: `find`

Find an existing task in the list.

Example of usage:

`find Concert`

Expected outcome: Displays a message that shows the found task.

```
Here are the matching tasks in your list:
[E][] Concert (at: Mar 01 2022)
Match Found: 1
```

### Deleting a specific task: `delete`

Delete an existing task in the list.

Example of usage:

`delete 1`

Expected outcome: Displays a message that indicates that the task is deleted.

```
Noted, I've removed this task:
[E][] Concert (at: Mar 01 2022)
Now you have 2 tasks left in the list
```

### Adding a new tag: `add-tag`

Adds a new tag.

Example of usage:

`add-tag Urgent`

Expected outcome: Displays a message that indicates that the tag is added.

```
Got it. I've added this task:
#Urgent
Now you have 2 tags left in the list
```

### Tagging a specific task: `tag`

Tags a specific task.

Example of usage:

`tag 1 2`

Expected outcome: Displays a message that indicates that the task is tagged.

```
Nice! I've tagged this task:
[E][] Concert (at: Mar 01 2022) #Urgent
```
