# User Guide for Duke

## Features 

### 3 types of tasks
- todo
- deadline
- event

### Feature 1 : Add Tasks

Create new tasks

### Feature 2: Find Tasks

Search for tasks by their name

### Feature 3: Mark Tasks

Mark tasks as done

## Usage

### 1, `todo` - Create a new todo task

- Create a new todo task into task list

Example of usage: 

`todo return book`

Expected outcome:

Create a new todo task named 'return book'

```
I've added the following task:
[T][X] return book
Currently there are 1 task
```

### 2, `deadline` - Create a new deadline task

- Create a new deadline task into task list

Example of usage:

`deadline assignment /2022-03-21 23:59`

Expected outcome:

Create a new deadline task named 'assignment' due 21 March 2022

```
I've added the following task:
[D][X] assignment (Mar 21 2022 23:59)
Currently there are 2 tasks
```
### 3, `event` - Create a new event task

- Create a new event task into task list

Example of usage:

`Event team meeting /2022-01-17 10:00~12:00`

Expected outcome:

Create a new event task named 'team meeting' from 10:00 to 12:00 17 Jan 2022

```
I've added the following task:
[E][X] team meeting (Jan 17 2022 10:00 ~ Jan 17 2022 12:00)
Currently there are 3 tasks
```
### 4, `list` - List all current tasks

- List out all available tasks stored in task list

Example of usage:

`list`

Expected outcome:

list out all tasks in task list

```
1, [T][X] return book
2, [D][X] assignment (Mar 21 2022 23:59)
3, [E][X] team meeting (Jan 17 2022 10:00 ~ Jan 17 2022 12:00)
```
### 5, `done` - mark a task as done

Example of usage:

`done 1`

Expected outcome:

Mark task 1 as done

```
I've marked the following task 
return book as done 
[T][✓] return book
Well done!
```
### 6, `delete` - delete a task 

- delete a task by its index number

Example of usage:

`delete 1`

Expected outcome:

Delete task 1 

```
I have removed the following task:
[T][X] return book
Currently there are 2 tasks 
```
### 7, `help` - show all available commands 

Example of usage:

`help`

Expected outcome:

List out all available commands for users to use 

```
You can use the following commands for Duke 

1, Todo  "task name" : 
Create a todo task 

2, Deadline  "task name"  /"date": 
Create a deadline task 

3, Event  "task name"  /"date" ~ 
"date":
Create an event task 

4, Find  "task index": 
Find task by index from the list


5, Delete  "task index": 
 Delete task by index from the list


6, List: 
 List out all existing tasks 


7, Help: 
 List out all commands for Duke


8, Done "task index": 
 Mark the task by index as done. 
 
9, Bye: 
 Close application 
```
### 8, `find` - find a task inside task list

Example of usage:

`find team meeting`

Expected outcome:

Find the task names team meeting

```
[E][X] team meeting (Jan 17 2022 10:00 ~ Jan 17 2022 12:00)
```
### 9, `bye` - close application

Example of usage:

`bye`

Expected outcome:

- Close the application

