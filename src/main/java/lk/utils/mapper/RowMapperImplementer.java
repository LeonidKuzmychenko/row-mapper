package lk.utils.mapper;

import lk.utils.mapper.annotation.RowMapper;

import java.lang.reflect.Field;
import java.util.List;
import java.util.stream.Collectors;

class RowMapperImplementer{

    public List<Field> removeDuplicate(List<Field> fields) {
        for (int i = 0; i < fields.size(); i++) {
            String field1Name = fields.get(i).getAnnotation(RowMapper.class).name();
            Class<?> field1Type = fields.get(i).getType();
            for (int j = i + 1; j < fields.size(); j++) {
                Field field2 = fields.get(j);
                Class<?> field2Type = field2.getType();
                String field2Name = field2.getAnnotation(RowMapper.class).name();
                if (field1Name.equals(field2Name) && field1Type == field2Type) {
                    fields.remove(field2);
                }
            }
        }
        return fields;
    }

    public List<Field> removeWithoutAnnotation(List<Field> fields) {
        return fields.stream().filter(this::withoutAnnotationFilter).collect(Collectors.toList());
    }

    public List<Field> removeNull(List<Field> fields, Object object, boolean revers) {
        return fields.stream().filter(it -> nullFilter(it, object, revers)).collect(Collectors.toList());
    }

    private boolean withoutAnnotationFilter(Field field) {
        RowMapper rowMapper = field.getAnnotation(RowMapper.class);
        return rowMapper != null && !rowMapper.name().trim().isEmpty();
    }

    private boolean nullFilter(Field fields, Object object, boolean revers) {
        try {
            fields.setAccessible(true);
            return (fields.get(object) == null) != revers;
        } catch (IllegalAccessException e) {
            e.printStackTrace();
            return false;
        }
    }

}
