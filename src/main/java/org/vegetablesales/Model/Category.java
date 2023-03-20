package org.vegetablesales.Model;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

import org.springframework.stereotype.Component;

@Entity
@Component
public class Category {
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Integer catId;
	private String catName;
	@OneToMany(cascade=CascadeType.ALL)
	private List<VegetableDTO> vegList = new ArrayList<>();
	public Category() {

	}
	
	public Category(Integer catId, String catName, List<VegetableDTO> vegList) {
		super();
		this.catId = catId;
		this.catName = catName;
		this.vegList = vegList;
	}

	public Integer getCatId() {
		return catId;
	}
	public void setCatId(Integer catId) {
		this.catId = catId;
	}
	public String getCatName() {
		return catName;
	}
	public void setCatName(String catName) {
		this.catName = catName;
	}
	
	public List<VegetableDTO> getVegList() {
		return vegList;
	}

	public void setVegList(List<VegetableDTO> vegList) {
		this.vegList = vegList;
	}

	@Override
	public String toString() {
		return "Category [catId=" + catId + ", catName=" + catName + ", vegList=" + vegList + "]";
	}

}
