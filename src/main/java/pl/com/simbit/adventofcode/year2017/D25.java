package pl.com.simbit.adventofcode.year2017;

import pl.com.simbit.adventofcode.Day;

public class D25 implements Day {

  @Override
  public Object problem2() {
    return null;
  }

  @Override
  public Object problem1() {

    int movements = 12208951;

    int[] values = new int[movements * 2];
    for (int i = 0; i < movements * 2; i++) {
      values[i] = 0;
    }

    State A = new State();
    State B = new State();
    State C = new State();
    State D = new State();
    State E = new State();
    State F = new State();

    A.set0(1, 1, B);
    A.set1(0, -1, E);
    B.set0(1, -1, C);
    B.set1(0, 1, A);
    C.set0(1, -1, D);
    C.set1(0, 1, C);
    D.set0(1, -1, E);
    D.set1(0, -1, F);
    E.set0(1, -1, A);
    E.set1(1, -1, C);
    F.set0(1, -1, E);
    F.set1(1, 1, A);

    int index = movements;
    State current = A;
    for (int i = 0; i < movements; i++) {
      if (values[index] == 0) {
        values[index] = current.value0;
        index += current.move0;
        current = current.state0;
      } else {
        values[index] = current.value1;
        index += current.move1;
        current = current.state1;
      }
    }

    int checksum = 0;
    for (int i = 0; i < movements * 2; i++) {
      if (values[i] > 0) {
        checksum++;
      }
    }

    return checksum;
  }

  class State {

    int value0;
    int value1;
    int move0;
    int move1;
    State state0;
    State state1;

    public void set0(int value, int move, State state) {
      this.value0 = value;
      this.move0 = move;
      this.state0 = state;
    }

    public void set1(int value, int move, State state) {
      this.value1 = value;
      this.move1 = move;
      this.state1 = state;
    }
  }
}
