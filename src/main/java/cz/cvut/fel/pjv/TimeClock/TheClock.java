package cz.cvut.fel.pjv.TimeClock;

import static java.lang.Thread.sleep;

public class TheClock implements Runnable {

    private int timeLefts;
    Thread thr = null;
    boolean threadSuspended;

    public void setTimeLefts(int timeLefts) {
        this.timeLefts = timeLefts;
    }

    public void setThr(Thread thr) {
        this.thr = thr;
    }

    public void setThreadSuspended(boolean threadSuspended) {
        this.threadSuspended = threadSuspended;
    }

    public Thread getThr() {
        return thr;
    }

    public boolean isThreadSuspended() {
        return threadSuspended;
    }

    public int getTimeLefts() {
        return timeLefts;
    }

    public TheClock(int timeLets) {
        this.timeLefts = timeLets;
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
        if (!isClockEnded()) {
            timeLefts -= time;
        } else {
            // System.err.println("End of timer!");
            thr.run();
        }
    }

    @Override
    public void run() {
        try {
            while (true) {
                if (!isClockEnded()) {
                    displayTime(timeLefts);
                }
                if (threadSuspended) {
                    synchronized (this) {
                        while (threadSuspended) {
                            wait();
                        }
                    }
                }
                changeTime(5000);
                thr.sleep(5000);
            }
        } catch (Exception e) {

        }
    }

    public boolean isClockEnded() {
        if (timeLefts > 0) {
            return false;
        } else {
            return true;
        }
    }
    private void displayTime(int timeLefts){
        timeLefts = timeLefts / 1000;
        int hour = timeLefts / 3600;
        timeLefts -= hour * 3600;
        int min = timeLefts / 60;
        timeLefts -= min * 60;
        int sec = timeLefts;

        System.out.println("Time lefts: " + hour + "h " + min + "m " + sec + "s");
    }
}
