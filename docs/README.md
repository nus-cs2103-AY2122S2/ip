# User Guide
Welcome to Duke Bot! A chat bot made to help you track tasks.

![Duke](Ui.png)

## Quick Start
1. Ensure you have Java 11 or higher installed in your computer.
2. Download the latest Duke.jar from [here](https://github.com/KeithCZW/ip/releases)
3. Copy the file to the folder you want to use Duke
4. Double-click the app to start the app.
5. Enjoy!

## Command Summary
| Command   | Description                                                                      | Command                                                                   |  
|-----------|----------------------------------------------------------------------------------|---------------------------------------------------------------------------|
| help      | Lists all commands.                                                              | `help`                                                                    |
| todo      | Add a task without any data/time attached to it.                                 | `todo <description>`                                                      |
| deadline  | Add a deadline that needs to be done before a specific date/time.                | `deadline <description> /by <date> <time>`                                |
| event     | Add an event that starts at a specific date/time.                                | `event <description> /at <date> <time>`                                   |
| mark      | Mark a task as complete.                                                         | `mark <index>`                                                            |
| unmark    | Mark a task as incomplete.                                                       | `unmark <index>`                                                          |
| delete    | Delete a task.                                                                   | `delete <index> `                                                         |
| Find      | Find a task based on a keyword.                                                  | `find <keyword>`                                                          |
| List      | List all current tasks.                                                          | `list`                                                                    |
| bye       | Says bye. That's it.                                                             | `bye`                                                                     |

- Date in format YYYY-MM-DD
- Time in format HH:mm