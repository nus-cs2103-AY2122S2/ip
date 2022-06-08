# Daphne Bot

This is Daphne, a friendly bot to help you keep track of your tasks! 

![](src/main/resources/view/png/bot.png)

![](docs/Ui.png)

## Features
| Feature   | Description                                                                      | Command (Case Sensitive)                                                  |  
|-----------|----------------------------------------------------------------------------------|---------------------------------------------------------------------------|
| Help      | Lists all commands.                                                              | `help`                                                                    |
| Hello     | Greets Daphne.                                                                   | `hello`                                                                   |
| To Do     | Add a task without any data/time attached to it.                                 | `todo <DESCRIPTION>`                                                      |
| Deadline  | Add a task that needds to be done before a specific date/time.                   | `deadline <DESCRIPTION> /by <YYYY-MM-DD> <HH:MM>`                         |
| Event     | Add a task that starts at a specific date/time and ends at a specific date/time. | `event <DESCRIPTION> /at <START DATE> <START TIME> <END DATE> <END TIME>` |
| Mark      | Mark a task as complete.                                                         | `mark <INDEX>`                                                            |
| Unmark    | Mark a task as incomplete.                                                       | `unmark <INDEX>`                                                          |
| Delete    | Delete a task.                                                                   | `delete <INDEX> `                                                         |
| Reset     | Delete all tasks.                                                                | `reset`                                                                   |
| Find      | Find a task based on a keyword.                                                  | `find <KEYWORD>`                                                          |
| List      | List all current tasks.                                                          | `list`                                                                    |
| UwU       | (⁄˘⁄ ⁄ ω⁄ ⁄ ˘⁄)♡                                                                 | `uwu`                                                                     |
| OwO       | (。O⁄ ⁄ω⁄ ⁄ O。)                                                                  | `owo`                                                                     |
| Terminate | Terminate the application.                                                       | `bye`                                                                      |

*Dates are required to be in the `YYYY-MM-DD` format.*  
*Times are required to be in the `HH:MM` or `HH:MM:SS` format.*
