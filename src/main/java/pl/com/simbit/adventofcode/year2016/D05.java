package pl.com.simbit.adventofcode.year2016;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import pl.com.simbit.adventofcode.Day;

public class D05 implements Day {

  @Override
  public Object problem1() {
    String password = "";
    String input = "abbhdwsy";
    int i = 0;
    while (password.length() < 8) {
      String s = MD5(input + i);
      i++;
      if (s.startsWith("00000")) {
        password += s.substring(5, 6);
      }
    }
    return password;
  }

  public String MD5(String md5) {
    try {
      java.security.MessageDigest md = java.security.MessageDigest.getInstance("MD5");
      byte[] array = md.digest(md5.getBytes());
      StringBuffer sb = new StringBuffer();
      for (int i = 0; i < array.length; ++i) {
        sb.append(Integer.toHexString((array[i] & 0xFF) | 0x100).substring(1, 3));
      }
      return sb.toString();
    } catch (java.security.NoSuchAlgorithmException e) {
    }
    return null;
  }

  @Override
  public Object problem2() {
    String[] pass = new String[8];
    for (int i = 0; i < 8; i++) {
      pass[i] = "";
    }
    String password = "";
    String input = "abbhdwsy";
    int i = 0;
    while (password.length() < 8) {
      password = "";
      String s = MD5(input + i);
      i++;
      try {

        if (s.startsWith("00000") && Integer.parseInt(s.substring(5, 6)) >= 0
            && Integer.parseInt(s.substring(5, 6)) < 8 && pass[Integer.parseInt(s.substring(5, 6))]
            .equals("")) {
          pass[Integer.parseInt(s.substring(5, 6))] = s.substring(6, 7);
          for (int ii = 0; ii < 8; ii++) {
            password += pass[ii];
          }
        }
      } catch (NumberFormatException e) {

      }
    }
    return password;
  }
}
