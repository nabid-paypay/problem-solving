package oop.rebirth.chain_of_responsibility.leave;

public class TeamLeader extends LeaveHandler{
    @Override
    public void applyLeave(Leave leave) {
        if (leave.getNumberOfDays() <= 7){
            System.out.println("Leave approved by Team Leader");
        }
        else{
            supervisor.applyLeave(leave);
        }
    }
}
