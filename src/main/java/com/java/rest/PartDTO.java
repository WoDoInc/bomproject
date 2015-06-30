package com.java.rest;

/**
 * This data transfer object contains the information of a single Part {@link com.java.rest.Part}
 * entry and specifies validation rules that are used to ensure that only valid information can be saved to the database.
 */
public final class PartDTO {

    private String id;

	// Common
	private String datasheets;
	private	String image;
	private String corporatePartNumber;
	private String manufacturerPartNumber;
	private String manufacturer;
	private String description;
	private String unitPrice; // (USD)
	private String packaging;
	private String tolerance;
	private String type; // Type of part
	
	// Capacitors and Resistors
	private String series;
	private String operatingTemperature;
	private String sizeDimension;
	
	// Capacitors and Diodes
	private String mountingType;
	private String packageCase;
	
	// Capacitors
	private String capacitance;
	private String voltageRating;
	private String lifetimeTemp;
	private String capacitorType;
	private String applications;
	private String rippleCurrent;
	private String impedance;
	private String heightSeated;
	private String surfaceMountLandSize;
	
	// Diodes
	private String voltageZener;
	private String powerMax; // Max Impedance (Zzt)
	private String current; // - Reverse Leakage @ Vr
	private String voltage;
	private String supplierDevicePackage;
	
	// Resistors
	private String resistance; // (Ohms)
	private String power; // (Watts)
	private String composition;
	private String features;
	private String temperatureCoefficient;
	
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

	public String getDatasheets() {
		return datasheets;
	}

	public void setDatasheets(String datasheets) {
		this.datasheets = datasheets;
	}

	public String getImage() {
		return image;
	}

	public void setImage(String image) {
		this.image = image;
	}

	public String getCorporatePartNumber() {
		return corporatePartNumber;
	}

	public void setCorporatePartNumber(String corporatePartNumber) {
		this.corporatePartNumber = corporatePartNumber;
	}

	public String getManufacturerPartNumber() {
		return manufacturerPartNumber;
	}

	public void setManufacturerPartNumber(String manufacturerPartNumber) {
		this.manufacturerPartNumber = manufacturerPartNumber;
	}

	public String getManufacturer() {
		return manufacturer;
	}

	public void setManufacturer(String manufacturer) {
		this.manufacturer = manufacturer;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getUnitPrice() {
		return unitPrice;
	}

	public void setUnitPrice(String unitPrice) {
		this.unitPrice = unitPrice;
	}

	public String getPackaging() {
		return packaging;
	}

	public void setPackaging(String packaging) {
		this.packaging = packaging;
	}

	public String getTolerance() {
		return tolerance;
	}

	public void setTolerance(String tolerance) {
		this.tolerance = tolerance;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getSeries() {
		return series;
	}

	public void setSeries(String series) {
		this.series = series;
	}

	public String getOperatingTemperature() {
		return operatingTemperature;
	}

	public void setOperatingTemperature(String operatingTemperature) {
		this.operatingTemperature = operatingTemperature;
	}

	public String getSizeDimension() {
		return sizeDimension;
	}

	public void setSizeDimension(String sizeDimension) {
		this.sizeDimension = sizeDimension;
	}

	public String getMountingType() {
		return mountingType;
	}

	public void setMountingType(String mountingType) {
		this.mountingType = mountingType;
	}

	public String getPackageCase() {
		return packageCase;
	}

	public void setPackageCase(String packageCase) {
		this.packageCase = packageCase;
	}

	public String getCapacitance() {
		return capacitance;
	}

	public void setCapacitance(String capacitance) {
		this.capacitance = capacitance;
	}

	public String getVoltageRating() {
		return voltageRating;
	}

	public void setVoltageRating(String voltageRating) {
		this.voltageRating = voltageRating;
	}

	public String getLifetimeTemp() {
		return lifetimeTemp;
	}

	public void setLifetimeTemp(String lifetimeTemp) {
		this.lifetimeTemp = lifetimeTemp;
	}

	public String getCapacitorType() {
		return capacitorType;
	}

	public void setCapacitorType(String capacitorType) {
		this.capacitorType = capacitorType;
	}

	public String getApplications() {
		return applications;
	}

	public void setApplications(String applications) {
		this.applications = applications;
	}

	public String getRippleCurrent() {
		return rippleCurrent;
	}

	public void setRippleCurrent(String rippleCurrent) {
		this.rippleCurrent = rippleCurrent;
	}

	public String getImpedance() {
		return impedance;
	}

	public void setImpedance(String impedance) {
		this.impedance = impedance;
	}

	public String getHeightSeated() {
		return heightSeated;
	}

	public void setHeightSeated(String heightSeated) {
		this.heightSeated = heightSeated;
	}

	public String getSurfaceMountLandSize() {
		return surfaceMountLandSize;
	}

	public void setSurfaceMountLandSize(String surfaceMountLandSize) {
		this.surfaceMountLandSize = surfaceMountLandSize;
	}

	public String getVoltageZener() {
		return voltageZener;
	}

	public void setVoltageZener(String voltageZener) {
		this.voltageZener = voltageZener;
	}

	public String getPowerMax() {
		return powerMax;
	}

	public void setPowerMax(String powerMax) {
		this.powerMax = powerMax;
	}

	public String getCurrent() {
		return current;
	}

	public void setCurrent(String current) {
		this.current = current;
	}

	public String getVoltage() {
		return voltage;
	}

	public void setVoltage(String voltage) {
		this.voltage = voltage;
	}

	public String getSupplierDevicePackage() {
		return supplierDevicePackage;
	}

	public void setSupplierDevicePackage(String supplierDevicePackage) {
		this.supplierDevicePackage = supplierDevicePackage;
	}

	public String getResistance() {
		return resistance;
	}

	public void setResistance(String resistance) {
		this.resistance = resistance;
	}

	public String getPower() {
		return power;
	}

	public void setPower(String power) {
		this.power = power;
	}

	public String getComposition() {
		return composition;
	}

	public void setComposition(String composition) {
		this.composition = composition;
	}

	public String getFeatures() {
		return features;
	}

	public void setFeatures(String features) {
		this.features = features;
	}

	public String getTemperatureCoefficient() {
		return temperatureCoefficient;
	}

	public void setTemperatureCoefficient(String temperatureCoefficient) {
		this.temperatureCoefficient = temperatureCoefficient;
	}

	@Override
	public String toString() {
		return "PartDTO [id=" + id + ", datasheets=" + datasheets + ", image=" + image + ", corporatePartNumber="
				+ corporatePartNumber + ", manufacturerPartNumber=" + manufacturerPartNumber + ", manufacturer="
				+ manufacturer + ", description=" + description + ", unitPrice=" + unitPrice + ", packaging="
				+ packaging + ", tolerance=" + tolerance + ", type=" + type + ", series=" + series
				+ ", operatingTemperature=" + operatingTemperature + ", sizeDimension=" + sizeDimension
				+ ", mountingType=" + mountingType + ", packageCase=" + packageCase + ", capacitance=" + capacitance
				+ ", voltageRating=" + voltageRating + ", lifetimeTemp=" + lifetimeTemp + ", capacitorType="
				+ capacitorType + ", applications=" + applications + ", rippleCurrent=" + rippleCurrent + ", impedance="
				+ impedance + ", heightSeated=" + heightSeated + ", surfaceMountLandSize=" + surfaceMountLandSize
				+ ", voltageZener=" + voltageZener + ", powerMax=" + powerMax + ", current=" + current + ", voltage="
				+ voltage + ", supplierDevicePackage=" + supplierDevicePackage + ", resistance=" + resistance
				+ ", power=" + power + ", composition=" + composition + ", features=" + features
				+ ", temperatureCoefficient=" + temperatureCoefficient + "]";
	}
}
