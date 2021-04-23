package codechefscraper;

import java.io.IOException;
import java.util.List;
import org.jsoup.*;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;

/**
 *
 * @author RUSHABH
 */
public class CodechefScraper {
    
    public static void main(String[] args) throws Exception{
        
        CodechefDataManager user = new CodechefDataManager();
        // enter here your codechef id.
        user.setUserId("rushabh309");
        
        List<String> partiallySolved = user.getPartiallySolvedProblems();
        List<String> fullySolved = user.getFullySolvedProblems();
        
        System.out.println("Username: " + user.getUsername());
        
        System.out.println("Current Rating: " + user.getCurrentRating());
        
        System.out.println("Highest Rating: " + user.getHighestRating());
        
        System.out.println("Partially Solved: " + user.partialSolvedCount());
        
        System.out.println("Fully Solved: " + user.fullySolvedCount());
        
        System.out.println("Global Rank: " + user.getGlobalRank());
        
        System.out.println("Country Rank: " + user.getCountryRank());
        
        System.out.print("Partially Solved Problems: ");
        for(String temp : partiallySolved){
            System.out.print(temp + ", ");
        }
        System.out.println();
        
        System.out.print("Fully Solved Problems: ");
        for(String temp : fullySolved){
            System.out.print(temp + " ");
        }
        System.out.println();
    }
    
}
