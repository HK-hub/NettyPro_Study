package com.hk.vfs.mapper;

import com.hk.vfs.constant.PWD;
import com.hk.vfs.model.HFile;

import java.io.*;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.LinkOption;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.attribute.BasicFileAttributeView;
import java.nio.file.attribute.BasicFileAttributes;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * @author : HK意境
 * @ClassName : HFileMapper
 * @date : 2021/12/22 13:22
 * @description :
 * @Todo :
 * @Bug :
 * @Modified :
 * @Version : 1.0
 */
public class HFileMapper {

    public static String path = "D:\\C++Code" ;


    // 获取当前目录下的所有文件和目录信息转换为HFile 类
    public static List<HFile> getJustCurrentPathAllFilesAndDirectory() throws IOException {

        File file = new File(path);
        File[] files = file.listFiles();

        List<HFile> hFiles = new ArrayList<>();

        for (File f : files) {
            //System.out.println(f.getName() + "\t " + getFileType(f.getName()));
            Path path = Paths.get(f.getAbsolutePath());
            BasicFileAttributeView basicview = Files.getFileAttributeView(path, BasicFileAttributeView.class, LinkOption.NOFOLLOW_LINKS);
            BasicFileAttributes attr = basicview.readAttributes();

            HFile hFile = new HFile(f.getName(),new Date(f.lastModified()).toString(),getFileType(f.getName()),Double.parseDouble((f.length()/1024)+""));
            hFile.setPath(f.getPath()).setCreateTime(new Date(attr.creationTime().toMillis()).toString());
            hFiles.add(hFile) ;
        }

        return hFiles;
    }


    // 获取文件扩展名，文件类型
    // 文件的绝对路径
    public static String getFileType(String fileName){

        String type = "dir" ;
        if (fileName.contains(".")){
           type =fileName.substring(fileName.lastIndexOf("."));
        }
        return type ;
    }




    /**
     * @methodName : 新建文件
     * @author : HK意境
     * @date : 2021/12/22 15:35
     * @description :
     * @Todo :
     * @params :
         * @param : null
     * @return : null
     * @throws:
     * @Bug :
     * @Modified :
     * @Version : 1.0
     */
    public static HFile createHFile(HFile hFile) throws IOException {

        if (hFile == null){
            return null ;
        }

        // 新建文件
        String fileName = hFile.getFileName();
        String extend = hFile.getExtend();
        String path = hFile.getPath();

        // 创建文件
        boolean res = new File(PWD.currentPath + "/" + fileName + "."+extend).createNewFile();

        if (res){
            // 创建文件成功
            hFile.setCreateTime(new Date().toString()).setUpdateTime(hFile.getCreateTime()).setReadCount(0).setWriteCount(0) ;
            hFile.setPath(PWD.currentPath + "\\" + fileName + "." + extend) ;
            hFile.setFileName(fileName + "." + extend);
            hFile.setExtend("."+extend);
            hFile.setType("file");
            return hFile;
        }

        return null ;
    }



    /**
     * @methodName : 新建文件夹
     * @author : HK意境
     * @date : 2021/12/22 19:05
     * @description :
     * @Todo :
     * @params :
         * @param : null
     * @return : null
     * @throws:
     * @Bug :
     * @Modified :
     * @Version : 1.0
     */
    public static HFile createHDirectory(HFile hFile) throws IOException {

        if (hFile == null){
            return null ;
        }

        // 新建文件
        String fileName = hFile.getFileName();
        String path = hFile.getPath();

        // 创建文件夹
        boolean res = false;
        File dir = new File(PWD.currentPath + "/" + fileName);

        if (!dir.exists()) {// 判断目录是否存在
            res = dir.mkdir();
        }

        if (res){
            // 创建文件成功
            hFile.setCreateTime(new Date().toString())
                    .setUpdateTime(hFile.getCreateTime())
                    .setPath(dir.getPath())
                    .setReadCount(0)
                    .setWriteCount(0) ;
            hFile.setType("dir");
            return hFile;
        }

        return null ;
    }


    /**
     * @methodName : 删除文件夹，文件
     * @author : HK意境
     * @date : 2021/12/22 19:31
     * @description :
     * @Todo :
     * @params :
         * @param : null
     * @return : null
     * @throws:
     * @Bug :
     * @Modified :
     * @Version : 1.0
     */
    public static boolean deleteFileOrDirectory(String fileName){

        File file = new File(fileName);
        if (!file.exists()) {
            System.out.println("删除文件失败:" + fileName + "不存在！");
            return false;
        } else {

            if (file.isFile()) {
                // 删除文件
                return deleteFile(fileName);
            } else {
                // 删除目录
                return deleteDirectory(fileName);
            }
        }

    }


    /**
     * @methodName : 删除单个普通文件
     * @author : HK意境
     * @date : 2021/12/22 19:37
     * @description :
     * @Todo :
     * @params :
         * @param : null
     * @return : null
     * @throws:
     * @Bug :
     * @Modified :
     * @Version : 1.0
     */
    public static boolean deleteFile(String fileName) {
        File file = new File(fileName);
        // 如果文件路径所对应的文件存在，并且是一个文件，则直接删除
        if (file.exists() && file.isFile()) {
            if (file.delete()) {
                System.out.println("删除单个文件" + fileName + "成功！");
                return true;
            } else {
                System.out.println("删除单个文件" + fileName + "失败！");
                return false;
            }
        } else {
            System.out.println("删除单个文件失败：" + fileName + "不存在！");
            return false;
        }
    }


    /**
     * 删除目录及目录下的文件
     *
     * @param dir
     *            要删除的目录的文件路径
     * @return 目录删除成功返回true，否则返回false
     */
    public static boolean deleteDirectory(String dir) {
        // 如果dir不以文件分隔符结尾，自动添加文件分隔符
        if (!dir.endsWith(File.separator)) {
            dir = dir + File.separator;
        }
        File dirFile = new File(dir);
        // 如果dir对应的文件不存在，或者不是一个目录，则退出
        if ((!dirFile.exists()) || (!dirFile.isDirectory())) {
            System.out.println("删除目录失败：" + dir + "不存在！");
            return false;
        }
        boolean flag = true;

        // 删除文件夹中的所有文件包括子目录
        File[] files = dirFile.listFiles();
        for (int i = 0; i < files.length; i++) {
            // 删除子文件
            if (files[i].isFile()) {
                flag = deleteFile(files[i].getAbsolutePath());
                if (!flag) {
                    break;
                }
            }
            // 删除子目录
            else if (files[i].isDirectory()) {
                flag = deleteDirectory(files[i]
                        .getAbsolutePath());
                if (!flag) {
                    break;
                }
            }
        }
        if (!flag) {
            System.out.println("删除目录失败！");
            return false;
        }
        // 删除当前目录
        if (dirFile.delete()) {
            System.out.println("删除目录" + dir + "成功！");
            return true;
        } else {
            return false;
        }
    }



    /**
     * @methodName : 获取指定文件的内容
     * @author : HK意境
     * @date : 2021/12/23 14:46
     * @description :
     * @Todo :
     * @params :
         * @param : null
     * @return : null
     * @throws:
     * @Bug :
     * @Modified :
     * @Version : 1.0
     */
    public static String getFileContent(String fileName){

        String sb = readFileByLines(fileName);

        return sb;
    }


    // 将数据写入文件中 : 全部重写
    public static Boolean writeContentToFile(String fileName, byte[] bytes) throws IOException {

        RandomAccessFile accessFile = new RandomAccessFile(fileName, "rw");

        ByteBuffer byteBuffer = ByteBuffer.wrap(bytes);
        FileChannel channel = accessFile.getChannel();
        int write = channel.write(byteBuffer);

        return write != 0 ;
    }



    /**
     * 以行为单位读取文件，常用于读面向行的格式化文件
     */
    public static String readFileByLines(String fileName) {
        File file = new File(fileName);
        if (!file.exists() || file.isDirectory()){
            // 文件不存在或不是可读文件

        }
        BufferedReader reader = null;
        StringBuffer sb = new StringBuffer();
        try {
            reader = new BufferedReader(new FileReader(file));
            String tempString = null;
            int line = 1;
            // 一次读入一行，直到读入null为文件结束
            while ((tempString = reader.readLine()) != null) {
                // 显示行号
                sb.append(tempString).append('\n');
            }
            reader.close();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (reader != null) {
                try {
                    reader.close();
                } catch (IOException e1) {
                }
            }
        }

        return sb.toString() ;
    }





    public static void main(String[] args) throws IOException {

        byte[] bytes = "11111111111111111111111111111111222222222222222222222222333333333333333333".getBytes(StandardCharsets.UTF_8);

        Boolean aBoolean = writeContentToFile("D:\\C++Code\\newfile.txt", bytes);
        System.out.println(aBoolean);

    }


}
