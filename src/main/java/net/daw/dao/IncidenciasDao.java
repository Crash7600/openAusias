/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package net.daw.dao;

import net.daw.bean.IncidenciasBean;

/**
 *
 * @author Jordi
 */
public class IncidenciasDao extends GenericDaoImplementation<IncidenciasBean> {

    public IncidenciasDao() throws Exception {
        super( "incidencias");
    }

}