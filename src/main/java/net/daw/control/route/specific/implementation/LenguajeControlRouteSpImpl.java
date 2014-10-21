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
package net.daw.control.route.specific.implementation;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import java.util.HashMap;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import net.daw.control.operation.publicinterface.ControlOperationInterface;
import net.daw.control.route.publicinterface.ControlRouteInterface;

/**
 *
 * @author al037805
 */
public class LenguajeControlRouteSpImpl implements ControlRouteInterface{
    
    @Override
    public String execute(HttpServletRequest request, ControlOperationInterface oControl) throws Exception {
        String operation = request.getParameter("op");        
        String jsonResult = "";
        switch (operation) {
            case "get":
                jsonResult = oControl.get(request);
                break;
            case "getaggregateviewone":
                jsonResult = oControl.getaggregateviewone(request);
                break;
            case "getprettycolumns":
                jsonResult = oControl.getprettycolumns(request);
                break;
            case "getcolumns":
                jsonResult = oControl.getcolumns(request);
                break;
            case "getpage":
                jsonResult = oControl.getpage(request);
                break;
            case "getpages":
                jsonResult = oControl.getpages(request);
                break;
            case "getregisters":
                jsonResult = oControl.getregisters(request);
                break;
            case "getaggregateviewsome":
                jsonResult = oControl.getaggregateviewsome(request);
                break;
            case "remove":
                jsonResult = oControl.remove(request);
                break;
            case "save":
                jsonResult = oControl.save(request);
                break;
            default:
                Map<String, String> data = new HashMap<>();
                data.put("status", "401");
                data.put("message", "error de operación: la operación no existe");
                Gson gson = new GsonBuilder().setDateFormat("dd/MM/yyyy").create();
                jsonResult = gson.toJson(data);
                break;
        }
        return jsonResult;
    }

    
}
