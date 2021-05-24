package cz.cvut.fel.pjv.TimeClock;

import java.util.Date;

public class TheClock implements Runnable {

    private double timeLefts;
    private long startTime;
    private long endTime;
    Thread thr = null;
    boolean threadSuspended;
    private boolean isActive;

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

    public double getTimeLefts() {
        return timeLefts;
    }

    private void setActualStartTime(){
        startTime = System.nanoTime();
    }
    private void setActualEndTime(){
        endTime = System.nanoTime();
    }
    private double timeDifferent(){
        return (endTime - startTime) / 1e6;
    }

    public TheClock(double timeLets) {
        this.timeLefts = timeLets;
        setActualEndTime();
        setActualStartTime();
        this.isActive = true;
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

    private void changeTime(double time) {
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
                if(isClockEnded() && isActive != false){
                    isActive = false;
                    System.err.println("The time has been expired!");
                }
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
                setActualStartTime();
                thr.sleep(5000);
                setActualEndTime();
                changeTime(timeDifferent());
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
    private void displayTime(double oTimeLeft){
        int timeLefts = (int) oTimeLeft;
        timeLefts = timeLefts / 1000;
        int hour = timeLefts / 3600;
        timeLefts -= hour * 3600;
        int min = timeLefts / 60;
        timeLefts -= min * 60;
        int sec = timeLefts;

        System.out.println("Time lefts: " + hour + "h " + min + "m " + sec + "s");
    }
}
