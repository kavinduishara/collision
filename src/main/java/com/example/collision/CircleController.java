package com.example.collision;

import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;

public class CircleController {
    private AnchorPane box;
    protected Circle circle;
    private double velocityX;
    private double velocityY;
    private double r;
    private double x;
    private double y;
    private double mass;

    CircleController(AnchorPane box,double r,double x,double y, double velocityX, double velocityY) {
        Circle circle=new Circle(x,y,r);
        this.circle=circle;
        circle.setFill(Color.rgb((int) (Math.random()*255),(int) (Math.random()*255),(int) (Math.random()*255)));
        this.box = box;
        this.velocityX = velocityX;
        this.velocityY = velocityY;
        box.getChildren().add(circle);
        this.x=x;
        this.y=y;
        this.mass=Math.pow(r,3);
    }
    protected void moveCircle() {
        circle.setLayoutX(circle.getLayoutX() + velocityX);
        circle.setLayoutY(circle.getLayoutY() + velocityY);
    }
    protected void bounceBack() {
        double radius = circle.getRadius();
        if (circle.getLayoutX()+x<= box.getLayoutX() || circle.getLayoutX()+x + radius >= box.getWidth()) {
            velocityX = -velocityX;
        }
        if (circle.getLayoutY()+y<= box.getLayoutY() || circle.getLayoutY() +y+ radius >= box.getHeight()) {
            velocityY = -velocityY;
        }
    }

    public double getVelocityX() {
        return velocityX;
    }

    public double getVelocityY() {
        return velocityY;
    }

    public void setVelocityX(double velocityX) {
        this.velocityX = velocityX;
    }

    public void setVelocityY(double velocityY) {
        this.velocityY = velocityY;
    }

    public double getMass() {
        return mass;
    }
}
