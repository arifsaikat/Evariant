package com.evariant;

import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

public class Sort {

	public static void main(String[] args) {
		// TODO Auto-generated method stub	
		 long startTime = System.currentTimeMillis();
		 List<Area> list = getAreaListing(Util.getPrecipitationByWban("data//201505precip.txt"), 
					Util.getWbanByArea("data//wbanmasterlist.psv"), 
					Util.getPopulationByArea("data//areas.csv"));
	      long stopTime = System.currentTimeMillis();
	      long elapsedTime = stopTime - startTime;
	      System.out.println("Time Taken: " + elapsedTime + " milisec");
		
		for (Area a : list) {
	        System.out.println(a);
	    }
	}
	
	public static List<Area> getAreaListing(Map<String, Double> precipitationByWbanMap,
								Map<String, List<String>> wbanByAreaMap,
								Map<String, Long> populationByAreaMap){
		List<Area> areaList = new LinkedList<Area>();
		double precipitation = 0;
		for (String key : populationByAreaMap.keySet()) {
			Long population = populationByAreaMap.get(key);
			List<String> wbanList = wbanByAreaMap.get(key);
			if(wbanList != null) {
				precipitation = 0;
				for (String wban : wbanList) {
					precipitation = precipitation + (precipitationByWbanMap.get(wban) == null ? 0 : precipitationByWbanMap.get(wban));    		 
				}
				areaList.add(new Area(key, precipitation * population));
			}
	        
	    }
		Collections.sort(areaList);
				
		return areaList;
	}
	


}
