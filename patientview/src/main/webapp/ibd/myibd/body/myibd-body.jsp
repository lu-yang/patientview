<%@ taglib uri="http://struts.apache.org/tags-html" prefix="html" %>
<%@ taglib uri="http://struts.apache.org/tags-bean" prefix="bean" %>
<%@ taglib uri="http://struts.apache.org/tags-logic" prefix="logic" %>
<div class="row">
    <div class="span12">
        <div class="page-header">
            <h1>My IBD</h1>
        </div>
        <p>
            Use this page to enter what medicines you are currently taking for you IBD. Any other medications you are taking please fill in the box below labelled other medications with the doses.
        </p>
    </div>
</div>
<logic:present name="myIbd">
    <div class="row">
        <div class="span6">
            <div class="row control-group">
                <div class="span3">
                    <label class="control-label">Primary Diagnosis:</label>
                </div>
                <div class="span3 controls">
                    <bean:write name="myIbd" property="diagnosis.name"/>
                </div>
            </div>
            <div class="row control-group">
                <div class="span3">
                    <label class="control-label">Disease Extent:</label>
                </div>
                <div class="span3 controls">
                    <bean:write name="myIbd" property="diseaseExtent.name"/>
                </div>
            </div>
            <div class="row control-group">
                <div class="span3">
                    <label class="control-label">Complications:</label>
                </div>
                <div class="span3 controls">
                    <bean:size id="complicationsListSize" name="myIbd" property="complications"/>
                    <logic:iterate name="myIbd" property="complications" id="complication" indexId="index">
                        <bean:write name="complication"
                                    property="name"/><%= (complicationsListSize == (index + 1) ? "" : ",") %>
                    </logic:iterate>
                </div>
            </div>
        </div>
        <div class="span6">
            <logic:present name="myIbd" property="diagnosis">
                <div class="medicalDiagram">
                    <img src="ibd/img/content/<bean:write name="myIbd" property="diseaseExtent.diagram"/>" alt="<bean:write name="myIbd" property="diseaseExtent.name"/>"/>
                </div>
            </logic:present>
        </div>
    </div>
    <div class="row">
        <div class="span3">
            Current Medications:
        </div>
        <div class="span9">
            <logic:present name="currentMedications">
                <table class="table table-bordered table-striped">
                    <thead>
                    <tr>
                        <th>Date Started</th>
                        <th>Type</th>
                        <th>Medication</th>
                        <th>Dose</th>
                        <th>No Tabs/Granules</th>
                        <th>Frequency</th>
                    </tr>
                    </thead>
                    <tbody>
                    <logic:iterate name="currentMedications" id="myMedication" indexId="index">
                        <tr>
                            <td>
                                <bean:write name="myMedication" property="dateStartedAsString" />
                            </td>
                            <td>
                                <bean:write name="myMedication" property="medicationType.name" />
                            </td>
                            <td>
                                <logic:present name="myMedication" property="medication">
                                    <bean:write name="myMedication" property="medication.name" />
                                </logic:present>
                                <logic:present name="myMedication" property="otherMedication">
                                    <bean:write name="myMedication" property="otherMedication" />
                                </logic:present>
                            </td>
                            <td>
                                <logic:present name="myMedication" property="medicationDose">
                                    <bean:write name="myMedication" property="medicationDose.formattedValue" />
                                </logic:present>
                            </td>
                            <td>
                                <bean:write name="myMedication" property="medicationNoOf.name" />
                            </td>
                            <td>
                                <bean:write name="myMedication" property="medicationFrequency.name" />
                            </td>
                        </tr>
                    </logic:iterate>
                    </tbody>
                </table>
            </logic:present>
            <logic:notPresent name="currentMedications">
                <p>No current medicines</p>
            </logic:notPresent>
        </div>
    </div>
    <div class="row">
        <div class="span6">
            <div class="row control-group">
                <div class="span3">
                    <label class="control-label">Other parts of the body affected:</label>
                </div>
                <div class="span3 controls">
                    <bean:write name="myIbd" property="bodyPartAffected.name"/>
                </div>
            </div>
        </div>
    </div>
    <div class="row">
        <div class="span6">
            <div class="row control-group">
                <div class="span3">
                    <label class="control-label">General:</label>
                </div>
                <div class="span3 controls">
                    <p>Weight: <bean:write name="myIbd" property="weight"/></p>

                    <p>IBD Related Family History: <bean:write name="myIbd" property="familyHistory.name"/></p>

                    <p>Smoking History: <bean:write name="myIbd" property="smoking.name"/></p>

                    <p>Surgical History: <bean:write name="myIbd" property="surgery.name"/></p>

                    <p>Vaccination Record: <bean:write name="myIbd" property="vaccinationRecord.name"/></p>
                </div>
            </div>
        </div>
    </div>
    <div class="row">
        <div class="span12">
            <html:link action="/myibd-edit" styleClass="btn">Edit</html:link>
        </div>
    </div>
</logic:present>
<logic:notPresent name="myIbd">
    <div class="row">
        <div class="span12">
            <html:link action="/myibd-edit" styleClass="btn">Add</html:link>
        </div>
    </div>
</logic:notPresent>
