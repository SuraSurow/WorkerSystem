package Service;

import java.lang.reflect.InvocationTargetException;

public class UniversalSetterField {
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
            System.out.println("Wpisano Niepoprawny znak !!! Sproboj Ponownie");
        } catch (InvocationTargetException e) {
            System.out.println("Wpisano Niepoprawny znak !!! Sproboj Ponownie");
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

