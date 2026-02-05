package cir.java.FinalProject2;

import java.util.List; 

	 class Part{
		String string;
		boolean correct;
		
		public String getString() {
			return string;
		}

		public void setString(String string) {
			this.string = string;
		}

		public boolean isCorrect() {
			return correct;
		}

		public void setCorrect(boolean correct) {
			this.correct = correct;
		}

		public Part() {
			
		}
		
		public Part(String string,boolean correct) {
			this.string = string;
			this.correct = correct;
		}
	 }
	 
public class Answer {
	public Answer(String question, List<Part> parts, int otvet,int level) {
		this.question = question;
		this.parts = parts;
		this.otvet = otvet;
		this.level = level;
	}
	String question;
	List<Part> parts;
	int otvet;
	int level;
	
	public Answer() {
		
	}
	public String getQuestion() {
		return question;
	}
	public void setQuestion(String question) {
		this.question = question;
	}
	public List<Part> getParts() {
		return parts;
	}
	public void setParts(List<Part> parts) {
		this.parts = parts;
	}
	public int getOtvet() {
		return otvet;
	}
	public void setOtvet(int otvet) {
		this.otvet = otvet;
	}
	public int getLevel() {
		return level;
	}
	public void setLevel(int level) {
		this.level = level;
	}

}