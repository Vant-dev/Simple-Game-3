package com.example.entity;

import java.util.Scanner;
import com.example.entity.Treasure;
import com.example.entity.Hangman;
import com.example.entity.Graphic;
import java.util.List;

public class Main {

    private static void databaseMenu(String table, DatabaseManager db, Scanner scanner, Player currentPlayer) {
        if (table.equals("Player"))
        {
        			while (true) {
                System.out.println("\n--- Меню для таблицы " + table + " ---");
                System.out.println("1. Показать все записи");
                System.out.println("2. Показать по ID");
                System.out.println("3. Добавить новую запись");
                System.out.println("4. Обновить существующую запись");
                System.out.println("5. Удалить по ID");
                System.out.println("6. Вывести игры по нику");
                System.out.println("7. Назад");
                System.out.print("Выберите действие: ");
                String action = scanner.nextLine();

                switch (action) {
                    case "1": showAll(table, db); break;
                    case "2": showById(table, db, scanner); break;
                    case "3": insertNew(table, db, scanner, currentPlayer); break;
                    case "4": updateAll(table,db, scanner); break;
                    case "5": deleteById(table, db, scanner); break; 
                    case "6": searchbyNickname(db, scanner); break;        
                    case "7": return;
                    default: System.out.println("Некорректный выбор.");
                }
            }
        }
        else {
    			while (true) {
    				System.out.println("\n--- Меню для таблицы " + table + " ---");
    				System.out.println("1. Показать все записи");
    				System.out.println("2. Показать по ID");
    				System.out.println("3. Добавить новую запись");
    				System.out.println("4. Обновить существующую запись");
    				System.out.println("5. Удалить по ID");
    				System.out.println("6. Назад");
    				System.out.print("Выберите действие: ");
    				String action = scanner.nextLine();

    				switch (action) {
    					case "1": showAll(table, db); break;
    					case "2": showById(table, db, scanner); break;
    					case "3": insertNew(table, db, scanner, currentPlayer); break;
    					case "4": updateAll(table,db, scanner); break;
    					case "5": deleteById(table, db, scanner); break;     
    					case "6": return;
    					default: System.out.println("Некорректный выбор.");
    				}
    			}
        }
     }

    private static void showAll(String table, DatabaseManager db) {
        switch (table) {
            case "Game": db.getAllGame().forEach(System.out::println); break;
            case "Player": db.getAllPlayer().forEach(System.out::println); break;
            case "Hangman": db.getAllHangman().forEach(System.out::println); break;
            case "Treasure": db.getAllTreasure().forEach(System.out::println); break;
        }
    }
    
    private static void updateAll(String table, DatabaseManager db, Scanner scanner)
    {
    	System.out.print("Введите id изменяемого столбца: ");
    	String str = scanner.nextLine();
    	Long id;
    	
    	try 
    	{
    		id = Long.valueOf(str);
    	} 
    	catch (NumberFormatException e) 
    	{
    		System.out.println("Ошибка: некорректный формат ID.");
    	    	return;
    	}
    		
    		switch (table)
    		{
    
    			case "Game":
    			{
    				System.out.print("Введите имя изменяемого столбца: ");
    				String name = scanner.nextLine();
    				
    				if (!name.isEmpty() && !name.equals("gameId") && !name.equals("player_id"))
    				{
    					System.out.print("Введите изменяемое значение: ");
    					String value = scanner.nextLine();
    					
    					if(!value.isEmpty())
    					{
    						db.updateGame(id,name,value);
    						System.out.print("Данные столбца " + name + " были изменены.");
    					}
    					
    				}
    				else
    				{
    					System.out.print("Вы не можете менять параметры id или введённая строка пустая.");
    					return;
    				}
    				break;
    			}
    			
    			case "Player":
    			{
    				System.out.print("Введите имя изменяемого столбца: ");
    				String name = scanner.nextLine();
    				
    				if (!name.isEmpty() && !name.equals("id"))
    				{
    					System.out.print("Введите изменяемое значение: ");
    					String value = scanner.nextLine();
    					
    					if(!value.isEmpty())
    					{
    						db.updatePlayer(id,name,value);
    						System.out.print("Данные столбца " + name + " были изменены.");
    					}
    					
    				}
    				else
    				{
    					System.out.print("Вы не можете менять параметры id или введённая строка пустая.");
    					return;
    				}
    				break;
    			}
    			case "Treasure":
    			{

    				System.out.print("Введите имя изменяемого столбца: ");
    				String name = scanner.nextLine();
    				
    				if (!name.isEmpty() && !name.equals("gameid"))
    				{
    					System.out.print("Введите изменяемое значение: ");
    					String value = scanner.nextLine();
    					
    					if(!value.isEmpty())
    					{
    						db.updateTreasure(id,name,value);
    						System.out.print("Данные столбца " + name + " были изменены.");
    					}
    					
    				}
    				else
    				{
    					System.out.print("Вы не можете менять параметры id или введённая строка пустая.");
    					return;
    				}
    				break;
    			}
    			case "Hangman":
    			{	
    				System.out.print("Введите имя изменяемого столбца: ");
				String name = scanner.nextLine();
				
				if (!name.isEmpty() && !name.equals("gameid"))
				{
					System.out.print("Введите изменяемое значение: ");
					String value = scanner.nextLine();
					
					if(!value.isEmpty())
					{
						db.updateHangman(id,name,value);
						System.out.print("Данные столбца " + name + " были изменены.");
					}
					
				}
				else
				{
					System.out.print("Вы не можете менять параметры id или введённая строка пустая.");
					return;
				}
    				break;
    			}
    		}
    }

    private static void showById(String table, DatabaseManager db, Scanner scanner) {
        System.out.print("Введите ID: ");
        try {
            Long id = Long.parseLong(scanner.nextLine());
            switch (table) {
                case "Game": System.out.println(db.getGame(id)); break;
                case "Player": System.out.println(db.getPlayer(id)); break;
                case "Hangman": System.out.println(db.getHangman(id)); break;
                case "Treasure": System.out.println(db.getTreasure(id)); break;
            }
        } catch (NumberFormatException e) {
            System.out.println("Ошибка: введён некорректный ID.");
        }
    }

    private static void searchbyNickname(DatabaseManager db, Scanner scanner)
    {
    		
    		List<Player> pl = db.getAllPlayer(); 

        System.out.print("Введите id: ");
    		Long id  = Long.parseLong(scanner.nextLine());
    		db.FindGamesByPlayerId(id);
    		

    	}
    
    private static void deleteById(String table, DatabaseManager db, Scanner scanner) {
        System.out.print("Введите ID для удаления: ");
        try {
            Long id = Long.parseLong(scanner.nextLine());
            switch (table) {
                case "Game": db.deleteGame(id); break;
                case "Player": db.deletePlayer(id); break;
                case "Hangman": db.deleteHangman(id); break;
                case "Treasure": db.deleteTreasure(id); break;
            }
            System.out.println("Удаление выполнено (если запись существовала).");
        } catch (NumberFormatException e) {
            System.out.println("Ошибка: введён некорректный ID.");
        }
    }

    private static void insertNew(String table, DatabaseManager db, Scanner scanner, Player currentPlayer) {
        switch (table) {
            case "Game":
                System.out.println("Выберите тип игры: 1 - Treasure, 2 - Hangman");

                // Сохраняем currentPlayer, если он ещё не сохранён
                if (currentPlayer.getId() == null) { // метод getId() замените на правильный геттер id
                    db.savePlayer(currentPlayer);
                }

                String type = scanner.nextLine();
                if (type.equals("1")) {
                    Treasure t = new Treasure(3,0,0,0);
                    fillTreasure(t, scanner);
                    t.setPlayer(currentPlayer);
                    db.saveTreasure(t);
                } else if (type.equals("2")) {
                    Hangman h = new Hangman();
                    fillHangman(h, scanner);
                    h.setPlayer(currentPlayer);
                    db.saveHangman(h);
                } else {
                    System.out.println("Неверный выбор.");
                }
                break;

            case "Player":
                Player p = new Player();
                System.out.print("Введите ник: ");
                p.setNick(scanner.nextLine());
                System.out.print("Введите тип игры: ");
                p.setGameType(scanner.nextLine());
                db.savePlayer(p);
                System.out.println("Игрок сохранён.");
                break;

            case "Hangman":
                if (currentPlayer.getId() == null) {
                    db.savePlayer(currentPlayer);
                }
                Hangman h = new Hangman(3, "", 0, "",0);
                fillHangman(h, scanner);
                h.setPlayer(currentPlayer);
                db.saveHangman(h);
                System.out.println("Игра Hangman сохранена.");
                break;

            case "Treasure":
                if (currentPlayer.getId() == null) {
                    db.savePlayer(currentPlayer);
                }
                Treasure t = new Treasure(3,0,0,0);
                fillTreasure(t, scanner);
                t.setPlayer(currentPlayer);
                db.saveTreasure(t);
                System.out.println("Игра Treasure сохранена.");
                break;
        }
    }


    private static void fillTreasure(Treasure t, Scanner scanner) {
        System.out.print("Введите сложность (1–10): ");
        while (t.setDifficult(scanner.nextLine()) == 0) {
            System.out.print("Неверно. Введите снова: ");
        }
        System.out.print("Введите количество бомб: ");
        t.setBombCount(Integer.parseInt(scanner.nextLine()));
        System.out.print("Введите количество найденных сокровищ: ");
        t.setCollectTreasure(Integer.parseInt(scanner.nextLine()));
        System.out.print("Введите максимум сокровищ: ");
        t.setMaxTreasure(Integer.parseInt(scanner.nextLine()));
    }

    private static void fillHangman(Hangman h, Scanner scanner) {
        System.out.print("Введите длину слова: ");
        h.setWordLength(Integer.parseInt(scanner.nextLine()));
        System.out.print("Введите количество неизвестных символов: ");
        h.setUnknownCharacterCount(Integer.parseInt(scanner.nextLine()));
        System.out.print("Введите выбранную строку: ");
        h.setChoosedString(scanner.nextLine());
        System.out.print("Введите слово: ");
        h.setWord(scanner.nextLine());
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Player player = new Player();
        DatabaseManager db = new DatabaseManager();

        System.out.println("Введите ваш ник: ");
        String n = scanner.nextLine();
        while (player.setNick(n) == 0) {
            System.out.println("Ник должен быть длинной минимум 1 символ: ");
            n = scanner.nextLine();
        }

        while (true) {
            System.out.println("Меню:");
            System.out.println("1. Игра 'Поиск сокровищ'");
            System.out.println("2. Игра 'Виселица'");
            System.out.println("3. Изменить число сетов");
            System.out.println("4. Операции с Бд");
            System.out.println("5. Выход");
            System.out.print("Выберите опцию: ");
            String menuChoice = scanner.nextLine();

            switch (menuChoice) {
                case "1": {
                    System.out.print("Выберите уровень сложности (1–10): ");
                    String diffStr = scanner.nextLine();
                    int difficulty;

                    while (true) {
                        try {
                            difficulty = Integer.parseInt(diffStr);
                            if (difficulty < 1 || difficulty > 10) {
                                throw new NumberFormatException();
                            }
                            break;
                        } catch (NumberFormatException e) {
                            System.out.print("Введите корректное число сложности (1–10): ");
                            diffStr = scanner.nextLine();
                        }
                    }

                    Treasure treasure = new Treasure(3,0,0,0);

                    while (treasure.setDifficult(diffStr) == 0) {
                        System.out.print("Введите корректное число сложности (1–10): ");
                        diffStr = scanner.nextLine();
                    }

                    Graphic graphic = new Graphic(treasure);

                    treasure.gameStart();
                    graphic.showCell();

                    while (treasure.getLocalLife() > 0 && treasure.getCollectTreasure() < treasure.getMaxTreasure()) {
                        System.out.print("Введите координату X: ");
                        String x = scanner.nextLine();
                        System.out.print("Введите координату Y: ");
                        String y = scanner.nextLine();

                        treasure.playTurn(x, y);
                        graphic.showCell();
                    }

                    treasure.gameEnd();
                    System.out.println("Игра 'Поиск сокровищ' завершена.");
                    System.out.println("Очки: " + treasure.getScoore());
                    System.out.println("Нажмите ENTER для возврата в меню...");
                    player.setGameType("Treasure");
                    db.savePlayer(player);
                    treasure.setPlayer(player);
                    db.saveTreasure(treasure);
                    scanner.nextLine();
                    break;
                }

                case "2": {
                    System.out.println("Выберите категорию: 1. Technology, 2. Biology, 3. Cooking");
                    String choice = scanner.nextLine();
                    while (!choice.matches("[123]")) {
                        System.out.print("Введите корректный выбор (1-3): ");
                        choice = scanner.nextLine();
                    }

                    Hangman hangman = new Hangman(3, "", 0, "",0);
                    hangman.setChoice(choice);
                    hangman.setLength(choice);

                    hangman.gameStart();

                    System.out.print("Возможные длины слов: ");
                    
                    for (int i = 0; i < 4; i++) {
                        System.out.print(hangman.lengthArr[i] + " ");
                    }

                    System.out.print("Выберите длину слова: ");
                    String lenStr = scanner.nextLine();
                    while (hangman.setLn(lenStr) == 0) {
                        System.out.print("Введите корректную длину: ");
                        lenStr = scanner.nextLine();
                    }
                    hangman.chooseWord();

                    while (hangman.getLife() > 0 && hangman.getUnknownCharacterCount() > 0) {
                        System.out.println("Слово: " + hangman.getWord());
                        System.out.print("Введите букву: ");
                        char letter = scanner.nextLine().charAt(0);
                        hangman.checkWord(letter);
                    }

                    hangman.gameEnd();
                    System.out.println("Игра 'Виселица' завершена.");
                    System.out.println("Набрано очков: " + hangman.getScoore());
                    System.out.println("Нажмите ENTER для возврата в меню...");
                    player.setGameType("Hangman");
                    db.savePlayer(player);
                    hangman.setPlayer(player);
                    db.saveHangman(hangman);
                    scanner.nextLine();
                    break;
                }

                case "3": {
                    System.out.print("Введите число сетов (от 1 до 99): ");
                    String setsStr = scanner.nextLine();
                    // Здесь можно добавить проверку и сохранение
                    break;
                }

                case "4": {
                    System.out.println("1. Game");
                    System.out.println("2. Player");
                    System.out.println("3. Hangman");
                    System.out.println("4. Treasure");
                    System.out.print("Выберите базу данных: ");
                    String choice = scanner.nextLine();
                    String table = null;

                    switch (choice) {
                        case "1":
                            table = "Game";
                            break;
                        case "2":
                            table = "Player";
                            break;
                        case "3":
                            table = "Hangman";
                            break;
                        case "4":
                            table = "Treasure";
                            break;
                        default:
                            System.out.println("Неверный выбор таблицы.");
                            break;
                    }

                    if (table != null) {
                        databaseMenu(table, db, scanner, player);
                    }
                    break;
                }

                case "5": {
                    System.exit(0);
                    break;
                }

                default:
                    System.out.println("Неверный выбор. Попробуйте снова.");
            }
        }
    }
}
