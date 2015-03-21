package fi.floweb.prizr.beans;

import java.io.Serializable;

public class MultiplierBase implements Serializable {

	private static final long serialVersionUID = -3304031619236159475L;
	
	String id; // unique id
	String multiplierBaseName;  // hintakertoimen nimi
	String multiplierBaseDescription; // kuvaus mihin hintakerrointa sovelletaan
	String appliesToCategory; // mihin kategoriaan kerrointa sovelletaan.
	String appliesToLocation; // mihin toimipisteeseen mätsää
	boolean isDomestic; // onko kotimaahan vai ulkomaille
	String countryCode; 
	boolean includesFreight; // sisällytetäänkö rahti hintaan
	double multiplier; // säännön kerroin kerroin
	
	public MultiplierBase() {
	}
	
	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getMultiplierBaseName() {
		return multiplierBaseName;
	}
	public void setMultiplierBaseName(String multiplierBaseName) {
		this.multiplierBaseName = multiplierBaseName;
	}
	public String getMultiplierBaseDescription() {
		return multiplierBaseDescription;
	}
	public void setMultiplierBaseDescription(String multiplierBaseDescription) {
		this.multiplierBaseDescription = multiplierBaseDescription;
	}
	public String getAppliesToCategory() {
		return appliesToCategory;
	}
	public void setAppliesToCategory(String appliesToCategory) {
		this.appliesToCategory = appliesToCategory;
	}
	public String getAppliesToLocation() {
		return appliesToLocation;
	}
	public void setAppliesToLocation(String appliesToLocation) {
		this.appliesToLocation = appliesToLocation;
	}
	public boolean isDomestic() {
		return isDomestic;
	}
	public void setDomestic(boolean isDomestic) {
		this.isDomestic = isDomestic;
	}
	
	public String getCountryCode() {
		return countryCode;
	}

	public void setCountryCode(String countryCode) {
		this.countryCode = countryCode;
	}

	public boolean isIncludesFreight() {
		return includesFreight;
	}
	public void setIncludesFreight(boolean includesFreight) {
		this.includesFreight = includesFreight;
	}
	public double getMultiplier() {
		return multiplier;
	}
	public void setMultiplier(double multiplier) {
		this.multiplier = multiplier;
	}
	
}
