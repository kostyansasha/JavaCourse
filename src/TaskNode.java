/**
 * Class  that defines the list node
 *
 * @author Sasha Kostyan
 * @version %I%, %G%
 */
//package ua.sumdu.j2se.kostyan.tasks;

public class TaskNode {

    private Task task;
    private TaskNode next;

    /**
     *
     * @return task
     */
    public Task getTask () {
        return this.task;
    }

    /**
     *
     * @param task that need to set
     */
    public void setTask (Task task) {
        this.task = task;
    }

    /**
     *
     * @param next is link to the next task
     */
    public void setNext(TaskNode next) {
        this.next = next;
    }

    /**
     *
     * @return link on current task
     */
    public TaskNode getNext() {
        return this.next;
    }
}