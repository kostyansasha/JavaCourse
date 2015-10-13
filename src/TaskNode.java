/**
 * Created by Саша on 03.10.2015.
 */
//package ua.sumdu.j2se.kostyan.tasks;

public class TaskNode {

    private Task task;
    private TaskNode next;

/**
  * metods for list node
  *
  */

    public Task getTask () {
        return this.task;
    }

    public void setTask (Task task) {
        this.task = task;
    }

    public void setNext(TaskNode next) {
        this.next = next;
    }

    public TaskNode getNext() {
        return this.next;
    }
}