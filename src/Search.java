import java.util.HashMap;
import java.util.HashSet;

/**
 * Created by gyk on 5/30/2017.
 */
public class Search {
    public static HashSet<String> exact(HashSet<String> input, HashMap<HashSet<String>, HashSet<String>> mapping){
        if(mapping.containsKey(input)){
            return mapping.get(input);
        } else{
            return new HashSet<String>();
        }
    }

    public static HashSet<String> includes(HashSet<String> toInclude, HashMap<HashSet<String>, HashSet<String>> mapping){
        HashSet<String> relevantRecipes = new HashSet<String>();
        for(HashSet<String> key:mapping.keySet()){
            if(key.containsAll(toInclude)){ //make sure all items in toInclude are present
                relevantRecipes.addAll(mapping.get(key));
            }
        }
        return relevantRecipes;
    }

    public static HashSet<String> excludes(HashSet<String> toExclude, HashMap<HashSet<String>, HashSet<String>> mapping){
        HashSet<String> relevantRecipes = new HashSet<String>();
        HashSet<String> temp = new HashSet<String>();
        HashSet<String> empty = new HashSet<String>();
        for(HashSet<String> key:mapping.keySet()){
            temp.addAll(toExclude);
            temp.retainAll(key);
            if(temp.size() == 0){ //make sure all items in toExclude are NOT present by checking size of itersection
                relevantRecipes.addAll(mapping.get(key));
            } else{
                temp.retainAll(empty); //avoids need to create new empty set in each iteration
            }
        }
        return relevantRecipes;
    }

}
