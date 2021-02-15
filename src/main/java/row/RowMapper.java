package row;

import org.apache.commons.lang3.reflect.FieldUtils;

import java.lang.reflect.Field;
import java.util.List;
import java.util.stream.Collectors;

public class RowMapper<T> {

    public T join(T to, Object from) {
        List<Field> toFields = FieldUtils.getAllFieldsList(to.getClass());
        List<Field> fromFields = FieldUtils.getAllFieldsList(from.getClass());

        toFields = removeDuplicate(toFields);
        fromFields = removeDuplicate(fromFields);

        toFields = filterNotNull(toFields, to);
        fromFields = filterNull(fromFields, from);

        for (Field fromField : fromFields) {
            for (Field toField : toFields) {
                String[] toNameArray = toField.getName().split("\\.");
                //field1.getName().replace(field1.getType().toString(), "");
                String[] fromNameArray = fromField.getName().split("\\.");
                //field1.getName().replace(field1.getType().toString(), "");
                String toName = toNameArray[toNameArray.length - 1];
                String fromName = fromNameArray[fromNameArray.length - 1];
                if (toName.equals(fromName)) {
                    try {
                        toField.set(to, fromField.get(from));
                    } catch (IllegalAccessException e) {
                        e.printStackTrace();
                        continue;
                    }
                }
            }
        }
        return to;
    }

    public List<Field> removeDuplicate(List<Field> fields){
        for (int i = 0; i < fields.size(); i++) {
            Field field1 = fields.get(i);
            String field1Name = field1.getName().replace(field1.getType().toString(), "");
            for (int j = i + 1; j < fields.size(); j++) {
                Field field2 = fields.get(j);
                String field2Name = field2.getName().replace(field2.getType().toString(), "");
                if (field1Name.equals(field2Name)) {
                    fields.remove(field2);
                }
            }
        }
        return fields;
    }

    public List<Field> filterNull(List<Field> fields, Object object) {
        return fields.stream().filter(it -> {
            try {
                it.setAccessible(true);
                return it.get(object) != null;
            } catch (IllegalAccessException e) {
                e.printStackTrace();
                return false;
            }
        }).collect(Collectors.toList());
    }

    public List<Field> filterNotNull(List<Field> fields, Object object) {
        return fields.stream().filter(it -> {
            try {
                it.setAccessible(true);
                return it.get(object) == null;
            } catch (IllegalAccessException e) {
                e.printStackTrace();
                return false;
            }
        }).collect(Collectors.toList());
    }
}
