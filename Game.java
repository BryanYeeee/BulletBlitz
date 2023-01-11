import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Main screen which the game will be played on
 * 
 * @author Bryan Yee
 * @version 12/20/2022
 */
public class Game extends World
{
    Label levelLabel;
    int level;
    int waveType; //Controls the spawning mechanics of the bullets
    int waveLength; //How many more bullets need to spawned
    int bulletsLeft; //How many more bullets the hero needs to defend before spawning next wave
    int bulletDirection;
    int bulletSpeed;
    int spawnSpeed;
    
    Label healthLabel;
    Label scoreLabel;
    int score;
    
    SimpleTimer spawnTimer = new SimpleTimer();
    /**
     * Constructor for objects of class Game.
     */
    public Game()
    {    
        super(800, 800, 1); 
        level = 0;
        waveType = 2;
        bulletSpeed = 3;
        spawnSpeed = 750;

        Hero hero = new Hero();
        addObject(hero, 400,400);
        
        healthLabel = new Label("HP: " + hero.getHealth(), 50);
        addObject(healthLabel,650,125); 
        
        levelLabel = new Label("Level: " + level,50);
        addObject(levelLabel, 175, 675);
        
        score = 0;
        scoreLabel = new Label(score, 100);
        addObject(scoreLabel,150,125);
        
        nextWave(); //start the game
    }
    
    /**
     * Increase score, if wave is cleared then send next wave.
     */
    public void increaseScore() {
        score++;
        scoreLabel.setValue(score);
        bulletsLeft--;
        if (bulletsLeft == 0) {
            nextWave();
        }
    }
    
    /**
     * Damages the player.
     * 
     * @param amount of damage the player will take
     */
    public void takeDamage(int damageTaken) {
        Hero hero = getObjects(Hero.class).get(0);
        if (hero.changeHealth(damageTaken*-1)) { //Damage taken will reduce health, so change value to negative
            //Game Over
        }
        healthLabel.setValue("HP: " + hero.getHealth());
        bulletsLeft--;
        if (bulletsLeft == 0) {
            nextWave();
        }
    }
    
    /**
     * Send next wave based on bullet settings
     */
    public void nextWave() {
        level++;
        levelLabel.setValue("Level: " + level);
        this.waveType = level == 1 ? 2 : Greenfoot.getRandomNumber(3)+1; //Set bullet spawn mechanics for this wave, unless it is first level
        this.waveLength = 10;
        this.bulletsLeft = 10;
        this.bulletDirection = -1; //Direction the bullets will spawn if the waveType equals 2
        this.bulletSpeed += bulletSpeed == 10 ? 0 : 1; //Increase bullet speed by 1, capping at 10
        this.spawnSpeed = spawnSpeed <= 500 ? 500 : (int) (spawnSpeed*0.95); //Decrease spawn speed of bullet by 5%, capping at 500ms
        sendBullet();
    }
    
    /**
     * Return true if all bullets have been sent.
     */
    public boolean finishWave() {
        return waveLength == 0;
    }
    
    /**
     * Method which will control the bullets spawn mechanics before adding to game world (Like a middleware).
     */
    public void sendBullet() {
        switch (waveType) {
             case 1: //Random direction wave
                spawnBullet(Greenfoot.getRandomNumber(4), bulletSpeed,spawnSpeed);
                break;
             case 2: //Set direction wave
                if(bulletDirection == -1) {
                    bulletDirection = Greenfoot.getRandomNumber(4);
                }
                spawnBullet(bulletDirection, bulletSpeed,spawnSpeed);
                break;
             case 3: //Slower bullets wave
                spawnBullet(Greenfoot.getRandomNumber(4), bulletSpeed/2,spawnSpeed/2);
                break;
        }
    }
    
    /**
     * Method which adds the bullet object into the game world.
     * 
     * @param direction that the spawned bullet will face
     * @param move speed of the bullet
     * @param number of milliseconds before the next bullet will spawn
     */
    public void spawnBullet(int direction, int speed, int spawnSpeed) {
        String[] possibleDirections = {"w","a","s","d"};
        waveLength--;
        Bullet newBullet = new Bullet(possibleDirections[direction], speed, spawnSpeed,1);
        addObject(newBullet, 0,0);
        newBullet.setSpawnLocation();
    }
}
