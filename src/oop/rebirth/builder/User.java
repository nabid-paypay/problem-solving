package oop.rebirth.builder;

public class User {
    private final String firstName;
    private final String lastName;
    private final int age;
    private final String phone;
    private final String address;

    private User(UserBuilder userBuilder) {
        this.firstName = userBuilder.firstName;
        this.lastName = userBuilder.lastName;
        this.age = userBuilder.age;
        this.phone = userBuilder.phone;
        this.address = userBuilder.address;
    }

    @Override
    public String toString() {
        return "User{" +
                "firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", age=" + age +
                ", phone='" + phone + '\'' +
                ", address='" + address + '\'' +
                '}';
    }

    public static class UserBuilder{
        private String firstName;
        private String lastName;
        private int age;
        private String phone;
        private String address;

        public UserBuilder age(int age){
            this.age = age;
            return this;
        }
        public UserBuilder firstName(String firstName){
            this.firstName = firstName;
            return this;
        }
        public UserBuilder lastName(String lastName){
            this.lastName = lastName;
            return this;
        }
        public UserBuilder phone(String phone){
            this.phone = phone;
            return this;
        }
        public UserBuilder address(String address){
            this.address = address;
            return this;
        }

        public User build(){
            User user = new User(this);
            return user;
        }
    }

    public static void main(String[] args) {
        User user = new User.UserBuilder()
                            .firstName("nabidul")

                            .address("ctg")
                            .age(29)
                            .phone("01780171423")
                            .build();
        System.out.println(user);

        System.out.println(Double.parseDouble("ab"));
        int count0 = 0;

    }
}
