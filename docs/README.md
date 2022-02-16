# USER GUIDE

Duke is a friendly personal task manager that helps you to manage your task and keep track of them. A greeting from Duke when the application just startup.

## features
### Adding task
##### Duke supports 3 type of task
- Todo, represent by [T]
- Event represent by [E]
- Deadline represent by [D]

To add a **Todo** task in task list:   **_`todo` (task discription)_**

<img src = "https://user-images.githubusercontent.com/72971889/154334008-80fbc42b-c3e9-4263-9900-ff398da4c0b6.png" width="500" height="400">

To add a **Event task** in task list:   **_`event` (event discription) /at (yyyy-mm-dd)_**

<img src = "https://user-images.githubusercontent.com/72971889/154335527-31042059-54ce-44eb-82b9-e6f25909d604.png" width="500" height="400">

To add a **Deadline task** in task list:   **_`deadline` (event discription) /at (yyyy-mm-dd)_**

<img src = "https://user-images.githubusercontent.com/72971889/154336478-26c05161-d0c3-49f5-b5e6-63a74792a71f.png" width="500" height="400">

### Listing your task
Duke supports listing of all your task simply by command: **_`list`_**

<img src = "https://user-images.githubusercontent.com/72971889/154338120-7bb78117-e1ad-472f-bb45-54a99f0cc426.png" width="500" height="400">

### Mark/Unmark your task
When a task is added, it is by default not done yet, which is marked as follows: `[ ]` (the second bracket in of the task). 
When you complete a task, you can mark with a cross to show the task is completed, as follows: `[X]` 

To **mark/unmark** : **_`mark`/`unmark` (task number that show in the list)_**

<img src = "https://user-images.githubusercontent.com/72971889/154339811-709d8eb9-6022-46c9-a4ba-e2a9cf3525bc.png" width="500" height="400">

### Delete task
To **remove** a task from your task list: **_`delete` (task number that show in the list)_**

<img src = "https://user-images.githubusercontent.com/72971889/154340308-4c2121c6-4131-47ca-b4fd-cdde2edc9587.png" width="500" height="400">

### Search task by keywords
To **find** tasks from your task list: **_`find` (keywords)_**

<img src = "https://user-images.githubusercontent.com/72971889/154340791-866d4c5e-ae1b-4da2-8827-4d92f62a881f.png" width="500" height="400">

### Command help
if you forget any format or command and needs **help** simply type the command: **_`help`_**

### Saving your tasks automatically
When the task list is update each time, Duke will automatically saves the task list into a data file (`ip/duke_Saved.txt`)

### Loading your tasks
When you start Duke application, it will automatically load your data from the data file (`ip/duke_Saved.txt`), if no data is store inside, it will create a new task list for you to edit.

### Closing Duke
To exit Duke application, simply type the command: **_`bye`_**    
It will closes the window by itself.

### Notes
1. Commands are not case-sensitive, you can type Uppercase letters or lowercase letters or mix, but for `find keyword` command, **KEYWORD is case-sensitive**
2. Do not give an empty command, a general error message will be shown
3. Any wrong format will be responds with a corresponding error message
