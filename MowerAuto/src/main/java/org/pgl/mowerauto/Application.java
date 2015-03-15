package org.pgl.mowerauto;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.Locale;
import java.util.Properties;
import java.util.ResourceBundle;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.pgl.mowerauto.business.MowerManager;
import org.pgl.mowerauto.business.MowerManagerImpl;
import org.pgl.mowerauto.dao.DataSource;
import org.pgl.mowerauto.dao.DataSourceFile;
import org.pgl.mowerauto.ui.UiConsole;
import org.pgl.mowerauto.ui.UiManager;
import org.pgl.mowerauto.util.exception.FunctionnalException;

/**
 * The entry point of application.
 * */
public class Application {

	private static Logger logger = LogManager.getLogger(Application.class);

	public static ResourceBundle bundle;

	public static void main(String[] args) {
		new Application(args);
	}

	public Application(String[] args){
		init();
		launch(args);
	}

	/**
	 * Initialize application.
	 * */
	private void init(){
		initConfig();
		initConsole();
		logger.info("MowerAuto initialized");
	}

	/**
	 * Initialize configuration and bundle.
	 * */
	private void initConfig(){
		Properties config = new Properties();
		InputStream is = getClass().getResourceAsStream("/config.properties");
		try {
			config.load(is);
		} catch (IOException e) {
			e.printStackTrace();
		}

		Locale locale;
		String localeCode = config.getProperty("LOCALE");

		if(localeCode != null){
			locale = new Locale(localeCode);
		}else{
			locale = Locale.getDefault();
			Locale.setDefault(locale);
		}

		bundle = ResourceBundle.getBundle("ResourceBundle", locale);
	}

	/**
	 * Initialize user console.
	 * */
	private void initConsole(){
		new UiManager(UiConsole.getInstance());
	}

	/**
	 * Launch the application.
	 * */
	private void launch(String[] args){
		MowerManager mowerManager = new MowerManagerImpl();

		//If none argument entered, a message is displayed.
		if(args.length > 0){
			String path = args[0];
			try {
				File file = new File(path);
				DataSource source = new DataSourceFile(file);
				mowerManager.loadOperation(source);
				mowerManager.executeOperation();
			} catch (FunctionnalException fe) {
				logger.error(fe);
				UiManager.display(fe.getMessage());
			}
		}else {
			UiManager.display(bundle.getString("MSG_NO_PATH"));
		}

		String str = UiManager.displayAndWait(bundle.getString("MSG_ENTER_PATH"));
		launch(new String[]{str});
	}

}
