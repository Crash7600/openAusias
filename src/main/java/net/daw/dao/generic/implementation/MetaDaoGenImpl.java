/*
 * Copyright (C) July 2014 Rafael Aznar
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
package net.daw.dao.generic.implementation;

import net.daw.dao.publicinterface.MetaDaoInterface;
import java.sql.Connection;
import java.util.ArrayList;
import net.daw.data.specific.implementation.MysqlDataSpImpl;

public class MetaDaoGenImpl<TIPO_OBJETO> implements MetaDaoInterface {

    protected final MysqlDataSpImpl oMysql;
    protected final String strView;
    protected Connection connection = null;

    public MetaDaoGenImpl(String view, Connection pooledConnection) throws Exception {
        try {
            connection = pooledConnection;
            strView = view;
            oMysql = new MysqlDataSpImpl(connection);
        } catch (Exception e) {
            throw new Exception("GenericMetaDaoImpl.constructor: Error: " + e.getMessage());
        }
    }

    @Override
    public ArrayList<String> getColumnsNames() throws Exception {
        ArrayList<String> alColumns = null;
        try {
            alColumns = oMysql.getColumnsName(strView);
        } catch (Exception e) {
            throw new Exception("GenericMetaDaoImpl.getColumnsNames: Error: " + e.getMessage());
        }
        return alColumns;
    }

    @Override
    public ArrayList<String> getPrettyColumnsNames() throws Exception {
        ArrayList<String> alColumns = null;
        try {
            alColumns = oMysql.getPrettyColumns(strView);
        } catch (Exception e) {
            throw new Exception("GenericMetaDaoImpl.getPrettyColumnsNames: Error: " + e.getMessage());
        }
        return alColumns;
    }

}
