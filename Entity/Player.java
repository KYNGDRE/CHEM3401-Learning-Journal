package Entity;

import Main.GamePanel;
import Main.KeyHandler;
import Main.TextDrawer;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import javax.imageio.ImageIO;
import java.util.concurrent.TimeUnit; // Optional, for using TimeUnit.SECONDS.sleep()


public class Player extends Entity{
    GamePanel gp;
    KeyHandler keyH;

    public final int screenX, screenY;
    // public final int screenX;
    public int hasKey = 0;
    public int hasChest = 0;
    public Graphics2D g3;


    public Player(GamePanel gp, KeyHandler keyH){

        this.gp=gp;
        this.keyH=keyH;


        screenX = gp.ScreenWidth/2 -(gp.tilesize/2);
        screenY= gp.ScreenHeight/2-(gp.tilesize/2);

        solidArea = new Rectangle();
        solidArea.x=8;
        solidArea.x=16;
        solidAreaDefaultX=solidArea.x;
        solidAreaDefaultY=solidArea.y;
        solidArea.width=32;
        solidArea.height=32;

        setDefaultValues();
        getPlayerImage();

    }
    public void setDefaultValues(){
        speed=4;
        worldY= gp.tilesize*25;
        worldX=gp.tilesize*26;
        direction="down";
    }

    public void getPlayerImage() {
        try {
            up1=ImageIO.read(getClass().getResourceAsStream("/Player/up1.png"));
            up2=ImageIO.read(getClass().getResourceAsStream("/Player/up2.png"));
            up3=ImageIO.read(getClass().getResourceAsStream("/Player/up3.png"));
            up4=ImageIO.read(getClass().getResourceAsStream("/Player/up4.png"));
            up5=ImageIO.read(getClass().getResourceAsStream("/Player/up5.png"));
            down1=ImageIO.read(getClass().getResourceAsStream("/Player/down1.png"));
            down2=ImageIO.read(getClass().getResourceAsStream("/Player/down2.png"));
            down3=ImageIO.read(getClass().getResourceAsStream("/Player/down3.png"));
            down4=ImageIO.read(getClass().getResourceAsStream("/Player/down4.png"));
            down5=ImageIO.read(getClass().getResourceAsStream("/Player/down5.png"));
            right1=ImageIO.read(getClass().getResourceAsStream("/Player/right1.png"));
            right2=ImageIO.read(getClass().getResourceAsStream("/Player/right2.png"));
            right3=ImageIO.read(getClass().getResourceAsStream("/Player/right3.png"));
            right4=ImageIO.read(getClass().getResourceAsStream("/Player/right4.png"));
            right5=ImageIO.read(getClass().getResourceAsStream("/Player/right5.png"));
            left1=ImageIO.read(getClass().getResourceAsStream("/Player/left1.png"));
            left2=ImageIO.read(getClass().getResourceAsStream("/Player/left2.png"));
            left3=ImageIO.read(getClass().getResourceAsStream("/Player/left3.png"));
            left4=ImageIO.read(getClass().getResourceAsStream("/Player/left4.png"));
            left5=ImageIO.read(getClass().getResourceAsStream("/Player/left5.png"));
            
            // System.out.println("Loading: " + getClass().getResource("/Player/up1.png"));


        } catch (Exception e) {
            e.printStackTrace();
        }

    }
    public void update(){
        if (keyH.upPressed==true||keyH.downPressed==true||keyH.leftPressed==true||keyH.rightPressed==true){
            if (keyH.upPressed == true){
                direction= "up";
            }
            else if (keyH.downPressed == true){
                direction= "down";
            } 
            else if (keyH.leftPressed == true){
                direction= "left";
            }
            else if (keyH.rightPressed == true){
                direction= "right";
            }
            else {}

            // Check TILE COLLISION
            collisionOn = false;
            gp.cCheaker.checkTile(this);

            // Check object collision
            int objIndex = gp.cCheaker.checkObject(this, true);
            pickUpObject(objIndex);

            // IF COLLISION IS FALSE, PLAYER CAN MOVE

            if (collisionOn==false){

                switch (direction) {
                    case "up":
                        worldY-=speed;                        
                        break;
                    case "down":
                        worldY+=speed;
                        break;
                    case "left":
                        worldX-=speed;
                        break;
                    case "right":
                        worldX+=speed;
                        break;
                    
                    default:
                        throw new AssertionError();
                }
            }

            spriteCounter++;
            if (spriteCounter>10){
                if (spriteNum==1){
                    spriteNum=2;
                }
                else if (spriteNum==2){
                    spriteNum=3;
                }
                else if (spriteNum==3){
                    spriteNum=4;
                }
                else if (spriteNum==4){
                    spriteNum=5;
                }
                else if (spriteNum==5){
                    spriteNum=1;
                }
                spriteCounter=0;
            }
            // spriteCounter=0;

        } 
        // else{
        //     switch (direction) {
        //         case "down":
        //         image=up1;
                    
        //             break;
        //         default:
        //             throw new AssertionError();
        //     }

        // }
    }

    public void pickUpObject(int i){
        if (i != 999){
            String objectName = gp.obj[i].name;

            switch (objectName) {
                case "Key":
                    gp.stopMusic();
                    gp.playSE(5);
                    // try {
                    //     Thread.sleep(5000);
                    // } catch (InterruptedException e) {
                    //     // TODO Auto-generated catch block
                    //     e.printStackTrace();
                    // }
                    gp.playMusic(0);
                    hasKey++;
                    gp.obj[i]=null;
                    // System.err.println("Key: "+hasKey);
                    gp.ui.showMessage("You found a Key!");
                    break;
                case "Door":
                    
                    // try {
                    // /j.setPreferredSize(new Dimension(gp.ScreenWidth,gp.ScreenHeight));
                    //     j.setBackground(Color.BLACK);
                    //     // j.setDoubleBuffered(true);
                    //     j.setTitle("Readings");
                    //     ((JFrame) j).setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                    //     // this.addKeyListener(keyH);
                    //     j.setFocusable(true);/     Thread.sleep(1000);
                    // // } catch (InterruptedException e) {
                    // //     // TODO Auto-generated catch block
                    // //     e.printStackTrace();
                    // // }
                    if (hasKey>0){
                        gp.stopMusic();
                        gp.playSE(1);
                        gp.obj[i]=null;
                        hasKey--;
                        gp.ui.showMessage("You used a key to open a door!");
                        gp.playMusic(0);
                    }
                    else{
                        gp.ui.showMessage("You need a key to open the door so go look for one!");

                    }
                    // System.err.println("Key: "+hasKey);
                    break;
                case "Scroll":
                    // gp.d
                    gp.obj[i]=null;
                    gp.ui.showMessage("You found a scroll!");
                    gp.stopMusic();
                    gp.playSE(6);        
                    gp.playMusic(0);


                    if (i==2) {
                        TextDrawer td = new TextDrawer("Introduction", "Welcome to Andre's Learning Journal. Read Scrolls to get the Journal Entries but the most important thing is to have fun. Keep in mind that you will finish the game if you interact with the chest so explore and when your finished go and touch the chest.\r\n" + //
                                                        "HINT: GO NORTH-WEST AND THE SHOES CAN MAKE YOU MOVE FASTER");
                        try {
                        Thread.sleep(5000);
                    } catch (InterruptedException e) {
                        // TODO Auto-generated catch block
                        e.printStackTrace();
                    }
                    } 
                    else if (i==3){
                        TextDrawer td = new TextDrawer("JOURNAL ENTRY 4", "Fourth and Fifth class\r\n" + //
                                                        "In the fourth class, groups were finalized, and in those same groups, we came up with our group catch phrases. The one our group came up with is “One man caa shake up fallow ground.” The rest of the class was spent speaking about being aware of our habits and the things we say and do. (Being self-aware)\r\n" + //
                                                        "In the fifth class, we recapped the fourth class by speaking about being self-aware. But in this class, we did the red and green smells. Red smells are bad behaviors in group dynamics, and green smells are good behaviors in group dynamics. We did this to identify the bad smell so we can avoid them in our individual group settings.\r\n" + //
                                                        "Hint go to North-East from the centre Tiles");
                        try {
                        Thread.sleep(5000);
                    } catch (InterruptedException e) {
                        // TODO Auto-generated catch block
                        e.printStackTrace();
                    }
                }
                    else if (i==6){
                        TextDrawer td = new TextDrawer("JOURNAL ENTRY 5", "Analysis Section\r\n" + //
                                                        "In our team, there was a lack of a communicator from the Glen Parker Team Mate style, so I ensured I tried to regulate my challenger personality type with the collaborator type and the communicator. How I did this is by asking questions (when necessary) about things that I believe were not helping the goal or could have been done better (Challenger and collaborator). Also, the fact that there was another Challenger and collaborator helped me to step away from the role more often. When I was not in those roles, I was a communicator, which helped me to facilitate discussions more and allowed more ideas to be heard.\r\n" + //
                                                        "Bruce Tuckman's theory of Team Development describes five stages: Forming, Storming, Norming, Performing, and Adjourning. The positives of my team are that the team did not have a real stage for storming and norming. This was because the group's members were all flexible thinkers and the other team players did not over-commit to a single team player style to ensure that the best of the other team members were brought out. The team was unable to form for a while because of a lack of scheduling time and then Hurricane Melissa did not allow us to meet further. This caused us to rush through the norming phase and this caused me to not perfectly understand my role. My group is now in the Performing stage and it seem like everything is going according to plan but I need to speak to one of my team mates because our sections are closely related but it seems like she has a lot on her plate at the moment so I have started working solo so when we start working we can finish as quickly as possible.\r\n" + //
                                                        "The most important thing that I have taken away from this experience is that you don’t need to have a single role when in a group dynamic; your role can be based on what the group needs at the moment. I have learned that, under certain circumstances, I can be a little more proactive than I thought. Example taking up different Team player styles. What I am most proud of in my group dynamic experience is the ability to sympathise and empathise with each team player's style from taking up their role. \r\n" + //
                                                        "You Can Now Finish The Game!!!!!");
                        try {
                        Thread.sleep(5000);
                    } catch (InterruptedException e) {
                        // TODO Auto-generated catch block
                        e.printStackTrace();
    
                    }
                    }
                    else if (i==8){
                    TextDrawer td = new TextDrawer("JOURNAL ENTRY 1", "First-class:  \r\n" + //
                                                "September 2, 2025.\r\n" + //
                                                "During the first class, we first had an ice breaker where we had to tell our favourite food and we had to do an activity where we would put ourselves in order we were born in without speaking.  Later in the class, the other activities that were done were: meeting my lecturer (Mrs. Verio), speaking about the Glenn Parker survey, commitment pledge and videos about the involvement of the individual.\r\n" + //
                                                "Question that stood out to me from our conversation in class:\r\n" + //
                                                "1)Who are you/ What are you seeing in the mirror?\r\n" + //
                                                "And to be honest, I am not sure. I have been so focused on working or resting so I can do more work that I didn’t reflect on how far I have come and who I have turned into.\r\n" + //
                                                " So after 15 minutes of  thinking:\r\n" + //
                                                "‘I am Ambitious, A Foodie, Goofy, Honest And Pragmatic’.\r\n" + //
                                                "Lastly, the question of who is my biggest cheerleader. And my response is my 2 sisters.\r\n" + //
                                                "Second-class:\r\n" + //
                                                "September 9, 2025.\r\n" + //
                                                "In the second class, we recapped the first class because there were a lot of students missing from the first class.\r\n" + //
                                                "Hint go to East from the centre Tiles");
                        try {
                        Thread.sleep(5000);
                    } catch (InterruptedException e) {
                        // TODO Auto-generated catch block
                        e.printStackTrace();
                    }
                    }
                    else if (i==10){
                        TextDrawer td = new TextDrawer("JOURNAL ENTRY 2", "Third-class:\r\n" + //
                                                        "September 16, 2025.\r\n" + //
                                                        "In the third class, we recapped the first class and asked some more personal and unnerving questions. In this class, we were told to form groups. So we started to form our preliminary group to be finalised by Mrs. Verio. The questions made me uncomfortable because I had never thought about these questions before, and I didn't have the answer to one of the questions. The first question was to say a quote I live by. I over-answer this question because there are several quotes: \r\n" + //
                                                        "1) Loyalty is a two-way street. If I am asking it from you, then you're getting it from me.’\r\n" + //
                                                        "2) ‘Do unto others as you will have them do unto you’\r\n" + //
                                                        "3) ‘Learn Lessons. Never Regret’\r\n" + //
                                                        "The second question is to say who I am when I am at my best.\r\n" + //
                                                        "And my response to that is ‘I am not sure’. The reason for this is that I have been fatigued mentally for 4-8 years, and a series of unfortunate events for the last year have really made me think that I am mentally and physically am nowhere near my best. And I don’t believe that I can guess and it will be valid because my guesses usually overestimate my abilities. \r\n" + //
                                                        "The third question is to say what values I live by. They are:\r\n" + //
                                                        "1) ‘Honesty’\r\n" + //
                                                        "2) ‘Loyalty’\r\n" + //
                                                        "3) ‘Dedication’\r\n" + //
                                                        "4) ‘Kindness’\r\n" + //
                                                        "The fourth question is to say what I love the most about myself or a positive affirmation about myself. To that, my response is my dedication and hard work. So, in class I responded with “I am Hardworking”.\r\n" + //
                                                        "Another question was to share any new insights that you have learnt about yourself having done this exercise.\r\n" + //
                                                        "My response is that I need to reflect on my experience more, and I am a harder worker than I thought.\r\n" + //
                                                        "The final question is to name something that I have overcome. And I can confidently say that I overcame High School. \r\n" + //
                                                        "After this class, I did the Glenn Parker survey. I found out that I am a Collaborator and a Challenger.\r\n" + //
                                                        "Challenger\r\n" + //
                                                        "The Challenger is a member who questions the goals, methods, and even the ethics of the team, is willing to disagree with the leader or higher authority and encourages the team to take well-conceived risks. \r\n" + //
                                                        "The characteristics that I had possessed from this style:\r\n" + //
                                                        "•\tHonest\r\n" + //
                                                        "•\tPrincipled/ ethical\r\n" + //
                                                        "•\tOpen\r\n" + //
                                                        "Even though I am a challenger, I am not very outspoken, and I usually don’t try to say something if it is not necessary. This can be a bad thing because my voice can help to identify faults with some solutions and give a different perspective on a situation or opinion. That is something that I need to work on.\r\n" + //
                                                        "\r\n" + //
                                                        "Collaborator\r\n" + //
                                                        "The Collaborator is a goal-directed member who sees the vision, mission, or goal of the team as paramount but is flexible and open to new ideas, willing to pitch in and work outside his or her defined role, and able to share the limelight with other team members. \r\n" + //
                                                        "The characteristics that I had possessed from this style:\r\n" + //
                                                        "•\tForward-looking\r\n" + //
                                                        "•\tGoal-directed\r\n" + //
                                                        "•\tAccommodating\r\n" + //
                                                        "•\tFlexible \r\n" + //
                                                        "Collaborator side of my personality is not imaginative at all. I believe that is something that I need to improve on because I could be a source of ideas and a greater perspective if it were.\r\n" + //
                                                        "We also got an activity to do in class called ‘A Letter to My Future Self’, where I would write a letter to myself now as myself a year from now.\r\n" + //
                                                        "In this activity, we should answer the following questions:\r\n" + //
                                                        "1) ‘What have you accomplished?’\r\n" + //
                                                        "2) ‘What are you most proud of?’\r\n" + //
                                                        "3) ‘What habits did you change?’\r\n" + //
                                                        "4) ‘How do you feel about yourself?’\r\n" + //
                                                        "AND THESE WILL BE ANSWERED IN The next entry\r\n" + //
                                                        "Hint go to West from the centre Tiles");
                        try {
                        Thread.sleep(5000);
                    } catch (InterruptedException e) {
                        // TODO Auto-generated catch block
                        e.printStackTrace();
                    }
                    }
                    // for 11
                    else {
                        TextDrawer td = new TextDrawer("JOURNAL ENTRY 3", "Letter to My Future Self\r\n" + //
                                                        "Dear Andre,\r\n" + //
                                                        "\tYou should be doing well, but school is really giving you problems about now. But don’t sweat the small stuff and study, and you should be fine.\r\n" + //
                                                        "\tA year from reading this, you will have graduated from UWI and will be working a Job in your field. The job pays extremely well, so of course I am happy, and it is making me think about moving out soon. And you have a car!!! It’s a Honda Fit, so nothing to brag about, but she does her job well.\r\n" + //
                                                        "\tThe thing that I am most proud of is handling the different characters in my group for my Capstone project because them boys are very different, but CHEM3401 group dynamics helped me navigate the group dynamic. Also, I am proud of the fact that I am working for a lot of money.  😊\r\n" + //
                                                        "\tA habit that I have stopped is procrastinating, and life feels good without it. And I have now started to emotionally compartmentalize better. \r\n" + //
                                                        "\tI feel happy and excited getting up every day and doing something I enjoy. But I am most happy with the fact that I am at peace with myself.\r\n" + //
                                                        "\tThat's it for now. Kiss our family for me, especially our mom and take care. You will see me soon like in a year. 😊\r\n" + //
                                                        "\t\t\t\t\t\tYours Truly, \r\n" + //
                                                        "\t\t\t\t\t\tAndre\r\n" + //
                                                        "Hint go to South-East from the centre Tiles");
                        try {
                        Thread.sleep(5000);
                    } catch (InterruptedException e) {
                        // TODO Auto-generated catch block
                        e.printStackTrace();
                    }
                    }


                    
                    break;
                case "Chest":
                    // if (hasChest==1){
                    gp.ui.gameFinished =true;
                    gp.stopMusic();
                    gp.playSE(4);
                    // hasChest--;
//                 }else{
//                     hasChest++;
//                     gp.stopMusic();
//                     gp.playSE(7);
//                     gp.playMusic(0);

// }
                    // gp.stopMusic();
                    
                    // try {
                    //     Thread.sleep(38000);
                    // } catch (InterruptedException e) {
                    //     // TODO Auto-generated catch block
                    //     e.printStackTrace();
                    // }
                    // gp.playMusic(0);
                    gp.ui.showMessage("You found a chest!");
                    
                    break;
                case "Boots":
                    gp.stopMusic();
                    gp.playSE(3);
                    // try {
                    //     Thread.sleep(3000);
                    // } catch (InterruptedException e) {
                    //     // TODO Auto-generated catch block
                    //     e.printStackTrace();
                    // }
                    gp.playMusic(0);
                    speed+=3;
                    gp.obj[i]=null;
                    gp.ui.showMessage("You found some running shoes!");

                    break;
                default:
                    throw new AssertionError();
            }
        }

    }
// need to change x and y
    public void draw(Graphics2D g2){
        // g2.setColor(Color.WHITE);
        // g2.fillRect(x, y, gp.tilesize, gp.tilesize);
        BufferedImage image; 
        if (keyH.upPressed==true||keyH.downPressed==true||keyH.leftPressed==true||keyH.rightPressed==true){
            switch (direction) {
                case "up":
                    // System.err.println("up");
                    image=up1;
                    // g2.drawImage(image, x, y, gp.tilesize, gp.tilesize, null);
                    if (spriteNum==1){
                        image=up1;
                    } 
                    else if (spriteNum==2){
                        image=up2;
                    } 
                    else if (spriteNum==3){
                        image=up3;
                    } 
                    else if (spriteNum==4){
                        image=up4;
                    }
                    else if (spriteNum==5){
                        image=up5;
                    }else{} 
                    g2.drawImage(image, screenX, screenY, gp.tilesize, gp.tilesize, null);
                    break;
                case "down":
                    // System.err.println("down");
                    image=down1;
                    if (spriteNum==1){
                        image=down1;
                    } 
                    else if (spriteNum==2){
                        image=down2;
                    } 
                    else if (spriteNum==3){
                        image=down3;
                    } 
                    else if (spriteNum==4){
                        image=down4;
                    }
                    else if (spriteNum==5){
                        image=down5;
                    }else{} 
                    g2.drawImage(image, screenX, screenY, gp.tilesize, gp.tilesize, null);
                    break;
                case "left":
                    // System.err.println("left");
                    image=left1;
                    if (spriteNum==1){
                        image=left1;
                    } 
                    else if (spriteNum==2){
                        image=left2;
                    } 
                    else if (spriteNum==3){
                        image=left3;
                    } 
                    else if (spriteNum==4){
                        image=left4;
                    }
                    else if (spriteNum==5){
                        image=left5;
                    }else{} 
                    g2.drawImage(image, screenX, screenY, gp.tilesize, gp.tilesize, null);
                    break;
                case "right":
                    // System.err.println("right");
                    image=right1;
                    if (spriteNum==1){
                        image=right1;
                    } 
                    else if (spriteNum==2){
                        image=right2;
                    } 
                    else if (spriteNum==3){
                        image=right3;
                    } 
                    else if (spriteNum==4){
                        image=right4;
                    }
                    else if (spriteNum==5){
                        image=right5;
                    }else{} 
                    g2.drawImage(image, screenX, screenY, gp.tilesize, gp.tilesize, null);
                    break;
                default:
                    // throw new AssertionError();
            }  
            // g2.drawImage(image, x, y, gp.tilesize, gp.tilesize, null);
        }
    else{
            switch (direction) {
                case "up":
                    // System.err.println("up");
                    image=up1;
                    g2.drawImage(image, screenX, screenY, gp.tilesize, gp.tilesize, null);
                    // g2.setColor(Color.red);
                    // g2.drawRect(screenX+solidArea.x,screenY+solidArea.y, solidArea.width, solidArea.height);

                    break;
                case "down":
                    // System.err.println("down");
                    image=down1; 
                    g2.drawImage(image, screenX, screenY, gp.tilesize, gp.tilesize, null);
                    // g2.setColor(Color.red);
                    // g2.drawRect(screenX+solidArea.x,screenY+solidArea.y, solidArea.width, solidArea.height);
                    break;
                case "left":
                    // System.err.println("left");
                    image=left1;
                    g2.drawImage(image, screenX, screenY, gp.tilesize, gp.tilesize, null);
                    // g2.setColor(Color.red);
                    // g2.drawRect(screenX+solidArea.x,screenY+solidArea.y, solidArea.width, solidArea.height);
                    break;
                case "right":
                    // System.err.println("right");
                    image=right1;
                    g2.drawImage(image, screenX, screenY, gp.tilesize, gp.tilesize, null);
                    // g2.setColor(Color.red);
                    // g2.drawRect(screenX+solidArea.x,screenY+solidArea.y, solidArea.width, solidArea.height);
                    break;
                default:
                    // throw new AssertionError();
            }  

        }

    }
}
