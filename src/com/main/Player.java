package com.main;

import java.awt.Color;
import java.awt.Graphics;

public class Player extends GameObject {

    public Player(ID id, int x, int y, int w, int h) {
        super(id, x, y, w, h);

        velX = 0;
    }

    public void tick() {
        x += velX;
    }

    public void render(Graphics g) {
        g.setColor(Color.WHITE);
        g.fillRect(x, y-120, w, h);
    }
    
}
