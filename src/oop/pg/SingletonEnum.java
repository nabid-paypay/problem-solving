package oop.pg;

public enum SingletonEnum {
    INSTANCE, TEST;
    int val;

    public int getVal() {
        return val;
    }

    public void setVal(int val) {
        this.val = val;
    }

    public static void main(String[] args) {
        SingletonEnum singletonEnum = SingletonEnum.INSTANCE;
        SingletonEnum singletonEnum1 = SingletonEnum.TEST;
        singletonEnum.setVal(2);
        System.out.println("singletonEnum.getVal() = " + singletonEnum.getVal());
        singletonEnum.setVal(3);
        System.out.println("singletonEnum.getVal() = " + singletonEnum.getVal());

        System.out.println(singletonEnum.equals("INSTANCE") ? "ins" : "na");
        SingletonEnum ss = SingletonEnum.valueOf("TEST");
        System.out.println("bbL :" + SingletonEnum.valueOf("TEST"));
        
        Object obj = new Object();
        System.out.println("obj.hashCode() = " + obj.hashCode());
        Object obj1 = new Object();
        System.out.println("obj1.hashCode() = " + obj1.hashCode());
    }
}
