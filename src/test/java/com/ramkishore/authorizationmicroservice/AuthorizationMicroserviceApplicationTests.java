package com.ramkishore.authorizationmicroservice;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;
import com.ramkishore.authorizationmicroservice.controller.AuthorizationControllerTests;
import com.ramkishore.authorizationmicroservice.model.AuthorizationModelTest;
import com.ramkishore.authorizationmicroservice.service.UserDetailServiceTests;

@RunWith(Suite.class)
@SuiteClasses({ AuthorizationControllerTests.class, AuthorizationModelTest.class, UserDetailServiceTests.class })
public class AuthorizationMicroserviceApplicationTests {

}
