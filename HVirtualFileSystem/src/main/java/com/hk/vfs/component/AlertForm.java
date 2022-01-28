package com.hk.vfs.component;

import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;

/**
 * @author : HK意境
 * @ClassName : AlertForm
 * @date : 2021/12/22 16:44
 * @description :
 * @Todo :
 * @Bug :
 * @Modified :
 * @Version : 1.0
 */
public class AlertForm {

    public static void display(String title,String msg, String type){

        Alert alert ;

        switch (type){
            case "info":
                alert = new Alert(AlertType.INFORMATION);
                break;
            case "error":
                alert = new Alert(AlertType.ERROR);
                break;
            case "warning":
                alert = new Alert(AlertType.WARNING);
                break;
            default:
                alert = new Alert(AlertType.INFORMATION);
                break;
        }

        alert.titleProperty().set(title);
        alert.setHeaderText("操作结果：");
        alert.setContentText(msg);
        alert.showAndWait() ;

    }

}
