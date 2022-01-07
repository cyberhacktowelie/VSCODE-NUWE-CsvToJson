package com.cyberhacktowelie.csvtojson;

import java.io.File;

public class FileReader {

    public File openFile(String filePath){
        File f = null;
        try{
            f = new File(filePath);
        }catch(Exception e){
            System.out.println(e.getMessage());
        }
        return f;
    }

    public boolean isCsv(File f){
        return (f.getName().endsWith("csv"));
    }

}
