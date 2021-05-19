package cz.cvut.fel.pjv.TimeClock;

import static java.lang.Thread.sleep;

public class TheClock implements Runnable {

    private int timeLets;
    Thread thr = null;
    boolean threadSuspended;

    public void setTimeLets(int timeLets) {
        this.timeLets = timeLets;
    }

    public int getTimeLets() {
        return timeLets;
    }

    public TheClock(int timeLets) {
        this.timeLets = timeLets;
    }

    public void start() {
        if (thr == null) {
            thr = new Thread(this);
            thr.setPriority(Thread.MIN_PRIORITY);
            threadSuspended = false;
            thr.start();
        } else {
            if (threadSuspended) {
                threadSuspended = false;
                synchronized (this) {
                    notify();
                }
            }
        }
    }

    public void stop() {
        threadSuspended = true;
    }

    private void changeTime(int time) {
        if (timeLets > 0) {
            timeLets -= time;
        } else {
            System.err.println("End of timer!");
            thr.run();
        }
    }

    @Override
    public void run() {
        try {
            while (true) {
                System.out.println("Time lefts: " + timeLets);
                if (threadSuspended) {
                    synchronized (this) {
                        while (threadSuspended) {
                            wait();
                        }
                    }
                }
                changeTime(1000);
                thr.sleep(1000);
            }
        } catch (Exception e) {

        }
    }

    public boolean isClockEnded() {
        if (timeLets > 0) {
            return false;
        } else {
            return true;
        }
    }
}
