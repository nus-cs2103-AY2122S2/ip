# ChiBot User Guide
##Getting Started
To start using ChiBot, download the `jar` from this [link](https://github.com/WJunHong/ip).


Make sure you have JDK 11 (Java) installed before proceeding. If not, proceed 
[here](https://www.oracle.com/sg/java/technologies/javase/jdk11-archive-downloads.html)
before carrying on.


## Features 
ChiBot comes with the following features:
1. Add a `todo`
2. Add a `deadline`
3. Add an `event`
4. `list` all your tasks
5. `find` tasks
6. `mark` tasks as done
7. `unmark` done tasks
8. `delete` unwanted tasks
9. Get `help`
10. Say `bye` to Chi

## Usage

### 1. `todo` command

To add a new todo using ChiBot, simply type in:
>**todo** _description_

Example:
>todo make pancakes

Expected outcome:

Creates a new todo.

### 2. `deadline` command

To add a new deadline using ChiBot, simply type in:
>**deadline** _description_ **/by** _yyyy-mm-dd_ _hh:mm_

Example:
>deadline finish iP /by 2022-02-18 23:59

Expected outcome:

Creates a new deadline.


Do take note of the **spacing** between the _date_ and _time_ fields.

### 3. `event` command

To add a new event using ChiBot, simply type in:
>**event** _description_ **/at** _yyyy-mm-dd_ _hh:mm_-_hh:mm_

Example:
>event attend brother's wedding /at 2022-02-19 12:00-15:00

Expected outcome:

Creates a new event.


Do take note of the **spacing** between the _date_ and _time_ fields. Also, ensure that
the 1st timing comes **before** to 2nd.
### 4. `list` command

To view all your tasks using ChiBot, simply type in:
>**list**

Example:
>list

Expected outcome:

Displays all your saved tasks.

### 5. `find` command

To find tasks which match **all** keywords specified, simply type in:
>**find** _keyword1_ _keyword2_ ...

Example:
>find homework english exam

Expected outcome:

Tasks which have all **3** keywords in them are shown.


Please ensure you type at least 1 keyword.
### 6. `mark` command

To mark a task as complete using ChiBot, simply type in:
>**mark** _index_

Example:
>mark 1

Expected outcome:

The 1st task in your list is marked as complete.

### 7. `unmark` command

To "unmark" a marked task using ChiBot, simply type in:
>**unmark** _index_

Example:
>unmark 1

Expected outcome:

The 1st task is no longer marked as complete.

### 8. `delete` command

To delete a task using ChiBot, simply type in:
>**delete** _index_

Example:
>delete 1

Expected outcome:

The 1st task is removed.

### 9. `help` command

To get help from Chi when using the application, simply type in:
>**help** _command_

Example:
>help todo

Expected outcome:

Chi will show you how to properly type a `todo` command.

### 10. `bye` command

To exit the program, type
>**bye**

Example:
>bye

Expected outcome:

The program will close after a few seconds.


