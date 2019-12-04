package randall.common.util;

/**
 * IO 辅助工具异常。
 *
 * @author mrzhqiang
 */
public final class IOHelperException extends RuntimeException {
  public IOHelperException(String message) {
    super(message);
  }

  public IOHelperException(String message, Throwable cause) {
    super(message, cause);
  }
}
