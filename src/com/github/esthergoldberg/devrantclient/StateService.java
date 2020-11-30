package com.github.esthergoldberg.devrantclient;


import com.intellij.openapi.components.PersistentStateComponent;
import com.intellij.openapi.components.State;
import com.intellij.openapi.components.Storage;
import com.intellij.util.xmlb.XmlSerializerUtil;


@State(
		name = "com.github.esthergoldberg.devrantclient",
		storages = {@Storage("SdkSettingsPlugin.xml")}
)
public class StateService implements PersistentStateComponent<StateService> {

	public MyCookieStore cookieStoreValue;

	public StateService getState() {
		return this;
	}

	public void loadState(StateService state) {
		XmlSerializerUtil.copyBean(state, this);
	}

	public MyCookieStore getCookieStoreValue() {
		return cookieStoreValue;
	}

	public void SetCookieStoreValue(MyCookieStore cookieStore) {
		this.cookieStoreValue = cookieStore;
	}

}
