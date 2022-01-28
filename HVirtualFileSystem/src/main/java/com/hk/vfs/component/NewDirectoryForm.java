package com.hk.vfs.component;

import com.hk.vfs.controller.HVFSController;
import com.hk.vfs.mapper.HFileMapper;
import com.hk.vfs.model.HFile;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.stage.Modality;
import javafx.stage.Stage;
import lombok.SneakyThrows;

import java.util.Date;

/**
 * @author : HK意境
 * @ClassName : NewFileForm
 * @date : 2021/12/22 15:53
 * @description : 新创建文件，文件夹，弹出信息床窗口
 * @Todo :
 * @Bug :
 * @Modified :
 * @Version : 1.0
 */
public class NewDirectoryForm {

    public static HFile res = null;

    private static Stage stage = new Stage();

    private static Scene scene = null;

    // 画布
    private static GridPane grid = new GridPane() ;

    // 登录框
    private static Button loginButton = new Button("创建目录");

    // 目录称输入框
    private static TextField fileNameField = new TextField();
    // 文件扩展名
    //private static TextField fileExtendField = new TextField();

    static {
        initialize();
    }

    public static boolean display(String title, String msg){

        stage.setTitle(title);
        stage.setScene(scene);
        stage.show();
        return true ;
    }

    public static void initialize(){

        layout();

        // 确认创建文件
        loginButton.setOnAction(new EventHandler<ActionEvent>() {
            @SneakyThrows
            @Override
            public void handle(ActionEvent event) {

                // 返回新文件信息
                res = new HFile(fileNameField.getText(),new Date().toString(),"dir",0.0) ;

                // 获取新创建的文件，进行实际创建
                HFile hFile = HFileMapper.createHDirectory(NewDirectoryForm.res);
                if (hFile != null) {
                    // 创建成功
                    AlertForm.display("目录创建消息","创建成功","info");

                    // 添加数据到list
                    HVFSController.filesList.add(hFile) ;
                }else{
                    // 创建失败
                    AlertForm.display("目录创建消息","创建失败","info");
                }

                System.out.println("res=" + res);

                // 清除缓存
                fileNameField.setText(null);

                stage.close();
            }
        });

    }

    // 初始化布局
    public static void layout(){

        grid.setAlignment(Pos.CENTER);
        grid.setHgap(10);
        grid.setVgap(10);
        grid.setPadding(new Insets(25, 25, 25, 25));
        Text scenetitle = new Text("创建新目录文件");
        scenetitle.setFont(Font.font("Tahoma", FontWeight.NORMAL, 20));
        grid.add(scenetitle, 0, 0, 2, 1);

        //创建Label对象，放到第0列，第1行
        Label userName = new Label("目录名称: ");
        grid.add(userName, 0, 1);

        //创建文本输入框，放到第1列，第1行
        grid.add(fileNameField, 1, 1);
        //Label pw = new Label("文件扩展名:");
        //grid.add(pw, 0, 2);
        //grid.add(fileExtendField, 1, 2);

        HBox hbBtn = new HBox(10);
        hbBtn.setAlignment(Pos.BOTTOM_RIGHT);
        hbBtn.getChildren().add(loginButton);//将按钮控件作为子节点
        grid.add(hbBtn, 1, 4);//将HBox pane放到grid中的第1列，第4行

        final Text actiontarget=new Text();//增加用于显示信息的文本
        grid.add(actiontarget, 1, 6);

        stage.initModality(Modality.APPLICATION_MODAL);
        scene = new Scene(grid, 300, 275);
    }


}
