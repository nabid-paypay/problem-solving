package oop.rebirth.chain_of_responsibility.leave;

public class ProjectLeader extends LeaveHandler{
    @Override
    public void applyLeave(Leave leave) {
        if (leave.getNumberOfDays() <= 14){
            System.out.println("Leave approved by Project Leader");
        }
        else{
            supervisor.applyLeave(leave);
        }
    }
}
