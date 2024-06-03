package com.qa.opencart.listener;

import org.testng.IRetryAnalyzer;
import org.testng.ITestResult;

public class Retry implements IRetryAnalyzer {
	int count = 0;
	int maxtry = 3;

	@Override
	public boolean retry(ITestResult result) {
		if (!result.isSuccess()) {

			if (count > maxtry) {
				count++;
				result.setStatus(ITestResult.FAILURE);
				return true;
			} else {
				result.setStatus(ITestResult.FAILURE);
			}
		} else {
			result.setStatus(ITestResult.SUCCESS);
		}

		return false;
	}

}
