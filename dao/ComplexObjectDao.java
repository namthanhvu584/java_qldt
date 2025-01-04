package com.qldt.dao;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import com.qldt.entity.ComplexObj;
import com.qldt.entity.ComplexObjXML;
import com.qldt.utils.FileUtils;

public class ComplexObjectDao {
    private static final String FILE_NAME = "complexObj.xml";
    private List<ComplexObj> list;

    public ComplexObjectDao() {
        this.list = readListObj();
        if (list == null) {
            list = new ArrayList<ComplexObj>();
        }
    }

    public void writeListObj(List<ComplexObj> objs) {
        ComplexObjXML xml = new ComplexObjXML();
        xml.setList(objs);
        FileUtils.writeXMLtoFile(FILE_NAME, xml);
    }

    public List<ComplexObj> readListObj() {
        List<ComplexObj> list = new ArrayList<ComplexObj>();
        ComplexObjXML xml = (ComplexObjXML) FileUtils.readXMLFile(FILE_NAME, ComplexObjXML.class);
        if (xml != null) {
            list = xml.getList();
        }
        return list;
    }

    public void add(ComplexObj obj) {
        list.add(obj);
        writeListObj(list);
    }

    public void edit(ComplexObj obj) {
        int size = list.size();
        for (int i = 0; i < size; i++) {
            if (list.get(i).getId().equals(obj.getId())) {
                list.get(i).setName(obj.getName());
                list.get(i).setBirth(obj.getBirth());
                list.get(i).setPlace(obj.getPlace());
                list.get(i).setHold(obj.getHold());
                list.get(i).setFamily(obj.getFamily());
                list.get(i).setRelate(obj.getRelate());
                list.get(i).setType(obj.getType());
                list.get(i).setGender(obj.getGender());
                writeListObj(list);
                break;
            }
        }
    }

    public void delete(ComplexObj obj) {
        boolean isFound = false;
        int size = list.size();
        for (int i = 0; i < size; i++) {
            if (list.get(i).getId().equals(obj.getId())) {
                obj = list.get(i);
                isFound = true;
                break;
            }
        }
        if (isFound) {
            list.remove(obj);
            writeListObj(list);
        }
    }

    public void sortObjByName() {
        Collections.sort(list, new Comparator<ComplexObj>() {
            public int compare(ComplexObj obj1, ComplexObj obj2) {
                return obj1.getName().compareTo(obj2.getName());
            }
        });
    }

    public void sortObjByID() {
        Collections.sort(list, new Comparator<ComplexObj>() {
            public int compare(ComplexObj obj1, ComplexObj obj2) {
                return obj1.getId().compareTo(obj2.getId());
            }
        });
    }

    public List<ComplexObj> searchObj(String search) {
        List<ComplexObj> res = new ArrayList<ComplexObj>();
        boolean isFound = false;
        for (ComplexObj obj : list) {
            if (obj.getName().contains(search)) {
                res.add(obj);
                isFound = true;
            }
        }
        if (!isFound) {
            return null;
        }
        return res;
    }

    public List<ComplexObj> searchObjById(String search) {
        List<ComplexObj> res = new ArrayList<ComplexObj>();
        boolean isFound = false;
        for (ComplexObj obj : list) {
            if (obj.getId().contains(search)) {
                res.add(obj);
                isFound = true;
            }
        }
        if (!isFound) {
            return null;
        }
        return res;
    }

    public String stat() {
        if (list.size() == 0) {
            return "Chưa có đối tượng";
        }
        int tienan = 0;
        int tiensu = 0;
        int nghien = 0;
        int nhanthandb = 0;
        int khanghi = 0;
        int chuabiet = 0;
        for (ComplexObj obj : list) {
            if (obj.getType().equals("Tiền án")) {
                tienan++;
            }
            if (obj.getType().equals("Tiền sự")) {
                tiensu++;
            }
            if (obj.getType().equals("Đối tượng nghiện")) {
                nghien++;
            }
            if (obj.getType().equals("Nhân thân đặc biệt")) {
                nhanthandb++;
            }
            if (obj.getType().equals("Có dấu hiệu khả nghi")) {
                khanghi++;
            }
            if (obj.getType().equals("Chưa xác định")) {
                chuabiet++;
            }
        }
        String str1 = String.format("Có tổng cộng %d đối tượng được quản lí, gồm:\n", list.size());
        String str2 = String.format("%d đối tượng có tiền án.\n", tienan);
        String str3 = String.format("%d đối tượng có tiền sự.\n", tiensu);
        String str4 = String.format("%d đối tượng nghiện.\n", nghien);
        String str5 = String.format("%d đối tượng có nhân thân đặc biệt.\n", nhanthandb);
        String str6 = String.format("%d đối tượng chưa xác định.\n", chuabiet);
        String str7 = String.format("%d đối tượng có tiền án.\n", khanghi);

        return str1 + str2 + str3 + str4 + str5 + str7 + str6;
    }

    public List<ComplexObj> getList() {
        return list;
    }

    public void setList(List<ComplexObj> list) {
        this.list = list;
    }

    public List<ComplexObj> tienAn(List<ComplexObj> list){
        int size = list.size();
        boolean isFound = false;
        List<ComplexObj> res = new ArrayList<>();
        for(int i = 0; i < size; i++){
            if (list.get(i).getType().equals("Tiền án")) {
                res.add(list.get(i));
                isFound = true;
            }
        }
        if (isFound) {
            return res;
        }
        return null;
    }

    public List<ComplexObj> tienSu(List<ComplexObj> list){
        int size = list.size();
        boolean isFound = false;
        List<ComplexObj> res = new ArrayList<>();
        for(int i = 0; i < size; i++){
            if (list.get(i).getType().equals("Tiền sự")) {
                res.add(list.get(i));
                isFound = true;
            }
        }
        if (isFound) {
            return res;
        }
        return null;
    }

    public List<ComplexObj> nghien(List<ComplexObj> list){
        int size = list.size();
        boolean isFound = false;
        List<ComplexObj> res = new ArrayList<>();
        for(int i = 0; i < size; i++){
            if (list.get(i).getType().equals("Đối tượng nghiện")) {
                res.add(list.get(i));
                isFound = true;
            }
        }
        if (isFound) {
            return res;
        }
        return null;
    }

    public List<ComplexObj> nhanThanDacBiet(List<ComplexObj> list){
        int size = list.size();
        boolean isFound = false;
        List<ComplexObj> res = new ArrayList<>();
        for(int i = 0; i < size; i++){
            if (list.get(i).getType().equals("Nhân thân đặc biệt")) {
                res.add(list.get(i));
                isFound = true;
            }
        }
        if (isFound) {
            return res;
        }
        return null;
    }

    public List<ComplexObj> coDauHieuKhaNghi(List<ComplexObj> list){
        int size = list.size();
        boolean isFound = false;
        List<ComplexObj> res = new ArrayList<>();
        for(int i = 0; i < size; i++){
            if (list.get(i).getType().equals("Có dấu hiệu khả nghi")) {
                res.add(list.get(i));
                isFound = true;
            }
        }
        if (isFound) {
            return res;
        }
        return null;
    }

    public List<ComplexObj> khac(List<ComplexObj> list){
        int size = list.size();
        boolean isFound = false;
        List<ComplexObj> res = new ArrayList<>();
        for(int i = 0; i < size; i++){
            if (list.get(i).getType().equals("Chưa xác định")) {
                res.add(list.get(i));
                isFound = true;
            }
        }
        if (isFound) {
            return res;
        }
        return null;
    }

    public List<ComplexObj> gioiTinhKhac(List<ComplexObj> list){
        int size = list.size();
        boolean isFound = false;
        List<ComplexObj> res = new ArrayList<>();
        for(int i = 0; i < size; i++){
            if (list.get(i).getGender().equals("Khác")) {
                res.add(list.get(i));
                isFound = true;
            }
        }
        if (isFound) {
            return res;
        }
        return null;
    }

    public List<ComplexObj> nam(List<ComplexObj> list){
        int size = list.size();
        boolean isFound = false;
        List<ComplexObj> res = new ArrayList<>();
        for(int i = 0; i < size; i++){
            if (list.get(i).getGender().equals("Nam")) {
                res.add(list.get(i));
                isFound = true;
            }
        }
        if (isFound) {
            return res;
        }
        return null;
    }

    public List<ComplexObj> nu(List<ComplexObj> list){
        int size = list.size();
        boolean isFound = false;
        List<ComplexObj> res = new ArrayList<>();
        for(int i = 0; i < size; i++){
            if (list.get(i).getGender().equals("Nữ")) {
                res.add(list.get(i));
                isFound = true;
            }
        }
        if (isFound) {
            return res;
        }
        return null;
    }

}
