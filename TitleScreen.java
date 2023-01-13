import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Title screen to the game
 * 
 * @author Bryan Yee 
 * @version 1/10/2023
 */
public class TitleScreen extends World
{

    /**
     * Constructor for objects of class TitleScreen.
     * 
     */
    public TitleScreen()
    {    
        super(800, 800, 1); 
        prepare();
    }

    public void act() {
        String key = Greenfoot.getKey();
        if(key==null)return;
        if(key.equals("space")) {
            Game gameWorld = new Game();
            Greenfoot.setWorld(gameWorld);
        }
        if(key.equals("w")) {
            Instructions instructions = new Instructions();
            Greenfoot.setWorld(instructions);
        }
    }

    /**
     * Prepare the world for the start of the program.
     * That is: create the initial objects and add them to the world.
     */
    private void prepare()
    {
        Label titleLabel = new Label("the game" , 100);
        addObject(titleLabel, 400,400);
        
        Label startLabel = new Label("<space> to Start",40);
        addObject(startLabel, 175, 675);
        
        Label helpLabel = new Label("<W> for help", 40);
        addObject(helpLabel,625,675); 
        
    }
}
