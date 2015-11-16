package com.alexander.compression;

import static org.junit.Assert.*;

import java.util.Map;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class FullWordCompressionTest {
	FullWordCompression full;
	@Before
	public void setUp() throws Exception {
		full = new FullWordCompression();
	}

	@After
	public void tearDown() throws Exception {
	}

	private String emptyLine = "";
	@Test
	public void testReadLine_givenEmptyLine() {
		full.readLine(emptyLine);
		Map<String, Word> map = full.getWordMap();
		assertEquals(0, map.size());
	}
	
	private String whitespaceLine = "              ";
	@Test
	public void testReadLine_givenWhitespaceLine(){
		full.readLine(whitespaceLine);
		Map<String, Word> map = full.getWordMap();
		assertEquals(1, map.size());
		assertEquals(1, map.get(whitespaceLine).getOccuranceCount());
		assertEquals(Word.prefix+0, map.get(whitespaceLine).getOccurances().get(0));
		
		
	}

	private String one = "one";
	private String two = "two";
	private String three = "three";
	private String four = "four";
	private String space = " ";
	
	private String noRepeatedWords = one+space+two+space+three+space+four;
	@Test
	public void testReadLine_givenNoWordsRepeated(){
		full.readLine(noRepeatedWords);
		assertEquals(4, full.getWordMap().size());
		
		assertNotNull(full.getWordMap().get(one));
		assertEquals(1, full.getWordMap().get(one).getOccuranceCount());
		assertEquals(1, full.getWordMap().get(one).getOccurances().size());
		assertEquals(0, full.getWordMap().get(one).getOccurances().get(0).intValue());
		
		assertNotNull(full.getWordMap().get(two));
		assertEquals(1, full.getWordMap().get(two).getOccuranceCount());
		assertEquals(1, full.getWordMap().get(two).getOccurances().size());
		assertEquals(1, full.getWordMap().get(two).getOccurances().get(0).intValue());

		assertNotNull(full.getWordMap().get(three));
		assertEquals(1, full.getWordMap().get(three).getOccuranceCount());
		assertEquals(1, full.getWordMap().get(three).getOccurances().size());
		assertEquals(2, full.getWordMap().get(three).getOccurances().get(0).intValue());
		
		assertNotNull(full.getWordMap().get(four));
		assertEquals(1, full.getWordMap().get(four).getOccuranceCount());
		assertEquals(1, full.getWordMap().get(four).getOccurances().size());
		assertEquals(3, full.getWordMap().get(four).getOccurances().get(0).intValue());
		
	}

	private String twoRepeatedWord = one+space+two+space+one+space+two;
	@Test
	public void testReadLine_givenTwoRepeatedWords(){
		full.readLine(twoRepeatedWord);
		assertEquals(2, full.getWordMap().size());
		
		assertNotNull(full.getWordMap().get(one));
		assertEquals(2, full.getWordMap().get(one).getOccuranceCount());
		assertEquals(2, full.getWordMap().get(one).getOccurances().size());
		assertEquals(0, full.getWordMap().get(one).getOccurances().get(0).intValue());
		assertEquals(2, full.getWordMap().get(one).getOccurances().get(1).intValue());
		assertEquals(Word.prefix+0, full.getWordMap().get(one).getMarker());
		
		assertNotNull(full.getWordMap().get(two));
		assertEquals(2, full.getWordMap().get(two).getOccuranceCount());
		assertEquals(2, full.getWordMap().get(two).getOccurances().size());
		assertEquals(1, full.getWordMap().get(two).getOccurances().get(0).intValue());
		assertEquals(3, full.getWordMap().get(two).getOccurances().get(1).intValue());
		assertEquals(Word.prefix+1, full.getWordMap().get(two).getMarker());
	
	}

	@Test
	public void testWriteLine_givenEmptyLine(){
		full.readLine(emptyLine);
		String resultLine = full.writeLine();
		String expectedLine = emptyLine;
		assertEquals(expectedLine, resultLine);
	}

	@Test
	public void testWriteLine_givenWhitespaceLine(){
		full.readLine(whitespaceLine);
		String resultLine = full.writeLine();
		String expectedLine = Word.prefix+0;
		assertEquals(expectedLine, resultLine);
	}
	
	@Test
	public void testWriteLine_givenNoWordsRepeated(){
		full.readLine(noRepeatedWords);
		String resultLine = full.writeLine();
		System.out.println(resultLine);
		resultLine.contains(Word.prefix+0);
		resultLine.contains(Word.prefix+1);
		resultLine.contains(Word.prefix+2);
		resultLine.contains(Word.prefix+3);
		
		String expectedLine = Word.prefix+0+space+Word.prefix+1+space+Word.prefix+2+space+Word.prefix+3;
		assertEquals(expectedLine, resultLine);
	}
	
	@Test
	public void testWriteLine_givenTwoWordsRepeated(){
		full.readLine(twoRepeatedWord);
		String resultLine = full.writeLine();
		System.out.println(resultLine);
		resultLine.contains(Word.prefix+0);
		resultLine.contains(Word.prefix+1);
		
		String expectedLine = Word.prefix+0+space+Word.prefix+1+space+Word.prefix+0+space+Word.prefix+1;
		assertEquals(expectedLine, resultLine);
	}

}












