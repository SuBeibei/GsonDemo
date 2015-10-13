import com.google.gson.Gson;

public class PrimitiveExample {
    public static void main(String[] args) {
        Gson gson = new Gson();

        // Serialization
        System.out.println(gson.toJson(1));
        System.out.println(gson.toJson("abcd"));
        System.out.println(gson.toJson(new Long(10)));
        int[] values = {1};
        System.out.println(gson.toJson(values));

        //Deserialization
        int one = gson.fromJson("1", int.class);
        System.out.println(one);
        Integer one2 = gson.fromJson("1", Integer.class);
        System.out.println(one2);
        Long one3 = gson.fromJson("1", Long.class);
        System.out.println(one3);
        Boolean aFalse = gson.fromJson("false", Boolean.class);
        System.out.println(aFalse);
        String str = gson.fromJson("\"abc\"", String.class);
        System.out.println(str);
        String[] arr = gson.fromJson("[\"abc\"]", String[].class);
        System.out.println(arr);
    }
}
