package Gui;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JFrame;
import javax.swing.JOptionPane;

import CustomControle.LabelStyle1;
import CustomControle.ButtonStyle1;
import CustomControle.TextFieldStyle1;


public class test {
    public static void main(String[] args) {
        // إنشاء نافذة (JFrame)
        JFrame frame = new JFrame("Car Rental Management");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(500, 400);

        // تخصيص خلفية نافذة التطبيق
        frame.getContentPane().setBackground(new Color(245, 245, 245)); // خلفية فاتحة

        // إنشاء الزر المخصص
        ButtonStyle1 rentButton = new ButtonStyle1("Rent Car");
        ButtonStyle1 returnButton = new ButtonStyle1("Return Car");

        // إنشاء حقل النص المخصص
        TextFieldStyle1 carIdField = new TextFieldStyle1(20);
        TextFieldStyle1 customerNameField = new TextFieldStyle1(20);

        // إنشاء تسمية مخصصة
        LabelStyle1 carIdLabel = new LabelStyle1("Car ID:",100,50);
        LabelStyle1 customerNameLabel = new LabelStyle1("Customer Name:");
        // تخصيص Layout
        frame.setLayout(new FlowLayout());

        // إضافة العناصر إلى النافذة
        frame.add(carIdLabel);
        frame.add(carIdField);
        frame.add(customerNameLabel);
        frame.add(customerNameField);
        frame.add(rentButton);
        frame.add(returnButton);

        // إضافة حدث عند الضغط على الزر
        rentButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JOptionPane.showMessageDialog(frame, "Car rented successfully!");
            }
        });

        returnButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JOptionPane.showMessageDialog(frame, "Car returned successfully!");
            }
        });

        // عرض النافذة
        frame.setVisible(true);
    }
}