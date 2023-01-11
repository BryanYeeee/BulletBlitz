import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)
import java.util.List;

/**
 * The controllable player character
 * 
 * @author Bryan Yee
 * @version 12/21/2022
 */
public class Hero extends Actor
{
    int health = 100;
    String facing = "s";
    GreenfootImage[] heroSprites = new GreenfootImage[4];
    
    public Hero() {
        heroSprites[0] = new GreenfootImage("images/Hero_w.png");
        heroSprites[1] = new GreenfootImage("images/Hero_a.png");
        heroSprites[2] = new GreenfootImage("images/Hero_s.png");
        heroSprites[3] = new GreenfootImage("images/Hero_d.png");
        setImage(heroSprites[2]);
    }
    
    /**
     * Returns true if character dies from health change
     */
    public boolean changeHealth(int change) {
        health += change;
        return health < 0;
    }
    
    public int getHealth() {
        return health;
    }
    
    public String getFacingDirection() {
        return facing;
    }
    
    /**
     * Act - do whatever the Hero wants to do. This method is called whenever
     * the 'Act' or 'Run' button gets pressed in the environment.
     */
    public void act() 
    {
        control();
        contact();
    } 
    
    public void contact() {
     if(isTouching(Bullet.class)) { 
            removeTouching(Bullet.class);
      }
    }
    
    /**
     * Method to allow user to control which direction the hero is facing.
     */
    public void control() {
        String key = Greenfoot.getKey();
        if(key==null)return;
        switch(key) {
         case "w":
         case "up":
            facing = "w";
            setImage(heroSprites[0]);
            break;
         case "a":
         case "left":
            facing = "a";
            setImage(heroSprites[1]);
            break;
         case "s":
         case "down":
            facing = "s";
            setImage(heroSprites[2]);
            break;
         case "d":
         case "right":
            facing = "d";
            setImage(heroSprites[3]);
            break;
         //Greenfoot.getKey() cannot be used twice at the same time, so the instruction page control will be placed here
         case "space":
            if(getWorld() instanceof Instructions) {
                ((Instructions) getWorld()).nextInstruction();
            }
        }
    }
}
