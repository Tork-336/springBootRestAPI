package com.applicationAPI.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class UtilResponse {

    public static Map<String, Object> mapOk(List data) {
        Map<String, Object> retorno = new HashMap<>(0);
        retorno.put("data", data);
        retorno.put("total", data.size());
        retorno.put("success", true);
        return retorno;
    }

    public static Map<String, Object> mapOk(Object data) {
        Map<String, Object> retorno = new HashMap<>(0);
        retorno.put("data", data);
        retorno.put("success", true);
        return retorno;
    }

    public static Map<String, Object> mapError (String message) {
        Map<String, Object> retorno = new HashMap<>(0);
        retorno.put("message", message);
        retorno.put("success", false);
        return retorno;
    }

    public static Map<String, Object> mapError (String message, List data) {
        Map<String, Object> retorno = new HashMap<>(0);
        retorno.put("data", data);
        retorno.put("message", message);
        retorno.put("success", true);
        return retorno;
    }
}
