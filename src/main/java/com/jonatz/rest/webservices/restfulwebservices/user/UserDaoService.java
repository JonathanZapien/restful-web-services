package com.jonatz.rest.webservices.restfulwebservices.user;

import java.util.ArrayList;
import java.util.List;
import org.springframework.stereotype.Component;

@Component
public class UserDaoService {
  private static final int CHRYS_YEAR_OF_BIRTH = 1984;
  private static final int JOHN_YEAR_OF_BIRTH = 1988;
  private static final int CHRYSTOPHER_YEAR_OF_BIRTH = 1990;
  private static final int SEMMY_YEAR_OF_BIRTH = 1991;
  private static final int NATH_YEAR_OF_BIRTH = 1994;
  private static final int TITA_YEAR_OF_BIRTH = 2004;
  private static final int LIA_YEAR_OF_BIRTH = 2016;
  private static final int CHRYS_DAY_OF_MONTH = 8;
  private static final int JOHN_DAY_OF_MONTH = 25;
  private static final int CHRYSTOPHER_DAY_OF_MONTH = 11;
  private static final int SEMMY_DAY_OF_MONTH = 27;
  private static final int NATH_DAY_OF_MONTH = 31;
  private static final int TITA_DAY_OF_MONTH = 18;
  private static final int LIA_DAY_OF_MONTH = 12;

  private static List<User> users = new ArrayList<>();
  private static int usersCounter = 7;

//  static {
//    users.add(
//        new User(1, "Chrys", LocalDate.of(CHRYS_YEAR_OF_BIRTH, Month.JULY, CHRYS_DAY_OF_MONTH)));
//    users.add(
//        new User(2, "John", LocalDate.of(JOHN_YEAR_OF_BIRTH, Month.SEPTEMBER, JOHN_DAY_OF_MONTH)));
//    users.add(
//        new User(
//            3,
//            "Chrystopher",
//            LocalDate.of(CHRYSTOPHER_YEAR_OF_BIRTH, Month.FEBRUARY, CHRYSTOPHER_DAY_OF_MONTH)));
//    users.add(
//        new User(
//            4, "Semmy", LocalDate.of(SEMMY_YEAR_OF_BIRTH, Month.FEBRUARY, SEMMY_DAY_OF_MONTH)));
//    users.add(
//        new User(5, "Nath", LocalDate.of(NATH_YEAR_OF_BIRTH, Month.OCTOBER, NATH_DAY_OF_MONTH)));
//    users.add(new User(6, "Tita", LocalDate.of(TITA_YEAR_OF_BIRTH, Month.MAY, TITA_DAY_OF_MONTH)));
//    users.add(new User(7, "Lia", LocalDate.of(LIA_YEAR_OF_BIRTH, Month.MAY, LIA_DAY_OF_MONTH)));
//  }

  public List<User> findAll() {
    return users;
  }

  public User findOne(int id) {
    return users.stream().filter(user -> user.getId().equals(id)).findFirst().orElse(null);
  }

  public User save(User user) {
    user.setId(++usersCounter);
    users.add(user);
    return user;
  }

  public void deleteById(int id) {
    users.removeIf(user -> user.getId().equals(id));
  }
}
