package randall.common.util.mud;

import com.google.common.collect.Lists;
import java.util.ArrayList;
import java.util.List;

/**
 * @author mrzhqiang
 */
public class QuickIDList extends ArrayList<String> {

  private final List<List<QuickID>> objects = Lists.newArrayList();

  /**
   * 使用字典排序的插入方法。
   * <p>
   * 其中查询是用 二分法。
   */
  public void addRecord(String account, String name, Integer index, Integer selectID) {
    QuickID quickID = new QuickID(account, name, index, selectID);
    if (isEmpty()) {
      add(account);
      objects.add(size() - 1, Lists.newArrayList(quickID));
    } else {
      if (size() == 1) {
        int med = account.compareTo(get(0));
        if (med > 0) {
          add(account);
          objects.add(size() - 1, Lists.newArrayList(quickID));
        } else {
          if (med < 0) {
            add(0, account);
            objects.add(0, Lists.newArrayList(quickID));
          } else {
            List<QuickID> list = objects.get(0);
            list.add(quickID);
          }
        }
      } else {
        int low = 0;
        int high = size() - 1;
        int med = (high - low) / 2 + low;
        while (true) {
          if (high - low == 1) {
            int n20 = account.compareTo(get(high));
            if (n20 > 0) {
              add(high + 1, account);
              objects.add(high + 1, Lists.newArrayList(quickID));
              break;
            } else {
              if (account.compareTo(get(high)) == 0) {
                List<QuickID> list = objects.get(high);
                list.add(quickID);
                break;
              } else {
                n20 = account.compareTo(get(low));
                if (n20 > 0) {
                  add(low + 1, account);
                  objects.add(low + 1, Lists.newArrayList(quickID));
                  break;
                } else {
                  if (n20 < 0) {
                    add(low, account);
                    objects.add(low, Lists.newArrayList(quickID));
                    break;
                  } else {
                    List<QuickID> list = objects.get(low);
                    list.add(quickID);
                    break;
                  }
                }
              }
            }
          } else {
            int n1c = account.compareTo(get(med));
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
            List<QuickID> list = objects.get(med);
            list.add(quickID);
            break;
          }
        }
      }
    }
  }

  public void delRecordEx(int index) {
    if (index > size() - 1) {
      return;
    }
    List<QuickID> list = objects.get(index);
    list.clear();
    remove(index);
    objects.remove(index);
  }

  public void delRecord(int index, String account) {
    if (index > size() - 1) {
      return;
    }
    List<QuickID> list = objects.get(index);
    list.stream()
        .filter(quickID -> quickID.getAccount().equals(account))
        .forEach(list::remove);
    if (list.isEmpty()) {
      remove(index);
      objects.remove(index);
    }
  }

  public int getChrList(String account, List<QuickID> list) {
    if (isEmpty()) {
      return -1;
    }
    if (list == null) {
      list = Lists.newArrayList();
    }
    if (!list.isEmpty()) {
      list.clear();
    }
    if (size() == 1) {
      if (account.compareTo(get(0)) == 0) {
        list.addAll(objects.get(0));
        return 0;
      }
    } else {
      int low = 0;
      int high = size() - 1;
      int med = (high - low) / 2 + low;
      int n24 = -1;
      while (true) {
        if (high - low == 1) {
          if (account.compareTo(get(high)) == 0) {
            n24 = high;
          }
          if (account.compareTo(get(low)) == 0) {
            n24 = low;
          }
          break;
        } else {
          int compareValue = account.compareTo(get(med));
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
          n24 = med;
          break;
        }
      }
      if (n24 != -1) {
        list.addAll(objects.get(n24));
        return n24;
      }
    }
    return -1;
  }
}
