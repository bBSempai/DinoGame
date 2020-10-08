package com.main;

import java.awt.Graphics;

public abstract class GameObject {
    protected int x, y;
    protected int w, h;
    protected ID id;
    protected int velX, velY;

    public GameObject(ID id, int x, int y, int w, int h) {
        this.id = id;
        this.x = x;
        this.y = y;
        this.w = w;
        this.h = h;
    }

    public abstract void tick();
    public abstract void render(Graphics g);

    public void setX(int x) {
        this.x = x;
    }
    public int getX() {
        return this.x;
    }
    public void setY(int y) {
        this.y = y;
    }
    public int getY() {
        return this.y;
    }
    public void setID(ID id) {
        this.id = id;
    }
    public ID getID() {
        return this.id;
    }
    public void setVelX(int velX) {
        this.velX = velX;
    }
    public int getVelX() {
        return this.velX;
    }
    public void setVelY(int velY) {
        this.velY = velY;
    }
    public int getVelY() {
        return this.velY;
    }
}
