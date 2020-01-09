package randall.common.util.mud;

import com.google.common.base.Ascii;
import com.google.common.base.Strings;
import java.nio.file.Files;
import java.nio.file.Paths;
import randall.common.util.HUtil32;

/**
 * @author mrzhqiang
 */
public class QuickIndexList extends StringList<StringList<Integer>> {
  public void addRecord(String name, String delName, Integer index) {
    if (isEmpty()) {
      StringList<Integer> list = StringList.newInstance();
      list.addObject(delName, index);
      addObject(name, list);
    } else {
      if (size() == 1) {
        int med = name.compareTo(get(0));
        if (med > 0) {
          StringList<Integer> list = StringList.newInstance();
          list.addObject(delName, index);
          addObject(name, list);
        } else {
          if (med < 0) {
            StringList<Integer> list = StringList.newInstance();
            list.addObject(delName, index);
            insertObject(0, name, list);
          } else {
            objects.get(0).addObject(delName, index);
          }
        }
      } else {
        int low = 0;
        int high = size() - 1;
        int med = (high - low) / 2 + low;
        while (true) {
          if (high - low == 1) {
            int n20 = name.compareTo(get(high));
            if (n20 > 0) {
              StringList<Integer> list = StringList.newInstance();
              list.addObject(delName, index);
              insertObject(high + 1, name, list);
              break;
            } else {
              if (name.compareTo(get(high)) == 0) {
                objects.get(high).addObject(delName, index);
                break;
              } else {
                n20 = name.compareTo(get(low));
                if (n20 > 0) {
                  StringList<Integer> list = StringList.newInstance();
                  list.addObject(delName, index);
                  insertObject(low + 1, name, list);
                  break;
                } else {
                  if (n20 < 0) {
                    StringList<Integer> list = StringList.newInstance();
                    list.addObject(delName, index);
                    insertObject(low, name, list);
                    break;
                  } else {
                    objects.get(low).addObject(delName, index);
                    break;
                  }
                }
              }
            }
          } else {
            int n1c = name.compareTo(get(med));
            if (n1c > 0) {
              low = med;
              med = (high - low) / 2 + low;
              continue;
            }
            if (n1c < 0) {
              high = med;
              med = (high - low) / 2 + low;
              continue;
            }
            objects.get(med).addObject(delName, index);
            break;
          }
        }
      }
    }
  }

  public void delRecord(int index) {
    if (index > size() - 1) {
      return;
    }
    objects.remove(index);
    remove(index);
  }

  public Integer getIndex(String name) {
    if (isEmpty()) {
      return -1;
    }
    if (size() == 1) {
      if (name.compareTo(get(0)) == 0) {
        return 0;
      }
    } else {
      int low = 0;
      int high = size() - 1;
      int med = (high - low) / 2 + low;
      int n24 = -1;
      while (true) {
        if (high - low == 1) {
          if (name.compareTo(get(high)) == 0) {
            n24 = high;
          }
          if (name.compareTo(get(low)) == 0) {
            n24 = low;
          }
          break;
        } else {
          int n20 = name.compareTo(get(med));
          if (n20 > 0) {
            low = med;
            med = (high - low) / 2 + low;
            continue;
          }
          if (n20 < 0) {
            high = med;
            med = (high - low) / 2 + low;
            continue;
          }
          n24 = med;
          break;
        }
      }
      return n24;
    }
    return -1;
  }

  public void loadFromFile(String filename) {
    StringList<String> tempList = StringList.newInstance();
    if (Files.exists(Paths.get(filename))) {
      tempList.loadFromFile(filename);
    }
    tempList.forEach(s -> {
      // fixme 或许 delphi 中 读取文件的字符串数组，下标 0 处的字符是 Ascii.NUL，所以需要进一步观察此处
      if (!Strings.isNullOrEmpty(s) && s.charAt(1) != ';') {
        String[] strings = HUtil32.getValidStr3(s, new char[] {Ascii.HT, ' '});
        s = strings[0];
        String name = strings[1];
        strings = HUtil32.getValidStr3(s, new char[] {Ascii.HT, ' '});
        s = strings[0];
        String delName = strings[1];
        strings = HUtil32.getValidStr3(s, new char[] {Ascii.HT, ' '});
        String delID = strings[1];
        int deleteId = -1;
        try {
          deleteId = Integer.parseInt(delID);
        } catch (Exception ignore) {
        }
        if (deleteId > 0) {
          addRecord(name, delName, deleteId);
        }
      }
    });
  }

  public void saveFile(String filename) {
    StringList<String> tempList = StringList.newInstance();
    for (int i = 0; i < size() - 1; i++) {
      StringList<Integer> list = objects.get(i);
      for (int j = 0; j < list.size(); j++) {
        tempList.add(get(i) + Ascii.HT + list.get(j) + Ascii.HT + list.objects.get(j));
      }
    }
    tempList.saveToFile(filename);
  }
}
