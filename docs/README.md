# User Guide

Duke is a desktop app for managing tasks via a 
Graphical User Interface (GUI). Duke has easy to learn commands
that support key features for a task manager app. 

## Features 

### Adding tasks
* Duke app is able to add 3 types of tasks:


* Todo (Tasks that needs to be completed by no specific date)\
   Format: `todo TASK_NAME`\
   Example: `todo read book`


* Deadline (Tasks that needs to be completed by a specific date)\
   Format: `deadline TASK_NAME /by YYYY-MM-DD`\
   Example: `deadline finish essay /by 2019-12-11`


* Event (Tasks that needs to be completed on a specific date and/or time)\
   Format: `event TASK_NAME /at DATE`\
   Example: `event attend meeting /at Mon 12-3PM`
 
 
### Marking/Un-marking tasks
* Duke app is able to mark tasks as completed and unmark tasks as not completed yet.\
Format: `mark TASK_NUMBER`, `unmark TASK_NUMBER`

### Deleting tasks
* Duke app can delete any tasks currently stored in your list.\
Format: `delete TASK_NUMBER`

### Listing tasks
* Duke app can list out all tasks stored, displaying their type, status of completion, description and due date if 
applicable.\
Format: `list`

### Finding tasks
* Duke app search for and list out all tasks where their description matches/contains a given keyword.\
Format: `find KEYWORD`\
Example: `find book`

### Undo command
* Duke app can undo commands from users up till the point where Duke is 
started up each time.\
Format: `undo`

### Clear command
* Duke app will clear all its saved data. This action cannot be undone.\
Format: `clear`

