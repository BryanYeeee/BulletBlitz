import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class EndScreen here.
 * 
 * @author Bryan Yee 
 * @version 1/12/2023
 */
public class EndScreen extends World
{

    /**
     * Constructor for objects of class EndScreen.
     * 
     */
    public EndScreen(int score, int level)
    {    
        super(800, 800, 1); 
        Label loseLabel = new Label("YOU LOSE . . ." , 100);
        addObject(loseLabel, 400,400);
        
        Label levelLabel = new Label("Level = " + level, 50);
        addObject(levelLabel, 400,500);
        
        Label scoreLabel = new Label("Score = " + score, 50);
        addObject(scoreLabel, 400,550);
        
        Label retryLabel = new Label("Press <space> to restart", 35);
        addObject(retryLabel, 400,600);
    }
    
    public void act() {
        String key = Greenfoot.getKey();
        if(key==null)return;
        if(key.equals("space")) {
            Game gameWorld = new Game();
            Greenfoot.setWorld(gameWorld);
        }
    }
}
