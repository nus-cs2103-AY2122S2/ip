# User Guide
![UI Showcase](./Ui.png)

IstjBot carries out your commands at no questions whatsoever. <br/>
Record your tasks and notes in one place. IstjBot will remember them.

## Features 

### Let's create the simplest task, `todo`.
This command will add a new todo task. <br/>
Just write what to do. No dates, modifiers, anything.
#### Usage
`todo <DESCRIPTION OF THE TASK>`
#### Example of usage:
`todo finish CS2103T Ip`
#### Expected outcome:
```
As an IstjBot, I will add this task right now.
[T][] finish CS2103T Ip
Now you have 1 task in the list.
```

### Want to set a date for your task? Try `event` or `deadline`.
Not only you can write the description of the task, you can also
specify when the event is happening, or when the deadline is due. <br/><br/> 
Just remember this: <br/> 
Event comes with 'at' ♪ , deadline comes with 'by' ♫
#### Usage
`event <DESCRIPTION OF THE TASK> /at <DATE>` <br/>
`deadline <DESCRIPTION OF THE TASK> /by <DATE>`
- Note that both description and date can't be empty
- Date should follow this format: "yyyy-MM-dd"
  - See below section for an actual example
#### Example of usage:
`event Jason's birthday party /at 2021-02-22` <br/>
`deadline CS2103T Tp /by 2021-04-21`
#### Expected outcome:
```
As an IstjBot, I will add this task right now. 
[E][ ] Jason's birthday party (at: Feb 22 2021)
Now you have 2 tasks in the list.
```
```
As an IstjBot, I will add this task right now. 
[D][ ] CS2103T Tp (by: Apr 21 2021)
Now you have 3 tasks in the list.
```

### Okay, okay, but how do I look at the tasks? Use `list` command!
You can view all the tasks you have created with `list` command.
#### Usage
`list tasks`
- Why `list tasks`, not just `list`?
  - You will know more when you see our note features...
#### Example of usage:
`list tasks`
#### Expected outcome:
```
As an IstjBot, I present you the task(s) in your list:
1. [T][ ] finish CS2103T Ip
2. [E][ ] Jason's birthday party (at: Feb 22 2021)
3. [D][ ] CS2103T Tp (by: Apr 21 2021)
```

### Done with your task (or not)? `mark` or `unmark` them!
You can mark your task as complete, or if you think it's not done yet...
try un-marking them as well! IstjBot supports both features.
#### Usage
`mark <TASK INDEX>` <br/>
`unmark <TASK INDEX>`
- The index refers to the index number shown in the displayed task list.
- The index must be a positive integer 1, 2, 3, …
#### Example of usage:
`mark 1` <br/>
`unmark 1`
#### Expected outcome:
```
As an IstjBot, I've marked this task as done: 
[T][X] finish CS2103T Ip
```
```
As an IstjBot, I've unmarked this task: 
[T][ ] finish CS2103T Ip
```

### Made a mistake? Fret not, simply `delete` the task!
If you want to delete a certain task, you can delete it easily as well.
Why so easy? Because... it is an IstjBot.
#### Usage
`delete task <TASK INDEX>`
- The index refers to the index number shown in the displayed task list.
- The index must be a positive integer 1, 2, 3, …
- Why `delete task`, not just `delete`? 
  - Again, You will know more later...
#### Example of usage:
`delete task 1`
#### Expected outcome:
```
As an IstjBot, I've removed this task: 
[T][ ] finish CS2103T Ip
Now you have 2 tasks in the list.
```

### Search for tasks by date or description: `find` and `date`.
Using `find` allows you to search for tasks by their descriptions. <br/>
Using `date` allows you to search for tasks by their set dates. <br/><br/>
Pretty neat, huh?
#### Usage
`find <DESCRIPTION>` <br/>
`date <DATE>`
- Date should follow this format: "yyyy-MM-dd"
  - See below section for an actual example
#### Example of usage:
`find CS2103T` <br/>
`date 2021-04-21`
#### Expected outcome:
```
As an IstjBot, I present you the task(s) with that keyword.
1. [T][ ] finish CS2103T Ip
2. [D][ ] CS2103T Tp (by: Apr 21 2021)
```
```
As an IstjBot, I present you the task(s) with that date.
1. [D][ ] CS2103T Tp (by: Apr 21 2021)
```

### Sometimes, you just need something simpler than tasks. Use our note features!
Use `note` to create a new note that contains small snippets of textual information,
`list notes` to list all the notes you wrote down, and `delete note` to delete a note that you no longer need. <br/><br/>
Wow, what a nice extension...
#### Usage
`note <DESCRIPTION>` <br/>
`list notes` <br/>
`delete note 1`
- The index refers to the index number shown in the displayed note list.
- The index must be a positive integer 1, 2, 3, …
#### Example of usage:
`note ask midterm date to Prof` <br/>
`list notes` <br/>
`delete note 1`
#### Expected outcome:
```
As an IstjBot, I will add this note right now. 
ask midterm date to Prof
Now you have 1 note in the list.
```
```
As an IstjBot, I present you the note(s) in your list:
1. ask midterm date to Prof
```
```
As an IstjBot, I've removed this note: 
ask midterm date to Prof
Now you have 0 note in the list.
```

### That's all I have for IstjBot, `bye`!
Say `bye` to IstjBot to close the program.
> Wait, But what happens to my tasks and notes?

Don't worry! They are stored locally under `data` folder, as `texts.txt`.
As long as you don't tamper with the txt file, IstjBot will preserve everything as well.
#### Usage
`bye`
#### Example of usage:
`bye`
#### Expected outcome:
```
Bye, I, IstjBot, will be organizing your tasks until you come back.
```
