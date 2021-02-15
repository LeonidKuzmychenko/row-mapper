package data.parent;

import lk.utils.mapper.annotation.RowMapper;

public class Animal {
    @RowMapper(name = "field1")
    private String field1;
    @RowMapper(name = "field2")
    private String field2;
    @RowMapper(name = "field3")
    private String field3;

    public Animal(String field1, String field2, String field3) {
        this.field1 = field1;
        this.field2 = field2;
        this.field3 = field3;
    }

    public String getField1() {
        return field1;
    }

    public String getField2() {
        return field2;
    }

    public String getField3() {
        return field3;
    }
}
