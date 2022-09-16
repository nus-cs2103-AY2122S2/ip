# User Guide

## Features 

### Manage Your Tasks

- Add, delete and mark/unmark tasks
- Save task information such as name and date
- Get tasks which occur on a specific date
- Find tasks which match a specific keyword

### Manage Your Contacts

- Add and delete contacts
- Save contact information such as name, phone number and birthday

## Example
![Ui](Ui.png)

## Usage

### 1. `todo` - Add a todo to your task list

Example of usage: 

`todo someTodo`

Expected outcome:

```
This todo has been added to your task list!
        1. [T][ ] someTodo
You now have 1 task!
```
### 2. `deadline` - Add a deadline to your task list

Example of usage:

`deadline someDeadline /by 2/2/2022`

Expected outcome:

```
This deadline has been added to your task list!
        2. [D][ ] someDeadline (by: 2 February 2022)
You now have 2 tasks!
```
### 3. `event` - Add an event to your task list

Example of usage:

`event someEvent /at 2/2/2022 1400`

Expected outcome:

```
This event has been added to your task list!
        3. [E][ ] someEvent (at: 2 February 2022, 2:00 PM)
You now have 3 tasks!
```
### 4. `list tasks` - View all tasks in your task list

Example of usage:

`list tasks`

Expected outcome:

```
1. [T][ ] someTodo
2. [D][ ] someDeadline (by: 2 February 2022) 
3. [E][ ] someEvent (at: 2 February 2022, 2:00 PM)
```
### 5. `mark` - Mark a task as complete in your task list

Example of usage:

`mark 1`

Expected outcome:

```
This task has been marked as complete in your task list!
        1. [T][X] someTodo
```
### 6. `unmark` - Unmark a task in your task list

Example of usage:

`unmark 1`

Expected outcome:

```
This task has been unmarked in your task list!
        1. [T][ ] someTodo
```
### 7. `find` - View the tasks in your task list that match the specified keyword

Example of usage:

`find todo`

Expected outcome:

```
1. [T][ ] someTodo
```
### 8. `get` - View the tasks in your task list that occur on the specified date

Example of usage:

`get 2/2/2022`

Expected outcome:

```
2. [D][ ] someDeadline (by: 2 February 2022) 
3. [E][ ] someEvent (at: 2 February 2022, 2:00 PM)
```
### 9. `delete task` - Delete a task in your task list

Example of usage:

`delete task 1`

Expected outcome:

```
This task has successfully been removed from your task list!
        1. [T][ ] someTodo
You now have 2 tasks!
```
### 10. `contact` - Add a contact to your contact list

Example of usage:

`contact someContact 91234567 2/2/2022`

Expected outcome:

```
This contact has been added to your contact list!
        1. someContact, 91234567 (birthday: 2 February 2022)
You now have 1 contact!
```
### 11. `list contacts` - View all contacts in your contact list

Example of usage:

`list contacts`

Expected outcome:

```
1. someContact, 91234567 (birthday: 2 February 2022)
```
### 12. `delete contact` - Delete a contact from your contact list

Example of usage:

`delete contact 1`

Expected outcome:

```
This contact has successfully been removed from your contact list!
        1. someContact, 91234567 (birthday: 2 February 2022)
You now have 0 contacts!
```
