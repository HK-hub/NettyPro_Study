package com.hk.vfs.model;

import javafx.beans.property.*;
import javafx.fxml.FXML;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import lombok.experimental.Accessors;

import java.util.Date;

/**
 * @author : HK意境
 * @ClassName : HFile
 * @date : 2021/12/18 21:54
 * @description :
 * @Todo :
 * @Bug :
 * @Modified :
 * @Version : 1.0
 */
@Data
@Accessors(chain = true)
@NoArgsConstructor
public class HFile {

    // 文件名称
    //@FXML
    private String fileName ;

    // 文件扩展名
    //@FXML
    private String extend ;

    // 文件目录
    //@FXML
    private String path ;

    // 文件大小 , 单位kb
    //@FXML
    private Double size ;

    // 文件类型：文件，目录
    //@FXML
    private String type ;

    // 文件所有者
    //@FXML
    private String user ;

    // 读取次数
    //@FXML
    private Integer readCount ;

    // 文件修改次数
    //@FXML
    private Integer writeCount ;

    // 文件修改事件
    //@FXML
    private String updateTime ;

    // 文件创建时间
    //@FXML
    private String createTime ;


    public HFile(String filename ,String updateTime ,String type ,Double size){

        this.updateTime = updateTime;
        this.type = "dir".equals(type)?"dir":"file";
        this.extend = type ;
        this.size = size;
        this.fileName = filename;

    }


    // 显示文件信息
    public String getHFileInfo(){

        return "fileName=" + fileName + '\n' +
                "extend=" + extend +'\n' +
                "path=" + path  + '\n' +
                "size=" + size +'\n' +
                "type=" + type  +'\n' +
                "updateTime=" + updateTime +'\n' +
                "createTime=" + createTime;
    }

    @Override
    public String toString() {
        return "HFile{" +
                "fileName='" + fileName + '\'' +
                ", extend='" + extend + '\'' +
                ", path='" + path + '\'' +
                ", size=" + size +
                ", type='" + type + '\'' +
                ", user='" + user + '\'' +
                ", readCount=" + readCount +
                ", writeCount=" + writeCount +
                ", updateTime='" + updateTime + '\'' +
                ", createTime='" + createTime + '\'' +
                '}';
    }
}
