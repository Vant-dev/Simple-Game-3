package com.example.entity;

import lombok.AllArgsConstructor;

import java.util.List;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Data // Генерирует геттеры, сеттеры, toString и другие методы
@NoArgsConstructor // Генерирует конструктор без параметров
@AllArgsConstructor // Генерирует конструктор с параметрами для всех полей

@Entity
@Table(name = "Game")
@Inheritance(strategy = InheritanceType.JOINED)
public abstract class Game 
{
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private  Long gameId; // Первичный ключ
  

 

  @ManyToOne(cascade = {CascadeType.PERSIST, CascadeType.MERGE})
  @JoinColumn(name = "player_id")
  private Player player;

  
  protected Integer difficult;
  protected Integer sCount = 0;
  protected Integer localLife;
  protected Integer moves;
  
  @Transient
  private StringBuilder diff = new StringBuilder();
  @Transient
  private StringBuilder sets = new StringBuilder();
  
  public Player getPlayer() {
	    return this.player;
	}
  
  public void setPlayer(Player player) {
      this.player = player;
  }
  
  public Long getGameId()
  {
	  return this.gameId;
  }

  public Integer sSetter(String s)
  {
	 if (s == null || s.isEmpty()) 
      {
          return 0;
      }
      
      // Проверка, что строка состоит только из цифр
      for (char c : s.toCharArray()) 
      {
          if (!Character.isDigit(c)) 
          {
              return 0;
          }
      }
      
      try 
      {
          int parsedDifficulty = Integer.parseInt(s);
          // Проверка диапазона
          if (parsedDifficulty < 1 || parsedDifficulty > 99) 
          {
              return 0;
          }
          this.sCount = parsedDifficulty; // Установка значения
          return this.sCount; // Возврат установленного значения
      } catch (NumberFormatException e) 
      {
          return 0; // Возврат 0 в случае ошибки парсинга
      }
  }
  
  public Integer setDifficult(String difficulty) 
  {
      if (difficulty == null || difficulty.isEmpty()) 
      {
          return 0;
      }
      
      // Проверка, что строка состоит только из цифр
      for (char c : difficulty.toCharArray()) 
      {
          if (!Character.isDigit(c)) 
          {
              return 0;
          }
      }
      
      try 
      {
          int parsedDifficulty = Integer.parseInt(difficulty);
          // Проверка диапазона
          if (parsedDifficulty < 1 || parsedDifficulty > 10) 
          {
              return 0;
          }
          this.difficult = parsedDifficulty; // Установка значения
          return this.difficult; // Возврат установленного значения
      } catch (NumberFormatException e) 
      {
          return 0; // Возврат 0 в случае ошибки парсинга
      }
  }
  
  public Integer setwordLen(String difficulty) 
  {
      if (difficulty == null || difficulty.isEmpty()) 
      {
          return 0;
      }
      
      // Проверка, что строка состоит только из цифр
      for (char c : difficulty.toCharArray()) 
      {
          if (!Character.isDigit(c)) 
          {
              return 0;
          }
      }
      
      try 
      {
          int parsedDifficulty = Integer.parseInt(difficulty);
          this.difficult = parsedDifficulty; // Установка значения
          return this.difficult; // Возврат установленного значения
      } catch (NumberFormatException e) 
      {
          return 0; // Возврат 0 в случае ошибки парсинга
      }
  }
  
  
  @PrePersist
  @PreUpdate
  protected void onCreate() {
      if (sCount == null) sCount = 0;
      sCount++;
      if (localLife == null) localLife = 3;  // дефолтное значение
  }
  
  public  void setMoves(Integer mv)
  {
	  this.moves = mv;
  }
  
  public Integer getMoves()
  {
	  return moves;
  }
  // Метод для добавления текста к diff
  public void appendToDiff(String text) 
  {
      diff.append(text);
  }
  
  public void appendToSets(String text) 
  {
	    sets.append(text);
  }
  
  public Integer calculateScore(Integer lifeCount, Integer difficult)
  {
       	Integer score = (lifeCount * 10 + difficult * 5);
       	return score;
       	
  }
  
  public void setlocalLife(Integer ll)
  {
	  this.localLife = ll;
  }
  
  public Integer getDifficult()
  {
	  return difficult;
  }
  
  public Integer getlocalLife()
  {
	  return localLife;
  }
  
  
  
  public Integer awardBonusLife(Integer score,Integer lifeCount)
  {
       final Integer bonusTreshold = 100;
       if (score > bonusTreshold) 
       {
          lifeCount++;
       }  
       
       return lifeCount;
  }
  
  abstract public void gameStart();
  
  abstract public void gameEnd();
}
