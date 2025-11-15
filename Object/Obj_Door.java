package Object;

import java.io.IOException;

import javax.imageio.ImageIO;

public class Obj_Door extends SuperObject{


public Obj_Door(){

    name="Door";
    try {
        image=ImageIO.read(getClass().getResourceAsStream("/Player/door1.png"));
    } catch (IOException e) {
        e.printStackTrace();
    }

    // TO MAKE SOLID
    collision=true;

 }


}
