package util;

import java.util.Map;

import com.github.shyiko.dotenv.DotEnv;

public class Constantes {
	
	private static String EMAIL_CONF_DIR;
	private static String DATABASE_CONF_DIR;
	private static String ARTICLES_DIR;
	
	private Constantes() {
		//não deve ter nada aqui
	}

	 static {
			Map<String, String> dotEnv = DotEnv.load();
			DATABASE_CONF_DIR = dotEnv.get("DATABASE_CONF_DIR");
			EMAIL_CONF_DIR = dotEnv.get("EMAIL_CONF_DIR");
			ARTICLES_DIR = dotEnv.get("ARTICLE_SARA_DIR");
	}
	
	 public static String getEmailConfDir() {
		return EMAIL_CONF_DIR;
	}
	 public static String getDatabaseConfDir() {
		return DATABASE_CONF_DIR;
	}
	 public static String getArticlesDir() {
		return ARTICLES_DIR;
	}
	 
	 
}


