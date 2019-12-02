package com.chicolira.clientservice;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;

import com.chicolira.clientservice.controller.ClientControllerIntegrationTest;
import com.chicolira.clientservice.repository.ClientRepositoryIntegrationTest;
import com.chicolira.clientservice.service.impl.ClientServiceImplIntegrationTest;

@RunWith(Suite.class)
@Suite.SuiteClasses({
	ClientRepositoryIntegrationTest.class,
	ClientServiceImplIntegrationTest.class,
	ClientControllerIntegrationTest.class
})
public class ClientServiceAppTests {

}
