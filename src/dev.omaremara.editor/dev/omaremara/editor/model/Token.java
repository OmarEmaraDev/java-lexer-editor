package dev.omaremara.editor.model;

import dev.omaremara.editor.model.Matchability;
import dev.omaremara.editor.model.TokenType;

public class Token {
  private final int lineNumber;
  private final String lexeme;
  private final TokenType tokenType;
  private final int wordNumber;
  private final Matchability matchability;

  public Token(int lineNumber, String lexeme, TokenType tokenType,
               int wordNumber) {
    this.lineNumber = lineNumber;
    this.lexeme = lexeme;
    this.tokenType = tokenType;
    this.wordNumber = wordNumber;
    this.matchability = tokenType == TokenType.UNKNOWN
                            ? Matchability.NOT_MATCHED
                            : Matchability.MATCHED;
  }

  public int getLineNumber() { return lineNumber; }
  public String getLexeme() { return lexeme; }
  public TokenType getTokenType() { return tokenType; }
  public int getWordNumber() { return wordNumber; }
  public Matchability getMatchability() { return matchability; }
}
