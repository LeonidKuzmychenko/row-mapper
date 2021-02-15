import row.RowMapperService;

public class Main {

    public static void main(String[] args) {
        Dog dog = new Dog("1", null, "3", null, "5", "6");
        Cat cat = new Cat("01", "02", null, "04", "05", "06");
        System.out.println(dog);
        dog = new RowMapperService<Dog>().join(dog, cat);
        System.out.println(dog);
    }

}
