package UIv2.Pages;

import UIv2.Frame;

import javax.swing.*;

interface IPages {
    void create(Frame frame);

    void add(JPanel panel);

    void remove(JPanel panel);
}
