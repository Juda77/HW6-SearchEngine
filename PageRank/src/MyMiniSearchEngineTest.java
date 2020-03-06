import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.*;

public class MyMiniSearchEngineTest {
    private List<String> documents() {
        return new ArrayList<String>(
                Arrays.asList(
                        "hello world",
                        "hello",
                        "world",
                        "world world hello",
                        "seattle rains hello abc world",
                        "sunday hello world fun"));
    }

    @Test
    public void testOneWord() {
    	
        MyMiniSearchEngine engine = new MyMiniSearchEngine(documents());
        
        List<Integer> result = engine.search("seattle");
        List<Integer> result2 = engine.search("Hello");
        assertEquals(1, result.size());
        assertEquals(Integer.valueOf(4), result.get(0));
        assertEquals(5, result2.size());
        assertEquals(Integer.valueOf(0), result2.get(0));
        
    }

    
    @Test
    public void testTwoWord() {
        MyMiniSearchEngine engine = new MyMiniSearchEngine(documents());
        List<Integer> result = engine.search("hello world");

        assertEquals(2, result.size());

        assertEquals(List.of(0, 5), result);
    }

    
    @Test
    public void testThreeWord() {
        MyMiniSearchEngine engine = new MyMiniSearchEngine(documents());

        String[] inputs = {
                "rains hello abc",
                "rains Hello abc",
        };

        for (String input : inputs) {
            List<Integer> result = engine.search(input);
            assertEquals(1, result.size());
            assertEquals(List.of(4), result);
        }
    }

    @Test
    public void testFourWord() {
    	// homework
    	MyMiniSearchEngine engine = new MyMiniSearchEngine(documents());
    
        List<Integer> result = engine.search("rains hello abc world");
        List<Integer> result2 = engine.search("sunday hello world fun");
        List<Integer> result3 = engine.search("sunday Sywalker world fun");
        
        assertEquals(1, result.size());
        assertEquals(List.of(4), result);
        assertEquals(1, result2.size());
        assertEquals(List.of(5), result2);
        assertEquals(0, result3.size());
    }

    @Test
    public void testWordNotFound() {
        // homework
    	MyMiniSearchEngine engine = new MyMiniSearchEngine(documents());
    	
    	String[] testPhrases = {"rains seattle", "rains hello abc hello", "skywalker", "Hello skywalker World"};
    	List<List<Integer>> tests = new ArrayList<>();   
    	
    	for (int i = 0; i < 4; i++) {
    		tests.add(engine.search(testPhrases[i]));
    		
    	}

    	for (int i = 0; i < 4; i++) {
    		assertEquals(0, tests.get(i).size());
    	}
        
    }
    

    
}
