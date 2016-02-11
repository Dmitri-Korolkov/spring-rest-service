package task.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import task.entity.UserDataEntity;
import task.service.UserData;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Controller
public class MainController {

    @Value("${msg.wrongMetod}")
    private String wrongMetod;

    @Value("${msg.unprocessableEntity}")
    private String unprocessableEntity;

    @Value("${msg.success}")
    private String success;

    @Autowired
    private UserData userData;

    private static final Logger log = LoggerFactory.getLogger(MainController.class);

    /**
     * method mapped for main page and error page for Unprocessable Entity
     *
     * @param request
     * @param response
     * @return
     */
    @RequestMapping(value = {"/", "/unprocessableEntity"}, method = RequestMethod.GET)
    @ResponseBody
    public String getMain(HttpServletRequest request, HttpServletResponse response) {

        if (request.getRequestURI().equals("/")) {
            return wrongMetod;
        }
        response.setStatus(422);
        return unprocessableEntity;

    }

    /**
     * method mapped for REST service and validate user data
     *
     * @param request
     * @param respons
     * @param firstName
     * @param lastName
     * @param time
     * @param telephoneNumber
     * @return
     * @throws IOException
     */
    @RequestMapping(value = "/", method = RequestMethod.POST)
    @ResponseBody
    public String getMainPost(HttpServletRequest request, HttpServletResponse respons,
                              @RequestParam(required = false) String firstName,
                              @RequestParam(required = false) String lastName,
                              @RequestParam(required = false) String telephoneNumber) throws IOException {

        try {
            UserDataEntity userDataEntity = new UserDataEntity(firstName, lastName, telephoneNumber);
            if (!userData.saveUserData(request, userDataEntity)) {
                respons.sendRedirect("/unprocessableEntity");
            }
        } catch (Exception e) {
            log.error("empty post");
            respons.sendRedirect("/unprocessableEntity");
        }
        return success;
    }
}
