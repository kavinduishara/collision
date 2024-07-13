package com.example.collision;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Background;
import javafx.scene.paint.Color;
import javafx.util.Duration;

import java.net.URL;
import java.util.*;

public class MultiCollisions implements Initializable {

    @FXML
    protected AnchorPane box;
    List<CircleController> list=new ArrayList<>();
    List<CircleController> list1=new ArrayList<>();

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        box.setBackground(Background.fill(Color.rgb(240,240,240)));
        Timeline timeline=new Timeline(new KeyFrame(Duration.millis(10),e->{
            list1.addAll(list);
            list.forEach(circleController -> {
                list1.remove(circleController);
                list1.forEach(Controller -> circleCollided(Controller,circleController));
                circleController.moveCircle();
                circleController.bounceBack();
            });
        }));
        timeline.setCycleCount(Timeline.INDEFINITE);
        timeline.play();
    }
    @FXML
    protected void createCircle(){
        double r=Math.random()*10;
        CircleController controller=new CircleController(box,r,50,50,Math.random(),Math.random());
        list.add(controller);
    }
    private void circleCollided(CircleController sphere1,CircleController sphere2){
        final double[] velx1 = {sphere1.getVelocityX()};
        final double[] vely1 = {sphere1.getVelocityY()};
        final double[] velx2 = {sphere2.getVelocityX()};
        final double[] vely2 = {sphere2.getVelocityY()};
        double mass1 = sphere1.getMass();
        double mass2 = sphere1.getMass();

            double x1 = sphere1.circle.getLayoutX();
            double y1 = sphere1.circle.getLayoutY();
            double x2 = sphere2.circle.getLayoutX();
            double y2 = sphere2.circle.getLayoutY();



            if (Math.sqrt(Math.pow(x2 - x1, 2) + Math.pow(y2 - y1, 2)) <= sphere1.circle.getRadius() + sphere2.circle.getRadius()) {
                double collisionAngle=Math.atan2((y2-y1),(x2-x1));

                double inline1=vely1[0]*Math.sin(collisionAngle)+velx1[0]*Math.cos(collisionAngle);
                double inline2=vely2[0]*Math.sin(collisionAngle)+velx2[0]*Math.cos(collisionAngle);
                double tanline1=vely1[0]*Math.cos(collisionAngle)-velx1[0]*Math.sin(collisionAngle);
                double tanline2=vely2[0]*Math.cos(collisionAngle)-velx2[0]*Math.sin(collisionAngle);

                double newx1vel = ((mass1 - mass2) * inline1) / (mass1 + mass2) + ((2 * mass2 * inline2) / (mass1 + mass2));
                double newx2vel = ((mass2 - mass1) * inline2) / (mass1 + mass2) + ((2 * mass1 * inline1) / (mass1 + mass2));

                sphere1.setVelocityX(newx1vel*Math.cos(collisionAngle)-tanline1*Math.sin(collisionAngle));
                sphere1.setVelocityY(newx1vel*Math.sin(collisionAngle)+tanline1*Math.cos(collisionAngle));
                sphere2.setVelocityX(newx2vel*Math.cos(collisionAngle)-tanline2*Math.sin(collisionAngle));
                sphere2.setVelocityY(newx2vel*Math.sin(collisionAngle)+tanline2*Math.cos(collisionAngle));

            }
    }
}
