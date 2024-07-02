package com.example.csc325_firebase_webview_auth.viewmodel;



import javafx.beans.property.ReadOnlyBooleanProperty;
import javafx.beans.property.ReadOnlyBooleanWrapper;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class AccessDataViewModel {

	private final StringProperty resourceName = new SimpleStringProperty();
	private final StringProperty resourceAddress = new SimpleStringProperty();
	private final StringProperty resourceCity = new SimpleStringProperty();
	private final StringProperty resourceState = new SimpleStringProperty();
	private final StringProperty resourceZip = new SimpleStringProperty();
	private final StringProperty resourceHours = new SimpleStringProperty();
	private final StringProperty resourceUrl = new SimpleStringProperty();

	private final ReadOnlyBooleanWrapper writePossible = new ReadOnlyBooleanWrapper();

	public AccessDataViewModel() {
		writePossible.bind(resourceName.isNotEmpty().and(resourceAddress.isNotEmpty().and(resourceCity.isNotEmpty().and(resourceState.isNotEmpty().and(resourceZip.isNotEmpty())))));
	}

	public StringProperty resourceNameProperty() {
		return resourceName;
	}

	public StringProperty resourceAddressProperty() {
		return resourceAddress;
	}

	public StringProperty resourceCityProperty() {
		return resourceCity;
	}

	public StringProperty resourceStateProperty() {
		return resourceState;
	}

	public StringProperty resourceZipProperty() {
		return resourceZip;
	}

	public StringProperty resourceHoursProperty() {
		return resourceHours;
	}

	public StringProperty resourceUrlProperty() {
		return resourceUrl;
	}

	public ReadOnlyBooleanProperty isWritePossibleProperty() {
		return writePossible.getReadOnlyProperty();
	}
}
