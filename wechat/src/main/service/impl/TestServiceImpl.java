package main.service.impl;

import java.io.Serializable;

import org.junit.Test;

import main.dao.impl.TestDaoImpl;


public class TestServiceImpl {
	// IOCע��
		private TestDaoImpl testDaoImpl;
		public void setTestDaoImpl(TestDaoImpl testDaoImpl) {
			this.testDaoImpl = testDaoImpl;
		}

		/**
		 * ��ѯ
		 * @param emp
		 */
		@Test
		public void findById() {
			testDaoImpl.findById();
			
		}
}
