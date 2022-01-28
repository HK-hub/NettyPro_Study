package com.hk.vfs.component;

import com.alibaba.druid.pool.DruidDataSource;
import com.hk.vfs.model.User;
import com.hk.vfs.utils.DruidJDBC;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.stage.Modality;
import javafx.stage.Stage;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.Map;

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
public class LoginForm {

    public static boolean res ;

    private static Stage stage ;

    // 画布
    private static GridPane grid = new GridPane() ;

    // 登录框
    private static Button loginButton = new Button("login");

    // 用户名框
    private static TextField userTextField = new TextField();
    // 密码输入框
    private static PasswordField passwordField = new PasswordField();


    public static boolean display(String title, String msg){

        initialize();

        stage.initModality(Modality.APPLICATION_MODAL);
        Scene scene = new Scene(grid, 300, 275);
        stage.setTitle(title);
        stage.setScene(scene);
        stage.show();

        return true ;
    }


    public static void initialize(){

        layout();

        loginButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {

                // 获取用户名，密码，
                String username = userTextField.getText();
                String password = passwordField.getText() ;

                // 存储验证
                // 连接数据库
                JdbcTemplate jdbcTemplate = new JdbcTemplate(DruidJDBC.getDataSource());
                String sql = "select * from tb_user where username = ? and password = ?" ;
                List<User> query = jdbcTemplate.query(sql, new RowMapper<User>() {
                    @Override
                    public User mapRow(ResultSet resultSet, int i) throws SQLException {

                        String username1 = resultSet.getString("username");
                        String password1 = resultSet.getString("password");

                        User user = new User(username1, password1);
                        return user;
                    }
                });

                // 验证
                if (query.size() > 0){
                    User users = query.get(0) ;

                    if (username.equals(users.getUsername()) && password.equals(users.getPassword())){
                        //通过
                        AlertForm.display("登录结果","登录成功","info");

                    }else{
                        AlertForm.display("登录结果","登录失败","error");
                    }
                }else {
                    AlertForm.display("登录结果","登录失败","error");

                }



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
        Text scenetitle = new Text("Welcome to HVFS");
        scenetitle.setFont(Font.font("Tahoma", FontWeight.NORMAL, 20));
        grid.add(scenetitle, 0, 0, 2, 1);

        //创建Label对象，放到第0列，第1行
        Label userName = new Label("Username: ");
        grid.add(userName, 0, 1);

        //创建文本输入框，放到第1列，第1行
        grid.add(userTextField, 1, 1);
        Label pw = new Label("Password:");
        grid.add(pw, 0, 2);
        grid.add(passwordField, 1, 2);

        HBox hbBtn = new HBox(10);
        hbBtn.setAlignment(Pos.BOTTOM_RIGHT);
        hbBtn.getChildren().add(loginButton);//将按钮控件作为子节点
        grid.add(hbBtn, 1, 4);//将HBox pane放到grid中的第1列，第4行

        final Text actiontarget=new Text();//增加用于显示信息的文本
        grid.add(actiontarget, 1, 6);
    }


}
