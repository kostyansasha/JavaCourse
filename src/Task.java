/**
 * Created by ���� on 03.10.2015.
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

    //�����������
//����� ������ �� ������ ������ ��� ����� � ����� ���
// ��� ��������� �� ������� ������
    public Task(String title, int time) {
        this.title = title;
        this.time = time;
    }


    //�����������
// ������� ������ ��� ����� � �������� ������� ���� (� ���, � ��)
// �� ������� ���������� � �� ������ �����
    public Task(String title, int start, int end, int interval) {
        this.title = title;
        this.start = start;
        this.end = end;
        this.interval = interval;
        repeat = true;
    }


    // ������ ��� ��������
    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    // ������ ��� ���������
    public void setActive(boolean active) {
        this.active = active;
    }

    public boolean isActive() {
        return active;
    }


    // ������ ��� ���� � ��� ������� ��������� ��� �� �����������
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

    // ����� ��� ���� � ��� ��� ��� �����������
    public int getStartTime() {
        if (repeat) {
            return start;   //???
        }
        return time; //- end; ???? ��� �������� ������
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


    //����� ��� �������� ��d������� ������
    public boolean isRepeated() {
        return repeat;
    }

// ����� ������� ���������� ������

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
