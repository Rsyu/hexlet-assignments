package exercise;

import java.util.List;
import java.util.stream.Collectors;

// BEGIN
public class App {
    public static List<String> buildApartmentsList(List<Home> homes, int limit) {
        return homes.stream()
            .sorted()
            .limit(limit)
            .map(Home::toString)
            .collect(Collectors.toList());
    }
}
// END
