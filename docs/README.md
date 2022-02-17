# SONA

> "The future depends on what you do today." - Mahatma Gandhi

Hi! My name is Sona, a ~~good~~ SUPER AMAZING task manager. I am:

- text based
- easy to learn

Being a personal assistant that helps to manage your task, I do more than just recording tasks!

- [X] Categorize your task into different types: to-do, deadline, event
- [X] mark/unmark your task
- [X] Lookup for tasks on a specific day
- [X] Search tasks by keyword

Most importantly, I am totally **free**!

It is simple have me in your computer! Simply...

1. Click [here](https://github.com/pnutzz-0207/ip/releases/tag/A-Jar) to download ⏬
2. double-click it 🖱️
3. add your tasks...and TADA! She will manage your task for you ✨

***
## Usage

To execute the command, simply replace [...] according to your own needs.

###To add tasks:
1. `todo [task name]` - adds a To-do  
2. `deadline [task name] / [due date time]` - adds a task with the specified name and deadline
3. `event [task name] / [due date time]` - adds an event with specified name and date

*Take note that date should be in the format of **YYYY-MM-DD HHMM** *(time is completely optional)*

###Other commands:
1. `help` - user guide for Sona
2. `list` - Show the full task list
3. `mark [task no.]` - mark the specified task as done
4. `unmark [task no.]` - mark the specified task as not done
5. `delete [task no.]` - delete the specified task
6. `schedule [date]` - list out the tasks happening on this day *(time is not accepted yet!)*
7. `find [keyword]` - list the tasks that include the specified keyword
8. `clear` - empty the task list
9. `bye` - save the list and say bye to Sona!

###Special command:
The **update command** edits the specified task. In one command, you can only change the task type, its description OR the date.

`update [task no.] [type/name/date]: [your change]`

- put 'type' to change the task to another category (i.e. todo, deadline, event)
- put 'name' to change the description of the task
- put 'date' to change the date (and time) of the task


Example of usage: 

`update 3 type: event` --> changes the task to an event
***
If you want to be a Java programmer, you can use me to practice too! Here is a snippet of the `getResponse` method:
```ruby
public String getResponse(String input) {
        try {
            if (input.equals("bye")) {
                return Ui.goodbyeMessage();
            }
            String[] command = new Parser().processMessage(input);
            storage.executeCommand(command);
            return tasks.executeCommand(command);
        } catch (DukeException | IOException e) {
            return e.getMessage();
        } catch (DateTimeParseException e) {
            return Ui.dateTimeErrorMessage();
        }

    }
```