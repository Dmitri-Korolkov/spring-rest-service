package task.service.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import task.entity.UserDataEntity;
import task.service.UserData;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.Date;

@Service
public class UserDataImpl implements UserData {

    private static final Logger log = LoggerFactory.getLogger(UserDataImpl.class);

    /**
     * save user data
     *
     * @param request
     * @param entity
     * @return
     */
    public boolean saveUserData(HttpServletRequest request, UserDataEntity entity) {

        String telephoneNumber = validateTelephoneNumber(entity.getTelephoneNumber());

        if (entity.getFirstName().length() > 30) {
            log.error("length of first name > 30 or empty " + entity.toString());
            return false;
        } else if (entity.getLastName().length() > 30 || entity.getLastName().isEmpty()) {
            log.error("length of last name > 30 or empty " + entity.toString());
            return false;
        } else if (telephoneNumber == null || entity.getTelephoneNumber().isEmpty()) {
            log.error("wrong Telephone Number or empty " + entity.toString());
            return false;
        } else {
            entity.setTelephoneNumber(telephoneNumber);
        }

        String fileName;

        if (!entity.getFirstName().isEmpty()) {
            fileName = entity.getLastName().replace(" ", "-").toUpperCase() + "_"
                    + entity.getFirstName().replace(" ", "-").toUpperCase() + ".txt";
        } else {
            fileName = entity.getLastName().replace(" ", "-").toUpperCase() + ".txt";
        }

        File file = new File(request.getServletContext().getRealPath("/WEB-INF/") + fileName);

        PrintWriter out = null;
        try {
            if (!file.exists()) {
                file.createNewFile();
            }

            out = new PrintWriter(file.getAbsoluteFile());
            out.println("Telephone number: " + entity.getTelephoneNumber());
            out.println("First name: " + entity.getFirstName());
            out.println("Last name: " + entity.getLastName());

            Date dNow = new Date();
            SimpleDateFormat ft = new SimpleDateFormat("HH:mm:ss");
            out.println("Time: " + ft.format(dNow));


        } catch (Exception e) {
            log.error("error on write file, error:" + e);
            log.error("entity" + entity.toString());
            return false;
        } finally {
            out.close();
        }

        return true;
    }

    /**
     * validate Telephone Number
     *
     * @param telephoneNumber
     * @return
     */
    private String validateTelephoneNumber(String telephoneNumber) {

        if (telephoneNumber.startsWith("00")) {
            telephoneNumber = telephoneNumber.replaceFirst("00", "");
        }

        telephoneNumber = telephoneNumber.replaceAll("\\(", "");
        telephoneNumber = telephoneNumber.replaceAll("\\)", "");
        telephoneNumber = telephoneNumber.replaceAll("-", "");
        telephoneNumber = telephoneNumber.replaceAll("\\+", "");
        telephoneNumber = telephoneNumber.replaceAll(" ", "");

        try {
            long number = Long.valueOf(telephoneNumber);
        } catch (Exception e) {
            return null;
        }

        StringBuffer buffer = new StringBuffer(telephoneNumber);

        switch (telephoneNumber.length()) {
            case 9:
                telephoneNumber = "00420 " + buffer.insert(3, " ").insert(7, " ").toString();
                break;
            case 12:
                telephoneNumber = "00" + buffer.insert(3, " ").insert(7, " ").insert(11, " ").toString();
                break;
            default:
                return null;
        }
        return telephoneNumber;
    }
}
