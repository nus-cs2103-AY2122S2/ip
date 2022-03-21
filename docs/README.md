# User Guide
Gary is a desktop app for managing your daily to-do lists, optimized for use via a Command Line Interface (CLI) while still having the benefits of a Graphical User Interface (GUI). 

## Features 
1. [Adding tasks](https://github.com/vanessaxuuan/ip/blob/master/docs/README.md#adding-tasks): task_type
2. [Deleting tasks](https://github.com/vanessaxuuan/ip/blob/master/docs/README.md#deleting-tasks): delete
3. [Finding related tasks](https://github.com/vanessaxuuan/ip/blob/master/docs/README.md#finding-related-tasks): find
4. [Listing out tasks](https://github.com/vanessaxuuan/ip/blob/master/docs/README.md#listing-out-tasks): list
5. [Viewing help](https://github.com/vanessaxuuan/ip/blob/master/docs/README.md#viewing-help): help
6. [Marking tasks](https://github.com/vanessaxuuan/ip/blob/master/docs/README.md#marking-and-un-marking-tasks): mark
7. [Interactions](https://github.com/vanessaxuuan/ip/blob/master/docs/README.md#interactions) 


### 1. Adding Tasks

##### Adding different types of Task to your to-do list:

1. ToDo: 

```
[todo task_name] e.g. todo errands
```

2. Event

```
[event name / dd-mm-yyyy,hhmm] e.g. event party / 25-03-2022,2359
```

3. Deadline

```
[deadline name / dd-mm-yyyy,hhmm] e.g. deadline lab4 / 05-12-2022,1400
```

### 2. Deleting Tasks

##### Delete Task(s)

```
[delete index ...] e.g. delete 5 3 1  or  delete 5
```
note: to delete multiple tasks at the same time, enter index in descending order

##### Delete all Tasks

```
[refresh]
```

### 3. Finding related Tasks
```
[find keyword] e.g. find assignment
```

Output: A lists of tasks containing the keyword


### 4. Listing out Tasks
```
[list]
```
 Output: Current to-do list
 
 
### 5. Viewing Help
```
[help]
```
Output: command summary 


### 6. Marking and un-marking Tasks

##### Mark task(s) as done

```
[mark index ...] e.g. mark 1 4 2
```
note: can mark multiple tasks at the same time 

outcome: e.g. T[X] errands 


##### Mark task(s) as not done

```
[unmark index ...] e.g. unmark 1 5 2
```
note: can un-mark multiple tasks at the same time 

outcome: e.g. T[ ] errands 


### 7. Interactions

Input:
```
1. hi
2. hello
3. hey
4. bye
```
#### Try out these inputs to interact with Gary! 😉
