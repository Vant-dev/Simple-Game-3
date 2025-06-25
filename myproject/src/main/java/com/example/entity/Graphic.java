package com.example.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


//Импортируем класс Treasure
import com.example.entity.Treasure;

@Data // Генерирует геттеры, сеттеры, toString и другие методы
@NoArgsConstructor // Генерирует конструктор без параметров
@AllArgsConstructor // Генерирует конструктор с параметрами для всех полей


public class Graphic {
    
	private Treasure treasure;
    

    public Graphic(Treasure treasure) 
    {
        this.treasure = treasure;
    }
    
    // Метод для отображения ячеек
    public void showCell() {
    		for (int i = 0; i < 50; i++) 
		{
			System.out.println();
		} 

        for (int i = 0; i < treasure.height; i++) {
            for (int j = 0; j < treasure.width; j++) {
                if (treasure.isOpen(i, j)) {
                    System.out.print(treasure.arr[i][j] + " ");
                } else {
                    System.out.print("x ");
                }
            }
            System.out.println();
        }
    }

    // Метод для установки координат
    public void setsCord(String X1, String Y1) {
        treasure.X = X1; // Используйте this для ясности
        treasure.Y = Y1;
    }
}
