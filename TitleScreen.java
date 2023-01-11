import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Title screen to the game
 * 
 * @author Bryan Yee 
 * @version 1/10/2023
 */
public class TitleScreen extends World
{

    /**
     * Constructor for objects of class TitleScreen.
     * 
     */
    public TitleScreen()
    {    
        super(800, 800, 1); 
    }
    
    public void act() {
        String key = Greenfoot.getKey();
        if(key==null)return;
        if(key.equals("space")) {
            Game gameWorld = new Game();
            Greenfoot.setWorld(gameWorld);
        }
        if(key.equals("w")) {
            Instructions instructions = new Instructions();
            Greenfoot.setWorld(instructions);
        }
    }
}
