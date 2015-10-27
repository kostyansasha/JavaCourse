/**
 * Class that describes the Task
 *
 * @author Sasha Kostyan
 * @version %I%, %G%
 */

//package ua.sumdu.j2se.kostyan.tasks;

public class Task {
    private String title;
    private int time;

    private int start;
    private int end;
    private int interval;

    private String titleInterval;
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
     */
    public Task(String title, int time) {
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
     */
    public Task(String title, int start, int end, int interval) {
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
     * @param title
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
     * @param time
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
     * @param current
     * @return
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

}
