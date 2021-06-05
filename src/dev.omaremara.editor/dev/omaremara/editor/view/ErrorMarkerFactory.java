package dev.omaremara.editor.view;

import dev.omaremara.editor.Main;
import dev.omaremara.editor.model.Matchability;
import dev.omaremara.editor.model.Token;
import java.util.function.IntFunction;
import javafx.beans.value.ObservableValue;
import javafx.scene.Node;
import javafx.scene.control.Tooltip;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;

public class ErrorMarkerFactory implements IntFunction<Node> {
  @Override
  public Node apply(int lineNumber) {
    Boolean lineHasError = false;
    String firstErrorLexme = "";
    for (Token token : Main.tokens) {
      if (token.getLineNumber() == lineNumber + 1 &&
          token.getMatchability() == Matchability.NOT_MATCHED) {
        lineHasError = true;
        firstErrorLexme = token.getLexeme();
        break;
      }
    }

    if (!lineHasError) {
      /* Just for layout calculations. */
      Circle circle = new Circle(5);
      circle.setVisible(false);
      return circle;
    }

    Circle circle = new Circle(5);
    circle.setFill(Color.RED);
    String error = "Unmatched lexme '" + firstErrorLexme + "'.";
    Tooltip tooltip = new Tooltip(error);
    Tooltip.install(circle, tooltip);
    return circle;
  }
}
