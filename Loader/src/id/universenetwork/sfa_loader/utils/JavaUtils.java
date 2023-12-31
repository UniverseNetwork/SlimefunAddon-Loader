package id.universenetwork.sfa_loader.utils;

import lombok.experimental.UtilityClass;

import java.lang.reflect.Field;
import java.util.logging.Level;

@UtilityClass
public class JavaUtils {
    /**
     * Returns if class is deprecated or not
     *
     * @param clazz Class to check
     * @return true if the class is deprecated, false otherwise
     * @author ARVIN3108 ID
     */
    public boolean isClassDeprecated(Class<?> clazz) {
        return clazz.getAnnotation(Deprecated.class) != null;
    }

    /**
     * Returns if class is available or not
     *
     * @param path Class to look for
     * @return true if the class is available, false otherwise
     * @author ARVIN3108 ID
     */
    public boolean isClassAvailable(String path) {
        try {
            Class.forName(path);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    /**
     * Return the corresponding major Java version such as 8 for Java 1.8, or 11 for Java 11.
     *
     * @return Java Version
     * @author kangarko
     */
    public int getJavaVersion() {
        String version = System.getProperty("java.version");

        if (version.startsWith("1.")) version = version.substring(2, 3);

        else {
            final int dot = version.indexOf(".");

            if (dot != -1) version = version.substring(0, dot);
        }

        if (version.contains("-")) version = version.split("-")[0];

        return Integer.parseInt(version);
    }

    /**
     * Get the value of a field in a class that isn't declared public
     *
     * @param name     Field name
     * @param instance Instance of the class
     * @return The value of the field
     * @author ARVIN3108 ID
     */
    public Object getField(Object instance, String name) {
        try {
            Field field = instance.getClass().getDeclaredField(name);
            field.setAccessible(true);
            return field.get(instance);
        } catch (Exception e) {
            LogUtils.log(Level.SEVERE, "An error occurred while obtaining the value of the field &e" + name, e);
        }
        return null;
    }
}