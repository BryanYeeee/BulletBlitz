import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class Hero here.
 * 
 * @author Bryan Yee
 * @version 12/21/2022
 */
public class Hero extends Actor
{
    String facing = "s";
    /**
     * Act - do whatever the Hero wants to do. This method is called whenever
     * the 'Act' or 'Run' button gets pressed in the environment.
     */
    public void act() 
    {
        control();
    } 
    
    //Method to allow user to control which direction the hero is facing
    public void control() {
        String key = Greenfoot.getKey();
        if(key==null)return;
        switch(key) {
         case "w":
         case "up":
            setRotation(270);
            break;
         case "a":
         case "left":
            setRotation(180);
            break;
         case "s":
         case "down":
            setRotation(90);
            break;
         case "d":
         case "right":
            setRotation(0);
            break;
        }
    }
}
