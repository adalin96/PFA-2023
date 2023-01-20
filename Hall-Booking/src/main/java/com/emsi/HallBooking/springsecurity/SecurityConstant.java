package com.emsi.HallBooking.springsecurity;

public class SecurityConstant {
    public static final long EXPIRATION_TIME = 432_000_000;// 5 days expressed in milliseconds
    // whomever give me a token with the following header is good no need for further verification
    public static final String TOKEN_PREFIX = "Bearer";
    public static final  String JWT_HEADER = "Jwt_Token"; //our costume header
    public static final String TOKEN_VERIFIED = "Token can not be verified";
    public static final String GET_ARRAYS_LLC = "Get Arrays, LLC"; //issuer of the token facebook, google or some other party
    public static final String GET_ARRAYS_ADMINISTRATION = "User Management Portal"; //token given for public use by the company of said app
    public static final String AUTHORITIES = "Authorities";
    public static final String FORBIDDEN_MESSAGE = "You need to log in to access this page ";
    public static final String ACCESS_DENIED_MESSAGE = "You do not have permission to access this page";
    public static final String OPTIIONS_HTTP_METHOS = "OPTIONS"; // sometimes u dont wanna block access
    public static final String[] PUBLIC_URLS = {"/user/login", "/user/register/", "/user/resetpassword/**", "/user/image/**"};
}
