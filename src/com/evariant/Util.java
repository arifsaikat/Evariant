package com.evariant;

import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import com.opencsv.CSVReader;

public class Util {
	public static Map<String, Long> getPopulationByArea(String fileName){	
		Map<String,  Long> populationByAreaMap = new HashMap<String,  Long>();
		CSVReader reader = null;
		long population = 0;
		try {
			reader = new CSVReader(new FileReader(fileName));
			String [] nextLine;
		     while ((nextLine = reader.readNext()) != null) {
		    	 try {
		    		 population = Long.parseLong(nextLine[2].replace(",",""));
		    		 populationByAreaMap.put(nextLine[0].toUpperCase(), population);
		    		 } catch(Exception e) {
		    			// not a number 
		    		 }
		     }
		     reader.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return populationByAreaMap;
	}
	
	public static Map<String, List<String>> getWbanByArea(String fileName){	
		Map<String,  List<String>> wbanByAreaMap = new HashMap<String,  List<String>>();
		CSVReader reader = null;
		List<String> wbanList = null;
		try {
			reader = new CSVReader(new FileReader(fileName), '|');
			String [] nextLine;
		     while ((nextLine = reader.readNext()) != null) {
		    	 if(nextLine[3] != null && nextLine[3].length() > 0) // only add areas with state
		    	 {
		    		 String key = nextLine[2] + ", " + nextLine[3];
		    		 key = key.toUpperCase();
		    		 wbanList = wbanByAreaMap.get(key);
		    		 if(wbanList == null)
		    			 wbanList = new LinkedList<String>();
		    		 wbanList.add(nextLine[1]); // add the wban to the area
		    		 wbanByAreaMap.put(key, wbanList);
		    	 }
		     }
		     reader.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return wbanByAreaMap;
	}
	
	public static Map<String, Double> getPrecipitationByWban(String fileName){	
		Map<String, Double> precipitationByWbanMap = new HashMap<String, Double>();
		CSVReader reader = null;
		double precipitation = 0;
		int hour = 0;
		try {
			reader = new CSVReader(new FileReader(fileName));
			String [] nextLine;
		     while ((nextLine = reader.readNext()) != null) {
		    		 try {
		    	     hour = Integer.parseInt(nextLine[2]); // hour
		    	     if(hour <= 7) // ignore rain between 12AM and 7AM
		    	    	 continue;
		    		 precipitation = Double.parseDouble(nextLine[3]);
		    		 precipitation = precipitation + (precipitationByWbanMap.get(nextLine[0]) == null ? 0 : precipitationByWbanMap.get(nextLine[0]));
		    		 precipitationByWbanMap.put(nextLine[0], precipitation);
		    		 } catch(Exception e) {
		    			// not a number 
		    		 }
		     }
		     reader.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return precipitationByWbanMap;
	}
}
