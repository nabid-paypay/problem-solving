package oop.rebirth.proxy.internet_access;

public class RealInternetAccess implements OfficeInternetAccess{
    String emp;

    public RealInternetAccess(String emp) {
        this.emp = emp;
    }

    @Override
    public void grantAccess() {
        System.out.println("internet access granted for: " + emp);
    }
}
