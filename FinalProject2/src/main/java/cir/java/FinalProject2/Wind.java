package cir.java.FinalProject2;
import java.awt.EventQueue;
import java.awt.Font;
import java.util.Collections;
import java.util.List;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.GridLayout;
import javax.swing.JLabel;
import javax.swing.JRadioButton;
import javax.swing.SwingConstants;
import javax.swing.ButtonGroup;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;
import java.awt.event.ActionEvent;
import javax.swing.JTextArea;
import java.awt.Color;

public class Wind extends JFrame {

	class QuestionRdBt extends JRadioButton {
		public int getNum() {
			return num;
		}
		public void setNum(int num) {
			this.num = num;
		}
		/**
		 * 
		 */
		private static final long serialVersionUID = 1L;
		private int num;
		String st;
		public QuestionRdBt(int num,String st) {
			super(st);
			this.num = num;			
		}
	}

	private class BeginListener implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			if (currentQuestion>-1) {
				Answer answ = questionList.get(currentQuestion);
				if (answ.parts.get(currentAnswer).correct) {
					count++;
				}
			}
			currentQuestion++;
			if (currentQuestion == 0) {
				btnNewButton.setText("Далее");
			}
			else if (currentQuestion==questionList.size() -1) {
				btnNewButton.setText("Завершить");
			}
			if (currentQuestion < questionList.size()) {
				showQuestion(e,currentQuestion);
			}
			else {
				panel2.removeAll();
				panel2.validate();
				panel2.add(lblNewLabel);
				lblNewLabel.setText("Вы молодцы!");
				lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
				lblNewLabel.setVisible(true);
				panel2.repaint();
				lblNewLabel_1.setText("Вопросов пройдено: "+currentQuestion+", Правильных ответов: "+count);
				btnNewButton.setVisible(false);


			}


		}
	}

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JButton btnNewButton;
	JPanel panel1 = new JPanel();
	JPanel panel2 = new JPanel();
	JPanel panel3 = new JPanel();
	List<Answer> questionList = loadQuestionsFromJson();
	int count = 0;
	int currentQuestion = -1;
	int currentAnswer = 0;
	private JLabel lblNewLabel_1;
	private JLabel lblNewLabel;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Wind frame = new Wind();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
	private List<Answer> loadQuestionsFromJson() {
		try {
			ObjectMapper mapper = new ObjectMapper();
			File file = new File("questions.txt");
			if (!file.exists()) {
				System.out.println("Файла нет");
				return null;
			}
			List<Answer> questions = mapper.readValue(file, new TypeReference<List<Answer>>() {});
			Collections.shuffle(questions);
			for (Answer q: questions) {
				
			}

			return questions;

		}
		catch(IOException e) {
			System.err.println("Ошибка"+e.getMessage());
			return null;
		}
	}

	public void showQuestion(ActionEvent e,int number) {
		panel2.removeAll();
		btnNewButton.setEnabled(false);
		Answer answ = questionList.get(number);
		Collections.shuffle(answ.parts);
		lblNewLabel_1.setText("Вопросов пройдено: "+number+", Правильных ответов: "+count);

		JTextArea textArea = new JTextArea(answ.question);
		//tArea.setHorizontalAlignment(SwingConstants.CENTER);
		textArea.setFont(new Font("Tahoma", Font.PLAIN, 20));
		textArea.setBackground(new Color(237, 251, 234));
		textArea.setLineWrap(true);
		textArea.setWrapStyleWord(true);
		panel2.add(textArea);
		ButtonGroup group = new ButtonGroup();
		for (int i=0; i < answ.parts.size(); i++  ) {
			QuestionRdBt rdbtnNewRadioButton = new QuestionRdBt(i,answ.parts.get(i).string);
			rdbtnNewRadioButton.setFont(new Font("Tahoma", Font.PLAIN, 24));
			rdbtnNewRadioButton.setBackground(new Color(231, 250, 222));
			rdbtnNewRadioButton.addActionListener(new ActionListener() {

				@Override
				public void actionPerformed(ActionEvent e) {
					QuestionRdBt bt = (QuestionRdBt) e.getSource();
					currentAnswer = bt.getNum();
					System.out.println(currentAnswer);
					btnNewButton.setEnabled(true);

				}});
			group.add(rdbtnNewRadioButton);
			panel2.add(rdbtnNewRadioButton);
		}

		panel2.validate();
	}
	/**
	 * Create the frame.
	 */
	public Wind() {
		setBackground(new Color(196, 250, 180));
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 616, 534);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(183, 240, 170));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(new BorderLayout(0, 0));

		JPanel panel1 = new JPanel();
		contentPane.add(panel1, BorderLayout.NORTH);
		panel1.setBackground(new Color(218, 247, 208));

		lblNewLabel_1 = new JLabel("");
		lblNewLabel_1.setFont(new Font("Tahoma", Font.PLAIN, 24));
		lblNewLabel_1.setBackground(new Color(170, 236, 142));
		panel1.add(lblNewLabel_1);

		contentPane.add(panel2, BorderLayout.CENTER);
		panel2.setLayout(new GridLayout(0, 1, 0, 0));
		panel2.setBackground(new Color(218, 247, 208));

		JPanel panel2 = new JPanel();
		lblNewLabel = new JLabel("");
		lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 24));
		panel2.add(lblNewLabel);

		JPanel panel3 = new JPanel();
		contentPane.add(panel3, BorderLayout.SOUTH);
		panel3.setBackground(new Color(218, 247, 208));

		btnNewButton = new JButton("Начать");
		btnNewButton.addActionListener(new BeginListener());
		btnNewButton.setFont(new Font("Tahoma", Font.PLAIN, 24));
		btnNewButton.setBackground(new Color(170, 236, 142));
		panel3.add(btnNewButton);		
	}

}