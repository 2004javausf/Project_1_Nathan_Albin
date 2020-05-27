package com.trs.beans;

public class Sentence {
	
	private String Sentence;

	public Sentence() {
		super();
	}

	public Sentence(String sentence) {
		super();
		Sentence = sentence;
	}

	public String getSentence() {
		return Sentence;
	}

	public void setSentence(String sentence) {
		Sentence = sentence;
	}

	@Override
	public String toString() {
		return "Sentence [Sentence=" + Sentence + "]";
	}
	
	

}
