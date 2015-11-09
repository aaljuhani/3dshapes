package main;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;

import java.awt.BorderLayout;

import javax.swing.JButton;
import javax.swing.JLabel;

import java.awt.Color;
import java.awt.Font;
import java.awt.GridLayout;

import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.LayoutStyle.ComponentPlacement;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class View {

	private JFrame frame;
	


	/**
	 * Create the application.
	 */
	public View() {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					View window = new View();
					window.frame.setVisible(true);
					
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
		
		initialize();
		
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 489, 435);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(new GridLayout(0, 1, 0, 0));
		
		JPanel panel = new JPanel();
		frame.getContentPane().add(panel);
		
		JLabel title = new JLabel("Lets Learn 3D shapes");
		title.setForeground(Color.DARK_GRAY);
		title.setFont(new Font("Tahoma", Font.BOLD, 24));
		title.setBackground(Color.DARK_GRAY);
		
		JLabel lblScore = new JLabel("Score");
		lblScore.setForeground(Color.DARK_GRAY);
		lblScore.setFont(new Font("Tahoma", Font.PLAIN, 20));
		
		JLabel lblLevel = new JLabel("Level");
		lblLevel.setForeground(Color.DARK_GRAY);
		lblLevel.setFont(new Font("Tahoma", Font.PLAIN, 20));
		
		JLabel LevelText = new JLabel("1");
		LevelText.setFont(new Font("Tahoma", Font.BOLD, 30));
		
		JLabel ScoreText = new JLabel("0");
		ScoreText.setFont(new Font("Tahoma", Font.BOLD, 30));
		
		JButton btnStart = new JButton("Start");
		btnStart.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				System.out.println("start");
				
			}
		});
		btnStart.setFont(new Font("Tahoma", Font.PLAIN, 16));
		
		JButton btnDone = new JButton("Done");
		btnDone.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				
			}
		});
		btnDone.setFont(new Font("Tahoma", Font.PLAIN, 16));
		
		JLabel lblTask = new JLabel("Task:");
		lblTask.setForeground(new Color(0, 51, 51));
		lblTask.setFont(new Font("Tahoma", Font.PLAIN, 16));
		
		JButton btnExplore = new JButton("Explore");
		btnExplore.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				System.out.println("Explore");
				
			}
		});
		btnExplore.setFont(new Font("Tahoma", Font.PLAIN, 16));
		GroupLayout gl_panel = new GroupLayout(panel);
		gl_panel.setHorizontalGroup(
			gl_panel.createParallelGroup(Alignment.TRAILING)
				.addGroup(gl_panel.createSequentialGroup()
					.addGap(39)
					.addComponent(btnExplore, GroupLayout.PREFERRED_SIZE, 101, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.RELATED, 136, Short.MAX_VALUE)
					.addComponent(btnStart)
					.addPreferredGap(ComponentPlacement.UNRELATED)
					.addComponent(btnDone)
					.addGap(53))
				.addGroup(gl_panel.createSequentialGroup()
					.addGap(119)
					.addComponent(title)
					.addContainerGap(101, Short.MAX_VALUE))
				.addGroup(gl_panel.createSequentialGroup()
					.addContainerGap(123, Short.MAX_VALUE)
					.addGroup(gl_panel.createParallelGroup(Alignment.LEADING)
						.addComponent(lblLevel, GroupLayout.PREFERRED_SIZE, 49, GroupLayout.PREFERRED_SIZE)
						.addComponent(lblScore))
					.addGap(141)
					.addGroup(gl_panel.createParallelGroup(Alignment.LEADING)
						.addComponent(LevelText)
						.addComponent(ScoreText, GroupLayout.PREFERRED_SIZE, 150, GroupLayout.PREFERRED_SIZE))
					.addContainerGap())
				.addGroup(Alignment.LEADING, gl_panel.createSequentialGroup()
					.addGap(53)
					.addComponent(lblTask)
					.addContainerGap(381, Short.MAX_VALUE))
		);
		gl_panel.setVerticalGroup(
			gl_panel.createParallelGroup(Alignment.TRAILING)
				.addGroup(gl_panel.createSequentialGroup()
					.addGap(24)
					.addComponent(title)
					.addGap(38)
					.addGroup(gl_panel.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblLevel, GroupLayout.PREFERRED_SIZE, 25, GroupLayout.PREFERRED_SIZE)
						.addComponent(LevelText))
					.addGap(52)
					.addGroup(gl_panel.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblScore)
						.addComponent(ScoreText, GroupLayout.PREFERRED_SIZE, 37, GroupLayout.PREFERRED_SIZE))
					.addGap(58)
					.addComponent(lblTask)
					.addPreferredGap(ComponentPlacement.RELATED, 43, Short.MAX_VALUE)
					.addGroup(gl_panel.createParallelGroup(Alignment.BASELINE)
						.addComponent(btnExplore, GroupLayout.PREFERRED_SIZE, 29, GroupLayout.PREFERRED_SIZE)
						.addComponent(btnDone, GroupLayout.PREFERRED_SIZE, 29, GroupLayout.PREFERRED_SIZE)
						.addComponent(btnStart))
					.addGap(26))
		);
		panel.setLayout(gl_panel);
	}
}
