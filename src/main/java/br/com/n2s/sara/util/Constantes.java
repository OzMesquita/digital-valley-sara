package br.com.n2s.sara.util;

import java.util.Map;

import com.github.shyiko.dotenv.DotEnv;

public class Constantes {

	private static String EMAIL_CONF_DIR;
	private static String DATABASE_CONF_DIR;
	private static String ARTICLES_DIR;
	private static String APP_GUARDIAO_URL;
	private static String LOGO_UFC;
	private static String TEMP_DIR;
	private static String SESSION;
	private static String SESSION_ERROR;
	private static Integer NUMBER_OF_ROWS_PER_PAGE;
	private static String ADM_URL;
	private static String APP_URL;
	private static String APP_ASSETS_URL;
	private static String APP_JS_URL;
	private static String APP_IMG_URL;
	private static String APP_CSS_URL;
	private Constantes() {
		//não deve ter nada aqui
	}

	static {
		Map<String, String> dotEnv = DotEnv.load();
		DATABASE_CONF_DIR = dotEnv.get("DATABASE_CONF_DIR");
		EMAIL_CONF_DIR = dotEnv.get("EMAIL_CONF_DIR");
		ARTICLES_DIR = dotEnv.get("ARTICLE_SARA_DIR");
		APP_GUARDIAO_URL = dotEnv.get("APP_GUARDIAO_URL");
		LOGO_UFC = dotEnv.get("LOGO_UFC");
		TEMP_DIR = dotEnv.get("TEMP_DIR");
		SESSION = dotEnv.get("SESSION_MSG");
		SESSION_ERROR =dotEnv.get("SESSION_MSG_ERROR");
		NUMBER_OF_ROWS_PER_PAGE = Integer.valueOf(dotEnv.get("NUMBER_OF_ROWS_PER_PAGE"));
		ADM_URL = dotEnv.get("ADM_URL");
		APP_URL = dotEnv.get("APP_URL");
		APP_ASSETS_URL = dotEnv.get("APP_ASSETS_URL");
		APP_JS_URL = dotEnv.get("APP_JS_URL");
		APP_IMG_URL = dotEnv.get("APP_IMG_URL");
		APP_CSS_URL = dotEnv.get("APP_CSS_URL");
	}
	
	public static String getASSETS_URL() {
		return APP_ASSETS_URL;
	}
	
	public static String getCSS_URL() {
		return APP_CSS_URL;
	}
	
	public static String getJS_URL() {
		return APP_JS_URL;
	}
	
	public static String getIMG_URL() {
		return APP_IMG_URL;
	}

	public static String getEMAIL_CONF_DIR() {
		return EMAIL_CONF_DIR;
	}
	public static String getDATABASE_CONF_DIR() {
		return DATABASE_CONF_DIR;
	}
	public static String getARTICLES_DIR() {
		return ARTICLES_DIR;
	}

	public static String getAppGuardiaoUrl(){
		return APP_GUARDIAO_URL;
	}
	public static String getLOGO_UFC() {
		return LOGO_UFC;
	}
	public static String getTEMP_DIR() {
		return TEMP_DIR;
	}
	public static String getSESSION_MGS() {
		return SESSION;
	}
	public static String getSESSION_MGS_ERROR() {
		return SESSION_ERROR;
	}
	public static Integer getNUMBER_OF_ROWS_PER_PAGE() {
		return NUMBER_OF_ROWS_PER_PAGE;
	}
	public static String getAdmUrl() {
		return ADM_URL;
	}
	public static String getAppUrl() {
		return APP_URL;
	}
}


