import javafx.application.Application;
import javafx.geometry.HPos;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

public class Lab04 extends Application {
    @Override
    public void start(Stage primaryStage) throws Exception {
        GridPane gp = new GridPane();
        gp.setAlignment(Pos.CENTER_LEFT);
        gp.setPadding(new Insets(10, 10, 10, 10));

        Label lblUsername = new Label("Username: ");
        TextField txtUsername = new TextField();
        txtUsername.setPromptText("Enter Username");
        gp.add(lblUsername, 0, 0);
        gp.add(txtUsername, 1, 0);

        Label lblPassword = new Label("Password: ");
        PasswordField txtPassword = new PasswordField();
        txtPassword.setPromptText("Enter Password");
        gp.add(lblPassword, 0, 1);
        gp.add(txtPassword, 1, 1);

        Label lblName = new Label("Full Name: ");
        TextField txtName = new TextField();
        txtName.setPromptText("Enter Full Name");
        gp.add(lblName, 0, 2);
        gp.add(txtName, 1, 2);

        Label lblEmail = new Label("E-Mail: ");
        TextField txtEmail = new TextField();
        txtEmail.setPromptText("Enter E-Mail");
        gp.add(lblEmail, 0, 3);
        gp.add(txtEmail, 1, 3);

        // MaskFormatter fmt = new MaskFormatter("(###) ###-####");
        Label lblPhone = new Label("Phone #: ");
        // JFormattedTextField txtPhone = new JFormattedTextField(fmt);
        TextField txtPhone = new TextField();
        txtPhone.setPromptText("Enter Phone #");
        gp.add(lblPhone, 0, 4);
        gp.add(txtPhone, 1, 4);

        Label lblDate = new Label("Birthday: ");
        DatePicker dtpDates = new DatePicker();
        gp.add(lblDate, 0, 5);
        gp.add(dtpDates, 1, 5);

        Button btReg = new Button("Register");
        gp.add(btReg, 1,6);
        GridPane.setHalignment(btReg, HPos.LEFT);

        btReg.setOnAction(event -> {
            System.out.println("Username: " + txtUsername.getText());
            System.out.println("Password: " + txtPassword.getText());
            System.out.println("Full Name: " + txtName.getText());
            System.out.println("E-Mail: " + txtEmail.getText());
            System.out.println("Phone #: " + txtPhone.getText());
            System.out.println("Birthday: "+ dtpDates.getValue());
        });

        Scene scene = new Scene(gp);
        primaryStage.setScene(scene);
        primaryStage.setTitle("Lab 04 Solution");
        primaryStage.show();
    }
}
