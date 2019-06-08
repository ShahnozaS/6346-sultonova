package ru.cft.focusstart.gameprocess;

import ru.cft.focusstart.field.Cell;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

public class CustomListener implements MouseListener {
    @Override
    public void mousePressed(MouseEvent e) {
        if (e.getButton() == MouseEvent.BUTTON1) {
            GameProcess.openCell((Cell) e.getSource());
        }
        if (e.getButton() == MouseEvent.BUTTON3) {
            GameProcess.setFlag((Cell) e.getSource());
        }
    }

    @Override
    public void mouseClicked(MouseEvent e) {
    }

    @Override
    public void mouseReleased(MouseEvent e) {
    }

    @Override
    public void mouseEntered(MouseEvent e) {
    }

    @Override
    public void mouseExited(MouseEvent e) {
    }
}
