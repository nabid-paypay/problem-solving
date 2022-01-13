package oop.rebirth.proxy.internet_access;

public class ProxyInternetAccess implements OfficeInternetAccess{
    String emp;
    RealInternetAccess realInternetAccess;
    public ProxyInternetAccess(String emp) {
        this.emp = emp;
    }

    @Override
    public void grantAccess() {
        if(getRole(emp) > 2){
            realInternetAccess = new RealInternetAccess(emp);
            realInternetAccess.grantAccess();
        }
        else{
            System.out.println("not granted. below 10 level");
        }
    }

    private int getRole(String emp) {
        return emp.startsWith("n") ? 10 : 2;
    }

    public static void main(String[] args) {
        ProxyInternetAccess proxyInternetAccess = new ProxyInternetAccess("nabid");
        proxyInternetAccess.grantAccess();
        proxyInternetAccess = new ProxyInternetAccess("abraka");
        proxyInternetAccess.grantAccess();

        StringBuilder sb = new StringBuilder();
        sb.append('1');
        sb.append('2');
        sb.append('3');
        sb.deleteCharAt(0);
        System.out.println(sb);
        sb.append('4');
        System.out.println(sb);

        System.out.println(16 << 1);
    }
}
