import java.util.ArrayList;
import java.util.Objects;

public class State implements Comparable<State> {

    private int population;
    private int boatCapacity;

    private int missionariesLeft;
    private int missionariesRight;
    private int cannibalsLeft;
    private int cannibalsRight;
    private position boatPosition;
    private int crossNumber = 0; // also g(n)
    private State father = null;

    private int score;

    State(int n, int m) { //n = arithmos missionaries/cannibals, m = xwritikotita varkas
        this.population = n;
        this.missionariesLeft = n;
        this.missionariesRight = 0;
        this.cannibalsLeft = n;
        this.cannibalsRight = 0;
        this.boatCapacity = m;
        this.boatPosition = position.LEFT;
    }

    State(int missionariesLeft, int missionariesRight, int cannibalsLeft, int cannibalsRight, int boatCapacity, position boatPosition, int crossNumber) {
        this.population = missionariesLeft + missionariesRight;
        this.missionariesLeft = missionariesLeft;
        this.missionariesRight = missionariesRight;
        this.cannibalsLeft = cannibalsLeft;
        this.cannibalsRight = cannibalsRight;
        this.boatCapacity = boatCapacity;
        this.boatPosition = boatPosition;
        this.crossNumber = crossNumber;
    }

    public int getScore() {
        return score;
    }

    public int getCrossNumber() {
        return crossNumber;
    }

    State getFather() {
        return this.father;
    }

    void setFather(State father) {
        this.father = father;
    }

    ArrayList<State> getChildren(boolean useHeuristic) {
        ArrayList<State> children = new ArrayList<>();

        for (int missionariesOnBoat = 0; missionariesOnBoat <= boatCapacity; missionariesOnBoat++) {
            for (int cannibalsOnBoat = 0; cannibalsOnBoat <= boatCapacity; cannibalsOnBoat++) {
                if (missionariesOnBoat + cannibalsOnBoat <= boatCapacity && missionariesOnBoat + cannibalsOnBoat > 0) {
                    if (boatPosition == position.LEFT) {
                        if ((missionariesLeft - missionariesOnBoat >= cannibalsLeft - cannibalsOnBoat || missionariesLeft - missionariesOnBoat == 0) && (missionariesRight + missionariesOnBoat >= cannibalsRight + cannibalsOnBoat || missionariesRight + missionariesOnBoat == 0)) {
                            State child = new State(missionariesLeft - missionariesOnBoat, missionariesRight + missionariesOnBoat, cannibalsLeft - cannibalsOnBoat, cannibalsRight + cannibalsOnBoat, boatCapacity, position.RIGHT, crossNumber + 1);
                            if (useHeuristic) child.heuristic();
                            child.setFather(this);
                            children.add(child);
                        }
                    } else { // boatPosition == position.RIGHT
                        if ((missionariesLeft + missionariesOnBoat >= cannibalsLeft + cannibalsOnBoat || missionariesLeft + missionariesOnBoat == 0) && (missionariesRight - missionariesOnBoat >= cannibalsRight - cannibalsOnBoat || missionariesRight - missionariesOnBoat == 0)) {
                            State child = new State(missionariesLeft + missionariesOnBoat, missionariesRight - missionariesOnBoat, cannibalsLeft + cannibalsOnBoat, cannibalsRight - cannibalsOnBoat, boatCapacity, position.LEFT, crossNumber + 1);
                            if (useHeuristic) child.heuristic();
                            child.setFather(this);
                            children.add(child);
                        }
                    }
                }
            }
        }

        return children;
    }

    boolean isFinal() {
        return missionariesRight == population && cannibalsRight == population;
    }

    void print() {
        System.out.print("                 Left    ||    Right");
        switch (boatPosition) {
            case LEFT:
                System.out.println("     --- Boat: LEFT | h(n) = " + getScore());
                break;
            case RIGHT:
                System.out.println("     --- Boat: RIGHT | h(n) = " + getScore());
                break;
        }
        System.out.println("             ------------||------------");
        System.out.print("Missionaries:");
        System.out.print("     " + missionariesLeft + "      " + "||" + "     " + missionariesRight);
        System.out.println();
        System.out.print("   Cannibals:");
        System.out.print("     " + cannibalsLeft + "      " + "||" + "     " + cannibalsRight);
        System.out.println();
        System.out.println("             ------------||------------");
    }

    //estw oti i varka mporei na perasei adeia, to score einai posa cross prepei na kanei i varka.
    void heuristic() {
        if (boatPosition == position.RIGHT) {
            if (missionariesLeft + cannibalsLeft == 0) this.score = 0;
            else this.score = (int) Math.ceil((double)(missionariesLeft + cannibalsLeft)/boatCapacity) * 2;
        }
        else { // boat LEFT
            if (missionariesLeft + cannibalsLeft <= boatCapacity) this.score = 1;
            else this.score = (int) Math.ceil((double)(missionariesLeft + cannibalsLeft)/boatCapacity) * 2 - 1;
        }
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        State state = (State) o;
        return missionariesLeft == state.missionariesLeft && missionariesRight == state.missionariesRight && cannibalsLeft == state.cannibalsLeft && cannibalsRight == state.cannibalsRight && boatPosition == state.boatPosition;
    }

    @Override
    public int hashCode() {
        return Objects.hash(missionariesLeft, missionariesRight, cannibalsLeft, cannibalsRight, boatPosition);
    }

    @Override
    public int compareTo(State s) {
        return Integer.compare(this.score + this.getCrossNumber(), s.score + s.getCrossNumber()); // add crossNumber for A*
    }

    enum position {LEFT, RIGHT}

}
