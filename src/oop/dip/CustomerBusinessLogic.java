package oop.dip;

public class CustomerBusinessLogic {
    CustomerDataAccessInterface customerDataAccessInterface;

    public CustomerBusinessLogic() {
       customerDataAccessInterface = DataAccessFactory.getCustomerDataAccess();
    }

    public String getCustomerId(int id){
        return customerDataAccessInterface.getCustomerName(id);
    }

}
