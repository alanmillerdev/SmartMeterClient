package SociologyInspiredSmartMeter.SmartMeterClient;

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

public class CalendarPagePreferenceSelection extends JFrame{

    private JPanel contentPane;

	private SociologyInspiredSmartMeter.Controller controller;

	private Config config;

	private Settings settings;

	private String selectedAppliance;

	private String selectedTimeslot;

	//Stores the timeslot preferences input by the user to be sent to the controller.
	//Used in both modes, but in generic mode, the appliance preferences are set to null.
	HashMap<Integer, String> timeslotPreferences = new HashMap<Integer, String>();

	DefaultListModel<String> UITimeslotList = new DefaultListModel<>();

	JComboBox<String> timeslotComboBox;

	public CalendarPagePreferenceSelection(SociologyInspiredSmartMeter.Controller passedController, Config passedConfig, Settings passedSettings) {

		this.controller = passedController;

		this.config = passedConfig;

		this.settings = passedSettings;

		buildFrame();

		staticElements();

		if(settings.getApplicationMode().equals("Appliance"))
		{
			ApplicanceTimeslotSelectionUI();
		} else if(settings.getApplicationMode().equals("Generic"))
		{
			GenericTimeslotSelectionUI();
		}

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
			BufferedImage backgroundImageImage = ImageIO.read(new File("src/SociologyInspiredSmartMeter/SmartMeterClient/Icons/BackgroundTimeslots.png"));
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

		//If the application is in applicance mode, display the applicance timeslot selection UI elements.
		//Else if the application is in generic mode, display the generic timeslot selection UI elements.
		if(settings.getApplicationMode().equals("Appliance"))
		{
			//Static Label for the timeslot selection section.
			JLabel applianceSelectionLabel = new JLabel("Appliance Selection");
			applianceSelectionLabel.setBounds(240, 100, 300, 50);
			applianceSelectionLabel.setFont(new Font("Tahoma", Font.PLAIN, 30));
			contentPane.add(applianceSelectionLabel);
			contentPane.setComponentZOrder(applianceSelectionLabel, 1);

			//Static Label for the timeslot selection section.
			JLabel timeslotSelectionLabel = new JLabel("Timeslot Selection");
			timeslotSelectionLabel.setBounds(240, 350, 300, 50);
			timeslotSelectionLabel.setFont(new Font("Tahoma", Font.PLAIN, 30));
			contentPane.add(timeslotSelectionLabel);
			contentPane.setComponentZOrder(timeslotSelectionLabel, 1);

		}
		else if(settings.getApplicationMode().equals("Generic"))
		{
			//Static Label for the timeslot selection section.
			JLabel timeslotSelectionLabel = new JLabel("Timeslot Selection");
			timeslotSelectionLabel.setBounds(240, 100, 300, 50);
			timeslotSelectionLabel.setFont(new Font("Tahoma", Font.PLAIN, 30));
			contentPane.add(timeslotSelectionLabel);
			contentPane.setComponentZOrder(timeslotSelectionLabel, 1);
		}

		//Static Label for the timeslot preference list.
		JLabel preferenceListLabel = new JLabel("Your Preferences");
		preferenceListLabel.setBounds(920, 100, 300, 50);
		preferenceListLabel.setFont(new Font("Tahoma", Font.PLAIN, 30));
		contentPane.add(preferenceListLabel);
		contentPane.setComponentZOrder(preferenceListLabel, 1);

		//Static Button to add a timeslot to preference list.
		JButton addPreferenceButton = new JButton("Add timeslot to preference list");
		addPreferenceButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				if(settings.getApplicationMode() == "Appliance")
				{
					if(selectedAppliance == null)
					{
						JOptionPane.showMessageDialog(null, "Please select an appliance.");
						return;
					} else if(selectedTimeslot == null || selectedTimeslot.equals("Select a timeslot"))
					{
						JOptionPane.showMessageDialog(null, "Please select a timeslot.");
						return;
					} else if(UITimeslotList.size() == 4)
					{
						JOptionPane.showMessageDialog(null, "You have already selected 4 timeslots. Please submit your timeslot preferences.");
						return;
					} else 
					{
						//Adds the timeslot and the appliance to the timeslot preference list to be used in the algorithm and UI.
						timeslotPreferences.put(Integer.parseInt(selectedTimeslot.substring(0, 2)), selectedAppliance);
						//Creates a string to be displayed in the UI.
						UITimeslotList.addElement(selectedAppliance + " at " + selectedTimeslot);
						timeslotComboBox.removeItem(selectedTimeslot);
						timeslotComboBox.setSelectedIndex(0);
						selectedAppliance = null;
						selectedTimeslot = null;
					}	
				} else if(settings.getApplicationMode() == "Generic")
				{
					if(selectedTimeslot == null || selectedTimeslot.equals("Select a timeslot"))
					{
						JOptionPane.showMessageDialog(null, "Please select a timeslot.");
						return;
					} else if(UITimeslotList.size() == 4)
					{
						JOptionPane.showMessageDialog(null, "You have already selected 4 timeslots. Please submit your timeslot preferences.");
						return;
					} else 
					{
						//Adds the timeslot and the appliance to the timeslot preference list to be used in the algorithm and UI.
						timeslotPreferences.put(Integer.parseInt(selectedTimeslot.substring(0, 2)), selectedAppliance);
						//Creates a string to be displayed in the UI.
						UITimeslotList.addElement("1 kWh available at " + selectedTimeslot);
						timeslotComboBox.removeItem(selectedTimeslot);
						timeslotComboBox.setSelectedIndex(0);
						selectedAppliance = null;
						selectedTimeslot = null;
					}
				}				
			}
		});
		addPreferenceButton.setBounds(220, 600, 300, 50);
		addPreferenceButton.setFont(new Font("Tahoma", Font.PLAIN, 20));
		contentPane.add(addPreferenceButton);
		contentPane.setComponentZOrder(addPreferenceButton, 1);

		//Static Button to submit timeslot preference list.
		JButton submitButton = new JButton("Submit timeslot preferences");
		submitButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(UITimeslotList.size() != 4)
				{
					JOptionPane.showMessageDialog(null, "Please select " + (4 - UITimeslotList.size()) + " more timeslots.");
					return;
				} else
				{
				//If the user has selected 4 timeslots, submit the 4 timeslots to the controller.
				//Dispose of the current page.
				dispose();
				//Call the controller to handle next steps.
				controller.PreferenceSubmissionHandler(timeslotPreferences);
				}
			}
		});
		submitButton.setBounds(870, 600, 300, 50);
		submitButton.setFont(new Font("Tahoma", Font.PLAIN, 20));
		contentPane.add(submitButton);
		contentPane.setComponentZOrder(submitButton, 1);

		//Preferences List
		JList<String> stringList = new JList<>(UITimeslotList);
		stringList.setBounds(720, 180, 600, 420);
		stringList.setFont(new Font("Tahoma", Font.PLAIN, 28));
		contentPane.add(stringList);
		contentPane.setComponentZOrder(stringList, 1);

	}

	public void ApplicanceTimeslotSelectionUI()
	{
		
		ImageIcon washingMachineImage = new ImageIcon("src/SociologyInspiredSmartMeter/SmartMeterClient/Icons/washing-machine.png");
		ImageIcon tumbleDryeerImage = new ImageIcon("src/SociologyInspiredSmartMeter/SmartMeterClient/Icons/tumble-dryer.png");
		ImageIcon dishwasherImage = new ImageIcon("src/SociologyInspiredSmartMeter/SmartMeterClient/Icons/dishwasher.png");
		ImageIcon heaterImage = new ImageIcon("src/SociologyInspiredSmartMeter/SmartMeterClient/Icons/heat-pump.png");

		JToggleButton washingMachineButton = new JToggleButton(washingMachineImage);
		JToggleButton tumbleDryerButton = new JToggleButton(tumbleDryeerImage);
		JToggleButton dishwasherButton = new JToggleButton(dishwasherImage);
		JToggleButton heaterButton = new JToggleButton(heaterImage);

		JLabel washingMachineLabel = new JLabel("Washing Machine");
		JLabel tumbleDryerLabel = new JLabel("Tumble Dryer");
		JLabel dishwasherLabel = new JLabel("Dishwasher");
		JLabel heaterLabel = new JLabel("Heater");

		ButtonGroup group = new ButtonGroup();
		group.add(washingMachineButton);
		group.add(tumbleDryerButton);
		group.add(dishwasherButton);
		group.add(heaterButton);

    washingMachineButton.addActionListener(new ActionListener() {
        public void actionPerformed(ActionEvent e) {
            selectedAppliance = "Washing Machine";
        }
    });

    tumbleDryerButton.addActionListener(new ActionListener() {
        public void actionPerformed(ActionEvent e) {
            selectedAppliance = "Tumble Dryer";
        }
    });

    dishwasherButton.addActionListener(new ActionListener() {
        public void actionPerformed(ActionEvent e) {
            selectedAppliance = "Dishwasher";
        }
    });

    heaterButton.addActionListener(new ActionListener() {
        public void actionPerformed(ActionEvent e) {
            selectedAppliance = "Heater";
        }
    });

	washingMachineButton.setBounds(60, 145, 150, 150);
	washingMachineLabel.setBounds(60, 280, 150, 50);
	washingMachineLabel.setFont(new Font("Tahoma", Font.PLAIN, 16));
	contentPane.add(washingMachineButton);
	contentPane.add(washingMachineLabel);
	contentPane.setComponentZOrder(washingMachineButton, 1);
	contentPane.setComponentZOrder(washingMachineLabel, 1);

	tumbleDryerButton.setBounds(210, 145, 150, 150);
	tumbleDryerLabel.setBounds(210, 280, 150, 50);
	tumbleDryerLabel.setFont(new Font("Tahoma", Font.PLAIN, 16));
	contentPane.add(tumbleDryerButton);
	contentPane.add(tumbleDryerLabel);
	contentPane.setComponentZOrder(tumbleDryerButton, 1);
	contentPane.setComponentZOrder(tumbleDryerLabel, 1);
	
	dishwasherButton.setBounds(360, 145, 150, 150);
	dishwasherLabel.setBounds(360, 280, 150, 50);
	dishwasherLabel.setFont(new Font("Tahoma", Font.PLAIN, 16));
	contentPane.add(dishwasherButton);
	contentPane.add(dishwasherLabel);
	contentPane.setComponentZOrder(dishwasherButton, 1);
	contentPane.setComponentZOrder(dishwasherLabel, 1);

	heaterButton.setBounds(510, 145, 150, 150);
	heaterLabel.setBounds(510, 280, 150, 50);
	heaterLabel.setFont(new Font("Tahoma", Font.PLAIN, 16));
    contentPane.add(heaterButton);
	contentPane.add(heaterLabel);
	contentPane.setComponentZOrder(heaterButton, 1);
	contentPane.setComponentZOrder(heaterLabel, 1);

	String[] timeslots = new String[25];
	timeslots[0] = "Select a timeslot";
    for (int i = 1; i < 24; i++) {
        timeslots[i] = String.format("%02d:00 - %02d:00", i-1, i);
    };
	
	timeslots[24] = "23:00 - 00:00";

    timeslotComboBox = new JComboBox<>(timeslots);
	timeslotComboBox.setBounds(210, 360, 300, 100);
	timeslotComboBox.setFont(new Font("Tahoma", Font.PLAIN, 20));
	contentPane.add(timeslotComboBox);
	contentPane.setComponentZOrder(timeslotComboBox, 1);
	timeslotComboBox.addActionListener(new ActionListener() {
		public void actionPerformed(ActionEvent e) {
			selectedTimeslot = timeslotComboBox.getSelectedItem().toString();
		}
	});

	}
	
	public void GenericTimeslotSelectionUI()
	{
		
	String[] timeslots = new String[25];
	timeslots[0] = "Select a timeslot";
    for (int i = 1; i < 24; i++) {
        timeslots[i] = String.format("%02d:00 - %02d:00", i-1, i);
    };
	
	timeslots[24] = "23:00 - 00:00";

    timeslotComboBox = new JComboBox<>(timeslots);
	timeslotComboBox.setBounds(210, 110, 300, 100);
	timeslotComboBox.setFont(new Font("Tahoma", Font.PLAIN, 20));
	contentPane.add(timeslotComboBox);
	contentPane.setComponentZOrder(timeslotComboBox, 1);
	timeslotComboBox.addActionListener(new ActionListener() {
		public void actionPerformed(ActionEvent e) {
			selectedTimeslot = timeslotComboBox.getSelectedItem().toString();
		}
	});

	}

}