package Model;

import java.util.Map;

public class Board
{
    public void setA_point(int x, int y) {
        A_point = new Point(x, y);
    }
    public void setB_point(int x, int y) {
        B_point = new Point(x, y);
    }

    public Board(int searched_x, int searched_y, int A_x, int A_y, int B_x, int B_y) {
        Searched_point = new Point(searched_x, searched_y);
        A_point = new Point(A_x, A_y);
        B_point = new Point(B_x, B_y);
    }

    public Board( int searched_x, int searched_y) {
        Searched_point = new Point(searched_x, searched_y);
    }

    public Map.Entry<Closest, Integer> calculate_distance()
    {
        int distance_A = Math.abs(Searched_point.getX() - A_point.getX()) + Math.abs(Searched_point.getY() - A_point.getY());
        int distance_B = Math.abs(Searched_point.getX() - B_point.getX()) + Math.abs(Searched_point.getY() - B_point.getY());
        if (distance_A == distance_B) {
            return Map.entry(Closest.EQUAL, distance_A);
        }
        if (distance_A < distance_B) {
            return Map.entry(Closest.A, distance_A);
        }
        return Map.entry(Closest.B, distance_B);
    }


    private static class Point
    {
        private int x;
        private int y;

        public Point(int x, int y) {
            this.x = x;
            this.y = y;
        }

        public int getX() {
            return x;
        }

        public void setX(int x) {
            this.x = x;
        }

        public int getY() {
            return y;
        }

        public void setY(int y) {
            this.y = y;
        }
    }

    public int getSearched_point_x() {
        return Searched_point.getX();
    }

    public int getSearched_point_y() {
        return Searched_point.getY();
    }

    public int getA_x() {
        return A_point.getX();
    }

    public int getA_y() {
        return A_point.getY();
    }

    public int getB_x() {
        return B_point.getX();
    }

    public int getB_y() {
        return B_point.getY();
    }
    private Point Searched_point;
    private Point A_point;
    private Point B_point;

    public void printDebugState() {
        System.out.println("SEARCHED: " + Searched_point.getX() + ", " + Searched_point.getY());
        System.out.println("A: " + A_point.getX() + ", " + A_point.getY());
        System.out.println("B: " + B_point.getX() + ", " + B_point.getY());
    }

}
