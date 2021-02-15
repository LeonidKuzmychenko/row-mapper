package data.animals;

import data.animals.parent.Animal;
import lk.utils.mapper.annotation.RowMapper;

public class Lion extends Animal {

    @RowMapper(name = "field4")
    private String fieldFour;
    @RowMapper(name = "field5")
    private String fieldFive;
    @RowMapper(name = "field6")
    private String fieldSix;

    public Lion(String field1, String field2, String field3, String fieldFour, String fieldFive, String fieldSix) {
        super(field1, field2, field3);
        this.fieldFour = fieldFour;
        this.fieldFive = fieldFive;
        this.fieldSix = fieldSix;
    }

    public String getFieldFour() {
        return fieldFour;
    }

    public String getFieldFive() {
        return fieldFive;
    }

    public String getFieldSix() {
        return fieldSix;
    }
}
