package DAO;

import javax.persistence.*;
import java.text.SimpleDateFormat;
import java.util.Date;

@Entity
@Table(name = "user")
public class User
{
    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "username")
    private String username;

    @Column(name = "password")
    private String password;

    @Column(name = "name")
    private String name;

    @Column(name = "address")
    private String address;

    @Column(name = "birthday")
    private Date birthday;

    public Integer getId()
    {
        return id;
    }

    public String getUsername()
    {
        return username;
    }

    public String getPassword()
    {
        return password;
    }

    public void setId(Integer id)
    {
        this.id = id;
    }

    public void setUsername(String username)
    {
        this.username = username;
    }

    public void setPassword(String password)
    {
        this.password = password;
    }

    public String getAddress()
    {
        return address;
    }

    public Date getBirthday()
    {
        return birthday;
    }

    public String getBirthdayFormated()
    {
        if (birthday == null)
        {
            return null;
        }

        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
        return formatter.format(birthday);
    }

    public void setAddress(String address)
    {
        this.address = address;
    }

    public void setBirthday(Date birthday)
    {
        if (birthday != null)
        {
            this.birthday = birthday;
        }
    }

    public String getName()
    {
        return name;
    }

    public void setName(String name)
    {
        this.name = name;
    }
}
