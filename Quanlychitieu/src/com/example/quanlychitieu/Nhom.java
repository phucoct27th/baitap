package com.example.quanlychitieu;

import java.util.ArrayList;

public class Nhom {

    private String phanloai;
    private String khoanthukhoanchi;
 

	public String getPhanloai() {
		return phanloai;
	}

	public void setPhanloai(String phanloai) {
		this.phanloai = phanloai;
	}

	public String getKhoanthukhoanchi() {
		return khoanthukhoanchi;
	}

	public void setKhoanthukhoanchi(String khoanthukhoanchi) {
		this.khoanthukhoanchi = khoanthukhoanchi;
	}

	private ArrayList<Child> Items;


    public ArrayList<Child> getItems() {
        return Items;
    }

    public void setItems(ArrayList<Child> Items) {
        this.Items = Items;
    }

}