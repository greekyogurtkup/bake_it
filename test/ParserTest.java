import java.util.HashMap;
import java.util.HashSet;

/**
 * Created by gyk on 5/23/2017.
 */
public class ParserTest {
    public static void main(String[] arg){
//        ArrayList<Recipe> recipeList = Parser.parseFile("cakes.txt");
//
//        System.out.println("================================");
//
//        for(Recipe item:recipeList){
//            System.out.println(item.getName());
//            HashSet<String> ingredients = Parser.parseIngredients(item);
//            item.printIngredients(true);
//        }

        HashMap<HashSet<String>, HashSet<String>> mapping = Parser.parse("cakes.txt");
        for(HashSet<String> ingredient:mapping.keySet()){
            System.out.println(ingredient + " : " + mapping.get(ingredient));
        }
    }

}
