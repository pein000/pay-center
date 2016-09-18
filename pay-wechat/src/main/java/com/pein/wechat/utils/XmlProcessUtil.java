package com.pein.wechat.utils;

import org.dom4j.Document;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * Created by Darren on 2014/6/29 0029.
 */
public class XmlProcessUtil {

    // root元素
    private Element rootElement;

    private Map<String,String> textData = new ConcurrentHashMap<>();

    private Map<String,Element> elementData = new ConcurrentHashMap<>();

    public XmlProcessUtil(String xml){
        try {
            Document doc = DocumentHelper.parseText(xml);
            rootElement = doc.getRootElement();
            init(rootElement);
        }catch (Exception ex){
            ex.printStackTrace();
        }
    }

    private void init(Element node){
        List<Element> elementList = node.elements();
        Element temp;
        for(int i = 0 ; i < elementList.size(); i++){
            temp = elementList.get(i);
            if(temp.isTextOnly()){
                textData.put(temp.getName(),temp.getText());
            }else{
                elementData.put(temp.getName(),temp);
                init(temp);
            }
        }
    }

    /**
     * 根据Name获取相应节点的Text值
     * @param name 节点名称
     * @return text值
     */
    public String getTextByName(String name){
        return textData.get(name);
    }

    /**
     * 根据Name获取节点对象
     * @param name
     * @return
     */
    public Element getElementByName(String name){
        return elementData.get(name);
    }

    /**
     * 
     * 
     * XML 转换成 键值对
     * 
     */
    public Map getElementToMap(){
        return textData;
    }

    /**
     * 根据完整路径获取Text值
     * @param fullPath 完整路径，从root元素开始，格式：element1>element2>element3....>element=value
     * @return
     */
    public String getTextByFullPath(String fullPath){
        Element ele = getElementByFullPath(fullPath);
        if(ele != null){
            return ele.getTextTrim();
        }
        return "";
    }

    /**
     *
     * @param fullPath
     * @return
     */
    public Element getElementByFullPath(String fullPath){
        if(fullPath == null || fullPath.length() == 0){
            return null;
        }

        // 分割路径
        String[] paths = fullPath.split(">");
        // 获取最后
        Element ele = rootElement.element(paths[0]);
        if(paths.length < 2){
            return ele;
        }else {
            for (int i = 1; i <= (paths.length - 2); i++) {
                ele = ele.element(paths[i]);
            }
            // 查询字符串
            String queryStr = paths[paths.length -1];
            if(queryStr.contains("=")){
                String[] temp = queryStr.split("=");
                // 获取所有子节点
                List<Element> elementList = ele.elements(temp[0]);
                for(int i = 0; i < elementList.size(); i++){
                    Element ele1 = elementList.get(i);
                    if(ele1.getTextTrim().equalsIgnoreCase(temp[1])){
                        ele = ele1;
                    }
                }
            }else{
                ele = ele.element(queryStr);
            }
            return ele;
        }
    }
    
    /**
    *
    * @param fullPath
    * @return
    */
   public List<Element> getElementsByFullPath(String fullPath){
       if(fullPath == null || fullPath.length() == 0){
           return null;
       }

       // 分割路径
       String[] paths = fullPath.split(">");
       if(paths.length < 2){
    	   return rootElement.elements(paths[0]);
       }else {
    	   Element ele = rootElement.element(paths[0]);
    	   for (int i = 1; i <= (paths.length - 2); i++) {
               ele = ele.element(paths[i]);
           }
    	   String queryStr = paths[paths.length -1];
    	   if(ele != null){
    		   return ele.elements(queryStr);
    	   }
    	   return null;
       }
   }

    /**
     * Element转换成Map , 只转换没有子节点的
     * @param ele
     * @return
     */
    public static Map<String,String> convertToMap(Element ele){
        List<Element> elementList = ele.elements();
        Map<String,String> data = new HashMap<String, String>();
        Element temp;
        for(int i = 0 ; i < elementList.size(); i++){
            temp = elementList.get(i);
            if(temp.isTextOnly()){
                data.put(temp.getName(),temp.getText());
            }
        }
        return data;
    }
    
    /**
     * 添加单个Element
     * @param srcEle
     * @param insertEle
     */
    public void addElement(String srcEle,Element insertEle){
    	Element e = this.getElementByFullPath(srcEle);
    	// 复制一个没有父类的对象，并添加到e节点下
    	e.add(insertEle.createCopy());
    }
    
    /**
     * 添加多个Element
     * @param srcEle
     * @param insertEle
     */
    public void addElements(String srcEle,List<Element> insertEle){
    	if(insertEle == null || insertEle.isEmpty()){
    		throw new NullPointerException("");
    	}
    	
    	for(int i = 0 ; i < insertEle.size(); i++){
    		addElement(srcEle,insertEle.get(i));
    	}
    }
    
    /**
     * 合并多个xml文件
     * @param utils
     * @param srcEle
     * @param insertEle
     */
    public static String xmlAppend(List<XmlProcessUtil> utils,String srcEle,String insertEle){
    	if(utils == null || utils.isEmpty()){
    		throw new NullPointerException("银行响应数据空！");
    	}
    	XmlProcessUtil u1 = utils.get(0);
    	
    	if(utils.size() == 1){
    		return u1.rootElement.asXML();
    	}
    	
    	XmlProcessUtil temp = null;
    	List<Element> eleList = null;
    	for(int i = 1; i < utils.size(); i++){
    		temp = utils.get(i);
    		eleList = temp.getElementsByFullPath(insertEle);
    		u1.addElements(srcEle, eleList);
    	}
    	return u1.rootElement.asXML();
    }
}
