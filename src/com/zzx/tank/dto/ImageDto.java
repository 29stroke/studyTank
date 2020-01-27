package com.zzx.tank.dto;

import java.awt.image.BufferedImage;

public class ImageDto {
    private BufferedImage up;
    private BufferedImage down;
    private BufferedImage left;
    private BufferedImage right;
    private BufferedImage rightUp;
    private BufferedImage rightDown;
    private BufferedImage leftUp;
    private BufferedImage leftDown;

    public BufferedImage getUp() {
        return up;
    }

    public void setUp(BufferedImage up) {
        this.up = up;
    }

    public BufferedImage getDown() {
        return down;
    }

    public void setDown(BufferedImage down) {
        this.down = down;
    }

    public BufferedImage getLeft() {
        return left;
    }

    public void setLeft(BufferedImage left) {
        this.left = left;
    }

    public BufferedImage getRight() {
        return right;
    }

    public void setRight(BufferedImage right) {
        this.right = right;
    }

    public BufferedImage getRightUp() {
        return rightUp;
    }

    public void setRightUp(BufferedImage rightUp) {
        this.rightUp = rightUp;
    }

    public BufferedImage getRightDown() {
        return rightDown;
    }

    public void setRightDown(BufferedImage rightDown) {
        this.rightDown = rightDown;
    }

    public BufferedImage getLeftUp() {
        return leftUp;
    }

    public void setLeftUp(BufferedImage leftUp) {
        this.leftUp = leftUp;
    }

    public BufferedImage getLeftDown() {
        return leftDown;
    }

    public void setLeftDown(BufferedImage leftDown) {
        this.leftDown = leftDown;
    }
}
