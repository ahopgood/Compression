package com.alexander.compression;

import java.io.InputStream;
import java.io.OutputStream;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

public class FullWordCompression implements Compressor {

	public static void main (String[] args){
		
	}

	public void readCompressedFile(InputStream stream) {
		// TODO Auto-generated method stub
		
	}

	public boolean writeCompressedFile(OutputStream output) {
		// TODO Auto-generated method stub
		return false;
	}
	
	private Map<String,Word> wordMap = new HashMap<String, Word>();
	private List<String> documentList = new LinkedList<String>();
	
	
	public void readLine(String line){
		int count = 0;
		String [] words = line.split(" ");
		
		for (String word : words){
			if (!word.isEmpty()){
				if (!wordMap.containsKey(word)){
					Word newWord = new Word();
					newWord.setWord(word);
					wordMap.put(word, newWord);
				}
				wordMap.get(word).addOccurance(count);
				count++;		
			}
		}
	}

	public String writeLine(){
		StringBuilder builder = new StringBuilder();
		List<String> occurances = new LinkedList<String>();
		for (String key : this.wordMap.keySet()){
			Word word = this.wordMap.get(key);
			for (Integer occuranceMarker : word.getOccurances()){
				occurances.set(occuranceMarker, word.getMarker());
			}
		}
		for (String string : occurances){
			builder.append(string);
		}
		return builder.toString();
	}
	
	public Map<String, Word> getWordMap(){
		return this.wordMap;
	}
}
