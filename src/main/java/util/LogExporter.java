package util;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class LogExporter {
	// slf4jとlogbackを使用する宣言
	private Logger logger = LoggerFactory.getLogger("LogExporter");
	 
	public void runLog(String message) {
		logger.trace(message);
        logger.debug(message);
        logger.info(message);
        logger.warn(message);
        logger.error(message);
	}

}
