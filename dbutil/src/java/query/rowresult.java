package query;

import com.sun.org.apache.regexp.internal.RE;
import sun.security.jca.GetInstance;
import dbutil.converutil;
import zhujie.column;

import java.lang.reflect.Field;
import java.sql.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by csx on 2017/12/7.
 */
public class rowresult {
    /**
     * 查询一条数据,将结果集转换成一个Bean对象
     */
    public static <T> T toBean(Class<T> clz, ResultSet rs) throws SQLException {
        Object obj = newObject(clz);
        Field[] fields = clz.getDeclaredFields();
        for(int i=0;i<fields.length;i++){
            Object val = setField(fields[i],rs,clz);
            set(fields[i],obj,val);
        }
        return (T)obj;
    }

    /**
     * 查询一条数据,将结果集转换成某一列的值
     */
    public static <T> T toColumn(int columnIndex,Class<T> clz,ResultSet rs) throws SQLException {
        Object val = rs.getObject(columnIndex);
        if(val != null){
            String name = rs.getMetaData().getColumnLabel(columnIndex);
            val = converutil.columnconvert(rs,name,clz);
        }
        return (T)val;
    }
    /**
     * 查询一条数据,将结果集转成map集合
     */
    public static Map<Object,Object> toMap(ResultSet rs) throws SQLException {
        Map<Object,Object> map = new HashMap<Object, Object>();
        map = setMap(map,rs);
        return map;
    }

    /**
     * 查询一条数据,将结果集转换成数组
     */
    public static Object[] toArray(ResultSet rs) throws SQLException {
        ResultSetMetaData rsd = rs.getMetaData();
        Object[] obj = new Object[rsd.getColumnCount()];
        for(int i=0;i<rsd.getColumnCount();i++){
            obj[i] = rs.getObject(i+1);
        }
        return obj;
    }

    /**
     * 给map集合设置值
     */
    private static Map<Object,Object> setMap(Map<Object,Object> map,ResultSet rs) throws SQLException {
        ResultSetMetaData rsd= rs.getMetaData();
        for(int i=0;i<rsd.getColumnCount();i++){
            String name = rsd.getColumnName(i+1);
            map.put(name,rs.getObject(name));
        }
        return map;
    }

    /**
     * 给实体属性设置值
     */
    private static Object setField(Field field,ResultSet rs,Class<?> clz) throws SQLException {
        field.setAccessible(true);
        String columnName = field.isAnnotationPresent(column.class) ?
                field.getAnnotation(column.class).value():field.getName();
        Object val = converutil.columnconvert(rs,columnName,clz);
        return val;
    }

    /**
     * 创建一个实例
     */
    private static <T> Object newObject(Class<T> clz){
        Object obj = null;
        try {
            obj = clz.newInstance();
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
        return obj;
    }


    private static void set(Field field,Object obj,Object val){
        try {
            field.set(obj,val);
        } catch (IllegalAccessException e) {
            throw new RuntimeException("set error",e);
        }
    }
}
