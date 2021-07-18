package jm.task.core.jdbc;

import jm.task.core.jdbc.service.UserService;
import jm.task.core.jdbc.service.UserServiceImpl;


public class Main {
    private static final UserService userService = new UserServiceImpl();
    public static void main(String[] args) {
        userService.createUsersTable();
        userService.saveUser("Serg","Denisov", (byte) 32);
        userService.saveUser("Oleg","Ivanov", (byte) 34);
        userService.saveUser("Alex","Petrov",(byte) 29);
        userService.saveUser("Olga","Sidorova", (byte) 24);
        userService.getAllUsers();
        userService.cleanUsersTable();
        userService.dropUsersTable();
    }
}
