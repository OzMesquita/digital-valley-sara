package br.com.n2s.sara.model;
import java.util.Map;
//import com.github.shyiko.dotenv.DotEnv;

import com.github.shyiko.dotenv.DotEnv;


public class Constantes {

	
		

			private static String APP_URL;
			private static String APP_GUARDIAO_URL;
			private static String APP_ASSETS_URL;
			private static String APP_JS_URL;
			private static String APP_IMG_URL;
			private static String APP_CSS_URL;
			private static Integer NUMBER_OF_ROWS_PER_PAGE;
			private static String SESSION_MSG;
			private static String DATABASE_CONF_DIR;
			private static String PRE_URL;
			private static String DOCUMENTS_DIR;
			private Constantes() {
				//
			}

			static {
				Map<String, String> dotEnv = DotEnv.load();
				DATABASE_CONF_DIR = dotEnv.get("DATABASE_CONF_DIR");
				APP_URL = dotEnv.get("APP_URL");
				APP_GUARDIAO_URL = dotEnv.get("APP_GUARDIAO_URL");
				APP_ASSETS_URL = dotEnv.get("APP_ASSETS_URL");
				APP_JS_URL = dotEnv.get("APP_JS_URL");
				APP_IMG_URL = dotEnv.get("APP_IMG_URL");
				APP_CSS_URL = dotEnv.get("APP_CSS_URL");
				NUMBER_OF_ROWS_PER_PAGE = Integer.valueOf(dotEnv.get("NUMBER_OF_ROWS_PER_PAGE"));
				SESSION_MSG = dotEnv.get("SESSION_MSG");
				PRE_URL = dotEnv.get("PRE_URL");
				DOCUMENTS_DIR = dotEnv.get("DOCUMENTS_DIR");
			}

			/**
			 * @return the appUrl
			 */
			public static String getAppUrl() {
				return APP_URL;
			}
			
			/**
			 * @return the appUrl
			 */
			public static String getAppGuardiaoUrl() {
				return APP_GUARDIAO_URL;
			}
			
			
			/**
			 * @return the appUrl
			 */
			public static String getPreUrl() {
				return PRE_URL;
			}
			/**
			 * @return the appAssetsUrl
			 */
			public static String getAppAssetsUrl() {
				return APP_ASSETS_URL;
			}

			/**
			 * @return the appJsUrl
			 */
			public static String getAppJsUrl() {
				return APP_JS_URL;
			}

			/**
			 * @return the appImgUrl
			 */
			public static String getAppImgUrl() {
				return APP_IMG_URL;
			}

			/**
			 * @return the appCssUrl
			 */ 
			public static String getAppCssUrl() {
				return APP_CSS_URL;
			}

			/**
			 * @return the numberOfRowsPerPage
			 */
			public static Integer getNumberOfRowsPerPage() {
				return NUMBER_OF_ROWS_PER_PAGE;
			}

			/**
			 * @return the sessionMsg
			 */
			public static String getSessionMsg() {
				return SESSION_MSG;
			}

			/**
			 * @return the databaseConfDir
			 */
			public static String getDatabaseConfDir() {
				return DATABASE_CONF_DIR;
			}
			/**
			 * @return the documentsDir
			 */
			public static String getDocumentsDir() {
				return DOCUMENTS_DIR;
			}
			
}
		// TODO Auto-generated constructor stub



