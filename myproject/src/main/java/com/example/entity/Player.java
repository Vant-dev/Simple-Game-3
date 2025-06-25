package com.example.entity;

import javax.persistence.Entity;
import javax.persistence.FetchType;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Transient;

import javax.persistence.OneToMany;
import javax.persistence.CascadeType;
import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import com.example.entity.Game;


import java.util.List;


@Entity
@Table(name = "Player")

public class Player 
{
   @Id
   @GeneratedValue(strategy = GenerationType.IDENTITY)
   private Long id;


   @OneToMany(mappedBy = "player", cascade = CascadeType.ALL)
   private List<Game> games;
	
	private String nickname;
	
	private String gameType;
	
	
	@Override
    public String toString() {
        return "Treasure{" +
                "id=" + getId() +
                ", gameType=" + getGameType() +
                ", nickname=" + getNick() +'}';
    }
	


	public void setGameType(String type)
	{
		this.gameType = type;
	}
	
	public String getGameType()
	{
		return this.gameType;
	}
	
	public Long getId()
	{
		return this.id;
	}
	
	public Integer setNick(String n)
	{
		if(n == null || n.isEmpty())
		{
			return 0;
		}
		this.nickname = n;
		
		return 1;
	}
	
	public String getNick()
	{
		return this.nickname;
	}
	
	public List<Game> getGames()
	{
		return this.games;
	}

}
