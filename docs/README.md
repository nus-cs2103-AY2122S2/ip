# User Guide

## Table of contents
1. [Quick start](#quickstart)
   - [Installation](#installation)
   - [A quick example](#quickexample)
   - [Source code](#sourcecode)
2. [Features](#features)
   - [Viewing help: `help`](#viewinghelp)
   - [Listing tasks: `list`](#listingtasks)
   - [Adding a to-do task: `todo`](#addingtodo)
   - [Adding an event: `event`](#addingevent)
   - [Setting a deadline: `deadline`](#settingddl)
   - [Marking a task: `mark`](#markingtask)
   - [Unmarking a task: `unmark`](#unmarkingtask)
   - [Deleting a task: `delete`](#deletingtask)
   - [Finding a task: `find`](#findingtask)
   - [Exiting the program (CLI only): `bye`](#exiting)
3. [FAQ](#faq)
4. [About this project](#about)

## Quick start <a name="quickstart"></a>

### Installation <a name="insatllation"></a>

1. Ensure you have Java `11` or above installed. You can download from 
[here](https://www.oracle.com/java/technologies/javase/jdk11-archive-downloads.html).
    - You can run commands `java -verison` and `javac -version` on the terminal to check 
2. Download `Pyke.jar` of the latest release version from [here](https://github.com/Rye-Catcher/ip/releases).
3. Move `Pyke.jar` to the directory where you want to use as the _home folder_ of the application.
4. Double click `Pyke.jar` file to run the application.
   - Alternatively, you can open the terminal on that folder and run `java -jar Pyke.jar`.
   - If you wish to run in CLI, you can open the terminal on that folder and run `java -cp Pyke.jar pyke.Pyke`.

### A quick example <a name="quickexample"></a>

Enter `deadline CS4215 lab /by 2022-02-20` to add your first task into the list!

And enter `list` to view all the task in the list. Try `mark 1` and view the list again.

You can view the list of command guide by entering `help`.

Below it's a picture of what it should roughly look like on Windows 10.

<p align="center">
  <img src="ug_pics\quick_example.png" />
</p>

And this is a demo for **CLI** (Command-Line Interface) in case you are a _GIGA_ turbo-geek.

Note that you can enter `bye` to exit from CLI (you can try this on GUI).

<p align="center">
  <img src="ug_pics\quick_example_CLI.png" />
</p>

### Source code <a name="sourcecode"></a>

You can check out the code in this [GitHub repository](https://github.com/Rye-Catcher/ip).

## Features <a name="features"></a>

- [Viewing help: `help`](#viewinghelp)
- [Listing tasks: `list`](#listingtasks)
- [Adding a to-do task: `todo`](#addingtodo)
- [Adding an event: `event`](#addingevent)
- [Setting a deadline: `deadline`](#settingddl)
- [Marking a task: `mark`](#markingtask)
- [Unmarking a task: `unmark`](#unmarkingtask)
- [Deleting a task: `delete`](#deletingtask)
- [Finding a task: `find`](#findingtask)
- [Exiting the program (CLI only): `bye`](#exiting)

### Viewing help: `help` <a name="viewinghelp"></a>

```{0}
help
```

Shows a brief command guide.

### Listing tasks: `list` <a name="listing tasks"></a>

```{0}
list
```

Lists tasks indexed by integers in the form of:

```{0}
[INDEX].[TASK_TYPE][IS_MARKED] TASK_NAME (TIME)
```

There are 3 types of tasks: to-do, event and deadline, which will be denoted as
`T`, `E` and `D` respectively.

If the task is marked as done, `IS_MARKED` will be a `X`. Otherwise, it will be a white space.

Event and deadline will be associated with a `TIME` attribute. 

**Examples**

> The annotation in the snippets will only be served for explanation purpose here. It will not be shown
in the real application

```{0}
$ list
//this is a todo task and marked as done
1.[T][X] read Kafka on the shore 
//this is a deadeline and marked as un-done
2.[D][ ] play Half-life 3 and Kenshi 2    (by: Dec 31 2099)
//this is an event and marked as done
3.[E][X] Toad   (at: 17 Aug 1926) 
```


### Adding a to-do task: `todo` <a name="addingtodo"></a>

```{0}
todo <TASK NAME>
```

Adds a to-do task into the task manager embedded in Pyke.

**Examples**
```{0}
todo play Kenshi
```
It will add a to-do task named `play Kenshi`.

### Adding an event: `event` <a name="addingevent"></a>

```{0}
event <TASK NAME> /at <EVENT TIME>
```

Adds an event that takes place at `<EVENT_TIME>` to the task manager.

**IMPORTANT NOTES**: `<EVENT TIME>` _MUST_ be in the form of `yyyy-mm-dd`.

For example, `1926-08-17` is valid but `2001-1-01` or `2002-01-1` is not valid.

**Example**

```{0}
event Overwatch Leagure Season 5 /at 2022-05-06
```

It will add an event named `Overwatch Leagure Season 5` with its time `2022-05-06`

### Setting a deadline: `deadline` <a name="settingddl"></a>

```{0}
ddl <TASK NAME> /by <DEADLINE TIME>
```
Sets a deadline into the task manager

**IMPORTANT NOTES**: Just as the event time. `<DEADLINE TIME>` _MUST_ be in the form of `yyyy-mm-dd`.

For example, `1926-08-17` is valid but `2001-1-01` or `2002-01-1` is not valid.

**Example**

```{0}
deadline CS4215 lab /by 2022-02-20
```
It will set a deadeline named `CS4215 lab` with deadline time `2022-02-20`.

### Marking a task: `mark` <a name="markingtask"></a>

```{0}
mark <TASK INDEX>
```
Marks the `TASK_INDEX`th task in the list as done.

**IMPORTANT NOTES**: Index number will start from 1. You can use `list` to view the list with index.

**Example**

```{0}
mark 1
```
It will mark the first task in the list as done

### Unmakring a task: `unmark` <a name="unmarkingtask"></a>

```{0}
unmark <TASK INDEX>
```
Marks the `TASK_INDEX`th task in the list as un-done.

**IMPORTANT NOTES**: Index number will start from 1. You can use `list` to view the list with index.

**Example**

```{0}
unmark 2
```
It will mark the second task in the list as done

### Deleting a task: `delete` <a name="deletingtask"></a>

```{0}
delete <TASK INDEX>
```

Deletes the `TASK_INDEX`th task from the list.

**Example**

```{0}
delete 3
```
It will delete the third task.

### Finding a task: `find` <a name="findingtask"></a>

```{0}
find <KEYWORD>
```
Shows all tasks whose `TASK NAME` contains `KEYWORD`

**Example**
```{0}
find book
```
It will search all tasks and show those tasks whose name has substring `book`.

### Exiting the program: `bye` <a name="existing"></a>
```{0}
bye
```

Exits the program.

**IMPORTANT NOTES**: Only intend to work for CLI.

## FAQ <a name="faq"></a>

**Q**: Why my computer shows JNI error message when I try to open the application  
**A**: This is caused by wrong version of `jdk` (Java Development Kit) on your environment. You can read 
[this post](https://stackoverflow.com/questions/22381202/a-jni-error-has-occurred-please-check-your-installation-and-try-again-in-eclips?page=1&tab=votes#tab-top)
for a solution.

## About <a name="about"></a>

This is the [Xiaoteng](https://github.com/Rye-Catcher) 's project homework for 
[CS2103T Software Engineering](https://nus-cs2103-ay2122s2.github.io/website/admin/index.html) module at NUS.