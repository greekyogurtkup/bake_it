import org.junit.Test;

import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;

import static org.junit.Assert.assertEquals;

/**
 * Created by gyk on 5/30/2017.
 */
public class SearchTest {
    public static void main(String[] arg) throws Exception {
        //Create mapping
        HashMap<HashSet<String>, HashSet<String>> mapping = new HashMap<HashSet<String>, HashSet<String>>();

        HashSet<String> vegetables = new HashSet<String>(Arrays.asList("tomato", "broccoli", "carrot", "lettuce", "onion", "spinach"));
        HashSet<String> fruits = new HashSet<String>(Arrays.asList("strawberry", "apple", "orange", "banana", "tomato"));
        HashSet<String> seasons = new HashSet<String>(Arrays.asList("spring", "summer", "fall", "winter"));
        mapping.put(vegetables, new HashSet<String>(Arrays.asList("vegetables", "produce")));
        mapping.put(fruits, new HashSet<String>(Arrays.asList("fruits", "produce")));
        mapping.put(seasons, new HashSet<String>(Arrays.asList("seasons")));

        //Invoke tests
        System.out.println("-----BEGIN SEARCH TESTS-----");
        testExact(mapping);
        testInclude(mapping);
        testExclude(mapping);

        System.out.println("-----END SEARCH TESTS-----");
    }

    @Test
    public static void testExact(HashMap<HashSet<String>, HashSet<String>> mapping) throws Exception{
        HashSet<String> empty = new HashSet<String>();
        assertEquals(empty, Search.exact(empty, mapping));

        HashSet<String> myGarden = new HashSet<String>(Arrays.asList("carrot"));
        assertEquals(empty, Search.exact(myGarden, mapping));
        HashSet<String> theStore = new HashSet<String>(Arrays.asList("tomato", "broccoli", "carrot", "lettuce", "onion", "spinach", "kale"));
        assertEquals(empty, Search.exact(theStore, mapping));
        HashSet<String> myFridge = new HashSet<String>(Arrays.asList("tomato", "broccoli", "carrot", "lettuce", "onion", "orange"));
        assertEquals(empty, Search.exact(myFridge, mapping));

        HashSet<String> fruits = new HashSet<String>(Arrays.asList("strawberry", "apple", "orange", "banana", "tomato"));
        assertEquals(new HashSet<String>(Arrays.asList("produce", "fruits")), Search.exact(fruits, mapping));
        HashSet<String> seasons = new HashSet<String>(Arrays.asList("spring", "summer", "fall", "winter"));
        assertEquals(new HashSet<String>(Arrays.asList("seasons")), Search.exact(seasons, mapping));

        System.out.println("All exact tests passed");
    }

    @Test
    public static void testInclude(HashMap<HashSet<String>, HashSet<String>> mapping) throws Exception{
        HashSet<String> empty = new HashSet<String>();
        HashSet<String> all = new HashSet<String>(Arrays.asList("seasons", "vegetables", "fruits", "produce"));
        assertEquals(all, Search.includes(empty, mapping));

        HashSet<String> theStore = new HashSet<String>(Arrays.asList("tomato", "broccoli", "carrot", "lettuce", "onion", "spinach", "kale"));
        assertEquals(empty, Search.includes(theStore, mapping));
        HashSet<String> myFridge = new HashSet<String>(Arrays.asList("tomato", "broccoli", "carrot", "lettuce", "onion", "orange"));
        assertEquals(empty, Search.includes(myFridge, mapping));

        HashSet<String> tomato = new HashSet<String>(Arrays.asList("tomato"));
        assertEquals(new HashSet<String>(Arrays.asList("produce", "fruits", "vegetables")), Search.includes(tomato, mapping));
        HashSet<String> seasons = new HashSet<String>(Arrays.asList("fall", "winter"));
        assertEquals(new HashSet<String>(Arrays.asList("seasons")), Search.includes(seasons, mapping));

        System.out.println("All include tests passed");
    }

    @Test
    public static void testExclude (HashMap<HashSet<String>, HashSet<String>> mapping) throws Exception{
        HashSet<String> empty = new HashSet<String>();
        HashSet<String> all = new HashSet<String>(Arrays.asList("seasons", "vegetables", "fruits", "produce"));
        assertEquals(all, Search.excludes(empty, mapping));

        HashSet<String> eachOfEverything = new HashSet<String>(Arrays.asList("broccoli", "spring", "apple"));
        assertEquals(empty, Search.excludes(eachOfEverything, mapping));

        HashSet<String> tomato = new HashSet<String>(Arrays.asList("tomato"));
        assertEquals(new HashSet<String>(Arrays.asList("seasons")), Search.excludes(tomato, mapping));
        HashSet<String> kale = new HashSet<String>(Arrays.asList("kale"));
        assertEquals(all, Search.excludes(kale, mapping));

        System.out.println("All exclude tests passed");
    }

}
