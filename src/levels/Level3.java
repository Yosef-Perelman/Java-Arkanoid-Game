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
import java.util.Random;

/**
 * level 3 information.
 */
public class Level3 implements LevelInformation {

    // the size of the screen
    private static final int SURFACE_WIDTH = 800;
    private static final int SURFACE_HEIGHT = 600;
    // the size of the little blocks that on the screen
    private static final int BLOCKS_SIZE = 50;


    @Override
    public int numberOfBalls() { return 3; }

    @Override
    public List<Velocity> initialBallVelocities() {
        List<Velocity> velocities = new ArrayList<>();
        double speed = 6;
        double angle = 359;
        Velocity v = Velocity.fromAngleAndSpeed(angle, speed);
        velocities.add(v);
        angle = 3;
        v = Velocity.fromAngleAndSpeed(angle, speed);
        velocities.add(v);
        angle = 356;
        v = Velocity.fromAngleAndSpeed(angle, speed);
        velocities.add(v);
        return velocities;
    }

    @Override
    public int paddleSpeed() {
        return 8;
    }

    @Override
    public int paddleWidth() {
        return 100;
    }

    @Override
    public String levelName() {
        return "level 3";
    }

    @Override
    public Sprite getBackground() {
        Rectangle rect = new Rectangle(new Point(25, 40), 750, 560);
        return new Block(rect, Color.green);
    }

    @Override
    public List<Block> blocks() {
        List<Block> blocks = new ArrayList<>();
        Random random = new Random();
        int l = 275;
        for (int j = 150; j < 275; j = j + 25) {
            // Create a random color
            float r = random.nextFloat();
            float a = random.nextFloat();
            float b = random.nextFloat();
            java.awt.Color blockColor = new Color(r, a, b);
            for (int i = l; i < SURFACE_WIDTH - BLOCKS_SIZE; i = i + BLOCKS_SIZE) {
                Rectangle rect = new Rectangle(new Point((double) i, (double) j), BLOCKS_SIZE, 25);
                Block block = new Block(rect, blockColor);
                blocks.add(block);
            }
            l = l + BLOCKS_SIZE;
        }
        return blocks;
    }

    @Override
    public int numberOfBlocksToRemove() {
        return 40;
    }
}
