package com.solidstategroup.radar.dao;

import com.solidstategroup.radar.model.sequenced.LabData;

import java.util.List;

public interface LabDataDao {

    void saveLabDate(LabData labData);

    LabData getLabData(long id);

    List<LabData> getLabDataByRadarNumber(long id);
}
