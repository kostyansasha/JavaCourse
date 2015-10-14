/**
 * Created by Саша on 03.10.2015.
 */

//package ua.sumdu.j2se.kostyan.tasks;

public class LinkedTaskList extends TaskList {
    // variable for size of array
    private int numberOfSizeArrayTask;
    //
    private TaskNode first;


    /**
     * Add to the list of task
     */
    public void add(Task task) throws Exception {
        // different exception
        if (task.getTitle() == null) {
            throw new Exception ("Title can not be null");
        }
        if (task.isRepeated() && task.getRepeatInterval() == 0) {
            throw new Exception ("interval can not be 0");
        }
        if (task.getTime() < 0) {
            throw new Exception ("time can not be < 0");
        }
        if (task.isRepeated() && (task.getTime() > task.getEndTime())) {
            throw new Exception ("time can not be > endTime");
        }
        if (task.isRepeated() && task.getRepeatInterval() >=
                task.getEndTime() - task.getTime()) {
            throw new Exception ("interval can not be >= EndTime - Time");
        }

        // add task in list
        TaskNode node = new TaskNode();
        node.setTask(task);
        node.setNext(first);
        this.first = node;
        this.numberOfSizeArrayTask++;
    }

    /**
     * find task in list
     */
 /*   public TaskNode find (Task task) {
        return find(first, task);
    }
    private TaskNode find (TaskNode point, Task task) {
        if (point == null)
            return null;
        if (point.getTask() == task)
            return first;
        return find(point.getNext(), task);
    }
 */

    /** Delete task from list
     *
     */
    public boolean remove(Task task) throws Exception {
        if (task == null)
            throw new Exception("incoming task is null");

        TaskNode pointPredecessor;
        TaskNode point = this.first;

        if (point == null)
            return false;

        // if one task in a list
        if ((point.getNext() == null) && point.getTask().equals(task)) {
            this.first = null;
            this.numberOfSizeArrayTask--;
            return true;
        }

        // if the first in a list
        if (point.getTask().equals(task)) {
            this.first = point.getNext();
            this.numberOfSizeArrayTask--;
            return true;
        }

        //if the task is in the middle of the list
        pointPredecessor = point;
        point = point.getNext();

        for (int i = 0; i < size() - 2; i++) { //without first and last
            if (point.getTask().equals(task)) {
                pointPredecessor.setNext(point.getNext());
                this.numberOfSizeArrayTask--;
                return true;
            }
            pointPredecessor = point;
            point = point.getNext();
        }

        // if task is last in list
        if (point.getNext() == null && point.getTask().equals(task)) {
            pointPredecessor.setNext(null);
            this.numberOfSizeArrayTask--;
            return true;
        }
        return false;
    }

    /** Number of jobs in the list
     *
     */
    public int size() {
        return numberOfSizeArrayTask;
    }

    /**
     * return the number of task that specified in the list
     */
    public Task getTask(int index) {
        if (index > numberOfSizeArrayTask || index < 0) {
            return null;
        }

        int count = 0;
        TaskNode point = first;

        if (point == null)
            return null;
        while (index >= count) {
            if (count == index)
                break;
            point = point.getNext();
            count += 1;
        }
        return point.getTask();
    }

    /**
     *
     * method that returns a subset of the tasks that
     * are scheduled to perform in the interval
     *
     */
  /*  public TaskList incoming(int from, int to) {
        LinkedTaskList linkListIntTo = new LinkedTaskList();
        TaskNode point = first;

        for (int i = 0; i < numberOfSizeArrayTask; i++) {
            // Not active task
            if (!point.getTask().isActive()) {
                point = point.getNext();
                continue;
            } else {
                // active task
                // Not repeat
                if (!point.getTask().isRepeated()) {
                    if (point.getTask().getTime() > from
                            && point.getTask().getTime() <= to) {
                        try {
                            linkListIntTo.add(point.getTask());
                        } catch (Exception e) {
                            return null;
                        }
                    }
                    point = point.getNext();
                    continue;
                }
                    // repeat task
                if (point.getTask().isRepeated()) {
                    int a;
                    a = point.getTask().nextTimeAfter(from);
                    if (a <= to && a >= from) {
                                        // >= from >>> becouse can return -1
                        try {
                            linkListIntTo.add(point.getTask());
                        } catch (Exception e) {
                            return null;
                        }
                    }
                    point = point.getNext();
                    continue;
                }
            }
        }
        return linkListIntTo;
    }*/


}