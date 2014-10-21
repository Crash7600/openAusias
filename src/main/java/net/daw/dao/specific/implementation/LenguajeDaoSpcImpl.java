/*
 * Copyright (C) 2014 al037805
 *
 * This program is free software; you can redistribute it and/or
 * modify it under the terms of the GNU General Public License
 * as published by the Free Software Foundation; either version 2
 * of the License, or (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program; if not, write to the Free Software
 * Foundation, Inc., 59 Temple Place - Suite 330, Boston, MA  02111-1307, USA.
 */
package net.daw.dao.specific.implementation;

import java.sql.Connection;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import net.daw.bean.generic.specific.implementation.LenguajeBeanGenSpImpl;
import net.daw.dao.publicinterface.MetaDaoInterface;
import net.daw.dao.publicinterface.TableDaoInterface;
import net.daw.dao.publicinterface.ViewDaoInterface;
import net.daw.data.specific.implementation.MysqlDataSpImpl;
import net.daw.helper.FilterBeanHelper;

/**
 *
 * @author al037805
 */
public class LenguajeDaoSpcImpl implements ViewDaoInterface<LenguajeBeanGenSpImpl>,TableDaoInterface<LenguajeBeanGenSpImpl> , MetaDaoInterface {
    
    private final String strTableName = "lenguaje";
    private final String strClassName = "LenguajeDaoSpcImpl";
    private final MysqlDataSpImpl oMysql;
    private final String strView;
    private Connection connection = null;

    public LenguajeDaoSpcImpl(String view, Connection pooledConnection) throws Exception {
        try {
            connection = pooledConnection;
            strView = view;
            oMysql = new MysqlDataSpImpl(connection);
        } catch (Exception e) {
            throw new Exception(strClassName + ".constructor: Error: " + e.getMessage());
        }
    }

    @Override
    public int getPages(int intRegsPerPag, ArrayList<FilterBeanHelper> hmFilter) throws Exception {
        int pages;
        try {
            pages = oMysql.getPages(strTableName, intRegsPerPag, hmFilter);
            return pages;
        } catch (Exception e) {
            throw new Exception(strClassName + ".getPages: Error: " + e.getMessage());
        }
    }

    @Override
    public int getCount(ArrayList<FilterBeanHelper> hmFilter) throws Exception {
        int pages;
        try {
            pages = oMysql.getCount(strTableName, hmFilter);
            return pages;
        } catch (Exception e) {
            throw new Exception(strClassName + ".getCount: Error: " + e.getMessage());
        }
    }

    @Override
    public ArrayList<LenguajeBeanGenSpImpl> getPage(int intRegsPerPag, int intPage, ArrayList<FilterBeanHelper> hmFilter, HashMap<String, String> hmOrder) throws Exception {
        ArrayList<Integer> arrId;
        ArrayList<LenguajeBeanGenSpImpl> arrLenguaje = new ArrayList<>();
        try {
            arrId = oMysql.getPage(strTableName, intRegsPerPag, intPage, hmFilter, hmOrder);
            Iterator<Integer> iterador = arrId.listIterator();
            while (iterador.hasNext()) {
                LenguajeBeanGenSpImpl oLenguajeBean = new LenguajeBeanGenSpImpl(iterador.next());
                arrLenguaje.add(this.get(oLenguajeBean));
            }
            return arrLenguaje;
        } catch (Exception e) {
            throw new Exception(strClassName + ".getPage: Error: " + e.getMessage());
        }
    }

    @Override
    public LenguajeBeanGenSpImpl get(LenguajeBeanGenSpImpl oLenguajeBean) throws Exception {
        if (oLenguajeBean.getId() > 0) {
            try {
                if (!oMysql.existsOne("lenguaje", oLenguajeBean.getId())) {
                    oLenguajeBean.setId(0);
                } else {
                    oLenguajeBean.setNombre(oMysql.getOne(strTableName, "nombre", oLenguajeBean.getId()));
                    
                }
            } catch (Exception e) {
                throw new Exception(strClassName + ".get: Error: " + e.getMessage());
            }
        } else {
            oLenguajeBean.setId(0);
        }
        return oLenguajeBean;
    }

    @Override
    public LenguajeBeanGenSpImpl set(LenguajeBeanGenSpImpl oLenguajeBean) throws Exception {
        try {
            if (oLenguajeBean.getId() == 0) {
                oLenguajeBean.setId(oMysql.insertOne(strTableName));
            }
            oMysql.updateOne(oLenguajeBean.getId(), strTableName, "nombre", oLenguajeBean.getNombre());
            
        } catch (Exception e) {
            throw new Exception(strClassName + ".set: Error: " + e.getMessage());
        }
        return oLenguajeBean;
    }

    @Override
    public int remove(LenguajeBeanGenSpImpl oLenguajeBean) throws Exception {
        int result = 0;
        try {
            result = oMysql.removeOne(oLenguajeBean.getId(), strTableName);
        } catch (Exception e) {
            throw new Exception(strClassName + ".remove: Error: " + e.getMessage());
        }
        return result;
    }

    @Override
    public ArrayList<String> getColumnsNames() throws Exception {
        ArrayList<String> alColumns = null;
        try {
            alColumns = oMysql.getColumnsName(strTableName);
        } catch (Exception e) {
            throw new Exception(strClassName + ".getColumnsNames: Error: " + e.getMessage());
        } finally {
        }
        return alColumns;
    }

    @Override
    public ArrayList<String> getPrettyColumnsNames() throws Exception {
        ArrayList<String> alColumns = null;
        try {
            alColumns = oMysql.getPrettyColumns(strTableName);
        } catch (Exception e) {
            throw new Exception(strClassName + ".getPrettyColumnsNames: Error: " + e.getMessage());
        } finally {
        }
        return alColumns;
    }
    
}
