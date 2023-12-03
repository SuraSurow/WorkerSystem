package SERVICE;

import java.lang.reflect.InvocationTargetException;

public class universalSetterField {
    public static <T> boolean setField(T object, String fieldName, String value) {
        if (value.isEmpty())
        {
            return  false;
        }
        try {
            Class<?> clazz = object.getClass();
            String methodName = "set" + capitalize(fieldName); // np. setAge

            java.lang.reflect.Method method = clazz.getMethod(methodName, String.class);
            method.invoke(object, value);
            return true;
        } catch (NoSuchMethodException | IllegalAccessException | NumberFormatException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        }
        return false;
    }

    private static String capitalize(String s) {
        if (s == null || s.isEmpty()) {
            return s;
        }
        return Character.toUpperCase(s.charAt(0)) + s.substring(1);
    }
}
