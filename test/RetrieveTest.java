import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;

/**
 * Created by gyk on 6/2/2017.
 */
public class RetrieveTest {
    public static void main(String[] arg){
        ArrayList<Recipe> allRecipes = Parser.parseFile("cakes.txt");
        HashMap<HashSet<String>, HashSet<String>> mapping = Parser.parseIngredients(allRecipes);
        HashSet<String> include = new HashSet<String>(Arrays.asList("eggs", "milk"));
        HashSet<String> recipes = Search.includes(include, mapping);
//        Retrieve.print(recipes, allRecipes);
        String result = Retrieve.asString(recipes, allRecipes);
        System.out.println(result);
    }
}
