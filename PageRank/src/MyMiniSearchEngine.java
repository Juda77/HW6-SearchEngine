import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

public class MyMiniSearchEngine {
	
    // default solution. OK to change.
    // do not change the signature of index()
	
	//array where each row's index is the document number and the rows themselves will store the indexes at that document which the key word is located
	//private List[][] locationsInDocs;
    private Map<String, List[]> indexes = new HashMap<>();


    // disable default constructor
    private MyMiniSearchEngine() {
    }

    public MyMiniSearchEngine(List<String> documents) {
        index(documents);
    }

    // each item in the List is considered a document.
    // assume documents only contain alphabetical words separated by white spaces.
    private void index(List<String> texts) {
    	//homework
    	
    	//loop through each document(String) in the list
    	for (int i = 0; i < texts.size(); i++) {
    		
    		//get the current document
    		String currentDocument = texts.get(i).toLowerCase();
    		
    		Scanner scanCurrentDocument = new Scanner(currentDocument);
    		
    		int index = 0; //index to keep track of the location/index of the current word(i will be the document number)
    		
    		//parse through the document's words and add them to the map
    		while (scanCurrentDocument.hasNext()) {
    			
    			String word = scanCurrentDocument.next();
    			
    			//if the word isn't already in the map, add it
    			if (!indexes.containsKey(word)) {
    				List[] locationsInDocs = new List[texts.size()]; //give our documents array the same amount of rows as the amount of documents
    				
    				List locations = new ArrayList<Integer>();
    				locations.add(index);
    				locationsInDocs[i] = locations;
    				indexes.put(word, locationsInDocs);
    			} else { //the word is already in the map, so just add the location to the correct document(the correct array index)
    				
    				//indexes.get(word) returns the array of documents/lists
    				List[] locationsInDocs = indexes.get(word); 
    				
    				//edit the location list so that the current index/location is added
    				List<Integer> locations = locationsInDocs[i];
    				
    				//first check if the there was none of this word found at the current document 
    				//if there wasn't then we need to make a new locations list for the current document index
    				if (locations != null) {
    					locations.add(index);
    					locationsInDocs[i] = locations;	
    					//put the edited array of locations back into the map
    					indexes.put(word, locationsInDocs);
    				} else {
    					List locations2 = new ArrayList<Integer>();
        				locations2.add(index);
        				locationsInDocs[i] = locations2;
        				indexes.put(word, locationsInDocs);
    				}
    				
    			}
    			
    			index++;
    		}
   		
    	}

    }

    // search(key) return all the document ids where the given key phrase appears.
    // key phrase can have one or two words in English alphabetic characters.
    // return an empty list if search() finds no match in all documents.
    public List<Integer> search(String keyPhrase) {
        // homework
    	
    	List<List[]> locationsOfEachWord = new ArrayList<>(); //list which contains arrays of each word's locations
    	
    	//first get every array of lists/locations for each word in the keyPhrase
    	Scanner scanPhrase = new Scanner(keyPhrase);
    	
    	String currentWord = scanPhrase.next();
    	
    	//determine the documents where all words in keyPhrase are present in
    	
    	
    	//this map
    	Map<Integer, List[]> keyDocuments = new HashMap<>();
    	
    	
    	
    	while (scanPhrase.hasNext()) {
    		
    		currentWord = scanPhrase.next();
    		
    		locationsOfEachWord.add(indexes.get(currentWord));
    		
    	}
    	
    	
    	
    	
    	
    	
    	
    	List<Integer> locations = new ArrayList<>();//final solution
    	
    	
    	
    	
    	
        return locations; // place holder
    }
}
