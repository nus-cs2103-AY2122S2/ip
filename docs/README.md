# Yae Chat User Guide

> -- just a simple Task Manager app!

---

## Prerequisite

Make sure you have **Java 11** or above installed.

## Features & Usage

### Add task

There are 3 types of task:
- Todo 
- Event 
- Deadline

Format:

```
todo <task_name>
event <task_name> /at YYYY-MM-DD
deadline <task_name> /by YYYY-MM-DD
```

Example usage: `event project meeting /at 2022-02-13` 

(Note that **Date format** and `/at` `/by` must be exactly followed)

### Mark / Unmark

Mark a task as complete or unmark it.

Format: `mark <task_number>`, `unmark <task_number>`

Example Usage: `mark 1`

### Find task by description

Find task that contains any of the given keywords. 

Format: `find <keyword>`

Note that keyword is:
- case sensitive
- substring is allowed i.e. partial keywords

Example Usage: `find project m`

### Search task by date

Search task that with given date

Format: `search YYYY-MM-DD`

Example Usage: `search 2022-02-13`

### List all tasks

Show all with task number, type, status of completion, priority, name, and date.

Format: `list`

### Priorities

Attach priorities level `high` `normal` `low` to task, default is normal

Format: `priority <task_number> <priority_level>`

Example Usage: `priority 1 high`

### Sort

There are 5 kinds of sort `<sort_by>`

- task name `task`
- completion status `mark`
- task type `type`
- priority level `prority`
- date chronologically `date`

Format: `sort <sort_by>`

Example Usage: `sort date`

### Delete

Delete in current list.

Format: `delete <task_number>`

Example Usage: `delete 1`

### Exit Goodbye

Format: `bye`

---

## Reference Command Table

| Action   | Examples                          |
|----------|-----------------------------------|
| todo     | `todo meeting`                    |
| event    | `event meeting /at 2022-02-13`    |
| deadline | `deadline meeting /by 2022-02-13` |
| mark     | `mark 1`                          |
| unmark   | `unmark 1`                        |
| find     | `find project m`                  |
| search   | `search 2022-02-13`               |
| list     | `list`                            |
| priority | `priority 1 high`                 |
| sort     | `sort type`                       |
| delete   | `delete 1`                        |
| bye      | `bye`                             |
