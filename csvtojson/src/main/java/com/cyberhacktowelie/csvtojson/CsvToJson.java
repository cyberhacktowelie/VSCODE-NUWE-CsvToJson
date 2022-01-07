package com.cyberhacktowelie.csvtojson;

import java.io.File;
import java.io.FileNotFoundException;  
import java.util.Scanner;
import java.util.ArrayList;
import java.util.List;

public class CsvToJson {

    public String csvToJson(File file){
       return generateJson(readCsv(file));
    }

    private List<String[]> readCsv(File file){
        List<String[]> lineas = new ArrayList<>();
        try{   
            Scanner myReader = new Scanner(file);
            while (myReader.hasNextLine()) {
                    String data = myReader.nextLine();
                    lineas.add(data.split(","));
            } 
        myReader.close();
        } catch (FileNotFoundException e) {
            System.out.println("No se ha podido leer el csv");
            e.printStackTrace();
        }
        return lineas;
    }

    private String generateJson(List<String[]> lineas){
        String[] firstline = lineas.get(0);
        lineas.remove(0);
        StringBuilder json = new StringBuilder();
        json.append("[\n");
        for(int j = 0; j < lineas.size(); j++){
          String[] linea = lineas.get(j);
          json.append("\t{\n");
          for(int i = 0; i < linea.length; i++ ){
            json.append("\t\""+parseKey(firstline[i])+"\"\t:\t"+parseValue(linea[i]));
            if(i != linea.length-1){
              json.append(",\n");
            }else{
              json.append("\n");
            }
          }
          if(j !=  lineas.size()-1){
            json.append("\t},\n");
          }else{
            json.append("\t}\n");
          }
        }
        json.append("]\n");
        return json.toString();
    }

    private String parseKey(String key){
      String parsedKey = "";
      if(key.charAt(0) == ' '){
        parsedKey = key.substring(1, key.length());
      }else{
        parsedKey = key;
      }
      return parsedKey;
    }

    private String parseValue(String value){
      String parsedValue = "";
      if( !isNumeric(value) && !value.startsWith("\"") && !value.endsWith("\"")){
        parsedValue = "\""+value+"\"";
      }else{
        parsedValue = value;
      }
      return parsedValue;
    }

    public static boolean isNumeric(String strNum) {
      if (strNum == null) {
          return false;
      }
      try {
          double d = Double.parseDouble(strNum);
      } catch (NumberFormatException nfe) {
          return false;
      }
      return true;
  }
}
/*

[
  {
    "album": "The White Stripes",
    "year": 1999,
    "US_peak_chart_post": "-"
  },
  {
    "album": "De Stijl",
    "year": 2000,
    "US_peak_chart_post": "-"
  },
  {
    "album": "White Blood Cells",
    "year": 2001,
    "US_peak_chart_post": 61
  },
  {
    "album": "Elephant",
    "year": 2003,
    "US_peak_chart_post": 6
  },
  {
    "album": "Get Behind Me Satan",
    "year": 2005,
    "US_peak_chart_post": 3
  },
  {
    "album": "Icky Thump",
    "year": 2007,
    "US_peak_chart_post": 2
  },
  {
    "album": "Under Great White Northern Lights",
    "year": 2010,
    "US_peak_chart_post": 11
  },
  {
    "album": "Live in Mississippi",
    "year": 2011,
    "US_peak_chart_post": "-"
  },
  {
    "album": "Live at the Gold Dollar",
    "year": 2012,
    "US_peak_chart_post": "-"
  },
  {
    "album": "Nine Miles from the White City",
    "year": 2013,
    "US_peak_chart_post": "-"
  }
]
*/