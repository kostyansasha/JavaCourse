//package ua.sumdu.j2se.kostyan.tasks;
import java.util.Date;
import java.util.Iterator;

/**
 *
 *
 * @author Sasha Kostyan
 * @version %I%, %G%
 */


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
        result = 31 * result + getTask(0).hashCode()+getTask(result-1).hashCode();
        return result;
    }

    @Override
    public String toString() {
        StringBuilder b = new StringBuilder();
        b.append("TaskList{   ").append("numberOfSizeArrayTask = ").append(size()).append(".").append( '\n' );

        Iterator<Task> it = iterator();
        int i = 0;
        while (it.hasNext()) {
            Task taskIt = it.next();
            b.append("   task[").append(i).append("]: ").append( taskIt.toString()).append('\n');
            i++;
        }
        b.append("}");
        return b.toString();
    }



    /**
     *
     * method that returns a subset of the tasks that
     * are scheduled to perform in the interval
     *
     *do not depend from array or list class
     */
    public  TaskList incoming(Date from, Date to){

        LinkedTaskList linkListIntTo = new LinkedTaskList();
        Task point;

        for (int i = 0; i < size(); i++) {
            point = getTask(i);

            // Not active task
            if (!point.isActive()) {
                // continue next step
                // continue;
            } else {
                // active task
                // Not repeat
                if (!point.isRepeated()) {
                    if (point.getTime().getTime() > from.getTime()
                            && point.getTime().getTime() <= to.getTime()) {
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
                    long a = point.nextTimeAfter(from).getTime();
                    if (a >= from.getTime() && a <= to.getTime()) {
                        // >= from >>> last version return -1, but now is null
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