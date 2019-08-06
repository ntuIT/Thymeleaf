package jp.ivs.mapper;

import jp.ivs.model.Person;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;

import java.util.List;

public interface PersonMapper
{
    @Select("select Username as 'FirstName', Fullname as 'LastName' from users")
    List<Person> getAllPerson();
    @Insert("INSERT INTO `users` (`Username`, `Password`, `Fullname`) VALUES (#{firstName}, ' ', #{lastName})")
    void addNewPerson();
}
