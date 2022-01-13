package oop.rebirth.chain_of_responsibility.leave;

public class HR extends LeaveHandler{
    @Override
    public void applyLeave(Leave leave) {
        if (leave.getNumberOfDays() <= 21){
            System.out.println("Leave approved by HR");
        }
        else{
            supervisor.applyLeave(leave);
        }
    }
}
