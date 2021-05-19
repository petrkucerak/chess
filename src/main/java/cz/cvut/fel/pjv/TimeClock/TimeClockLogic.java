package cz.cvut.fel.pjv.TimeClock;

/**
 * Implementations of custom Timeclock
 */
public class TimeClockLogic {
    public static void main(String[]args) {
        TheClock timer1 = new TheClock(10000);
        TheClock timer2 = new TheClock(10000);
        Thread thread1 = new Thread(timer1);
        Thread thread2 = new Thread(timer2);
        //thread1.setDaemon(true);
        //thread2.setDaemon(true);

        thread1.start();
    }
}
