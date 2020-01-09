package randall.common.util.mud;

import com.google.common.collect.Lists;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * ./GameOfMir/Common/MudUtil.pas
 * <p>
 * 这里的方法都比较臃肿，并且看起来非常生涩，以后如果理解了需求，可以优化一下。
 *
 * @author mrzhqiang
 */
public class QuickList extends ArrayList<String> {
  public boolean caseSensitive;

  private final Object mutex = this;
  private final List<Integer> objects = Lists.newArrayList();

  public boolean isCaseSensitive() {
    return caseSensitive;
  }

  public void setCaseSensitive(boolean caseSensitive) {
    this.caseSensitive = caseSensitive;
  }

  public boolean delRecordEx(String chrName) {
    synchronized (mutex) {
      int index = indexOf(chrName);
      if (index > -1) {
        remove(index);
        objects.remove(index);
        return true;
      }
    }
    return false;
  }

  public boolean delRecord(int index, String chrName) {
    synchronized (mutex) {
      int i = indexOf(chrName);
      if (index == objects.get(i)) {
        remove(i);
        objects.remove(i);
        return true;
      }
    }
    return false;
  }

  public int getIndex(String name) {
    if (!isEmpty()) {
      int size = size();
      if (size == 1) {
        if (name.compareTo(get(0)) == 0) {
          return 0;
        }
      } else {
        int low = 0;
        int high = size - 1;
        int med = (high - low) / 2 + low;
        while (true) {
          if ((high - low) == 1) {
            if (name.compareTo(get(high)) == 0) {
              return high;
            } else if (name.compareTo(get(low)) == 0) {
              return low;
            }
          } else {
            int compareValue = name.compareTo(get(med));
            if (compareValue > 0) {
              low = med;
              med = (high - low) / 2 + low;
              continue;
            }
            if (compareValue < 0) {
              high = med;
              med = (high - low) / 2 + low;
              continue;
            }
            return med;
          }
        }
      }
    }
    return -1;
  }

  public void sortString(int min, int max) {
    if (!isEmpty()) {
      while (true) {
        int minValue = min;
        int maxValue = max;
        // delphi shr == java >>>
        String s18 = get((minValue + maxValue) >>> 1);
        do {
          while (get(minValue).compareTo(s18) < 0) {
            minValue++;
          }
          while (get(maxValue).compareTo(s18) > 0) {
            maxValue--;
          }
          if (minValue <= maxValue) {
            // TStringList.ExChange
            // Swaps the position of two strings in the list.
            Collections.swap(this, minValue, maxValue);
            minValue++;
            maxValue--;
          }
        } while (minValue <= maxValue);
        if (min < maxValue) {
          sortString(min, maxValue);
        }
        min = minValue;
        if (minValue >= max) {
          break;
        }
      }
    }
  }

  public boolean addRecord(String name, int index) {
    if (isEmpty()) {
      add(name);
      objects.add(size() - 1, index);
    } else {
      if (size() == 1) {
        int compareValue = name.compareTo(get(0));
        if (compareValue > 0) {
          add(name);
          objects.add(size() - 1, index);
        } else if (compareValue < 0) {
          add(0, name);
          objects.add(0, index);
        }
      } else {
        int low = 0;
        int high = size() - 1;
        int med = (high - low) / 2 + low;
        while (true) {
          if (high - low == 1) {
            med = name.compareTo(get(high));
            if (med > 0) {
              add(high + 1, name);
              objects.add(high + 1, index);
            } else {
              med = name.compareTo(get(low));
              if (med > 0) {
                add(low + 1, name);
                objects.add(low + 1, index);
              } else {
                if (med < 0) {
                  add(low, name);
                  objects.add(low, index);
                } else {
                  return false;
                }
              }
            }
          } else {
            int compareValue = name.compareTo(get(med));
            if (compareValue > 0) {
              low = med;
              med = (high - low) / 2 + low;
              continue;
            }
            if (compareValue < 0) {
              high = med;
              med = (high - low) / 2 + low;
              continue;
            }
            return false;
          }
        }
      }
    }
    return true;
  }
}
