package btrace.test.verifyError;

public class Persons
{
	public Persons()
	{
		super();
	}

	public Person getPerson(String name)
	{
		Person ret = new PersonA(name);
		if (!name.isEmpty())
		{
			ret = new PersonB(name);
		}
		return ret;
	}
}
