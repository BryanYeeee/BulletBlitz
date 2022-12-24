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
        setValues(1,10,10,-1,1,750);
        
        levelLabel = new Label("Level: " + level,55);
        addObject(levelLabel, 105, 350);
        
        score = 0;
        scoreLabel = new Label(score, 80);
        addObject(scoreLabel,50,50); 
        
        Hero hero = new Hero();
        addObject(hero, 400,400);
        
        testLabel = new Label(1, 55);
        addObject(testLabel,400,200); 
        testLabel2 = new Label(1, 55);
        addObject(testLabel2,200,200); 
        sendBullet();
    }
    
    public void setValues(int waveType, int waveLength, int bulletsLeft, int bulletDirection, int bulletSpeed, int spawnSpeed) {
        this.waveType = waveType;
        this.waveLength = waveLength;
        this.bulletsLeft = bulletsLeft;
        this.bulletDirection = bulletDirection;
        this.bulletSpeed = bulletSpeed;
        this.spawnSpeed = spawnSpeed;
    }
    
    public void increaseScore() {
        score++;
        scoreLabel.setValue(score);
        bulletsLeft--;
        if (bulletsLeft == 0) {
            level++;
            levelLabel.setValue("Level: " + level);
            setValues(Greenfoot.getRandomNumber(3)+1,10,10,-1,bulletSpeed+1,750);
        }
    }
    
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
        Bullet newBullet = new Bullet(possibleDirections[direction], speed, spawnSpeed);
        addObject(newBullet, 0,0);
        newBullet.setSpawnLocation();
    }
}
