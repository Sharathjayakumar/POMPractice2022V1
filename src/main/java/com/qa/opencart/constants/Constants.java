package com.qa.opencart.constants;

import java.util.Arrays;
import java.util.List;

public class Constants {

	
	public static final String LOGIN_PAGE_TITLE = "Account Login";
	public static final String LOGIN_PAGE_URL_FRACTION = "route=account/login";
	public static final String ACCOUNT_PAGE_TITLE = "My Account";
	public static final String ACCOUNT_PAGE_URL_FRACTION = "route=account/account";

	
	public static final int DEFAULT_ELEMENT_TIMEOUT = 10;
	public static final int DEFAULT_TIMEOUT = 10;
	public static final String ACCOUNT_PAGE_HEADER = "Your Store";
	public static final List<String> EXPECTED_ACCOUNT_PAGE_LIST = 
			Arrays.asList("My Account","My Orders","My Affiliate Account","Newsletter");
	public static final String LOGOUT_SUCCESS_MESSAGE = "Account Logout";
	public static final CharSequence ACCOUNT_REGISTER_SUCCESS_MESSAGE = "Your Account Has Been Created";

	//************** Sheet Names ********************//
	
	public static final String REGISTER_SHEET_NAME = "Sheet 1";

}
