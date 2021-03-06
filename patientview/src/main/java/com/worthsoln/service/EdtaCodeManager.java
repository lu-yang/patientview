package com.worthsoln.service;

import com.worthsoln.patientview.model.EdtaCode;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 *
 */
@Transactional(propagation = Propagation.REQUIRES_NEW)
public interface EdtaCodeManager {

    EdtaCode getEdtaCode(String edtaCode);

    void save(EdtaCode edtaCode);

    void delete(String edtaCode);

    List<EdtaCode> get(String linkType);
}
