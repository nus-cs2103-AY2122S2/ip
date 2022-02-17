# User Guide
**Wensleydale** is a *GUI-based* (Graphical User Interface) desktop app for managing tasks, 
including time-sensitive tasks such as deadlines and events.

# Table of Contents
* [Quick Start](#quick-Start)
* [Example of Usage](#example-of-usage)
* [Features](#features)
  * [List of Tasks](#list-of-tasks)
  * [Basic Task](#basic-task)
  * [Deadline](#deadline)
  * [Event](#event)
  * [Mark/Un-mark Tasks](#markun-mark-tasks)
  * [Delete](#delete)
  * [Find by Keyword](#find-by-keyword)
  * [Sort the List](#sort-the-list)
    * [List of Conditions](#list-of-conditions)
  * [Saving](#saving)

# Quick Start
* Make sure that you have Java 11 installed in your computer
* Download the latest `Wensleydale` release from [here](https://github.com/AAlghrairy/ip/releases)
* Copy the file to the folder you wish to use as the main folder *(application will create directories in that folder)*
* Double-click the file to start the application

# Example of Usage
![Ui.png](/ip/Ui.png)

# Features

### Command List

Displays a list of commands, including their respective format.

Format: `help`

### List of Tasks

Lists all the tasks currently in your task list.

Format: `list`

### Basic Task

Adds a basic 'ToDo' task.

Format: `todo [description]`

### Deadline

Adds a task with a specific deadline and/or time.

Format: `deadline [Description] /by yyyy-mm-dd (optionally add /HH:mm for time)`

### Event

Adds an event task that starts and ends between a specified time.

Format: `event [Description] /at yyyy-mm-dd/HH:mm/HH:mm`

### Mark/Un-mark Tasks

Marks or Un-marks a task on the task list

***To mark a task:***  
Format: `mark [index]`  
***To un-mark a task:***  
Format: `unmark [index]`

### Delete

Removes a task from the list.

Format: `delete [index]`

### Find by Keyword

Finds a list of tasks that match a given keyword.

Format: `find [keyword]`

### Sort the List

Sorts the list of tasks based on the given condition.

Format: `sort [condition]`
###### List of Conditions:

***By chronological order:*** `chronologically`  
***By alphabetical order:*** `alphabetically`  
***By marked tasks:*** `done`

### Saving

The program will automatically save your task-list to the disk, and retrieve the list upon restarting.