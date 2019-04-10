package cn.roweb.k8sapi.user;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.junit.Test;

import cn.roweb.k8sapi.Tester;
import io.fabric8.kubernetes.client.Config;
import io.fabric8.kubernetes.client.ConfigBuilder;
import io.fabric8.kubernetes.client.DefaultKubernetesClient;
import io.fabric8.kubernetes.client.KubernetesClient;

public class UserControllerTest extends Tester {
	private  final Logger logger = LoggerFactory.getLogger(UserControllerTest.class);
	@Test
	public void userList() {
		logger.info("Test");
		
	}
	@Test
	public void TestK8sClient() {

	}
}
