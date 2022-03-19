# Chaterpillar User Guide

Welcome to Chaterpillar, a todo list and contacts management app that lives on your desktop. <3

<img src="Ui.png" style="max-width: 300px;">

<br>

## Getting Started

1. Make sure you have Java 11 installed
2. Download the jar file `ip-all.jar`
3. Open your command line and run `java -jar path/to/ip-all.jar`

<br>

## Features

### Todo List
Chaterpillar helps you maintain a todo list which is stored on your hard drive and will be loaded each time you open the app.

#### Add Task
Chaterpillar allows you to add 3 different types of tasks to your todo list.

##### Todo [T]
Create a "todo" if your task is not time-sensitive.
###### Command
`todo <description>`
###### Example
`todo edit novel draft 11`
###### Example Result
"I've added the task: [T][ ] edit novel draft 11"

<br>

##### Deadline [D]
Create a "deadline" if your task must be completed before a certain date time.
###### Command
`deadline <description> /by <yyyy-MM-dd HH:mm>`
###### Example
`deadline CS2103 IP /by 2022-03-20 23:59`
###### Example Result
"I've added the task: [D][ ] CS2103 IP (by: 2022-03-20 23:59)"

<br>

##### Event [E]
Create an "event" if your task must be completed at a specific time.
###### Command
`event <description> /at <yyyy-MM-dd HH:mm>`
###### Example
`event birthday party /at 2022-04-01 17:30`
###### Example Result
"I've added the task: [E][ ] birthday party (at: 2022-04-01 17:30)"

<br>

#### List Tasks
Display an ordered list of all your tasks.
##### Command
`event <description> /at <yyyy-MM-dd HH:mm>`

<br>

#### Find
Shows the list of tasks that contains a specified string.
##### Command
`find <some string>`
##### Example
`find homework`

### Exit
Closes the Chaterpillar app.
#### Command
`bye`

<br>

#### Delete Task
Deletes nth task in list. The first task has index 0.
##### Command
`delete <n>`
##### Example
`delete 0`

<br>

#### Mark Task
Marks nth task in list. The first task has index 0.
##### Command
`mark <n>`
##### Example
`mark 0`
##### Chaterpillar Response
"I've marked this task as done."

<br>

#### Unmark Task
Unmarks nth task in list. The first task has index 0.
##### Command
`unmark <n>`
##### Example
`unmark 0`
#### Chaterpillar Response
"I've marked this task as not done."

<br>

### Contact List
Chaterpillar includes a simple contact list feature which stores contact names and their telegram handles. This contact list is stored on your hard drive and will be loaded each time you open the app.

#### Add Contact
Adds a contact (name and telegram handle) to your contact list.
##### Command
`addContact <name> t/<telegram handle>`
##### Example
`addContact Gernene Tan t/gernene`
##### Chaterpillar Response
"I've added the contact: Gernene Tan: gernene"

<br>

#### List Contacts
Shows an ordered list (starting from 0) of all your contacts.
##### Command
`listContacts`
##### Chaterpillar Response
"Your contacts: ..."

<br>

#### Delete Contact
Removes a contact from your contact list.
##### Command
`deleteContact <n>`
##### Example
`deleteContact 0`
##### Chaterpillar Response
"I've deleted this contact."

<br>

## Commands

- `deadline <description> /by <yyyy-MM-dd HH:mm>` Adds new deadline to your list.
- `event <description> /at <yyyy-MM-dd HH:mm>` Adds new event to your list.
- `todo <description>` Adds new todo item to your list.
- `delete <n>` Deletes task at index n.
- `mark <n>` Marks task with index n as done.
- `unmark <n>` Marks task at index n as not done.
- `list` Displays all items in task list.
- `find <some string>` Displays all task list items which contain specified string.
- `addContact <name> t/<telegram handle>` Adds new contact.
- `listContacts` Lists all contacts.
- `deleteContact <n>` Deletes contact at index n.
- `bye` Closes app.
