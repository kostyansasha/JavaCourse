/**
 *
 *
 * @author Sasha Kostyan
 * @version %I%, %G%
 */

//package ua.sumdu.j2se.kostyan.tasks;

public abstract class TaskList {
    public abstract void add(Task task) throws Exception;

    public abstract boolean remove(Task task) throws Exception;

    public abstract int size();

    public abstract Task getTask(int index);


    /**
     *
     * method that returns a subset of the tasks that
     * are scheduled to perform in the interval
     *
     *do not depend from array or list class
     */
    public  TaskList incoming(int from, int to){

        LinkedTaskList linkListIntTo = new LinkedTaskList();
        Task point;

        for (int i = 0; i < size(); i++) {
            point = getTask(i);

            // Not active task
            if (!point.isActive()) {
                // point = point.getNext();
                continue;
            } else {
                // active task
                // Not repeat
                if (!point.isRepeated()) {
                    if (point.getTime() > from
                            && point.getTime() <= to) {
                        try {
                            linkListIntTo.add(point);
                        } catch (Exception e) {
                            return null;
                        }
                    }
                    continue;
                }
                // repeat task
                if (point.isRepeated()) {
                    int a;
                    a = point.nextTimeAfter(from);
                    if (a >= from && a <= to) {
                        // >= from >>> because can return -1
                        try {
                            linkListIntTo.add(point);
                        } catch (Exception e) {
                            return null;
                        }
                    }
                    continue;
                }
            }
        }
        return linkListIntTo;
    }

}