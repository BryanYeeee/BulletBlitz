import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class Game here.
 * 
 * @author Bryan Yee
 * @version 12/20/2022
 */
public class Game extends World
{
    int level;
    int wave;
    
    /**
     * Constructor for objects of class Game.
     * 
     */
    public Game()
    {    
        // Create a new world with 600x400 cells with a cell size of 1x1 pixels.
        super(800, 800, 1); 
        Hero hero = new Hero();
        addObject(hero, 400,400);
        
        spawnBullet();
    }
    
    public void spawnBullet() {
        Bullet bullet = new Bullet();
        addObject(bullet, 400, 0);
    }
}
