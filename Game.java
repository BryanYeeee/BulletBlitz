import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class Game here.
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
    
    public Label testLabel;
    public Label testLabel2;
    SimpleTimer spawnTimer = new SimpleTimer();
    /**
     * Constructor for objects of class Game.
     */
    public Game()
    {    
        // Create a new world with 800x800 cells with a cell size of 1x1 pixels.
        super(800, 800, 1); 
        level = 0;
        waveType = 2;
        bulletSpeed = 1;
        spawnSpeed = 1000;
        
        
        levelLabel = new Label("Level: " + level,50);
        addObject(levelLabel, 150, 650);
        
        score = 0;
        scoreLabel = new Label(score, 100);
        addObject(scoreLabel,150,150); 
        
        healthLabel = new Label(Hero.fullHealth, 100);
        addObject(healthLabel,650,150); 
        
        Hero hero = new Hero();
        addObject(hero, 400,400);
        
        testLabel = new Label(1, 35);
        addObject(testLabel,400,500); 
        testLabel2 = new Label(bulletsLeft, 55);
        addObject(testLabel2,650,650); 
        
        nextWave();
    }
    
    //Increase score, if wave is cleared then send next wave
    public void increaseScore() {
        score++;
        scoreLabel.setValue(score);
        bulletsLeft--;
        testLabel2.setValue(bulletsLeft);
        if (bulletsLeft == 0) {
            nextWave();
        }
    }
    
    //Damages player
    public void takeDamage(int damageTaken) {
        Hero hero = getObjects(Hero.class).get(0);
        if (hero.changeHealth(damageTaken*-1)) { //Damage taken will reduce health, so change value to negative
            //Game Over
        }
        healthLabel.setValue(hero.getHealth());
        bulletsLeft--;
        testLabel2.setValue(bulletsLeft);
        if (bulletsLeft == 0) {
            nextWave();
        }
    }
    
    //Send next wave based on bullet settings
    public void nextWave() {
        level++;
        levelLabel.setValue("Level: " + level);
        this.waveType = level == 1 ? 2 : Greenfoot.getRandomNumber(3)+1; //Set bullet spawn mechanics for this wave, unless it is first level
        this.waveLength = 10;
        this.bulletsLeft = 10;
        this.bulletDirection = -1;
        this.bulletSpeed += bulletSpeed == 10 ? 0 : 1; //Increase bullet speed by 1, capping at 15
        testLabel2.setValue(spawnSpeed + " " + spawnSpeed*0.95);
        this.spawnSpeed = spawnSpeed <= 500 ? 500 : (int) (spawnSpeed*0.95); //Decrease spawn speed of bullet by 5%, capping at 450ms
        sendBullet();
    }
    
    //Return true if all bullets have been sent
    public boolean finishWave() {
        return waveLength == 0;
    }
    
    //Method which will control the bullets spawn mechanics before adding to game world (Like a middleware)
    public void sendBullet() {
        testLabel.setValue(bulletsLeft + " " + waveLength + " " + finishWave());
        switch (waveType) {
             case 1:
                spawnBullet(Greenfoot.getRandomNumber(4), bulletSpeed,spawnSpeed);
                break;
             case 2:
                if(bulletDirection == -1) {
                    bulletDirection = Greenfoot.getRandomNumber(4);
                }
                spawnBullet(bulletDirection, bulletSpeed,spawnSpeed);
                break;
             case 3:
                spawnBullet(Greenfoot.getRandomNumber(4), bulletSpeed/2,spawnSpeed/2);
                break;
        }
    }
    
    //Method which adds the bullet object into the game world
    public void spawnBullet(int direction, int speed, int spawnSpeed) {
        String[] possibleDirections = {"w","a","s","d"};
        waveLength--;
        Bullet newBullet = new Bullet(possibleDirections[direction], speed, spawnSpeed,1);
        addObject(newBullet, 0,0);
        newBullet.setSpawnLocation();
    }
}
