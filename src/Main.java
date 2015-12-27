import java.io.IOException;

//package ua.sumdu.j2se.kostyan.tasks;
public class Main {
    public static void main(String[] args) {
        Runnable r = new Runnable() {
            @Override
            public void run() {
                try {
                    View.Start();
                } catch (IOException e) {

                }
            }
        };
        Thread t = new Thread(r);
        t.start();
    }
}
