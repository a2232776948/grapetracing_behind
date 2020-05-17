package cn.edu.scau.util.qrcode;

import java.awt.*;
import java.awt.geom.RoundRectangle2D;
import java.awt.image.BufferedImage;
import java.io.*;
import java.util.Hashtable;
import javax.imageio.ImageIO;
import com.google.zxing.BarcodeFormat;
import com.google.zxing.BinaryBitmap;
import com.google.zxing.DecodeHintType;
import com.google.zxing.EncodeHintType;
import com.google.zxing.MultiFormatReader;
import com.google.zxing.MultiFormatWriter;
import com.google.zxing.Result;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.common.HybridBinarizer;
import com.google.zxing.qrcode.decoder.ErrorCorrectionLevel;
import org.springframework.boot.system.ApplicationHome;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.web.multipart.MultipartFile;

public class QRCodeUtil {

    public static String getbaseURL(){
        String c = "在测试环境无效";
        ApplicationHome h = new ApplicationHome(QRCodeUtil.class);
        File jarF = h.getSource();
        c = jarF.getParentFile().toString();
        System.out.println(c);
        return c;
    }

    private static final String CHARSET = "utf-8";
    private static final String FORMAT_NAME = "JPG";
    // 二维码尺寸
    private static final int QRCODE_SIZE = 300;
    // LOGO宽度
    private static final int WIDTH = 60;
    // LOGO高度
    private static final int HEIGHT = 60;

    //获取流文件
    private static void inputStreamToFile(InputStream ins, File file) {
        try {
            OutputStream os = new FileOutputStream(file);
            int bytesRead = 0;
            byte[] buffer = new byte[8192];
            while ((bytesRead = ins.read(buffer, 0, 8192)) != -1) {
                os.write(buffer, 0, bytesRead);
            }
            os.close();
            ins.close();
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    //创建底部没有文字的二维码
    private static BufferedImage createImage(String content, String imgPath, boolean needCompress) throws Exception {
        Hashtable hints = new Hashtable();
        hints.put(EncodeHintType.ERROR_CORRECTION, ErrorCorrectionLevel.H);
        hints.put(EncodeHintType.CHARACTER_SET, CHARSET);
        hints.put(EncodeHintType.MARGIN, 1);
        BitMatrix bitMatrix = new MultiFormatWriter().encode(content, BarcodeFormat.QR_CODE, QRCODE_SIZE, QRCODE_SIZE,
                hints);
        int width = bitMatrix.getWidth();
        int height = bitMatrix.getHeight();
        BufferedImage image = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
        for (int x = 0; x < width; x++) {
            for (int y = 0; y < height; y++) {
                image.setRGB(x, y, bitMatrix.get(x, y) ? 0xFF000000 : 0xFFFFFFFF);
            }
        }
        if (imgPath == null || "".equals(imgPath)) {
            return image;
        }
        // 插入图片
        QRCodeUtil.insertImage(image, imgPath, needCompress);
        return image;
    }

    //创建底部没有文字的二维码
    private static BufferedImage createImage(String content, InputStream imgPath, boolean needCompress) throws Exception {
        Hashtable hints = new Hashtable();
        hints.put(EncodeHintType.ERROR_CORRECTION, ErrorCorrectionLevel.H);
        hints.put(EncodeHintType.CHARACTER_SET, CHARSET);
        hints.put(EncodeHintType.MARGIN, 1);
        BitMatrix bitMatrix = new MultiFormatWriter().encode(content, BarcodeFormat.QR_CODE, QRCODE_SIZE, QRCODE_SIZE,
                hints);
        int width = bitMatrix.getWidth();
        int height = bitMatrix.getHeight();
        BufferedImage image = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
        for (int x = 0; x < width; x++) {
            for (int y = 0; y < height; y++) {
                image.setRGB(x, y, bitMatrix.get(x, y) ? 0xFF000000 : 0xFFFFFFFF);
            }
        }
        if (imgPath == null || "".equals(imgPath)) {
            return image;
        }
        // 插入图片
        QRCodeUtil.insertImage(image, imgPath, needCompress);
        return image;
    }

    //创建底部有文字的二维码
    private static BufferedImage createImage(String content, InputStream imgPath, String note,boolean needCompress) throws Exception {
        BufferedImage image = createImage(content,  imgPath,  needCompress);
        BufferedImage imageWithFont = createImageWithFont(image, note);
        return imageWithFont;
    }

    //创建底部有文字的二维码
    private static BufferedImage createImage(String content, String imgPath, String note,boolean needCompress) throws Exception {
        BufferedImage image = createImage(content,  imgPath,  needCompress);
        BufferedImage imageWithFont = createImageWithFont(image, note);
        return imageWithFont;
    }

    //给二维码底部添加文字
    private static BufferedImage createImageWithFont(BufferedImage image,String note){
        Image src = image.getScaledInstance(300, 300,Image.SCALE_DEFAULT);
        BufferedImage tag;
        if (note.length()<=16){
            tag = new BufferedImage(300, 322,BufferedImage.TYPE_INT_RGB);
        }else{
            tag = new BufferedImage(300, 345,BufferedImage.TYPE_INT_RGB);
        }
        Graphics g1 = tag.getGraphics();//设置低栏白边
        Graphics2D g2 = tag.createGraphics();//设置文字
        Font font = new Font("微软雅黑", Font.BOLD,18);
        g2.setFont(font);
        g2.setColor(Color.BLACK);
        if (note.length()<=16) {
            g1.fillRect(0, QRCODE_SIZE, QRCODE_SIZE, 22);
            g2.drawString(note,QRCODE_SIZE/2-note.length()*8-14, QRCODE_SIZE+font.getSize());
        }else{
            g1.fillRect(0, QRCODE_SIZE, QRCODE_SIZE, 45);
            g2.drawString(note.substring(0, 16),5, QRCODE_SIZE+font.getSize());
            g2.drawString(note.substring(16,note.length()), QRCODE_SIZE/2-(note.length()-16)*8-14, QRCODE_SIZE+font.getSize()*2+4);
        }
        g1.drawImage(src, 0, 0, null);
        g1.dispose();
        g2.dispose();
        image = tag;
        image.flush();
        return image;
    }

    private static void insertImage(BufferedImage source, InputStream imgPath, boolean needCompress) throws Exception {
        Image src = ImageIO.read(imgPath);
        int width = src.getWidth(null);
        int height = src.getHeight(null);
        if (needCompress) { // 压缩LOGO
            if (width > WIDTH) {
                width = WIDTH;
            }
            if (height > HEIGHT) {
                height = HEIGHT;
            }
            Image image = src.getScaledInstance(width, height, Image.SCALE_SMOOTH);
            BufferedImage tag = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
            Graphics g = tag.getGraphics();
            g.drawImage(image, 0, 0, null); // 绘制缩小后的图
            g.dispose();
            src = image;
        }
        // 插入LOGO
        Graphics2D graph = source.createGraphics();
        int x = (QRCODE_SIZE - width) / 2;
        int y = (QRCODE_SIZE - height) / 2;
        graph.drawImage(src, x, y, width, height, null);
        Shape shape = new RoundRectangle2D.Float(x, y, width, width, 6, 6);
        graph.setStroke(new BasicStroke(3f));
        graph.draw(shape);
        graph.dispose();
    }

    private static void insertImage(BufferedImage source, String imgPath, boolean needCompress) throws Exception {
        File file = new File(imgPath);
        if (!file.exists()) {
            System.err.println("" + imgPath + "   该文件不存在！");
            return;
        }
        Image src = ImageIO.read(new File(imgPath));
        int width = src.getWidth(null);
        int height = src.getHeight(null);
        if (needCompress) { // 压缩LOGO
            if (width > WIDTH) {
                width = WIDTH;
            }
            if (height > HEIGHT) {
                height = HEIGHT;
            }
            Image image = src.getScaledInstance(width, height, Image.SCALE_SMOOTH);
            BufferedImage tag = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
            Graphics g = tag.getGraphics();
            g.drawImage(image, 0, 0, null); // 绘制缩小后的图
            g.dispose();
            src = image;
        }
        // 插入LOGO
        Graphics2D graph = source.createGraphics();
        int x = (QRCODE_SIZE - width) / 2;
        int y = (QRCODE_SIZE - height) / 2;
        graph.drawImage(src, x, y, width, height, null);
        Shape shape = new RoundRectangle2D.Float(x, y, width, width, 6, 6);
        graph.setStroke(new BasicStroke(3f));
        graph.draw(shape);
        graph.dispose();
    }

    public static void encode(String content, String imgPath, String destPath, boolean needCompress) throws Exception {
        BufferedImage image = QRCodeUtil.createImage(content, imgPath, needCompress);
        mkdirs(destPath);
        // String file = new Random().nextInt(99999999)+".jpg";
        // ImageIO.write(image, FORMAT_NAME, new File(destPath+"/"+file));
        ImageIO.write(image, FORMAT_NAME, new File(destPath));
    }

    public static void encode(String content, String imgPath, String destPath, String note, boolean needCompress) throws Exception {
        BufferedImage image = QRCodeUtil.createImage(content, imgPath, note,needCompress);
        mkdirs(destPath);
        // String file = new Random().nextInt(99999999)+".jpg";
        // ImageIO.write(image, FORMAT_NAME, new File(destPath+"/"+file));
        ImageIO.write(image, FORMAT_NAME, new File(destPath));
    }

    public static void encode(String content, InputStream imgPath, String destPath, String note, boolean needCompress) throws Exception {
        BufferedImage image = QRCodeUtil.createImage(content, imgPath, note,needCompress);
        mkdirs(destPath);
        // String file = new Random().nextInt(99999999)+".jpg";
        // ImageIO.write(image, FORMAT_NAME, new File(destPath+"/"+file));
        ImageIO.write(image, FORMAT_NAME, new File(destPath));
    }

    //用content生成二维码的MultipartFile
    public static MultipartFile encodeToMultipartFile(String content, String imgPath,boolean needCompress) throws Exception {
        BufferedImage image = QRCodeUtil.createImage(content, imgPath, needCompress);
        ByteArrayOutputStream os = new ByteArrayOutputStream();
        ImageIO.write(image,"jpg", os);
        InputStream is = new ByteArrayInputStream(os.toByteArray());
        MultipartFile multipartFile = new MockMultipartFile("文件名","www.jpg","jpg",is);
        return multipartFile;
    }

    public static BufferedImage encode(String content, String imgPath, boolean needCompress) throws Exception {
        BufferedImage image = QRCodeUtil.createImage(content, imgPath, needCompress);
        return image;
    }

    public static void mkdirs(String destPath) {
        File file = new File(destPath);
        // 当文件夹不存在时，mkdirs会自动创建多层目录，区别于mkdir．(mkdir如果父目录不存在则会抛出异常)
        if (!file.exists() && !file.isDirectory()) {
            file.mkdirs();
        }
    }

    public static void encode(String content, String imgPath, String destPath) throws Exception {
        //QRCodeUtil.encode(content, imgPath, destPath, false);
        QRCodeUtil.encode(content, imgPath, destPath, true);
    }

    public static void encode(String content, String imgPath, String destPath,String note) throws Exception {
        //QRCodeUtil.encode(content, imgPath, destPath, false);
        QRCodeUtil.encode(content, imgPath, destPath, note,true);
    }

    public static void encode(String content, InputStream imgPath, String destPath,String note) throws Exception {
        //QRCodeUtil.encode(content, imgPath, destPath, false);
        QRCodeUtil.encode(content, imgPath, destPath, note,true);
    }

    // 被注释的方法
    /*
     * public static void encode(String content, String destPath, boolean
     * needCompress) throws Exception { QRCodeUtil.encode(content, null, destPath,
     * needCompress); }
     */

    public static void encode(String content, String destPath) throws Exception {
        QRCodeUtil.encode(content, null, destPath, false);
    }

    public static void encode(String content, String imgPath, OutputStream output, boolean needCompress)
            throws Exception {
        BufferedImage image = QRCodeUtil.createImage(content, imgPath, needCompress);
        ImageIO.write(image, FORMAT_NAME, output);
    }

    public static void encode(String content, OutputStream output) throws Exception {
        QRCodeUtil.encode(content, null, output, false);
    }

    public static String decode(File file) throws Exception {
        BufferedImage image;
        image = ImageIO.read(file);
        if (image == null) {
            return null;
        }
        BufferedImageLuminanceSource source = new BufferedImageLuminanceSource(image);
        BinaryBitmap bitmap = new BinaryBitmap(new HybridBinarizer(source));
        Result result;
        Hashtable hints = new Hashtable();
        hints.put(DecodeHintType.CHARACTER_SET, CHARSET);
        result = new MultiFormatReader().decode(bitmap, hints);
        String resultStr = result.getText();
        return resultStr;
    }

    public static String decode(String path) throws Exception {
        return QRCodeUtil.decode(new File(path));
    }

}
