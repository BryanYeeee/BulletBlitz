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
    int waveType;
    int waveLength;
    int bulletsLeft;
    int bulletDirection;
    int bulletSpeed;
    int spawnSpeed;
    
    Label scoreLabel;
    int score;
    
    public Label testLabel;
    SimpleTimer spawnTimer = new SimpleTimer();
    /**
     * Constructor for objects of class Game.
     */
    public Game()
    {    
        // Create a new world with 800x800 cells with a cell size of 1x1 pixels.
        super(800, 800, 1); 
        level = 0;
        setValues(1,10,0,-1,1,750);
        
        levelLabel = new Label("Level: " + level,55);
        addObject(levelLabel, 105, 350);
        
        score = 0;
        scoreLabel = new Label(score, 80);
        addObject(scoreLabel,50,50); 
        
        Hero hero = new Hero();
        addObject(hero, 400,400);
        
        testLabel = new Label(spawnTimer.millisElapsed(), 55);
        addObject(testLabel,50,200); 
        sendWave();
    }
    
    public void setValues(int waveType, int waveLength, int bulletsLeft, int bulletDirection, int bulletSpeed, int spawnSpeed) {
        this.waveType = waveType;
        this.waveLength = waveLength;
        this.bulletsLeft = bulletsLeft;
        this.bulletDirection = bulletDirection;
        this.bulletSpeed = bulletSpeed;
        this.spawnSpeed = spawnSpeed;
    }
    
    public void sendWave() {
        if (bulletsLeft == 0) {
            level++;
            levelLabel.setValue("Level: " + level);
            setValues(Greenfoot.getRandomNumber(3)+1,10,0,-1,bulletSpeed+1,750);
            testLabel.setValue(waveType);
            sendWave();
            return;
        }
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
    
    public void increaseScore() {
        score++;
        scoreLabel.setValue(score);
        bulletsLeft--;
    }
    
    public void spawnBullet(int direction, int speed, int spawnSpeed) {
        Bullet bullet;
        switch(direction) {
            case 0:
                bullet = new Bullet("w",speed,spawnSpeed,wave == 1);
                addObject(bullet, 400, 0);
                break;
            case 1:
                bullet = new Bullet("a",speed,spawnSpeed,wave == 1);
                addObject(bullet, 0, 400);
                break;
            case 2:
                bullet = new Bullet("s",speed,spawnSpeed,wave == 1);
                addObject(bullet, 400, 800);
                break;
            case 3:
                bullet = new Bullet("d",speed,spawnSpeed,wave == 1);
                addObject(bullet, 800, 400);
                break;
        }
    }
}
