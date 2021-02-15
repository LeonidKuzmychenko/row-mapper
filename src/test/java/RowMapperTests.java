import data.animals.Cat;
import data.animals.Dog;
import data.animals.Lion;
import data.animals.Pig;
import data.animals.parent.Animal;
import data.furniture.Chair;
import data.furniture.Table;
import lk.utils.mapper.RowMapperService;
import org.junit.Assert;
import org.junit.Test;

public class RowMapperTests {

    private final RowMapperService<Dog> rowMapperService = new RowMapperService<>();

    @Test
    public void allFields() {
        Dog dog = new Dog("1", null, "3", null, "5", "6");
        Pig pig = new Pig("1p", "2p", "3p", "4p", "5p", "6p");
        dog = rowMapperService.join(dog, pig);
        Assert.assertEquals("1", dog.getField1());
        Assert.assertEquals("2p", dog.getField2());
        Assert.assertEquals("3", dog.getField3());
        Assert.assertEquals("4p", dog.getField4());
        Assert.assertEquals("5", dog.getField5());
        Assert.assertEquals("6", dog.getField6());
    }

    @Test
    public void allFieldsWithAnotherType() {
        Dog dog = new Dog("1", null, "3", "4", null, "6");
        Cat cat = new Cat("1c", "2c", "3c", "4c", 5, "6c");
        dog = rowMapperService.join(dog, cat);
        Assert.assertEquals("1", dog.getField1());
        Assert.assertEquals("2c", dog.getField2());
        Assert.assertEquals("3", dog.getField3());
        Assert.assertEquals("4", dog.getField4());
        Assert.assertEquals(null, dog.getField5());
        Assert.assertEquals("6", dog.getField6());
    }

    @Test
    public void allFieldsWithOverrideTypeInChild() {
        Dog dog = new Dog("1", null, null, "4", null, "6");
        Table table = new Table("1t", "2t", 3, "3t", "4t", "5t", "6t");
        dog = rowMapperService.join(dog, table);
        Assert.assertEquals("1", dog.getField1());
        Assert.assertEquals("2t", dog.getField2());
        Assert.assertEquals("3t", dog.getField3());
        Assert.assertEquals("4", dog.getField4());
        Assert.assertEquals("5t", dog.getField5());
        Assert.assertEquals("6", dog.getField6());
    }

    @Test
    public void allFieldsWithOverrideTypeInParent() {
        Dog dog = new Dog("1", null, null, "4", "5", "6");
        Chair chair = new Chair("1pc", "2pc",3, 22,"3pc", "4c", "5c", "6c");
        dog = rowMapperService.join(dog, chair);
        Assert.assertEquals("1", dog.getField1());
        Assert.assertEquals("2pc", dog.getField2());
        Assert.assertEquals("3pc", dog.getField3());
        Assert.assertEquals("4", dog.getField4());
        Assert.assertEquals("5", dog.getField5());
        Assert.assertEquals("6", dog.getField6());
    }

    @Test
    public void allFieldsNullNull() {
        Dog dog = new Dog("1", null, "3", null, "5", "6");
        Animal animal = new Animal("1a", null, "3a");
        dog = rowMapperService.join(dog, animal);
        Assert.assertEquals("1", dog.getField1());
        Assert.assertEquals(null, dog.getField2());
        Assert.assertEquals("3", dog.getField3());
        Assert.assertEquals(null, dog.getField4());
        Assert.assertEquals("5", dog.getField5());
        Assert.assertEquals("6", dog.getField6());
    }

    @Test
    public void differentNames() {
        Dog dog = new Dog("1", null, "3", null, "5", "6");
        Lion lion = new Lion("1l", "2l", "3l", "4l", "5l", "6l");
        dog = rowMapperService.join(dog, lion);
        Assert.assertEquals("1", dog.getField1());
        Assert.assertEquals("2l", dog.getField2());
        Assert.assertEquals("3", dog.getField3());
        Assert.assertEquals("4l", dog.getField4());
        Assert.assertEquals("5", dog.getField5());
        Assert.assertEquals("6", dog.getField6());
    }
}
