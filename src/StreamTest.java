import java.util.Comparator;
import java.util.List;

public class StreamTest {
    public static class Person {
        String name;
        int age;

        public Person(String name, int age) {
            this.name = name;
            this.age = age;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public int getAge() {
            System.out.println("getAge() called for " + this.name + " " + this.age);
            return age;
        }

        public void setAge(int age) {
            this.age = age;
        }
    }

    public static List<Person> createPeople(){
        return List.of(

                new Person("a", 10),
                new Person("b", 11),
                new Person("c", 12),
                new Person("d", 13),
                new Person("e", 14)

        );
    }
    public static void main(String[] args) {
        Comparator<Person> cmp = (Person o1, Person o2) -> Integer.compare(o1.getAge(), o2.getAge());

        Comparator<Person> cmpAge = Comparator.comparing(person -> person.getAge());
        System.out.println(createPeople()
                .stream()
                .sorted(cmp)
                .map(Person::getName)
                .findFirst().
                 orElse(""));

    }
}
