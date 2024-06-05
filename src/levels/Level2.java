package levels;

import interfaces.LevelInformation;
import interfaces.Sprite;
import primitives.Point;
import primitives.Rectangle;
import primitives.Velocity;
import sprites.Block;

import java.awt.Color;
import java.util.ArrayList;
import java.util.List;

/**
 * level 2 information's.
 */
public class Level2 implements LevelInformation {

    // the size of the screen
    private static final int SURFACE_WIDTH = 800;
    private static final int SURFACE_HEIGHT = 600;
    // the size of the little blocks that on the screen
    private static final int BLOCKS_SIZE = 50;


    @Override
    public int numberOfBalls() { return 10; }

    @Override
    public List<Velocity> initialBallVelocities() {
        List<Velocity> velocities = new ArrayList<>();
        double speed = 6;
        double angle = 315;
        for (int i = 0; i < 5; i++) {
            Velocity v = Velocity.fromAngleAndSpeed(angle, speed);
            velocities.add(v);
            angle += 8;
        }
        angle = 45;
        for (int i = 0; i < 5; i++) {
            Velocity v = Velocity.fromAngleAndSpeed(angle, speed);
            velocities.add(v);
            angle -= 8;
        }
        return velocities;
    }

    @Override
    public int paddleSpeed() {
        return 2;
    }

    @Override
    public int paddleWidth() {
        return 600;
    }

    @Override
    public String levelName() {
        return "level 2";
    }

    @Override
    public Sprite getBackground() {
        Rectangle rect = new Rectangle(new Point(25, 40), 750, 560);
        return new Block(rect, Color.white);
    }

    @Override
    public List<Block> blocks() {
        List<Block> blocks = new ArrayList<>();
        double x = 25;
        Rectangle rect = new Rectangle(new Point(x, 250), BLOCKS_SIZE, 25);
        Block block = new Block(rect, Color.red);
        blocks.add(block);
        x += BLOCKS_SIZE;
        rect = new Rectangle(new Point(x, 250), BLOCKS_SIZE, 25);
        block = new Block(rect, Color.red);
        blocks.add(block);
        x += BLOCKS_SIZE;
        rect = new Rectangle(new Point(x, 250), BLOCKS_SIZE, 25);
        block = new Block(rect, Color.orange);
        blocks.add(block);
        x += BLOCKS_SIZE;
        rect = new Rectangle(new Point(x, 250), BLOCKS_SIZE, 25);
        block = new Block(rect, Color.orange);
        blocks.add(block);
        x += BLOCKS_SIZE;
        rect = new Rectangle(new Point(x, 250), BLOCKS_SIZE, 25);
        block = new Block(rect, Color.yellow);
        blocks.add(block);
        x += BLOCKS_SIZE;
        rect = new Rectangle(new Point(x, 250), BLOCKS_SIZE, 25);
        block = new Block(rect, Color.yellow);
        blocks.add(block);
        x += BLOCKS_SIZE;
        rect = new Rectangle(new Point(x, 250), BLOCKS_SIZE, 25);
        block = new Block(rect, Color.green);
        blocks.add(block);
        x += BLOCKS_SIZE;
        rect = new Rectangle(new Point(x, 250), BLOCKS_SIZE, 25);
        block = new Block(rect, Color.green);
        blocks.add(block);
        x += BLOCKS_SIZE;
        rect = new Rectangle(new Point(x, 250), BLOCKS_SIZE, 25);
        block = new Block(rect, Color.green);
        blocks.add(block);
        x += BLOCKS_SIZE;
        rect = new Rectangle(new Point(x, 250), BLOCKS_SIZE, 25);
        block = new Block(rect, Color.blue);
        blocks.add(block);
        x += BLOCKS_SIZE;
        rect = new Rectangle(new Point(x, 250), BLOCKS_SIZE, 25);
        block = new Block(rect, Color.blue);
        blocks.add(block);
        x += BLOCKS_SIZE;
        rect = new Rectangle(new Point(x, 250), BLOCKS_SIZE, 25);
        block = new Block(rect, Color.pink);
        blocks.add(block);
        x += BLOCKS_SIZE;
        rect = new Rectangle(new Point(x, 250), BLOCKS_SIZE, 25);
        block = new Block(rect, Color.pink);
        blocks.add(block);
        x += BLOCKS_SIZE;
        rect = new Rectangle(new Point(x, 250), BLOCKS_SIZE, 25);
        block = new Block(rect, Color.cyan);
        blocks.add(block);
        x += BLOCKS_SIZE;
        rect = new Rectangle(new Point(x, 250), BLOCKS_SIZE, 25);
        block = new Block(rect, Color.cyan);
        blocks.add(block);
        return blocks;
    }

    @Override
    public int numberOfBlocksToRemove() {
        return 15;
    }
}
