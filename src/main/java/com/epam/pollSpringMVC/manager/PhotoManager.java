package com.epam.pollSpringMVC.manager;

import com.epam.pollSpringMVC.db.DBConnectionProvider;
import com.epam.pollSpringMVC.models.Photo;
import com.epam.pollSpringMVC.models.User;
import com.epam.pollSpringMVC.repository.PhotoRepository;
import org.apache.commons.io.IOUtils;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.sql.*;
import java.util.UUID;

public class PhotoManager implements PhotoRepository<Photo, Integer> {
    Connection connection;

    public PhotoManager() {
        connection = DBConnectionProvider.getInstance().getConnection();
    }

    String imageUploadDir = "C:\\Users\\Art\\IdeaProjects\\pollSpringMVC\\src\\main\\webapp\\static\\images\\";

    @Override
    public void addPhoto(Photo o) {

    }

    @Override
    public Photo getPhotoByUser(int id) {

        Photo photo = new Photo();
        try {
            String query = "SELECT * FROM `plain`.`photo` WHERE `id` = ?; ";
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setInt(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                photo.setId(resultSet.getInt("id"));
                photo.setName(resultSet.getString("name"));

            }

        } catch (SQLException e) {
            throw new RuntimeException("Oops, something went wrong during create");
        }

        return photo;
    }

    @Override
    public boolean getByName(String imageName) {
        return false;
    }

    @Override
    public int addPhoto(MultipartFile multipartFile, User user, String s) throws IOException {
        int photoId = 0;
        String imageName = multipartFile.getOriginalFilename();
        imageName = UUID.randomUUID() + "_" + imageName;
        Photo photo = new Photo();
        File file = new File(imageUploadDir, imageName);
        assert false;
        multipartFile.transferTo(file);
        photo.setName(imageName);
        try {
            String query = "INSERT INTO `plain`.`photo` (`name`) VALUES (?);";
            PreparedStatement preparedStatement = connection.prepareStatement(query, Statement.RETURN_GENERATED_KEYS);
            preparedStatement.setString(1, photo.getName());
            preparedStatement.executeUpdate();
            ResultSet rs = preparedStatement.getGeneratedKeys();
            if (rs.first()) {
                photoId = Integer.parseInt(rs.getString(1));
            }
        } catch (
                SQLException e) {
            throw new RuntimeException("Oops, something went wrong during create");
        }
        return photoId;

    }

    public byte[] getImage(String name) {
        InputStream in = null;
        try {
            in = new FileInputStream(imageUploadDir + name);
            return IOUtils.toByteArray(in);
        } catch (IOException ignored) {

        }
        return null;
    }
}
