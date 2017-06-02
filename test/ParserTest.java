import org.junit.Test;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;

/**
 * Created by gyk on 5/23/2017.
 */
public class ParserTest {
    public static void main(String[] arg){
        HashMap<HashSet<String>, HashSet<String>> mapping = Parser.parse("cakes.txt");
        for(HashSet<String> ingredient:mapping.keySet()){
            System.out.println(ingredient + " : " + mapping.get(ingredient));
        }
    }

    @Test
    public void testParser(){
        ArrayList<Recipe> recipeList = Parser.parseFile("cakes.txt");
        assertEquals(30, recipeList.size());
        for(Recipe item:recipeList){
            assertFalse(item.getName().equals("unknown"));
            assertFalse(item.getIngredients().isEmpty());
            assertFalse(item.getInstructions().isEmpty());
        }

        HashMap<HashSet<String>, HashSet<String>> mapping = Parser.parseIngredients(recipeList);
        assertEquals(30, mapping.size());
    }

}
