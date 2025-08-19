package com.mjc.school.controller;

import java.util.Scanner;

import com.mjc.school.service.NewsDto;
import com.mjc.school.service.NewsDtoStorage;



public class Controller {

    private final Scanner scan;
    private final NewsDtoStorage newsDtoStore;

    public Controller(Scanner scanner) {
        scan = scanner;
        newsDtoStore = new NewsDtoStorage();
    }

    public void start() {
        while (true) {
            this.console();
            String command = scan.nextLine();

            if(!command.equals("0") && !command.equals("1") && !command.equals("2") && !command.equals("3") && !command.equals("4") && !command.equals("5")) {
                System.out.println("Command not found");
            }

            if(command.equals("0")) {
                break;
            }

            if(command.equals("1")) {
                System.out.println("Operation: Get all dataSourceModel.");
                newsDtoStore.printAllNewsDto();
            }

            if(command.equals("2")) {
                System.out.println("""
                        Operation: Get dataSourceModel by id.
                        Enter dataSourceModel id:
                        """);
                String id = scan.nextLine();
                try{
                    Long longId = Long.parseLong(id);
                    NewsDto newsDto = newsDtoStore.getNewsDtoById(longId);

                    if(newsDto==null) {
                        newsDtoStore.newsIdNotFound(longId);
                    } else {
                        System.out.println(newsDto);
                    }
                } catch (NumberFormatException e) {
                    newsDtoStore.newsIdNotNumber();
                }
            }

            if(command.equals("3")) {
                while(true) {
                    System.out.println("""
                        Operation: Create dataSourceModel.
                        Enter dataSourceModel title:
                        """);
                    String title = scan.nextLine();

                    System.out.println("Enter dataSourceModel content:");
                    String content = scan.nextLine();

                    System.out.println("Enter author id:");
                    String authorId = scan.nextLine();

                    try{
                        Long longId = Long.parseLong(authorId);
                        if(newsDtoStore.isAuthorIdExist(longId)) {
                            NewsDto newsDto = new NewsDto(title, content, longId);
                            newsDtoStore.createNewsDto(newsDto);
                            break;
                        }else{
                            newsDtoStore.authorIdNotFound(longId);
                            break;
                        }

                    } catch (NumberFormatException e) {
                        newsDtoStore.authorIdNotNumber();
                    }
                }
            }

            if(command.equals("4")) {
                while(true) {
                    Long longNewsId=null;
                    String newsId;
                    while(longNewsId==null) {
                        System.out.println("""
                        Operation: Update dataSourceModel.
                        Enter dataSourceModel id:
                        """);
                        newsId = scan.nextLine();
                        try{
                            longNewsId = Long.parseLong(newsId);
                        } catch (NumberFormatException e) {
                            newsDtoStore.newsIdNotNumber();
                        }
                    }
                    System.out.println("Enter dataSourceModel title:");
                    String title = scan.nextLine();

                    System.out.println("Enter dataSourceModel content:");
                    String content = scan.nextLine();

                    System.out.println("Enter author id:");
                    String authorId = scan.nextLine();

                    try{
                        Long longAuthorId = Long.parseLong(authorId);
                        NewsDto newsDto = new NewsDto(title, content, longAuthorId);
                        if(newsDto.getId()==null) break;
                        if(!newsDtoStore.isAuthorIdExist(longAuthorId)){
                            newsDtoStore.authorIdNotFound(longAuthorId);
                            break;
                        }
                        if(newsDtoStore.isNewsIdExist(longNewsId)) {
                            if(newsDtoStore.isAuthorIdExist(longAuthorId)) {
                                newsDto.setId(longNewsId);
                                newsDtoStore.updateNewsDto(newsDto);
                            }else{
                                newsDtoStore.authorIdNotFound(longAuthorId);
                            }
                            break;
                        }else{
                            newsDtoStore.newsIdNotFound(longNewsId);
                            break;
                        }
                    }catch (NumberFormatException e) {
                        newsDtoStore.authorIdNotNumber();
                    }
                }
            }

            if(command.equals("5")) {
                System.out.println("""
                        Operation: Remove dataSourceModel by id.
                        Enter dataSourceModel id:
                        """);
                String newsId = scan.nextLine();

                try{
                    Long longNewsId = Long.parseLong(newsId);
                    if(newsDtoStore.isNewsIdExist(longNewsId)) {
                        System.out.println(newsDtoStore.deleteNewsDtoById(longNewsId));
                    }else{
                        newsDtoStore.newsIdNotFound(longNewsId);
                    }
                } catch (NumberFormatException e) {
                    newsDtoStore.newsIdNotNumber();
                }
            }
        }
    }

    public void console(){
        System.out.println("""
                Enter the number of operation:
                1 - Get all dataSourceModel.
                2 - Get dataSourceModel by id.
                3 - Create dataSourceModel.
                4 - Update dataSourceModel.
                5 - Remove dataSourceModel by id.
                0 - Exit.
                """);
    }
}