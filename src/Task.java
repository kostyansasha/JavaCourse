/**
 * Class that describes the Task
 *
 * @author Sasha Kostyan
 * @version %I%, %G%
 */

//package ua.sumdu.j2se.kostyan.tasks;

public class Task implements Cloneable {
    private String title;
    private int time;

    private int start;
    private int end;
    private int interval;

    private boolean active;
    private boolean repeat;

    //неакт задача що констр задачу €ка викон у задан час
    // без повторень ≥з заданою назвою
    /**
     * Constructor(String title, int time)
     * constructs a task which is executed in a given time
     * without repetitions with a given name
     *
     * @param title name Task
     * @param time  the beginning of the task
     * @throws Exception which show that the task has not been created
     */
    public Task(String title, int time) throws Exception{

        if (title == null) {
            throw new Exception ("Title can not be null");
        }

        if (time < 0) {
            throw new Exception ("time can not be < 0");
        }

        this.title = title;
        this.time = time;
    }

    // неактив задача €ка викон у заданому пром≥жку часу (≥ поч, ≥ к≥н)
    // ≥з заданим ≥нтервалом ≥ маЇ задану назву
    /**
     * Constructor(String title, int start, int end, int interval
     * Inactive task which is executed in a given period of time (start, end)
     * with a specified frequency and has given name
     *
     * @param title    name Task
     * @param start    the beginning of the task
     * @param end      of task
     * @param interval through which to repeat the task
     * @throws Exception which show that the task has not been created
     */
    public Task(String title, int start, int end, int interval) throws Exception {

        if (title == null) {
            throw new Exception ("Title can not be null");
        }

        if ( interval == 0) {
            throw new Exception ("interval can not be 0");
        }

        if ( time > end ) {
            throw new Exception ("time can not be > endTime");
        }

        if ( interval >= end - time) {
            throw new Exception ("interval can not be >= EndTime - Time");
        }

        this.title = title;
        this.start = start;
        this.end = end;
        this.interval = interval;
        repeat = true;
    }

    /**
     *
     * @return name of task
     */
    public String getTitle() {
        return title;
    }

    /**
     * Set the name of task
     * @param title is name of task
     */
    public void setTitle(String title) {
        this.title = title;
    }

    /**
     * method of task status
     *
     * @param active <code>true</code> if the task active
     *               <code>false</code> if the task does not active
     */
    public void setActive(boolean active) {
        this.active = active;
    }

    /**
     *
     * @return status of task
     */
    public boolean isActive() {
        return active;
    }


    // методы дл€ счит и изм времени состо€ни€ что не повтор€ютс€
    /**
     * Reading time status of tasks that are not repeated
     *
     * @return if task is repeated back task start time
     *          else return time
     */
    public int getTime() {
        if (repeat) {
            return start;   //???
        }

        return time;
    }

    /**
     * Set time of tasks that are not repeated
     * if the problem is repeated, it has become such, not repeated.
     *
     * @param time is time for set
     */
    public void setTime(int time) {
        if (repeat) {
            repeat = false;
        }

        this.time = time;
    }

    // метод дл€ счит и изм зад что повтор€ютс€
    /**
     * Reading start time status of tasks that are repeated
     *
     * @return if task is repeated back task start time
     *          else return time
     */
    public int getStartTime() {
        if (repeat) {
            return start;   //???
        }

        return time; //- end; ???? час виконан€ задачи
    }

    /**
     * Reading end time of tasks that are repeated
     *
     * @return end time
     */
    public int getEndTime() {
        if (repeat) {
            return end;   //???
        }

        return time;
    }

    /**
     * method for reading interval of tasks that are repeated
     *
     * @return interval and if task not repeat return 0
     */
    public int getRepeatInterval() {
        if (!repeat) {
            return 0;   //???
        }

        return interval;
    }

    /**
     * method for setting the task that is repeated
     *
     * @param start     of task
     * @param end       of task
     * @param interval  of task
     */
    public void setTime(int start, int end, int interval) {
        if (!repeat) {
            repeat = true;
        }

        this.start = start;
        this.end = end;
        this.interval = interval;
    }

    //метод дл€ проверки поdторени€ задачи
    /**
     * method to check the recurrence of the problem
     *
     * @return <code>true</code> if the task repeat
     *               <code>false</code> if the task does not repeat
     */
    public boolean isRepeated() {
        return repeat;
    }

    // врем€ следуйщ выполнени€ задачи
    /**
     * method returns the next time the task after a specified
     * time current, if subsequent to the time the problem is
     * not fulfilled, the method must return -1.
     *
     * @param current after which the task is executed
     * @return time the next execution
     */
    public int nextTimeAfter(int current) {
        if (active) {

            if (time > current) {
                return time;
            }

            if (repeat) {
                if (start > current) {
                    return  start;
                } else {
                    int i = start;
                    while (i <= current) {
                        i += interval;
                    }
                    if (i > end) {
                        return -1;
                    }
                    return i;
                }
            }

        }

        return -1;
    }

///////////////////////////////////////////////////////////////////////////////////////////////
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Task task = (Task) o;

        if (time != task.time)   return false;
        //if (start != task.start) return false;
        //if (end != task.end)     return false;
        //if (interval != task.interval) return false;
        if (active != task.active) return false;
        if (repeat != task.repeat) return false;

        if (!title.equals(task.title)) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result;

        result = title != null ? title.hashCode() : 0;

        result = 31 * result + time;
  //     result = 31 * result + start;
    //   result = 31 * result + end;
      // result = 31 * result + interval;

        result = 31 * result + (active ? 1 : 0);
        result = 31 * result + (repeat ? 1 : 0);

        return result;
    }

    @Override
    public String toString() {
        StringBuilder t = new StringBuilder();

        t.append("Task{ ").append("title='").append(title).append("', time=").append(time).append(", start=")
                .append(start).append(", end=").append(end).append(", interval=").append(interval).append(", active=")
                .append(active).append(", repeat=").append(repeat).append('}');

        return t.toString();
    }

    @Override
    public Task clone() {
        Task result = null;

        try {
            result = (Task) super.clone();
        } catch (CloneNotSupportedException e) {
            e.printStackTrace();
        } catch (Exception e) {
            System.out.println("Task in clone not create");
        }

        try {
            result.setTitle(this.getTitle());
            result.setTime(this.getTime());
            result.setTime(this.getStartTime(), this.getEndTime(), this.getRepeatInterval());
            result.setActive(this.isActive());
            result.repeat = this.isRepeated();
        } catch (Exception e) {
            System.out.println("Task parametrs not set");
            //e.printStackTrace();
        }

        return result;
    }

}
