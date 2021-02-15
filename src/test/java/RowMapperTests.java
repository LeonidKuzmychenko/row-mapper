import data.Lion;
import data.parent.Animal;
import data.Cat;
import data.Dog;
import data.Pig;
import org.junit.Assert;
import org.junit.Test;
import lk.utils.mapper.RowMapperService;

public class RowMapperTests {

    private final RowMapperService<Dog> rowMapperService = new RowMapperService<>();

    @Test
    public void allFields() {
        Dog dog = new Dog("1", null, "3", null, "5", "6");
        Pig pig = new Pig("1p", "2p", "3p", "4p", "5p", "6p");
        dog = rowMapperService.join(dog, pig);
        Assert.assertEquals(dog.getField1(),"1");
        Assert.assertEquals(dog.getField2(),"2p");
        Assert.assertEquals(dog.getField3(),"3");
        Assert.assertEquals(dog.getField4(),"4p");
        Assert.assertEquals(dog.getField5(),"5");
        Assert.assertEquals(dog.getField6(),"6");
    }

    @Test
    public void allFieldsWithAnotherType() {
        Dog dog = new Dog("1", null, "3", "4", null, "6");
        Cat cat = new Cat("1c", "2c", "3c", "4c", 5, "6c");
        dog = rowMapperService.join(dog, cat);
        Assert.assertEquals(dog.getField1(),"1");
        Assert.assertEquals(dog.getField2(),"2c");
        Assert.assertEquals(dog.getField3(),"3");
        Assert.assertEquals(dog.getField4(),"4");
        Assert.assertEquals(dog.getField5(),null);
        Assert.assertEquals(dog.getField6(),"6");
    }

    @Test
    public void allFieldsNullNull() {
        Dog dog = new Dog("1", null, "3", null, "5", "6");
        Animal animal = new Animal("1a", null, "3a");
        dog = rowMapperService.join(dog, animal);
        Assert.assertEquals(dog.getField1(),"1");
        Assert.assertEquals(dog.getField2(),null);
        Assert.assertEquals(dog.getField3(),"3");
        Assert.assertEquals(dog.getField4(),null);
        Assert.assertEquals(dog.getField5(),"5");
        Assert.assertEquals(dog.getField6(),"6");
    }

    @Test
    public void differentNames() {
        Dog dog = new Dog("1", null, "3", null, "5", "6");
        Lion lion = new Lion("1l", "2l", "3l", "4l", "5l", "6l");
        dog = rowMapperService.join(dog, lion);
        Assert.assertEquals(dog.getField1(),"1");
        Assert.assertEquals(dog.getField2(),"2l");
        Assert.assertEquals(dog.getField3(),"3");
        Assert.assertEquals(dog.getField4(),"4l");
        Assert.assertEquals(dog.getField5(),"5");
        Assert.assertEquals(dog.getField6(),"6");
    }
}
