import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class BagOfObject {
    private int value1 = 1;
    private String value2 = "abc";
    private transient int value3 = 3;
    private boolean value4 = true;
    private float value5 = 1.0f;
    private double value6 = 2.0;
    private int[] value7 = {1, 2, 3};
    private List<Integer> value8 = new ArrayList<>();
    private Map<Integer, String> value9 = new HashMap<>();

    public BagOfObject() {
        value8.add(4);
        value8.add(5);
        value8.add(6);

        value9.put(1, "a");
        value9.put(2, "b");
        value9.put(3, "c");
    }

    @Override
    public String toString() {
        return  "value1: " + value1 + "\n" +
                "value2: " + value2 + "\n" +
                "value3: " + value3 + "\n" +
                "value4: " + value4 + "\n" +
                "value5: " + value5 + "\n" +
                "value6: " + value6 + "\n" +
                "value7: " + value7 + "\n" +
                "value8: " + value8 + "\n" +
                "value9: " + value9 + "\n";
    }

    public static void main(String[] args) {
        final Gson gson = new Gson();

        // Serialization
        final BagOfObject obj = new BagOfObject();
        System.out.println(gson.toJson(obj));

        // Deserialization
        final String str = "{\"value1\":1,\"value2\":\"abc\",\"value3\":3,\"value4\":true,\"value5\":1.0,\"value6\":2.0,\"value7\":[1,2,3],\"value8\":[4,5,6],\"value9\":{\"1\":\"a\",\"2\":\"b\",\"3\":\"c\"}}";
        System.out.println(gson.fromJson(str, BagOfObject.class).toString());
    }
}
