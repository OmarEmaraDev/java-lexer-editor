package dev.omaremara.editor.controller;

import dev.omaremara.editor.Main;
import dev.omaremara.editor.model.AutoCompleteSuggestion;
import dev.omaremara.editor.model.Matchability;
import dev.omaremara.editor.model.Token;
import dev.omaremara.editor.model.TokenType;
import dev.omaremara.editor.util.ViewUtil;
import dev.omaremara.editor.view.TokensView;
import java.lang.Character;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javafx.stage.Stage;
import javafx.util.Pair;

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

  public List<AutoCompleteSuggestion> autoComplete(int anchor, String source) {
    String searchTerm = "";
    for (int i = anchor - 1; i >= 0; i--) {
      char character = source.charAt(i);
      if (Character.isLetterOrDigit(character)) {
        searchTerm = character + searchTerm;
      } else {
        break;
      }
    }

    List<AutoCompleteSuggestion> suggestions = new ArrayList<>();
    if (searchTerm == "") {
      return suggestions;
    }

    Set<String> suggestionsSet = new HashSet<>();

    Pattern pattern = Pattern.compile("\\b" + searchTerm + "(\\w+)");
    Matcher matcher = pattern.matcher(source);
    matcher.results().forEach(matchResult -> {
      String fullSuggestion = matchResult.group(0);
      if (suggestionsSet.contains(fullSuggestion)) {
        return;
      }
      suggestionsSet.add(fullSuggestion);

      String partialSuggestion = matchResult.group(1);
      suggestions.add(
          new AutoCompleteSuggestion(fullSuggestion, partialSuggestion));
    });

    return suggestions;
  }

  public void tokens() { ViewUtil.setSceneRoot(new TokensView()); }
}
