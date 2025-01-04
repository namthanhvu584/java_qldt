package com.qldt.controller;

import java.util.List;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

import com.qldt.dao.ComplexObjectDao;
import com.qldt.entity.ComplexObj;
import com.qldt.view.View;

public class ConmplexObjectController {
    private ComplexObjectDao dao;
    private View view;

    public ConmplexObjectController(View view) {
        this.view = view;
        dao = new ComplexObjectDao();

        view.addAddObjListener(new AddListener());
        view.addEditObjListener(new EditListener());
        view.addClearObjListener(new ClearListener());
        view.addDelObjListener(new DeleteListener());
        view.addSearchObjListener(new SearchListener());
        view.addSortObjListener(new SortListener());
        view.addStatObjListener(new StatListener());
        view.addSearchIDObjListener(new SearchIDListener());

        view.addListObjSelectionListener(new ListObjSelectionListener());
    }

    public void showObjView() {
        List<ComplexObj> list = dao.getList();
        view.setVisible(true);
        view.showListObj(list);
    }

    class AddListener implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            ComplexObj obj = view.getObjInfo();
            if (obj != null) {
                dao.add(obj);
                view.showObj(obj);
                view.showListObj(dao.getList());
                view.showMessage("Đã thêm đối tượng");
                view.clearInfo();
            }
        }
    }

    class EditListener implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            ComplexObj obj = view.getObjInfo();
            if (obj != null) {
                dao.edit(obj);
                view.showObj(obj);
                view.showListObj(dao.getList());
                view.showMessage("Đã sửa thông tin đối tượng");
                view.clearInfo();
            }
        }
    }

    class DeleteListener implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            ComplexObj obj = view.getObjInfo();
            if (obj != null) {
                dao.delete(obj);
                view.clearInfo();
                view.showListObj(dao.getList());
                view.showMessage("Đã xóa đối tượng");
                view.clearInfo();
            }
        }
    }

    class ClearListener implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            view.clearInfo();
        }
    }

    class SortListener implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            List<ComplexObj> list;
            switch (view.getIndexSortCombobox()) {
                case 0:
                    dao.sortObjByID();
                    view.showListObj(dao.getList());
                    break;
                case 1:
                    dao.sortObjByName();
                    view.showListObj(dao.getList());
                    break;
                case 2:
                    list = dao.tienAn(dao.getList());
                    if (list == null) {
                        view.showMessage("Không có đối tượng có tiền án");
                    } else
                        view.showListObj(dao.tienAn(list));
                    break;
                case 3:
                    list = dao.tienSu(dao.getList());
                    if (list == null) {
                        view.showMessage("Không có đối tượng có tiền sự");
                    } else
                        view.showListObj(list);
                    break;
                case 4:
                    list = dao.nghien(dao.getList());
                    if (list == null) {
                        view.showMessage("Không có đối tượng nghiện");
                    } else
                        view.showListObj(list);
                    break;
                case 5:
                    list = dao.coDauHieuKhaNghi(dao.getList());
                    if (list == null) {
                        view.showMessage("Không có đối tượng có dấu hiệu khả nghi");
                    } else
                        view.showListObj(list);
                    break;
                case 6:
                    list = dao.nhanThanDacBiet(dao.getList());
                    if (list == null) {
                        view.showMessage("Không có đối tượng có thân nhân đặc biệt");
                    } else
                        view.showListObj(list);
                    break;
                case 7:
                    list = dao.khac(dao.getList());
                    if (list == null) {
                        view.showMessage("Không có đối tượng chưa xác định");
                    } else
                        view.showListObj(list);
                    break;
                case 8:
                    list = dao.nam(dao.getList());
                    if (list == null) {
                        view.showMessage("Không có đối tượng nam");
                    } else
                        view.showListObj(list);
                    break;
                case 9:
                    list = dao.nu(dao.getList());
                    if (list == null) {
                        view.showMessage("Không có đối tượng nữ");
                    } else
                        view.showListObj(list);
                    break;
                case 10:
                    list = dao.gioiTinhKhac(dao.getList());
                    if (list == null) {
                        view.showMessage("Không có đối tượng giới tính khác");
                    } else
                        view.showListObj(list);
                    break;
                default:
                    break;
            }
        }
    }

    class SearchListener implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            String search = view.getSearchField();
            if (!search.isBlank() && search != null) {
                if (dao.searchObj(search) == null) {
                    view.showMessage("Không tìm thấy đối tượng!");
                    return;
                }
                view.showListObj(dao.searchObj(search));
            }
        }
    }

    class SearchIDListener implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            String search = view.getSearchField();
            if (!search.isBlank() && search != null) {
                if (dao.searchObjById(search) == null) {
                    view.showMessage("Không tìm thấy đối tượng!");
                    return;
                }
                view.showListObj(dao.searchObjById(search));
            }
        }
    }

    class StatListener implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            view.showMessage(dao.stat());
        }

    }

    class ListObjSelectionListener implements ListSelectionListener {
        public void valueChanged(ListSelectionEvent e) {
            view.fillObjfromSelectedRow();
        }
    }
}
