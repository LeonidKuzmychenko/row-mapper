package row;

import org.apache.commons.lang3.reflect.FieldUtils;

import java.lang.reflect.Field;
import java.util.List;
import java.util.stream.Collectors;

public class RowMapperService<T> {

    public T join(T to, Object from) {
        //беру все поля
        List<Field> toFields = FieldUtils.getAllFieldsList(to.getClass());
        List<Field> fromFields = FieldUtils.getAllFieldsList(from.getClass());

        //удаляю те поля, которые не помечены аннотацией
        toFields = removeWithoutAnnotation(toFields);
        fromFields = removeWithoutAnnotation(fromFields);

        //удаляю те поля, которые дублируются
        toFields = removeDuplicate(toFields);
        fromFields = removeDuplicate(fromFields);

        //удаляю те поля, которые не равны null
        toFields = removeNull(toFields, to,false);
        //удаляю те поля, которые равны null
        fromFields = removeNull(fromFields, from,true);

        return mergeFields(to, from, toFields, fromFields);
    }

    public List<Field> removeDuplicate(List<Field> fields) {
        for (int i = 0; i < fields.size(); i++) {
            Field field1 = fields.get(i);
            String field1Name = getAnnotationName(field1);
            for (int j = i + 1; j < fields.size(); j++) {
                Field field2 = fields.get(j);
                String field2Name = getAnnotationName(field2);
                if (field1Name.equals(field2Name)) {
                    fields.remove(field2);
                }
            }
        }
        return fields;
    }

    public List<Field> removeWithoutAnnotation(List<Field> fields) {
        return fields.stream().filter(field -> {
            RowMapper rowMapper = field.getAnnotation(RowMapper.class);
            return rowMapper != null && !rowMapper.name().trim().isEmpty();
        }).collect(Collectors.toList());
    }

    public List<Field> removeNull(List<Field> fields, Object object, boolean revers) {
        return fields.stream().filter(it -> {
            try {
                it.setAccessible(true);
                return (it.get(object) == null) != revers;
            } catch (IllegalAccessException e) {
                e.printStackTrace();
                return false;
            }
        }).collect(Collectors.toList());
    }

    public T mergeFields(T to, Object from, List<Field> toFields, List<Field> fromFields) {
        for (Field fromField : fromFields) {
            for (Field toField : toFields) {
                String toName = getAnnotationName(toField);
                String fromName = getAnnotationName(fromField);
                if (toName.equals(fromName)) {
                    try {
                        toField.set(to, fromField.get(from));
                    } catch (IllegalAccessException e) {
                        e.printStackTrace();
                    }
                }
            }
        }
        return to;
    }

    public String getAnnotationName(Field field) {
        return field.getAnnotation(RowMapper.class).name();
    }
}
