package util;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class LogExporter {
	// slf4jとlogbackを使用する宣言
	private Logger logger = LoggerFactory.getLogger("LogExporter");
	 
	public void runLog() {
		logger.trace("test-trace");
        logger.debug("test-debug");
        logger.info("test-info");
        logger.warn("test-warn");
        logger.error("test-error");
	}

}
