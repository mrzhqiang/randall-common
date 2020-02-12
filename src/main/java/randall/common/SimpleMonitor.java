package randall.common;

import com.google.common.base.Strings;
import com.google.common.collect.Lists;
import java.util.List;
import lombok.extern.slf4j.Slf4j;

/**
 * 简单的监视器。
 *
 * @author mrzhqiang
 */
@Slf4j
final class SimpleMonitor implements Monitor {

  private final long startTime = System.currentTimeMillis();
  private final List<Period> periods = Lists.newArrayList();

  @Override
  public void record(String name) {
    if (Strings.isNullOrEmpty(name)) {
      return;
    }
    periods.add(new Period(name, System.currentTimeMillis()));
  }

  @Override
  public void report(String name) {
    long entTime = System.currentTimeMillis();
    long totalTime = entTime - startTime;
    log.info("The [{}] total time: {}(ms)", name, totalTime);

    for (int i = 0; i < periods.size(); i++) {
      Period book = periods.get(i);
      long intervalTime;
      if (i == 0) {
        intervalTime = book.timestamp - startTime;
      } else if (i == periods.size() - 1) {
        intervalTime = entTime - book.timestamp;
      } else {
        intervalTime = periods.get(i + 1).timestamp - book.timestamp;
      }
      log.info("The [{}] >>> [{}] time: {}(ms)", name, book.name, intervalTime);
    }
    periods.clear();
  }

  private static final class Period {
    final String name;
    final long timestamp;

    private Period(String name, long timestamp) {
      this.name = name;
      this.timestamp = timestamp;
    }
  }
}
