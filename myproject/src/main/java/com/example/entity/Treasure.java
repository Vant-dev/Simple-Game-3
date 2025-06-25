package com.example.entity;
import com.example.entity.Game;

import javax.persistence.*;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.util.Random;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "Treasure")
@PrimaryKeyJoinColumn(name = "gameId") // правильно связывает с родителем

public class Treasure extends Game 
{	
	
    private int bombCount;
    private int collectTreasure;   
    protected int x,y;

    private int  maxTreasure;
    private int  treasureCount;
    private int moveCount;
    
    @Transient
    protected String X, Y;
    @Transient
	protected static final Integer height = 5;
    @Transient
    protected static final Integer width = 5;
    @Transient
    protected int[][] arr = new int[height][width];
    @Transient
    private int[] cordX = new int[25];
    @Transient
    private int[] cordY = new int[25];
    @Transient
    private int indexX;
    @Transient
    private int  indexY;
    @Transient
    private int  scoore;
    
    
    @Override
    public String toString() {
        return "Treasure{" +
                "id=" + getGameId() +
                ", x=" + getX() +
                ", y=" + getY() +
                ", bombCount=" + getBombCount() +
                ", collectTreasure=" + getCollectTreasure() +
                ", maxTreasure=" + getMaxTreasure() +
                ", moveCount=" + getMoveCount() +
                ", treasureCount=" + getTreasureCount() +
                ", localLife =" + getLocalLife() +
                ", player=" + (getPlayer() != null ? getPlayer().getNick() : "null") +
                '}';
    }
    
    public Treasure(Integer ll, Integer bC, Integer tC, Integer mv)
    {
    		setlocalLife(ll);
    		this.bombCount = bC;
    		this.treasureCount = tC;
    		setMoves(mv);
    }
    
    public Treasure() {
        
    }
    
    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public int getBombCount() {
        return bombCount;
    }

    public int getMoveCount() {
        return moveCount;
    }

    public int getTreasureCount() {
        return treasureCount;
    }

  
    
    public void setBombCount(Integer bC)
    {
    		this.bombCount = bC;
    }
    
    public void setCollectTreasure(Integer cT)
    {
    	  	this.collectTreasure = cT;
    }
    
    public void setMaxTreasure(Integer mT)
    {
    		this.maxTreasure = mT;
    }
    
    public Integer getLocalLife()
    {
    		return localLife;
    }
    
    public Integer getCollectTreasure()
    {
    		return collectTreasure;
    }
    
    
    public Integer getMaxTreasure()
    {
    		return maxTreasure;
    }
    
    public Integer getScoore()
    {
    		return scoore;
    }
    
    public boolean isOpen(int i, int j)
    {
    	  		for(int k = 0; k < indexX; k++)
    	  		{
    	  			if(cordX[k] == i && cordY[k] == j)
    	  			{
    	  				return true;
    	  			}
    	  		}
    	  		return false;
    }
    
    public void hideTreasure()
    {
    	   	for(int i = 0; i < height; i++)
    	   	{
    	   		for(int j = 0; j < width; j++)
        	   		{
        	   			if(arr[i][j] != 2)
        	   			{
        	   				if (treasureCount < getDifficult())
        	   				{
        	   					arr[i][j] = (int) (Math.random() * (0 - 1 + 1)) + 0;
        	   					if(arr[i][j] == 1)
        	   					{
        	   						treasureCount++;
        	   					}
        	   				}
        	   			}
        	   		}
    	   	}
    }
    
    @Override
    public void gameStart() {
        bombCount = 0;
        collectTreasure = 0;
        setlocalLife(3);
        indexX = indexY = moveCount = treasureCount = 0;

        for (int i = 0; i < height; i++) {
            for (int j = 0; j < width; j++) {
                arr[i][j] = 0;
            }
        }

        maxTreasure = difficult;

        while (bombCount < difficult) {
        			x = (int) (Math.random() * height); // Генерирует случайное число от 0 до height - 1
        			y = (int) (Math.random() * width);  // Генерирует случайное число от 0 до width - 1
            if (arr[x][y] == 0) {
                arr[x][y] = 2;
                bombCount++;
            }
        }
        bombCount = 0; // для статистики
    }

    public void playTurn(String X, String Y) {
        x = Integer.parseInt(X);
        y = Integer.parseInt(Y);

        if (x >= 0 && x < height && y >= 0 && y < width) {
            if (arr[x][y] == 2) {
                if (!isOpen(x, y)) {
                    localLife--;
                    bombCount++;
                    cordX[indexX] = x;
                    cordY[indexY] = y;
                    indexX++;
                    indexY++;
                    moveCount++;
                }
            } else if (arr[x][y] == 1) {
                if (!isOpen(x, y)) {
                    collectTreasure++;
                    cordX[indexX] = x;
                    cordY[indexY] = y;
                    indexX++;
                    indexY++;
                    moveCount++;
                }
            } else {
                cordX[indexX] = x;
                cordY[indexY] = y;
                indexX++;
                indexY++;
                moveCount++;
            }
        }
        setMoves(getMoves() + 1);
    }

    @Override
    public void gameEnd() {
        scoore = calculateScore(localLife,maxTreasure);
        localLife = awardBonusLife(scoore,localLife);
    }
}
