package SociologyInspiredSmartMeter.SmartMeterClient;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import javax.imageio.ImageIO;
import javax.swing.BorderFactory;
import javax.swing.DefaultComboBoxModel;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.SwingConstants;
import javax.swing.Timer;
import javax.swing.border.EmptyBorder;

public class SettingsPage extends JFrame{

    private JPanel contentPane;

    private SociologyInspiredSmartMeter.Controller controller;

	private Config config;

	private Settings settings;

    public SettingsPage(SociologyInspiredSmartMeter.Controller passedController, Config passedConfig, Settings passedSettings) {

        this.controller = passedController;

		this.config = passedConfig;

		this.settings = passedSettings;

        buildFrame();

		staticElements();

		userPreferences();

		devicePreferences();

    }
	
	public void buildFrame() {

		setTitle("Smart Meter");

		setResizable(false);

		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		setBounds(0, 0, 1380, 800);

		contentPane = new JPanel();

		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		contentPane.setBackground(Color.WHITE);

		contentPane.setLayout(null);

		//Announcement Text
		JLabel announcementText = new JLabel(controller.announcementHandler(), SwingConstants.CENTER);
		announcementText.setBounds(80,45, 1100, 32);
		announcementText.setFont(new Font("Serif", Font.PLAIN, 32));
		
		
		// Clock
		JLabel onScreenClock = new JLabel();
		onScreenClock.setBounds(1260, 45, 200, 32);
		onScreenClock.setFont(new Font("Serif", Font.PLAIN, 32));
		

		Timer timer = new Timer(1000, (ActionEvent e) -> {
			DateTimeFormatter myTime = DateTimeFormatter.ofPattern("HH:mm");
			LocalDateTime now = LocalDateTime.now();
			onScreenClock.setText(String.valueOf(myTime.format(now)));
			announcementText.setText(controller.announcementHandler());
		});

		timer.setRepeats(true);
		timer.start();
		contentPane.add(onScreenClock);
		contentPane.add(announcementText);
		
		// Static Images
		// Background Image
		JLabel backgroundImage;
		try {
			InputStream backgroundImageStream = getClass().getResourceAsStream("/resources/BackgroundSettings.png");
			BufferedImage backgroundImageImage = ImageIO.read(backgroundImageStream);
			backgroundImage = new JLabel(new ImageIcon(backgroundImageImage));
			backgroundImage.setBounds(0, 0, 1400, 800);
			contentPane.add(backgroundImage);
			contentPane.setComponentZOrder(backgroundImage, 2);
		} catch (IOException backgroundImageErr) {
			System.out.println("Background Image unable to load");
			System.exit(2);
		}

		// Icon Images
		// Battery Icon
		JLabel batteryIcon;
		try {
			InputStream batteryIconStream = getClass().getResourceAsStream("/resources/battery.png");
			BufferedImage batteryIconImage = ImageIO.read(batteryIconStream);
			batteryIcon = new JLabel(new ImageIcon(batteryIconImage));
			batteryIcon.setBounds(1190, 45, 64, 32);
			contentPane.add(batteryIcon);
			contentPane.setComponentZOrder(batteryIcon, 1);
		} catch (IOException batteryIconErr) {
			System.out.println("Battery Icon unable to load");
			System.exit(2);
		}

		// Signal Icon
		JLabel signalIcon;
		try {
			InputStream signalIconStream = getClass().getResourceAsStream("/resources/signal.png");
			BufferedImage signalIconImage = ImageIO.read(signalIconStream);
			signalIcon = new JLabel(new ImageIcon(signalIconImage));
			signalIcon.setBounds(40, 40, 32, 32);
			contentPane.add(signalIcon);
			contentPane.setComponentZOrder(signalIcon, 1);
		} catch (IOException signalIconErr) {
			System.out.println("Signal Icon unable to load");
			System.exit(2);
		}

		// Navigation
		// Home Navigation Button
		JButton btnHome;
		try {
			InputStream buttonIconHomeStream = getClass().getResourceAsStream("/resources/home.png");
			BufferedImage buttonIconHome = ImageIO.read(buttonIconHomeStream);
			btnHome = new JButton(new ImageIcon(buttonIconHome));
			btnHome.setBorder(BorderFactory.createEmptyBorder());
			btnHome.setContentAreaFilled(false);
			btnHome.addActionListener(new ActionListener() {
				@SuppressWarnings("deprecation")
				public void actionPerformed(ActionEvent e) {

					dispose();
					controller.displayHomePage();

				}
			});
			btnHome.setBounds(648, 687, 64, 64);
			contentPane.add(btnHome);
			contentPane.setComponentZOrder(btnHome, 1);
		} catch (IOException btnHomeErr) {
			System.out.println("Home Icon unable to load");
			System.exit(2);
		}

		// Settings Navigation Button
		JButton btnSettings;
		try {
			InputStream buttonIconSettingsStream = getClass().getResourceAsStream("/resources/settings.png");
			BufferedImage buttonIconSettings = ImageIO.read(buttonIconSettingsStream);
			btnSettings = new JButton(new ImageIcon(buttonIconSettings));
			btnSettings.setBorder(BorderFactory.createEmptyBorder());
			btnSettings.setContentAreaFilled(false);
			btnSettings.addActionListener(new ActionListener() {
				@SuppressWarnings("deprecation")
				public void actionPerformed(ActionEvent e) {

					dispose();
					controller.displaySettingsPage();

				}
			});
			btnSettings.setBounds(808, 687, 64, 64);
			contentPane.add(btnSettings);
			contentPane.setComponentZOrder(btnSettings, 1);
		} catch (IOException btnSettingsErr) {
			System.out.println("Settings Icon unable to load");
			System.exit(2);
		}

		// Refresh Navigation Button
		JButton btnRefresh;
		try {
			InputStream buttonIconRefreshStream = getClass().getResourceAsStream("/resources/refresh.png");
			BufferedImage buttonIconRefresh = ImageIO.read(buttonIconRefreshStream);
			btnRefresh = new JButton(new ImageIcon(buttonIconRefresh));
			btnRefresh.setBorder(BorderFactory.createEmptyBorder());
			btnRefresh.setContentAreaFilled(false);
			btnRefresh.addActionListener(new ActionListener() {
				@SuppressWarnings("deprecation")
				public void actionPerformed(ActionEvent e) {

					revalidate();
					repaint();

				}
			});
			btnRefresh.setBounds(728, 687, 64, 64);
			contentPane.add(btnRefresh);
			contentPane.setComponentZOrder(btnRefresh, 1);
		} catch (IOException btnRefreshErr) {
			System.out.println("Refresh Icon unable to load");
			System.exit(2);
		}

		// Calendar Navigation Button
		JButton btnCalendar;
		try {
			InputStream buttonIconCalendarStream = getClass().getResourceAsStream("/resources/calendar.png");
			BufferedImage buttonIconCalendar = ImageIO.read(buttonIconCalendarStream);
			btnCalendar = new JButton(new ImageIcon(buttonIconCalendar));
			btnCalendar.setBorder(BorderFactory.createEmptyBorder());
			btnCalendar.setContentAreaFilled(false);
			btnCalendar.addActionListener(new ActionListener() {
				@SuppressWarnings("deprecation")
				public void actionPerformed(ActionEvent e) {

					dispose();
					controller.displayCalendarPage();

				}
			});
			btnCalendar.setBounds(568, 687, 64, 64);
			contentPane.add(btnCalendar);
			contentPane.setComponentZOrder(btnCalendar, 1);
		} catch (IOException btnCalendarErr) {
			System.out.println("Calendar Icon unable to load");
			System.exit(2);
		}

		// Statistics Navigation Button
		JButton btnStatistics;
		try {
			InputStream buttonIconStatisticsStream = getClass().getResourceAsStream("/resources/bar-chart.png");
			BufferedImage buttonIconStatistics = ImageIO.read(buttonIconStatisticsStream);
			btnStatistics = new JButton(new ImageIcon(buttonIconStatistics));
			btnStatistics.setBorder(BorderFactory.createEmptyBorder());
			btnStatistics.setContentAreaFilled(false);
			btnStatistics.addActionListener(new ActionListener() {
				@SuppressWarnings("deprecation")
				public void actionPerformed(ActionEvent e) {

					dispose();
					controller.displayStatisticsPage();

				}
			});
			btnStatistics.setBounds(488, 687, 64, 64);
			contentPane.add(btnStatistics);
			contentPane.setComponentZOrder(btnStatistics, 1);
		} catch (IOException btnStatisticsErr) {
			System.out.println("Statistics Icon unable to load");
			System.exit(2);
		}

		setContentPane(contentPane);
		this.setVisible(true);
	}

	public void staticElements()
	{

		//Label for user preferences
		JLabel lblUserPreferences = new JLabel("User Preferences");
		lblUserPreferences.setFont(new Font("Tahoma", Font.PLAIN, 30));
		lblUserPreferences.setBounds(230, 120, 400, 25);
		contentPane.add(lblUserPreferences);
		contentPane.setComponentZOrder(lblUserPreferences, 1);

		//Label for device preferences
		JLabel lblDevicePreferences = new JLabel("Device Preferences");
		lblDevicePreferences.setFont(new Font("Tahoma", Font.PLAIN, 30));
		lblDevicePreferences.setBounds(860, 120, 400, 25);
		contentPane.add(lblDevicePreferences);
		contentPane.setComponentZOrder(lblDevicePreferences, 1);
		
	}

	public void userPreferences()
	{

		//Label for agent approach
		JLabel lblAgentApproach = new JLabel("Agent Mode");
		lblAgentApproach.setFont(new Font("Tahoma", Font.PLAIN, 24));
		lblAgentApproach.setBounds(70, 170, 400, 40);
		contentPane.add(lblAgentApproach);
		contentPane.setComponentZOrder(lblAgentApproach, 1);

		//Text area to explain agent approach
		JTextArea txtrAgentApproach = new JTextArea();
		txtrAgentApproach.setText("The agent mode determines how your agent will behave, this will force the agent to act in a social or selfish way. \n\n\nSocial: Your agent will cooperate with other agents in the system to find the best outcome for all agents. \n\nSelfish: Your agent will prioritise your timeslot preferences over other agents, this may result in a worse outcome for all agents.");
		txtrAgentApproach.setWrapStyleWord(true);
		txtrAgentApproach.setLineWrap(true);
		txtrAgentApproach.setFont(new Font("Tahoma", Font.PLAIN, 15));
		txtrAgentApproach.setEditable(false);
		txtrAgentApproach.setBounds(70, 210, 400, 200);
		contentPane.add(txtrAgentApproach);
		contentPane.setComponentZOrder(txtrAgentApproach, 1);

		JComboBox<String> comboBoxAgentApproach = new JComboBox<String>();
		comboBoxAgentApproach.setModel(new DefaultComboBoxModel<String>(new String[] {"Social", "Selfish"}));
		comboBoxAgentApproach.setSelectedItem(settings.getAgentMode());
		comboBoxAgentApproach.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				settings.setAgentMode(comboBoxAgentApproach.getSelectedItem().toString());

			}
		});
		comboBoxAgentApproach.setFont(new Font("Tahoma", Font.PLAIN, 20));
		comboBoxAgentApproach.setBounds(65, 230, 200, 80);
		contentPane.add(comboBoxAgentApproach);
		contentPane.setComponentZOrder(comboBoxAgentApproach, 1);
		
	}

	public void devicePreferences()
	{



	}

}