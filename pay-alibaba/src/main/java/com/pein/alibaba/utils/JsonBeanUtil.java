//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package com.pein.alibaba.utils;

import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fasterxml.jackson.core.JsonFactory;
import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.databind.type.TypeFactory;
import org.apache.commons.lang3.StringUtils;

import java.io.StringWriter;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.concurrent.ConcurrentHashMap;

public class JsonBeanUtil {
    private static JsonBeanUtil allJsonBeanUtil;
    private static JsonBeanUtil notNullJsonBeanUtil;
    private static JsonBeanUtil notDefJsonBeanUtil;
    private static JsonBeanUtil notEmpJsonBeanUtil;
    private ObjectMapper mapper = new ObjectMapper();

    public static JsonBeanUtil getAllJsonBeanUtil() {
        return allJsonBeanUtil;
    }

    public static void setAllJsonBeanUtil(JsonBeanUtil allJsonBeanUtil) {
        allJsonBeanUtil = allJsonBeanUtil;
    }

    public static JsonBeanUtil getNotNullJsonBeanUtil() {
        return notNullJsonBeanUtil;
    }

    public static void setNotNullJsonBeanUtil(JsonBeanUtil notNullJsonBeanUtil) {
        notNullJsonBeanUtil = notNullJsonBeanUtil;
    }

    public static JsonBeanUtil getNotDefJsonBeanUtil() {
        return notDefJsonBeanUtil;
    }

    public static void setNotDefJsonBeanUtil(JsonBeanUtil notDefJsonBeanUtil) {
        notDefJsonBeanUtil = notDefJsonBeanUtil;
    }

    public static JsonBeanUtil getNotEmpJsonBeanUtil() {
        return notEmpJsonBeanUtil;
    }

    public static void setNotEmpJsonBeanUtil(JsonBeanUtil notEmpJsonBeanUtil) {
        notEmpJsonBeanUtil = notEmpJsonBeanUtil;
    }

    public ObjectMapper getMapper() {
        return this.mapper;
    }

    public void setMapper(ObjectMapper mapper) {
        this.mapper = mapper;
    }

    public JsonBeanUtil(Include include) {
        this.mapper.setSerializationInclusion(include);
        this.mapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
        this.mapper.configure(DeserializationFeature.FAIL_ON_NUMBERS_FOR_ENUMS, true);
//        switch(JsonBeanUtil.SyntheticClass_1.$SwitchMap$com$fasterxml$jackson$annotation$JsonInclude$Include[include.ordinal()]) {
//            case 1:
//            case 2:
//                this.mapper.disable(SerializationFeature.WRITE_NULL_MAP_VALUES);
//            default:
//                this.setDateFormat("yyyy-MM-dd HH:mm:ss");
//        }
    }

    public static JsonBeanUtil buildNormalBinder() {
        Class var0 = JsonBeanUtil.class;
        synchronized(JsonBeanUtil.class) {
            if(allJsonBeanUtil == null) {
                allJsonBeanUtil = new JsonBeanUtil(Include.ALWAYS);
            }
        }

        return allJsonBeanUtil;
    }

    public static JsonBeanUtil buildNonNullBinder() {
        Class var0 = JsonBeanUtil.class;
        synchronized(JsonBeanUtil.class) {
            if(notNullJsonBeanUtil == null) {
                notNullJsonBeanUtil = new JsonBeanUtil(Include.NON_NULL);
            }
        }

        return notNullJsonBeanUtil;
    }

    public static JsonBeanUtil buildNonDefaultBinder() {
        Class var0 = JsonBeanUtil.class;
        synchronized(JsonBeanUtil.class) {
            if(notDefJsonBeanUtil == null) {
                notDefJsonBeanUtil = new JsonBeanUtil(Include.NON_DEFAULT);
            }
        }

        return notDefJsonBeanUtil;
    }

    public static JsonBeanUtil buildNonEmptyBinder() {
        Class var0 = JsonBeanUtil.class;
        synchronized(JsonBeanUtil.class) {
            if(notEmpJsonBeanUtil == null) {
                notEmpJsonBeanUtil = new JsonBeanUtil(Include.NON_EMPTY);
            }
        }

        return notEmpJsonBeanUtil;
    }

    public <T> Object getJsonToObject(String json, Class<T> clazz) {
        Object object = null;
        if(StringUtils.isNotBlank(json)) {
            try {
                object = this.getMapper().readValue(json, clazz);
            } catch (Exception var5) {
                var5.printStackTrace();
            }
        }

        return object;
    }

    public Object getJsonToList(String json, Class clazz) {
        Object object = null;
        if(StringUtils.isNotBlank(json)) {
            try {
                object = this.getMapper().readValue(json, TypeFactory.defaultInstance().constructCollectionType(ArrayList.class, clazz));
            } catch (Exception var5) {
                var5.printStackTrace();
            }
        }

        return object;
    }

    public Object getJsonToListByMap(String json, Class clazz) {
        Object object = null;
        if(StringUtils.isNotBlank(json)) {
            try {
                object = this.getMapper().readValue(json, TypeFactory.defaultInstance().constructArrayType(TypeFactory.defaultInstance().constructMapType(HashMap.class, String.class, clazz)));
            } catch (Exception var5) {
                var5.printStackTrace();
            }
        }

        return object;
    }

    public Object[] getJsonToArray(String json, Class clazz) {
        Object[] object = null;
        if(StringUtils.isNotBlank(json)) {
            try {
                object = (Object[])this.getMapper().readValue(json, TypeFactory.defaultInstance().constructArrayType(clazz));
            } catch (Exception var5) {
                var5.printStackTrace();
            }
        }

        return object;
    }

    public byte[] getJsonTobyteArray(String json) {
        byte[] object = null;
        if(StringUtils.isNotBlank(json)) {
            try {
                object = (byte[])this.getMapper().readValue(json, TypeFactory.defaultInstance().constructArrayType(Byte.TYPE));
            } catch (Exception var4) {
                var4.printStackTrace();
            }
        }

        return object;
    }

    public Object getJsonToMap(String json, Class keyclazz, Class valueclazz) {
        Object object = null;
        if(StringUtils.isNotBlank(json)) {
            try {
                object = this.getMapper().readValue(json, TypeFactory.defaultInstance().constructParametricType(HashMap.class, new Class[]{keyclazz, valueclazz}));
            } catch (Exception var6) {
                var6.printStackTrace();
            }
        }

        return object;
    }

    public Object getJsonToConcMap(String json, Class keyclazz, Class valueclazz) {
        Object object = null;
        if(StringUtils.isNotBlank(json)) {
            try {
                object = this.getMapper().readValue(json, TypeFactory.defaultInstance().constructParametricType(ConcurrentHashMap.class, new Class[]{keyclazz, valueclazz}));
            } catch (Exception var6) {
                var6.printStackTrace();
            }
        }

        return object;
    }

    public Object getJsonToMapByMap(String json, Class keyclazz, Class valueclazz) {
        Object object = null;
        if(StringUtils.isNotBlank(json)) {
            try {
                object = this.getMapper().readValue(json, TypeFactory.defaultInstance().constructMapType(HashMap.class, TypeFactory.defaultInstance().uncheckedSimpleType(keyclazz), TypeFactory.defaultInstance().constructMapType(HashMap.class, String.class, valueclazz)));
            } catch (Exception var6) {
                var6.printStackTrace();
            }
        }

        return object;
    }

    public Object getJsonToMapByList(String json, Class keyclazz, Class valueclazz) {
        Object object = null;
        if(StringUtils.isNotBlank(json)) {
            try {
                object = this.getMapper().readValue(json, TypeFactory.defaultInstance().constructMapType(HashMap.class, TypeFactory.defaultInstance().uncheckedSimpleType(keyclazz), TypeFactory.defaultInstance().constructCollectionType(ArrayList.class, valueclazz)));
            } catch (Exception var6) {
                var6.printStackTrace();
            }
        }

        return object;
    }

    public static Map<String, String> getJsonToMap(String str) {
        HashMap map = new HashMap();
        if(StringUtils.isNotBlank(str)) {
            String[] s = str.split(",");
            if(s.length > 0) {
                for(int i = 0; i < s.length; ++i) {
                    String con = s[i];
                    int s1 = con.indexOf(":");
                    if(s1 > 0) {
                        map.put(con.substring(0, s1).trim().replace("\"", ""), con.substring(s1 + 1).replace("\"", ""));
                    } else {
                        map.put(con.trim().replace("\"", ""), "");
                    }
                }
            }
        }

        return map;
    }

    public String getMapToJson(Map<String, String> map) {
        ArrayList list = new ArrayList();
        if(null != map && !map.isEmpty()) {
            Iterator i$ = map.keySet().iterator();

            while(i$.hasNext()) {
                String key = (String)i$.next();
                String[] strS = new String[]{key, (String)map.get(key)};
                list.add(strS);
            }
        }

        return this.jsonObject(list);
    }

    public String jsonObject(List list) {
        StringWriter sw = new StringWriter();

        try {
            JsonGenerator gen = (new JsonFactory()).createGenerator(sw);
            this.getMapper().writeValue(gen, list);
            gen.close();
        } catch (Exception var5) {
            ;
        }

        return sw.toString();
    }

    public Object getJsonToObject(String json, Class objclazz, Class... pclazz) {
        Object object = null;
        if(StringUtils.isNotBlank(json)) {
            try {
                object = this.getMapper().readValue(json, TypeFactory.defaultInstance().constructParametricType(objclazz, pclazz));
            } catch (Exception var6) {
                ;
            }
        }

        return object;
    }

    public <T> Object getJsonToObject(String json, TypeReference<T> typeReference) {
        Object object = null;
        if(StringUtils.isNotBlank(json)) {
            try {
                object = this.getMapper().readValue(json, typeReference);
            } catch (Exception var5) {
                ;
            }
        }

        return object;
    }

    public String toJson(Object object) {
        String json = null;

        try {
            json = this.getMapper().writeValueAsString(object);
        } catch (Exception var4) {
            var4.printStackTrace();
        }

        return json;
    }

    public void setDateFormat(String pattern) {
        if(StringUtils.isNotBlank(pattern)) {
            SimpleDateFormat df = new SimpleDateFormat(pattern);
            this.getMapper().setDateFormat(df);
        }

    }

    public static Object getResultObject(String json) {
        Map map = (Map)buildNormalBinder().getJsonToMap(json, String.class, Object.class);
        return map != null?map.get("result"):json;
    }

}
