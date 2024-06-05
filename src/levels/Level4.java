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

/**.
 * level 4 information's
 */
public class Level4 implements LevelInformation {

    // the size of the screen
    private static final int SURFACE_WIDTH = 800;
    private static final int SURFACE_HEIGHT = 600;
    // the size of the little blocks that on the screen
    private static final int LITTLE_BLOCKS_SIZE = 50;


    @Override
    public int numberOfBalls() { return 3; }

    @Override
    public List<Velocity> initialBallVelocities() {
        List<Velocity> velocities = new ArrayList<>();
        double speed = 6;
        double angle = 359;
        Velocity v = Velocity.fromAngleAndSpeed(angle, speed);
        velocities.add(v);
        angle = 315;
        v = Velocity.fromAngleAndSpeed(angle, speed);
        velocities.add(v);
        angle = 20;
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
        return "level 4";
    }

    @Override
    public Sprite getBackground() {
        Rectangle rect = new Rectangle(new Point(25, 40), 750, 560);
        return new Block(rect, Color.blue);
    }

    @Override
    public List<Block> blocks() {
        List<Block> blocks = new ArrayList<>();
        Random random = new Random();
        int l = 25;
        for (int j = 100; j < 275; j = j + 25) {
            // Create a random color
            float r = random.nextFloat();
            float a = random.nextFloat();
            float b = random.nextFloat();
            java.awt.Color blockColor = new Color(r, a, b);
            for (int i = l; i < SURFACE_WIDTH - 50; i = i + 50) {
                Rectangle rect = new Rectangle(new Point((double) i, (double) j), 50, 25);
                Block block = new Block(rect, blockColor);
                blocks.add(block);
            }
        }
        return blocks;
    }

    @Override
    public int numberOfBlocksToRemove() {
        return 105;
    }
}
