package com.zzx.tank;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.IOException;

public class ResourceManage {
    public static BufferedImage tankU;
    public static BufferedImage tankD;
    public static BufferedImage tankL;
    public static BufferedImage tankR;
    public static BufferedImage tankRU;
    public static BufferedImage tankRD;
    public static BufferedImage tankLD;
    public static BufferedImage tankLU;

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
             tankU = ImageIO.read(ResourceManage.class.getClassLoader().getResourceAsStream("images/tankU.gif"));
             tankD = ImageIO.read(ResourceManage.class.getClassLoader().getResourceAsStream("images/tankD.gif"));
             tankL = ImageIO.read(ResourceManage.class.getClassLoader().getResourceAsStream("images/tankL.gif"));
             tankR = ImageIO.read(ResourceManage.class.getClassLoader().getResourceAsStream("images/tankR.gif"));
             tankRU = ImageIO.read(ResourceManage.class.getClassLoader().getResourceAsStream("images/tankRU.gif"));
             tankRD = ImageIO.read(ResourceManage.class.getClassLoader().getResourceAsStream("images/tankRD.gif"));
             tankLD = ImageIO.read(ResourceManage.class.getClassLoader().getResourceAsStream("images/tankLD.gif"));
             tankLU = ImageIO.read(ResourceManage.class.getClassLoader().getResourceAsStream("images/tankLU.gif"));

             bulletU = ImageIO.read(ResourceManage.class.getClassLoader().getResourceAsStream("images/bulletU.gif"));
             bulletD = ImageIO.read(ResourceManage.class.getClassLoader().getResourceAsStream("images/bulletD.gif"));
             bulletL = ImageIO.read(ResourceManage.class.getClassLoader().getResourceAsStream("images/bulletL.gif"));
             bulletR = ImageIO.read(ResourceManage.class.getClassLoader().getResourceAsStream("images/bulletR.gif"));
             missileRU = ImageIO.read(ResourceManage.class.getClassLoader().getResourceAsStream("images/missileRU.gif"));
             missileRD = ImageIO.read(ResourceManage.class.getClassLoader().getResourceAsStream("images/missileRD.gif"));
             missileLD = ImageIO.read(ResourceManage.class.getClassLoader().getResourceAsStream("images/missileLD.gif"));
             missileLU = ImageIO.read(ResourceManage.class.getClassLoader().getResourceAsStream("images/missileLU.gif"));

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
