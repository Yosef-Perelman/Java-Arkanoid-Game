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
 * level 1 information's.
 */
public class Level1 implements LevelInformation {

    // the size of the screen
    private static final int SURFACE_WIDTH = 800;
    private static final int SURFACE_HEIGHT = 600;

    @Override
    public int numberOfBalls() { return 1; }

    @Override
    public List<Velocity> initialBallVelocities() {
        List<Velocity> velocities = new ArrayList<>();
        double speed = 6;
        double angle = 360;
        velocities.add(Velocity.fromAngleAndSpeed(angle, speed));
        return velocities;
    }

    @Override
    public int paddleSpeed() {
        return 8;
    }

    @Override
    public int paddleWidth() {
        return 90;
    }

    @Override
    public String levelName() {
        return "level 1";
    }

    @Override
    public Sprite getBackground() {
        Rectangle rect = new Rectangle(new Point(25, 40), 750, 560);
        return new Block(rect, Color.black);
    }

    @Override
    public List<Block> blocks() {
        List<Block> blocks = new ArrayList<>();
//        Rectangle rect = new Rectangle(new Point(385, 150), 30, 30);
//        Block block = new Block(rect, Color.red);
//        blocks.add(block);

        // new staff
        int Xstart = 355, Ystart = 120;
        for (int i = 0 ; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                Rectangle rect = new Rectangle(new Point(Xstart + 30 * i, Ystart + 30 * j), 30, 30);
                Block block = new Block(rect, Color.red);
                blocks.add(block);
            }
        }

        return blocks;
    }

    @Override
    public int numberOfBlocksToRemove() {
        return 9;
    }
}
