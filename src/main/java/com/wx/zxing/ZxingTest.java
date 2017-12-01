package com.wx.zxing;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.EncodeHintType;
import com.google.zxing.Writer;
import com.google.zxing.WriterException;
import com.google.zxing.client.j2se.MatrixToImageConfig;
import com.google.zxing.client.j2se.MatrixToImageWriter;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.datamatrix.encoder.SymbolShapeHint;
import com.google.zxing.qrcode.QRCodeWriter;
import com.google.zxing.qrcode.decoder.ErrorCorrectionLevel;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;

public class ZxingTest {

    public static void main(String[] args) {
//       create();
//        read();
        try {
            test2();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (WriterException e) {
            e.printStackTrace();
        }
    }

    public static void test2() throws WriterException, IOException {

//        需要输入的参数
        int version = 4;        //二维码版本
        int margin = 0;         //二维码白边宽度
        int onColor = 0xFFAFEEAA; //二维码线条颜色
        int offColor = 0xFFFFFFFF;//二维码背景色
        String contents = "https://www.baidu.com/";//二维码内容
        int width1 = 145;//二维码宽 x
        int height1 = 145;//二维码高 y
        String pathname = "e:/fff.png";//背景图读取地址 也是最终图片输出路径

        int  tempW = 135;//二维码起始点位置x
        int  tempH = 200;//二维码起始点位置y

//        生成二维码
        Map map = new HashMap();//生成二维码配置
        map.put(EncodeHintType.ERROR_CORRECTION, ErrorCorrectionLevel.M);//纠错等级
        map.put(EncodeHintType.DATA_MATRIX_SHAPE, SymbolShapeHint.FORCE_SQUARE);//二维码矩阵形状
        map.put(EncodeHintType.QR_VERSION, version);//二维码版本
        map.put(EncodeHintType.MARGIN, margin);//二维码白边宽度 文件上要求最小是4

        Writer writer = new QRCodeWriter();
        BitMatrix bitMatrix = writer.encode(contents, BarcodeFormat.QR_CODE, width1, height1, map);


//        生成背景图

//        计算背景图线条宽度 即像素块大小
        int inputWidth = (version - 1) * 4 + 21;//根据版本计算二维码的码元尺寸
        int inputHeight = inputWidth;
        int qrWidth = inputWidth + (margin * 2);
        int qrHeight = inputHeight + (margin * 2);
        int outputWidth = Math.max(width1, qrWidth);
        int outputHeight = Math.max(height1, qrHeight);
        int xintiao = Math.min(outputWidth / qrWidth, outputHeight / qrHeight);


        File file = new File(pathname);
        BufferedImage bufferedImage = ImageIO.read(file);
        int width = bufferedImage.getWidth(); //背景图的宽x
        int height = bufferedImage.getHeight(); //背景图的高y
        int LWidthNum = width/xintiao;//宽能容纳的像素块个数
        int LHigthtNum = height/xintiao;//高能容纳的像素块个数
        int leftPadding = (width - (LWidthNum * xintiao)) / 2;//宽的白边
        int topPadding = (height - (LHigthtNum * xintiao)) / 2;//宽的白边
        Random random = new Random();

        //生成0、1随机矩阵
        int[][] input = new int[LWidthNum][LHigthtNum];
        for (int i = 0; i < input.length; i++){
            for (int j = 0; j < input[0].length; j++){
                input[i][j] = random.nextInt(2);
            }
        }
        BitMatrix output = new BitMatrix(width, height);
        for (int inputY = 0, outputY = topPadding; inputY < LHigthtNum; inputY++, outputY += xintiao) {
            // 将随机矩阵编程像素块矩阵
            for (int inputX = 0, outputX = leftPadding; inputX < LWidthNum; inputX++, outputX += xintiao) {
                if (input[inputX][inputY] == 1) {
                    output.setRegion(outputX, outputY, xintiao, xintiao);
                }
            }
        }
        MatrixToImageConfig config = new MatrixToImageConfig(onColor, offColor);
        BufferedImage bufferedImage1 = MatrixToImageWriter.toBufferedImage(output,config);

        for (int j = 0; j < height; j++) {
            for (int i = 0; i < width; i++) {
                int pixel = bufferedImage.getRGB(i, j);
                if (pixel == -1 || bufferedImage1.getRGB(i,j) == -1){
                    bufferedImage1.setRGB(i,j,offColor);
                }
            }
        }

//        将二维码放入背景图中
        for (int startX = 0 ; startX < bitMatrix.getWidth(); startX++){
            for (int startY = 0; startY < bitMatrix.getHeight(); startY++){
                System.out.println(bitMatrix.get(startX, startY) + "#####" + startX + "-----" +  startY);
                if(bitMatrix.get(startX, startY)){
                    bufferedImage1.setRGB(startX+tempW,startY+tempH,onColor);
                } else if ( bufferedImage1.getRGB(startX+tempW, startY+tempH) != -1){
                    bufferedImage1.setRGB(startX+tempW,startY+tempH,offColor);
                }

            }
        }


        ImageIO.write(bufferedImage1, "png", file);

    }





//
//
//    public static void create(){
//
////        File file = new File("e:/ddd.png");
//
//            Writer writer = new QRCodeWriter();
//            String contents = "https://www.baidu.com/";
////        contents = "中文";
//            Map map = new HashMap();
////        map.put(EncodeHintType.CHARACTER_SET, "utf-8");
//            map.put(EncodeHintType.ERROR_CORRECTION, ErrorCorrectionLevel.M);
//            map.put(EncodeHintType.DATA_MATRIX_SHAPE, SymbolShapeHint.FORCE_RECTANGLE);
//            map.put(EncodeHintType.QR_VERSION, 3);
//            map.put(EncodeHintType.MARGIN, 0);
//
//            try {
//                BitMatrix bitMatrix = writer.encode(contents, BarcodeFormat.QR_CODE, 50, 50, map);
//                Path path = new File("e:/img.png").toPath();
////            MatrixToImageWriter.writeToFile(bitMatrix, "png", path);
//                MatrixToImageWriter.writeToPath(bitMatrix, "png", path);
//
////            BufferedImage bufferedImage = MatrixToImageWriter.toBufferedImage(bitMatrix);
//
//            } catch (WriterException e) {
//                e.printStackTrace();
//            } catch (IOException e) {
//                e.printStackTrace();
//            }
//        }

//    public static void read(){
//        Reader reader = new QRCodeReader();
//        File path = new File("e:/image.png");
//        try {
//            BufferedImage bufferedImage = ImageIO.read(path);
//            BinaryBitmap binaryBitmap = new BinaryBitmap(new HybridBinarizer(new BufferedImageLuminanceSource(bufferedImage)));
//            Result result = reader.decode(binaryBitmap);
//            System.out.println(result.getText());
//        } catch (NotFoundException e) {
//            e.printStackTrace();
//        } catch (ChecksumException e) {
//            e.printStackTrace();
//        } catch (FormatException e) {
//            e.printStackTrace();
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//    }

}
