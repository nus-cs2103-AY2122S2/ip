# User Guide

## Features 
- Task List
- Personalised Notes

### Task List

Task List helps you organise your tasks, keep track of progress and can remind you of deadlines and events.

### Personalised Notes

Personalised Notes can help you record important stuffs that you might want to refer to any time. 

## Usage

### `list` - view Task List

The current Task List is printed. A task has the following format:
`[type of task][makred/unmarked] description (date of task)` 

Example of usage:

`list`

Expected outcome:

```
Here are the tasks in your list:
  1. [T][X] borrow book
  2. [D][ ] return book (Mar 11 2022)
  3. [D][ ] CS2103 iP (Feb 18 2022)
  4. [E][ ] party at Evan's (Feb 27 2022)
```



### `todo [description]` - add a todo task to Task List

Add a todo task to your Task List. Todo task supports task description feature only.

Example of usage:

`todo borrow book`

Expected outcome:

A new task `todo` will be added to your Task List and labelled [T] for **T**odos. Todos support task description 

```
Got it. BMO has added this task:
    [T][ ] borrow book
Now you have 1 task in your list.
```



### `deadline [description] /[yyyy-mm-dd]` - add a deadline to Task List

Example of usage: 

`deadline return book /2022-03-11`

Expected outcome:

A new task `deadline` will be added to your Task List and labelled [D] for **D**eadlines. Deadlines support task description and date

```
Got it. BMO has added this task:
    [D][ ] return book (Feb 27 2022)
Now you have 2 tasks in your list.
```



### `event [description] /[yyyy-mm-dd]` - add an event to Task List

Example of usage:

`event party at Evan's /2022-02-27`

Expected outcome:

A new task `event` will be added to your Task List and labelled [E] for **E**vents. Events support task description and date.

```
Got it. BMO has added this task:
    [E][ ] party at Evan's (Feb 27 2022)
Now you have 3 tasks in your list.
```



### `mark [index]` - mark a task

Marks the task according to the index of the Task List.

Example of usage:

`mark 1`

Expected outcome:

The indexed task will be marked and shown.

```
Awesome! :D Another task done:
    [T][X] borrow book
```



### `unmark [index]` - unmark a task

Unmarks the task according to the index of the Task List.

Example of usage:

`unmark 1`

Expected outcome:

The indexed task will be unmarked and shown.

```
T_T BMO hate it when you lie. I will unmark this task:
    [T][ ] borrow book
```



### `delete [index]` - delete a task

Deletes the task according to the index of the Task List.

Example of usage:

`delete 1`

Expected outcome:

The indexed task will be unmarked and shown.

```
BMO deleted the task. Yay!:
    [T][X] borrow book
```



### `note [label]: [description]` - add a new note to your Personalised Notes

Adds a new note with a label and a description. Notes are referred to by their labels instead of index.

Example of usage:

`note StudentID: A0123456M`

Expected outcome:

New note added to Personalised Notes. `label` of the note is StudentID and `description` of the note is A0123456M. 

```
BMO added this to your notes:
    StudentID: A0123456M
```



### `checknote` - check your Personalised Notes

Checks your Personalised Notes.

Example of usage:

`checknote`

Expected outcome:

The current list of Personalised Notes.

```
Here are your notes:
    StudentID: A0123456M
```



### `deletenote [label]` - delete a note

Deletes a note with the label given.

Example of usage:

`deletenote StudentID`

Expected outcome:

Note with label StudentID is deleted from Personalised Notes.

```
Deleted this note:
    StudentID: A0123456M
```