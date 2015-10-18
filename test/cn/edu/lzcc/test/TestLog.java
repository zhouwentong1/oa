package cn.edu.lzcc.test;


import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.junit.Test;

public class TestLog {
	@Test
	public void testLog() throws Exception {
		Log log = LogFactory.getLog(this.getClass());
		log.debug("debug级别");
		log.info("info级别");
		log.warn("warn级别");
		log.error("error级别");
		log.fatal("fatal级别");
	}
}
