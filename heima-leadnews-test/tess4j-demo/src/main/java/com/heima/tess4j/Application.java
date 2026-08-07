package com.heima.tess4j;


import net.sourceforge.tess4j.ITesseract;
import net.sourceforge.tess4j.Tesseract;
import net.sourceforge.tess4j.TesseractException;

import java.io.File;

public class Application {

    /**
     * 识别图片中的文字
     * @param args
     */
    public static void main(String[] args) throws TesseractException {
        try{
            //创建实例
            ITesseract  tesseract= new Tesseract();
            //设置字体库路径
            tesseract.setDatapath("F:/all-person-program/springcloud20260730/test");

            //设置语言
            tesseract.setLanguage("chi_sim");

            File file = new File("F:\\\\all-person-program\\\\springcloud20260730\\\\day04-文章审核-敏感词过滤\\\\image-20210524161243572.png");

            //识别图片
            String result = tesseract.doOCR(file);
            //替换回车和tal键  使结果为一行
            result = result.replaceAll("\\r|\\n","-").replaceAll(" ","");
            System.out.println("识别的结果为："+result);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
