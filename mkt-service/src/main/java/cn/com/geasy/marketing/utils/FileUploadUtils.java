package cn.com.geasy.marketing.utils;

import org.springframework.web.multipart.MultipartFile;

import java.io.*;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Date;

import static java.lang.Runtime.getRuntime;

/**
 * Created by Think on 2018/2/11.
 */
public class FileUploadUtils {

    private static String imageUrl="/anxinImage/";

    private static String htmlUrl="/anxinHtml/";


    public static String  handleFileUploadImage(MultipartFile file, String imagePath, String domainUrl){
        String dateDir=DateUtils.getDate2FormatString(new Date(),"yyyyMMdd" )+"/";
        if(!file.isEmpty()){
            try {
                imagePath =imagePath+ dateDir;
                mkdir(imagePath);
                String fileName=imagePath+file.getOriginalFilename();
                File saveFile= new File(fileName);
                saveFile.createNewFile();
                chmod(fileName);
                BufferedOutputStream out = new BufferedOutputStream(new FileOutputStream(saveFile));
                out.write(file.getBytes());
                out.flush();
                out.close();
            } catch (FileNotFoundException e) {
                e.printStackTrace();
                return  "上传失败,"+e.getMessage();
            } catch (IOException e) {
                e.printStackTrace();
                return "上传失败,"+e.getMessage();
            }
            return  domainUrl+imageUrl+dateDir+file.getOriginalFilename();
        }else{
            return "上传失败，因为文件是空的.";
        }
    }


    /**
     * 爬虫url的图片
     *
     * @param imgUrl
     * @return
     */
    public static String getSpiderImageUrl(String imgUrl,String imagePath,String domainUrl) {
        String dateDir=DateUtils.getDate2FormatString(new Date(),"yyyyMMdd" )+"/";
        InputStream dis = null;
        try {
            URL url = new URL(imgUrl);
            try {
                dis = new DataInputStream(url.openStream());
            } catch (IOException e) {
                e.printStackTrace();
            }
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }
        imagePath =imagePath+ dateDir;
        mkdir(imagePath);
        String image= IdGenerator.getLongId()+".jpg";
        String newImageName=imagePath+image;
        creatFile(newImageName,dis);
        return domainUrl+imageUrl+dateDir+image;
    }


    /**
     * 保存文本 html的内容
     *
     * @return
     */
    public static String saveText(String body,String htmlPath,String domainUrl) {
        String dateDir=DateUtils.getDate2FormatString(new Date(),"yyyyMMdd" )+"/";
        InputStream   in_withcode   = null;
        try {
            in_withcode = new ByteArrayInputStream(body.getBytes("UTF-8"));
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        htmlPath =htmlPath+ dateDir;
        mkdir(htmlPath);
        String htmlName= IdGenerator.getLongId()+".html";
        String newHtmlName=htmlPath+htmlName;
        creatFile(newHtmlName, in_withcode);
        return domainUrl+htmlUrl+dateDir+htmlName;
    }


    public static void mkdir(String filePath){

        File targetFile = new File(filePath);
        if(!targetFile.exists()){
            targetFile.mkdirs();
            chmod(filePath);
        }
    }

    public static  void chmod(String filePath){
        Runtime runtime = getRuntime();
        String command = "chmod 775 " + filePath;
        try {
            Process process = runtime.exec(command);
            process.waitFor();
            int existValue = process.exitValue();
            if(existValue != 0){
                System.out.println( "Change file permission failed.");
            }
        } catch (Exception e) {
            System.out.println( "Command execute failed.");
        }
    }

    public static void creatFile(String fileName, InputStream dis){
        //建立一个新的文件
        FileOutputStream fos = null;
        File saveFile= new File(fileName);
        try {
            saveFile.createNewFile();
        } catch (IOException e) {
            e.printStackTrace();
        }
        chmod(fileName);
        try {
            fos = new FileOutputStream(saveFile);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        byte[] buffer = new byte[1024];
        int length;
        //开始填充数据
        try {
            while((length = dis.read(buffer))>0){
                fos.write(buffer,0,length);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        try {
            dis.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        try {
            fos.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
