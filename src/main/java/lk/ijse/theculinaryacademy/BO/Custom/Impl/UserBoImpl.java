package lk.ijse.theculinaryacademy.BO.Custom.Impl;

import lk.ijse.theculinaryacademy.BO.Custom.UserBo;
import lk.ijse.theculinaryacademy.DAO.Custom.UserDao;
import lk.ijse.theculinaryacademy.DAO.DaoFactory;
import lk.ijse.theculinaryacademy.DTO.UserDto;
import lk.ijse.theculinaryacademy.Entity.User;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class UserBoImpl implements UserBo {
    UserDao userDao = (UserDao) DaoFactory.getDaoFactory().getDaoType(DaoFactory.DaoType.USER);

    @Override
    public boolean save(UserDto userDto) throws IOException {
        User user = new User(
                userDto.getUser_id(),
                userDto.getUsername(),
                userDto.getPassword(),
                userDto.getUser_email(),
                userDto.getUser_phone(),
                userDto.getUser_role()
               );
        return userDao.save(user);
    }

    @Override
    public boolean update(UserDto userDto) throws IOException {
        User user = new User(
                userDto.getUser_id(),
                userDto.getUsername(),
                userDto.getPassword(),
                userDto.getUser_email(),
                userDto.getUser_phone(),
                userDto.getUser_role()
               );
        return userDao.update(user);
    }

    @Override
    public boolean delete(String id) throws IOException {
        return userDao.delete(id);
    }

    @Override
    public UserDto getUser(String id) {
        return null;
    }

    @Override
    public List<User> getUserList() throws IOException {
        List<User> userList = new ArrayList<>();
        List<User> users = userDao.getAll();
        for (User user : users) {
            userList.add(new User(
                    user.getUser_id(),
                    user.getUsername(),
                    user.getPassword(),
                    user.getUser_email(),
                    user.getUser_phone(),
                    user.getUser_role()
                   ));
        }
        return userList;
    }
}
