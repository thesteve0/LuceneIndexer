/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package luceneindexer;

import com.fasterxml.jackson.databind.ObjectMapper;
import java.io.BufferedReader;
import luceneindexer.data.Park;
import luceneindexer.files.FileOpener;
import luceneindexer.files.LuceneWriter;
import org.apache.lucene.index.IndexWriter;

/**
 *
 * @author spousty
 */
public class Main {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
        System.out.println("Hello world");
        //Open the file of JSON for reading
        FileOpener fOpener = new FileOpener("parks.json");
        
        //Create the object for writing
        LuceneWriter luceneWriter = new LuceneWriter("indexDir");
        
        //This is from Jackson which allows for binding the JSON to the Park.java class
        ObjectMapper objectMapper = new ObjectMapper();
        
        
        try {
            
            //first see if we can open a directory for writing
            if (luceneWriter.openIndex()){
                //get a buffered reader handle to the file
                BufferedReader breader = new BufferedReader(fOpener.getFileForReading());
                String value = null;
                //loop through the file line by line and parse 
                while((value = breader.readLine()) != null){
                    Park park  = objectMapper.readValue(value, Park.class);
                    
                    //now submit each park to the lucene writer to add to the index
                    luceneWriter.addPark(park);
                    
                }
                
            } else {
                System.out.println("We had a problem opening the directory for writing");
            }
             
            
        } catch (Exception e) {
            System.out.println("Threw exception " + e.getClass() + " :: " + e.getMessage());
        } finally {
            //close out the index and release the lock on the file
            luceneWriter.finish();
        }
        
    }
    
}
