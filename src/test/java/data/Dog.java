package data;

import data.parent.Animal;
import row.RowMapper;

public class Dog extends Animal {

    @RowMapper(name = "field4")
    private String field4;
    @RowMapper(name = "field5")
    private String field5;
    @RowMapper(name = "field6")
    private String field6;

    public Dog(String field1, String field2, String field3, String field4, String field5, String field6) {
        super(field1, field2, field3);
        this.field4 = field4;
        this.field5 = field5;
        this.field6 = field6;
    }

    public String getField4() {
        return field4;
    }

    public void setField4(String field4) {
        this.field4 = field4;
    }

    public String getField5() {
        return field5;
    }

    public void setField5(String field5) {
        this.field5 = field5;
    }

    public String getField6() {
        return field6;
    }

    public void setField6(String field6) {
        this.field6 = field6;
    }

    @Override
    public String toString() {
        return "data.Dog{" +
                "field1='" + getField1() + '\'' +
                ", field2='" + getField2() + '\'' +
                ", field3='" + getField3() + '\'' +
                ", field4='" + field4 + '\'' +
                ", field5='" + field5 + '\'' +
                ", field6='" + field6 + '\'' +
                '}';
    }
}
