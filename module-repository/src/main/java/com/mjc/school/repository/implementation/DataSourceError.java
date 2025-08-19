package com.mjc.school.repository.implementation;

public class DataSourceError {
    private static Long errorCode = 1L;

    public static boolean isNewsAttributesCorrect(String title, String content) {

        if(title.length() > 30 || title.length() < 5){
            System.out.println("ERROR_CODE: "+String.format("%06d", errorCode++)+" ERROR_MESSAGE: News title can not be less than 5 or more than 30 symbols. News title is " +title);
            return true;
        }
        if(content.length() > 255 || content.length() < 5){
            System.out.println("ERROR_CODE: "+String.format("%06d", errorCode++)+" ERROR_MESSAGE: News content can not be less than 5 or more than 255 symbols. News content is " +content);
            return true;
        }
        return false;
    }

    public static boolean isAuthorLengthCorrect(String name) {
        if(name.length() > 15 || name.length() < 3){
            System.out.println("ERROR_CODE: "+String.format("%06d", errorCode++)+" ERROR_MESSAGE: Author name can not be less than 3 or more than 15 symbols. Author name is " +name);
            return true;
        }
        return false;
    }

    public static void newsIdNotFound(Long id) {
        System.out.println("ERROR_CODE: "+String.format("%06d", errorCode++)+" ERROR_MESSAGE: News with id "+id+" does not exist");
    }
    public static void newsIdNotNumber() {
        System.out.println("ERROR_CODE: "+String.format("%06d", errorCode++)+" ERROR_MESSAGE: News Id should be number");
    }

    public static void authorIdNotNumber() {
        System.out.println("ERROR_CODE: "+String.format("%06d", errorCode++)+" ERROR_MESSAGE: Author Id should be number");
    }
    public static void authorIdNotFound(Long authorId) {
        System.out.println("ERROR_CODE: "+String.format("%06d", errorCode++)+" ERROR_MESSAGE: Author Id does not exist. Author Id is: "+authorId);
    }



}
