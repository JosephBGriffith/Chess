package matchcomponents.move.path;

public class Displacement {
    private final byte fileDisplacement;
    private final byte rankDisplacement;

    public Displacement(byte fileDisplacement, byte rankDisplacement) {
        this.fileDisplacement = fileDisplacement;
        this.rankDisplacement = rankDisplacement;
    }

    public byte getFileDisplacement() {
        return fileDisplacement;
    }

    public byte getRankDisplacement() {
        return rankDisplacement;
    }

    public String toString() {
        return getFileDisplacement() + ", " + getRankDisplacement();
    }
}
