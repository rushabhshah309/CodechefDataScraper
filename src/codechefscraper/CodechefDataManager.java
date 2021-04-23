package codechefscraper;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

/**
 *
 * @author RUSHABH
 */
public class CodechefDataManager {
    
    private Document document;
    private String userId;
    
    // this function will set userId provided by user.
    // if this userId doesn't exists, then it will throw an exception.
    public void setUserId(String userId) throws IOException, Exception{
        this.userId = userId;
        
        document = Jsoup.connect("https://www.codechef.com/users/" + this.userId).get();
        if(document.baseUri().equals("https://www.codechef.com/")){
            throw new Exception("Incorrect UserId");
        }
    }
    
    // this function will fetch username.
    public String getUsername(){
        Elements username = document.select("div.user-details-container header h2");        
        return username.html();
    }
    
    // this function will fetch user's current rating.
    public int getCurrentRating(){
        Elements userCurrentRating = document.select("div.rating-number");
        
        return Integer.parseInt(userCurrentRating.html());
    }
    
    // this function will fetch user's Highest rating.
    public int getHighestRating(){
        String highestRating = document.select("div.rating-header small").html();
        return extractInteger(highestRating);
    }        
    
    // this function will fetch total number of partially solved questions.
    public int partialSolvedCount(){
        String partiallySolved = document.select("section.problems-solved h5").get(1).html();
        return extractInteger(partiallySolved);
    }
    
    // this function will fetch total number of fully solved questions.
    public int fullySolvedCount(){
        String fullySolved = document.select("section.problems-solved h5").get(0).html();
        return extractInteger(fullySolved);
    }
    
    // this function will just extract integer from the provided string.
    public int extractInteger(String text){
        String temp = "";
        for(int i=0; i<text.length(); i++){
            if(Character.isDigit(text.charAt(i))){
                temp = temp + text.charAt(i);
            }
        }
        return Integer.parseInt(temp);
    }
    
    // this function will fetch problem id of fully solved problems and will return list of same.
    public List<String> getFullySolvedProblems(){
        Element elements = document.select("section.problems-solved article").get(0);
        
        Elements solvedProblems = elements.select("p").get(0).select("span a");
        
        List<String> result = new ArrayList<>();
        
        for(Element ele : solvedProblems){
            result.add(ele.html());
        }
        return result;
    }
    
    // this function will fetch problem id of partially solved problems and will return list of same.
    public List<String> getPartiallySolvedProblems(){
        Element elements = document.select("section.problems-solved article").get(1);
        
        Elements solvedProblems = elements.select("p").get(0).select("span a");
        
        List<String> result = new ArrayList<>();
        
        for(Element ele : solvedProblems){
            result.add(ele.html());
        }
        return result;
    }
    
    // this function returns global rank.
    public int getGlobalRank(){
        String globalRank = document.select("ul.inline-list").get(1).select("li").get(0).select("a strong").html();
        return Integer.parseInt(globalRank);
    }
    
    // this function returns country rank.
    public int getCountryRank(){
        String countryRank = document.select("ul.inline-list").get(1).select("li").get(1).select("a strong").html();
        return Integer.parseInt(countryRank);
    }
    
}
