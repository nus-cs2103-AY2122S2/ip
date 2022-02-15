# <b> Duke Pro User Guide </b>

> <p align="center">What did I need to do again? - <s>You</s> Me, <b>everyday</b> </p>
<p> Ever forgot what you needed to do? :frowning_face: Now you won't! :relieved: </p>

With Duke Pro, you now have an easy way to remember tasks, deadlines and important events.
It is:
* Easy to use
* Text based

How to start:
1. [Click here](https://github.com/alexteo98/ip/releases/download/A-BetterGui/Duke.Pro.jar) to download
2. Double click on "Duke.Pro.jar" to start!

Features supported:
- [x] Add/Remove Tasks
- [x] Mark Tasks
- [x] Find Tasks
- [x] Update Tasks
- [x] Sort Tasks
- [x] View Tasks
- [x] Graphical User Interface

## <b> Features </b>

### [Add Tasks](#adding-tasks)
<p> Add new tasks to your task list.</p>

### [List all task](#listing-tasks)
<p> View your tasks in a list.</p>

### [Sort Tasks](#sorting-tasks)
<p> Sort your task list by name or by date.</p> 

### [Delete Tasks](#deleting-tasks)
<p> Delete tasks in your task list.</p>

### [Mark Tasks](#marking-tasks)
<p> Mark the specific task as done or not done.</p>

### [Update Tasks](#updating-tasks)
<p> Change the date or name of your task.</p>

### [Find Tasks](#finding-tasks)
<p> View all tasks that contains the keyword.</p>

## <b> Usage </b>

### <u>Adding Tasks</u>
* #### `todo <Task Name>`
Add a new To Do task with a specified task name. <br>

Example of usage: <br>
`todo collect mail` <br>

Expected outcome: <br>
A new todo Task will be created and added to your task list. <br>

```
Got it. I've added this task:
    [T][ ] collect mail
Now you have 2 tasks in the list.
```
<br>

* #### `deadline <Deadline Name> /by <Date>`
Add a new Deadline task with a specified name and deadline. <br>
The date can be entered in the form `dd/mm/yyyy hhmm` for it to be sortable. <br>

Example of usage: <br>
`deadline go to the post office /by 10/1/2022 1445` <br>
`deadline pick up parcel /by this friday` <br>

Expected outcome: <br>
A new Deadline will be created and added to your task list. <br>

```
Got it. I've added this task:
    [D][ ] go to the post office (by: 10 Jan 2022, 2:45PM)
Now you have 2 tasks in the list.

Got it. I've added this task:
    [D][ ] pick up parcel (by: this friday)
Now you have 3 tasks in the list.
```
<br>

* #### `event <Event Name> /at <Date>`
Add a new event with a specified name and date. <br>
<br>The date can be entered in the form `dd/mm/yyyy hhmm` for it to be sortable. <br>

Example of usage: <br>
`event son's birthday /at 10/1/2022 1100` <br>
`event family dinner /at Next Monday` <br>

Expected outcome: <br>
A new event will be created and added to your task list. <br>

```
Got it. I've added this task:
    [E][ ] son's birthday (by: 10 Jan 2022, 11:00AM)
Now you have 4 tasks in the list.

Got it. I've added this task:
    [E][ ] family dinner (by: Next Monday)
Now you have 5 tasks in the list.
```
<br>

### <u>Listing tasks</u>

* #### `list`
View all added tasks as a list. <br>

Example of usage: <br>
`list` <br>

Expected outcome: <br>
All tasks added will be displayed in a list. <br>

```
================================================
Here is your task list:
1.[T][ ] collect mail
2.[D][ ] go to the post office (by: 10 Jan 2022, 2:45PM)
3.[D][ ] pick up parcel (by: this friday)
4.[E][ ] son's birthday (by: 10 Jan 2022, 11:00AM)
5.[E][ ] family dinner (by: Next Monday)
================================================
```
<br>

### <u>Sorting Tasks</u>

* #### `list name`
Sorts all tasks by name in lexicographical order, and displays them in a list. <br>

Example of usage: <br>
`list name` <br>

Expected outcome: <br>
All tasks added will be sorted according to name, and will be displayed in a list. <br>

```
================================================
Here is your task list:
1.[T][ ] collect mail
2.[E][ ] family dinner (by: Next Monday)
3.[D][ ] go to the post office (by: 10 Jan 2022, 2:45PM)
4.[D][ ] pick up parcel (by: this friday)
5.[E][ ] son's birthday (by: 10 Jan 2022, 11:00AM)
================================================
```
<br>

* #### `list date`
Sorts all tasks by date in chronological order, and displays them in a list. <br>
If the dates are not sortable, they will be ordered below those with sortable dates. <br>

Example of usage:  <br>
`list date` <br>

Expected outcome: <br>
All tasks added will be sorted according to date, and will be displayed in a list. <br>

```
================================================
Here is your task list:
1.[E][ ] son's birthday (by: 10 Jan 2022, 11:00AM)
2.[D][ ] go to the post office (by: 10 Jan 2022, 2:45PM)
3.[T][ ] collect mail
4.[E][ ] family dinner (by: Next Monday)
5.[D][ ] pick up parcel (by: this friday)
================================================
```
<br>

### <u>Deleting Tasks</u>

* #### `delete <Task Number>`
Deletes the specified task from the task list. <br>

Example of usage:  <br>
`delete 5` <br>

Expected outcome: <br>
The specified task will be removed from the task list. <br>

```
Noted. I have removed this task:
    [D][ ] pick up parcel (by: this friday)
There are now 4 tasks in your task list
```
<br>

### <u>Marking Tasks</u>

* #### `mark <Task Number>`
Marks the specified task as done. <br>

Example usage:  <br>
`mark 1` <br>

Expected outcome: <br>
The specified task will be marked as done. If the task is already marked as done, no changes will be made. <br>

```
    Nice! I've marked this task as done:
+++ [E][X] son's birthday (by: 10 Jan 2022, 11:00AM)

    This task is already marked as done:
    [E][ ] son's birthday (by: 10 Jan 2022, 11:00AM)
```
<br>

* #### `unmark <Task Number>`
Unmarks the specified task as done. <br>

Example usage: <br>
`unmark 1` <br>

Expected outcome: <br>
The mark will be removed if it is present. If the task has not been marked yet, no changes will be made. <br>

```
    OK, I've marked this task as not done yet:
--- [E][ ] son's birthday (by: 10 Jan 2022, 11:00AM)

    This task has not been marked as done yet:
    [E][ ] son's birthday (by: 10 Jan 2022, 11:00AM)
```
<br>

### <u>Updating Tasks</u>

* #### `unmark <Task Number> /name <New Name>`
Changes the name of the task specified. <br>

Example usage: <br>
`update 1 /name son's graduation` <br>

Expected outcome: <br>
The name of the specified task will be changed to the new name provided. <br>

```
    Nice! I've updated this task:
--- [E][ ] son's graduation (by: 10 Jan 2022, 11:00AM)
``` 
<br>

* #### `unmark <Task Number> /date <New Date>`
Changes the date of the task specified. <br>

Example usage:<br>
`update 1 /date 6/5/2022 1900` <br>
`update 1 /date first friday of May` <br>
`update 3 /date 1/2/2011 1800` <br>

Expected outcome: <br>
The name of the specified task will be changed to the new name provided. <br>
If the task is a todo (no date field), no changes will be made and an error message will be show. <br>


```
    Nice! I've updated this task:
--- [E][ ] son's graduation (by: 6 May 2022, 7:00PM)

    Nice! I've updated this task:
--- [E][ ] son's graduation (by: first friday of May)

No date information is associated with this task.
```

### <u>Finding Tasks</u>

* #### `find <keyword>`
Find all tasks that contain the keyword to be searched in their name. <br>

Example usage: <br>
`find son` <br>

Expected outcome: <br>
All tasks with task names containing the keyword will be displayed as a list. <br>

```
================================================
Here are the matching tasks in your list:
1.[E][X] son's graduation (by: first friday of May)
2.[D][ ] buy gift for son (by: 26 Apr 2022, 1:00PM)
================================================
```
<hr>

[Back to top](#-duke-pro-user-guide-)
