package Main;
import Entity.Player;
import Object.SuperObject;
import Tile.Tile;
import Tile.TileManager;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;

import javax.swing.JPanel;

public class GamePanel extends JPanel implements Runnable{
    // Screen Settings
    final int originalTileSize = 16; /*Size is 16 *16 pixel which is standard. */
    final int scale = 3;

    public final int tilesize = originalTileSize * scale; /*48 * 48 pixel size*/
    public final int maxScreenCol = 16;
    public final int maxScreenRow = 12;
    public final int ScreenWidth = tilesize*maxScreenCol; //768 Pixel
    public final int ScreenHeight = tilesize*maxScreenRow; //576 Pixel


    // WORLD SETTINGS
    public final int maxWorldCol = 50;
    public final int maxWorldRow = 50;
    // public final int worldWidth = tilesize*maxWorldCol;
    // public final int worldHeight = tilesize*maxWorldRow;


    //FPS
    int FPS=60;

    // SYSTEM
    TileManager tileM= new TileManager(this);
    KeyHandler keyH = new KeyHandler();

    // Sound
    Sound music=new Sound();
    // Sound se =new Sound();
    
    public CollisionChecker cCheaker= new CollisionChecker(this);
    public AssetSetter aSetter = new AssetSetter(this);
    public UI ui = new UI(this);
    Thread gameThread;

    // ENTITY AND OBJECT
    public Player player=new Player(this, keyH); 
    public SuperObject obj[] = new SuperObject[20];

    
    // // Set player's default position
    // int playerX = 100;
    // int playerY = 100;
    // int playerSpeed = 4;


    public GamePanel(){
        this.setPreferredSize(new Dimension(ScreenWidth,ScreenHeight));
        this.setBackground(Color.BLACK);
        this.setDoubleBuffered(true);
        this.addKeyListener(keyH);
        this.setFocusable(true);
    }

    public void setUpGame(){
        aSetter.setObject();
        playMusic(0);
    }

    public void startGameThread(){
        gameThread = new Thread();
        gameThread.start();

    }
    // @Override
    // public void run() {

    //     double drawInterval = 1000000000/FPS;// 0.01666667 seconds
    //     double nextDrawTime = System.nanoTime() + drawInterval;
    //     // double nextDrawTime = System.currentTimeMillis() + drawInterval;

    //     while (gameThread != null){

    //         // long currentTime = System.nanoTime();
    //         // System.out.println("Current Time;: "+currentTime);       
    //         // System.out.println("THE GAME LOOP IS RUNNING!!!");


    //         // 1) Update: Update information such as character position
    //         update();
    //         // 2) Draw: Draw the screen with the updated information.
    //         repaint();

    //         try {
    //             double remainingTime = nextDrawTime-System.nanoTime();
    //             remainingTime= remainingTime / 1000000;

    //             if (remainingTime<0){
    //                 remainingTime=0;
    //             }
    //             // double remainingTime = nextDrawTime-System.currentTimeMillis();
    //             Thread.sleep((long)remainingTime);

    //             nextDrawTime += drawInterval;                
    //         } catch (InterruptedException ex) {
    //         }
    //     }
    //     // System.out.println("NOT RUNNING!!!");
    //     throw new UnsupportedOperationException("Not supported yet.");
    // }
    @Override
    public void run() {
        double drawInterval = 1000000000/FPS;// 0.01666667 seconds
        double delta =0;
        long lastTime = System.nanoTime();
        long currentTime;
        long timer=0;
        int drawCount=0;

        while (gameThread != null){
            currentTime= System.nanoTime();
            delta += (currentTime-lastTime)/drawInterval;
            // currentTime+=(currentTime-lastTime);
            timer+=(currentTime-lastTime);
            lastTime = currentTime;

            if (delta >= 1){
                // 1) Update: Update information such as character position
                update();
                // 2) Draw: Draw the screen with the updated information.
                repaint();
                delta--;
                drawCount++;
            }
            if (timer>=1000000000){
                System.out.println("FPS: "+drawCount);
                drawCount=0;
                timer=0;
            }

            }

            
    }

    public void update(){
        player.update();

    }
    @Override
    public void paintComponent(Graphics g){
        super.paintComponent(g);
        Graphics2D g2= (Graphics2D)g;
        // Tile
        tileM.draw(g2);
        // Object
        for (int i=0; i<obj.length; i++){
            if (obj[i] != null){
                obj[i].draw(g2, this);
            }
        }
        // Player
        player.draw(g2);

        // UI
        ui.draw(g2);

        g2.dispose();

    }
    public void playMusic(int i){
        music.setFile(i);
        music.play();
        music.loop();
    }
    public void stopMusic(){
        music.stop();
    }
    public void playSE(int i){
        music.setFile(i);
        music.play();
    }
}
