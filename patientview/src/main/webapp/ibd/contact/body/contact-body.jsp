<%@ taglib uri="http://struts.apache.org/tags-html" prefix="html" %>
<%@ taglib uri="http://struts.apache.org/tags-bean" prefix="bean" %>
<%@ taglib uri="http://struts.apache.org/tags-logic" prefix="logic" %>
<%@ taglib uri="http://struts.apache.org/tags-nested" prefix="nested" %>

<html:xhtml/>

<div class="page-header">
    <h1>Contact Details</h1>
</div>

<logic:notPresent name="emailSent">
    <h2>Questions about your health</h2>
    <ul class="padded-list">
        <li>
            If you have any questions about your condition you can contact us by email below.
            Alternatively you can leave a message through the IBD helpline on 0161 20 64023.
        </li>
        <li>
            We aim to review and respond to messages by the end of the next working day.
            In case of emergencies and you haven't received a response, please contact your GP or attend you
            local A &amp; E department in cases of emergency.
        </li>
        <li>
            <strong>Note:</strong> Your name and NHS number will be sent with this message.
        </li>
        <li>
            Please enter your message here with your preferred contact email or phone number.
        </li>
    </ul>

    <html:form action="/patient/ibd-contact-send">
        <fieldset>
            <logic:present name="healthFormError">
                <div class="alert alert-error">
                    <p>To continue you must meet the following criteria</p>
                    <ul>
                        <li>Please enter a message</li>
                    </ul>
                </div>
            </logic:present>

            <input type="hidden" name="type" value="1" />
            <div class="control-group">
                <label class="control-label">Message</label>
                <div class="controls"><html:textarea rows="6" cols="30" property="message"/></div>
            </div>
            <div class="control-group">
                <label class="control-label">Preferred contact email or phone number</label>
                <div class="controls"><html:text property="email"/></div>
            </div>
            <div class="form-actions">
                <html:submit value="Send" styleClass="btn"/>
            </div>
        </fieldset>
    </html:form>

    <h2>Questions about your details, the site, feedback, any other questions</h2>
    <ul>
        <li>For Frequently Asked Questions please visit the <html:link action="ibd-help">Help</html:link> section. For any queries about login problems,
            incorrect details on the site, content issues, results not appearing or being wrong, or any
            concerns or feedback, email us here. </li>

        <li><strong>Note:</strong> Your name and NHS number will be sent with this message.</li>

        <li>Please enter your message here with your preferred contact email or phone number.</li>
    </ul>
    <html:form action="/patient/ibd-contact-send">
        <fieldset>
            <logic:present name="otherFormError">
                <div class="alert alert-error">
                    <p>To continue you must meet the following criteria</p>
                    <ul>
                        <li>Please enter a message</li>
                    </ul>
                </div>
            </logic:present>

            <input type="hidden" name="type" value="2" />
            <div class="control-group">
                <label class="control-label">Message</label>
                <div class="controls"><html:textarea rows="6" cols="30" property="message"/></div>
            </div>
            <div class="control-group">
                <label class="control-label">Preferred contact email or phone number</label>
                <div class="controls"><html:text property="email"/></div>
            </div>
            <div class="form-actions">
                <html:submit value="Send" styleClass="btn"/>
            </div>
        </fieldset>
    </html:form>
</logic:notPresent>

<logic:present name="emailSent">
    <div class="alert alert-success">Your contact form was successfully submitted.</div>
</logic:present>




