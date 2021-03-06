package org.academiadecodigo.tropadelete.charlie;

import org.academiadecodigo.simplegraphics.graphics.Rectangle;
import org.academiadecodigo.tropadelete.charlie.GameObjects.Ball;
import org.academiadecodigo.tropadelete.charlie.GameObjects.Player;

import java.util.Random;

public class Utils {


    public static Ball startBall(Player player1, Player player2, String ballSkin) {

        int ballPaddleDistance = 50;
        boolean isFacingPlayerTwo = new Random().nextBoolean();
        boolean startAngleUp = new Random().nextBoolean();
        int startAngle;

        Rectangle rPlayer1 = player1.getRectangle();
        Rectangle rPlayer2 = player2.getRectangle();

        Ball ball;

        int startY = rPlayer1.getY() + (rPlayer1.getHeight() / 2) - (Ball.getBound() / 2);

        if (isFacingPlayerTwo) {

            int startX = rPlayer2.getX() - ballPaddleDistance - Ball.getBound();
            startAngle = startAngleUp ? 180 + 30  : 180 - 30;

            ball = new Ball(startX, startY, PlayerNumber.TWO,ballSkin);
        }

        else {

            int startX = rPlayer1.getX() + rPlayer1.getWidth() + ballPaddleDistance;
            startAngle = startAngleUp ? 30 : -30;

            ball = new Ball(startX, startY, PlayerNumber.ONE,ballSkin);
        }

        ball.setDeltaByAngle(startAngle);
        return ball;
    }

    public static PlayerNumber checkVictoryCondition(Ball ball, Rectangle canvas, Player player1, Player player2) {

        PlayerNumber playerNumber = CollisionDetector.ballCollisionGoal(ball.getEllipse(), canvas);

        if (playerNumber.equals(PlayerNumber.ONE)) {
            player1.losePoint();
            return PlayerNumber.TWO;
        }

        if (playerNumber.equals(PlayerNumber.TWO)) {
            player2.losePoint();
            return PlayerNumber.ONE;
        }

        return PlayerNumber.NONE;
    }

}