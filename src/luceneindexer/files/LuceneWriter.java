/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package luceneindexer.files;

import com.sun.org.apache.xalan.internal.xsltc.compiler.util.Type;
import java.io.File;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import luceneindexer.data.Park;
import org.apache.lucene.analysis.Analyzer;
import org.apache.lucene.analysis.standard.StandardAnalyzer;
import org.apache.lucene.document.Document;
import org.apache.lucene.document.Field;
import org.apache.lucene.document.StoredField;
import org.apache.lucene.document.TextField;
import org.apache.lucene.index.IndexWriter;
import org.apache.lucene.index.IndexWriterConfig;
import org.apache.lucene.index.IndexWriterConfig.OpenMode;
import org.apache.lucene.store.Directory;
import org.apache.lucene.store.FSDirectory;
import org.apache.lucene.util.Version;

/**
 *
 * @author spousty
 */
public class LuceneWriter {
    
    String pathToIndex = "";
    IndexWriter indexWriter = null;

    private LuceneWriter() {
    }

    public LuceneWriter(String pathToIndex) {
        this.pathToIndex = pathToIndex;
    }
    
    public boolean openIndex(){
        
        
        try {
            
            //Open the directory so lucene knows how to deal with it
            Directory dir = FSDirectory.open(new File(pathToIndex));
            
            //Chose the analyzer we are going to use to write documents to the index
            Analyzer analyzer = new StandardAnalyzer(Version.LUCENE_44);
            
            //Create an index writer configuraiton
            IndexWriterConfig iwc = new IndexWriterConfig(Version.LUCENE_44, analyzer);
            
            //we are always going to overwrite the index that is currently in the directory
            iwc.setOpenMode(OpenMode.CREATE);
            
            //let's open that index and get a writer to hand back to the main code
            indexWriter = new IndexWriter(dir, iwc);
            
            return true;
        } catch (Exception e) {
            System.out.println("Threw an exception trying to open the index for writing: " + e.getClass() + " :: " + e.getMessage());
            return false;
        }
                
    }
    
    public void addPark(Park park){
        Document doc = new Document();
        doc.add(new TextField("name", park.getname(), Field.Store.YES));
        doc.add(new StoredField("coordinates", park.getPos().toString()));
        try {
            indexWriter.addDocument(doc);
        } catch (IOException ex) {
            System.out.println("Threw an exception trying to add the doc: " + ex.getClass() + " :: " + ex.getMessage());
        }
        System.out.println( park.getname() );
        
    }
    
    
    public void finish(){
        System.out.println("about to close the writer");
        try {
            indexWriter.commit();
            indexWriter.close();
        } catch (IOException ex) {
            System.out.println("We had a problem closing the index: " + ex.getClass() + " :: " + ex.getLocalizedMessage());
        }
    }
    
    
    
    
    
}
