package com.example.entity;

import java.lang.reflect.*;
import java.util.ArrayList;
import java.util.Collections;


import org.hibernate.Session;
import java.lang.reflect.Field;

import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;

import java.util.List;

public class DatabaseManager {
    private SessionFactory sessionFactory;

    public DatabaseManager() {
        sessionFactory = new Configuration().configure().buildSessionFactory();
    }

    public void saveGame(Game game) {
        Transaction transaction = null;
        try (Session session = sessionFactory.openSession()) {
            transaction = session.beginTransaction();
            
            // При сохранении игры обязательно должен быть установлен player
            if (game.getPlayer() == null) {
                throw new IllegalStateException("Game must have a Player assigned before saving");
            }
            
            
            session.save(game);
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) transaction.rollback();
            e.printStackTrace();
        }
    }

    
    public void savePlayer(Player player) {
        Transaction transaction = null;
        try (Session session = sessionFactory.openSession()) {
            transaction = session.beginTransaction();
            session.saveOrUpdate(player);
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) transaction.rollback();
            e.printStackTrace();
        }
    }

    
    public void deletePlayer(Long id) {
        Transaction transaction = null;
        try (Session session = sessionFactory.openSession()) {
            transaction = session.beginTransaction();
            
            Player player = session.get(Player.class, id);
            if (player != null) {
                session.delete(player); // Удаляем объект из сессии
            }
            
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) transaction.rollback();
            e.printStackTrace();
        }
    }
    
    public void updatePlayer(Long id, String columnName, String newValueStr) {
        Transaction transaction = null;
        try (Session session = sessionFactory.openSession()) {
            transaction = session.beginTransaction();
            
            Player player = session.get(Player.class, id);
            if (player != null) {
				try 
				{
					Field field = Player.class.getDeclaredField(columnName);
					field.setAccessible(true);
					
					Class<?> fieldType = field.getType();
					Object convertedValue = convertToFieldType(fieldType, newValueStr);

					field.set(player, convertedValue);
					session.update(player); // Обновляем объект в сессии
    				

	        
				} 
				catch (NoSuchFieldException e) 
				{
					System.out.println("Ошибка: поле '" + columnName + "' не найдено в классе Player.");
					return;
				}
            			}
            else
            { 
            				System.out.println("Запись Player с id=" + id + " не найдена.");
                				return;
                
            }
            
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) transaction.rollback();
            e.printStackTrace();
        }
    }

    private Object convertToFieldType(Class<?> type, String value) {
        if (type == String.class) return value;
        if (type == int.class || type == Integer.class) return Integer.parseInt(value);
        if (type == long.class || type == Long.class) return Long.parseLong(value);
        if (type == boolean.class || type == Boolean.class) return Boolean.parseBoolean(value);
        if (type == double.class || type == Double.class) return Double.parseDouble(value);
        // Добавь другие типы по необходимости
        throw new IllegalArgumentException("Неизвестный тип поля: " + type);
    }
    
    public Player getPlayer(Long id) {
        try (Session session = sessionFactory.openSession()) {
            return session.get(Player.class, id);
        }
    }
    
    public List<Player> getAllPlayer() {
        try (Session session = sessionFactory.openSession()) {
            return session.createQuery("from Player", Player.class).list();
        }
    }
    
    public void deleteGame(Long id) {
        Transaction transaction = null;
        try (Session session = sessionFactory.openSession()) {
            transaction = session.beginTransaction();
            
            Game game = session.get(Game.class, id);
            if (game != null) {
                session.delete(game); // Удаляем объект из сессии
            }
            
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) transaction.rollback();
            e.printStackTrace();
        }
    }
    
    public void deleteTreasure(Long id) {
        Transaction transaction = null;
        try (Session session = sessionFactory.openSession()) {
            transaction = session.beginTransaction();
            
            Treasure treasure = session.get(Treasure.class, id);
            if (treasure != null) {
                session.delete(treasure); // Удаляем объект из сессии
            }
            
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) transaction.rollback();
            e.printStackTrace();
        }
    }
    
    
    public void deleteHangman(Long id) {
        Transaction transaction = null;
        try (Session session = sessionFactory.openSession()) {
            transaction = session.beginTransaction();
            
            Hangman hangman = session.get(Hangman.class, id);
            if (hangman != null) {
                session.delete(hangman); // Удаляем объект из сессии
            }
            
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) transaction.rollback();
            e.printStackTrace();
        }
    }
    
    public void updateGame(Long id, String columnName, String newValueStr) {
        Transaction transaction = null;
        try (Session session = sessionFactory.openSession()) {
            transaction = session.beginTransaction();
            
            Game game = session.get(Game.class, id);
            if (game != null) {
				try 
				{
					Field field = Game.class.getDeclaredField(columnName);
					field.setAccessible(true);
					
					Class<?> fieldType = field.getType();
					Object convertedValue = convertToFieldType(fieldType, newValueStr);

					field.set(game, convertedValue);
					session.update(game); // Обновляем объект в сессии
    				
		
	        
				} 
				catch (NoSuchFieldException e) 
				{
					System.out.println("Ошибка: поле '" + columnName + "' не найдено в классе Game.");
					return;
				}
            			}
            else
            {
                				System.out.println("Запись Game с id=" + id + " не найдена.");
                				return;
            }
            
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) transaction.rollback();
            e.printStackTrace();
        }
    }
    
    public void updateTreasure(Long id, String columnName, String newValueStr) {
        Transaction transaction = null;
        try (Session session = sessionFactory.openSession()) {
            transaction = session.beginTransaction();

            Treasure existing = session.get(Treasure.class, id);
            if (existing != null) {
				try 
				{
					Field field = Treasure.class.getDeclaredField(columnName);
					field.setAccessible(true);
					
					Class<?> fieldType = field.getType();
					Object convertedValue = convertToFieldType(fieldType, newValueStr);

					field.set(existing, convertedValue);
					session.update(existing); // Обновляем объект в сессии
    				
	
	        
				} 
				catch (NoSuchFieldException e) 
				{
					System.out.println("Ошибка: поле '" + columnName + "' не найдено в классе Treasure.");
					return;
				}
            }
            else
            {
            				System.out.println("Запись Treasure с id=" + id + " не найдена.");
            				return;
            }

            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) transaction.rollback();
            e.printStackTrace();
        }
    }

    
    public void updateHangman(Long id, String columnName, String newValueStr) {
        Transaction transaction = null;
        try (Session session = sessionFactory.openSession()) {
            transaction = session.beginTransaction();

            Hangman existing = session.get(Hangman.class, id);
            if (existing != null) {
            				try 
            				{
            					Field field = Hangman.class.getDeclaredField(columnName);
            					field.setAccessible(true);
            					
            					Class<?> fieldType = field.getType();
            					Object convertedValue = convertToFieldType(fieldType, newValueStr);

            					field.set(existing, convertedValue);
            					session.update(existing); // Обновляем объект в сессии
                				

         	        
            				} 
            				catch (NoSuchFieldException e) 
            				{
            					System.out.println("Ошибка: поле '" + columnName + "' не найдено в классе Hangman.");
            					return;
            				}
            }
            else
            {
            				System.out.println("Запись Hangman с id=" + id + " не найдена.");
            				return;
            }

            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) transaction.rollback();
            e.printStackTrace();
        }
    }
    


    public List<Game> getAllGame() {
        try (Session session = sessionFactory.openSession()) {
            return session.createQuery("from Game", Game.class).list();
        }
    }


    public void saveTreasure(Treasure treasure) {
	    	Transaction transaction = null;
	    	Session session = null;
	
	    	try {
						    	    session = sessionFactory.openSession();
						    	    transaction = session.beginTransaction();
						
						    	    // любые действия с БД
						    	    session.save(treasure);
						
						    	    transaction.commit();
	    	} catch (Exception e) {
	    		if (transaction != null && transaction.getStatus().canRollback()) {
	    			transaction.rollback();
					    	    }
					    	    	e.printStackTrace();
	    	} finally {
	    		if (session != null && session.isOpen()) {
	    			session.close(); // Закрываем сессию здесь, после всех операций
	    		}
	    	}
    }

    public Treasure getTreasure(Long id) {
        try (Session session = sessionFactory.openSession()) {
            return session.get(Treasure.class, id);
        }
    }

    public List<Treasure> getAllTreasure() {
        try (Session session = sessionFactory.openSession()) {
            return session.createQuery("from Treasure", Treasure.class).list();
        }
        
    }
    

    public void saveHangman(Hangman hangman) {
        Transaction transaction = null;
        try (Session session = sessionFactory.openSession()) {
            transaction = session.beginTransaction();
            session.save(hangman);
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) transaction.rollback();
            e.printStackTrace();
        }
    }

    public Hangman getHangman(Long id) {
        try (Session session = sessionFactory.openSession()) {
            return session.get(Hangman.class, id);
        }
    }
       

    public void FindGamesByPlayerId(Long playerId) {
        try (Session session = sessionFactory.openSession()) {
            Player player = session.get(Player.class, playerId);

            if (player != null) {
                List<Game> games = player.getGames(); 
                for (Game game : games) {
                    System.out.println(game);
                }
            } else {
                System.out.println("Игрок не найден.");
            }
        } 
    }
    public Game getGame(Long id) {
        try (Session session = sessionFactory.openSession()) 
        {
            return session.get(Game.class, id);
        }
    }
    
    public List<Hangman> getAllHangman() {
        try (Session session = sessionFactory.openSession()) {
            return session.createQuery("from Hangman", Hangman.class).list();
        }
    }


    public void close() {
        sessionFactory.close();
    }
}