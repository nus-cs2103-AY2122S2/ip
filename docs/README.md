# User Guide
DukePro is a simple chatbot aimed at managing your tasks to ensure that you'll never miss another important task ever again. It optimized for use via a **Command Line Interface** (CLI) while still having the benefits of a Graphical User Interface (GUI).

- [Quick start](#quick-start)
- [Features](#features)
  - [Viewing help tab: `help`](#viewing-help-tab-help)
  - [Viewing chat tab: `chat`](#viewing-chat-tab-chat)
  - [Viewing tasklist: `tasklist`](#viewing-tasklist-list)
  - [Adding a ToDo task: `todo`](#adding-a-todo-task-todo) 
  - [Adding a Deadline task: `deadline`](#adding-a-deadline-task-deadline) 
  - [Adding an Event task: `event`](#adding-an-event-task-event) 
  - [Deleting a task: `delete`](#deleting-a-task-delete) 
  - [Clearing the tasklist: `clearls`](#clearing-the-tasklist-clearls) 
  - [Finding tasks: `find`](#finding-tasks-find)
  - [Marking tasks: `mark`](#marking-a-task-mark)
  - [Unmarking tasks: `unmark`](#unmarking-a-task-unmark)
  - [Closing the application: `bye`](#closing-the-application-bye)
  - [Saving the data](#saving-the-data)
  - [Editing the data file](#editing-the-data-file)
- [FAQ](#faq)

## Quick start
1. Ensure you have Java `11` or above installed in your Computer.
2. Download the latest release from [here](https://github.com/kev-intq/ip/releases).
3. Copy the jar file to the folder you want to use as the home folder for your DukePro.
4. Double-click the file to start the app.
5. Type the command in the command box and press Enter to execute it. e.g. typing `help` and pressing Enter will switch to the help tab.

 ![07 02 2022_03 07 04_REC (2)](https://user-images.githubusercontent.com/77195251/154564958-9e7069f5-65dc-4f22-918f-1b5b90a30c20.png)
 
6. Refer to the Features below for details of each command. 

## Features 

> Words in ```UPPER_CASE``` are the parameters to be supplied by the user.<br/>
e.g. in ```mark INDEX```, INDEX is a parameter which can be used as ```mark 1```.<br/><br/>
Items in square brackets are optional.<br/>
e.g ```help [COMMAND_NAME]``` can be used as ```help todo``` or as ```help```.


### Viewing help tab: `help`
Switches the tab to a help page.

Format: ```help [COMMAND_NAME]```
  - Directs the user to the specific section if ```COMMAND_NAME``` is specified.

Example: 
  - ```help deadline```
  - ```help```

### Viewing chat tab: `chat`
Switches the tab to a chat page.

Format: ```chat```

### Viewing tasklist: `list`
Shows the tasklist

Format: ```list```

### Adding a ToDo task: `todo`
Adds a ToDo task to the tasklist

Format: ```todo DESCRIPTION```

Example: 
  - ```todo buy groceries```
  - ```todo go for a run```

### Adding a Deadline task: `deadline`
Adds a Deadline task to the tasklist

Format: ```deadline DESCRIPTION /by DATETIME```
  - ```DATETIME``` format is given in DD/MM/YYYY HHmm

Example: 
  - ```deadline CS2105 Assignment /by 01/03/2022 2359```

### Adding an Event task: `event`
Adds an Event task to the tasklist

Format: ```event DESCRIPTION /at LOCATION```

Example: 
  - ```event party /at Tom's house```

### Deleting a task: `delete`
Deletes the specified task from the tasklist.

Format: ```delete INDEX```
  - Deletes the task at the specified INDEX.
  - The index refers to the index number shown in the displayed task list.
  - The index must be a positive integer 1, 2, 3, …​

Example: 
  - ```delete 1```

### Clearing the tasklist: `clearls`
Deletes all tasks from the tasklist.

Format: ```clearls```

### Finding tasks: `find`
Finds tasks whose description contain any of the given keywords.

Format: ```find KEYWORDS```
  - The search is case-insensitive. e.g ```hans``` will match ```Hans```
  - The order of the keywords matters. e.g. ```Hans Bo``` will **NOT** match ```Bo Hans```

Example: 
  - ```find groceries```

### Marking a task: `mark`
Marks a specified task from the tasklist as COMPLETED.

Format: ```mark INDEX```
  - Marks the task at the specified INDEX.
  - The index refers to the index number shown in the displayed task list.
  - The index must be a positive integer 1, 2, 3, …​

Example: 
  - ```mark 1```

### Unmarking a task: `unmark`
Marks a specified task from the tasklist as NOT COMPLETED.

Format: ```Unmark INDEX```
  - Unmarks the task at the specified INDEX.
  - The index refers to the index number shown in the displayed task list.
  - The index must be a positive integer 1, 2, 3, …​

Example: 
  - ```unmark 1```

### Closing the application: `bye`
Exits the application

Format: ```bye```

### Saving the data
Task data are saved in the hard disk automatically after any command that changes the data. There is no need to save manually.<br/><br/>

### Editing the data file
Task data are saved as a txt file [JAR file location]/data/listData.txt. Advanced users are welcome to update data directly by editing that data file.<br/><br/>

## FAQ
**Q**: How do I transfer my data to another Computer?<br/>
**A**: Install the app in the other computer and overwrite the empty data file it creates with the file that contains the data of your previous Duke data folder.

## Command summary
<br/>

| Action | Format | Examples |
| --- | --- | --- |
| Help | ```help [COMMAND_NAME]``` | ```help``` |
| Chat | ```chat``` | ```chat``` | 
| List | ```list``` | ```list``` |
| Todo | ```todo DESCRIPTION``` | ```todo buy groceries``` |
| Deadline | ```deadline DESCRIPTION /by DATETIME``` | ```deadline CS2105 Assignment /by 01/03/2022 2359``` |
| Event | ```event DESCRIPTION /at LOCATION``` | ```event party /at Tom's house``` |
| Delete | ```delete INDEX``` | ```delete 1``` |
| Clear list | ```clearls``` | ```clearls``` |
| Find | ```find KEYWORDS``` | ```find groceries``` |
| Mark | ```mark INDEX``` | ```mark 1``` |
| Unmark | ```ummark INDEX``` | ```unmark 1``` |
| Exit | ```bye``` | ```bye``` |



