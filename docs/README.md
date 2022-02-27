# Walle User Guide
------------------------------------------------------------------------------------------------------------------------
> Waaaaaaaaaaaaaaaaaaaaaaaaaaall-E - Wall e

Walle is a simple application that can record and keep track of the tasks you want to do
~~Also, it's Walle so who woudn't like it?!~~ 

![Screenshot of Walle](Ui.png)

## Downloading Walle

1. You can click [here](https://github.com/tzhan98/ip/releases) to get the latest version of Walle
2. Download the latest Walle.jar
3. Double-click on Walle.jar to run it

________________________________________________________________________________________________________________________
## Features 

### Check out the awesome stuff that Walle can do

 - [x] [Add tasks](https://tzhan98.github.io/ip/#add-tasks)
 - [x] [Mark/Unmark tasks](https://tzhan98.github.io/ip/#markunmark-tasks)
 - [x] [List all tracked tasks](https://tzhan98.github.io/ip/#list)
 - [x] [Detect duplicates](https://tzhan98.github.io/ip/#detect-duplicates)
 - [x] [Delete tasks](https://tzhan98.github.io/ip/#delete-task)
 - [x] [Find tasks](https://tzhan98.github.io/ip/#find-task)

________________________________________________________________________________________________________________________
## Usage of Walle

### Add tasks

There are 3 types of tasks you can add
- todo
- deadline
- event

 - **Adding a todo**

Example of usage: 

todo \<name of task\>

    `todo borrow book`

Expected outcome:

```
     Got it. I've added this task: 
       [T][ ] borrow book
     Now you have 5 tasks in the list.
```

 - **Adding a deadline**

Example of usage:

deadline \<name of task\> /by \<date to complete task\>

    `deadline return math book /by Sunday`

you can also use 'DD-MM-YYYY HHMM' format for \<date to complete task\>

    `deadline return sci book /by 17-02-2022 1900`

Expected outcome:

```
     Got it. I've added this task: 
       [D][ ] return math book (by: Sunday)
     Now you have 6 tasks in the list.
     
     Got it. I've added this task: 
       [D][ ] return sci book (by: 17 Feb 2022 07:00 PM)
     Now you have 7 tasks in the list.
```
 - **Adding a event**

Example of usage:

event \<name of task\> /at \<time of event\>

    `event project meeting /at Mon 2-4pm`

you can also use 'DD-MM-YYYY HHMM' format for \<time of event\>

    `event lunch with mom /at 17-02-2022 1900`

Expected outcome:

```
     Got it. I've added this task: 
       [E][ ] project meeting (at: Mon 2-4pm)
     Now you have 7 tasks in the list.

     Got it. I've added this task: 
       [E][ ] return sci book (at: 17 Feb 2022 07:00 PM)
     Now you have 8 tasks in the list.
```

### Mark/Unmark tasks

Walle is able to mark or unmark tasks

 - **Marking tasks**

Example of usage:

 mark \<task number\>
    `mark 2`

Expected outcome:

```
     Nice! I've marked this task as done: 
       [T][X] return book
```

 - **Unmarking tasks**

Example of usage:

 unmark \<task number\>

    `unmark 2`

Expected outcome:

```
     OK, I've marked this task as not done yet:
       [T][ ] return book
```

### list

Walle will list all current tasks in tasklist

Example of usage:

    'list'

Expected outcome:

```
     Here are the tasks in your list:
     1. [T][X] read book
     2. [D][ ] return book (by: 20 Feb 2022 08:00 PM)
     3. [T][ ] buy bread
```

### Detect duplicates

Walle will detect duplicate entries and reject input

Example of usage: 

    'todo have dinner'

when there already exists a todo with the task name 'have dinner'

Expected outcome:

```
    BEEP! Error! There is a duplicate task (task Number 6)
```

### Delete task

Walle will delete a specified task number listed in the tasklist

Example of usage:

delete \<task number\>

    `delete 3`

Expected outcome:

```
    BEEP. I've removed this task: [T][X] have dinner 
    Now you have 5 task on the list.  
```

### Find task

Walle will search for task based on input keywords

Example of usage:

find \<task name\>

    `find dinner`

Expected outcome:

```
    Here are the matched tasks in your list:
    3. [T][X] have dinner with eva
    5. [T][ ] have dinner with mom
```
[back to top](https://tzhan98.github.io/ip/)

________________________________________________________________________________________________________________________