import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;

import static org.junit.Assert.assertEquals;

/**
 * Created by gyk on 6/2/2017.
 */
public class RetrieveTest {
    public static void main(String[] arg){
        ArrayList<Recipe> allRecipes = Parser.parseFile("cakes.txt");
        HashMap<HashSet<String>, HashSet<String>> mapping = Parser.parseIngredients(allRecipes);
        HashSet<String> include = new HashSet<String>(Arrays.asList("milk"));
        HashSet<String> recipes = Search.includes(include, mapping);
        Retrieve.print(recipes, allRecipes);
    }

    @Test
    public void end2endInclusion() throws Exception{
        System.out.print("end2endInclusion...");
        ArrayList<Recipe> allRecipes = Parser.parseFile("cakes.txt");
        HashMap<HashSet<String>, HashSet<String>> mapping = Parser.parseIngredients(allRecipes);
        HashSet<String> include = new HashSet<String>(Arrays.asList("cinnamon"));
        HashSet<String> inclusionRecipes = Search.includes(include, mapping);

        assertEquals(new HashSet<String>(
                Arrays.asList("Pumpkin Cake ", "Carrot Cake ", "Apple Top Coffee Cake ", "Old Fashioned Applesauce Cake ", "Carrot Cake ", "Peach Cake ", "Rhubarb Cake ")), inclusionRecipes);
        assertEquals(6, Retrieve.num(inclusionRecipes, allRecipes));
        System.out.println("passed!\n");
    }

    @Test
    public void end2endExclusion() throws Exception{
        System.out.print("end2endExclusion...");
        ArrayList<Recipe> allRecipes = Parser.parseFile("cakes.txt");
        HashMap<HashSet<String>, HashSet<String>> mapping = Parser.parseIngredients(allRecipes);

        HashSet<String> exclude = new HashSet<String>(Arrays.asList("cinnamon", "eggs"));
        HashSet<String> exclusionRecipes = Search.excludes(exclude, mapping);
        assertEquals(new HashSet<String>(
                Arrays.asList("Banana Walnut Cake ", "Dirt Cake ", "Rhubarb Upsidedown Cake ", "Lemonade Cake ", "Crazy Cake ", "Mayonnaise Chocolate Cake ", "Snickers Cake ")), exclusionRecipes);
        assertEquals(7, Retrieve.num(exclusionRecipes, allRecipes));
        System.out.println("passed!\n");
    }

    @Test
    public void end2endExact() throws Exception{
        System.out.print("end2endExact...");
        ArrayList<Recipe> allRecipes = Parser.parseFile("cakes.txt");
        HashMap<HashSet<String>, HashSet<String>> mapping = Parser.parseIngredients(allRecipes);

        HashSet<String> exact = new HashSet<String>(Arrays.asList("eggs", "graham crackers", "cream cheese", "blueberries", "sugar", "margarine", "vanilla"));
        HashSet<String> exactRecipes = Search.exact(exact, mapping);
        assertEquals(new HashSet<String>(
                Arrays.asList("Blueberry Cheesecake ")), exactRecipes);
        assertEquals(1, Retrieve.num(exactRecipes, allRecipes));
        System.out.println("passed!\n");
    }
}
