package matchcomponents.move.path;

public class Path {
    private final Displacement[] displacements;

    public Path(int length) {
        displacements = new Displacement[length];
    }

    public void appendDisplacement(byte file, byte rank) {
        displacements[getDisplacementsFirstNullIndex()] = new Displacement(file, rank);
    }

    public Displacement[] getDisplacements() {
        return displacements;
    }

    public static Path pathRotated90DegreesClockwise(Path path) {
        Displacement[] displacements = path.getDisplacements();
        Path rotatedPath = new Path(displacements.length);

        for(Displacement displacement : displacements) {
            rotatedPath.appendDisplacement(displacement.getRankDisplacement(), (byte) -displacement.getFileDisplacement());
        }

        return rotatedPath;
    }

    private int getDisplacementsFirstNullIndex() {
        for(int i = displacements.length - 1; i >= 0; i--) {
            if(displacements[i] == null && i > 0 && displacements[i - 1] != null) {
                return i;
            }
        }

        return 0;
    }
}
