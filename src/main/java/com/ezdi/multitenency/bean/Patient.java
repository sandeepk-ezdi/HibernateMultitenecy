package com.ezdi.multitenency.bean;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.Filter;
import org.hibernate.annotations.FilterDef;
import org.hibernate.annotations.Filters;
import org.hibernate.annotations.ParamDef;

@Entity
@FilterDef(name="hospitalFilter",parameters=@ParamDef(name="hospitalId",type="string"))
@Filters(@Filter(name="hospitalFilter", condition="HOSPITAL_ID=:hospitalId"))
@Table(name = "Patients")
public class Patient implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@Column(name = "ID", nullable = false)
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer id;

	@Column(name = "NAME", nullable = false)
	private String name;

	@Column(name = "AGE", nullable = false)
	private Integer age;
	
	@Column(name = "SEX", nullable = false)
	private String sex;

	@Column(name = "CITY", nullable = false)
	private String city;
	
	@Column(name = "HOSPITAL_ID", nullable = false)
	private String hospitalId;
	
	public Patient() {
		// TODO Auto-generated constructor stub
	}
	
	public Patient(String name, Integer age, String sex, String city, String hospitalId) {
		super();
		this.name = name;
		this.age = age;
		this.sex = sex;
		this.city = city;
		this.hospitalId = hospitalId;
	}



	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Integer getAge() {
		return age;
	}

	public void setAge(Integer age) {
		this.age = age;
	}

	public String getSex() {
		return sex;
	}

	public void setSex(String sex) {
		this.sex = sex;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getHospitalId() {
		return hospitalId;
	}

	public void setHospitalId(String hospitalId) {
		this.hospitalId = hospitalId;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + id;
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (!(obj instanceof Patient))
			return false;
		Patient other = (Patient) obj;
		if (id != other.id)
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Patient [id=" + id + ", name=" + name + ", age=" + age + ", sex=" + sex + ", city=" + city
				+ ", hospitalId=" + hospitalId + "]";
	}

	
	
	

}
