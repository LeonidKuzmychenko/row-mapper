package data.furniture.parent;

import lk.utils.mapper.annotation.RowMapper;

public class Furniture{

    @RowMapper(name = "field1")
    private String field1;
    @RowMapper(name = "field2")
    private String field2;
    @RowMapper(name = "field3")
    private Integer field3;

    public Furniture(String field1, String field2, Integer field3) {
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

    public Integer getField3() {
        return field3;
    }

}
