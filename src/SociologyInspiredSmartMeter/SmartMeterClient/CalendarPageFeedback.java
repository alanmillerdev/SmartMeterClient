package SociologyInspiredSmartMeter.SmartMeterClient;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;

import javax.imageio.ImageIO;
import javax.swing.BorderFactory;
import javax.swing.ButtonGroup;
import javax.swing.DefaultListModel;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JToggleButton;
import javax.swing.SwingConstants;
import javax.swing.Timer;
import javax.swing.UIManager;

import SociologyInspiredSmartMeter.Controller;

public class CalendarPageFeedback extends JFrame{

    private JPanel contentPane;

	private SociologyInspiredSmartMeter.Controller controller;

	private Config config;

	private Settings settings;

    public CalendarPageFeedback(Controller passedController, Config passedConfig, Settings passedSettings) {

        this.controller = passedController;

		this.config = passedConfig;

		this.settings = passedSettings;

		buildFrame();

		staticElements();

		feedbackElements();

        setContentPane(contentPane);

		this.setVisible(true);

    }

    public void buildFrame() {

		setTitle(config.getFrameTitle());

		setResizable(false);

		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		setBounds(config.getViewXPos(), config.getViewYPos(), config.getViewWidth(), config.getViewHeight());

		contentPane = new JPanel();

		contentPane.setBorder(config.getBorder());

		contentPane.setBackground(config.getBackgroundColour());

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
			BufferedImage backgroundImageImage = ImageIO.read(new File("src/SociologyInspiredSmartMeter/SmartMeterClient/Icons/Background.png"));
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
			BufferedImage batteryIconImage = ImageIO.read(new File("src/SociologyInspiredSmartMeter/SmartMeterClient/Icons/battery.png"));
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
			BufferedImage signalIconImage = ImageIO.read(new File("src/SociologyInspiredSmartMeter/SmartMeterClient/Icons/signal.png"));
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
			BufferedImage buttonIconHome = ImageIO.read(new File("src/SociologyInspiredSmartMeter/SmartMeterClient/Icons/home.png"));
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
			BufferedImage buttonIconSettings = ImageIO.read(new File("src/SociologyInspiredSmartMeter/SmartMeterClient/Icons/settings.png"));
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
			BufferedImage buttonIconRefresh = ImageIO.read(new File("src/SociologyInspiredSmartMeter/SmartMeterClient/Icons/refresh.png"));
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
			BufferedImage buttonIconCalendar = ImageIO.read(new File("src/SociologyInspiredSmartMeter/SmartMeterClient/Icons/calendar.png"));
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
			BufferedImage buttonIconStatistics = ImageIO.read(new File("src/SociologyInspiredSmartMeter/SmartMeterClient/Icons/bar-chart.png"));
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
	}

	public void staticElements()
	{

        //Static Label for the preference timeslot list.
		JLabel feedbackHeaderLabel = new JLabel("Provide Feedback");
		feedbackHeaderLabel.setBounds(600, 100, 500, 50);
		feedbackHeaderLabel.setFont(new Font("Tahoma", Font.PLAIN, 30));
		contentPane.add(feedbackHeaderLabel);
		contentPane.setComponentZOrder(feedbackHeaderLabel, 1);

         //Static Label for the preference timeslot list.
		JLabel feedbackInstructionLabel = new JLabel("Please provide feedback for the metrics below. Rating them either, positive, neutral or negative using the respective thumb buttons.");
		feedbackInstructionLabel.setBounds(100, 150, 1200, 50);
		feedbackInstructionLabel.setFont(new Font("Tahoma", Font.PLAIN, 20));
		contentPane.add(feedbackInstructionLabel);
		contentPane.setComponentZOrder(feedbackInstructionLabel, 1);

        //JButton to navigate to the feedback page.
        JButton btnFeedback = new JButton("Submit Feedback");
        btnFeedback.addActionListener(new ActionListener() {
            @SuppressWarnings("deprecation")
            public void actionPerformed(ActionEvent e) {

                controller.status = "Submitted";
                dispose();
                controller.displayAssignedTimelinePage();
				
            }
        });
        btnFeedback.setBounds(500, 600, 400, 50);
        btnFeedback.setFont(new Font("Tahoma", Font.PLAIN, 30));
        contentPane.add(btnFeedback);
        contentPane.setComponentZOrder(btnFeedback, 1);



    }

	public void feedbackElements()
	{

		ImageIcon thumbUp = new ImageIcon("src/SociologyInspiredSmartMeter/SmartMeterClient/Icons/thumbUp.png");
		ImageIcon thumbSide = new ImageIcon("src/SociologyInspiredSmartMeter/SmartMeterClient/Icons/thumbSide.png");
		ImageIcon thumbDown = new ImageIcon("src/SociologyInspiredSmartMeter/SmartMeterClient/Icons/thumbDown.png");
		ImageIcon metricOneIcon = new ImageIcon("src/SociologyInspiredSmartMeter/SmartMeterClient/Icons/washingMachineMedium.png");
		ImageIcon metricTwoIcon = new ImageIcon("src/SociologyInspiredSmartMeter/SmartMeterClient/Icons/tumbleDryerMedium.png");
		ImageIcon metricThreeIcon = new ImageIcon("src/SociologyInspiredSmartMeter/SmartMeterClient/Icons/dishwasherMedium.png");

		//Metric 1
		//Metric Icon
		JLabel metric1Icon = new JLabel(metricOneIcon);
		metric1Icon.setBounds(460, 220, 64, 64);
		contentPane.add(metric1Icon);
		contentPane.setComponentZOrder(metric1Icon, 1);
		
		JLabel metric1Label = new JLabel("Metric One");
		metric1Label.setBounds(420, 290, 200, 50);
		metric1Label.setFont(new Font("Tahoma", Font.PLAIN, 24));
		contentPane.add(metric1Label);
		contentPane.setComponentZOrder(metric1Label, 1);

		ButtonGroup metric1Group = new ButtonGroup();
        JToggleButton metric1ThumbsUp = new JToggleButton(thumbUp);
        JToggleButton metric1ThumbsSide = new JToggleButton(thumbSide);
        JToggleButton metric1ThumbsDown = new JToggleButton(thumbDown);
        metric1Group.add(metric1ThumbsUp);
        metric1Group.add(metric1ThumbsSide);
        metric1Group.add(metric1ThumbsDown);

		metric1ThumbsUp.setBounds(460, 350, 64, 64);
		metric1ThumbsSide.setBounds(460, 430, 64, 64);
		metric1ThumbsDown.setBounds(460, 510, 64, 64);
		contentPane.add(metric1ThumbsUp);
		contentPane.add(metric1ThumbsSide);
		contentPane.add(metric1ThumbsDown);
		contentPane.setComponentZOrder(metric1ThumbsUp, 1);
		contentPane.setComponentZOrder(metric1ThumbsSide, 1);
		contentPane.setComponentZOrder(metric1ThumbsDown, 1);

		// Metric 2
		// Metric Icon
		JLabel metric2Icon = new JLabel(metricTwoIcon);
		metric2Icon.setBounds(660, 220, 64, 64);
		contentPane.add(metric2Icon);
		contentPane.setComponentZOrder(metric2Icon, 1);

		JLabel metric2Label = new JLabel("Metric Two");
		metric2Label.setBounds(620, 290, 200, 50);
		metric2Label.setFont(new Font("Tahoma", Font.PLAIN, 24));
		contentPane.add(metric2Label);
		contentPane.setComponentZOrder(metric2Label, 1);

		ButtonGroup metric2Group = new ButtonGroup();
		JToggleButton metric2ThumbsUp = new JToggleButton(thumbUp);
		JToggleButton metric2ThumbsSide = new JToggleButton(thumbSide);
		JToggleButton metric2ThumbsDown = new JToggleButton(thumbDown);
		metric2Group.add(metric2ThumbsUp);
		metric2Group.add(metric2ThumbsSide);
		metric2Group.add(metric2ThumbsDown);
		
		

		metric2ThumbsUp.setBounds(660, 350, 64, 64);
		metric2ThumbsSide.setBounds(660, 430, 64, 64);
		metric2ThumbsDown.setBounds(660, 510, 64, 64);
		contentPane.add(metric2ThumbsUp);
		contentPane.add(metric2ThumbsSide);
		contentPane.add(metric2ThumbsDown);
		contentPane.setComponentZOrder(metric2ThumbsUp, 1);
		contentPane.setComponentZOrder(metric2ThumbsSide, 1);
		contentPane.setComponentZOrder(metric2ThumbsDown, 1);

		// Metric 3
		// Metric Icon
		JLabel metric3Icon = new JLabel(metricThreeIcon);
		metric3Icon.setBounds(860, 220, 64, 64);
		contentPane.add(metric3Icon);
		contentPane.setComponentZOrder(metric3Icon, 1);

		JLabel metric3Label = new JLabel("Metric Three");
		metric3Label.setBounds(820, 290, 200, 50);
		metric3Label.setFont(new Font("Tahoma", Font.PLAIN, 24));
		contentPane.add(metric3Label);
		contentPane.setComponentZOrder(metric3Label, 1);

		ButtonGroup metric3Group = new ButtonGroup();
		JToggleButton metric3ThumbsUp = new JToggleButton(thumbUp);
		JToggleButton metric3ThumbsSide = new JToggleButton(thumbSide);
		JToggleButton metric3ThumbsDown = new JToggleButton(thumbDown);
		metric3Group.add(metric3ThumbsUp);
		metric3Group.add(metric3ThumbsSide);
		metric3Group.add(metric3ThumbsDown);

		metric3ThumbsUp.setBounds(860, 350, 64, 64);
		metric3ThumbsSide.setBounds(860, 430, 64, 64);
		metric3ThumbsDown.setBounds(860, 510, 64, 64);
		contentPane.add(metric3ThumbsUp);
		contentPane.add(metric3ThumbsSide);
		contentPane.add(metric3ThumbsDown);
		contentPane.setComponentZOrder(metric3ThumbsUp, 1);
		contentPane.setComponentZOrder(metric3ThumbsSide, 1);
		contentPane.setComponentZOrder(metric3ThumbsDown, 1);
	}

}