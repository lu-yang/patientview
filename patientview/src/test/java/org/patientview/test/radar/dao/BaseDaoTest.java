package org.patientview.test.radar.dao;

import org.patientview.test.radar.TestPvDbSchema;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;

@RunWith(org.springframework.test.context.junit4.SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:context.xml"})
public abstract class BaseDaoTest extends TestPvDbSchema {

}
