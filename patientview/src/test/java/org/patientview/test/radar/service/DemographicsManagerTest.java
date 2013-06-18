package org.patientview.test.radar.service;


import org.patientview.test.radar.TestPvDbSchema;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.patientview.radar.service.DemographicsManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;

@RunWith(org.springframework.test.context.junit4.SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:context.xml"})
public class DemographicsManagerTest extends TestPvDbSchema {
    @Autowired
    private DemographicsManager demographicsManager;

    @Test
    public void testIsNhsNumberValid() throws Exception {
        String validCode = "4010232137";
        Assert.assertTrue(demographicsManager.isNhsNumberValid(validCode));
    }
}
