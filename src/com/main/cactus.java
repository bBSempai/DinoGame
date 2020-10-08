package com.main;

import java.awt.Color;
import java.awt.Graphics;

public class cactus extends GameObject {

    public cactus(ID id, int x, int y, int w, int h) {
        super(id, x, y, w, h);

        velX = -6;
    }

    public void tick() {
        x += velX;
    }

    public void render(Graphics g) {
        g.setColor(Color.GREEN);
        g.fillRect(x, y-80-h, w, h);
    }
    
}
