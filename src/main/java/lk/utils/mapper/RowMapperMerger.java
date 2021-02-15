package lk.utils.mapper;

import lk.utils.mapper.annotation.RowMapper;

import java.lang.reflect.Field;
import java.util.List;

class RowMapperMerger<T> {

    public T fields(T to, Object from, List<Field> toFields, List<Field> fromFields) {
        for (Field fromField : fromFields) {
            for (Field toField : toFields) {
                String toName = toField.getAnnotation(RowMapper.class).name();
                String fromName = fromField.getAnnotation(RowMapper.class).name();
                if (toName.equals(fromName) && (toField.getType() == fromField.getType())) {
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

}
