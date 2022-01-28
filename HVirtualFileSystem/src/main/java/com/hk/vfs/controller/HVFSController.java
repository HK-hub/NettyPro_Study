package com.hk.vfs.controller;

import com.hk.vfs.component.*;
import com.hk.vfs.constant.PWD;
import com.hk.vfs.mapper.HFileMapper;
import com.hk.vfs.model.HFile;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.IOException;
import java.util.ArrayList;

/**
 * @author : HK意境
 * @ClassName : HVFSController
 * @date : 2021/12/18 22:17
 * @description :
 * @Todo :
 * @Bug :
 * @Modified :
 * @Version : 1.0
 */
@Data
@NoArgsConstructor
public class HVFSController {

    public static ObservableList<HFile> filesList ;

    // 前进后退按钮
    @FXML
    private Button forwardButton ;
    @FXML
    private Button backwardButton ;

    // 文件目录列表
    @FXML
    private TableView fileTableView ;
    @FXML
    private TableColumn fileNameColumn ;
    @FXML
    private TableColumn updateTimeColumn ;
    @FXML
    private TableColumn typeColumn ;
    @FXML
    private TableColumn sizeColumn ;

    // 文件操作按钮组
    @FXML
    private Button dirAddButton ;
    @FXML
    private Button fileAddButton;
    @FXML
    private Button checkFileButton;
    @FXML
    private Button modifyFileButton;
    @FXML
    private Button deleteFileButton ;

    // 文件详情信息
    @FXML
    private TextArea fileInfoTextArea ;

    // 当前工作目录
    @FXML
    private TextField currentPath ;


    // 全局初始化配置
    @FXML
    public void initialize() throws IOException {

        System.out.println("controller 初始化");

        // 中心数据集初始化
        centerInitialize();

    }


    // center 部分初始化
    public void centerInitialize() throws IOException {
        // 数据集合
        filesList = FXCollections.observableArrayList(HFileMapper.getJustCurrentPathAllFilesAndDirectory());
        // 设置属性列
        fileNameColumn.setCellValueFactory(new PropertyValueFactory<HFile, SimpleStringProperty>("fileName"));
        updateTimeColumn.setCellValueFactory(new PropertyValueFactory<HFile,SimpleStringProperty>("updateTime"));
        typeColumn.setCellValueFactory(new PropertyValueFactory<HFile,SimpleStringProperty>("extend"));
        sizeColumn.setCellValueFactory(new PropertyValueFactory<HFile, SimpleDoubleProperty>("size"));
        fileTableView.setItems(filesList);


        // 当前工作目录
        currentPath.setText(PWD.currentPath);

        // 选中某一行
        fileTableView.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<HFile>() {
            @Override
            public void changed(
                    ObservableValue<? extends HFile> observableValue,
                    HFile oldItem, HFile newItem) {
                //System.out.println(newItem.getFileName());
                // 显示详细文件信息
                fileInfoTextArea.setText(newItem.getHFileInfo());

            }

        });


    }


    /**
     * @methodName : 后退
     * @author : HK意境
     * @date : 2021/12/21 20:13
     * @description : 点击后退按钮，返回上一级目录
     * @Todo : 添加 backward 按钮点击事件
     * @params :
         * @param : null
     * @return : null
     * @throws:
     * @Bug :
     * @Modified :
     * @Version : 1.0
     */
    @FXML
    public void backwardAction(){

        System.out.println(backwardButton.getText() + " 被点击了");

    }

    /**
     * @methodName : 前进，
     * @author : HK意境
     * @date : 2021/12/21 20:39
     * @description :
     * @Todo : 进入栈内下一级目录文件
     * @params :
         * @param : null
     * @return : null
     * @throws:
     * @Bug :
     * @Modified :
     * @Version : 1.0
     */
    @FXML
    public void forwardAction(){

        System.out.println(forwardButton.getText() + "被点击了");
    }

    
    /**
     * @methodName : 新建文件
     * @author : HK意境
     * @date : 2021/12/22 14:02
     * @description :
     * @Todo : 填写信息新建文件，控制权限
     * @params :
         * @param : null
     * @return : null
     * @throws:
     * @Bug :
     * @Modified :
     * @Version : 1.0
     */
    @FXML
    public void createNewFileAction() throws IOException, InterruptedException {

        System.out.println("新建文件被点击了");

        // 打开弹窗
        NewFileForm.display("新建文件","");

    }
    
    
    /**
     * @methodName : 新建文件夹
     * @author : HK意境
     * @date : 2021/12/22 14:04
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
    @FXML
    public void createNewDirectoryAction(){
        System.out.println("新建文件夹别点击了");
        NewDirectoryForm.display("新建目录文件","");

    }
    
    /**
     * @methodName : 修改文件，文件夹
     * @author : HK意境
     * @date : 2021/12/22 14:05
     * @description :
     * @Todo :
     * @params : 
         * @param : null
     * @return : int 返回修改的字节数，没有修改返回0，修改失败返回-1
     * @throws: 
     * @Bug :
     * @Modified :
     * @Version : 1.0
     */
    @FXML
    public int modifyFileAction(){

        System.out.println("修改文件/文件夹按钮被点击了");

        // 判断被选择的是文件还是文件夹
        if (filesList.size() <= 0){
            return -1;
        }

        // 获取选择行
        HFile selectedItem = (HFile) fileTableView.getSelectionModel().getSelectedItem();
        if (selectedItem == null){
            return 0 ;
        }

        // 判断是否是文件
        if (selectedItem.getType().equals("dir")) {
            // 不是文件
            return -1 ;
        }

        System.out.println("selected=" + selectedItem);
        // 获取被选择的行
        for (HFile hFile : filesList) {

            // 判断是否是待删除文件
            // 文件名称，文件路径，文件扩展名
            if (hFile.getFileName().equals(selectedItem.getFileName())
                    && hFile.getPath().equals(selectedItem.getPath())
                    && hFile.getExtend().equals(selectedItem.getExtend())){

                // 进行修改文件
                FileEditor.display(selectedItem.getFileName(),hFile.getPath());

            }

        }

        return 0 ;


    }


    /**
     * @methodName : 查看文件夹信息
     * @author : HK意境
     * @date : 2021/12/22 14:09
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
    public void checkFileInfoAction(){

        System.out.println("查看文件夹被点击了");

    }



    /**
     * @methodName : 删除文件，文件夹
     * @author : HK意境
     * @date : 2021/12/22 14:06
     * @description :
     * @Todo :
     * @params :
         * @param : null
     * @return : 返回成功删除的文件数量
     * @throws:
     * @Bug :
     * @Modified :
     * @Version : 1.0
     */
    @FXML
    public int deleteFileAction(){

        System.out.println("删除文件夹被点击了");

        if (filesList.size() <= 0){
            return 0;
        }

        // 获取选择行
        HFile selectedItem = (HFile) fileTableView.getSelectionModel().getSelectedItem();
        if (selectedItem == null){
            return 0 ;
        }

        System.out.println("selected=" + selectedItem);
        // 获取被选择的行
        for (HFile hFile : filesList) {

            // 判断是否是待删除文件
            // 文件名称，文件路径，文件扩展名
            if (hFile.getFileName().equals(selectedItem.getFileName()) && hFile.getPath().equals(selectedItem.getPath()) && hFile.getExtend().equals(selectedItem.getExtend())){
                // 删除文件
                boolean remove = filesList.remove(hFile);
                if (remove){
                    // 删除成功,需要删除磁盘上的实际文件
                    // 此处的 path 目录
                    boolean res = HFileMapper.deleteFileOrDirectory(selectedItem.getPath());

                    if (!res){
                        // 删除失败，回滚
                        System.out.println("删除：" + selectedItem.getPath() + "失败");
                        filesList.add(hFile) ;
                    }else {
                        // 提示删除成功
                        System.out.println("删除：" + selectedItem.getPath() + "成功");
                    }


                }
                return 1 ;
            }

        }

        return 0 ;
    }


    // 用户登录
    @FXML
    public int login(){

        LoginForm.display("登录","用户登录");

        return 0 ;

    }

}
