import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Set of instructions for the player to understand how to play
 * 
 * @author Bryan Yee
 * @version 1/11/2023
 */
public class Instructions extends World
{
    Label[] instructions = new Label[9];
    Label continueLabel = new Label("Press <space> to continue", 35);
    int currentInstruction;
    /**
     * Constructor for objects of class Instructions 
     */
    public Instructions()
    {    
        super(800, 800, 1);
        instructions[0] = new Label("1. The green arrows will spawn \nfrom one of the 4 entrances and \nhead towards the player in the middle", 50);
        instructions[1] = new Label("2. The player must block \nthe arrows using the shield.", 50);
        instructions[2] = new Label("3. Use <W> <A> <S> <D> \nto control which direction \nthe shield is blocking", 50);
        instructions[3] = new Label("4. If the player fails to block an arrow \nthe player's health will decrease by 1 \n(health in the top right corner)", 50);
        instructions[4] = new Label("5. When the player blocks an arrow, \nthe player's score will increase \n(score in the top left corner)", 50);
        instructions[5] = new Label("6. Each level will consist of 10 arrows.", 50); 
        instructions[6] = new Label("7. Once all arrows reach the player, \nthe next level will start \n(level in bottom left)", 50);
        instructions[7] = new Label("8. Every level will have different \nspawning mechanics and increasing \nspeed/difficulty", 50);
        instructions[8] = new Label("Try to get the most score!", 50);
        
        Hero hero = new Hero();
        addObject(hero, 400,400);
        
        addObject(instructions[0], 400, 650);
        addObject(continueLabel, 400, 750);
        currentInstruction = 1;        
    }
    
    /**
     * The next instruction will show. When all instructions have been displayed, it will return to title screen.
     */
    public void nextInstruction() {
        if(currentInstruction == 9) {
            TitleScreen title = new TitleScreen();
            Greenfoot.setWorld(title);
            return;
        }
        removeObjects(getObjects(Label.class));
        addObject(instructions[currentInstruction],400,650);
        addObject(continueLabel, 400, 750);
        currentInstruction++;
    }
}
