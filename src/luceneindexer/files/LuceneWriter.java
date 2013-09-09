/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package luceneindexer.files;

/**
 *
 * @author spousty
 */
public class LuceneWriter {
    
    String pathToIndex = "";

    private LuceneWriter() {
    }

    public LuceneWriter(String pathToIndex) {
        this.pathToIndex = pathToIndex;
    }
    
    public boolean openIndex(){
        
        boolean worked = true;
        try {
            
            //Todo download the lucene jar files
            /*
            Directory dir = FSDirectory.open(new File(pathToIndex));
            Analyzer analyzer = new StandardAnalyzer(Version.LUCENE_40);
            IndexWriterConfig iwc = new IndexWriterConfig(Version.LUCENE_40, analyzer);
            */
        } catch (Exception e) {
        }
        
        return worked;
    }
    
    
    
    
    
}
