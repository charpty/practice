package com.ifugle.test.ejb;

import gt3.esb.ejb.adapter.client.IEsbXmlMessageReceiver;

import java.util.Properties;

import javax.naming.InitialContext;
import javax.naming.NamingException;

/**
 * @author CaiBo
 * @date 2016年2月26日 下午4:33:17
 * @version $Id$
 * 
 */
public class EJBOneClientOneTheadWithDelayTest {

	public static void main(String[] args) throws NamingException, InterruptedException {
		Properties props = new Properties();
		props.setProperty("java.naming.factory.initial", "weblogic.jndi.WLInitialContextFactory");
		props.setProperty("java.naming.provider.url", "t3://150.12.56.135:8001");
		String xml = "<?xml version=\"1.0\" encoding=\"UTF-8\"?><service xmlns=\"http://www.chinatax.gov.cn/spec/\" xmlns:xsi=\"http://www.w3.org/2001/XMLSchema-instance\">	<head>		<tran_id>SWZJ.GSGL.SB.CXZRRDJXXWBXT</tran_id>		<channel_id>GDDS.ZJNB.GSHD</channel_id>		<tran_seq>eea4ff11269045068f421dd3d654804e</tran_seq>		<tran_date>20120416</tran_date>		<tran_time>083231475</tran_time>		<expand>			<name>identityType</name>			<value>49308fd2f75d462594648203c1ae5e93</value>		</expand>		<expand>			<name>sjry</name>			<value>24406060733</value>		</expand>		<expand>			<name>sjjg</name>			<value>24406820018</value>		</expand>	</head>	<body><![CDATA[<?xml version=\"1.0\" encoding=\"UTF-8\"?><taxML xsi:type=\"zrrxxcxrequest\" xmlbh=\"String\" bbh=\"String\" xmlmc=\"String\" xsi:schemaLocation=\"http://www.chinatax.gov.cn/dataspec/ TaxMLBw_GSGL_SB_001_Request_v1.0.xsd\" xmlns=\"http://www.chinatax.gov.cn/dataspec/\" xmlns:xsi=\"http://www.w3.org/2001/XMLSchema-instance\">	<djxh>20124400001005357570</djxh>	<zjzlDm></zjzlDm>	<zjhm></zjhm>	<xm></xm>	<nsrsbh></nsrsbh></taxML>]]></body></service>";

		InitialContext ctx = new InitialContext(props);
		IEsbXmlMessageReceiver lookup = (IEsbXmlMessageReceiver) ctx.lookup("gt3.esb.ejb.AipEJBAdapterXml");

		int sleepArray[] = { 10, 60, 70, 130, 310 };
		int sleepTime = 0;
		for (int i = 0; i < sleepArray.length + 1; i++) {
			testMultiTimes(xml, lookup, sleepTime);
			System.out.println("-------------------------------------");
			if (i == sleepArray.length) {
				break;
			}
			sleepTime = sleepArray[i];
			Thread.sleep(sleepTime * 1000);
		}
	}

	private static void testMultiTimes(String xml, IEsbXmlMessageReceiver lookup, int sleepTime) {
		long t1 = System.nanoTime();
		testOnce(xml, lookup);
		long t2 = System.nanoTime();
		System.out.println("sleepTime:" + sleepTime + " time:" + (t2 - t1));
	}

	private static void testOnce(String xml, IEsbXmlMessageReceiver lookup) {
		lookup.receiveMessageXML(xml);
	}

}
