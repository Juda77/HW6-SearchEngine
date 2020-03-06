import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

public class MyMiniSearchEngine {
	
    // default solution. OK to change.
    // do not change the signature of index()
	
	//map of strings/ words with their documents and locations as the values
	//the inner map will be a map of integers(document ids) with values of arraylists which contain the word's locations within that doc
    private Map<String, Map<Integer, List<Integer>>> indexes = new HashMap<>();


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
    	for (int documentID = 0; documentID < texts.size(); documentID++) {
    		
    		//get the current document
    		String currentDocument = texts.get(documentID).toLowerCase();
    		
    		Scanner scanCurrentDocument = new Scanner(currentDocument);
    		
    		int index = 0; //index to keep track of the location/index of the current word(i will be the document number)
    		
    		//parse through the document's words and add them to the map
    		while (scanCurrentDocument.hasNext()) {
    			
    			String word = scanCurrentDocument.next();
    			
    			//if the word isn't already in the map, add it
    			if (!indexes.containsKey(word)) {
    				
    				Map<Integer, List<Integer>> docIdAndLocations = new HashMap<>();
    				List<Integer> locations = new ArrayList<>();
    				locations.add(index);
    				
    				docIdAndLocations.put(documentID, locations);
    				indexes.put(word, docIdAndLocations);
    				
    				
    			} else { //the word is already in the map, so just add the location to the correct document(the correct array index)
    				
    				//The word may already be in the map, but make sure to only add
    				//a new document id to our inner map if that document id isn't already added
    				//so that we don't have duplicate doc ids in our map
    				if (indexes.get(word).get(documentID) == null) {
    					List<Integer> locations = new ArrayList<>();
        				locations.add(index);
    					indexes.get(word).put(documentID, locations);

    					
    				} else if (indexes.get(word).get(documentID) != null) {
    					//if the docID is already present, just add the new location
    					indexes.get(word).get(documentID).add(index);
    					
    				
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
    	/*
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
    	
    	
    	*/
    	
    	
    	
    	
    	

    	Map locationsAsMap = indexes.get(keyPhrase);
    	List<Integer> locations = new ArrayList<Integer>(locationsAsMap.keySet());//final solution
    	
    	
    	
        return locations; // place holder
    }
}
