package com.worthsoln.ibd.action.mymedications;

import com.worthsoln.ibd.Ibd;
import com.worthsoln.ibd.action.BaseAction;
import com.worthsoln.ibd.model.medication.MyMedication;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.action.DynaActionForm;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class MyMedicationEditAction extends BaseAction {

    public ActionForward execute(ActionMapping mapping, ActionForm form, HttpServletRequest request,
                                 HttpServletResponse response) throws Exception {
        MyMedication myMedication = new MyMedication();

        DynaActionForm dynaForm = (DynaActionForm) form;

        String dateStarted = "";

        if (myMedication.getDateStarted() != null) {
            dateStarted = Ibd.DATE_FORMAT.format(myMedication.getDateStarted());
        }

        dynaForm.set(Ibd.DATE_STARTED_PARAM, dateStarted);

        if (myMedication.getMedicationType() != null) {
            dynaForm.set(Ibd.MEDICATION_TYPE_ID_PARAM, myMedication.getMedicationType().getId());
        } else {
            dynaForm.set(Ibd.MEDICATION_TYPE_ID_PARAM, new Long(-1));
        }

        if (myMedication.getMedication() != null) {
            dynaForm.set(Ibd.MEDICATION_ID_PARAM, myMedication.getMedication().getId());
        } else if (myMedication.getOtherMedication() != null && myMedication.getOtherMedication().length() > 0) {
            dynaForm.set(Ibd.MEDICATION_ID_PARAM, new Long(-2));
        } else {
            dynaForm.set(Ibd.MEDICATION_ID_PARAM, new Long(-1));
        }

        String otherMedication = "";
        if (myMedication.getOtherMedication() != null) {
            otherMedication = myMedication.getOtherMedication();
        }

        dynaForm.set(Ibd.OTHER_MEDICATION_ID_PARAM, otherMedication);
        dynaForm.set(Ibd.MEDICATION_NO_OF_ID_PARAM, myMedication.getMedicationNoOfId());
        dynaForm.set(Ibd.MEDICATION_FREQUENCY_ID_PARAM, myMedication.getMedicationFrequencyId());

        // set up all the needed lists in the session
        request.getSession().setAttribute(MEDICATION_TYPE_LIST_PROPERTY, getMedicationTypeList());
        request.getSession().setAttribute(MEDICATION_NO_OF_LIST_PROPERTY, getMedicationNoOfList());
        request.getSession().setAttribute(MEDICATION_FREQUENCY_LIST_PROPERTY, getMedicationFrequencyList());

        return mapping.findForward(SUCCESS);
    }
}