package life.genny.qwanda.utils;

import java.util.List;

import org.jboss.logging.Logger;

import life.genny.qwanda.utils.callbacks.FIGetStringCallBack;

/**
 * A few Common Utils to use throughout Genny.
 *
 * @author Bryn
 * @author Jasper
 */
public class CommonUtils {

    private static final Logger log = Logger.getLogger(CommonUtils.class);


    /**
     * Fetch a System Environment Variable (Effectively {@link System#getenv()} but with logging). Will log if the environment variable is missing
     * @param env - Environment variable to fetch
     * @return - value of env or null if it is missing
     */
    public static String getSystemEnv(String env) {
        return getSystemEnv(env, true);
    }

    /**
     * Fetch a System Environment Variable (Effectively {@link System#getenv()} but with logging)
     * @param env - Environment variable to fetch
     * @param alert whether or not to log if the environment variable is missing (default true)
     * @return - value of env or null if it is missing
     */
    public static String getSystemEnv(String env, boolean alert) {
        String result = System.getenv(env);
        if(result == null && alert) {
            log.error("Cannot get environment variable " + env + ". Is it set?");
        }

        return result;
    }

    /**
     * String Array Builder to get Stringified arrays of custom components
     */
    public static class StringArrayBuilder<T> {

        /**
         * Get a JSON style array of objects. Pass a callback for custom values. Will default to {@link Object#toString()} otherwise
         * @param list - list to get array of
         * @param stringCallback - callback to use to retrieve a string value of the object
         * @return a JSON style array of objects, where each item is the value returned from stringCallback
         */
        public String getArrayString(List<T> list, FIGetStringCallBack<T> stringCallback) {
            String result = "";
            for(T object : list) {
                result += "\"" + stringCallback.getString(object) + "\",";
            }
            return "[" + result.substring(0, result.length() - 1) + "]";
        }

        /**
         * Get a JSON style array of objects. Pass a callback for custom values. Will default to {@link Object#toString()} otherwise
         * @param list - list to get array of
         * @param stringCallback - callback to use to retrieve a string value of the object
         * @return a JSON style array of objects, where each item is the value returned from stringCallback
         */
        public String getArrayString(T[] array, FIGetStringCallBack<T> stringCallback) {
            String result = "";
            for(T object : array) {
                result += "\"" + stringCallback.getString(object) + "\",";
            }
            return "[" + result.substring(0, result.length() - 1) + "]";
        }
    }
}
