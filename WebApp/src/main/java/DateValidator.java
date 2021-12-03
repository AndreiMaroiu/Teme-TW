import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;

public final class DateValidator
{
    private String outMessage;

    public boolean validateDate(Date date)
    {
        if (date == null)
        {
            return true;
        }

        LocalDate current = LocalDate.now(ZoneId.systemDefault());
        LocalDate temp = date.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();

        if (temp.isAfter(current))
        {
            outMessage = "Date cannot be in future!";
            return false;
        }

        if (temp.getYear() > current.getYear() - 13)
        {
            outMessage = "You are too young! You must be over 13 years old.";
            return false;
        }

        return true;
    }

    public String getOutMessage()
    {
        return outMessage;
    }

    public static Date GetDate(String date)
    {
        Date newDate = null;
        try
        {
            newDate = new SimpleDateFormat("yyyy-MM-dd").parse(date);
        }
        catch (ParseException e)
        {
            e.printStackTrace();
        }

        return newDate;
    }
}
