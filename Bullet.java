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
    int damage;
    
    boolean firstAct = true;
    boolean spawnNext = false;
    SimpleTimer spawnTimer = new SimpleTimer();
    
    public Bullet(String direction, int speed, int spawnSpeed, int damage) {
        this.direction = direction;
        this.speed = speed;
        this.spawnSpeed = spawnSpeed;
        this.damage = damage;
    }
    
    public void act() 
    {
        if(firstAct) {
         firstAct = false;
         spawnTimer.mark();
        }
        
        if(!spawnNext && !((Game) getWorld()).finishWave() && spawnTimer.millisElapsed() >= spawnSpeed) {
            spawnTimer.mark();
            spawnNext = true;
            ((Game) getWorld()).sendBullet();
        }
        
        move();
        contact();
    }   
    
    public void contact() {
     if(isTouching(Hero.class)) { 
            if(!spawnNext && !((Game) getWorld()).finishWave()) {
                ((Game) getWorld()).sendBullet();
            }
            Hero hero = (Hero) getOneIntersectingObject(Hero.class);
            if(hero.getFacingDirection().equals(direction)) {
                ((Game) getWorld()).increaseScore();
            } else {
                ((Game) getWorld()).takeDamage(damage);
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
    
    public void setSpawnLocation() {
        switch(direction) {
            case "w":
                setLocation(400, 0);
                break;
            case "a":
                setLocation(0, 400);
                break;
            case "s":
                setLocation(400, 800);
                break;
            case "d":
                setLocation(800, 400);
                break;
        }
    }
}
