package main.service.impl;

import java.io.Serializable;

import org.junit.Test;

import main.dao.impl.TestDaoImpl;


public class TestServiceImpl {
	// IOC×¢Èë
		private TestDaoImpl testDaoImpl;
		public void setTestDaoImpl(TestDaoImpl testDaoImpl) {
			this.testDaoImpl = testDaoImpl;
		}

		/**
		 * ²éÑ¯
		 * @param emp
		 */
		@Test
		public void findById() {
			testDaoImpl.findById();
			
		}
}
