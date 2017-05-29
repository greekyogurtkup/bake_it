import java.util.ArrayList;

/**
 * Created by gyk on 5/23/2017.
 */
public class Recipe {
    String name;
    ArrayList<String> ingredients;
    ArrayList<String> instructions;
    ArrayList<String> parsedIngredients;
    ArrayList<String> parsedInstructions;

    public Recipe(String name, ArrayList<String> ingredients, ArrayList<String> instructions){
        this.name = name;
        this.ingredients = ingredients;
        this.instructions = instructions;
    }

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

    public void setParsedIngredients(ArrayList<String> parsedIngredients){
        this.parsedIngredients = parsedIngredients;
    }

    public void setParsedInstructions(ArrayList<String> parsedInstructions){
        this.parsedInstructions = parsedInstructions;
    }

    public void print(Boolean parsed){
        System.out.println("Recipe name: " + this.name);
        System.out.println("Ingredients:");
        printIngredients(parsed);
        System.out.println("Instructions:");
        printInstructions(parsed);
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

    public void printInstructions(Boolean parsed){
        if(parsed){
            for(String item:parsedInstructions){
                System.out.println("    " + item);
            }
        } else {
            for (String item : instructions) {
                System.out.println("    " + item);
            }
        }
    }
}
