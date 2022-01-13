package oop.rebirth.chain_of_responsibility.leave;

public class CEO extends LeaveHandler{
    @Override
    public void applyLeave(Leave leave) {
        if (leave.getNumberOfDays() > 21 && leave.getReason().equals(ReasonType.SPECIAL)){
            System.out.println("Leave approved by CEO");
        }
        else{
            System.out.println("LEAVE denied by manger");
        }
    }
}
