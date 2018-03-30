package lt.andrius_statkevicius.laivu_musis;

import lt.andrius_statkevicius.laivu_musis.entities.User;
import lt.andrius_statkevicius.laivu_musis.services.UserService;
import org.json.simple.parser.ParseException;

import java.io.IOException;

public class App {

    public static void main(String[] args) throws IOException, ParseException {
        UserService userService = new UserService();
        User user = userService.createUser( "Andrius", "andrius.statkevicius@gmail.com");
    }
}
