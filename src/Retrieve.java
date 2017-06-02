import java.util.ArrayList;
import java.util.HashSet;

/**
 * Created by gyk on 6/1/2017.
 */
public class Retrieve {
    /**
     * Retrieves and prints all relevant recipes as a single block of humann-readable text
     * @param relevantRecipeNames names of recipes that matched search criteria
     * @param allRecipes store of recipes
     */
    public static void print(HashSet<String> relevantRecipeNames, ArrayList<Recipe> allRecipes){
        //TODO: store recipes in hash instead
        for(Recipe recipe:allRecipes){
            if(relevantRecipeNames.contains(recipe.getName())){
                recipe.print(false);
            }
        }
    }

    public static String asString(HashSet<String> relevantRecipeNames, ArrayList<Recipe> allRecipes){
        //TODO: store recipes in hash instead
        StringBuilder recipes = new StringBuilder();
        for(Recipe recipe:allRecipes){
            if(relevantRecipeNames.contains(recipe.getName())){
                recipe.print(false);
                recipes.append(recipe.all());
            }
        }
        return recipes.toString();
    }

    public static int num(HashSet<String> relevantRecipeNames, ArrayList<Recipe> allRecipes){
        return relevantRecipeNames.size();
    }
}
