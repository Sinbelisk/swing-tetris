package tetris.gameLogic;

public class Timer {
    private long lastTime;
    private int delay;

    public Timer(int delay){
        this.delay = delay;
        lastTime = System.currentTimeMillis();
    }
    public boolean hasElapsed(){
        long currentTime = System.currentTimeMillis();
        if(currentTime - lastTime >= delay){
            reset();
            return true;
        }
        return false;
    }
    public void reset(){
        lastTime = System.currentTimeMillis();
    }

    public long getLastTime() {
        return lastTime;
    }

    public void setLastTime(long lastTime) {
        this.lastTime = lastTime;
    }

    public int getDelay() {
        return delay;
    }

    public void setDelay(int delay) {
        this.delay = delay;
    }
}
