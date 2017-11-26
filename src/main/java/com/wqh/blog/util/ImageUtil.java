package com.wqh.blog.util;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

/**
 * @Author wqh
 * @Date 2017/10/18 16:23
 * @Description: 图片处理工具类
 */
public class ImageUtil {

    /**
     * 图片压缩
     * @param srcImg 需要压缩的图片
     * @param rate 压缩比例
     */
    public static File reduceImg(File srcImg,File tagFile,float rate){
        try {
            Image image = ImageIO.read(srcImg);
            int width = image.getWidth(null);
            int height = image.getHeight(null);
            if(width != 0 && height != 0){
                //如果宽高不为空，则开始压缩图片
                BufferedImage tagImg = new BufferedImage(width,height,BufferedImage.TYPE_INT_RGB);
                tagImg.getGraphics().drawImage(
                        image.getScaledInstance(width,height,Image.SCALE_SMOOTH),
                        0,0,null);
                ImageIO.write(tagImg,".jpg",tagFile);
            /*   FileOutputStream out = new FileOutputStream("H://33.jpg");
                JPEGImageEncoder encoder = JPEGCodec.createJPEGEncoder(out);
                encoder.encode(tagImg);
                out.close();*/
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return tagFile;
    }

    /**
     * 判断文件是不是图片
     * @param img
     * @return
     */
    public static final boolean isImage(File img){
        boolean flag = false;
        try {
            BufferedImage image = ImageIO.read(img);
            int width = image.getWidth();
            int height = image.getHeight();
            if(width==0 || height==0){
                flag = false;
            }else {
                flag = true;
            }
        } catch (Exception e) {
            flag = false;
        }
        return flag;
    }

    public static void main(String[] args) throws IOException {
        File file = new File("H:/P70823-130332.jpg");
        File tagFile = new File("H:/123.jpg");
        File file1 = reduceImg(file, tagFile,50);
        System.out.println(file1.length());
    }

}
