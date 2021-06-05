package dev.omaremara.editor.controller;

import dev.omaremara.editor.model.TokenType;
import java.lang.Character;

public class DoubleLexemeDefinition {
  private final char firstChar;
  private final char secondChar;
  private final TokenType singleTokenType;
  private final TokenType doubleTokenType;

  public DoubleLexemeDefinition(char firstChar, char secondChar,
                                TokenType singleTokenType,
                                TokenType doubleTokenType) {
    this.firstChar = firstChar;
    this.secondChar = secondChar;
    this.singleTokenType = singleTokenType;
    this.doubleTokenType = doubleTokenType;
  }

  public char getFirstChar() { return firstChar; }
  public char getSecondChar() { return secondChar; }
  public TokenType getSingleTokenType() { return singleTokenType; }
  public TokenType getDoubleTokenType() { return doubleTokenType; }

  String getDoubleLexme() {
    return Character.toString(firstChar) + Character.toString(secondChar);
  }

  String getSingleLexme() { return Character.toString(firstChar); }
}
