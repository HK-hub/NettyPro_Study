package com.hk.vfs.controller;

import javafx.fxml.FXML;
import javafx.scene.control.TableView;
import lombok.NoArgsConstructor;



/**
 * @author : HK意境
 * @ClassName : FileController
 * @date : 2021/12/21 20:02
 * @description :
 * @Todo :
 * @Bug :
 * @Modified :
 * @Version : 1.0
 */
@NoArgsConstructor
public class FileController {

    // 文件目录列表
    @FXML
    private TableView fileTableView ;
    
    /**
     * @methodName : 初始化完成之后，会执行该方法
     * @author : HK意境
     * @date : 2021/12/21 20:03
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
    public void initialize(){
        System.out.println("FileController 初始化");
    }


}
