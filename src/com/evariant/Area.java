package com.evariant;

public class Area implements Comparable<Area>
{
	private String name;
	private Double precipitation;
	public Area(String name, double precipitation){
		this.name = name;
		this.precipitation = precipitation;		
	}
	
	public String toString(){
		return name + " [" + String.format("%.2f", precipitation) + "]";		
	}
	
	public Double getPrecipitation() {
		return precipitation;
	}
	
	public String getName() {
		return name;
	}

	@Override
	public int compareTo(Area o) {
		// TODO Auto-generated method stub
		return (-1) * this.getPrecipitation().compareTo(o.getPrecipitation());
	}
}
