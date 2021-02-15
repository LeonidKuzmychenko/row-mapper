package data.furniture;

import data.furniture.parent.Furniture;
import lk.utils.mapper.annotation.RowMapper;

public class Table extends Furniture {

    @RowMapper(name = "field3")
    private String field3;
    @RowMapper(name = "field4")
    private String field4;
    @RowMapper(name = "field5")
    private String field5;
    @RowMapper(name = "field6")
    private String field6;

    public Table(String field1, String field2, Integer field3, String field3v2, String field4, String field5, String field6) {
        super(field1, field2, field3);
        this.field3 = field3v2;
        this.field4 = field4;
        this.field5 = field5;
        this.field6 = field6;
    }
}
