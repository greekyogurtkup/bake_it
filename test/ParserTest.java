import java.util.ArrayList;

/**
 * Created by gyk on 5/23/2017.
 */
public class ParserTest {
    public static void main(String[] arg){
        ArrayList<Recipe> recipeList = Parser.parseFile("cakes.txt");

        System.out.println("================================");

        for(Recipe item:recipeList){
            System.out.println(item.getName());
            ArrayList<String> ingredients = Parser.parseIngredients(item);
            item.printIngredients(true);
        }
    }

}
