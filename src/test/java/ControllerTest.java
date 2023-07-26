package test.java;

import static org.junit.jupiter.api.Assertions.*;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;

import org.junit.jupiter.api.Test;

import SociologyInspiredSmartMeter.Controller;
import SociologyInspiredSmartMeter.SmartMeterClient.Config;
import SociologyInspiredSmartMeter.SmartMeterClient.ExchangeMessage;
import SociologyInspiredSmartMeter.SmartMeterClient.Settings;

class ControllerTest {

	@Test
    public void testSaveSettings() {
        Controller controller = new Controller();

        Settings settings = new Settings();
        settings.setAgentMode("Social");
        settings.setApplicationMode("Appliance");
        
        controller.settings = settings;
        
        controller.saveSettings();

        File file = new File("settings.ser");
        assertTrue(file.exists());

        try {
            FileInputStream fileIn = new FileInputStream("settings.ser");
            ObjectInputStream in = new ObjectInputStream(fileIn);
            Settings savedSettings = (Settings) in.readObject();
            in.close();
            fileIn.close();
            assertEquals(settings.getAgentMode(), savedSettings.getAgentMode());
            assertEquals(settings.getApplicationMode(), savedSettings.getApplicationMode());
        } catch (IOException i) {
            i.printStackTrace();
            fail("IOException thrown");
        } catch (ClassNotFoundException c) {
            c.printStackTrace();
            fail("ClassNotFoundException thrown");
        }
        
        file.delete();
    }
	
	@Test
    public void testLoadSettings() {
        Controller controller = new Controller();

        Settings settings = new Settings();
        settings.setAgentMode("Selfish");
        settings.setApplicationMode("Generic");

        try {
            FileOutputStream fileOut = new FileOutputStream("settings.ser");
            ObjectOutputStream out = new ObjectOutputStream(fileOut);
            out.writeObject(settings);
            out.close();
            fileOut.close();
        } catch (IOException i) {
            i.printStackTrace();
            fail("IOException thrown");
        }

        controller.loadSettings();
     
        assertEquals(settings.getAgentMode(), controller.settings.getAgentMode());
        assertEquals(settings.getApplicationMode(), controller.settings.getApplicationMode());

        File file = new File("settings.ser");
        file.delete();
    }
	
	@Test
    public void testLoadSettingsFileNotFound() {
        Controller controller = new Controller();

        controller.loadSettings();

        assertNull(controller.settings.getAgentMode());
        assertNull(controller.settings.getApplicationMode());
    }

    @Test
    public void testLoadSettingsInvalidFile() {
        Controller controller = new Controller();

        try {
            FileOutputStream fileOut = new FileOutputStream("settings.ser");
            ObjectOutputStream out = new ObjectOutputStream(fileOut);
            out.writeObject("invalid data");
            out.close();
            fileOut.close();
        } catch (IOException i) {
            i.printStackTrace();
            fail("IOException thrown");
        }

        controller.loadSettings();

        assertNull(controller.settings.getAgentMode());
        assertNull(controller.settings.getApplicationMode());

        File file = new File("settings.ser");
        file.delete();
    }
      
}
