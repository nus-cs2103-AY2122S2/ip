# DookButler

> "When in doubt, whip it out *(a notebook)*" - Anonymous

Have too many things to do, but can't remember them all? Ever wanted a digital butler to keep track of your tasks for you? DookButler is the application for you!

- lightweight application
- easy to use commands
- ~~completes tasks for you irl~~
- it's also ***F R E E*** 😉

### Features

- `todo` adds a simple task that can be *done* or *not done*
  - `deadline` is a `todo` task but with a date and time by adding `/by` after the title of the deadline task
  - `event` is a `todo` task but with a date and time by adding `/at` after the title of the event
- `list` shows all your current tasks
  - `mark` and `unmark` along with a *number* will mark a task on the list as *done* or *not done*
  - `delete` along with a *number* deletes the task on the list
  - `find` along with part of a task's name will fetch all tasks matching that name

#### Download!

1. Download it [here](https://github.com/Fenway17/ip/releases)
2. Put the `.jar` file into your desired storage location[^1]
3. Double-click to run the file *(or use command line terminal)*
4. Add tasks and Dook will keep track of it for you!

### Development Goals

- [x] Get Dook into a simple to use `.jar` file
- [ ] Include reminders
- [ ] Give Dook a pretty user interface
- [ ] ~~Have Dook do tasks for you irl~~


[^1]: 
    Should you find the need to change where the data is being saved, edit the `FILE_PATH` and `FILE_DIR` in `Duke.java`.
    ```java
    public class Duke {
        ...

        protected static String FILE_PATH = "./data/duke.txt";
        protected static String FILE_DIR = "./data";
    
    ```
    `FILE_PATH` denotes the actual file, while `FILE_DIR` denotes the folder containing the file.
