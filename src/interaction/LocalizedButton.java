package interaction;

import javax.swing.*;

public class LocalizedButton extends JButton {
    private final byte file;
    private final byte rank;

    public LocalizedButton(byte file, byte rank) {
        super();
        this.file = file;
        this.rank = rank;
    }

    public byte getFile() {
        return file;
    }

    public byte getRank() {
        return rank;
    }
}
