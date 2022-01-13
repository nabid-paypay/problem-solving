package oop.rebirth.chain_of_responsibility.leave;

public abstract class LeaveHandler {
    protected LeaveHandler supervisor;
    public void setSuperVisor(LeaveHandler superVisor) {
        this.supervisor = superVisor;
    }

    public abstract void applyLeave(Leave leave);
}
