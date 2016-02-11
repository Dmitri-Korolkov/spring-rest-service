package task.service;

import task.entity.UserDataEntity;

import javax.servlet.http.HttpServletRequest;

public interface UserData {
    public boolean saveUserData(HttpServletRequest request, UserDataEntity entity);
}
