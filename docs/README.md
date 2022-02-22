# Duke-tator

Can't seem to keep track of your daily tasks? No?

Say less!

I present to you, Duke-tator, the ultimate Tasks keeper!

**Store!**

**Keep!**

**Remind!**

Never forget to complete a task with Duke-tator!

Follow the instructions below to start using Duke-tator **NOW**!

## Running Duke-tator

1. Download the jar file for Duke-tator.
   1. *Optional*: If you want to pre-load data from a text file, please create a text file as "duke.txt" under a folder named "data" that is in the same location as the jar file.
   2. For pre-loading Task data, make sure the tasks follow this format: 


      T - 0 - Sprint 5
      D - 0 - Create new function - 2022/January/07
      E - 1 - Project Meeting with Duke-tator CEO - 2022/February/15

2. Double click on the jar file to start the program.
3. Once the program starts, Duke-tator will provide you with the necessary commands to get you going:
   1. **list** [Displays out the list of tasks provided in the data/duke.txt file. Make sure the tasks are in this format: T - 0 - NameOfTask or D - 0 - NameOfTask - 2020/January/07]
   2. **todo <TaskName>** [Creates a Todo task with provided 'TaskName']
   3. **deadline <TaskName> /by <2020/January/07>** [Creates a Deadline task with provided 'TaskName' and 'date' in the format shown that has to be completed by 'date']
   4. **event <TaskName> /at <2020/January/07>** [Creates a Event task with provided 'TaskName' and 'date' in the format shown that is taking place on 'date']
   5. **mark <TaskNameNumber>** [Marks the task with the TaskNumber as done]
   6. **unmark <TaskNameNumber>** [Unmarks the task with the TaskNumber as undone]
   7. **delete <TaskNameNumber>** [Deletes the task with the TaskNumber]
   8. **find <TaskName>** [Finds the task with any likeness to the TaskName]
   9. **sort** [Sort the existing tasks by their name in alphabetical order]
   10. **bye** [Saves the existing tasks into the data/duke.txt file and terminates the application]
