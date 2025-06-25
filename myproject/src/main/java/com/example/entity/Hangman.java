package com.example.entity;

import java.util.List;
import javax.persistence.PrimaryKeyJoinColumn;


import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Transient;

import org.hibernate.Session;

import com.example.entity.Game;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data // Генерирует геттеры, сеттеры, toString и другие методы
@NoArgsConstructor // Генерирует конструктор без параметров
@AllArgsConstructor // Генерирует конструктор с параметрами для всех полей


@Entity
@Table(name = "Hangman")
@PrimaryKeyJoinColumn(name = "gameId") // правильно связывает с родителем

public class Hangman extends Game {

     private int unknownCharacterCount;
     private String choosedString;
     
    
     @Transient
     private final int M = 4;
     @Transient
     private boolean check;
     @Transient
     private boolean test;
     @Transient
     private char charr;
     @Transient
     private int scooore;
     @Transient
     private String len;
     @Transient
     private String choice;
     @Transient
     protected int[] lengthArr = new int[M];
     @Transient
     private String[] technology  = {"informatics", "computer", "processor", "bitcoin"};
     @Transient
     private String[] biology   = {"mammal", "amphibian", "cell", "family"};
     @Transient
     private String[] cooking   = {"cake", "pie", "cheesecake", "salad"};
     @Transient
     private String  word;
     @Transient
     private DatabaseManager db;
       
     
   

	public Hangman(int life,  String wordLn, int UCC, String chStr, int mv)
     {
		setlocalLife(life);
		setDifficult(wordLn);
    	 	unknownCharacterCount = UCC;
    	 	choosedString = chStr;	
    	 	setMoves(mv);
    	 	this.db = new DatabaseManager();
    	 	
     }
	 
	public Hangman()
	{
		
	}
    
	@Override
	public String toString() {
	    return "Hangman{" +
            "id=" + getGameId() +
            ", wordLength=" + difficult +
            ", unknownCharacterCount=" + unknownCharacterCount +
            ", choosedString='" + choosedString + '\'' +
            ", localLife=" + localLife +
            ", player=" + (getPlayer() != null ? getPlayer().getNick() : "null") +
            '}';
	}
    
	public void setWordLength(Integer len)
	{
		difficult = len;
	}
	
	public void setUnknownCharacterCount(Integer UCC)
	{
		this.unknownCharacterCount = UCC;
	}
	
	public void setChoosedString(String str)
	{
		this.choosedString = str;
	}
	
	public void setWord(String wrd)
	{
		this.word = wrd;
	}
	
     public void setLength(String choice) {
         if (choice.equals("1")) {
             for (int i = 0; i < M; i++) {
                 lengthArr[i] = technology[i].length();
             }
         } else if (choice.equals("2")) {
             for (int i = 0; i < M; i++) {
                 lengthArr[i] = biology[i].length();
             }
         } else if (choice.equals("3")) {
             for (int i = 0; i < M; i++) {
                 lengthArr[i] = cooking[i].length();
             }
         }
     }
     
     public void setChoice(String ch)
     {
    	 	choice = ch;
     }
     
     public Integer getLife()
     {
    	 	return localLife;
     }
     
     public Integer getUnknownCharacterCount()
     {
    	 	return unknownCharacterCount;
     }
     
     public String getWord()
     {
    	 	return word;
     }
     
     public Integer getScoore()
     {
    	 	return scooore;
     }
     
     public void checkWord(char character) {
         check = false;
         for (int i = 0; i < choosedString.length(); i++) {
             if (choosedString.charAt(i) == character) {
                 word = word.substring(0, i) + character + word.substring(i + 1);
                 unknownCharacterCount--;
                 check = true;
             }
         }
         if (!check) {
        	 		setlocalLife(getlocalLife() - 1);
         }
         setMoves(getMoves() + 1);
     }
     
     public int setLn(String lenStr) {
         if (lenStr.isEmpty()) return 0;
         for (char m : lenStr.toCharArray()) {
             if (!Character.isDigit(m)) {
                 return 0;
             }
         }
         try {
            String s = lenStr;
            setwordLen(s);
         } catch (NumberFormatException e) {
             return 0;
         }
         test = false;
         for (int i = 0; i < M; i++) {
             if (lengthArr[i] == getDifficult()) {
                 test = true;
                 break;
             }
         }
         if (!test) {
             return 0;
         } else {
             test = false;
             return getDifficult();
         }
     }

     public void chooseWord() {
         choosedString = "";
         if (choice.equals("1")) {
             while (choosedString.length() != getDifficult()) {
                 choosedString = technology[(int) (Math.random() * M)];
             }
         } else if (choice.equals("2")) {
             while (choosedString.length() != getDifficult()) {
                 choosedString = biology[(int) (Math.random() * M)];
             }
         } else {
             while (choosedString.length() != getDifficult()) {
                 choosedString = cooking[(int) (Math.random() * M)];
             }
         }
         word = "*".repeat(choosedString.length());
         unknownCharacterCount = choosedString.length();
     }

     public void gameStart() {
         word = "";
         setDifficult("4");
         moves = 0;
         unknownCharacterCount = 0;
         check = false;
         test = false;
         choosedString = "";
     }

     public void gameEnd() {
    
    	 	
         scooore = calculateScore(getlocalLife(),difficult);
         setlocalLife(awardBonusLife(scooore,getlocalLife()));
     }
	
}
