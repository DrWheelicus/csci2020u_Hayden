import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.geometry.HPos;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import javafx.util.Duration;
import org.apache.commons.validator.routines.EmailValidator;

public class Lab04 extends Application {

    //check if phone number is int only
    public boolean checkPhoneNumberSymbols(String input) {
        for (char i : input.toCharArray()) {
            if (i < 48 || i > 57)
                return false;
        }
        return true;
    }

    //check if phone number has 10 digits
    public boolean checkPhoneNumberLength(String input) {
        int counter = 0;
        for (char i : input.toCharArray()) {
            if (i < 48 || i > 57)
                continue;
            counter++;
        }
        return counter == 10;
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        GridPane gp = new GridPane();
        gp.setAlignment(Pos.CENTER_LEFT);
        gp.setPadding(new Insets(20, 100, 20, 20));
        gp.setVgap(15);
        gp.setHgap(10);

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

        //special label for email validation
        Label emailInvalid = new Label("Invalid E-Mail.");
        emailInvalid.setMinWidth(80);
        gp.add(emailInvalid, 2, 3);
        emailInvalid.setOpacity(0);

        Label lblPhone = new Label("Phone #: ");
        TextField txtPhone = new TextField();
        txtPhone.setPromptText("Enter Phone #");
        gp.add(lblPhone, 0, 4);
        gp.add(txtPhone, 1, 4);

        Label lblDate = new Label("Birthday: ");
        DatePicker dtpDates = new DatePicker();
        gp.add(lblDate, 0, 5);
        gp.add(dtpDates, 1, 5);

        //special label for invalid submission
        Label submittionInvalid = new Label("Invalid Submision.");
        submittionInvalid.setMinHeight(50);
        submittionInvalid.setTextFill(Color.color(1,0,0));
        gp.add(submittionInvalid, 1, 7);
        submittionInvalid.setOpacity(0);
        Timeline timeline = new Timeline(new KeyFrame(
                Duration.millis(2500),
                ae -> submittionInvalid.setOpacity(0)));
        //if changes are detected in email textfield, check validity
        txtEmail.textProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observableValue,
                                String s, String t1) {
                if (EmailValidator.getInstance().isValid(txtEmail.getText()) || txtEmail.getText().length() == 0) {
                    emailInvalid.setOpacity(0);
                } else {
                    emailInvalid.setOpacity(100);
                }
            }
        });

        //if changes detected in the phone field, reformat (or not)
        java.text.MessageFormat phoneFormat = new java.text.MessageFormat("({0})-{1}-{2}");
        txtPhone.textProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observable,
                                String oldValue, String newValue) {
                //System.out.println(oldValue + "\t" + newValue);//debug
                //if 10 digits, all numbers
                if (checkPhoneNumberLength(txtPhone.getText()) && checkPhoneNumberSymbols(txtPhone.getText())) {
                    String unformattedNumber = txtPhone.getText();
                    String[] formattedNumber = {unformattedNumber.substring(0, 3),
                            unformattedNumber.substring(3, 6),
                            unformattedNumber.substring(6)};//cut number into 3 parts as a list
                    Platform.runLater(() -> {
                        txtPhone.setText(phoneFormat.format(formattedNumber));//replace content in textfield
                    });
                } else {
                    if (!checkPhoneNumberLength(txtPhone.getText()) ||
                            (checkPhoneNumberLength(txtPhone.getText()) && txtPhone.getText().length() > 14)) {
                        String revertText = "";
                        for (char i : txtPhone.getText().toCharArray()) {
                            if (i >= 48 && i <= 57)
                                revertText += i;
                        }
                        String finalRevertText = revertText;
                        Platform.runLater(() -> {
                            txtPhone.setText(finalRevertText);
                        });
                    }
                }
                Platform.runLater(() -> {
                    txtPhone.positionCaret(txtPhone.getLength());
                });
            }
        });

        Button btReg = new Button("Register");
        gp.add(btReg, 1,6);
        GridPane.setHalignment(btReg, HPos.LEFT);

        btReg.setOnAction(event -> {
            if (EmailValidator.getInstance().isValid(txtEmail.getText())
                    && txtUsername.getText().length() != 0
                    && txtPassword.getText().length() != 0
                    && txtName.getText().length() != 0
                    && txtPhone.getText().length() == 14
                    && dtpDates.getEditor().getText().length() != 0) {
                //only continue if all fields are valid
                System.out.println("Username: " + txtUsername.getText());
                System.out.println("Password: " + txtPassword.getText());
                System.out.println("Full Name: " + txtName.getText());
                System.out.println("E-Mail: " + txtEmail.getText());
                System.out.println("Phone #: " + txtPhone.getText());
                System.out.println("DOB: " + dtpDates.getEditor().getText());
                txtUsername.clear();
                txtPassword.clear();
                txtName.clear();
                txtEmail.clear();
                txtPhone.clear();
                dtpDates.getEditor().clear();
                submittionInvalid.setOpacity(0);
            }
            else{
                submittionInvalid.setOpacity(100);
                timeline.play();
            }
        });

        Scene scene = new Scene(gp);
        primaryStage.setScene(scene);
        primaryStage.setTitle("Lab 04 Solution");
        primaryStage.show();
    }
}
