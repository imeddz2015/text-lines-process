package textProcess;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.JLabel;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class ref extends JFrame {

	private JPanel contentPane;
	private JTextField title;
	private JTextField link;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					ref frame = new ref();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public ref() {
		setBounds(100, 100, 614, 142);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		title = new JTextField();
		title.setBounds(52, 11, 529, 20);
		contentPane.add(title);
		title.setColumns(10);
		
		link = new JTextField();
		link.setColumns(10);
		link.setBounds(52, 42, 529, 20);
		contentPane.add(link);
		
		JButton btnNewButton = new JButton("OK");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				ok();
				
			}
		});
		btnNewButton.setBounds(492, 70, 89, 23);
		contentPane.add(btnNewButton);
		
		JButton btnNewButton_1 = new JButton("Annuler");
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				annuler();
			}
		});
		btnNewButton_1.setBounds(393, 70, 89, 23);
		contentPane.add(btnNewButton_1);
		
		JLabel lblTitre = new JLabel("Titre");
		lblTitre.setBounds(10, 14, 46, 14);
		contentPane.add(lblTitre);
		
		JLabel lblLien = new JLabel("Lien");
		lblLien.setBounds(10, 45, 46, 14);
		contentPane.add(lblLien);
	}

	//==================================================================================
	
	protected void annuler() {

		this.hide();
		
	}

	protected void ok() { 
		
		SystemTrayDemo.currentPaperTitle = title.getText() ;
		SystemTrayDemo.currentPaperLink = link.getText() ;
		
		this.hide();
	}
	
}
