package Object;

import java.io.IOException;

import javax.imageio.ImageIO;

public class Obj_Scroll extends SuperObject{

public Obj_Scroll(){

    name="Scroll";
    try {
        image=ImageIO.read(getClass().getResourceAsStream("/Player/scroll1.png"));
    } catch (IOException e) {
        e.printStackTrace();
    }
    // TO MAKE SOLID
    collision=true;

 }


}
