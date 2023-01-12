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
    public EndScreen()
    {    
        super(800, 800, 1); 
        Label loseLabel = new Label("YOU LOSE . . ." , 100);
        addObject(loseLabel, 400,400);
    }
}
