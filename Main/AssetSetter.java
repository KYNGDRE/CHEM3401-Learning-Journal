package Main;
import Object.*;
// import Object.Obj_Key;
// import Object.Obj_Scroll;

public class AssetSetter {

    GamePanel gp;

    public AssetSetter(GamePanel gp){
        this.gp=gp;
    }

    public void setObject(){
        gp.obj[0]=new Obj_Key();
        gp.obj[0].worldX= 25* gp.tilesize;
        gp.obj[0].worldY= 7*gp.tilesize;

        gp.obj[1]=new Obj_Key();
        gp.obj[1].worldX= 25* gp.tilesize;
        gp.obj[1].worldY= 42*gp.tilesize;

        gp.obj[2]=new Obj_Scroll();
        gp.obj[2].worldX= 25* gp.tilesize;
        gp.obj[2].worldY= 25*gp.tilesize;

        gp.obj[8]=new Obj_Scroll();
        gp.obj[8].worldX= 4* gp.tilesize;
        gp.obj[8].worldY= 4*gp.tilesize;

        gp.obj[3]=new Obj_Scroll();
        gp.obj[3].worldX= 45* gp.tilesize;
        gp.obj[3].worldY= 45*gp.tilesize;

        gp.obj[4]=new Obj_Door();
        gp.obj[4].worldX= 9*gp.tilesize;
        gp.obj[4].worldY= 41*gp.tilesize;

        gp.obj[5]=new Obj_Door();
        gp.obj[5].worldX= 40* gp.tilesize;
        gp.obj[5].worldY= 10*gp.tilesize;

        gp.obj[6]=new Obj_Scroll();
        gp.obj[6].worldX= 45* gp.tilesize;
        gp.obj[6].worldY= 6*gp.tilesize;

        gp.obj[7]=new Obj_Chest();
        gp.obj[7].worldX= 6* gp.tilesize;
        gp.obj[7].worldY= 45*gp.tilesize;

        gp.obj[9]=new Obj_Boots();
        gp.obj[9].worldX= 24* gp.tilesize;
        gp.obj[9].worldY= 24*gp.tilesize;

        gp.obj[10]=new Obj_Scroll();
        gp.obj[10].worldX= 45* gp.tilesize;
        gp.obj[10].worldY= 25*gp.tilesize;

        gp.obj[11]=new Obj_Scroll();
        gp.obj[11].worldX= 5* gp.tilesize;
        gp.obj[11].worldY= 25*gp.tilesize;
        

        


    }
}
