package com.zzx.tank;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.IOException;

public class ResourceManage {
    public static BufferedImage goodTankU;
    public static BufferedImage goodTankD;
    public static BufferedImage goodTankL;
    public static BufferedImage goodTankR;
    public static BufferedImage goodTankRU;
    public static BufferedImage goodTankRD;
    public static BufferedImage goodTankLD;
    public static BufferedImage goodTankLU;

    public static BufferedImage badTankU;
    public static BufferedImage badTankD;
    public static BufferedImage badTankL;
    public static BufferedImage badTankR;
    public static BufferedImage badTankRU;
    public static BufferedImage badTankRD;
    public static BufferedImage badTankLD;
    public static BufferedImage badTankLU;

    public static BufferedImage bulletU;
    public static BufferedImage bulletD;
    public static BufferedImage bulletL;
    public static BufferedImage bulletR;
    public static BufferedImage missileRU;
    public static BufferedImage missileRD;
    public static BufferedImage missileLD;
    public static BufferedImage missileLU;

    public static BufferedImage[] explodes = new BufferedImage[16];

    static {
         try {
//             tankU = ImageIO.read(ResourceManage.class.getClassLoader().getResourceAsStream("images/tankU.gif"));
//             tankD = ImageIO.read(ResourceManage.class.getClassLoader().getResourceAsStream("images/tankD.gif"));
//             tankL = ImageIO.read(ResourceManage.class.getClassLoader().getResourceAsStream("images/tankL.gif"));
//             tankR = ImageIO.read(ResourceManage.class.getClassLoader().getResourceAsStream("images/tankR.gif"));
//             tankRU = ImageIO.read(ResourceManage.class.getClassLoader().getResourceAsStream("images/tankRU.gif"));
//             tankRD = ImageIO.read(ResourceManage.class.getClassLoader().getResourceAsStream("images/tankRD.gif"));
//             tankLD = ImageIO.read(ResourceManage.class.getClassLoader().getResourceAsStream("images/tankLD.gif"));
//             tankLU = ImageIO.read(ResourceManage.class.getClassLoader().getResourceAsStream("images/tankLU.gif"));
             goodTankU = ImageIO.read(ResourceManage.class.getClassLoader().getResourceAsStream("images/GoodTank1.png"));
             goodTankD = ImageUtil.rotateImage(goodTankU, 180);
             goodTankL = ImageUtil.rotateImage(goodTankU, -90);
             goodTankR = ImageUtil.rotateImage(goodTankU, 90);
             goodTankRU = ImageUtil.rotateImage(goodTankU, 45);
             goodTankRD = ImageUtil.rotateImage(goodTankU, 135);
             goodTankLD = ImageUtil.rotateImage(goodTankU, -135);
             goodTankLU = ImageUtil.rotateImage(goodTankU, -45);

             badTankU = ImageIO.read(ResourceManage.class.getClassLoader().getResourceAsStream("images/BadTank1.png"));
             badTankD = ImageUtil.rotateImage(badTankU, 180);
             badTankL = ImageUtil.rotateImage(badTankU, -90);
             badTankR = ImageUtil.rotateImage(badTankU, 90);
             badTankRU = ImageUtil.rotateImage(badTankU, 45);
             badTankRD = ImageUtil.rotateImage(badTankU, 135);
             badTankLD = ImageUtil.rotateImage(badTankU, -135);
             badTankLU = ImageUtil.rotateImage(badTankU, -45);

//             bulletU = ImageIO.read(ResourceManage.class.getClassLoader().getResourceAsStream("images/bulletU.gif"));
//             bulletD = ImageIO.read(ResourceManage.class.getClassLoader().getResourceAsStream("images/bulletD.gif"));
//             bulletL = ImageIO.read(ResourceManage.class.getClassLoader().getResourceAsStream("images/bulletL.gif"));
//             bulletR = ImageIO.read(ResourceManage.class.getClassLoader().getResourceAsStream("images/bulletR.gif"));
//             missileRU = ImageIO.read(ResourceManage.class.getClassLoader().getResourceAsStream("images/missileRU.gif"));
//             missileRD = ImageIO.read(ResourceManage.class.getClassLoader().getResourceAsStream("images/missileRD.gif"));
//             missileLD = ImageIO.read(ResourceManage.class.getClassLoader().getResourceAsStream("images/missileLD.gif"));
//             missileLU = ImageIO.read(ResourceManage.class.getClassLoader().getResourceAsStream("images/missileLU.gif"));
             bulletU = ImageIO.read(ResourceManage.class.getClassLoader().getResourceAsStream("images/bulletU.png"));
             bulletD = ImageUtil.rotateImage(bulletU, 180);
             bulletL = ImageUtil.rotateImage(bulletU, -90);
             bulletR = ImageUtil.rotateImage(bulletU, 90);
             missileRU = ImageUtil.rotateImage(bulletU, 45);
             missileRD = ImageUtil.rotateImage(bulletU, 135);
             missileLD = ImageUtil.rotateImage(bulletU, -135);
             missileLU = ImageUtil.rotateImage(bulletU, 135);

             for(int i=0; i<16; i++){
                 int ex = i+1;
                 explodes[i] = ImageIO.read(ResourceManage.class.getClassLoader().getResourceAsStream("images/e" + ex + ".gif"));
             }

         } catch (IOException e) {
             System.out.println("file path is error.");
            e.printStackTrace();
        }
    }
}
