package core;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardOpenOption;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

public class RewriteDB {
    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd.MM.yyyy");
    private String[] user;
    private String lastname;
    private String surname;
    private String name;
    private LocalDate birthDate;
    private long phoneNumber;
    private String sex;
    private Path file;


    public void setFile(String pathToFile, String nameOfFile) {
        this.file = Path.of(pathToFile + nameOfFile + ".txt");
    }

    public Path getFile() {
        return this.file;
    }

    public void parseString(String userString) throws ArrayDataError {
        this.user = userString.split(" ");
        if (this.user.length != 6) {
            throw new ArrayDataError("Введено неправильное количество данных!");
        }
    }

    public void convertString() throws DateTimeParseException, NumberFormatException {
        this.lastname = user[0];
        this.surname = user[1];
        this.name = user[2];
        this.birthDate = LocalDate.parse(user[3], formatter);
        this.phoneNumber = Long.parseLong(user[4]);
        this.sex = user[5];
    }

    public String[] getUser() {
        return user;
    }

    public void createDbFile(Path dbFile) throws Exception {
        if (!Files.exists(dbFile)) {
            Files.createFile(dbFile);
            if (Files.isRegularFile(dbFile)) {
                System.out.println("Файл \"" + dbFile + "\" успешно создан!");
            } else {
                System.out.println("Файл \"" + dbFile + "\" не создан!");
            }
        }
    }

    public void addUser(Path pathToFile) throws IOException {
        StringBuilder userString = new StringBuilder();
        if (Files.size(pathToFile) == 0) {
            userString.append(this.lastname).append(" ").append(this.surname).append(" ").append(this.name).append(" ").
                    append(this.birthDate).append(" ").append(this.phoneNumber).append(" ").append(this.sex);
        } else
            userString.append("\n").append(this.lastname).append(" ").append(this.surname).append(" ").append(this.name)
                    .append(" ").append(this.birthDate).append(" ").append(this.phoneNumber).append(" ").append(this.sex);

        Files.writeString(pathToFile, userString.toString(), StandardOpenOption.APPEND);
    }

}


