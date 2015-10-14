/**
 * Created by Ñàøà on 03.10.2015.
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

    //ÊÎÍÑÒĞÓÊÒÎĞ
//íåàêò çàäà÷à ùî êîíñòğ çàäà÷ó ÿêà âèêîí ó çàäàí ÷àñ
// áåç ïîâòîğåíü ³ç çàäàíîş íàçâîş
    public Task(String title, int time) {
        this.title = title;
        this.time = time;
    }


    //ÊÎÍÑÒĞÓÊÒÎĞ
// íåàêòèâ çàäà÷à ÿêà âèêîí ó çàäàíîìó ïğîì³æêó ÷àñó (³ ïî÷, ³ ê³í)
// ³ç çàäàíèì ³íòåğâàëîì ³ ìàº çàäàíó íàçâó
    public Task(String title, int start, int end, int interval) {
        this.title = title;
        this.start = start;
        this.end = end;
        this.interval = interval;
        repeat = true;
    }


    // ìåòîäû äëÿ íàçâàíèÿ
    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    // ìåòîäû äëÿ ñîñòîÿíèÿ
    public void setActive(boolean active) {
        this.active = active;
    }

    public boolean isActive() {
        return active;
    }


    // ìåòîäû äëÿ ñ÷èò è èçì âğåìåíè ñîñòîÿíèÿ ÷òî íå ïîâòîğÿşòñÿ
    public int getTime() {
        if (repeat) {
            return start;   //???
        }
        return time;
    }

    public void setTime(int time) {
        if (repeat) {
            repeat = false;
        }
        this.time = time;
    }

    // ìåòîä äëÿ ñ÷èò è èçì çàä ÷òî ïîâòîğÿşòñÿ
    public int getStartTime() {
        if (repeat) {
            return start;   //???
        }
        return time; //- end; ???? ÷àñ âèêîíàíÿ çàäà÷è
    }

    public int getEndTime() {
        if (repeat) {
            return end;   //???
        }
        return time;
    }

    public int getRepeatInterval() {
        if (!repeat) {
            return 0;   //???
        }
        return interval;
    }

    public void setTime(int start, int end, int interval) {
        if (!repeat) {
            repeat = true;
        }
        this.start = start;
        this.end = end;
        this.interval = interval;
    }


    //ìåòîä äëÿ ïğîâåğêè ïîdòîğåíèÿ çàäà÷è
    public boolean isRepeated() {
        return repeat;
    }

// âğåìÿ ñëåäóéù âûïîëíåíèÿ çàäà÷è

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
