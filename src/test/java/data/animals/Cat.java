package data.animals;

import data.animals.parent.Animal;
import lk.utils.mapper.annotation.RowMapper;

public class Cat extends Animal {

    @RowMapper(name = "field4")
    private String field4;
    @RowMapper(name = "field5")
    private Integer field5;
    @RowMapper(name = "field6")
    private String field6;

    public Cat(String field1, String field2, String field3, String field4, Integer field5, String field6) {
        super(field1, field2, field3);
        this.field4 = field4;
        this.field5 = field5;
        this.field6 = field6;
    }

    public String getField4() {
        return field4;
    }

    public Integer getField5() {
        return field5;
    }

    public String getField6() {
        return field6;
    }
}
