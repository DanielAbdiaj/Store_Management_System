package Module;

public class InputCheck {


    public static boolean isInt(String text)
    {
        for(int i = 0; i < text.length(); i++)
        {
            char c = text.charAt(i);
            if(!Character.isDigit(c)) return false;
        }
        return true;
    }

    public static boolean isDouble(String text)
    {
        int dotCounter = 0;
        for(int i = 0; i < text.length(); i++)
        {
            char c = text.charAt(i);

            if(!Character.isDigit(c))
            {
                if(c == '.') dotCounter++;
                else  return false;
                if(dotCounter > 1) return false;
            }
        }
        return true;
    }

    public static boolean isUsernameValid(String text)
    {
        for(int i = 0; i < text.length(); i++)
        {
            char c = text.charAt(i);
            if(!Character.isDigit(c) && !Character.isLetter(c) && c != '-' && c != '_' && c != '.') return false;
        }
        return true;
    }

    public static boolean isPhoneNrValid(String text)
    {
        if(text.length() != 10) return false;

        for(int i = 0; i < text.length(); i++)
        {
            char c = text.charAt(i);
            if(!Character.isDigit(c)) return false;
        }
        return true;
    }

    public static boolean isNameValid(String text)
    {
        for(int i = 0; i < text.length(); i++)
        {
            char c = text.charAt(i);
            if(!Character.isLetter(c)) return false;
        }
        return true;
    }

    public static boolean isSurnameValid(String text)
    {
        for(int i = 0; i < text.length(); i++)
        {
            char c = text.charAt(i);
            if(!Character.isLetter(c)) return false;
        }
        return true;
    }

    public static boolean doesUsernameExists(String text)
    {
        for(Cashier c : DataBase.getCashiers())
            if(text.equals(c.getUsername())) return true;

        for(Manager e : DataBase.getManager())
            if(text.equals(e.getUsername())) return true;

        return false;
    }
}
