package randall.common.util;

import com.google.common.base.Ascii;
import com.google.common.base.Strings;

/**
 * @author mrzhqiang
 */
public class HUtil32 {
  private static final int BUFF_SIZE = 0x7fff;
  private static final String[] EMPTY_STRING_RESULT_AND_DESTINATION = new String[] {"", ""};

  /**
   * todo 重构
   */
  public static String[] getValidStr3(String str, char[] divider) {
    if (Strings.isNullOrEmpty(str)) {
      return EMPTY_STRING_RESULT_AND_DESTINATION;
    }
    int length = str.length();
    int buffCount = 0;
    int count = 1;
    if (length > BUFF_SIZE - 1) {
      return EMPTY_STRING_RESULT_AND_DESTINATION;
    }
    char[] buffer = new char[BUFF_SIZE];
    char ch = Ascii.NUL;
    try {
      while (true) {
        if (count <= length) {
          ch = str.charAt(count);
          for (char c : divider) {
            if (ch == c) {
              if (buffCount > 0) {
                if (buffCount < BUFF_SIZE - 1) {
                  buffer[buffCount] = Ascii.NUL;
                  return new String[] {str.substring(count + 1), new String(buffer)};
                }
              } else {
                if (count > length) {
                  return new String[] {str.substring(count + 2), ""};
                }
              }
            }
          }
        }
        if (count > length) {
          if (buffCount > 0) {
            if (buffCount < BUFF_SIZE - 1) {
              buffer[buffCount] = Ascii.NUL;
              return new String[] {str.substring(count + 1), new String(buffer)};
            }
          } else {
            if (count > length) {
              return new String[] {str.substring(count + 2), ""};
            }
          }
        } else {
          if (buffCount < BUFF_SIZE - 1) {
            buffer[buffCount] = ch;
            buffCount++;
          }
        }
        count++;
      }
    } catch (Exception ignore) {
      return EMPTY_STRING_RESULT_AND_DESTINATION;
    }
  }
}
