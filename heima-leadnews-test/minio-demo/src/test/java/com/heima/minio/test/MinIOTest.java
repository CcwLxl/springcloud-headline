package com.heima.minio.test;

import com.heima.file.service.FileStorageService;
import com.heima.minio.MinioApplication;
import io.minio.MinioClient;
import io.minio.PutObjectArgs;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.io.FileInputStream;
import java.io.FileNotFoundException;

//启动spring容器，classes哪个启动类来初始化容器 ，这样才能注入FileStorageService（别的模块的）
@SpringBootTest(classes = MinioApplication.class )
@RunWith(SpringRunner.class)
public class MinIOTest {

    @Autowired
    private FileStorageService fileStorageService;

    //把list.html文件上传到minio中，并且可以在浏览器中访问
    @Test
    public void test() throws FileNotFoundException {
        FileInputStream fileInputStream = new FileInputStream("F:/all-person-program/springcloud20260730/day01-环境搭建、SpringCloud微服务(注册发现、网关)/代码/list.html");
        String path = fileStorageService.uploadHtmlFile("", "list.html", fileInputStream);
        System.out.println(path);
    }

    /**
     * 把list.html文件上传到minio中，并且可以在浏览器中访问
     * @param args
     */
    public static void main(String[] args) throws FileNotFoundException {

       try{
           FileInputStream fileInputStream = new FileInputStream("F:/all-person-program/springcloud20260730/day02-app端文章查看，静态化freemarker,分布式文件系统minIO/新增文章类、微服务模块、模板、freemarker模板/css/index.css");

           //1、获取minio的链接信息 创建一个minio客户端
           MinioClient minioClient = MinioClient.builder().credentials("minioadmin", "minioadmin").endpoint("http://10.240.17.12:9000").build();
           //2、上传
           PutObjectArgs putObjectArgs = PutObjectArgs.builder()
                   .object("plugins/css/index.css") //文件名称
                   .contentType("text/css")  //文件类型
                   .bucket("leadnews")  //桶名称 与minio管理界面创建的桶一致;
                   .stream(fileInputStream,fileInputStream.available(),-1).build();
           minioClient.putObject(putObjectArgs);

           //访问路径
           System.out.println("http://10.240.17.12:9000/leadnews/list.html");

       }catch (Exception e){
           e.printStackTrace();
       }
    }
}
