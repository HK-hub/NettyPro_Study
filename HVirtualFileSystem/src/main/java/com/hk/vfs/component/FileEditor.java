package com.hk.vfs.component;

import com.hk.vfs.mapper.HFileMapper;
import com.hk.vfs.model.HFile;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.SneakyThrows;

import java.nio.charset.StandardCharsets;

/**
 * @author : HK意境
 * @ClassName : FileEditor
 * @date : 2021/12/23 14:18
 * @description :
 * @Todo :
 * @Bug :
 * @Modified :
 * @Version : 1.0
 */
@Data
@NoArgsConstructor
public class FileEditor {

    // 画布
    private static Scene scene ;
    // 布局
    private static BorderPane borderPane = new BorderPane();

    // 文本编辑器：TextArea
    private static TextArea editor = new TextArea();
    // 菜单栏
    private static MenuBar menuBar = new MenuBar();

    // 文件菜单：保存，删除
    private static Menu fileMenu = new Menu("File");
    // 保存文件
    private static MenuItem saveFileItem = new MenuItem("Save");
    // 删除文件
    private static MenuItem deleteFileItem = new MenuItem("Delete");

    // 编辑器：复制，粘贴
    private static Menu editMenu = new Menu("Edit") ;
    private static MenuItem copyFileItem = new MenuItem("Copy");

    // 当前操作文件
    private static HFile file ;
    private static String fileFullPath ;

    static {

        initialize();


    }



    private static void initialize(){

        scene = new Scene(borderPane) ;

        // 可以编辑
        editor.setEditable(true);
        // 不允许缓存
        editor.setCache(false);

        layout();

        // 事件绑定
        eventListener();
    }

    public static void display(String title, String filePath){

        // 数据初始化
        setData(filePath);


        // 设置初始化配置
        Stage stage = new Stage();
        stage.setTitle(title);
        stage.setWidth(920);
        stage.setHeight(580);
        stage.setScene(scene);

        // 展示
        stage.show();

    }



    public static void layout(){

        // 顶部
        // 设置菜单栏
        fileMenu.getItems().addAll(saveFileItem,deleteFileItem);
        editMenu.getItems().addAll(copyFileItem);
        menuBar.getMenus().addAll(fileMenu,editMenu) ;
        borderPane.setTop(menuBar) ;

        // 中心
        borderPane.setCenter(editor);


    }


    // 获取修改文件内容数据，进行填充
    public static void setData(String filePath){

        fileFullPath = filePath ;
        String fileContent = HFileMapper.getFileContent(filePath);
        editor.setText(fileContent);

    }


    // 事件监听
    public static void eventListener(){

        saveFileItem.setOnAction(new EventHandler<ActionEvent>() {
            @SneakyThrows
            @Override
            public void handle(ActionEvent event) {
                System.out.println("保存文件");

                Boolean aBoolean = HFileMapper.writeContentToFile(fileFullPath, editor.getText().getBytes(StandardCharsets.UTF_8));
                if (aBoolean){
                    // 保存成功
                    AlertForm.display("保存文件",fileFullPath + " 文件保存成功!!","info");

                }else{
                    // 保存成功
                    AlertForm.display("保存文件",fileFullPath + " 文件保存失败!!","error");

                }

            }
        });


    }



}
