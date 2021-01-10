package dbUtils;

public class SQLGeneric {

    //TODO: finish maybe...? not sure if abstraction is worth the time
    public static void insertGeneric(String tableName, Integer id, String[] columnNames, String[] columnValues) {
        String names = null;
        String values = null;
        String id_String = "";

        if (id != null) {
            id_String = "" + id + ", ";
        }

        //TODO: include id (id columns have different names...)

        if (columnNames != null && columnValues != null && columnNames.length == columnValues.length) {
            names = " (" + combinedString(columnNames) + ") ";
            values = " (" + combinedString(columnValues) + ") ";
        }
    }

    // returns [ " arr_1[0]=arr_2[0] ", " arr_1[1]=arr_2[1] ", ...]
    private static String[] combinedStringArray(String[] arr_1, String[] arr_2) {
        String[] combinedArray = null;
        if (arr_1 != null && arr_2 != null && arr_1.length == arr_2.length) {
            combinedArray = new String[arr_1.length];
            for (int i = 0; i < combinedArray.length; i++) {
                combinedArray[i] = " " + arr_1[i] + "=" + arr_2[i] + " ";
            }
        }
        return combinedArray;
    }

    // returns " strings[0], strings[1], ..., strings[n] "
    private static String combinedString(String[] strings) {
        String str = " ";
        for (int i = 0; i < strings.length - 1; i++) {
            str += strings[i] + ", ";
        }
        str += strings[strings.length - 1] + " ";

        return str;
    }
}
