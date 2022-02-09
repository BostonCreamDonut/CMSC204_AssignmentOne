import java.util.ArrayList;
import java.util.regex.Pattern;
import java.util.regex.Matcher;
/**
 * @author Michael DeReggi
 * @param String password, a given password
 * @param passwordConfirm, check if matches password
 */

public class PasswordCheckerUtility
{
	//constructors, setters. and getters
	private String password,passwordConfirm,passwords[];
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getPasswordConfirm() {
		return passwordConfirm;
	}
	public void setPasswordConfirm(String passwordConfirm) {
		this.passwordConfirm = passwordConfirm;
	}
	public String[] getPasswords() {
		return passwords;
	}
	public void setPasswords(String[] passwords) {
		this.passwords = passwords;
	}
	/**
	 *@throws unmatched exception when passwords dont match
	 * @param passwordConfirm, check if matches password
	 *@param String password, a given password
	 */
	public static void comparePasswords(String password, String passwordConfirm)
	{
		boolean matches=password.equals(passwordConfirm);
		if(!matches)
		{
			throw new UnmatchedException();
		}
	}
	/**
	 *@return true if passwords and passwordConfirm are the same
	 *@param String password, a given password
	 * @param passwordConfirm, check if matches password
	 */
	public static boolean comparePasswordsWithReturn(String password, String passwordConfirm)
	{
		//compares if password and passwordConfirm are equal
		if(!password.equals(passwordConfirm))
		{
			return false;
		}
		return true;
	}
	/**
	 *@param ArrayList<String> passwords, a list of passwords
	 *@return ArrayList<String> of the invalid
	 *
	 */
	public static ArrayList<String> getInvalidPasswords(ArrayList<String> passwords) 
	{
		ArrayList<String> invalids = new ArrayList<String>();
		for(int i=0;i<passwords.size();i++) 
		{
			try
			{
				//checks if password is valed
				if(!isValidPassword(passwords.get(i)));
				{
					invalids.add(passwords.get(i)+" -> "+isValidPassword(passwords.get(i)));
				}
			}
			//catched the exception, e.getMEssage() put it at end
			catch(Exception e)
			{
				invalids.add(passwords.get(i)+" -> "+e.getMessage());
			}
		}
		return invalids;
		
	}
	/**
	 *@return true if between 6 and 9 length
	 *@param String password, a given password
	 */
	public static boolean hasBetweenSixandNineChars(String password)
	{
		if(password.length()>6&&password.length()<9) //finds if length between 6 and 9
		{
			return true;
		}
		return false;
	}
	/**
	 *@return true if password has digit
	 *@throws NoDigitException when has no digit
	 *@param String password, a given password
	 */
	public static boolean hasDigit(String password)
	{
		for(int i=0;i<password.length();i++)
		{
			if(Character.isDigit(password.charAt(i))) //using Character to find digit
			{
				return true;
			}
		}
		throw new NoDigitException();
	}
	/**
	 *@return true if password has lowercase
	 *@throws NoLowerAlphaException when has no lowercase
	 *@param String password, a given password
	 */
	public static boolean hasLowerAlpha(String password)
	{
		for(int i=0;i<password.length();i++)
		{
			if(Character.isLowerCase(password.charAt(i)))//using Character to find lowercase
			{
				return true;
			}
		}
		throw new NoLowerAlphaException();	
	}
	/**
	 *@return true if password has special character
	 *@throws NoSpecialCharacterException has no special character
	 *@param String password, a given password
	 */
	public static boolean hasSpecialChar(String password)
	{
		Pattern p = Pattern.compile("[^a-z0-9A-Z ]");
		for(int i=0;i<password.length();i++)
		{
			Matcher m = p.matcher(Character.toString(password.charAt(i)));//regex B)
			if((m.matches()))//if matches, will not print exception, if doesnt, throws exception
			{
				return true;
			}
		}
		throw new NoSpecialCharacterException();
		
	}
	/**
	 *@return true if password has uppercase
	 *@throws NoUpperAlphaException when has no uppercase
	 *@param String password, a given password
	 */
	public static boolean hasUpperAlpha(String password)
	{
		for(int i=0;i<password.length();i++)
		{
			if(Character.isUpperCase(password.charAt(i))) ////using Character to find uppercase
			{
				return true;
			}
		}
		throw new NoUpperAlphaException();
		
	}
	/**
	 *@return true if password has length >= 6
	 *@throws LengthException when length is < 6
	 *@param String password, a given password
	 */
	public static boolean isValidLength(String password)
	{
		if(password.length()>=6)//if length at least valid (could still be weak)
		{
			return true;
		}
		else
		{
			throw new LengthException();
		}
		
	}
	/**
	 *@return true if password is valid / passes all requirements
	 *@throws first method that is not true/ throws
	 *@param String password, a given password
	 */
	public static boolean isValidPassword(String password)
	{
		//CHECKS ALL requirements to be valid password, the fail that is checked first will be the one thrown
		if(!isValidLength(password))
		{
			throw new LengthException();
		}
		if(!hasUpperAlpha(password))
		{
			throw new NoUpperAlphaException();
		}
		if(!hasLowerAlpha(password))
		{
			throw new NoLowerAlphaException();
		}
		if(!hasDigit(password))
		{
			throw new NoDigitException();
		}
		if(!hasSpecialChar(password))
		{
			throw new NoSpecialCharacterException();
		}
		if(!NoSameCharInSequence(password))
		{
			throw new InvalidSequenceException();
		}
		return true;
		
	}
	/**
	 *@return false if password is not 6 to 9 length
	 *@throws WeakPasswordException has weak password
	 *@param String password, a given password
	 */
	public static boolean isWeakPassword(String password)
	{
		if(hasBetweenSixandNineChars(password))//if length between 6-9
		{
			throw new WeakPasswordException();
		}
		return false;
		
	}
	/**
	 *@return true if password has two characters the same in a sequence
	 *@throws InvalidSequenceException when has double of character in same sequence
	 *@param String password, a given password
	 */
	public static boolean NoSameCharInSequence(String password)
	{
		for(int i=1;i<password.length();i++)
		{
			if(password.charAt(i)==password.charAt(i-1))//compares index and index-1 to easily see if they equal each other
			{
				throw new InvalidSequenceException();
			}
		}
		return true;
	}
}
@SuppressWarnings("serial")
/**
 *The rest of these are my exceptions
 */
class LengthException extends RuntimeException
{
	public LengthException()
	{
		super("The password must be at least 6 characters long");
	}
}
@SuppressWarnings("serial")
class NoUpperAlphaException extends RuntimeException
{
	public NoUpperAlphaException()
	{
		super("The password must contain at least one uppercase alphabetic character");
	}
}
@SuppressWarnings("serial")
class NoLowerAlphaException extends RuntimeException
{
	public NoLowerAlphaException()
	{
		super("The password must contain at least one lowercase alphabetic character");
	}
}
@SuppressWarnings("serial")
class NoDigitException extends RuntimeException
{
	public NoDigitException()
	{
		super("The password must contain at least one digit");
	}
}
@SuppressWarnings("serial")
class NoSpecialCharacterException extends RuntimeException
{
	public NoSpecialCharacterException()
	{
		super("The password must contain at least one special character");
	}
}
@SuppressWarnings("serial")
class InvalidSequenceException extends RuntimeException
{
	public InvalidSequenceException()
	{
		super(" The password cannot contain more than two of the same character in sequence");
	}
}
@SuppressWarnings("serial")
class WeakPasswordException extends RuntimeException
{
	public WeakPasswordException()
	{
		super("The password is OK but weak - it contains fewer than 10 characters.");
	}
}
@SuppressWarnings("serial")
class UnmatchedException extends RuntimeException
{
	public UnmatchedException()
	{
		super("Passwords do not match");
	}
}
