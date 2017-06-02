import org.junit.Test;

import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;

import static org.junit.Assert.assertEquals;

/**
 * Created by gyk on 5/30/2017.
 */
public class SearchTest {
    @Test
    public void testExact() throws Exception{
        System.out.println("-------- testExact ---------");
        HashMap<HashSet<String>, HashSet<String>> mapping = new HashMap<HashSet<String>, HashSet<String>>();
        HashSet<String> vegetables = new HashSet<String>(Arrays.asList("tomato", "broccoli", "carrot", "lettuce", "onion", "spinach"));
        HashSet<String> fruits = new HashSet<String>(Arrays.asList("strawberry", "apple", "orange", "banana", "tomato"));
        HashSet<String> seasons = new HashSet<String>(Arrays.asList("spring", "summer", "fall", "winter"));
        mapping.put(vegetables, new HashSet<String>(Arrays.asList("vegetables", "produce")));
        mapping.put(fruits, new HashSet<String>(Arrays.asList("fruits", "produce")));
        mapping.put(seasons, new HashSet<String>(Arrays.asList("seasons")));

        System.out.print("empty case...");
        HashSet<String> empty = new HashSet<String>();
        assertEquals(empty, Search.exact(empty, mapping));
        System.out.println("passed!");

        System.out.print("no match cases...");
        HashSet<String> myGarden = new HashSet<String>(Arrays.asList("carrot"));
        assertEquals(empty, Search.exact(myGarden, mapping));
        HashSet<String> theStore = new HashSet<String>(Arrays.asList("tomato", "broccoli", "carrot", "lettuce", "onion", "spinach", "kale"));
        assertEquals(empty, Search.exact(theStore, mapping));
        HashSet<String> myFridge = new HashSet<String>(Arrays.asList("tomato", "broccoli", "carrot", "lettuce", "onion", "orange"));
        assertEquals(empty, Search.exact(myFridge, mapping));
        System.out.println("passed!");

        System.out.print("single match case");
        assertEquals(new HashSet<String>(Arrays.asList("seasons")), Search.exact(seasons, mapping));
        System.out.println("passed!");

        System.out.print("multiple match cases...");
        assertEquals(new HashSet<String>(Arrays.asList("produce", "fruits")), Search.exact(fruits, mapping));
        System.out.println("passed!");

        System.out.println("-----------------\n");
    }

    @Test
    public void testInclude() throws Exception{
        System.out.println("-------- testInclude ---------");
        HashMap<HashSet<String>, HashSet<String>> mapping = new HashMap<HashSet<String>, HashSet<String>>();
        HashSet<String> vegetables = new HashSet<String>(Arrays.asList("tomato", "broccoli", "carrot", "lettuce", "onion", "spinach"));
        HashSet<String> fruits = new HashSet<String>(Arrays.asList("strawberry", "apple", "orange", "banana", "tomato"));
        HashSet<String> seasons = new HashSet<String>(Arrays.asList("spring", "summer", "fall", "winter"));
        mapping.put(vegetables, new HashSet<String>(Arrays.asList("vegetables", "produce")));
        mapping.put(fruits, new HashSet<String>(Arrays.asList("fruits", "produce")));
        mapping.put(seasons, new HashSet<String>(Arrays.asList("seasons")));

        System.out.print("empty case...");
        HashSet<String> empty = new HashSet<String>();
        HashSet<String> all = new HashSet<String>(Arrays.asList("seasons", "vegetables", "fruits", "produce"));
        assertEquals(all, Search.includes(empty, mapping));
        System.out.println("passed!");

        System.out.print("no match cases...");
        HashSet<String> theStore = new HashSet<String>(Arrays.asList("tomato", "broccoli", "carrot", "lettuce", "onion", "spinach", "kale"));
        assertEquals(empty, Search.includes(theStore, mapping));
        HashSet<String> myFridge = new HashSet<String>(Arrays.asList("tomato", "broccoli", "carrot", "lettuce", "onion", "orange"));
        assertEquals(empty, Search.includes(myFridge, mapping));
        System.out.println("passed!");

        System.out.print("single match case...");
        assertEquals(new HashSet<String>(Arrays.asList("seasons")), Search.includes(seasons, mapping));
        System.out.println("passed!");

        System.out.print("multiple match case...");
        HashSet<String> tomato = new HashSet<String>(Arrays.asList("tomato"));
        assertEquals(new HashSet<String>(Arrays.asList("produce", "fruits", "vegetables")), Search.includes(tomato, mapping));
        System.out.println("passed!");

        System.out.println("-----------------\n");
    }

    @Test
    public void testExclude () throws Exception{
        System.out.println("-------- testExclude ---------");
        HashMap<HashSet<String>, HashSet<String>> mapping = new HashMap<HashSet<String>, HashSet<String>>();
        HashSet<String> vegetables = new HashSet<String>(Arrays.asList("tomato", "broccoli", "carrot", "lettuce", "onion", "spinach"));
        HashSet<String> fruits = new HashSet<String>(Arrays.asList("strawberry", "apple", "orange", "banana", "tomato"));
        HashSet<String> seasons = new HashSet<String>(Arrays.asList("spring", "summer", "fall", "winter"));
        mapping.put(vegetables, new HashSet<String>(Arrays.asList("vegetables", "produce")));
        mapping.put(fruits, new HashSet<String>(Arrays.asList("fruits", "produce")));
        mapping.put(seasons, new HashSet<String>(Arrays.asList("seasons")));

        System.out.print("empty case...");
        HashSet<String> empty = new HashSet<String>();
        HashSet<String> all = new HashSet<String>(Arrays.asList("seasons", "vegetables", "fruits", "produce"));
        assertEquals(all, Search.excludes(empty, mapping));
        System.out.println("passed!");

        System.out.print("no match case...");
        HashSet<String> eachOfEverything = new HashSet<String>(Arrays.asList("broccoli", "spring", "apple"));
        assertEquals(empty, Search.excludes(eachOfEverything, mapping));
        System.out.println("passed!");

        System.out.print("single match case...");
        HashSet<String> tomato = new HashSet<String>(Arrays.asList("tomato"));
        assertEquals(new HashSet<String>(Arrays.asList("seasons")), Search.excludes(tomato, mapping));
        System.out.println("passed!");

        System.out.print("multiple match case...");
        HashSet<String> kale = new HashSet<String>(Arrays.asList("kale"));
        assertEquals(all, Search.excludes(kale, mapping));
        System.out.println("passed!");

        System.out.println("-----------------\n");
    }

}
