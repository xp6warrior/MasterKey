package UI.Pages;

import UI.Frame;

import javax.swing.*;

interface IPages {
    void create(Frame frame);

    void add(JPanel panel);

    void remove(JPanel panel);
}
