package com.byw.platfrom.log.test;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Assert;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import com.byw.platform.log.utils.PlatformLogger;

public class PlatformLoggerTest {
	PlatformLogger logger = null;

	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
	}

	@AfterClass
	public static void tearDownAfterClass() throws Exception {
	}

	@Before
	public void setUp() throws Exception {
		logger = PlatformLogger.getLogger(PlatformLoggerTest.class);
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void testLoggingOutput() {
		Assert.assertNotNull(logger);
		logger.info("logging info.");
		logger.debug("logging debug");
		logger.warn("logging warn");
		logger.exception(new Exception("logging exception"));
		logger.exception("Test Exception", new Exception("logging exception"));
		logger.info("Test logging info message format : one {} ,two {} ,three {}", "1", "2", "3");
		logger.error("Test logging errors message format : one {} ,two {} ,three {}", "1", "2", "3");
		logger.debug("Test logging debugs message format : one {} ,two {} ,three {}", "1", "2", "3");
		logger.warn("Test logging warns message format : one {} ,two {} ,three {}", "1", "2", "3");
		logger.info("pingSample", 64, "192.168.16.1", 1, 255, 0.683);
		logger.info("pingSample11111", 64, "192.168.16.1", 1, 255, 0.683);
	}

	@Test
	public void testLogging() {
		for (int i = 0; i < 1000; i++) {
			new Thread() {
				public void run() {
					while (true) {
						try {
							this.sleep(1000L);
						} catch (InterruptedException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
						logger.info("logging info.");
						logger.debug("logging debug");
						logger.warn("logging warn");
						logger.exception(new Exception("logging exception"));
						logger.exception("Test Exception", new Exception("logging exception"));
						logger.info("Test logging info message format : one {} ,two {} ,three {}", "1", "2", "3");
						logger.error("Test logging errors message format : one {} ,two {} ,three {}", "1", "2", "3");
						logger.debug("Test logging debugs message format : one {} ,two {} ,three {}", "1", "2", "3");
						logger.warn("Test logging warns message format : one {} ,two {} ,three {}", "1", "2", "3");
						logger.info("pingSample", 64, "192.168.16.1", 1, 255, 0.683);
						logger.info("pingSample11111", 64, "192.168.16.1", 1, 255, 0.683);
					}
				}
			}.start();
		}
	}

	@Test
	public void testMessageFormat() {
		String str1 = "64 bytes from 192.168.16.1: icmp_req=1 ttl=255 time=0.683 ms";
		logger.info(str1);
		// pingSample={0} bytes from {1}: icmp_req={2} ttl={3} time={4} ms
		String message1 = logger.MessageFormat("pingSample", 64, "192.168.16.1", 1, 255, 0.683);
		logger.info(message1);
		Assert.assertEquals(str1, message1);
		String message2 = logger.MessageFormat("{0} bytes from {1}: icmp_req={2} ttl={3} time={4} ms", 64, "192.168.16.1", 1, 255, 0.683);
		logger.info(message2);
		Assert.assertEquals(str1, message2);
		String message3 = logger.MessageFormat("pingSample_@##$", 64, "192.168.16.1", 1, 255, 0.683);
		logger.info(message3);
		Assert.assertFalse(str1.equals(message3));
	}
}
