package randall.common.util.mud;

import com.google.common.collect.Lists;
import helper.Explorer;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * TStringList for Delphi 2007
 *
 * @author mrzhqiang
 */
class StringList<T> extends ArrayList<String> {
  static <T> StringList<T> newInstance() {
    return new StringList<>();
  }

  public final List<T> objects = Lists.newArrayList();

  public void addObject(String element, T object) {
    add(element);
    objects.add(object);
  }

  public void insertObject(int index, String element, T object) {
    add(index, element);
    objects.add(index, object);
  }

  public void loadFromFile(String filename) {
    addAll(Explorer.lines(Paths.get(filename)));
  }

  public void saveToFile(String filename) {
    Explorer.write(Paths.get(filename),
        stream().map(s -> s + System.lineSeparator()).collect(Collectors.joining()));
  }
}
