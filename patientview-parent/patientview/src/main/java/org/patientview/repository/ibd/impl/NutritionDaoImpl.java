/*
 * PatientView
 *
 * Copyright (c) Worth Solutions Limited 2004-2013
 *
 * This file is part of PatientView.
 *
 * PatientView is free software: you can redistribute it and/or modify it under the terms of the
 * GNU General Public License as published by the Free Software Foundation, either version 3 of the License,
 * or (at your option) any later version.
 * PatientView is distributed in the hope that it will be useful, but WITHOUT ANY WARRANTY; without even
 * the implied warranty of MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the
 * GNU General Public License for more details.
 * You should have received a copy of the GNU General Public License along with PatientView in a file
 * titled COPYING. If not, see <http://www.gnu.org/licenses/>.
 *
 * @package PatientView
 * @link http://www.patientview.org
 * @author PatientView <info@patientview.org>
 * @copyright Copyright (c) 2004-2013, Worth Solutions Limited
 * @license http://www.gnu.org/licenses/gpl-3.0.html The GNU General Public License V3.0
 */

package org.patientview.repository.ibd.impl;

import org.patientview.ibd.model.Nutrition;
import org.patientview.ibd.model.Nutrition_;
import org.patientview.repository.AbstractHibernateDAO;
import org.patientview.repository.ibd.NutritionDao;
import org.springframework.stereotype.Repository;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.util.List;

@Repository(value = "nutritionDao")
public class NutritionDaoImpl extends AbstractHibernateDAO<Nutrition> implements NutritionDao {

    @Override
    public List<Nutrition> getAllNutritions(String nhsno) {
        CriteriaBuilder builder = getEntityManager().getCriteriaBuilder();
        CriteriaQuery<Nutrition> criteria = builder.createQuery(Nutrition.class);
        Root<Nutrition> nutritionRoot = criteria.from(Nutrition.class);

        criteria.where(builder.equal(nutritionRoot.get(Nutrition_.nhsno), nhsno));
        criteria.orderBy(builder.asc(nutritionRoot.get(Nutrition_.nutritionDate)));

        return getEntityManager().createQuery(criteria).getResultList();
    }

}