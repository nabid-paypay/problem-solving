package oop.dip;

public class DataAccessFactory {
    public static CustomerDataAccessInterface getCustomerDataAccess(){
        return new CustomerDataAccessInterfaceImpl();
    }
}
