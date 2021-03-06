import java.io.File;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Scanner;
import java.util.SortedMap;

/**
 * Created by ���� on 20.12.2015.
 */
public class Model {
    TaskList tasks;
    File file;
    public Model(){
        try {
            tasks = new ArrayTaskList();
            file = new File("tasks_File.txt");;
            TaskIO.readText(tasks, file);
        } catch (Exception e){
            System.out.println("inisialization task error");
        }
    }

    public void AllTask(){
        System.out.println(tasks.toString());

    }
    public void Close(){
        try {
            TaskIO.writeText(tasks, file);
            System.exit(0);
        } catch (Exception e ){
            System.out.println("write in file Error");
        } finally {
            System.exit(1);
        }
    }

    public SortedMap Period(int i){
        Date d = new Date(System.currentTimeMillis());
        Date d2 = new Date( d.getTime() + (i*1000 * 86400) );
        return ( Tasks.calendar(tasks, d, d2 ));
    }
    public void Remove(int i){
        try {
            tasks.remove(tasks.getTask(i));
        } catch (Exception e){
            System.out.println("task remove Error");
        }
    }
    public void View(int a){
        System.out.println(tasks.getTask(a).toString());
    }
    public void Edit(int i){
        Task task = tasks.getTask(i).clone();
        Scanner s = new Scanner(System.in);
        DateFormat dateForm = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SSS");
        String choose;
        String str = null;
        while (true) {
            if (task.isRepeated())
                System.out.println("name- 2en, date- 2ed, endDate- 2ee, interval- 2ei, active/unact- 2ea, view-v, confirm- cof ");
            else System.out.println("name- 2en, date- 2ed, active/unact- 2ea, view-v, confirm- cof ");
            choose = s.nextLine();
            if (choose.equals("2en")) {//name
                System.out.println("write new name");
                task.setTitle(s.nextLine());
            }
            if (choose.equals("2ed")){
                System.out.println("number format: yyyy-MM-dd HH:mm:ss.SSS");
                while (str == null || str.equals(""))
                    str = s.nextLine();

                try {
                    task.setTime(dateForm.parse(str));
                } catch (ParseException e) {
                    System.out.println("not correct");
                }
            }
            if (task.isRepeated()){
                if (choose.equals("2ee")){
                    System.out.println(" vvod endDate");
                    System.out.println("number format: yyyy-MM-dd HH:mm:ss.SSS");
                    String end=null;
                    while (end == null || end.equals(""))
                        end = s.nextLine();

                    try {
                        task.setTime(task.getTime(), dateForm.parse(end), task.getRepeatInterval());
                    } catch (ParseException e) {
                    }
                }
                if (choose.equals("2ei")){
                    System.out.println(" vvod interval");
                    int interval = 0;
                    while (interval <= 0)
                        interval = s.nextInt();

                    task.setTime(task.getTime(), task.getEndTime(), interval);
                }
            }
            if (choose.equals("2ea")){
                System.out.println("if task active write Y, else N");
                while(true){
                    String act = s.nextLine();
                    if (act.equals("Y")) {
                        task.setActive(true);
                        break;
                    }
                    if (act.equals("N")) {
                        task.setActive(false);
                        break;
                    }
                }
            }
            if (choose.equals("v")){//view
                System.out.println(task.toString());
            }
            if (choose.equals("cof")){
                while (true) {
                    System.out.println("write Y or n ");
                    String conf = s.nextLine();
                    if (conf.equals("Y")) {
                        try {
                            tasks.remove(tasks.getTask(i));
                            tasks.add(task);
                            return;
                        } catch (Exception e) {
                        }
                    }
                    if (conf.equals("n")) {
                        task = null;
                        return;
                    }
                }
            }
        }
    }
    public void AddTask(){
        Scanner scan = new Scanner(System.in);
        DateFormat dateForm = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SSS");
        Task task = null;

        System.out.println("write new name");
        String name = scan.nextLine();
        System.out.println("write start time");
        System.out.println("number format: yyyy-MM-dd HH:mm:ss.SSS");
        String str = null;
        while (str == null || str.equals(""))
            str = scan.nextLine();

        System.out.println("is task repeat? write y or n");
        String ch = "";
        while(!ch.equals("y") && !ch.equals("n") ) {
            ch = scan.nextLine();
        }
        if (ch.equals("y")) {
            System.out.println(" write endDate");
            System.out.println("number format: yyyy-MM-dd HH:mm:ss.SSS");
            String end = null;
            while (end == null || end.equals(""))
                end = scan.nextLine();

            System.out.println(" vvod interval");
            int interval = 0;
            while (interval <= 0)
                interval = scan.nextInt();


            try {
                task = new Task(name, dateForm.parse(str), dateForm.parse(end), interval);
            } catch (Exception e) {
                e.printStackTrace();
            }
        } else {
            try {
                task = new Task(name, dateForm.parse(str) );
            } catch (Exception e) {
            }
        }
        System.out.println("if task active write y, else n");
        String act = "";
        while(!act.equals("y") && !act.equals("n") )
            act = scan.nextLine();
        if (act.equals("y"))
            task.setActive(true);
        if (act.equals("n"))
            task.setActive(false);

        System.out.println("are you want save this task:");
        System.out.println(task.toString());
        while (true) {
            System.out.println("write Y or n ");
            String conf = scan.nextLine();
            if (conf.equals("Y")) {
                try {
                    tasks.add(task);
                    System.out.println("tass add");
                } catch (Exception e) {
                }
                return;
            }
            if (conf.equals("n")) {
                System.out.println("task not add");
                task = null;
                return;
            }
        }
    }
}
