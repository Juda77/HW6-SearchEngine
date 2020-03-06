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
 
    	ArrayList<Integer> documentIDs = null; //list to contain docIDS
    	
    	//final result to be returned(these docIDs will have the correct phrases contained)
    	List<Integer> finalListOfDocumentIDs = new ArrayList<Integer>(); 
    	
    	//parse each word in the keyphrase
    	Scanner phraseParser = new Scanner(keyPhrase);
    	
    	/*with each word in the phrase, take their documentIDs and add them to an arraylist
    	 * as we add, use the retain all method to only keep the document IDs that are shared amound words.
    	 * By the end, the arraylist will only contain document IDS that are shared among every word in the keyphrase.
    	 * 
    	 * After, we have the key docIDS, parse through each documentID. Then, with each docID, parse through the
    	 * keyPhrase again to make sure that the orders are truly correct. If they are correct, then add
    	 * the current docID to the final result list that will be returned

		//map of strings/ words with their documents and locations as the values
		//the inner map will be a map of integers(document ids) with values of arraylists which contain the word's locations within that doc
    	//private Map<String, Map<Integer, List<Integer>>> indexes = new HashMap<>();
    	
    	
    	*/
    	
    	List<String> words = new ArrayList<>(); //also just have another structure containing the words
    	String currentWord = phraseParser.next().toLowerCase();
    	
    	//if the word is not contained in the map, then the whole phrase is not contained in any of the documents
    	if (!indexes.containsKey(currentWord)) {
    		return finalListOfDocumentIDs;
    	}
    	documentIDs = new ArrayList<>(indexes.get(currentWord).keySet()); //grab the word's keys(docIDs and add them to the list)
    	words.add(currentWord);
    	
    	
		
    	while (phraseParser.hasNext()){
    		
    		currentWord = phraseParser.next().toLowerCase();
    		//if the word is not contained in the map, then the whole phrase is not contained in any of the documents
        	if (!indexes.containsKey(currentWord)) {
        		return finalListOfDocumentIDs;
        	}
    		words.add(currentWord);
        	
    		
    		//instead of having to loop through all the docIDs/keys to add them to a list,
    		//just take the keySet and add them to an arraylist of sets(list of one set)

    		documentIDs.retainAll(indexes.get(currentWord).keySet()); //only retain matching docIDs
    		
    	} 
    	
    	/*At this point we now have all the proper documentIDS
    	  parse through each documentID. Then, with each docID, parse through the
    	  keyPhrase again to make sure that the orders are truly correct. If they are correct, then add
    	  the current docID to the final result list that will be returned
    	*/
    	//map of strings/words with their documents and locations as the values
    	//the inner map will be a map of integers(document ids) with values of arraylists which contain the word's locations within that doc
    	//private Map<String, Map<Integer, List<Integer>>> indexes = new HashMap<>();
 
    	//run through each document
    	for (int i = 0; i < documentIDs.size(); i++) {
    		boolean documentIsLegit = true;
	
    		List<Integer> locations = new ArrayList<>(indexes.get(words.get(0)).get(documentIDs.get(i)));
    		//take each word's locations in the doc

    		for (int j = 1; j < words.size(); j++) {

    			//check if the word's (location - j) matches any of the first word's locations 
    			List<Integer> tempLocations = new ArrayList<>(indexes.get(words.get(j)).get(documentIDs.get(i)));
    		
    			int locationsSize = tempLocations.size();
    			
    			//loop through all location indexes to subtract the proper number
    			for (int k = 0; k < locationsSize; k++) {

    				tempLocations.set(k, tempLocations.get(k) - j);
    			
    				if (tempLocations.retainAll(locations) == true && tempLocations.size() == 0) {
    					
    					//if retainsAll was true(all values in tempLocations that didn't match with any value in
    					//locations was removed and all values were removed which means there were no matching values
    					documentIsLegit = false;
    					break;
    					
    				}
    					
    			}
    			    				
    		}
    		
    		if (documentIsLegit) {
    			finalListOfDocumentIDs.add(documentIDs.get(i));
    		}
    		
    	}
    	    		
        return finalListOfDocumentIDs;
    }
}
