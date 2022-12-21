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
    int wave;
    
    Label scoreLabel;
    int score;
    
    Label testLabel;
    SimpleTimer spawnTimer = new SimpleTimer();
    /**
     * Constructor for objects of class Game.
     * 
     */
    public Game()
    {    
        // Create a new world with 600x400 cells with a cell size of 1x1 pixels.
        super(800, 800, 1); 
        level = 1;
        wave = 0;
        levelLabel = new Label("Level: " + level,55);
        addObject(levelLabel, 105, 350);
        
        score = 0;
        scoreLabel = new Label(score, 80);
        addObject(scoreLabel,50,50); 
        
        Hero hero = new Hero();
        addObject(hero, 400,400);
        
        testLabel = new Label(spawnTimer.millisElapsed(), 55);
        addObject(testLabel,50,200); 
        spawnBullet();
    }
    
    public void sendWave() {
        while(wave > 0) {
            if(spawnTimer.millisElapsed() >= 1000){
            testLabel.setValue(spawnTimer.millisElapsed());
            spawnTimer.mark();
            
            wave--;
            levelLabel.setValue("Level: " + wave);
            spawnBullet();
        }
        }
        
    }
    
    public void increaseScore() {
        score++;
        scoreLabel.setValue(score);
        
        if(wave == 0) {
            wave= 5;
            spawnTimer.mark();
            sendWave();
        }
    }
    
    public void spawnBullet() {
        Bullet bullet = new Bullet();
        addObject(bullet, 400, 0);
    }
}
