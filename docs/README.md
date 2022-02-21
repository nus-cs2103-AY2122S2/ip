# User Guide

Duke is a conversational task management app.

<img src="Ui.png" style="max-width: 300px;">


## Features

- Store events, deadlines, and todo items in your personal task list.
- Mark tasks as "done/not done" when you complete them.
- Search for tasks using keywords.


## Getting Started

1. Make sure you have Java 11 installed
2. Download the jar file `ip-all.jar'
3. Open your command line and run `java -jar path/to/ip-all.jar'


## Commands

- `deadline <description> /by <yyyy-MM-dd HH:mm>` Adds new deadline to your list.
- `event <description> /at <yyyy-MM-dd HH:mm>` Adds new event to your list.
- `todo <description>` Adds new todo item to your list.
- `delete <n>` Deletes task at index n.
- `mark <n>` Marks task with index n as done.
- `unmark <n>` Marks task at index n as not done.
- `list` Displays all items in task list.
- `find <some string>` Displays all task list items which contain specified string.
