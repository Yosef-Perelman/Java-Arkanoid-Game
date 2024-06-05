// ID: 206344814

package gameactions;

import biuoop.DrawSurface;
import biuoop.KeyboardSensor;
import data.GameEnvironment;
import data.ScoreIndicator;
import data.SpriteCollection;
import interfaces.Animation;
import interfaces.Collidable;
import interfaces.LevelInformation;
import interfaces.Sprite;
import levels.CountdownAnimation;
import primitives.Counter;
import primitives.Point;
import primitives.Rectangle;
import primitives.Velocity;
import sprites.Ball;
import sprites.Block;
import sprites.Paddle;

import java.awt.Color;
import java.util.ArrayList;
import java.util.List;

/**
 * Initialize a new game, and run it.
 */
public class GameLevel implements Animation {
    private LevelInformation level;
    private AnimationRunner runner;
    private boolean running;
    // the size of the screen
    private static final int SURFACE_WIDTH = 800;
    private static final int SURFACE_HEIGHT = 600;
    // finish level score
    private static final int FINISH_LEVEL_SCORE = 100;


    private SpriteCollection sprites;
    private GameEnvironment environment;
    private BlockRemover blockRemover;
    private BallRemover ballRemover;
    private Counter score;
    private ScoreTrackingListener scoreTrackingListener;
    private ScoreIndicator scoreIndicator;
    private KeyboardSensor keyboard;
    private boolean blocksCheck;

    /**
     * Constructor.
     *
     * @param blocksCheck a boolean variable that check if the blocks over
     * @param keyboardSensor a keyboard sensor
     * @param level the level that run
     * @param runner an animation runner
     * @param score the score
     */
    public GameLevel(LevelInformation level, AnimationRunner runner,
                     KeyboardSensor keyboardSensor, Counter score, boolean blocksCheck) {
        this.level = level;
        this.runner = runner;
        this.keyboard = keyboardSensor;
        this.sprites = new SpriteCollection();
        this.environment = new GameEnvironment();
        this.score = score;
        this.blocksCheck = blocksCheck;
    }

    /**
     * @return the sprite collection of the ball
     */
    public SpriteCollection getSprites() { return this.sprites; }

    /**
     * @return the game environment
     */
    public GameEnvironment getEnvironment() { return this.environment; }

    /**
     * Add a collidable to the game environment.
     *
     * @param c a collidable
     */
    public void addCollidable(Collidable c) {
        environment.addCollidable(c);
    }

    /**
     * add a sprite to the sprite's collection of the game.
     * @param s a sprite
     */
    public void addSprite(Sprite s) {
        sprites.addSprite(s);
    }

    /**
     * Remove a given collidable from the collidables collection of the game.
     *
     * @param c a collidable.
     */
    public void removeCollidable(Collidable c) { this.environment.removeCollidable(c); }

    /**
     * Remove a given sprite from the sprites collection of the game.
     *
     * @param s a sprite.
     */
    public void removeSprite(Sprite s) { this.sprites.removeSprite(s); }

    /**
     * @return the blockRemover
     */
    public BlockRemover getBlockRemover() { return this.blockRemover; }

    /**
     * @return the ballRemover
     */
    public BallRemover getBallRemover() { return this.ballRemover; }

    /**
     * @return the blocksCheck
     */
    public boolean getBlocksCheck() { return blocksCheck; }

    /**
     * create border blocks and add them to the game.
     */
    public void createBorderBlocks() {
        Rectangle rectangle1 = new Rectangle(new Point(0, 20), 800, 20);
        Block block2 = new Block(rectangle1, Color.orange);
        Rectangle rectangle2 = new Rectangle(new Point(0, 40), 25, 580);
        Block block1 = new Block(rectangle2, Color.orange);
        Rectangle rectangle3 = new Rectangle(new Point(775, 40), 25, 580);
        Block block3 = new Block(rectangle3, Color.orange);
        Block[] borderBlocks = {block1, block2, block3};
        for (Block block : borderBlocks) {
            block.addToGame(this);
        }
        // The bottom block
        Rectangle rectangle4 = new Rectangle(new Point(25, 599), 750, 1);
        Block deathRegion = new Block(rectangle4, Color.orange);
        deathRegion.addToGame(this);
        deathRegion.addHitListener(this.ballRemover);
    }

    /**
     * create blocks and add them to the game.
     */
    public void createBlocks() {
        List<Block> blocks = new ArrayList<>(level.blocks());
        for (Block block:blocks) {
            // Add the block to the game, and add blockRemover and scoreTrackingListener
            // to the hit listener list of the block
            block.addToGame(this);
            block.addHitListener(blockRemover);
            block.addHitListener(scoreTrackingListener);
            // add the block to the game's blocks counter
        }
        this.blockRemover.setRemainingBlocks(level.numberOfBlocksToRemove());
    }

    /**
     * create two balls and add them to the game.
     */
    public void createBalls() {
        List<Velocity> velocities = new ArrayList<>(level.initialBallVelocities());
        for (Velocity v:velocities) {
            Ball ball = new Ball(400, 550, 5, Color.white);
            ball.setVelocity(v);
            ball.addToGame(this);
            ball.setGameEnvironment(this.environment);
        }
        this.ballRemover.setRemainingBalls(level.numberOfBalls());
    }

    /**
     * create paddle and add it to the game.
     */
    public void createPadlle() {
        Rectangle rectangle = new Rectangle(new Point(400 - ((double) level.paddleWidth() / 2), 570),
                level.paddleWidth(), 15);
        Paddle paddle = new Paddle(rectangle, Color.yellow, this.runner.getGui(), level.paddleSpeed(),
                level.paddleWidth());
        paddle.addToGame(this);
    }

    /**
     * @return if the game need to stop or not
     */
    public boolean shouldStop() { return !this.running; }

    /**
     * run the game frames.
     *
     * @param d a drawSurface
     */
    public void doOneFrame(DrawSurface d) {
        // Run the game
        d.setColor(Color.blue);
        d.fillRectangle(0, 0, SURFACE_WIDTH, SURFACE_HEIGHT);
        this.sprites.drawAllOn(d);
        if (this.keyboard.isPressed("p")) {
            KeyPressStoppableAnimation k = new KeyPressStoppableAnimation(
                    this.keyboard, KeyboardSensor.SPACE_KEY, new PauseScreen());
            this.runner.run(k);
        }
        this.sprites.notifyAllTimePassed();

        if (this.blockRemover.getRemainingBlocks() == 0) {
            scoreTrackingListener.addScore(FINISH_LEVEL_SCORE);
            this.running = false;
        }
        if (this.ballRemover.getRemainingBalls() == 0) {
            this.running = false;
            this.blocksCheck = false;
        }
    }

    /**
     * Initialize a new game: create Blocks, Balls and sprites.Paddle,
     * and add them to the game.
     */
    public void initialize() {
        this.addSprite(level.getBackground());
        Counter blocksCounter = new Counter();
        this.blockRemover = new BlockRemover(this, blocksCounter);
        Counter ballsCounter = new Counter();
        this.ballRemover = new BallRemover(this, ballsCounter);
        this.scoreTrackingListener = new ScoreTrackingListener(score);
        this.scoreIndicator = new ScoreIndicator(this.score, level.levelName());
        this.addSprite(scoreIndicator);
        // Create three balls and adds them to the game
        createBalls();
        // Create a paddle and add it to the game
       createPadlle();
        // create blocks and add them to the game
        createBorderBlocks();
        createBlocks();
    }

    /**
     *  Run the game -- start the animation loop.
     */
    public void run() {
        //this.createBallsOnTopOfPaddle(); // or a similar method
        this.running = true;
        // use our runner to run the current animation -- which is one turn of
        // the game.
        this.runner.run(new CountdownAnimation(3, 3, this.sprites));
        this.runner.run(this);
        }
    }
