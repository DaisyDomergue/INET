public class Treasure {

    private String type;
    private int posX;
    private int posY;
    private int value;

    public Treasure(String type, int posX, int posY, int value) {
        this.type = type;
        this.posX = posX;
        this.posY = posY;
        this.value = value;
    }

    public int getX() {
        return posX;
    }

    public int getY() {
        return posY;
    }

    public void setPosX(int posX) {
        this.posX = posX;
    }

    public void setPosY(int posY) {
        this.posY = posY;
    }

    public int getValue() {
        return value;
    }

}