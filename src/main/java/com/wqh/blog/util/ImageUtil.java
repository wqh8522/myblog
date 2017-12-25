package com.wqh.blog.util;

import com.wqh.blog.domain.User;
import com.wqh.blog.enums.ResultEnum;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.time.DateFormatUtils;
import org.springframework.web.multipart.MultipartFile;
import sun.misc.BASE64Decoder;
import sun.misc.BASE64Encoder;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.*;
import java.util.Date;

/**
 * @Author wqh
 * @Date 2017/10/18 16:23
 * @Description: 图片处理工具类
 */
public class ImageUtil {


    /**
     * 保存文件，直接以multipartFile形式
     * @param multipartFile
     * @param path 文件保存绝对路径
     * @return 返回文件名
     * @throws IOException
     */
    public static String saveImg(MultipartFile multipartFile,String path) throws IOException {
        File file = new File(path);
        if (!file.exists()) {
            file.mkdirs();
        }
        FileInputStream fileInputStream = (FileInputStream) multipartFile.getInputStream();
        String fileName = Constants.getUUID() + ".png";
        BufferedOutputStream bos = new BufferedOutputStream(new FileOutputStream(path + File.separator + fileName));
        byte[] bs = new byte[1024];
        int len;
        while ((len = fileInputStream.read(bs)) != -1) {
            bos.write(bs, 0, len);
        }
        bos.flush();
        bos.close();
        return fileName;
    }


    /***
     * 保存base64图片
     * @param img_data 图片的base码
     * @param path 保存的路径
     * @return 返回文件名
     * @throws IOException
     */
    public static String saveImg(String img_data, String path) throws Exception {
        if(StringUtils.isNotBlank(img_data)){
            img_data = img_data.substring(img_data.indexOf(",")+1);
            // 读取保存文件
            BASE64Decoder decoder = new BASE64Decoder();
            byte[] b = decoder.decodeBuffer(img_data);
            for (int i = 0; i < b.length; ++i) {
                //调整异常数据
                if (b[i] < 0) {
                    b[i] += 256;
                }
            }
            //保存图片
            String fileName = Constants.getUUID() + ".png";
            File file = new File(path);
            if (!file.exists()) {
                file.mkdirs();
            }
            BufferedOutputStream bos = new BufferedOutputStream(new FileOutputStream(path+File.separator+fileName));
            bos.write(b);
            bos.flush();
            bos.close();
            Thread.sleep(1000);
            return fileName;
        }
        return null;
    }

    /**
     * 获取图片上传的路径
     *
     * @param user
     * @return
     */
    public static String getFilePath(User user) {
        //处理图片
        String path = File.separator + "article"
                + File.separator + user.getUsername()
                + File.separator + DateFormatUtils.format(new Date(), "yyyy-MM-dd");
        return path;
    }

    /**
     * 图片压缩
     *
     * @param srcImg 需要压缩的图片
     * @param rate   压缩比例
     */
    public static File reduceImg(File srcImg, File tagFile, float rate) {
        try {
            Image image = ImageIO.read(srcImg);
            int width = image.getWidth(null);
            int height = image.getHeight(null);
            if (width != 0 && height != 0) {
                //如果宽高不为空，则开始压缩图片
                BufferedImage tagImg = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
                tagImg.getGraphics().drawImage(
                        image.getScaledInstance(width, height, Image.SCALE_SMOOTH),
                        0, 0, null);
                ImageIO.write(tagImg, ".jpg", tagFile);
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
     *
     * @param img
     * @return
     */
    public static final boolean isImage(File img) {
        boolean flag = false;
        try {
            BufferedImage image = ImageIO.read(img);
            int width = image.getWidth();
            int height = image.getHeight();
            if (width == 0 || height == 0) {
                flag = false;
            } else {
                flag = true;
            }
        } catch (Exception e) {
            flag = false;
        }
        return flag;
    }

    public static String getImgStr(String imgFile){
        InputStream inputStream = null;
        byte[] data = null;
        try {
            inputStream = new FileInputStream(imgFile);
            data = new byte[inputStream.available()];
            inputStream.read(data);
            inputStream.close();
        }catch (Exception e){
            e.printStackTrace();
        }
        BASE64Encoder encoder = new BASE64Encoder();
        return encoder.encode(data);
    }

    public static void main(String[] args) throws Exception {
//        File file = new File("H:/P70823-130332.jpg");
//        File tagFile = new File("H:/123.jpg");
//        File file1 = reduceImg(file, tagFile, 50);
//        System.out.println(file1.length());
        String imgStr = getImgStr("G:0.jpg");
        System.out.println(imgStr);
        saveImg(imgStr,"G:\\blog");
    }

}
