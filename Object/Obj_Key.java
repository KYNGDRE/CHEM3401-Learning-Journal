package Object;

import java.io.IOException;

import javax.imageio.ImageIO;

public class Obj_Key extends SuperObject{
    public Obj_Key(){

    name="Key";
    try {
        image=ImageIO.read(getClass().getResourceAsStream("/Player/key.png"));
    } catch (IOException e) {
        e.printStackTrace();
    }
    // TO MAKE SOLID
    collision=true;

 }

}
