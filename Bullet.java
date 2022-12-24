import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class Bullet here.
 * 
 * @author Bryan Yee 
 * @version 12/21/2022
 */
public class Bullet extends Actor
{
    /**
     * Act - do whatever the Bullet wants to do. This method is called whenever
     * the 'Act' or 'Run' button gets pressed in the environment.
     */
    String direction;
    int speed;
    int spawnSpeed;
    
    boolean firstAct = true;
    boolean spawnNext = false;
    SimpleTimer spawnTimer = new SimpleTimer();
    public Bullet(String direction, int speed, int spawnSpeed, boolean spawnNext) {
        this.direction = direction;
        this.speed = speed;
        this.spawnSpeed = spawnSpeed;
        this.spawnNext = spawnNext;
    }
    public void act() 
    {
        if(firstAct) {
         firstAct = false;
         spawnTimer.mark();
        }
        
        move();
        contact();
        
        if(!spawnNext && spawnTimer.millisElapsed() >= spawnSpeed) {
            spawnTimer.mark();
            spawnNext = true;
            ((Game) getWorld()).sendWave();
        }
    }    
    public void contact() {
     if(isTouching(Hero.class)) { 
            Game game = ((Game) getWorld());
            game.increaseScore();
            if(!spawnNext) {
                game.sendWave();
            }
      }
    }
    public void move() {
        switch(direction) {
            case "w":
                setLocation(getX(), getY()+speed);
                break;
            case "a":
                setLocation(getX()+speed, getY());
                break;
            case "s":
                setLocation(getX(), getY()-speed);
                break;
            case "d":
                setLocation(getX()-speed, getY());
                break;
        }
        
    }
}
