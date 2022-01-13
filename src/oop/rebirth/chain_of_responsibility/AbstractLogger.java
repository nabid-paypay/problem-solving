package oop.rebirth.chain_of_responsibility;

public abstract class AbstractLogger {
    public static final int INFO = 1;
    public static final int DEBUG = 2;
    public static final int ERROR = 3;

    protected int level;
    protected AbstractLogger nextLogger;

    public void setNextLogger(AbstractLogger abstractLogger){
        this.nextLogger = abstractLogger;
    }

    public void logMessage(int level, String content){
        if(this.level <= level){
            write(content);
        }
        if(nextLogger!=null){
            nextLogger.logMessage(level, content);
        }
    }

    abstract protected void write(String message);
}
