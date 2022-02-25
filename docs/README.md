# Welcome to Duke's User Guide!
*It is said that everyday, if you know that you need to eat an ugly frog, make sure eating it is the first thing that you do! It gives you the greatest sense of accomplishment and relief - knowing that the toughest task of the day is out of your way :muscle:*

**Let's guide you through all the steps that you need to know in order to tackle your ugly frogs :frog:!**
---

## Features:

### 1. Feature adding tasks

When it comes to adding tasks (AKA some froggies:frog:), we have 3 different types of tasks for you to choose from:
1. Todo tasks
2. Deadline tasks
3. Events

Since they serve different purposes, they have different requirements. Let's go through them:
#### Adding a todo task to the list:
Simply type `todo <description>`  
eg: todo email prof

#### Adding a deadline task to the list:
Type `deadline <description> /by <date> <end-time>`  
eg: deadline finish assignment 1 /by 26/02/2022 2359

#### Adding an event task to the list:
Type `event <description> /at <date> <start-time> - <end-time>`  
eg: event attend NYE Bash /at 31/12/2022 2000 - 2400


### 2. Listing all the tasks in your logs

If you need an overview of all your tasks, their agendas, deadlines and completion statuses, use  
`list`

### 3. Deleting a task

If you need to delete a task, just note down it's index in the list and use the following command  
`delete <task-index>`

### 4. Marking a task done

If you have successfully eaten a frog and want to mark it done, simply use the following command  
`mark <task-index>`

### 5. Unmarking a task or Marking a task as 'not done'

If you need to reverse the marking on a task, use  
`unmark <task-index>`

### 6. Snoozing a task

NOTE: this function is only compatible with deadline and event tasks.
The general format of the command is `snooze <task-index> <field-to-change> <information>`
#### if you want to only change the time associated with a task deadline:
command: `snooze <task-index> time <new-time>`
in the case of an event task, replace `<new-time>` with `<start-time> - <end-time>`

#### if you want to change both the dates and times associated with a task deadline:
command: `snooze <task-index> both <new-date> <new-time>`
in the case of an event task, replace `<new-time>` with `<start-time> - <end-time>`

### 7. Finding tasks with keywords

If you want to find all the tasks that contain at least one of the keywords in their task descriptions, use the command  
`find <keyword1>, <keyword2>, ... <keywordn>`
