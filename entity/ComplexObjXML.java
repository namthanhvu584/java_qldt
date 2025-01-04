package com.qldt.entity;

import java.util.List;
 
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;
 
@XmlRootElement(name = "ComplexObjs")
@XmlAccessorType(XmlAccessType.FIELD)

public class ComplexObjXML {
    private List<ComplexObj> list;

    public List<ComplexObj> getList() {
        return list;
    }

    public void setList(List<ComplexObj> list) {
        this.list = list;
    }
    
}
