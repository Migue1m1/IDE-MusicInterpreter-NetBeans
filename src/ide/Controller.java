package ide;

import javafx.application.Application;
import javafx.fxml.FXML;

import java.io.ByteArrayInputStream;
import java.nio.charset.StandardCharsets;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TextArea;
import javafx.stage.Stage;

public class Controller extends Application {
    @FXML private TextArea  txtEntrada;
    @FXML private TextArea  txtSalida;

    public static void main(String[] args) {
        launch(args);
    }
    
    public Controller() {
    }

    public static void Launch(){
        Application.launch();
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        Parent root = FXMLLoader.load(getClass().getResource("view.fxml"));
        primaryStage.setTitle("IDE");
        primaryStage.setResizable(false);
        primaryStage.setScene(new Scene(root, 650, 450));
        primaryStage.show();
    }

    @FXML
    public void runCompiler() throws Exception {
        parsing();
    }

    public TextArea getTxtEntrada() {
        return txtEntrada;
    }

    public TextArea getTxtSalida() {
        return txtSalida;
    }

    private void parsing() {
        Thread thread = new Thread(new Runnable() {
            @Override
            public void run() {
                Yylex lex = new Yylex(new ByteArrayInputStream(txtEntrada.getText().getBytes(StandardCharsets.UTF_8)));
                lex.setOut(txtSalida);
                parser pars = new parser(lex);
                pars.setOut(txtSalida);
                try {
                    pars.parse();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }, "parsing");
        thread.start();
    }
}
