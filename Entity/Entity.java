package Entity;

import java.awt.Rectangle;
import java.awt.image.BufferedImage;

public class Entity {
    public int worldX,worldY;
    public int speed;
    
    public BufferedImage up1, up2, up3, up4, up5, down1, down2, down3, down4, down5, left1, left2, left3, left4, left5, right1, right2, right3, right5, right4;
    public String direction; 

    public int spriteCounter;
    public int spriteNum =1;
    
    public Rectangle solidArea;
    public int solidAreaDefaultX, solidAreaDefaultY;
    public boolean collisionOn = false;
}

