package com.alexander.compression;

import java.util.LinkedList;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

public class Word {

	private String word;
	private List<Integer> occurances = new LinkedList<Integer>();
	private String marker;
	
	private static AtomicInteger count = new AtomicInteger();
	protected static String prefix = "#";
	
	private String generateMarker(){
		return prefix+count.getAndIncrement();
	}
	
	public String getWord() {
		return word;
	}
	public void setWord(String word) {
		this.word = word;
	}
	public int getSize() {
		if (this.word == null){
			return 0;
		}
		return word.length();
	}
	public int getOccuranceCount() {
		return this.occurances.size();
	}
	public void addOccurance(int location){
		if (this.occurances.size() == 0){
			this.marker = this.generateMarker();
		}
		this.occurances.add(location);
	}
	public List<Integer> getOccurances(){
		return this.occurances;
	}
	public String getMarker() {
		return marker;
	}
}
