package com.zzx.tank;

import com.zzx.tank.dto.ImageDto;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.IOException;

public class ResourceManage {
    public ImageDto goodTank;
    public ImageDto badTank;
    public ImageDto bullet;
    public BufferedImage[] explodes = new BufferedImage[16];

    private static final ResourceManage INSTANCE = new ResourceManage();

    public static ResourceManage getInstance() {
        return INSTANCE;
    }

    private ResourceManage() {
         try {
             goodTank = createImageDto("images/GoodTank1.png");
             badTank = createImageDto("images/BadTank1.png");
             bullet = createImageDto("images/bulletU.png");
             for(int i=0; i<16; i++){
                 int ex = i+1;
                 explodes[i] = ImageIO.read(ResourceManage.class.getClassLoader().getResourceAsStream("images/e" + ex + ".gif"));
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
