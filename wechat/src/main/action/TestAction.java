package main.action;

import java.util.Map;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;

import main.dao.impl.*;
import main.service.impl.*;

public class TestAction extends ActionSupport{
	// IOC容器注入
		private TestServiceImpl testServiceImpl;
		public void setTestServiceImpl(TestServiceImpl testServiceImpl) {
			this.testServiceImpl = testServiceImpl;
		}
		@Override
		public String execute(){
			System.out.println("访问action");
			
			// 调用Service
			testServiceImpl.findById();
			// 保存到request
			
			
			return "success";
		}
}
