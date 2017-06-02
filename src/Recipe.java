import java.util.ArrayList;
import java.util.HashSet;

/**
 * Created by gyk on 5/23/2017.
 */
public class Recipe {
    String name;
    ArrayList<String> ingredients;
    ArrayList<String> instructions;
    HashSet<String> parsedIngredients;

    public Recipe(String name){
        this.name = name;
        this.ingredients = new ArrayList<String>();
        this.instructions = new ArrayList<String>();
    }

    public Recipe(){
        this.name = "unknown";
        this.ingredients = new ArrayList<String>();
        this.instructions = new ArrayList<String>();
    }

    public String getName(){
        return this.name;
    }

    public ArrayList<String> getIngredients(){
        return this.ingredients;
    }

    public ArrayList<String> getInstructions(){
        return this.instructions;
    }

    public void addIngredient(String item){
        ingredients.add(item);
    }

    public void addInstruction(String item){
        instructions.add(item);
    }

    public void setParsedIngredients(HashSet<String> parsedIngredients){
        this.parsedIngredients = parsedIngredients;
    }

    public String all(){
        StringBuilder all = new StringBuilder();
        all.append("--------------\n");
        all.append("Recipe name: ");
        all.append(this.name);
        all.append("\n");

        all.append("Ingredients:\n");
        for(String item:ingredients){
            all.append("    ");
            all.append(item);
            all.append("\n");
        }

        all.append("Instructions:\n");
        for(String item:instructions){
            all.append("    ");
            all.append(item);
            all.append("\n");
        }

        return all.toString();
    }

    public void print(Boolean parsed){
        System.out.println("Recipe name: " + this.name);
        System.out.println("Ingredients:");
        printIngredients(parsed);
        System.out.println("Instructions:");
        printInstructions();
        System.out.println("---------");
    }

    public void printIngredients(Boolean parsed){
        if(parsed){
            for(String item:parsedIngredients){
                System.out.println("    " + item);
            }
        } else{
            for(String item:ingredients){
                System.out.println("    " + item);
            }
        }
    }

    public void printInstructions(){
        for (String item : instructions) {
            System.out.println("    " + item);
        }
    }
}
