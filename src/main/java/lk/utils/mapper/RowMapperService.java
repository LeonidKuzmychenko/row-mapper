package lk.utils.mapper;

import org.apache.commons.lang3.reflect.FieldUtils;

import java.lang.reflect.Field;
import java.util.List;

public class RowMapperService<T> {

    private final RowMapperImplementer implementer = new RowMapperImplementer();
    private final RowMapperMerger<T> merger = new RowMapperMerger<>();

    public T join(T to, Object from) {
        //беру все поля
        List<Field> toFields = FieldUtils.getAllFieldsList(to.getClass());
        List<Field> fromFields = FieldUtils.getAllFieldsList(from.getClass());

        //удаляю те поля, которые не помечены аннотацией
        toFields = implementer.removeWithoutAnnotation(toFields);
        fromFields = implementer.removeWithoutAnnotation(fromFields);

        //удаляю те поля, которые дублируются
        toFields = implementer.removeDuplicate(toFields);
        fromFields = implementer.removeDuplicate(fromFields);

        //удаляю те поля, которые не равны null
        toFields = implementer.removeNull(toFields, to, false);
        //удаляю те поля, которые равны null
        fromFields = implementer.removeNull(fromFields, from, true);

        //объеденяю два списка
        return merger.fields(to, from, toFields, fromFields);
    }

}