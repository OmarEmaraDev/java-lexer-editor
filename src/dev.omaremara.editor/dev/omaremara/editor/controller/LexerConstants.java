package dev.omaremara.editor.controller;

import dev.omaremara.editor.controller.DoubleLexemeDefinition;
import dev.omaremara.editor.model.TokenType;
import java.lang.Character;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class LexerConstants {
  /* A map of single character lexemes that are not ambiguous. The key of the
   * map is the lexeme. The value of the map is the lexeme type. */
  public static final Map<Character, TokenType> singlarLexemeMap =
      defineSinglarLexemeMap();

  private static Map<Character, TokenType> defineSinglarLexemeMap() {
    Map<Character, TokenType> result = new HashMap<>();
    result.put('+', TokenType.ARITHMETIC_OPERATION);
    result.put('-', TokenType.ARITHMETIC_OPERATION);
    result.put('*', TokenType.ARITHMETIC_OPERATION);
    result.put('@', TokenType.TOKEN_DELIMITER);
    result.put(';', TokenType.TOKEN_DELIMITER);
    result.put('.', TokenType.ACCESS_OPERATOR);
    result.put('[', TokenType.BRACES);
    result.put(']', TokenType.BRACES);
    result.put('{', TokenType.BRACES);
    result.put('}', TokenType.BRACES);
    result.put('~', TokenType.LOGIC_OPERATOR);
    return Collections.unmodifiableMap(result);
  }

  public static boolean isSingularLexeme(char character) {
    return singlarLexemeMap.containsKey(character);
  }

  /* A map of single or double character lexemes that are ambiguous but can be
   * disambiguated through the immediately following character. The key of the
   * map is the first character in the lexeme. The value of the map is a
   * DoubleLexemeDefinition, which stores information necessary to disambiguate
   * the lexeme.*/
  public static final Map<Character, DoubleLexemeDefinition> doubleLexemeMap =
      defineDoubleLexemeMap();

  private static Map<Character, DoubleLexemeDefinition>
  defineDoubleLexemeMap() {
    Map<Character, DoubleLexemeDefinition> result = new HashMap<>();
    result.put('=', new DoubleLexemeDefinition('=', '=',
                                               TokenType.ASSIGNMENT_OPERATOR,
                                               TokenType.RELATIONAL_OPERATOR));
    result.put('<', new DoubleLexemeDefinition('<', '=',
                                               TokenType.RELATIONAL_OPERATOR,
                                               TokenType.RELATIONAL_OPERATOR));
    result.put('>', new DoubleLexemeDefinition('>', '=',
                                               TokenType.RELATIONAL_OPERATOR,
                                               TokenType.RELATIONAL_OPERATOR));
    result.put('!', new DoubleLexemeDefinition('!', '=', TokenType.UNKNOWN,
                                               TokenType.RELATIONAL_OPERATOR));
    result.put('&', new DoubleLexemeDefinition('&', '&', TokenType.UNKNOWN,
                                               TokenType.LOGIC_OPERATOR));
    result.put('|', new DoubleLexemeDefinition('|', '|', TokenType.UNKNOWN,
                                               TokenType.LOGIC_OPERATOR));
    result.put('#', new DoubleLexemeDefinition('#', '/', TokenType.UNKNOWN,
                                               TokenType.COMMENT));
    return Collections.unmodifiableMap(result);
  }

  public static boolean isDoubleLexeme(char character) {
    return doubleLexemeMap.containsKey(character);
  }

  /* A map of keywords and their token types. The key of the map is the keyword.
   * The value of the map is the token type. */
  public static final Map<String, TokenType> keywordMap = defineKeywordMap();

  private static Map<String, TokenType> defineKeywordMap() {
    Map<String, TokenType> result = new HashMap<>();
    result.put("Divisio", TokenType.CLASS);
    result.put("InferedFrom", TokenType.INHERITANCE);
    result.put("WheatherDO", TokenType.CONDITION);
    result.put("Else", TokenType.CONDITION);
    result.put("Ire", TokenType.INTEGER);
    result.put("Sire", TokenType.SINTEGER);
    result.put("Clo", TokenType.CHARACTER);
    result.put("SetOfClo", TokenType.STRING);
    result.put("FBU", TokenType.FLOAT);
    result.put("SFBU", TokenType.SFLOAT);
    result.put("NoneValue", TokenType.VOID);
    result.put("TerminateThisNow", TokenType.BREAK);
    result.put("RingWhen", TokenType.LOOP);
    result.put("BackedValue", TokenType.RETURN);
    result.put("STT", TokenType.STRUCT);
    result.put("Check", TokenType.SWITCH);
    result.put("CaseOf", TokenType.SWITCH);
    result.put("Beginning", TokenType.START_STATEMENT);
    result.put("End", TokenType.END_STATEMENT);
    return Collections.unmodifiableMap(result);
  }
}
