import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;

/**
 * Created by gyk on 5/22/2017.
 */
public class Parser {
    public static HashMap<HashSet<String>, HashSet<String>> parse(String filename){
        //Round 1: parse file into recipes
        ArrayList<Recipe> parsedRecipes = parseFile(filename);
        //Round 2: parse ingredients list
        return parseIngredients(parsedRecipes);
    }

    public static ArrayList<Recipe> parseFile(String filename){
        ArrayList<Recipe> allRecipes = new ArrayList<Recipe>();
        BufferedReader reader = null;
        try {
            reader = new BufferedReader(new FileReader(filename));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

        String line = null;
        //for categorizing line inputs
        String type = "unknown";
        boolean dash = false;
        //temp Recipe
        Recipe recipe = new Recipe();

            //Read in file line by line
        try {
            while ((line = reader.readLine()) != null) {

                //TODO: ignore empty lines
                if(line.equals("**************************")){
                    if(!recipe.getName().equals("unknown")){
                        allRecipes.add(recipe); //add recipe before moving on to next one
                    }
//                    recipe.print(false);
                    type = "name";
                } else if(line.equals("-------------------------")){
                    type = (dash) ? "instructions" : "ingredients";
                    dash = !dash;
                } else{
                    if(type.equals("name")){
                        recipe = new Recipe(line);
                    } else if(type.equals("ingredients")){
                        recipe.addIngredient(line);
                    } else if(type.equals("instructions")){
                        recipe.addInstruction(line);
                    } else{
                        //TODO: error handling
                        System.err.println(line);
                    }
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        return allRecipes;
    }

    public static HashMap<HashSet<String>, HashSet<String>> parseIngredients(ArrayList<Recipe> parsedRecipes){
        HashMap<HashSet<String>, HashSet<String>> ingredients2RecipesMapping = new HashMap<>();
        for(Recipe item:parsedRecipes){
            HashSet<String> ingredients = parseIngredientsHelper(item);
            if(ingredients2RecipesMapping.containsKey(ingredients)){
                HashSet<String> recipes = ingredients2RecipesMapping.get(ingredients);
                recipes.add(item.getName());
            } else{
                HashSet<String> recipes = new HashSet<>();
                recipes.add(item.getName());
                ingredients2RecipesMapping.put(ingredients, recipes);
            }
        }

        return ingredients2RecipesMapping;
    }

    public static HashSet<String> parseIngredientsHelper(Recipe recipe){
        ArrayList<String> unparsedIngredients = recipe.getIngredients();
        HashSet<String> parsedIngredients = new HashSet<String>();
        for(String line:unparsedIngredients){
            String[] splitLine = line.split(" ");
            if(splitLine.length < 3) continue;
            //remove first two items which refer to quantity/measurement
            String[] nameOnly = new String[splitLine.length - 2];
            System.arraycopy(splitLine, 2, nameOnly, 0, splitLine.length - 2);
            String finalName = String.join(" ", nameOnly);
            parsedIngredients.add(finalName);
        }
        recipe.setParsedIngredients(parsedIngredients);
        return parsedIngredients;
    }
}
