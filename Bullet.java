import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * The projectile that the character will defend from
 * 
 * @author Bryan Yee 
 * @version 12/21/2022
 */
public class Bullet extends Actor
{
    GreenfootSound contactSound = new GreenfootSound("clink.mp3");
    GreenfootSound damageSound = new GreenfootSound("bonk.mp3");
    String direction;
    int speed;
    int spawnSpeed;
    int damage;
    
    boolean firstAct = true;
    boolean spawnNext = false;
    SimpleTimer spawnTimer = new SimpleTimer();
    
    GreenfootImage[] animationFrames = new GreenfootImage[17];
    int currentFrame = 0;
    SimpleTimer animTimer = new SimpleTimer();
    
    /**
     * Create a bullet
     * 
     * @param string direction the bullet is spawning from (w, a, s, or d)
     * @param speed at which bullet will move
     * @param number of milliseconds before the next bullet will spawn
     * @param amount of damage the bullet will deal
     */
    public Bullet(String direction, int speed, int spawnSpeed, int damage) {
        this.direction = direction;
        this.speed = speed;
        this.spawnSpeed = spawnSpeed; //number of milliseconds before the next bullet will spawn
        this.damage = damage;
        
        //Load Images/Frames
        for (int i = 0; i < 17; i++) {
            animationFrames[i] = new GreenfootImage("images/bullet"+direction.toUpperCase()+"/bullet"+i+".png");
        }
    }
    
    /**
     * Act - do whatever the Bullet wants to do. This method is called whenever
     * the 'Act' or 'Run' button gets pressed in the environment.
     */
    public void act() 
    {
        if(firstAct) {
         firstAct = false;
         spawnTimer.mark();
        }
        animation();
        spawnBullet();
        move();
        contact();
    }   
    
    /**
     * Method to check when to spawn the next bullet.
     *  1. If the next bullet has already spawned, it will not spawn (spawnNext)
     *  2. If the bullet is the last bullet in the wave, it will not spawn
     *  3. When the spawnTimer hits the spawnSpeed it will spawn
     */
    public void spawnBullet() {
        if(!spawnNext && !((Game) getWorld()).finishWave() && spawnTimer.millisElapsed() >= spawnSpeed) {
            spawnTimer.mark();
            spawnNext = true;
            ((Game) getWorld()).sendBullet();
        }
    }
    
    public void contact() {
     if(isTouching(Hero.class)) { 
            //If the bullet touches the hero before the time reaches the spawn speed, then another bullet with spawn
            if(!spawnNext && !((Game) getWorld()).finishWave()) {
                ((Game) getWorld()).sendBullet();
            }
            
            Hero hero = (Hero) getOneIntersectingObject(Hero.class);
            //If the hero is facing the right direction then they will get score else they will take damage
            if(hero.getFacingDirection().equals(direction)) {
                contactSound.play();
                ((Game) getWorld()).increaseScore();
            } else {
                damageSound.play();
                ((Game) getWorld()).takeDamage(damage);
            }
      }
    }
    
    public void animation() {
        if(animTimer.millisElapsed() < 25) return;
        animTimer.mark();
        
        setImage(animationFrames[currentFrame]);
        currentFrame = (currentFrame+1) % animationFrames.length;
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
