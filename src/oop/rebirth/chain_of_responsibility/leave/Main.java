package oop.rebirth.chain_of_responsibility.leave;

public class Main {
    public static void main(String[] args) {

        TeamLeader teamLeader = new TeamLeader();
        ProjectLeader projectLeader = new ProjectLeader();
        HR hr = new HR();
        CEO ceo = new CEO();

        teamLeader.setSuperVisor(projectLeader);
        projectLeader.setSuperVisor(hr);
        hr.setSuperVisor(ceo);

        Leave leave1 = new Leave(5,4, ReasonType.REGULAR);
        teamLeader.applyLeave(leave1);

        Leave leave2 = new Leave(5,5, ReasonType.REGULAR);
        teamLeader.applyLeave(leave2);

        Leave leave3 = new Leave(12,3, ReasonType.REGULAR);
        teamLeader.applyLeave(leave3);

        Leave leave4 = new Leave(18,2, ReasonType.CRITICAL);
        teamLeader.applyLeave(leave4);

        Leave leave5 = new Leave(18,2, ReasonType.REGULAR);
        teamLeader.applyLeave(leave5);

        Leave leave6 = new Leave(30,2, ReasonType.SPECIAL);
        teamLeader.applyLeave(leave6);
    }
}
