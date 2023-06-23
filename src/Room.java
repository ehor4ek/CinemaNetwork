package src;

//default method interface java

interface Room {
    String getType();

    void setType(String type);

    int[][] getMatrix();

    void setMatrix(int[][] matrix);

    boolean hasPlace(int x, int y);

    int buy (int i, int j);

    String toWrite();
    void addMoney(int x, int k);

    @Override
    String toString();
    String toString(char c);
    void setPrice(int x);
    int getColumns();
    int getRows();
}
