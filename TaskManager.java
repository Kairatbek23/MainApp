public class TaskManager {
    private TaskList taskList;

    public TaskManager() {
        this.taskList = new TaskList();
    }

    public TaskList getTaskList() {
        return taskList;
    }

    public void addTask(String description) {
        Task task = new Task(description);
        taskList.add(task);
    }

    public void deleteTask(int index) {
        taskList.remove(index);
    }
}
