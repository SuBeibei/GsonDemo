import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class CollectionExample {
    public static void main(String[] args) {
        Gson gson = new Gson();

        // Serialization
        List<Integer> list = new ArrayList<>();
        list.add(1);
        list.add(2);
        list.add(3);
        System.out.println(gson.toJson(list));

        Map<String, Integer> map = new HashMap<>();
        map.put("a", 1);
        map.put("b", 2);
        map.put("c", 3);
        System.out.println(gson.toJson(map));


        // Deserialization
        String listStr = "[1,2,3]";

        System.out.println(gson.fromJson(listStr, List.class));

        Type listType = new TypeToken<List<Integer>>(){}.getType();
        list = gson.fromJson(listStr, listType);
        System.out.println(list);

        String mapStr = "{\"a\":1,\"b\":2,\"c\":3}";

        System.out.println(gson.fromJson(mapStr, Map.class));

        Type mapType = new TypeToken<Map<String, Integer>>(){}.getType();
        map = gson.fromJson(mapStr, mapType);
        System.out.println(map);
    }
}
