package com.zzx.tank;

import com.zzx.tank.dto.ImageDto;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.IOException;

public class ResourceManage {
    public ImageDto[] goodTank = new ImageDto[11];
    public ImageDto[] badTank = new ImageDto[11];
    public ImageDto[] bullet = new ImageDto[2];
    public BufferedImage[][] explodes = new BufferedImage[3][16];

    private static final ResourceManage INSTANCE = new ResourceManage();

    public static ResourceManage getInstance() {
        return INSTANCE;
    }

    private ResourceManage() {
         try {
             // 己方塔克 GoodTank1.png~GoodTank10.png + BaseTank.gif
             for(int i=0; i<10; i++){
                 int ex = i+1;
                 goodTank[i] = createImageDto("images/Tank/GoodTank" + ex + ".png");
             }
             goodTank[10] = createImageDto("images/Tank/BaseTank.gif");

             // 敌方坦克 BadTank1.png~BadTank10.png + BaseTank.gif
             for(int i=0; i<10; i++){
                 int ex = i+1;
                 badTank[i] = createImageDto("images/Tank/BadTank" + ex + ".png");
             }
             badTank[10] = createImageDto("images/Tank/BaseTank.gif");

             // 子弹1 bulletU_01.gif
             bullet[0] = createImageDto("images/bullet/bulletU_01.gif");
             // 子弹2 bulletU_02.png
             bullet[1] = createImageDto("images/bullet/bulletU_02.png");

             // 爆炸1 1.gif~11.gif
             for(int i=0; i<11; i++){
                 int ex = i+1;
                 explodes[0][i] = ImageIO.read(ResourceManage.class.getClassLoader().getResourceAsStream("images/exblode/" + ex + ".gif"));
             }
             // 爆炸2 e1.gif~e16.gif
             for(int i=0; i<16; i++){
                 int ex = i+1;
                 explodes[1][i] = ImageIO.read(ResourceManage.class.getClassLoader().getResourceAsStream("images/exblode/e" + ex + ".gif"));
             }
             // 爆炸3 yun1.png~yun10.png
             for(int i=0; i<10; i++){
                 int ex = i+1;
                 explodes[2][i] = ImageIO.read(ResourceManage.class.getClassLoader().getResourceAsStream("images/exblode/yun" + ex + ".png"));
             }
         } catch (IOException e) {
             System.out.println("file path is error.");
            e.printStackTrace();
        }
    }

    private ImageDto createImageDto(String path) throws IOException {
        ImageDto imageDto = new ImageDto();
        BufferedImage image  = ImageIO.read(ResourceManage.class.getClassLoader().getResourceAsStream(path));
        imageDto.setUp(ImageUtil.rotateImage(image, 0));
        imageDto.setDown(ImageUtil.rotateImage(image, 180));
        imageDto.setLeft(ImageUtil.rotateImage(image, -90));
        imageDto.setRight(ImageUtil.rotateImage(image, 90));
        imageDto.setRightUp(ImageUtil.rotateImage(image, 45));
        imageDto.setRightDown(ImageUtil.rotateImage(image, 135));
        imageDto.setLeftDown(ImageUtil.rotateImage(image, -135));
        imageDto.setLeftUp(ImageUtil.rotateImage(image, -45));
        return imageDto;
    }
}
