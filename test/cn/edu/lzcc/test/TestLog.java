package cn.edu.lzcc.test;


import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.junit.Test;

public class TestLog {
	@Test
	public void testLog() throws Exception {
		Log log = LogFactory.getLog(this.getClass());
		log.debug("debug����");
		log.info("info����");
		log.warn("warn����");
		log.error("error����");
		log.fatal("fatal����");
	}
}
