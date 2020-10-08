package com.main;

import java.awt.Canvas;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.image.BufferStrategy;

public class Game extends Canvas implements Runnable{
    private static final long serialVersionUID = 8049297171898535242L;

    public static final int width = 960;
    public static final int height = 420;
    private Thread thread;
    private boolean running = false;
    private Handler handler;

    public Game(){
        new Window(width, height,"Dino", this);
        handler = new Handler();

        handler.addObject(new Player(ID.Player, 60, height, 20, 40));
        handler.addObject(new cactus(ID.Enemy, width, height, 30, 60));
    }

    public synchronized void start() {
        thread = new Thread(this);
        thread.start();
        running = true;
    }
    public synchronized void stop() {
        try {
            thread.join();
            running = false;
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void run(){
        long lastTime = System.nanoTime(); // get current time to the nanosecond
        double amountOfTicks = 60.0; // set the number of ticks 
        double ns = 1000000000 / amountOfTicks; // this determines how many times we can devide 60 into 1e9 of nano seconds or about 1 second
        double delta = 0; // change in time (delta always means a change like delta v is change in velocity)
        long timer = System.currentTimeMillis(); // get current time
        int frames = 0; // set frame variable
        while(running){ 
         long now = System.nanoTime(); // get current time in nonoseconds durring current loop
         delta += (now - lastTime) / ns; // add the amount of change since the last loop
         lastTime = now;  // set lastTime to now to prepare for next loop
         while(delta >= 1){
          // one tick of time has passed in the game this 
          //ensures that we have a steady pause in our game loop 
          //so we don't have a game that runs way too fast 
          //basically we are waiting for  enough time to pass so we 
          // have about 60 frames per one second interval determined to the nanosecond (accuracy)
          // once this pause is done we render the next frame
          tick();  
          delta--;  // lower our delta back to 0 to start our next frame wait
        }
        if(running){
         render(); // render the visuals of the game
        }
        frames++; // note that a frame has passed
        if(System.currentTimeMillis() - timer > 1000 ){ // if one second has passed
         timer+= 1000; // add a thousand to our timer for next time
         System.out.println("FPS: " + frames); // print out how many frames have happend in the last second
         frames = 0; // reset the frame count for the next second
        }
       }
       stop(); // no longer running stop the thread
    }

    private void tick(){
        handler.tick();
    }

    private void render(){
        BufferStrategy bs = this.getBufferStrategy();
        if(bs == null) {
            this.createBufferStrategy(3);
            return;
        }

        Graphics g = bs.getDrawGraphics();

        g.setColor(Color.BLACK);
        g.fillRect(0, 0, width, height);
        
        handler.render(g);

        g.dispose();
        bs.show();
    }
    public static void main(String[] args) {
        new Game();
    }
}
