package dev.omaremara.editor.controller;

import dev.omaremara.editor.Main;
import dev.omaremara.editor.model.Matchability;
import dev.omaremara.editor.model.Token;
import dev.omaremara.editor.model.TokenType;
import dev.omaremara.editor.util.ViewUtil;
import dev.omaremara.editor.view.TokensView;
import java.util.List;
import javafx.stage.Stage;

public class EditorController {
  public void compile() {
    /* Test Data */
    List<Token> tokens = List.of(
        new Token(1, "/-", TokenType.COMMENT, 1, Matchability.MATCHED),
        new Token(2, "lre", TokenType.INTEGER_TYPE, 1, Matchability.MATCHED),
        new Token(2, "@", TokenType.TOKEN_DELIMITER, 2, Matchability.MATCHED),
        new Token(2, "Decrease", TokenType.IDENTIFIER, 3,
                  Matchability.MATCHED));

    Main.tokens.addAll(tokens);
  }

  public void tokens() { ViewUtil.setSceneRoot(new TokensView()); }
}
