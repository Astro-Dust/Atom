package meuRobo;

import robocode.*;
import robocode.Robot;

import java.awt.*;

public class Atom extends Robot {

    private boolean isDefensive = false;

    public void run() {
        while (true) {

            setColors(Color.black, Color.white, Color.red);

            evaluateStrategy();

            if (isDefensive) {
                moveDefensively();
            } else {
                attack();
            }

            // procura por inimigos
            scan();
        }
    }

    public void onScannedRobot(ScannedRobotEvent event) {
        // dependendo da distância, irá mudar ou não a estratégia
        if (event.getDistance() < 100) {
            isDefensive = true;
        }

        fire(3);
    }

    public void onHitByBullet(HitByBulletEvent event) {
        isDefensive = true;
    }

    public void onHitWall(HitWallEvent event) {
        turnLeft(90);
    }

    private void evaluateStrategy() {
        if (getEnergy() < 30 || isDefensive) {
            isDefensive = true;
        } else {
            isDefensive = false;
        }
    }

    private void moveDefensively() {
        back(100);
        turnLeft(45);
    }

    private void attack() {
        ahead(100);
        turnGunRight(90);
    }
}
