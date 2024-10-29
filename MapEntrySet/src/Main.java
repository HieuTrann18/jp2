import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.atomic.AtomicReference;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    public static void main(String[] args) {

        Map<String, String> categories = new HashMap<String, String>();
        categories.put("AP001", "Apple");
        categories.put("SS002", "Samsung");
//        categories.put(null, null);

        Map.Entry<String, String> category = categories.entrySet().stream()
                .filter(c -> c.getKey().equals("AP001"))
                .findFirst()
                .orElse(null);

        AtomicReference<String> categoryName = new AtomicReference<>();
        categories.entrySet()
                        .stream()
                        .filter(c -> c.getKey().equals("SS002"))
                        .findFirst()
                        .ifPresent(c -> {
                            categoryName.set(c.getValue());
                        });

        System.out.println(categoryName.get());
//        System.out.println(category);
//        System.out.println(categoryName);
    }
}