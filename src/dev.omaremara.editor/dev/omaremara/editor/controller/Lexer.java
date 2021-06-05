package dev.omaremara.editor.controller;

import static java.lang.Character.isLetterOrDigit;

import dev.omaremara.editor.controller.LexerConstants;
import dev.omaremara.editor.model.Matchability;
import dev.omaremara.editor.model.Token;
import dev.omaremara.editor.model.TokenType;
import java.io.File;
import java.io.FileNotFoundException;
import java.lang.Character;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Lexer {
  private int lineNumber;
  private int position;
  private char character;
  private int lexemeNumberInLine;
  private String source;

  Lexer(String source) {
    this.lineNumber = 1;
    this.position = 0;
    this.lexemeNumberInLine = 0;
    this.source = source;
    this.character = source.charAt(position);
  }

  public static Lexer fromFile(File file) throws FileNotFoundException {
    Scanner scanner = new Scanner(file);
    String source = "";
    while (scanner.hasNext()) {
      source += scanner.nextLine() + "\n";
    }
    return new Lexer(source);
  }

  /* Return the next character without advancing the position and setting
   * character. */
  char peek() {
    if (position + 1 > source.length() - 1) {
      return '\u0000';
    }
    return source.charAt(position + 1);
  }

  /* Return the next character with advancing the position and setting the
   * characater. */
  char readChar() {
    position++;
    if (position > source.length() - 1) {
      character = '\u0000';
    } else {
      character = source.charAt(position);
    }
    return character;
  }

  void incrementLineNumber() {
    lineNumber++;
    lexemeNumberInLine = 0;
  }

  String readWhiteSpace() {
    String whiteSpace = "";
    while (Character.isWhitespace(character)) {
      if (character == '\n') {
        incrementLineNumber();
      }
      whiteSpace += character;
      readChar();
    }
    return whiteSpace;
  }

  Token getWhiteSpaceToken() {
    String whiteSpace = readWhiteSpace();
    return new Token(lineNumber, whiteSpace, TokenType.WHITE_SPACE,
                     lexemeNumberInLine);
  }

  Token getSinglularToken() {
    Token token = new Token(lineNumber, Character.toString(character),
                            LexerConstants.singlarLexemeMap.get(character),
                            lexemeNumberInLine);
    readChar();
    return token;
  }

  Token getDoubleToken() {
    DoubleLexemeDefinition definition =
        LexerConstants.doubleLexemeMap.get(character);

    Token token;
    if (peek() == definition.getSecondChar()) {
      readChar();
      token = new Token(lineNumber, definition.getDoubleLexme(),
                        definition.getDoubleTokenType(), lexemeNumberInLine);
    } else {
      token = new Token(lineNumber, definition.getSingleLexme(),
                        definition.getSingleTokenType(), lexemeNumberInLine);
    }
    readChar();
    return token;
  }

  Token getSingleLineCommentToken() {
    readChar();
    Token token =
        new Token(lineNumber, "/-", TokenType.COMMENT, lexemeNumberInLine);
    while (readChar() != '\n') {
    }
    return token;
  }

  Token getDelimitedCommentToken() {
    readChar();
    Token token =
        new Token(lineNumber, "/#", TokenType.COMMENT, lexemeNumberInLine);
    while (!(readChar() == '#' && peek() == '/')) {
      if (character == '\n') {
        incrementLineNumber();
      }
    }
    return token;
  }

  Token getDivideToken() {
    Token token = new Token(lineNumber, "/", TokenType.ARITHMETIC_OPERATION,
                            lexemeNumberInLine);
    readChar();
    return token;
  }

  Token getDivideOrCommentToken() {
    Token token;
    if (peek() == '-') {
      return getSingleLineCommentToken();
    } else if (peek() == '#') {
      return getDelimitedCommentToken();
    } else {
      return getDivideToken();
    }
  }

  String readNumber() {
    String number = "";
    while (Character.isDigit(character)) {
      number += character;
      readChar();
    }
    return number;
  }

  Token getNumberToken() {
    String number = readNumber();
    return new Token(lineNumber, number, TokenType.CONSTANT,
                     lexemeNumberInLine);
  }
  
  Token getUsingToken() {
    int flag = 0;
    String char = readChar();
    String text = ""
    while(flag != 5){
      text =+ char;
      flag ++;
      char = readChar();
    }
      
    if(text == "Using"){
       Token token = 
         new Token(lineNumber, text, TokenType.INCLUDE, lexemeNumberInLine);
    }
    else{
      Token token = 
         new Token(lineNumber, text, TokenType.I, lexemeNumberInLine);
    }
  
    return token;
  }
  
  Token getStringToken() {
    String char = readChar();
    if(char == "'"){
      Token token =
        new Token(lineNumber, "'", TokenType.STRING, lexemeNumberInLine);
      String text = ""
      while (readChar() != "'") {
        text =+ readChar();
      }
    }
    if(char == '"'){
      Token token =
        new Token(lineNumber, '"', TokenType.STRING, lexemeNumberInLine);
      String text = ""
      while (readChar() != '"') {
        text + readChar();
      }
    }
    return token;
  }

  boolean isValidIdentifierChar(char c) {
    return Character.isAlphabetic(c) || Character.isDigit(c) || c == '_';
  }

  Token getUnknownToken() {
    Token token = new Token(lineNumber, Character.toString(character),
                            TokenType.UNKNOWN, lexemeNumberInLine);
    readChar();
    return token;
  }

  String readIdentifierOrKeyword() {
    String identifierOrKeyword = "";
    while (isValidIdentifierChar(character)) {
      identifierOrKeyword += character;
      readChar();
    }
    return identifierOrKeyword;
  }

  Token getKeywordToken(String keyword, TokenType keywordType) {
    return new Token(lineNumber, keyword, keywordType, lexemeNumberInLine);
  }

  Token getKeywordToken(String identifier) {
    return new Token(lineNumber, identifier, TokenType.IDENTIFIER,
                     lexemeNumberInLine);
  }

  Token getNumberIdentifierOrKeyword() {
    if (Character.isDigit(character)) {
      return getNumberToken();
    }

    if (!isValidIdentifierChar(character)) {
      return getUnknownToken();
    }

    String identifierOrKeyword = readIdentifierOrKeyword();
    TokenType keywordType = LexerConstants.keywordMap.get(identifierOrKeyword);
    if (keywordType != null) {
      return getKeywordToken(identifierOrKeyword, keywordType);
    }
    return getKeywordToken(identifierOrKeyword);
  }

  Token getNextToken() {
    Token token;
    if (character == '\u0000') {
      return null;
    } else if (Character.isWhitespace(character)) {
      token = getWhiteSpaceToken();
    } else if (LexerConstants.isSingularLexeme(character)) {
      token = getSinglularToken();
    } else if (LexerConstants.isDoubleLexeme(character)) {
      token = getDoubleToken();
    } else if (character == '/') {
      token = getDivideOrCommentToken();
    } else {
      token = getNumberIdentifierOrKeyword();
    }
    lexemeNumberInLine++;
    return token;
  }

  List<Token> tokenize() {
    List<Token> tokens = new ArrayList<>();
    for (int i = 0; i < source.length(); i++) {
      Token token = getNextToken();
      if (token == null) {
        break;
      }
      if (token.getTokenType() == TokenType.WHITE_SPACE) {
        continue;
      }
      tokens.add(token);
    }
    return tokens;
  }
}
