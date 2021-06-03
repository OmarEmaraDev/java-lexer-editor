package dev.omaremara.editor.controller;

import dev.omaremara.editor.Main;
import dev.omaremara.editor.model.Matchability;
import dev.omaremara.editor.model.Token;
import dev.omaremara.editor.model.TokenType;
import dev.omaremara.editor.util.ViewUtil;
import dev.omaremara.editor.view.EditorView;
import java.io.File;
import java.util.List;
import javafx.stage.FileChooser;
import javafx.stage.FileChooser.ExtensionFilter;
import javafx.stage.Stage;

public class TokensController {
  private String screenshotPath = "";

  public void browse() {
    Stage stage = Main.primaryStage;
    FileChooser fileChooser = new FileChooser();
    fileChooser.setTitle("Choose Source File");
    fileChooser.setInitialDirectory(
        new File(System.getProperty("user.home") + "/Documents"));
    fileChooser.getExtensionFilters().addAll(
        new ExtensionFilter("Compiler #2 Source Files", "*.c2"));
    File selectedFile = fileChooser.showOpenDialog(stage);
    if (selectedFile == null) {
      return;
    }

    /* Test Data */
    List<Token> tokens = List.of(
        new Token(1, "/-", TokenType.COMMENT, 1, Matchability.MATCHED),
        new Token(2, "lre", TokenType.INTEGER_TYPE, 1, Matchability.MATCHED),
        new Token(2, "@", TokenType.TOKEN_DELIMITER, 2, Matchability.MATCHED),
        new Token(2, "Decrease", TokenType.IDENTIFIER, 3,
                  Matchability.MATCHED));

    Main.tokens.addAll(tokens);
  }

  public void editor() { ViewUtil.setSceneRoot(new EditorView()); }
}
