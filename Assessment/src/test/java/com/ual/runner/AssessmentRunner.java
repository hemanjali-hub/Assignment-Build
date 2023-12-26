package com.ual.runner;




import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import org.testng.annotations.BeforeSuite;
import org.testng.annotations.DataProvider;


import com.ual.helpers.CommonUtils;



import io.cucumber.testng.AbstractTestNGCucumberTests;
import io.cucumber.testng.CucumberOptions;



@CucumberOptions(
		 features="src/test/resources/features/Assessments",		
		 monochrome=true,
		 glue= {"com.ual.steps","com.ual.hooks"},
		 plugin={"pretty","com.aventstack.extentreports.cucumber.adapter.ExtentCucumberAdapter:",
		 "rerun:target/failedrerunAssessments.txt","timeline:test-output-thread/",
		 "json:target/cucumber-reports/Assessments.json",
		 "html:target/cucumber-reports/Assessments.html"
		 },
		 tags= "@assessment"
		// strict=true,
//		 ,publish=true
//		 ,dryRun = true
		 )

//@Listeners(Listener.class)
public class AssessmentRunner extends AbstractTestNGCucumberTests{
	private static Logger log = LogManager.getLogger(AssessmentRunner.class.getName());
	
	
	
	@Override
   @DataProvider(parallel = true)
   public Object[][] scenarios() {
       return super.scenarios();
   }
	
	
	
	@BeforeSuite
	public void initialise() throws Exception {
		log.info("Intialise started");
		CommonUtils.loadProperties();
		
	
	}
	
}


