package main.action;

import java.util.Map;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;

import main.dao.impl.*;
import main.service.impl.*;

public class TestAction extends ActionSupport{
	// IOC����ע��
		private TestServiceImpl testServiceImpl;
		public void setTestServiceImpl(TestServiceImpl testServiceImpl) {
			this.testServiceImpl = testServiceImpl;
		}
		@Override
		public String execute(){
			System.out.println("����action");
			
			// ����Service
			testServiceImpl.findById();
			// ���浽request
			
			
			return "success";
		}
}
