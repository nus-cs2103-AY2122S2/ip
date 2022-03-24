# A Sweet Guide to Using Duke

Hello there 👋 

I'll bet you're utter shite at keeping track of tasks. 😅 Thought so.

Well, here comes Duke, your very own personal task-tracking bot 🤖

## Features 

### Add Tasks

Duke allows users to upload not 1, not 2 but 3️⃣ different tasks. 
Neatly organised into to-dos, events, and deadline categories,
your tasks are easily managed and won't become overwhelming.

### Mark Tasks as Complete

With a simple command, Duke marks tasks as done and helps you
stay focused on the tasks that truly matter.

A true display of ask, don't tell, if you ask me 😌

### Add deadlines or event dates

Duke's 

## Usage

### `list` - Describe action

Prints list of all tasks added to Duke.

Example of usage: 

`list`

Expected outcome:

Duke displays all your tasks in a clear and concise format.

```
1. [E][ ] Arts Bonding Camp (at: Feb 26 2022)
2. [T][X] CS2103T tp v1.2 User Guide
3. [D][ ] Send Charlie daily deliverables (by: Feb 13 2022)  
```

### `todo` - Add to-do task

Adds new to-do task to Duke.

Example of usage:

`todo (...task description...)`

Expected outcome:

Duke shows confirmation message that to-do task has been created.

```
Nice! I've added the task:
    [T][ ] CS2103T tp v1.2 User Guide
You now have 1 task!  
```
### `deadline` - Add task with deadline

Adds a new to-do task with a deadline.

Example of usage:

`deadline (...task description...) /by (deadline)`

Expected outcome:

Duke shows confirmation message that deadline task has been created.

```
Nice! I've added the task:
    [D][ ] Send Charlie deliverables (by: Feb 13 2022)
You now have 2 tasks!  
```
### `event` - Add event with date 

Adds a new event with date.

Example of usage:

`event (...task description...) /at (date)`

Expected outcome:

Duke shows confirmation message that event has been added.

```
Nice! I've added the task:
    [E][ ] Arts Bonding Camp (at: Feb 26 2022)
You now have 2 tasks!  
```
### `delete` - Deletes task

Removes a task from duke.

Example of usage:

`delete (task index)`

Expected outcome:

Duke shows confirmation message that task has been removed. 

```
I've removed the task:
    [E][ ] Arts Bonding Camp (at: Feb 26 2022)
You now have 2 tasks!  
```
### `mark`/`unmark` - Set task completion status

Mark or unmark a task as completed.

Example of usage:

`mark (task index)` or `unmark (task index)`

Expected outcome:

Duke shows confirmation message that task has been marked or unmarked.

```
OK. I've marked this task as done:
    [E][X] Arts Bonding Camp (at: Feb 26 2022) 
```
### `find` - Search for tasks

Returns list of tasks that match the keyword specified by user

Example of usage:

`find (keyword)`

Expected outcome:

Duke displays list of tasks that match the specified keyword.

```
Here is a list of tasks that match Camp:
    1. [E][ ] Arts Bonding Camp (at: Feb 26 2022)
    2. [D][X] submit Arts Camp member details (by: Feb 20 2022)
```
