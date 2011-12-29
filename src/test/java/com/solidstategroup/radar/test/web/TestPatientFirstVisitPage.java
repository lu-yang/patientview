package com.solidstategroup.radar.test.web;

import com.solidstategroup.radar.web.pages.PatientPage;
import org.apache.wicket.Component;
import org.junit.Test;

/**
 * Simple test using the WicketTester
 */
public class TestPatientFirstVisitPage extends BasePageTest {

    @Test
    public void renderPatientPage() {
        // Start and render the test page
        tester.startPage(PatientPage.class);

        // Assert rendered page class
        tester.assertRenderedPage(PatientPage.class);

        // Try Ajax refresh
        tester.clickLink("firstVisitLink");

        // View the lab results tab
        Component labResultsLink = tester.getLastRenderedPage().get("firstVisitPanel").get("laboratoryResultsLink");
        tester.clickLink(labResultsLink.getPageRelativePath());

        Component labResultsPanel = tester.getLastRenderedPage().get("firstVisitPanel").get("laboratoryResultsPanel");
        tester.assertVisible(labResultsPanel.getPageRelativePath());

        // View the treatment tab
        Component treatmentLink = tester.getLastRenderedPage().get("firstVisitPanel").get("treatmentLink");
        tester.clickLink(treatmentLink.getPageRelativePath());

        Component treatmentPanel = tester.getLastRenderedPage().get("firstVisitPanel").get("treatmentPanel");
        tester.assertVisible(treatmentPanel.getPageRelativePath());
    }
}
