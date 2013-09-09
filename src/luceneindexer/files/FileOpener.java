/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package luceneindexer.files;

import java.io.InputStreamReader;
import java.io.File;
import java.io.FileInputStream;

/**
 *
 * @author spousty
 */
public class FileOpener {
    
    private String fileToIndex = "";

    private FileOpener() {
    }

    public FileOpener(String fileToIndex) {
        this.fileToIndex = fileToIndex;
    }
    
    public InputStreamReader getFileForReading(){
        
        InputStreamReader iStreamReader = null;
        
        try {
            iStreamReader = new InputStreamReader(new FileInputStream( new File(fileToIndex)), "UTF-8");
        } catch (Exception e){
            System.out.println(" Yo - something went wrong trying to set up the file to read: " +  e.getClass() + " :: " + e.getMessage());
        }
        
        return iStreamReader;
        
        
    }
    
    
    
    
}
