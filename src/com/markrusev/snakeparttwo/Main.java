package com.markrusev.snakeparttwo;

import java.awt.Color;

import javax.swing.JFrame;

public class Main {

	public static void main(String[] args) {
		JFrame window = new JFrame("Snake");
		Game gameplay = new Game();
		
		
		window.setBounds(10,10,905,700);
		window.setVisible(true);
		window.setResizable(false);
		window.setBackground(Color.WHITE);
		window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		window.add(gameplay);

	}

}
