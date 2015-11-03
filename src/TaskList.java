import java.util.Iterator;

/**
 *
 *
 * @author Sasha Kostyan
 * @version %I%, %G%
 */

//package ua.sumdu.j2se.kostyan.tasks;

public abstract class TaskList implements Iterable<Task> {
    public abstract void add(Task task);

    public abstract boolean remove(Task task) throws Exception;

    public abstract int size();

    public abstract Task getTask(int index);

    public abstract Iterator<Task> iterator();

    public void rangeCheck(int index) {
        if (index < 0 || index >= size())
            throw new IndexOutOfBoundsException("Index: "+index);
    }

    @Override
    public boolean  equals(Object o) {
        if (o == this)
            return true;

        if (o == null || !(o instanceof TaskList))
            return false;

        if ( ((TaskList) o).size() != this.size() )
            return false;

        Iterator<Task> itCurrent = iterator();
        Iterator<Task> itObj = ((TaskList) o).iterator();

        while (itCurrent.hasNext() && itObj.hasNext()) {
            Task tItCur = itCurrent.next();
            Task tItObj = itObj.next();

            if ( !(tItCur == null ? tItObj == null : tItCur.equals(tItObj) ))
                return false;
        }
        return true; //!(e1.hasNext() || e2.hasNext())
    }

    @Override
    public int hashCode() {
        int result = size();
        result = 31 * result + getTask(0).hashCode()+getTask(result).hashCode();
        return result;
    }



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