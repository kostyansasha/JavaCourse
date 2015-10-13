/**
 * Created by Саша on 03.10.2015.
 */

//package ua.sumdu.j2se.kostyan.tasks;

public class ArrayTaskList extends TaskList {

    // variable for size of array
    private int numberOfSizeArrayTask;
    // create array of tasks
    private Task arrayTask[] /*= new Task[numberOfSizeArrayTask]*/;

/**
  * Add to the list of task
  */

  public void add(Task task) throws Exception {
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

        Task tempArrayTask[] = new Task[numberOfSizeArrayTask + 1];
        for (int i=0; i < numberOfSizeArrayTask; i++) {
                tempArrayTask[i] = arrayTask[i];
        }
        tempArrayTask[numberOfSizeArrayTask] = task;
        arrayTask = tempArrayTask;
        numberOfSizeArrayTask++;

    }

/** Delete task from list
  *
  */
   public boolean remove(Task task) {
        int i; // to find a match for the entire array
        int j; // to move an item after the first found
        boolean itemFound; //status delete
        itemFound = false;

        for (i = 0; i < numberOfSizeArrayTask; i++) { //Not ArrayTask.length-1!
            if (task == arrayTask[i]) {  /*


            METOD equals() !!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!


            */
                // delete the last element
                if (i == numberOfSizeArrayTask-1) {
                    arrayTask[i] = null;
                    numberOfSizeArrayTask -= 1;

                    Task tempArrayTask[] = new Task[numberOfSizeArrayTask];
                    for (int k=0; k < numberOfSizeArrayTask; k++) {
                        tempArrayTask[k] = arrayTask[k];
                    }
                    arrayTask = tempArrayTask;

                    itemFound = true;
                    break;
                }
                // not last element, fist found
                for (j = i; j < numberOfSizeArrayTask-1; j++) {
                    arrayTask[j] = arrayTask[j + 1];
                }
                arrayTask[numberOfSizeArrayTask-1] = null;
                numberOfSizeArrayTask -= 1;

                Task tempArrayTask[] = new Task[numberOfSizeArrayTask];
                for (int k=0; k < numberOfSizeArrayTask; k++) {
                    tempArrayTask[k] = arrayTask[k];
                }
                arrayTask = tempArrayTask;

                itemFound = true;
                break;
            }
        }
        // task is not found in the array
        if (!itemFound) {
            return false;
        }
        return true;
    }

/** Number of jobs in the list
  *
  */
    public int size() {
        return numberOfSizeArrayTask;
    }

/** return the number of task that specified in the list
  *
 *
 *
  */
    public Task getTask(int index) {
        if (index > numberOfSizeArrayTask || index < 0) {
            return null;
        }
        return arrayTask[index];
    }

/**
  *
  * method that returns a subset of the tasks that
  * are scheduled to perform in the interval
  *
  */
/*   public TaskList incoming(int from, int to) {
        ArrayTaskList arrayIntTo = new ArrayTaskList();

        for (int i = 0; i < numberOfSizeArrayTask; i++) {
            // Not active task
            if (!arrayTask[i].isActive()) {
                continue;
            } else {
                // active task
                // Not repeat
                if (!arrayTask[i].isRepeated()) {
                    if (arrayTask[i].getTime() > from
                            && arrayTask[i].getTime() <= to) {
                        try {
                            arrayIntTo.add(arrayTask[i]);
                        } catch (Exception e) {
                            return null;
                        }
                    }
                    continue;
                }
                    // repeat task
                if (arrayTask[i].isRepeated()) {
                    int a;
                    a = arrayTask[i].nextTimeAfter(from);
                    if (a <= to && a >= from) {
                                        // >= from >>> becouse can return -1
                        try {
                            arrayIntTo.add(arrayTask[i]);
                        } catch (Exception e) {
                            return null;
                        }
                    }
                    continue;
                }
            }
        }
        return arrayIntTo;
    }
*/

}